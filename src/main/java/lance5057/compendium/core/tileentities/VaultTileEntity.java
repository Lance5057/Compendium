//package lance5057.compendium.core.tileentities;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//import lance5057.compendium.CompendiumTileEntities;
//import lance5057.compendium.core.workstations.recipes.HammeringStationRecipe;
//import net.minecraft.block.BlockState;
//import net.minecraft.entity.player.Player;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.network.NetworkManager;
//import net.minecraft.network.play.server.ClientboundBlockEntityDataPacket;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.Direction;
//import net.minecraft.util.InteractionHand;
//import net.minecraftforge.common.capabilities.Capability;
//import net.minecraftforge.common.util.LazyOptional;
//import net.minecraftforge.items.CapabilityItemHandler;
//import net.minecraftforge.items.IItemInteractionHandler;
//import net.minecraftforge.items.ItemStackInteractionHandler;
//
//public class VaultTileEntity extends TileEntity {
//
//    private final LazyOptional<IItemInteractionHandler> optional;
//    ItemStackInteractionHandler InteractionHandler;
//    private final int maxSize = 64;
//
//    public VaultTileEntity() {
//	super(CompendiumTileEntities.VAULT_TE.get());
//	InteractionHandler = createInteractionHandler();
//	optional = LazyOptional.of(() -> InteractionHandler);
//    }
//
//    private ItemStackInteractionHandler createInteractionHandler() {
//	return new ItemStackInteractionHandler(1) {
//	    @Override
//	    protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
//		return maxSize * stack.getMaxStackSize();
//	    }
//
//	    @Override
//	    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
//		if (this.getStackInSlot(slot) == ItemStack.EMPTY)
//		    return true;
//		else {
//		    if (ItemStack.areItemsEqual(getStackInSlot(slot), stack)) {
//			return true;
//		    }
//		}
//		return false;
//	    }
//	};
//    }
//
//    @Nonnull
//    @Override
//    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
//	if (cap == CapabilityItemHandler.ITEM_InteractionHandLER_CAPABILITY) {
//	    return optional.cast();
//	}
//	return super.getCapability(cap, side);
//    }
//
//    public void extractInsertItem(Player Player, InteractionHand InteractionHand) {
//	ItemStack held = Player.getHeldItem(InteractionHand);
//	if (!held.isEmpty()) {
//	    insertItem(InteractionHandler, held);
//	} else {
//	    extractItem(Player, InteractionHandler);
//	}
//
//	updateInventory();
//    }
//
//    public void extractItem(Player Player, IItemInteractionHandler inventory) {
//	if (!inventory.getStackInSlot(0).isEmpty()) {
//	    ItemStack itemStack = ItemStack.EMPTY;
//	    if (!Player.isCrouching())
//		itemStack = inventory.extractItem(0, 1, false);
//	    else
//		itemStack = inventory.extractItem(0, inventory.getStackInSlot(0).getMaxStackSize(), false);
//	    Player.addItemStackToInventory(itemStack);
//	}
//	updateInventory();
//    }
//
//    public void insertItem(IItemInteractionHandler inventory, ItemStack heldItem) {
//	if (inventory.isItemValid(0, heldItem))
//	    if (!inventory.insertItem(0, heldItem, true).isItemEqual(heldItem)) {
//		final int leftover = inventory.insertItem(0, heldItem.copy(), false).getCount();
//		heldItem.setCount(leftover);
//	    }
//	updateInventory();
//    }
//
//    // External extract InteractionHandler
//    public void extractItem(Player Player) {
//	this.extractItem(Player, InteractionHandler);
//    }
//
//    // External insert InteractionHandler
//    public void insertItem(ItemStack heldItem) {
//	this.insertItem(InteractionHandler, heldItem);
//    }
//
//    public void updateInventory() {
//	requestModelDataUpdate();
//	this.markDirty();
//	if (this.getLevel() != null) {
//	    this.getLevel().notifyBlockUpdate(pos, this.getBlockState(), this.getBlockState(), 3);
//	}
//    }
//
//    @Nullable
//    @Override
//    public ClientboundBlockEntityDataPacket getUpdatePacket() {
//	return new ClientboundBlockEntityDataPacket(this.getPos(), -1, this.getUpdateTag());
//    }
//
//    @Override
//    public void onDataPacket(NetworkManager net, ClientboundBlockEntityDataPacket pkt) {
//	InteractionHandleUpdateTag(this.getBlockState(), pkt.getNbtCompound());
//    }
//
//    @Override
//    @Nonnull
//    public CompoundTag getUpdateTag() {
//	CompoundTag updateTag = super.getUpdateTag();
//	updateTag.put("items", InteractionHandler.serializeNBT());
//	return updateTag;
//    }
//
//    @Override
//    public void InteractionHandleUpdateTag(BlockState state, CompoundTag tag) {
//	InteractionHandler.deserializeNBT(tag.getCompound("items"));
//    }
//
//    @Override
//    public void read(BlockState state, @Nonnull CompoundTag nbt) {
//	super.read(state, nbt);
//
//	InteractionHandler.deserializeNBT(nbt.getCompound("items"));
//    }
//
//    @Override
//    @Nonnull
//    public CompoundTag write(@Nonnull CompoundTag nbt) {
//	CompoundTag n = super.write(nbt);
//
//	n.put("items", InteractionHandler.serializeNBT());
//	return n;
//    }
//}
