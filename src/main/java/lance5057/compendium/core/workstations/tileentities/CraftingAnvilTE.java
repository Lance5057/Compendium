//package lance5057.compendium.core.workstations.tileentities;
//
//import java.util.Optional;
//
//import lance5057.compendium.CompendiumTileEntities;
//import lance5057.compendium.core.util.recipes.WorkstationRecipeWrapper;
//import lance5057.compendium.core.workstations.WorkstationRecipes;
//import lance5057.compendium.core.workstations.containers.CraftingAnvilContainer;
//import lance5057.compendium.core.workstations.recipes.CraftingAnvilRecipe;
//import lance5057.compendium.core.workstations.tileentities.bases.MultiToolRecipeStation;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//import net.minecraft.network.chat.Component;
//import net.minecraft.network.chat.TextComponent;
//import net.minecraft.world.MenuProvider;
//import net.minecraft.world.entity.player.Inventory;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.inventory.AbstractContainerMenu;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraftforge.common.util.LazyOptional;
//import net.minecraftforge.items.CapabilityItemHandler;
//import net.minecraftforge.items.IItemHandler;
//import net.minecraftforge.items.IItemHandlerModifiable;
//import net.minecraftforge.items.ItemStackHandler;
//
//public class CraftingAnvilTE extends MultiToolRecipeStation<CraftingAnvilRecipe> implements MenuProvider {
////    private final LazyOptional<IItemInteractionHandlerModifiable> InteractionHandler = LazyOptional.of(this::createInteractionHandler);
//	private ItemStack ghostStack = ItemStack.EMPTY;
//
////    // public Optional<CraftingAnvilRecipe> currentRecipe;
////    public boolean recipeLocked = false;
////    // public NonNullList<RecipeItemUse> toolList;
////    private int progress;
////    private int maxProgress;
////    private Ingredient curTool;
////    public int toolCount;
////    public int stage = 0;
//
//	public CraftingAnvilTE(BlockPos pos, BlockState state) {
//		super(27, 5, 5, CompendiumTileEntities.CRAFTING_ANVIL_TE.get(), pos, state);
//	}
//
////	@Nullable
////	@Override
////	public AbstractContainerMenu createMenu(int windowID, Inventory playerInventory, Player Player) {
////
////		CraftingAnvilContainer c = InteractionHandler.map(i -> {
////			return CraftingAnvilContainer.createContainerServerSide(windowID, playerInventory,
////					(ItemStackInteractionHandler) i);
////		}).get();
////
////		return c;
////	}
//
////    @Nonnull
////    @Override
////    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
////	if (side != Direction.DOWN)
////	    if (cap == CapabilityItemHandler.ITEM_InteractionHandLER_CAPABILITY) {
////		return InteractionHandler.cast();
////	    }
////	return super.getCapability(cap, side);
////    }
////
////    private Optional<CraftingAnvilRecipe> matchRecipe() {
////
////	if (world != null) {
////	    Optional<CraftingAnvilRecipe> recipe = InteractionHandler.map(i -> {
////		return world.getRecipeManager().getRecipe(WorkstationRecipes.CRAFTING_ANVIL_RECIPE,
////			new WorkstationRecipeWrapper(5, 5, i), world);
////	    }).get();
////
////	    // setRecipe(recipe);
////	    return recipe;
////	}
////	return null;
////    }
////
////    private void setRecipe(Optional<CraftingAnvilRecipe> r) {
////
////	if (r.isPresent()) {
////	    this.setupStage(r.get(), 0);
////	} else
////	    this.zeroProgress();
////    }
////
////    private IItemInteractionHandlerModifiable createInteractionHandler() {
////	return new ItemStackInteractionHandler(27) {
////	    @Override
////	    protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
////		return stack.getMaxStackSize();
////	    }
////
////	    @Override
////	    protected void onContentsChanged(int slot) {
////		updateInventory();
////		if (slot != 25) {
////
////		    zeroProgress();
////		    Optional<CraftingAnvilRecipe> recipe = matchRecipe();
////
////		    setGhostStack(ItemStack.EMPTY);
////
////		    if (recipe.isPresent()) {
////			setGhostStack(recipe.get().getRecipeOutput().copy());
////			setRecipe(recipe);
////		    }
////		}
////	    }
////	};
////    }
////
////    public void zeroProgress() {
////	this.progress = 0;
////	this.maxProgress = 0;
////	this.curTool = null;
////	this.toolCount = 0;
////	this.stage = 0;
////    }
////
//	public void setGhostStack(ItemStack i) {
//		this.ghostStack = i;
//	}
//
//	public ItemStack getGhostStack() {
//		return this.ghostStack;
//	}
////
////    public RecipeItemUse getCurrentTool() {
////	Optional<CraftingAnvilRecipe> currentRecipe = matchRecipe();
////	if (currentRecipe.isPresent())
////	    return currentRecipe.get().getToolList().get(stage);
////	return null;
////    }
////
////    public void updateInventory() {
////
////	requestModelDataUpdate();
////	this.markDirty();
////	if (this.getLevel() != null) {
////	    this.getLevel().notifyBlockUpdate(pos, this.getBlockState(), this.getBlockState(), 3);
////	}
////    }
////
////    protected void setupStage(CraftingAnvilRecipe r, int i) {
////
////	this.progress = 0;
////	this.maxProgress = r.getToolList().get(i).uses;
////	this.curTool = r.getToolList().get(i).tool;
////	this.toolCount = r.getToolList().get(i).count;
////
////	this.stage = i;
////    }
////
////    boolean isFinalStage(CraftingAnvilRecipe r) {
////	int i = r.getToolList().size();
////	if (i - 1 > stage) {
////	    return false;
////	}
////	return true;
////    }
////
////    public InteractionResultHolder hammer(Player Player, ItemStack hammer) {
////	Optional<CraftingAnvilRecipe> currentRecipe = matchRecipe();
////	currentRecipe.ifPresent(r -> {
////
////	    if (this.curTool == null) {
////		setupStage(r, stage);
////	    }
////	    if (this.curTool.test(hammer))
////		if (hammer.getCount() == this.toolCount) {
////
////		    if (this.progress >= this.maxProgress) {
////
////			if (isFinalStage(r)) {
////
////			    for (int i = 0; i < 5; i++) {
////				world.addParticle(new ItemParticleOption(ParticleTypes.ITEM, ghostStack),
////					pos.getX() + 0.5f, pos.getY() + 1, pos.getZ() + 0.5f,
////					(world.rand.nextFloat() - 0.5f) / 2, (world.rand.nextFloat() - 0.5f) / 2,
////					(world.rand.nextFloat() - 0.5f) / 2);
////			    }
////			    world.playSound(Player, pos, SoundEvents.BLOCK_ANVIL_USE, SoundSource.BLOCKS, 1, 0);
////			    hammer.damageItem(1, Player, null);
////
////			    InteractionHandler.ifPresent(h -> {
////				ItemStack item = r.getRecipeOutput().copy();
////				TileEntity te = world.getTileEntity(this.getPos().add(0, -1, 0));
////				if (te != null) {
////				    LazyOptional<IItemInteractionHandler> ih = te
////					    .getCapability(CapabilityItemHandler.ITEM_InteractionHandLER_CAPABILITY, Direction.UP);
////
////				    if (h.getStackInSlot(25) == ItemStack.EMPTY) {
////
////					item = ih.map(h2 -> dropItemBelow(h2, r.getRecipeOutput().copy())).get();
////					if (item == null)
////					    craft();
////
////				    }
////				}
////
////				if (h.getStackInSlot(25) == ItemStack.EMPTY) {
////				    h.setStackInSlot(25, item);
////				    craft();
////				}
////			    });
////			} else {
////			    setupStage(r, stage + 1);
////			}
////		    } else {
////			hammer.damageItem(1, Player, null);
////			progress++;
////		    }
////
////		}
////	});
////	this.updateInventory();
////
////	return InteractionResultHolder.PASS;
//////TODO - Redo this entire thing
////    }
////
////    void craft() {
////	this.InteractionHandler.ifPresent(it -> {
////	    for (int i = 0; i < 25; i++) {
////		ItemStack stack = it.getStackInSlot(i);
////		stack.setCount(stack.getCount() - 1);
////		it.setStackInSlot(i, stack);
////	    }
////	});
////    }
////
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
//////	@Nullable
//////	@Override
//////	public ClientboundBlockEntityDataPacket getUpdatePacket() {
//////		CompoundTag nbt = this.getUpdateTag();
//////		return new ClientboundBlockEntityDataPacket(this.getPos(), -1, nbt);
//////	}
////
//////    @Override
//////    public void onDataPacket(NetworkManager net, ClientboundBlockEntityDataPacket pkt) {
//////        InteractionHandleUpdateTag(pkt.getNbtCompound());
//////    }
////
//////	@Override
//////	@Nonnull
//////	public CompoundTag getUpdateTag() {
//////		CompoundTag updateTag = new CompoundTag();
//////		updateTag.putInt("progress", progress);
//////		final IItemInteractionHandler itemInteractionHandler = getCapability(CapabilityItemHandler.ITEM_InteractionHandLER_CAPABILITY).orElseGet(this::createInteractionHandler);
//////		// CompoundTag inv = new CompoundTag();
//////
//////		updateTag.put("items", ((ItemStackInteractionHandler) itemInteractionHandler).serializeNBT());
//////		return updateTag;
//////	}
//////
//////	@Override
//////	public void InteractionHandleUpdateTag(BlockState state, CompoundTag tag) {
//////		final IItemInteractionHandler itemInteractionHandler = getCapability(CapabilityItemHandler.ITEM_InteractionHandLER_CAPABILITY).orElseGet(this::createInteractionHandler);
//////		((ItemStackInteractionHandler) itemInteractionHandler).deserializeNBT(tag.getCompound("items"));
//////		
//////		this.progress = tag.getInt("progress");
//////	}
////    @Override
////    public CompoundTag getUpdateTag() {
////	CompoundTag nbt = super.getUpdateTag();
////
////	writeNBT(nbt);
////
////	return nbt;
////    }
////
////    @Override
////    public void InteractionHandleUpdateTag(BlockState state, CompoundTag tag) {
////	readNBT(tag);
////    }
////
////    @Override
////    public ClientboundBlockEntityDataPacket getUpdatePacket() {
////	CompoundTag tag = new CompoundTag();
////
////	writeNBT(tag);
////
////	return new ClientboundBlockEntityDataPacket(getPos(), 1, tag);
////    }
////
////    @Override
////    public void onDataPacket(NetworkManager net, ClientboundBlockEntityDataPacket pkt) {
////	CompoundTag tag = pkt.getNbtCompound();
////	// InteractionHandle your Data
////	readNBT(tag);
////    }
////
////    void readNBT(CompoundTag nbt) {
////	final IItemInteractionHandler itemInteractionHandler = getCapability(CapabilityItemHandler.ITEM_InteractionHandLER_CAPABILITY)
////		.orElseGet(this::createInteractionHandler);
////	((ItemStackInteractionHandler) itemInteractionHandler).deserializeNBT(nbt.getCompound("inventory"));
////
////	this.ghostStack = ItemStack.read(nbt.getCompound("ghost"));
////	this.stage = nbt.getInt("stage");
////    }
////
////    CompoundTag writeNBT(CompoundTag tag) {
////	// CompoundTag tag = new CompoundTag();
////	// Write your data into the nbtTag
////
////	IItemInteractionHandler itemInteractionHandler = getCapability(CapabilityItemHandler.ITEM_InteractionHandLER_CAPABILITY)
////		.orElseGet(this::createInteractionHandler);
////	tag.put("inventory", ((ItemStackInteractionHandler) itemInteractionHandler).serializeNBT());
////
////	tag.put("ghost", this.ghostStack.serializeNBT());
////	
////	tag.putInt("stage", stage);
////
////	// CompoundTag tagTool = new CompoundTag();
////
//////	for (RecipeItemUse r : this.toolList) {
//////	    r.write(r, null);
//////	}
////
////	return tag;
////    }
////
////    @Override
////    public void read(BlockState state, @Nonnull CompoundTag nbt) {
////	super.read(state, nbt);
////	readNBT(nbt);
////    }
////
////    @Override
////    @Nonnull
////    public CompoundTag write(@Nonnull CompoundTag nbt) {
////	super.write(nbt);
////	writeNBT(nbt);
////	return nbt;
////    }
//
//	private static final TextComponent CONTAINER_NAME = new TextComponent(
//			"compendium.workstations.crafting_anvil");
//
////	@Override
////	public TextComponent getDisplayName() {
////		// TODO Auto-generated method stub
////		return CONTAINER_NAME;
////	}
//
//	@Override
//	protected IItemHandlerModifiable createInteractionHandler() {
//		return new ItemStackHandler(27) {
//
//			@Override
//			protected void onContentsChanged(int slot) {
//				updateInventory();
//				if (slot != 25) {
//
//					zeroProgress();
//					Optional<CraftingAnvilRecipe> recipe = matchRecipe();
//
//					setGhostStack(ItemStack.EMPTY);
//
//					if (recipe.isPresent()) {
//						setGhostStack(recipe.get().getRecipeOutput().copy());
//						setRecipe(recipe);
//					}
//				}
//			}
//
//			@Override
//			public int getSlots() {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//
//			@Override
//			public ItemStack getStackInSlot(int slot) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public ItemStack extractItem(int slot, int amount, boolean simulate) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public int getSlotLimit(int slot) {
//				return this.getStackInSlot(slot).getMaxStackSize();
//			}
//
//			@Override
//			public boolean isItemValid(int slot, ItemStack stack) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public void setStackInSlot(int slot, ItemStack stack) {
//				// TODO Auto-generated method stub
//
//			}
//		};
//	}
//
//	@Override
//	public void addParticle() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	protected Optional<CraftingAnvilRecipe> matchRecipe() {
//		if (level != null) {
//
//			Optional<CraftingAnvilRecipe> recipe = InteractionHandler.map(i -> {
//				return level.getRecipeManager().getRecipeFor(WorkstationRecipes.CRAFTING_ANVIL_RECIPE,
//						new WorkstationRecipeWrapper(5, 5, i), level);
//			}).get();
//
//			// setRecipe(recipe);
//			return recipe;
//		}
//		return null;
//	}
//
//	@Override
//	public void finishRecipe(Player Player, CraftingAnvilRecipe r) {
//		InteractionHandler.ifPresent(h -> {
//			ItemStack item = r.getRecipeOutput().copy();
//			BlockEntity te = level.getBlockEntity(this.getBlockPos().offset(0, -1, 0));
//			if (te != null) {
//				LazyOptional<IItemHandler> ih = te.getCapability(
//						CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.UP);
//
//				if (h.getStackInSlot(25) == ItemStack.EMPTY) {
//
//					item = ih.map(h2 -> dropItemBelow(h2, r.getRecipeOutput().copy())).get();
//					if (item == null)
//						craft();
//
//				}
//			}
//
//			if (h.getStackInSlot(25) == ItemStack.EMPTY) {
//				h.setStackInSlot(25, item);
//				craft();
//			}
//		});
//	}
//
//	@Override
//	public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
//		return new CraftingAnvilContainer(id, inv, null);
//	}
//
//	@Override
//	public Component getDisplayName() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
