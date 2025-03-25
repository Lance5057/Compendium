package com.lance5057.compendium.blocks;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.blocks.entities.MultiMaterialBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class WindowBlockEntity extends MultiMaterialBlockEntity {

	public WindowBlockEntity(BlockPos pos, BlockState blockState) {
		super(CompendiumBlockEntities.WINDOW.get(), pos, blockState);
	}

}
