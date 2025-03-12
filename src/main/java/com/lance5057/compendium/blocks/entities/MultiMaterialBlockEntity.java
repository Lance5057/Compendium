package com.lance5057.compendium.blocks.entities;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.client.models.MultiMaterialModelData;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;

public class MultiMaterialBlockEntity extends BlockEntity {
	
	String[] materials;

	public MultiMaterialBlockEntity(BlockPos pos, BlockState blockState) {
		super(CompendiumBlockEntities.MULTIMATERIAL.get(), pos, blockState);
	}

	@Override
	public ModelData getModelData() {
		return MultiMaterialModelData.builder(null).build();
	}
}
