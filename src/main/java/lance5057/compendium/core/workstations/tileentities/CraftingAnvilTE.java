package lance5057.compendium.core.workstations.tileentities;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lance5057.compendium.CompendiumTileEntities;
import lance5057.compendium.core.util.recipes.WorkstationRecipeWrapper;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations.containers.CraftingAnvilContainer;
import lance5057.compendium.core.workstations.recipes.CraftingAnvilRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class CraftingAnvilTE extends TileEntity implements INamedContainerProvider {
	private final LazyOptional<IItemHandlerModifiable> handler = LazyOptional.of(this::createHandler);
	private ItemStack ghostStack = ItemStack.EMPTY;
	public int progress = 0;
	public int maxProgress = 0;

	public CraftingAnvilTE() {
		super(CompendiumTileEntities.CRAFTING_ANVIL_TE.get());
	}

	@Nullable
	@Override
	public Container createMenu(int windowID, PlayerInventory playerInventory, PlayerEntity playerEntity) {

		CraftingAnvilContainer c = handler.map(i -> {
			return CraftingAnvilContainer.createContainerServerSide(windowID, playerInventory, (ItemStackHandler) i, this.progress);
		}).get();

		return c;
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (side != Direction.DOWN)
			if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
				return handler.cast();
			}
		return super.getCapability(cap, side);
	}

	private Optional<CraftingAnvilRecipe> matchRecipe() {

		if (world != null) {
			Optional<CraftingAnvilRecipe> recipe = handler.map(i -> {
				return world.getRecipeManager().getRecipe(WorkstationRecipes.CRAFTING_ANVIL_RECIPE, new WorkstationRecipeWrapper(5, 5, i), world);
			}).get();
			return recipe;
		}
		return null;
	}

	private IItemHandlerModifiable createHandler() {
		return new ItemStackHandler(26) {
			@Override
			protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
				return stack.getMaxStackSize();
			}

			@Override
			protected void onContentsChanged(int slot) {
				updateInventory();
				if (slot != 25) {

					zeroProgress();
					Optional<CraftingAnvilRecipe> recipe = matchRecipe();

					setGhostStack(ItemStack.EMPTY);
					recipe.ifPresent(r -> {
						setGhostStack(r.getRecipeOutput().copy());
					});
				}
			}
		};
	}

	public void zeroProgress() {
		this.progress = 0;
		this.maxProgress = 0;
	}

	public void setGhostStack(ItemStack i) {
		this.ghostStack = i;
	}

	public ItemStack getGhostStack() {
		return this.ghostStack;
	}

	public void updateInventory() {

		requestModelDataUpdate();
		this.markDirty();
		if (this.getWorld() != null) {
			this.getWorld().notifyBlockUpdate(pos, this.getBlockState(), this.getBlockState(), 3);
		}
	}

	public void hammer(PlayerEntity playerEntity, ItemStack hammer) {
		Optional<CraftingAnvilRecipe> recipe = matchRecipe();
		recipe.ifPresent(r -> {
			this.maxProgress = r.getStrikes();
			if (this.progress >= r.getStrikes()) {

				// craft();
				for (int i = 0; i < 5; i++) {
					world.addParticle(new ItemParticleData(ParticleTypes.ITEM, ghostStack), pos.getX() + 0.5f, pos.getY() + 1, pos.getZ() + 0.5f, (world.rand.nextFloat() - 0.5f) / 2, (world.rand.nextFloat() - 0.5f) / 2, (world.rand.nextFloat() - 0.5f) / 2);
				}
				world.playSound(playerEntity, pos, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 1, 0);
				hammer.damageItem(1, playerEntity, null);

				handler.ifPresent(h -> {
					ItemStack item = r.getRecipeOutput().copy();
					TileEntity te = world.getTileEntity(this.getPos().add(0, -1, 0));
					if (te != null) {
						LazyOptional<IItemHandler> ih = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP);

						if (h.getStackInSlot(25) == ItemStack.EMPTY) {

							item = ih.map(h2 -> dropItemBelow(h2, r.getRecipeOutput().copy())).get();
							if (item == null)
								craft();

						}
					}

					if (h.getStackInSlot(25) == ItemStack.EMPTY) {
						h.setStackInSlot(25, item);
						craft();
					}
				});
			} else
				progress++;
		});
		this.updateInventory();

	}

	void craft() {
		progress = 0;
		this.handler.ifPresent(it -> {
			for (int i = 0; i < 25; i++) {
				ItemStack stack = it.getStackInSlot(i);
				stack.setCount(stack.getCount() - 1);
				it.setStackInSlot(i, stack);
			}
		});
	}

	ItemStack dropItemBelow(IItemHandler handler, ItemStack insert) {
		for (int i = 0; i < handler.getSlots(); i++) {
			insert = handler.insertItem(i, insert, false);

			if (insert.isEmpty()) {
				return ItemStack.EMPTY;
			}
		}

		return insert;
	}

//	@Nullable
//	@Override
//	public SUpdateTileEntityPacket getUpdatePacket() {
//		CompoundNBT nbt = this.getUpdateTag();
//		return new SUpdateTileEntityPacket(this.getPos(), -1, nbt);
//	}

//    @Override
//    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
//        handleUpdateTag(pkt.getNbtCompound());
//    }

//	@Override
//	@Nonnull
//	public CompoundNBT getUpdateTag() {
//		CompoundNBT updateTag = new CompoundNBT();
//		updateTag.putInt("progress", progress);
//		final IItemHandler itemHandler = getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseGet(this::createHandler);
//		// CompoundNBT inv = new CompoundNBT();
//
//		updateTag.put("items", ((ItemStackHandler) itemHandler).serializeNBT());
//		return updateTag;
//	}
//
//	@Override
//	public void handleUpdateTag(BlockState state, CompoundNBT tag) {
//		final IItemHandler itemHandler = getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseGet(this::createHandler);
//		((ItemStackHandler) itemHandler).deserializeNBT(tag.getCompound("items"));
//		
//		this.progress = tag.getInt("progress");
//	}
	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT nbt = super.getUpdateTag();

		writeNBT(nbt);

		return nbt;
	}

	@Override
	public void handleUpdateTag(BlockState state, CompoundNBT tag) {
		readNBT(tag);
	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT tag = new CompoundNBT();

		writeNBT(tag);

		return new SUpdateTileEntityPacket(getPos(), 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		CompoundNBT tag = pkt.getNbtCompound();
		// Handle your Data
		readNBT(tag);
	}

	void readNBT(CompoundNBT nbt) {
		final IItemHandler itemHandler = getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseGet(this::createHandler);
		((ItemStackHandler) itemHandler).deserializeNBT(nbt.getCompound("inventory"));

		this.progress = nbt.getInt("progress");
		this.maxProgress = nbt.getInt("maxProgress");

		this.ghostStack = ItemStack.read(nbt.getCompound("ghost"));
	}

	CompoundNBT writeNBT(CompoundNBT tag) {
		// CompoundNBT tag = new CompoundNBT();
		// Write your data into the nbtTag

		IItemHandler itemHandler = getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseGet(this::createHandler);
		tag.put("inventory", ((ItemStackHandler) itemHandler).serializeNBT());

		tag.putInt("progress", progress);
		tag.putInt("maxProgress", maxProgress);

		tag.put("ghost", this.ghostStack.serializeNBT());

		return tag;
	}

	@Override
	public void read(BlockState state, @Nonnull CompoundNBT nbt) {
		super.read(state, nbt);
		readNBT(nbt);
	}

	@Override
	@Nonnull
	public CompoundNBT write(@Nonnull CompoundNBT nbt) {
		super.write(nbt);
		writeNBT(nbt);
		return nbt;
	}

	private static final ITextComponent CONTAINER_NAME = new TranslationTextComponent("compendium.workstations.crafting_anvil");

	@Override
	public ITextComponent getDisplayName() {
		// TODO Auto-generated method stub
		return CONTAINER_NAME;
	}
}
