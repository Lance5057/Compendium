package com.lance5057.compendium.workstations.workbench;

import java.util.Objects;

import com.lance5057.compendium.CompendiumBlocks;
import com.lance5057.compendium.CompendiumMenus;
import com.lance5057.compendium.workstations._bases.blockentities.MultiToolRecipeStation;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;

public class WorkbenchMenu extends AbstractContainerMenu {

	public final WorkbenchBlockEntity blockEntity;
	private final ContainerLevelAccess canInteractWithCallable;
	protected final Level level;

	protected WorkbenchMenu(int windowId, final Inventory playerInventory, final MultiToolRecipeStation<?> tileEntity) {
		super(CompendiumMenus.WORKBENCH_MENU.get(), windowId);
		this.blockEntity = (WorkbenchBlockEntity) tileEntity;
		this.level = playerInventory.player.level();
		this.canInteractWithCallable = ContainerLevelAccess.create(tileEntity.getLevel(), tileEntity.getBlockPos());

		if (tileEntity != null) {
			// Ingredient Slots - 2 Rows x 3 Columns
			int startX = 8;
			int startY = 8;
			int inputStartX = 13;
			int inputStartY = -11;
			int borderSlotSize = 18;

			int row;
			int column;
			for (row = 0; row < 5; ++row) {
				for (column = 0; column < 5; ++column) {
					this.addSlot(new SlotItemHandler(tileEntity.getInventory(), (row * 5) + column,
							inputStartX + (column * borderSlotSize), inputStartY + (row * borderSlotSize)) {
						@Override
						public boolean mayPlace(ItemStack stack) {
							int r = (int) Math.floor((float) index / 5);
							int c = index % 5;
							if (r == 0 || r == 4 || c == 0 || c == 4) {

								if (blockEntity.getGridLevel() <= 3)
									return false;
							}
							if (c == 4 || r == 4) {
								if (blockEntity.getGridLevel() <= 4)
									return false;
							}
							return true;
						}

						@Override
						public boolean isActive() {
							int r = (int) Math.floor((float) index / 5);
							int c = index % 5;
							if (r == 0 || r == 4 || c == 0 || c == 4) {

								if (blockEntity.getGridLevel() <= 3)
									return false;
							}
							if (c == 4 || r == 4) {
								if (blockEntity.getGridLevel() <= 4)
									return false;
							}
							return true;
						}
					});
				}
			}

			this.addSlot(
					new SlotItemHandler(tileEntity.getInventory(), WorkbenchBlockEntity.UPGRADE_4x4_SLOT, 181, -8));
			this.addSlot(
					new SlotItemHandler(tileEntity.getInventory(), WorkbenchBlockEntity.UPGRADE_5x5_SLOT, 181, 10));

			this.addSlot(
					new SlotItemHandler(tileEntity.getInventory(), WorkbenchBlockEntity.UPGRADE_LIGHT_SLOT, 181, 28));
			this.addSlot(new SlotItemHandler(tileEntity.getInventory(), WorkbenchBlockEntity.UPGRADE_ENERGY, 181, 46));
			this.addSlot(new SlotItemHandler(tileEntity.getInventory(), WorkbenchBlockEntity.UPGRADE_BATTERY, 181, 64));
			this.addSlot(new SlotItemHandler(tileEntity.getInventory(), WorkbenchBlockEntity.UPGRADE_TIME, 181, 82));

			this.addSlot(new SlotItemHandler(tileEntity.getInventory(), WorkbenchBlockEntity.OUTPUT_SLOT, 143, 51));
			this.addSlot(
					new SlotItemHandler(tileEntity.getInventory(), WorkbenchBlockEntity.PRODUCT_DISPLAY_SLOT, 143, 25) {
						@Override
						public boolean mayPickup(Player playerIn) {
							return false;
						}

						@Override
						public ItemStack remove(int amount) {
							return ItemStack.EMPTY.copy();
						}

						@Override
						public boolean mayPlace(ItemStack stack) {
							return false;
						}
					});

			// Main Player Inventory
			int startPlayerInvY = startY * 4 + 60;
			for (int r = 0; r < 3; ++r) {
				for (int c = 0; c < 9; ++c) {
					this.addSlot(new Slot(playerInventory, 9 + (r * 9) + c, startX + (c * borderSlotSize),
							startPlayerInvY + (r * borderSlotSize)));
				}
			}

			// Hotbar
			for (int c = 0; c < 9; ++c) {
				this.addSlot(new Slot(playerInventory, c, startX + (c * borderSlotSize), 150));
			}

		}
	}

	public WorkbenchMenu(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}

	private static WorkbenchBlockEntity getTileEntity(final Inventory playerInventory, final FriendlyByteBuf data) {
		Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final BlockEntity tileAtPos = playerInventory.player.level().getBlockEntity(data.readBlockPos());
		if (tileAtPos instanceof WorkbenchBlockEntity) {
			return (WorkbenchBlockEntity) tileAtPos;
		}
		throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
	}

	@Override
	public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
		int indexOutput = 9;
		int startPlayerInv = indexOutput;
		int endPlayerInv = startPlayerInv + 36;

		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(pIndex);
		if (slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();

			if (pIndex < startPlayerInv) {
				if (!this.moveItemStackTo(itemstack1, startPlayerInv, endPlayerInv, true)) {
					return ItemStack.EMPTY;
				}

			} else {
				if (!this.moveItemStackTo(itemstack1, 0, indexOutput, false)) {
					return ItemStack.EMPTY;
				}
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(pPlayer, itemstack1);
		}
		return itemstack;
	}

	@Override
	public boolean stillValid(Player pPlayer) {
		return stillValid(canInteractWithCallable, pPlayer, CompendiumBlocks.WORKBENCH.get());
	}

}
