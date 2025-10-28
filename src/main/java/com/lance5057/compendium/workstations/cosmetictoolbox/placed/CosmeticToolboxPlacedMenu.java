package com.lance5057.compendium.workstations.cosmetictoolbox.placed;

import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.CompendiumMenus;
import com.lance5057.compendium.network.StyleSyncPacket;
import com.lance5057.compendium.styleblock.StyleBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

public class CosmeticToolboxPlacedMenu extends AbstractContainerMenu {
	private final StyleContainer container = new StyleContainer();
	private final ContainerLevelAccess access;
	private final Player player;

	public CosmeticToolboxPlacedMenu(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) {
		this(windowId, playerInventory);
	}

	public CosmeticToolboxPlacedMenu(int pContainerId, Inventory pPlayerInventory) {
		this(pContainerId, pPlayerInventory, ContainerLevelAccess.NULL);
	}

	public CosmeticToolboxPlacedMenu(int pContainerId, Inventory pPlayerInventory, final ContainerLevelAccess pAccess) {
		super(CompendiumMenus.PLACED_STYLE_MENU.get(), pContainerId);
		this.access = pAccess;
		this.player = pPlayerInventory.player;

		int startX = 8;
		int startY = 8;
		int borderSlotSize = 18;

		this.addSlot(new Slot(container, 0, -47, 67));

		// Main Player Inventory
		int startPlayerInvY = startY * 4 + 89;
		for (int r = 0; r < 3; ++r) {
			for (int c = 0; c < 9; ++c) {
				this.addSlot(new Slot(pPlayerInventory, 9 + (r * 9) + c, startX + (c * borderSlotSize),
						startPlayerInvY + (r * borderSlotSize)));
			}
		}

		// Hotbar
		for (int c = 0; c < 9; ++c) {
			this.addSlot(new Slot(pPlayerInventory, c, startX + (c * borderSlotSize), 179));
		}

	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index == 0) {
				if (!this.moveItemStackTo(itemstack1, 1, 36, false))
					return ItemStack.EMPTY;
			} else if (!this.moveItemStackTo(itemstack1, 0, 1, true)) {
//				return ItemStack.EMPTY;

				if (itemstack1.isEmpty()) {
					slot.setByPlayer(ItemStack.EMPTY);
				} else {
					slot.setChanged();
				}

				if (itemstack1.getCount() == itemstack.getCount()) {
					return ItemStack.EMPTY;
				}

				slot.onTake(player, itemstack1);
				if (index == 0) {
					player.drop(itemstack1, false);
				}
			}
		}
		return itemstack;
	}

	@Override
	public boolean stillValid(Player player) {
		return true;
	}

	@Override
	public void sendAllDataToRemote() {
		super.sendAllDataToRemote();
		if (this.player instanceof ServerPlayer serverPlayer)
			serverPlayer.connection.send(new StyleSyncPacket(this.containerId, BlockPos.ZERO));
	}

	@Override
	public void removed(Player player) {
		super.removed(player);
		this.access.execute((p_39371_, p_39372_) -> this.clearContainer(player, this.container));
	}

}
