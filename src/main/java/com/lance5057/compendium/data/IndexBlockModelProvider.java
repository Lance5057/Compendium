package com.lance5057.compendium.data;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.material.base.MaterialGlass;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class IndexBlockModelProvider extends BlockModelProvider {

	public IndexBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, Compendium.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {

		CompendiumIndex.index.forEach(i -> {
			i.blockModel(this);
		});

		cubeAll("block/material/glass/clear_glass/window", mcLoc("block/glass")).renderType("cutout");
		
		cubeAll("block/material/glass/red_glass/window", mcLoc("block/glass")).renderType("cutout");
		
		cubeAll("block/material/glass/white_glass/window", mcLoc("block/glass")).renderType("cutout");
		cubeAll("block/material/glass/light_gray_glass/window", mcLoc("block/light_gray_stained_glass")).renderType("cutout");
		cubeAll("block/material/glass/gray_glass/window", mcLoc("block/gray_stained_glass")).renderType("cutout");
		cubeAll("block/material/glass/black_glass/window", mcLoc("block/black_stained_glass")).renderType("cutout");
		cubeAll("block/material/glass/brown_glass/window", mcLoc("block/brown_stained_glass")).renderType("cutout");
		cubeAll("block/material/glass/red_glass/window", mcLoc("block/red_stained_glass")).renderType("cutout");
		cubeAll("block/material/glass/orange_glass/window", mcLoc("block/orange_stained_glass")).renderType("cutout");
		cubeAll("block/material/glass/yellow_glass/window", mcLoc("block/yellow_stained_glass")).renderType("cutout");
		cubeAll("block/material/glass/lime_glass/window", mcLoc("block/lime_stained_glass")).renderType("cutout");
		cubeAll("block/material/glass/green_glass/window", mcLoc("block/green_stained_glass")).renderType("cutout");
		cubeAll("block/material/glass/cyan_glass/window", mcLoc("block/cyan_stained_glass")).renderType("cutout");
		cubeAll("block/material/glass/light_blue_glass/window", mcLoc("block/light_blue_stained_glass")).renderType("cutout");
		cubeAll("block/material/glass/blue_glass/window", mcLoc("block/blue_stained_glass")).renderType("cutout");
		cubeAll("block/material/glass/purple_glass/window", mcLoc("block/purple_stained_glass")).renderType("cutout");
		cubeAll("block/material/glass/magenta_glass/window", mcLoc("block/magenta_stained_glass")).renderType("cutout");
		cubeAll("block/material/glass/pink_glass/window", mcLoc("block/pink_stained_glass")).renderType("cutout");
	}

}
