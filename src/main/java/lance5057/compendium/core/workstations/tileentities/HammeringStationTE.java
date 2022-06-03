//package lance5057.compendium.core.workstations.tileentities;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//import lance5057.compendium.CompendiumTileEntities;
//import lance5057.compendium.core.workstations.recipes.HammeringStationRecipe;
//import net.minecraft.core.Direction;
//import net.minecraft.core.particles.ParticleTypes;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.world.Containers;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraftforge.common.capabilities.Capability;
//import net.minecraftforge.common.util.LazyOptional;
//import net.minecraftforge.items.CapabilityItemHandler;
//import net.minecraftforge.items.IItemHandler;
//import net.minecraftforge.items.ItemStackHandler;
//
//public class HammeringStationTE extends BlockEntity {
//	private final LazyOptional<IItemHandler> optional;
//	ItemStackHandler InteractionHandler;
//	private ItemStack lastStack = ItemStack.EMPTY;
//	private int progress = 0;
//
//	public HammeringStationTE() {
//		super(CompendiumTileEntities.HAMMERING_STATION_TE.get());
//		InteractionHandler = createHandler();
//		optional = LazyOptional.of(() -> InteractionHandler);
//	}
//
//	@Nonnull
//	@Override
//	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
//		if (side != Direction.DOWN)
//			if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
//				return optional.cast();
//			}
//		return super.getCapability(cap, side);
//	}
//
//	private HammeringStationRecipe matchRecipe(ItemStack stackInSlot) {
//		if (level != null) {
//			return level.getRecipeManager().getRecipes().stream()
//					.filter(recipe -> recipe instanceof HammeringStationRecipe)
//					.map(recipe -> (HammeringStationRecipe) recipe).filter(recipe -> recipe.matches(stackInSlot))
//					.findFirst().orElse(null);
//		}
//		return null;
//	}
//
//	private ItemStackHandler createHandler() {
//		return new ItemStackHandler(1) {
//			@Override
//			protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
//				return stack.getMaxStackSize();
//			}
//
//			@Override
//			protected void onContentsChanged(int slot) {
//				updateInventory();
//			}
//
//			@Override
//			public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
//				HammeringStationRecipe r = matchRecipe(stack);
//				return r != null && super.isItemValid(slot, stack);
//			}
//		};
//	}
//
//	public void extractInsertItem(Player Player, InteractionHand InteractionHand) {
//
//		ItemStack held = Player.getItemInHand(InteractionHand);
//		if (!held.isEmpty()) {
//			insertItem(InteractionHandler, held);
//		} else {
//			extractItem(Player, InteractionHandler);
//		}
//
//		updateInventory();
//
//	}
//
//	public void extractItem(Player Player, IItemHandler inventory) {
//		if (!inventory.getStackInSlot(0).isEmpty()) {
//			ItemStack itemStack = inventory.extractItem(0, inventory.getStackInSlot(0).getCount(), false);
//			Player.addItem(itemStack);
//		}
//		updateInventory();
//	}
//
//	public void insertItem(IItemHandler inventory, ItemStack heldItem) {
//		if (inventory.isItemValid(0, heldItem))
//			if (!inventory.insertItem(0, heldItem, true).sameItem(heldItem)) {
//				final int leftover = inventory.insertItem(0, heldItem.copy(), false).getCount();
//				heldItem.setCount(leftover);
//			}
//		updateInventory();
//	}
//
//	// External extract InteractionHandler
//	public void extractItem(Player Player) {
//		this.extractItem(Player, InteractionHandler);
//	}
//
//	// External insert InteractionHandler
//	public void insertItem(ItemStack heldItem) {
//		this.insertItem(InteractionHandler, heldItem);
//	}
//
//	public void updateInventory() {
//		requestModelDataUpdate();
//		this.setChanged();
//		if (this.getLevel() != null) {
//			this.getLevel().sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 3);
//		}
//	}
//
//	public void hammer(Player Player, ItemStack hammer) {
//		if (lastStack != InteractionHandler.getStackInSlot(0)) {
//			progress = 0;
//			lastStack = InteractionHandler.getStackInSlot(0);
//		} else if (lastStack != ItemStack.EMPTY && lastStack.getItem() != Items.AIR) {
//			this.progress++;
//			hammer.hurtAndBreak(1, Player, null);
//			level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, lastStack), this.worldPosition.getX() + 0.5f,
//					this.worldPosition.getY() + 1, this.worldPosition.getZ() + 0.5f,
//					(level.random.nextFloat() - 0.5f) / 2, (level.random.nextFloat() - 0.5f) / 2,
//					(level.random.nextFloat() - 0.5f) / 2);
//			level.playSound(Player, this.worldPosition, SoundEvents.STONE_HIT, SoundSource.BLOCKS,
//					level.random.nextFloat() + 0.5f, 0);
//		}
//		HammeringStationRecipe recipe = matchRecipe(InteractionHandler.getStackInSlot(0));
//		if (recipe != null) {
//			if (this.progress >= recipe.getStrikes()) {
//
//				for (int i = 0; i < 5; i++) {
//					level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, lastStack),
//							this.worldPosition.getX() + 0.5f, this.worldPosition.getY() + 1,
//							this.worldPosition.getZ() + 0.5f, (level.random.nextFloat() - 0.5f) / 2,
//							(level.random.nextFloat() - 0.5f) / 2, (level.random.nextFloat() - 0.5f) / 2);
//				}
//				level.playSound(Player, this.worldPosition, SoundEvents.BASALT_BREAK, SoundSource.BLOCKS, 1, 0);
//
//				progress = 0;
//
//				TileEntity te = level.getBlockEntity(this.getBlockPos().offset(0, -1, 0));
//				ItemStack item = recipe.getRecipeOutput().copy();
//				if (te != null) {
//					LazyOptional<IItemInteractionHandler> ih = te.getCapability(CapabilityItemHandler.ITEM_InteractionHandLER_CAPABILITY,
//							Direction.UP);
//
//					item = ih.map(h -> dropItemBelow(h, recipe.getRecipeOutput().copy())).get();
//				}
//
//				Containers.dropItemStack(level, this.worldPosition.getX(), this.worldPosition.getY(),
//						this.worldPosition.getZ(), item);
//				// this.lastStack.shrink(1);
//				InteractionHandler.getStackInSlot(0).shrink(1);
//
//			}
//		}
//		this.updateInventory();
//	}
//
//	ItemStack dropItemBelow(IItemInteractionHandler InteractionHandler, ItemStack insert) {
//		for (int i = 0; i < InteractionHandler.getSlots(); i++) {
//			insert = InteractionHandler.insertItem(i, insert, false);
//
//			if (insert.isEmpty()) {
//				return ItemStack.EMPTY;
//			}
//		}
//
//		return insert;
//	}
//
//	@Nullable
//	@Override
//	public ClientboundBlockEntityDataPacket getUpdatePacket() {
//		return new ClientboundBlockEntityDataPacket(this.getBlockPos(), -1, this.getUpdateTag());
//	}
//
//	@Override
//	public void onDataPacket(NetworkManager net, ClientboundBlockEntityDataPacket pkt) {
//		InteractionHandleUpdateTag(this.getBlockState(), pkt.getTag());
//	}
//
//	@Override
//	@Nonnull
//	public CompoundTag getUpdateTag() {
//		CompoundTag updateTag = super.getUpdateTag();
//		updateTag.put("items", InteractionHandler.serializeNBT());
//		return updateTag;
//	}
//
//	@Override
//	public void InteractionHandleUpdateTag(BlockState state, CompoundTag tag) {
//		InteractionHandler.deserializeNBT(tag.getCompound("items"));
//	}
//
//	@Override
//	public void load(BlockState state, @Nonnull CompoundTag nbt) {
//		super.load(state, nbt);
//		progress = nbt.getInt("progress");
//		lastStack.deserializeNBT(nbt.getCompound("lastStack"));
//
//		InteractionHandler.deserializeNBT(nbt.getCompound("items"));
//	}
//
//	@Override
//	@Nonnull
//	public CompoundTag save(@Nonnull CompoundTag nbt) {
//		CompoundTag n = super.save(nbt);
//		n.putInt("progress", progress);
//		n.put("lastStack", lastStack.serializeNBT());
//
//		n.put("items", InteractionHandler.serializeNBT());
//		return n;
//	}
//}
