package com.lance5057.compendium.blocks.entities;

import com.lance5057.compendium.CompendiumBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class WindowBlockEntity extends MultiMaterialBlockEntity {

	public WindowBlockEntity(BlockPos pos, BlockState blockState) {
		super(CompendiumBlockEntities.WINDOW.get(), pos, blockState);
	}

	@Override
	public int getMaterialsCount() {
		return 1;
	}

}
