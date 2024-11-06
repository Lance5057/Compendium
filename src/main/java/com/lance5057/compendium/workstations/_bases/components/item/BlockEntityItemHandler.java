package com.lance5057.compendium.workstations._bases.components.item;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.ItemStackHandler;

public class BlockEntityItemHandler extends ItemStackHandler {

	BlockEntity be;

	public BlockEntityItemHandler(BlockEntity be, int size) {
		super(size);
		this.be = be;
	}
}
