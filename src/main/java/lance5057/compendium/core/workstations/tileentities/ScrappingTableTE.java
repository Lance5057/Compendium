package lance5057.compendium.core.workstations.tileentities;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lance5057.compendium.CompendiumTileEntities;
import lance5057.compendium.core.util.recipes.WorkstationRecipeWrapper;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations.recipes.ScrappingTableRecipe;
import lance5057.compendium.core.workstations.tileentities.bases.MultiToolRecipeStation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ScrappingTableTE extends MultiToolRecipeStation<ScrappingTableRecipe> {
    // private final LazyOptional<IItemHandler> handler =
    // LazyOptional.of(this::createHandler);

//    private int progress;
//    private int maxProgress;
//    private Ingredient curTool;
//    public int toolCount;
//    public int stage = 0;

    public ScrappingTableTE() {
	super(1, 1, 1, CompendiumTileEntities.SCRAPPING_TABLE_TE.get());
//	handler = createHandler();
//	optional = LazyOptional.of(() -> handler);
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

//    private ScrappingTableRecipe matchRecipe(ItemStack stackInSlot) {
//	if (world != null) {
//	    return world.getRecipeManager().getRecipes().stream()
//		    .filter(recipe -> recipe instanceof ScrappingTableRecipe)
//		    .map(recipe -> (ScrappingTableRecipe) recipe).filter(recipe -> recipe.matches(stackInSlot))
//		    .findFirst().orElse(null);
//	}
//	return null;
//    }

    @Override
    protected ItemStackHandler createHandler() {
	return new ItemStackHandler(1) {
	    @Override
	    protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
		return stack.getMaxStackSize();
	    }

	    @Override
	    protected void onContentsChanged(int slot) {
		updateInventory();
	    }
	};
    }

    public void extractInsertItem(PlayerEntity playerEntity, Hand hand) {
	handler.ifPresent(inventory -> {
	    ItemStack held = playerEntity.getHeldItem(hand);
	    if (!held.isEmpty()) {
		insertItem(inventory, held);
	    } else {
		extractItem(playerEntity, inventory);
	    }
	});
	updateInventory();
    }

    public void extractItem(PlayerEntity playerEntity, IItemHandler inventory) {
	if (!inventory.getStackInSlot(0).isEmpty()) {
	    ItemStack itemStack = inventory.extractItem(0, inventory.getStackInSlot(0).getCount(), false);
	    playerEntity.addItemStackToInventory(itemStack);
	}
	updateInventory();
    }

    public void insertItem(IItemHandler inventory, ItemStack heldItem) {
	if (inventory.isItemValid(0, heldItem))
	    if (!inventory.insertItem(0, heldItem, true).isItemEqual(heldItem)) {
		final int leftover = inventory.insertItem(0, heldItem.copy(), false).getCount();
		heldItem.setCount(leftover);
	    }
	updateInventory();
    }

    // External extract handler
    public void extractItem(PlayerEntity playerEntity) {
	handler.ifPresent(inventory -> {
	    this.extractItem(playerEntity, inventory);
	});
    }

    // External insert handler
    public void insertItem(ItemStack heldItem) {
	handler.ifPresent(inventory -> {
	    this.insertItem(inventory, heldItem);
	});
    }
//
//    public void updateInventory() {
//	requestModelDataUpdate();
//	this.markDirty();
//	if (this.getWorld() != null) {
//	    this.getWorld().notifyBlockUpdate(pos, this.getBlockState(), this.getBlockState(), 3);
//	}
//    }

//    public void hammer(PlayerEntity playerEntity, ItemStack hammer) {
////	if (lastStack != handler.getStackInSlot(0)) {
////	    progress = 0;
////	    lastStack = handler.getStackInSlot(0);
////	} else if (lastStack != ItemStack.EMPTY && lastStack.getItem() != Items.AIR) {
////	    this.progress++;
////	    hammer.damageItem(1, playerEntity, null);
////	    world.addParticle(new ItemParticleData(ParticleTypes.ITEM, lastStack), pos.getX() + 0.5f, pos.getY() + 1,
////		    pos.getZ() + 0.5f, (world.rand.nextFloat() - 0.5f) / 2, (world.rand.nextFloat() - 0.5f) / 2,
////		    (world.rand.nextFloat() - 0.5f) / 2);
////	    world.playSound(playerEntity, pos, SoundEvents.BLOCK_STONE_HIT, SoundCategory.BLOCKS,
////		    world.rand.nextFloat() + 0.5f, 0);
////	}
//	handler.ifPresent(inventory -> {
//
//	    ScrappingTableRecipe recipe = matchRecipe(inventory.getStackInSlot(0));
//	    if (recipe != null) {
//
//	    }
//	});
//
//	this.updateInventory();
//    }

//    ItemStack dropItemBelow(IItemHandler handler, ItemStack insert) {
//	for (int i = 0; i < handler.getSlots(); i++) {
//	    insert = handler.insertItem(i, insert, false);
//
//	    if (insert.isEmpty()) {
//		return ItemStack.EMPTY;
//	    }
//	}
//
//	return insert;
//    }
//
//    @Nullable
//    @Override
//    public SUpdateTileEntityPacket getUpdatePacket() {
//	return new SUpdateTileEntityPacket(this.getPos(), -1, this.getUpdateTag());
//    }
//
//    @Override
//    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
//	handleUpdateTag(this.getBlockState(), pkt.getNbtCompound());
//    }
//
//    @Override
//    @Nonnull
//    public CompoundNBT getUpdateTag() {
//	CompoundNBT updateTag = new CompoundNBT();
//	final IItemHandler itemHandler = getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
//		.orElseGet(this::createHandler);
//	CompoundNBT itemSlot = new CompoundNBT();
//	itemHandler.getStackInSlot(0).write(itemSlot);
//	updateTag.put("item", itemSlot);
//	return updateTag;
//    }
//
////    @Override
////    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
////	handler.deserializeNBT(tag.getCompound("items"));
////    }
//
//    @Override
//    public void read(BlockState state, @Nonnull CompoundNBT nbt) {
//	super.read(state, nbt);
//	progress = nbt.getInt("progress");
//	// lastStack.deserializeNBT(nbt.getCompound("lastStack"));
//	handler.ifPresent(iItemHandler -> {
//	    if (iItemHandler instanceof ItemStackHandler) {
//		((ItemStackHandler) iItemHandler).deserializeNBT(nbt.getCompound("inventory"));
//	    }
//	});
//    }
//
//    @Override
//    @Nonnull
//    public CompoundNBT write(@Nonnull CompoundNBT nbt) {
//	super.write(nbt);
//	nbt.putInt("progress", progress);
//	// nbt.put("lastStack", lastStack.serializeNBT());
//	handler.ifPresent(iItemHandler -> {
//	    if (iItemHandler instanceof ItemStackHandler) {
//		nbt.put("inventory", ((ItemStackHandler) iItemHandler).serializeNBT());
//	    }
//	});
//	return nbt;
//    }

//    private Optional<ScrappingTableRecipe> matchRecipe() {
//
//	if (world != null) {
//	    Optional<ScrappingTableRecipe> recipe = handler.map(i -> {
//		return world.getRecipeManager().getRecipe(WorkstationRecipes.CRAFTING_ANVIL_RECIPE,
//			new WorkstationRecipeWrapper(5, 5, i), world);
//	    }).get();
//
//	    // setRecipe(recipe);
//	    return recipe;
//	}
//	return null;
//    }

//    protected void setupStage(ScrappingTableRecipe r, int i) {
//
//	this.progress = 0;
//	this.maxProgress = r.getToolList().get(i).uses;
//	this.curTool = r.getToolList().get(i).tool;
//	this.toolCount = r.getToolList().get(i).count;
//
//	this.stage = i;
//    }

    @Override
    protected Optional<ScrappingTableRecipe> matchRecipe() {
	if (world != null) {
	    Optional<ScrappingTableRecipe> recipe = handler.map(i -> {
		return world.getRecipeManager().getRecipe(WorkstationRecipes.SCRAPPING_TABLE_RECIPE,
			new WorkstationRecipeWrapper(1, 1, i), world);
	    }).get();

	    // setRecipe(recipe);
	    return recipe;
	}
	return null;
    }

    @Override
    public void addParticle() {
	// TODO Auto-generated method stub

    }

    @Override
    public void finishRecipe(PlayerEntity playerEntity, ScrappingTableRecipe r) {
	handler.ifPresent(h -> {
	    if (this.world instanceof ServerWorld) {
		ResourceLocation rc = r.getLoottable();

		LootTable loottable = this.world.getServer().getLootTableManager().getLootTableFromLocation(rc);
		LootContext.Builder lootcontext$builder = new LootContext.Builder((ServerWorld) this.world)
			.withRandom(this.world.rand).withParameter(LootParameters.THIS_ENTITY, playerEntity)
			.withParameter(LootParameters.field_237457_g_,
				new Vector3d(this.pos.getX(), this.pos.getY(), this.pos.getZ()));

		LootContext ctx = lootcontext$builder.build(LootParameterSets.GIFT);
		List<ItemStack> items = loottable.generate(ctx);

		TileEntity te = world.getTileEntity(this.getPos().add(0, -1, 0));

		items.forEach(i -> {
		    if (te != null) {
			LazyOptional<IItemHandler> ih = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
				Direction.UP);

			final ItemStack it = i.copy();
			i = ih.map(j -> dropItemBelow(j, it)).get();
		    }
		    InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), i);

		});

	    }
	    craft();
	});
    }
}
