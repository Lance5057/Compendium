package com.lance5057.compendium.blocks.entities;

import java.util.stream.Stream;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialModelData;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;

public class WindowBlockEntity extends MultiMaterialBlockEntity {

	public WindowBlockEntity(BlockPos pos, BlockState blockState) {
		super(CompendiumBlockEntities.WINDOW.get(), pos, blockState, Stream.of("invalid").toList());
	}
	
	@Override
	public ModelData getModelData() {
		return MultiMaterialModelData.builder(this.materials).build();
	}

	@Override
	public int getMaterialsCount() {
		return 1;
	}
}
