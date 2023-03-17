package lance5057.compendium.core.workstations._bases.blockentities;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lance5057.compendium.Compendium;
import lance5057.compendium.core.workstations._bases.recipes.AnimatedRecipeItemUse;
import lance5057.compendium.core.workstations._bases.recipes.multitoolrecipe.MultiToolRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public abstract class MultiToolRecipeStation<V extends MultiToolRecipe> extends BlockEntity {
	protected final LazyOptional<IItemHandlerModifiable> handler = LazyOptional.of(this::createInteractionHandler);
	// private ItemStack ghostStack = ItemStack.EMPTY;

	public List<V> currentRecipes = new ArrayList<V>();
	public boolean recipeLocked = false;
	private ItemStack lastUsed = ItemStack.EMPTY;
	private int progress;
//	private int maxProgress;
//	private Ingredient curTool;
//	public int toolCount;
	public int stage = 0;
	public final int width;
	public final int height;
	public final int numSlots;

	public MultiToolRecipeStation(int slots, int width, int height, BlockEntityType<?> tileEntityTypeIn, BlockPos pos,
			BlockState state) {
		super(tileEntityTypeIn, pos, state);

		this.width = width;
		this.height = height;
		this.numSlots = slots;
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (side != Direction.DOWN)
			if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
				return handler.cast();
			}

		LazyOptional<T> extra = getExtraCapability(cap, side);
		if (extra != null)
			return extra;

		return super.getCapability(cap, side);
	}

	public boolean isSlotEmpty(int slot) {
		return handler.map(h -> h.getStackInSlot(slot).isEmpty()).get();
	}

	protected abstract <T> LazyOptional<T> getExtraCapability(@Nonnull Capability<T> cap, @Nullable Direction side);

	protected abstract List<V> matchRecipe();

	public void setRecipe(List<V> r) {

		if (!r.isEmpty()) {
			this.setupStage(0);
		} else
			this.zeroProgress();
	}

	protected abstract IItemHandlerModifiable createInteractionHandler();

	public void zeroProgress() {
		this.progress = 0;
		this.stage = 0;
		this.currentRecipes.clear();
	}

	public List<AnimatedRecipeItemUse> getCurrentTools() {
		List<V> currentRecipe = matchRecipe();
		List<AnimatedRecipeItemUse> tools = new ArrayList<AnimatedRecipeItemUse>();
		if (!currentRecipe.isEmpty())
			for (V a : currentRecipe)
				tools.add(a.getToolList().get(stage));
		return null;
	}

	public void updateInventory() {
		requestModelDataUpdate();
		this.setChanged();
		if (this.getLevel() != null) {
			this.getLevel().sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
		}
	}

	protected void setupStage(int i) {

		this.progress = 0;
		this.stage = i;
	}

	void validateStage(Player player, ItemStack curTool) {
		for (int i = 0; i < this.currentRecipes.size(); i++) {
			V r = currentRecipes.get(i);

			if (r.getRecipeTools().size() < stage + 1) {
				AnimatedRecipeItemUse a = r.getRecipeTools().get(stage);
				AnimatedRecipeItemUse b = r.getRecipeTools().get(stage + 1);

				if (a.tool.test(lastUsed)) {
					if (b.tool.test(curTool)) {
						setupStage(stage + 1);
						break;
					}
				}
			} else {
				if (this.validateFinalStage())
					this.finishRecipe(player, r);
			}
		}
	}

	// by this point there should be only one recipe left, if not something went
	// wrong and an error should be thrown
	boolean validateFinalStage() {
		if (this.currentRecipes.size() > 1) {
			Compendium.logger.error(
					"MultiToolRecipeStation finished a recipe with more than one recipe in list, this should not have happened!");
			return false;
		}
		return true;
	}

	List<V> validateRecipes(ItemStack curTool) {
		List<V> o = new ArrayList<V>();

		for (int i = 0; i < this.currentRecipes.size(); i++) {
			V r = currentRecipes.get(i);

			AnimatedRecipeItemUse a = r.getRecipeTools().get(stage);
			if (!(a.uses < this.progress || !a.tool.test(curTool))) {
				o.add(currentRecipes.get(i));
			}
		}
		return o;
	}

	public InteractionResult hammer(Player player, ItemStack hammer) {
		this.validateStage(player, hammer);
		List<V> r = validateRecipes(hammer);

		// if r is empty consider it a misfire and dont do anything
		if (!r.isEmpty()) {
			currentRecipes = r;
			this.lastUsed = hammer;
		}

//		currentRecipe.ifPresent(r -> {
//
//			if (this.curTool == null) {
//				setupStage(r, stage);
//			}
//			if (this.curTool.test(hammer))
//				if (hammer.getCount() >= this.toolCount) {
//
//					if (this.progress >= this.maxProgress) {
//
//						if (isFinalStage(r)) {
//
//							for (int i = 0; i < 5; i++) {
//								addParticle();
//							}
//							level.playSound(Player, worldPosition, SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1, 0);
//
//							if (hammer.isDamageableItem())
//								hammer.hurtAndBreak(1, Player, null);
//							else
//								hammer.setCount(hammer.getCount() - this.toolCount);
//
//							this.finishRecipe(Player, r);
//						} else {
//							setupStage(r, stage + 1);
//						}
//					} else {
//						if (hammer.isDamageableItem())
//							hammer.hurtAndBreak(1, Player, null);
//						else
//							hammer.setCount(hammer.getCount() - this.toolCount);
//
//						progress++;
//					}
//
//				}
//		});
		this.updateInventory();

		return InteractionResult.SUCCESS;
	}

	public abstract void addParticle();

	public abstract void finishRecipe(Player Player, V recipe);

	protected void craft() {
		this.handler.ifPresent(it -> {
			for (int i = 0; i < width * height; i++) {
				ItemStack stack = it.getStackInSlot(i);
				stack.setCount(stack.getCount() - 1);
				it.setStackInSlot(i, stack);
			}
		});
	}

	protected void dropItems(ItemStack... item) {
		BlockEntity te = level.getBlockEntity(this.getBlockPos().offset(0, -1, 0));

		for (int i = 0; i < item.length; i++) {
			ItemStack s = item[i];
			ItemStack sf = item[i];

			if (te != null) {
				LazyOptional<IItemHandler> ih = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
						Direction.UP);

				s = ih.map(h2 -> dropItemBelow(h2, sf)).get();
			}
			level.addFreshEntity(
					new ItemEntity(level, getBlockPos().getX(), getBlockPos().getY() + 0.5f, getBlockPos().getZ(), s));
		}
	}

	protected ItemStack dropItemBelow(IItemHandler InteractionHandler, ItemStack insert) {
		for (int i = 0; i < InteractionHandler.getSlots(); i++) {
			insert = InteractionHandler.insertItem(i, insert, false);

			if (insert.isEmpty()) {
				return ItemStack.EMPTY;
			}
		}

		return insert;
	}

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag nbt = super.getUpdateTag();

		writeNBT(nbt);

		return nbt;
	}

	@Override
	public void handleUpdateTag(CompoundTag tag) {
		readNBT(tag);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		CompoundTag tag = new CompoundTag();

		writeNBT(tag);

		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
		CompoundTag tag = pkt.getTag();
		// InteractionHandle your Data
		readNBT(tag);
	}

	void readNBT(CompoundTag nbt) {
		final IItemHandler itemInteractionHandler = getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				.orElseGet(this::createInteractionHandler);
		((ItemStackHandler) itemInteractionHandler).deserializeNBT(nbt.getCompound("inventory"));

		this.stage = nbt.getInt("stage");

		readExtraNBT(nbt);
	}

	protected abstract void readExtraNBT(CompoundTag nbt);

	CompoundTag writeNBT(CompoundTag tag) {

		IItemHandler itemInteractionHandler = getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				.orElseGet(this::createInteractionHandler);
		tag.put("inventory", ((ItemStackHandler) itemInteractionHandler).serializeNBT());

		tag.putInt("stage", stage);

		writeExtraNBT(tag);

		return tag;
	}

	// External extract handler
	public void extractItem(Player playerEntity) {
		handler.ifPresent(inventory -> this.extractItem(playerEntity, inventory));
	}

	// External insert handler
	public void insertItem(ItemStack heldItem) {
		handler.ifPresent(inventory -> this.insertItem(inventory, heldItem));
	}

	public void extractItem(Player playerEntity, IItemHandler inventory) {
	}

	public void insertItem(IItemHandler inventory, ItemStack heldItem) {
	}

	protected abstract CompoundTag writeExtraNBT(CompoundTag tag);

	@Override
	public void load(@Nonnull CompoundTag nbt) {
		super.load(nbt);
		readNBT(nbt);
	}

	@Override
	@Nonnull
	public void saveAdditional(@Nonnull CompoundTag nbt) {
		super.saveAdditional(nbt);
		writeNBT(nbt);
		// return nbt;
	}
}
