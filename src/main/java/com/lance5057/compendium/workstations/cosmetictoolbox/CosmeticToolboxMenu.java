package com.lance5057.compendium.workstations.cosmetictoolbox;

import com.lance5057.compendium.CompendiumMenus;
import com.lance5057.compendium.blocks.entities.SimpleStyleBlockEntity;
import com.lance5057.compendium.network.StyleSyncPacket;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class CosmeticToolboxMenu extends AbstractContainerMenu {
	private final ContainerLevelAccess access;
	public BlockPos pos;
	private final Player player;

	public CosmeticToolboxMenu(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) {
		this(windowId, playerInventory);
	}

	public CosmeticToolboxMenu(int pContainerId, Inventory pPlayerInventory) {
		this(pContainerId, pPlayerInventory, ContainerLevelAccess.NULL, BlockPos.ZERO);
	}

	public CosmeticToolboxMenu(int pContainerId, Inventory pPlayerInventory, final ContainerLevelAccess pAccess,
			BlockPos pos) {
		super(CompendiumMenus.STYLE_MENU.get(), pContainerId);
		this.access = pAccess;
		this.pos = pos;
		this.player = pPlayerInventory.player;

	}

	@Override
	public boolean clickMenuButton(Player p_39465_, int p_39466_) {
		this.access.execute((level, pos) -> {
			BlockEntity state = level.getBlockEntity(pos);
			if (state instanceof SimpleStyleBlockEntity s) {
				s.getStyles().get(0).setStyle(p_39466_);
				s.requestModelDataUpdate();
				s.setChanged();
			}
		});

		return true;
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean stillValid(Player player) {
		// TODO Auto-generated method stubvvvv
		return true;
	}

	@Override
	public void sendAllDataToRemote() {
		super.sendAllDataToRemote();
		if (this.player instanceof ServerPlayer serverPlayer)
			serverPlayer.connection.send(new StyleSyncPacket(this.containerId, this.pos));
	}

}
