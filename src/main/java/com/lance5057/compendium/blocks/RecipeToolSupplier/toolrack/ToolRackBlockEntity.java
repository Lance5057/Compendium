package com.lance5057.compendium.blocks.RecipeToolSupplier.toolrack;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.blocks.RecipeToolSupplier.RecipeToolSupplierBlockEntity;
import com.lance5057.compendium.workstations._bases.components.item.BlockEntityItemHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class ToolRackBlockEntity extends RecipeToolSupplierBlockEntity {

	public ToolRackBlockEntity(BlockPos pos, BlockState blockState) {
		super(CompendiumBlockEntities.TOOLRACK.get(), pos, blockState);
	}

	@Override
	protected BlockEntityItemHandler createItemHandler() {
		return new BlockEntityItemHandler(this, 4);
	}

	@Override
	protected boolean canAccept(ItemStack stack) {
		return false;
	}

}
