//package lance5057.compendium.core.workstations.tileentities;
//
//import java.util.List;
//import java.util.Optional;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//import com.mojang.math.Vector3d;
//
//import lance5057.compendium.CompendiumTileEntities;
//import lance5057.compendium.core.util.recipes.WorkstationRecipeWrapper;
//import lance5057.compendium.core.workstations.WorkstationRecipes;
//import lance5057.compendium.core.workstations.recipes.ScrappingTableRecipe;
//import lance5057.compendium.core.workstations.tileentities.bases.MultiToolRecipeStation;
//import net.minecraft.loot.LootContextParams;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.world.Containers;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.storage.loot.LootContext;
//import net.minecraft.world.level.storage.loot.LootTable;
//import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
//import net.minecraft.world.server.ServerLevel;
//import net.minecraftforge.common.capabilities.Capability;
//import net.minecraftforge.common.util.LazyOptional;
//import net.minecraftforge.items.CapabilityItemHandler;
//import net.minecraftforge.items.IItemInteractionHandler;
//import net.minecraftforge.items.ItemStackInteractionHandler;
//
//public class ScrappingTableTE extends MultiToolRecipeStation<ScrappingTableRecipe> {
//	// private final LazyOptional<IItemInteractionHandler> InteractionHandler =
//	// LazyOptional.of(this::createInteractionHandler);
//
////    private int progress;
////    private int maxProgress;
////    private Ingredient curTool;
////    public int toolCount;
////    public int stage = 0;
//
//	public ScrappingTableTE() {
//		super(1, 1, 1, CompendiumTileEntities.SCRAPPING_TABLE_TE.get());
////	InteractionHandler = createInteractionHandler();
////	optional = LazyOptional.of(() -> InteractionHandler);
//	}
//
//	@Nonnull
//	@Override
//	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
//		if (side != Direction.DOWN)
//			if (cap == CapabilityItemHandler.ITEM_InteractionHandLER_CAPABILITY) {
//				return InteractionHandler.cast();
//			}
//		return super.getCapability(cap, side);
//	}
//
////    private ScrappingTableRecipe matchRecipe(ItemStack stackInSlot) {
////	if (world != null) {
////	    return world.getRecipeManager().getRecipes().stream()
////		    .filter(recipe -> recipe instanceof ScrappingTableRecipe)
////		    .map(recipe -> (ScrappingTableRecipe) recipe).filter(recipe -> recipe.matches(stackInSlot))
////		    .findFirst().orElse(null);
////	}
////	return null;
////    }
//
//	@Override
//	protected ItemStackInteractionHandler createInteractionHandler() {
//		return new ItemStackInteractionHandler(1) {
//			@Override
//			protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
//				return stack.getMaxStackSize();
//			}
//
//			@Override
//			protected void onContentsChanged(int slot) {
//				updateInventory();
//			}
//		};
//	}
//
//	public void extractInsertItem(Player Player, InteractionHand InteractionHand) {
//		InteractionHandler.ifPresent(inventory -> {
//			ItemStack held = Player.getItemInInteractionHand(InteractionHand);
//			if (!held.isEmpty()) {
//				insertItem(inventory, held);
//			} else {
//				extractItem(Player, inventory);
//			}
//		});
//		updateInventory();
//	}
//
//	public void extractItem(Player Player, IItemInteractionHandler inventory) {
//		if (!inventory.getStackInSlot(0).isEmpty()) {
//			ItemStack itemStack = inventory.extractItem(0, inventory.getStackInSlot(0).getCount(), false);
//			Player.addItem(itemStack);
//		}
//		updateInventory();
//	}
//
//	public void insertItem(IItemInteractionHandler inventory, ItemStack heldItem) {
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
////
////    public void updateInventory() {
////	requestModelDataUpdate();
////	this.markDirty();
////	if (this.getLevel() != null) {
////	    this.getLevel().notifyBlockUpdate(pos, this.getBlockState(), this.getBlockState(), 3);
////	}
////    }
//
////    public void hammer(Player Player, ItemStack hammer) {
//////	if (lastStack != InteractionHandler.getStackInSlot(0)) {
//////	    progress = 0;
//////	    lastStack = InteractionHandler.getStackInSlot(0);
//////	} else if (lastStack != ItemStack.EMPTY && lastStack.getItem() != Items.AIR) {
//////	    this.progress++;
//////	    hammer.damageItem(1, Player, null);
//////	    world.addParticle(new ItemParticleOption(ParticleTypes.ITEM, lastStack), pos.getX() + 0.5f, pos.getY() + 1,
//////		    pos.getZ() + 0.5f, (world.rand.nextFloat() - 0.5f) / 2, (world.rand.nextFloat() - 0.5f) / 2,
//////		    (world.rand.nextFloat() - 0.5f) / 2);
//////	    world.playSound(Player, pos, SoundEvents.BLOCK_STONE_HIT, SoundSource.BLOCKS,
//////		    world.rand.nextFloat() + 0.5f, 0);
//////	}
////	InteractionHandler.ifPresent(inventory -> {
////
////	    ScrappingTableRecipe recipe = matchRecipe(inventory.getStackInSlot(0));
////	    if (recipe != null) {
////
////	    }
////	});
////
////	this.updateInventory();
////    }
//
////    ItemStack dropItemBelow(IItemInteractionHandler InteractionHandler, ItemStack insert) {
////	for (int i = 0; i < InteractionHandler.getSlots(); i++) {
////	    insert = InteractionHandler.insertItem(i, insert, false);
////
////	    if (insert.isEmpty()) {
////		return ItemStack.EMPTY;
////	    }
////	}
////
////	return insert;
////    }
////
////    @Nullable
////    @Override
////    public ClientboundBlockEntityDataPacket getUpdatePacket() {
////	return new ClientboundBlockEntityDataPacket(this.getPos(), -1, this.getUpdateTag());
////    }
////
////    @Override
////    public void onDataPacket(NetworkManager net, ClientboundBlockEntityDataPacket pkt) {
////	InteractionHandleUpdateTag(this.getBlockState(), pkt.getNbtCompound());
////    }
////
////    @Override
////    @Nonnull
////    public CompoundTag getUpdateTag() {
////	CompoundTag updateTag = new CompoundTag();
////	final IItemInteractionHandler itemInteractionHandler = getCapability(CapabilityItemHandler.ITEM_InteractionHandLER_CAPABILITY)
////		.orElseGet(this::createInteractionHandler);
////	CompoundTag itemSlot = new CompoundTag();
////	itemInteractionHandler.getStackInSlot(0).write(itemSlot);
////	updateTag.put("item", itemSlot);
////	return updateTag;
////    }
////
//////    @Override
//////    public void InteractionHandleUpdateTag(BlockState state, CompoundTag tag) {
//////	InteractionHandler.deserializeNBT(tag.getCompound("items"));
//////    }
////
////    @Override
////    public void read(BlockState state, @Nonnull CompoundTag nbt) {
////	super.read(state, nbt);
////	progress = nbt.getInt("progress");
////	// lastStack.deserializeNBT(nbt.getCompound("lastStack"));
////	InteractionHandler.ifPresent(iItemInteractionHandler -> {
////	    if (iItemInteractionHandler instanceof ItemStackInteractionHandler) {
////		((ItemStackInteractionHandler) iItemInteractionHandler).deserializeNBT(nbt.getCompound("inventory"));
////	    }
////	});
////    }
////
////    @Override
////    @Nonnull
////    public CompoundTag write(@Nonnull CompoundTag nbt) {
////	super.write(nbt);
////	nbt.putInt("progress", progress);
////	// nbt.put("lastStack", lastStack.serializeNBT());
////	InteractionHandler.ifPresent(iItemInteractionHandler -> {
////	    if (iItemInteractionHandler instanceof ItemStackInteractionHandler) {
////		nbt.put("inventory", ((ItemStackInteractionHandler) iItemInteractionHandler).serializeNBT());
////	    }
////	});
////	return nbt;
////    }
//
////    private Optional<ScrappingTableRecipe> matchRecipe() {
////
////	if (world != null) {
////	    Optional<ScrappingTableRecipe> recipe = InteractionHandler.map(i -> {
////		return world.getRecipeManager().getRecipe(WorkstationRecipes.CRAFTING_ANVIL_RECIPE,
////			new WorkstationRecipeWrapper(5, 5, i), world);
////	    }).get();
////
////	    // setRecipe(recipe);
////	    return recipe;
////	}
////	return null;
////    }
//
////    protected void setupStage(ScrappingTableRecipe r, int i) {
////
////	this.progress = 0;
////	this.maxProgress = r.getToolList().get(i).uses;
////	this.curTool = r.getToolList().get(i).tool;
////	this.toolCount = r.getToolList().get(i).count;
////
////	this.stage = i;
////    }
//
//	@Override
//	protected Optional<ScrappingTableRecipe> matchRecipe() {
//
//		if (this.level != null) {
//			Optional<ScrappingTableRecipe> recipe = InteractionHandler.map(i -> {
//				return level.getRecipeManager().getRecipeFor(WorkstationRecipes.SCRAPPING_TABLE_RECIPE,
//						new WorkstationRecipeWrapper(1, 1, i), level);
//			}).get();
//
//			// setRecipe(recipe);
//			return recipe;
//		}
//		return null;
//	}
//
//	@Override
//	public void addParticle() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void finishRecipe(Player Player, ScrappingTableRecipe r) {
//		InteractionHandler.ifPresent(h -> {
//			if (this.level instanceof ServerLevel) {
//				ResourceLocation rc = r.getLoottable();
//
//				LootTable loottable = this.level.getServer().getLootTables().get(rc);
//				LootContext.Builder lootcontext$builder = new LootContext.Builder((ServerLevel) this.level)
//						.withRandom(this.level.random).withParameter(LootContextParams.THIS_ENTITY, Player)
//						.withParameter(LootContextParams.ORIGIN,
//								new Vector3d(this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ()));
//
//				LootContext ctx = lootcontext$builder.create(LootContextParamSets.GIFT);
//				List<ItemStack> items = loottable.getRandomItems(ctx);
//
//				TileEntity te = level.getBlockEntity(this.getBlockPos().offset(0, -1, 0));
//
//				items.forEach(i -> {
//					if (te != null) {
//						LazyOptional<IItemInteractionHandler> ih = te.getCapability(CapabilityItemHandler.ITEM_InteractionHandLER_CAPABILITY,
//								Direction.UP);
//
//						final ItemStack it = i.copy();
//						i = ih.map(j -> dropItemBelow(j, it)).get();
//					}
//					Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), i);
//
//				});
//
//			}
//			craft();
//		});
//	}
//}
