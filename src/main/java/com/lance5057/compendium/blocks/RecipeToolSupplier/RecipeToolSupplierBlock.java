package com.lance5057.compendium.blocks.RecipeToolSupplier;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RecipeToolSupplierBlock extends Block {

	public RecipeToolSupplierBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer,
			ItemStack stack) {
		BlockEntity b = level.getBlockEntity(pos);
		if (b != null) {
			if (b instanceof RecipeToolSupplierBlockEntity rtsb)
				rtsb.searchForWorkstations(level);
		}
	}
}
