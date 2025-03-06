package com.lance5057.compendium.blocks.RecipeToolSupplier.drawer;

import java.util.Objects;

import com.lance5057.compendium.CompendiumBlocks;
import com.lance5057.compendium.CompendiumMenus;

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

public class ComponentDrawerMenu extends AbstractContainerMenu {
	public final ComponentDrawerBlockEntity blockEntity;
	private final ContainerLevelAccess canInteractWithCallable;
	protected final Level level;

	protected ComponentDrawerMenu(int windowId, final Inventory playerInventory,
			final ComponentDrawerBlockEntity tileEntity) {
		super(CompendiumMenus.COMPONENT_DRAWER_MENU.get(), windowId);
		this.blockEntity = (ComponentDrawerBlockEntity) tileEntity;
		this.level = playerInventory.player.level();
		this.canInteractWithCallable = ContainerLevelAccess.create(tileEntity.getLevel(), tileEntity.getBlockPos());

		if (tileEntity != null) {
			int startX = 8;
			int startY = 8;
			int inputStartX = 71;
			int inputStartY = 25;
			int borderSlotSize = 18;

			int row;
			int column;
			for (row = 0; row < 2; ++row) {
				for (column = 0; column < 2; ++column) {
					this.addSlot(new SlotItemHandler(tileEntity.getItems(), (row * 2) + column,
							inputStartX + (column * borderSlotSize), inputStartY + (row * borderSlotSize)) {
						@Override
						public boolean mayPlace(ItemStack stack) {
							return blockEntity.canAccept(stack);
						}
					});
				}
			}

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

	public ComponentDrawerMenu(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}

	private static ComponentDrawerBlockEntity getTileEntity(final Inventory playerInventory,
			final FriendlyByteBuf data) {
		Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final BlockEntity tileAtPos = playerInventory.player.level().getBlockEntity(data.readBlockPos());
		if (tileAtPos instanceof ComponentDrawerBlockEntity) {
			return (ComponentDrawerBlockEntity) tileAtPos;
		}
		throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
	}

	@Override
	public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
		int indexOutput = 4;
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
		return stillValid(canInteractWithCallable, pPlayer, CompendiumBlocks.COMPONENT_DRAWER.get());
	}
}
