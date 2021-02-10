package lance5057.compendium.core.tileentities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lance5057.compendium.CompendiumTileEntities;
import lance5057.compendium.core.workstations.recipes.HammeringStationRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class VaultTileEntity extends TileEntity {

    private final LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);
    private final int maxSize = 64;

    public VaultTileEntity() {
	super(CompendiumTileEntities.VAULT_TE.get());
    }

    private IItemHandler createHandler() {
	return new ItemStackHandler(1) {
	    @Override
	    protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
		return maxSize * stack.getMaxStackSize();
	    }

	    @Override
	    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
		if (this.getStackInSlot(slot) == ItemStack.EMPTY)
		    return true;
		else {
		    if (ItemStack.areItemsEqual(getStackInSlot(slot), stack)) {
			return true;
		    }
		}
		return false;
	    }
	};
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
	if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
	    return handler.cast();
	}
	return super.getCapability(cap, side);
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
	    ItemStack itemStack = ItemStack.EMPTY;
	    if (!playerEntity.isCrouching())
		itemStack = inventory.extractItem(0, 1, false);
	    else
		itemStack = inventory.extractItem(0, inventory.getStackInSlot(0).getMaxStackSize(), false);
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

    public void updateInventory() {
	requestModelDataUpdate();
	this.markDirty();
	if (this.getWorld() != null) {
	    this.getWorld().notifyBlockUpdate(pos, this.getBlockState(), this.getBlockState(), 3);
	}
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
	return new SUpdateTileEntityPacket(this.getPos(), -1, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
	handleUpdateTag(this.getBlockState(), pkt.getNbtCompound());
    }

    @Override
    @Nonnull
    public CompoundNBT getUpdateTag() {
	CompoundNBT updateTag = new CompoundNBT();
	final IItemHandler itemHandler = getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		.orElseGet(this::createHandler);
	CompoundNBT itemSlot = new CompoundNBT();
	itemHandler.getStackInSlot(0).write(itemSlot);
	updateTag.put("item", itemSlot);
	return updateTag;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
	final IItemHandler itemHandler = getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		.orElseGet(this::createHandler);
	((ItemStackHandler) itemHandler).setStackInSlot(0, ItemStack.read(tag.getCompound("item")));

    }

    @Override
    public void read(BlockState state, @Nonnull CompoundNBT nbt) {
	super.read(state, nbt);
	handler.ifPresent(iItemHandler -> {
	    if (iItemHandler instanceof ItemStackHandler) {
		((ItemStackHandler) iItemHandler).deserializeNBT(nbt.getCompound("inventory"));
	    }
	});
    }

    @Override
    @Nonnull
    public CompoundNBT write(@Nonnull CompoundNBT nbt) {
	super.write(nbt);
	handler.ifPresent(iItemHandler -> {
	    if (iItemHandler instanceof ItemStackHandler) {
		nbt.put("inventory", ((ItemStackHandler) iItemHandler).serializeNBT());
	    }
	});
	return nbt;
    }
}
