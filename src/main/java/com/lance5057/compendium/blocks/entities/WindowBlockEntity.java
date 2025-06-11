package com.lance5057.compendium.blocks.entities;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialModelData;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.multimaterial.MultiMaterialType;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;

public class WindowBlockEntity extends MultiMaterialBlockEntity {

	public WindowBlockEntity(BlockPos pos, BlockState blockState) {
		super(CompendiumBlockEntities.WINDOW.get(), pos, blockState,
				new ArrayList<MultiMaterialType>(List.of(new MultiMaterialType(MATERIAL_TYPES.METAL.toString()))));
	}

	@Override
	public ModelData getModelData() {
		return MultiMaterialModelData.builder(this.materials).build();
	}

	@Override
	public int getMaterialsCount() {
		return 2;
	}

}
