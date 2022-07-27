package lance5057.compendium.core.tileentities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lance5057.compendium.CompendiumTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class HandTileEntity extends BlockEntity {

	public HandTileEntity(BlockPos p_155229_, BlockState p_155230_) {
		super(CompendiumTileEntities.HAND_DISPLAY_TE.get(), p_155229_, p_155230_);
		// TODO Auto-generated constructor stub
	}

	protected final LazyOptional<IItemHandlerModifiable> InteractionHandler = LazyOptional
			.of(this::createInteractionHandler);

	private ItemStackHandler createInteractionHandler() {
		return new ItemStackHandler(1) {
			@Override
			protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
				return 1;
			}

			@Override
			public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
				if (this.getStackInSlot(slot) == ItemStack.EMPTY)
					return true;
				else {
					if (ItemStack.matches(getStackInSlot(slot), stack)) {
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
			return InteractionHandler.cast();
		}
		return super.getCapability(cap, side);
	}

	public void extractInsertItem(Player Player, InteractionHand InteractionHand) {
		ItemStack held = Player.getItemInHand(InteractionHand);
		if (!held.isEmpty()) {
			insertItem(InteractionHandler, held);
		} else {
			extractItem(Player, InteractionHandler);
		}

		updateInventory();
	}

	public void extractItem(Player Player, LazyOptional<IItemHandlerModifiable> interactionHandler2) {
		interactionHandler2.ifPresent(r -> {
			if (!r.getStackInSlot(0).isEmpty()) {
				ItemStack itemStack = ItemStack.EMPTY;
				if (!Player.isCrouching())
					itemStack = r.extractItem(0, 1, false);
				else
					itemStack = r.extractItem(0, r.getStackInSlot(0).getMaxStackSize(), false);
				Player.addItem(itemStack);
			}
			changeLight();
			updateInventory();
		});
	}

	public void insertItem(LazyOptional<IItemHandlerModifiable> interactionHandler2, ItemStack heldItem) {
		interactionHandler2.ifPresent(r -> {
			if (r.isItemValid(0, heldItem))
				if (!r.insertItem(0, heldItem, true).sameItem(heldItem)) {
					final int leftover = r.insertItem(0, heldItem.copy(), false).getCount();
					heldItem.setCount(leftover);
				}
			changeLight();
			updateInventory();
		});
	}

	// External extract InteractionHandler
	public void extractItem(Player Player) {
		this.extractItem(Player, InteractionHandler);
	}

	// External insert InteractionHandler
	public void insertItem(ItemStack heldItem) {
		this.insertItem(InteractionHandler, heldItem);
	}

	private void changeLight() {
		InteractionHandler.ifPresent(r -> {
			Item i = r.getStackInSlot(0).getItem();

			if (i == Items.GLOWSTONE || i == Items.GLOWSTONE_DUST || i == Items.TORCH || i == Items.LANTERN)
				this.level.setBlock(this.worldPosition,
						this.level.getBlockState(this.worldPosition).setValue(AbstractFurnaceBlock.LIT, true), 3);
			else
				this.level.setBlock(this.worldPosition,
						this.level.getBlockState(this.worldPosition).setValue(AbstractFurnaceBlock.LIT, false), 3);
		});
	}

	public void updateInventory() {
		requestModelDataUpdate();
		this.setChanged();
		if (this.getLevel() != null) {
			this.getLevel().sendBlockUpdated(worldPosition, this.getBlockState(), this.getBlockState(), 3);
		}
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

	}

	CompoundTag writeNBT(CompoundTag tag) {

		IItemHandler itemInteractionHandler = getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				.orElseGet(this::createInteractionHandler);
		tag.put("inventory", ((ItemStackHandler) itemInteractionHandler).serializeNBT());

		return tag;
	}

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
