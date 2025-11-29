package com.lance5057.compendium.gui;

import com.lance5057.compendium.CompendiumMenus;
import com.lance5057.compendium.network.AdjustinatorSyncPacket;
import com.lance5057.compendium.workstations._bases.blocks.StationBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;

public class AdjustinatorWorkstationMenu extends AbstractContainerMenu {

	private final ContainerLevelAccess access;
	public BlockPos pos;
	private final Player player;
	private final StationBlock guiless;

	public StationBlock getGuiless() {
		return guiless;
	}

	public AdjustinatorWorkstationMenu(final int windowId, final Inventory playerInventory,
			final FriendlyByteBuf data) {
		this(windowId, playerInventory);
	}

	public AdjustinatorWorkstationMenu(int pContainerId, Inventory pPlayerInventory) {
		this(pContainerId, pPlayerInventory, ContainerLevelAccess.NULL, BlockPos.ZERO, null);
	}

	public AdjustinatorWorkstationMenu(int pContainerId, Inventory pPlayerInventory, final ContainerLevelAccess pAccess,
			BlockPos pos, StationBlock guiless) {
		super(CompendiumMenus.ADJUSTINATOR_WORKSTATION_MENU.get(), pContainerId);
		this.access = pAccess;
		this.pos = pos;
		this.player = pPlayerInventory.player;
		this.guiless = guiless;
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

	@Override
	public void sendAllDataToRemote() {
		super.sendAllDataToRemote();
		if (this.player instanceof ServerPlayer serverPlayer)
			serverPlayer.connection.send(new AdjustinatorSyncPacket(this.containerId, this.pos));
	}

}
