package lance5057.compendium.core.workstations.tileentities.bases;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lance5057.compendium.core.recipes.RecipeItemUse;
import lance5057.compendium.core.workstations.recipes.bases.MultiToolRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public abstract class MultiToolRecipeStation<V extends MultiToolRecipe> extends TileEntity {
    protected final LazyOptional<IItemHandlerModifiable> handler = LazyOptional.of(this::createHandler);
    //private ItemStack ghostStack = ItemStack.EMPTY;

    // public Optional<V> currentRecipe;
    public boolean recipeLocked = false;
    // public NonNullList<RecipeItemUse> toolList;
    private int progress;
    private int maxProgress;
    private Ingredient curTool;
    public int toolCount;
    public int stage = 0;
    public final int width;
    public final int height;
    public final int numSlots;

    public MultiToolRecipeStation(int slots, int width, int height, TileEntityType<?> tileEntityTypeIn) {
	super(tileEntityTypeIn);
	
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
	return super.getCapability(cap, side);
    }

    protected abstract Optional<V> matchRecipe();
//    {
//
//	if (world != null) {
//	    Optional<V> recipe =  handler.map(i -> {
//		return world.getRecipeManager().getRecipe(WorkstationRecipes.CRAFTING_ANVIL_RECIPE,
//			new WorkstationRecipeWrapper(width, height, i), world);
//	    }).get();
//
//	    // setRecipe(recipe);
//	    return recipe;
//	}
//	return null;
//    }

    public void setRecipe(Optional<V> r) {

	if (r.isPresent()) {
	    this.setupStage(r.get(), 0);
	} else
	    this.zeroProgress();
    }

    protected abstract IItemHandlerModifiable createHandler(); 
//    {
//	return new ItemStackHandler(numSlots) {
//	    @Override
//	    protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
//		return stack.getMaxStackSize();
//	    }
//
//	    @Override
//	    protected void onContentsChanged(int slot) {
//		updateInventory();
//		if (slot != 25) {
//
//		    zeroProgress();
//		    Optional<V> recipe = matchRecipe();
//
//		    if (recipe.isPresent()) {
//			setRecipe(recipe);
//		    }
//		}
//	    }
//	};
//    }

    public void zeroProgress() {
	this.progress = 0;
	this.maxProgress = 0;
	this.curTool = null;
	this.toolCount = 0;
	this.stage = 0;
    }

    public RecipeItemUse getCurrentTool() {
	Optional<V> currentRecipe = matchRecipe();
	if (currentRecipe.isPresent())
	    return currentRecipe.get().getToolList().get(stage);
	return null;
    }

    public void updateInventory() {

	requestModelDataUpdate();
	this.markDirty();
	if (this.getWorld() != null) {
	    this.getWorld().notifyBlockUpdate(pos, this.getBlockState(), this.getBlockState(), 3);
	}
    }

    protected void setupStage(V r, int i) {

	this.progress = 0;
	this.maxProgress = r.getToolList().get(i).uses;
	this.curTool = r.getToolList().get(i).tool;
	this.toolCount = r.getToolList().get(i).count;

	this.stage = i;
    }

    boolean isFinalStage(V r) {
	int i = r.getToolList().size();
	if (i - 1 > stage) {
	    return false;
	}
	return true;
    }

    public ActionResultType hammer(PlayerEntity playerEntity, ItemStack hammer) {
	Optional<V> currentRecipe = matchRecipe();
	currentRecipe.ifPresent(r -> {

	    if (this.curTool == null) {
		setupStage(r, stage);
	    }
	    if (this.curTool.test(hammer))
		if (hammer.getCount() == this.toolCount) {

		    if (this.progress >= this.maxProgress) {

			if (isFinalStage(r)) {

			    for (int i = 0; i < 5; i++) {
				addParticle();
			    }
			    world.playSound(playerEntity, pos, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 1, 0);
			    hammer.damageItem(1, playerEntity, null);

			    this.finishRecipe(playerEntity, r);
			} else {
			    setupStage(r, stage + 1);
			}
		    } else {
			hammer.damageItem(1, playerEntity, null);
			progress++;
		    }

		}
	});
	this.updateInventory();

	return ActionResultType.PASS;
    }
    
    public abstract void addParticle();
    
    public abstract void finishRecipe(PlayerEntity playerEntity,V recipe);

    protected void craft() {
	this.handler.ifPresent(it -> {
	    for (int i = 0; i < width * height; i++) {
		ItemStack stack = it.getStackInSlot(i);
		stack.setCount(stack.getCount() - 1);
		it.setStackInSlot(i, stack);
	    }
	});
    }

    protected ItemStack dropItemBelow(IItemHandler handler, ItemStack insert) {
	for (int i = 0; i < handler.getSlots(); i++) {
	    insert = handler.insertItem(i, insert, false);

	    if (insert.isEmpty()) {
		return ItemStack.EMPTY;
	    }
	}

	return insert;
    }

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
	final IItemHandler itemHandler = getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		.orElseGet(this::createHandler);
	((ItemStackHandler) itemHandler).deserializeNBT(nbt.getCompound("inventory"));

	this.stage = nbt.getInt("stage");
    }

    CompoundNBT writeNBT(CompoundNBT tag) {

	IItemHandler itemHandler = getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		.orElseGet(this::createHandler);
	tag.put("inventory", ((ItemStackHandler) itemHandler).serializeNBT());

	tag.putInt("stage", stage);

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
}
