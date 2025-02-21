package com.lance5057.compendium.workstations._bases.components.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.ItemStackHandler;

public class BlockEntityItemHandler<T extends BlockEntity> extends ItemStackHandler {

	T be;

	public T getBe() {
		return be;
	}

	public BlockEntityItemHandler(T be, int size) {
		super(size);
		this.be = be;
	}

	public boolean isEmpty() {
		for (ItemStack s : this.stacks)
			if (!s.isEmpty())
				return false;
		return true;
	}

	public void shrinkAll() {
		for (ItemStack s : this.stacks) {
			s.shrink(1);
		}
	}

	public void shrinkAll(int i) {
		for (ItemStack s : this.stacks) {
			s.shrink(i);
		}
	}
}
