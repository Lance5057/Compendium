//package lance5057.compendium.core.workstations.tileentities;
//
//import java.util.List;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//import lance5057.compendium.CompendiumTileEntities;
//import lance5057.compendium.core.workstations.recipes.SawhorseStationRecipe;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//import net.minecraft.core.particles.ItemParticleOption;
//import net.minecraft.core.particles.ParticleTypes;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.world.Containers;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.storage.loot.LootContext;
//import net.minecraft.world.level.storage.loot.LootTable;
//import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
//import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
//import net.minecraft.world.phys.Vec3;
//import net.minecraftforge.common.capabilities.Capability;
//import net.minecraftforge.common.util.LazyOptional;
//import net.minecraftforge.items.CapabilityItemHandler;
//import net.minecraftforge.items.IItemHandler;
//import net.minecraftforge.items.ItemStackHandler;
//
//public class SawhorseStationTE extends BlockEntity {
//	private final LazyOptional<IItemHandler> InteractionHandler = LazyOptional.of(this::createInteractionHandler);
//	private ItemStack lastStack = ItemStack.EMPTY;
//	private int progress = 0;
//
//	public SawhorseStationTE(BlockPos pos, BlockState state) {
//		super(CompendiumTileEntities.SAWHORSE_STATION_TE.get(), pos, state);
//	}
//
//	@Nonnull
//	@Override
//	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
//		if (side != Direction.DOWN)
//			if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
//				return InteractionHandler.cast();
//			}
//		return super.getCapability(cap, side);
//	}
//
//	private SawhorseStationRecipe matchRecipe(ItemStack stackInSlot) {
//		if (level != null) {
//			return level.getRecipeManager().getRecipes().stream()
//					.filter(recipe -> recipe instanceof SawhorseStationRecipe)
//					.map(recipe -> (SawhorseStationRecipe) recipe).filter(recipe -> recipe.matches(stackInSlot))
//					.findFirst().orElse(null);
//		}
//		return null;
//	}
//
//	private IItemHandler createInteractionHandler() {
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
//				SawhorseStationRecipe r = matchRecipe(stack);
//				return r != null && super.isItemValid(slot, stack);
//			}
//		};
//	}
//
//	public void extractInsertItem(Player Player, InteractionHand InteractionHand) {
//		InteractionHandler.ifPresent(inventory -> {
//			ItemStack held = Player.getItemInHand(InteractionHand);
//			if (!held.isEmpty()) {
//				insertItem(inventory, held);
//			} else {
//				extractItem(Player, inventory);
//			}
//		});
//		updateInventory();
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
//		InteractionHandler.ifPresent(inventory -> {
//			this.extractItem(Player, inventory);
//		});
//	}
//
//	// External insert InteractionHandler
//	public void insertItem(ItemStack heldItem) {
//		InteractionHandler.ifPresent(inventory -> {
//			this.insertItem(inventory, heldItem);
//		});
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
//		// if (!this.level.isRemote()) {
//		InteractionHandler.ifPresent(inventory -> {
//			if (lastStack != inventory.getStackInSlot(0)) {
//				progress = 0;
//				lastStack = inventory.getStackInSlot(0);
//			} else if (lastStack != ItemStack.EMPTY && lastStack.getItem() != Items.AIR) {
//				this.progress++;
//				hammer.hurtAndBreak(1, Player, null);
//				level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, lastStack), this.worldPosition.getX() + 0.5f,
//						this.worldPosition.getY() + 1, this.worldPosition.getZ() + 0.5f,
//						(level.random.nextFloat() - 0.5f) / 2, (level.random.nextFloat() - 0.5f) / 2,
//						(level.random.nextFloat() - 0.5f) / 2);
//				level.playSound(Player, this.worldPosition, SoundEvents.STONE_HIT, SoundSource.BLOCKS,
//						level.random.nextFloat() + 0.5f, 0);
//			}
//			SawhorseStationRecipe recipe = matchRecipe(inventory.getStackInSlot(0));
//			if (recipe != null) {
//				if (this.progress >= recipe.getStrikes()) {
//
//					for (int i = 0; i < 5; i++) {
//						level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, lastStack),
//								this.worldPosition.getX() + 0.5f, this.worldPosition.getY() + 1,
//								this.worldPosition.getZ() + 0.5f, (level.random.nextFloat() - 0.5f) / 2,
//								(level.random.nextFloat() - 0.5f) / 2, (level.random.nextFloat() - 0.5f) / 2);
//					}
//					level.playSound(Player, worldPosition, SoundEvents.BASALT_BREAK, SoundSource.BLOCKS, 1, 0);
//
//					progress = 0;
//
//					if (this.level instanceof ServerLevel) {
//						ResourceLocation rc = recipe.getOutput();
//
//						LootTable loottable = this.level.getServer().getLootTables().get(rc);
//						LootContext.Builder lootcontext$builder = new LootContext.Builder((ServerLevel) this.level)
//								.withRandom(this.level.random).withParameter(LootContextParams.THIS_ENTITY, Player)
//								.withParameter(LootContextParams.ORIGIN, new Vec3(this.worldPosition.getX(),
//										this.worldPosition.getY(), this.worldPosition.getZ()));
//
//						LootContext ctx = lootcontext$builder.create(LootContextParamSets.GIFT);
//						List<ItemStack> items = loottable.getRandomItems(ctx);
//
//						BlockEntity te = level.getBlockEntity(this.getBlockPos().offset(0, -1, 0));
//
//						items.forEach(i -> {
//							if (te != null) {
//								LazyOptional<IItemHandler> ih = te
//										.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP);
//
//								final ItemStack it = i.copy();
//								i = ih.map(h -> dropItemBelow(h, it)).get();
//							}
//							Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY(),
//									worldPosition.getZ(), i);
//						});
//					}
//
//					// this.lastStack.shrink(1);
//					inventory.getStackInSlot(0).shrink(1);
//
//				}
//			}
//		});
//		this.updateInventory();
//		// }
//
//	}
//
//	ItemStack dropItemBelow(IItemHandler InteractionHandler, ItemStack insert) {
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
//		return ClientboundBlockEntityDataPacket.create(this);
//	}
//
////    @Override
////    public void onDataPacket(NetworkManager net, ClientboundBlockEntityDataPacket pkt) {
////        InteractionHandleUpdateTag(pkt.getTag());
////    }
//
//	@Override
//	@Nonnull
//	public CompoundTag getUpdateTag() {
//		CompoundTag updateTag = new CompoundTag();
//		final IItemHandler itemInteractionHandler = getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
//				.orElseGet(this::createInteractionHandler);
//		CompoundTag itemSlot = new CompoundTag();
//		itemInteractionHandler.getStackInSlot(0).save(itemSlot);
//		updateTag.put("item", itemSlot);
//		return updateTag;
//	}
//
////    @Override
////    public void InteractionHandleUpdateTag(CompoundTag tag) {
////        final IItemHandler itemInteractionHandler = getCapability(CapabilityItemHandler.ITEM_InteractionHandLER_CAPABILITY).orElseGet(this::createInteractionHandler);
////        ((ItemStackHandler) itemInteractionHandler).setStackInSlot(1, ItemStack.read(tag.getCompound("item")));
////    }
//
//	@Override
//	public void load(@Nonnull CompoundTag nbt) {
//		super.load(nbt);
//		progress = nbt.getInt("progress");
//		lastStack.deserializeNBT(nbt.getCompound("lastStack"));
//		InteractionHandler.ifPresent(iItemInteractionHandler -> {
//			if (iItemInteractionHandler instanceof ItemStackHandler) {
//				((ItemStackHandler) iItemInteractionHandler).deserializeNBT(nbt.getCompound("inventory"));
//			}
//		});
//	}
//
//	@Override
//	@Nonnull
//	public void saveAdditional(@Nonnull CompoundTag nbt) {
//		super.saveAdditional(nbt);
//		nbt.putInt("progress", progress);
//		nbt.put("lastStack", lastStack.serializeNBT());
//		InteractionHandler.ifPresent(iItemInteractionHandler -> {
//			if (iItemInteractionHandler instanceof ItemStackHandler) {
//				nbt.put("inventory", ((ItemStackHandler) iItemInteractionHandler).serializeNBT());
//			}
//		});
//	}
//}
