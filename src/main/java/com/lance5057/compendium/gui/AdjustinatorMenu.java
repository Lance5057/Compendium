package com.lance5057.compendium.gui;

import com.lance5057.compendium.CompendiumMenus;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;

public class AdjustinatorMenu extends AbstractContainerMenu {
	private final ContainerLevelAccess access;
	public BlockPos pos;
	private final Player player;

	public AdjustinatorMenu(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) {
		this(windowId, playerInventory);
	}

	public AdjustinatorMenu(int pContainerId, Inventory pPlayerInventory) {
		this(pContainerId, pPlayerInventory, ContainerLevelAccess.NULL, BlockPos.ZERO);
	}

	public AdjustinatorMenu(int pContainerId, Inventory pPlayerInventory, final ContainerLevelAccess pAccess,
			BlockPos pos) {
		super(CompendiumMenus.ADJUSTINATOR_MENU.get(), pContainerId);
		this.access = pAccess;
		this.pos = pos;
		this.player = pPlayerInventory.player;
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean stillValid(Player player) {
		// TODO Auto-generated method stub
		return true;
	}

}
