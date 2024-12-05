package com.lance5057.compendium.data;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumBlocks;
import com.lance5057.compendium.index.CompendiumIndex;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockModels extends BlockStateProvider {

	public BlockModels(PackOutput gen, ExistingFileHelper exFileHelper) {
		super(gen, Compendium.MOD_ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		CompendiumIndex.index.forEach(i -> {
			i.blockModel(this);
		});

		this.simpleBlock(CompendiumBlocks.HAMMERING_STATION.get(), models().getExistingFile(modLoc("block/workstations/hammering_station")));
	}

}
