package com.lance5057.compendium.gui;

import com.lance5057.compendium.CompendiumMenus;
import com.lance5057.compendium.blocks.entities.MultiMaterialBlockEntity;
import com.lance5057.compendium.network.AdjustinatorSyncPacket;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

public class AdjustinatorMultiMaterialMenu extends AbstractContainerMenu {

	private final ContainerLevelAccess access;
	public BlockPos pos;
	private final Player player;
	private final MultiMaterialBlockEntity multimaterial;

	public MultiMaterialBlockEntity getMultiMaterial() {
		return multimaterial;
	}

	public AdjustinatorMultiMaterialMenu(final int windowId, final Inventory playerInventory,
			final FriendlyByteBuf data) {
		this(windowId, playerInventory);
	}

	public AdjustinatorMultiMaterialMenu(int pContainerId, Inventory pPlayerInventory) {
		this(pContainerId, pPlayerInventory, ContainerLevelAccess.NULL, BlockPos.ZERO, null);
	}

	public AdjustinatorMultiMaterialMenu(int pContainerId, Inventory pPlayerInventory,
			final ContainerLevelAccess pAccess, BlockPos pos, MultiMaterialBlockEntity mmbe) {
		super(CompendiumMenus.ADJUSTINATOR_MULTIMATERIAL_MENU.get(), pContainerId);
		this.access = pAccess;
		this.pos = pos;
		this.player = pPlayerInventory.player;
		this.multimaterial = mmbe;
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

	public void syncBlockFromRemote(int i, String s) {
		this.access.execute((level, pos) -> {
			BlockEntity state = level.getBlockEntity(pos);

			this.multimaterial.setMaterial(i, s);

			state.getLevel().sendBlockUpdated(pos, state.getBlockState(), state.getBlockState(), Block.UPDATE_ALL);
		});
	}

}
