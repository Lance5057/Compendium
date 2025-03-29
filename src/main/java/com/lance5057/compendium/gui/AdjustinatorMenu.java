package com.lance5057.compendium.gui;

import com.lance5057.compendium.CompendiumMenus;
import com.lance5057.compendium.network.AdjustinatorPacket;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;

public class AdjustinatorMenu extends AbstractContainerMenu {
	public static enum MODES {
		NONE, STATION, MULTIMATERIAL
	};

	private final ContainerLevelAccess access;
	public BlockPos pos;
	private final Player player;
	private final MODES mode;

	public MODES getMode() {
		return mode;
	}

	public AdjustinatorMenu(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) {
		this(windowId, playerInventory);
	}

	public AdjustinatorMenu(int pContainerId, Inventory pPlayerInventory) {
		this(pContainerId, pPlayerInventory, ContainerLevelAccess.NULL, BlockPos.ZERO, MODES.STATION);
	}

	public AdjustinatorMenu(int pContainerId, Inventory pPlayerInventory, final ContainerLevelAccess pAccess,
			BlockPos pos, MODES m) {
		super(CompendiumMenus.ADJUSTINATOR_MENU.get(), pContainerId);
		this.access = pAccess;
		this.pos = pos;
		this.player = pPlayerInventory.player;
		this.mode = m;
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
			serverPlayer.connection.send(new AdjustinatorPacket(this.containerId, this.pos, this.mode.toString()));
	}

}
