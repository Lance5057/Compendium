package com.lance5057.compendium.data;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.blocks.chair.ChairBlockEntity;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.material.base._MaterialBase;

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

			if (i instanceof _MaterialBase mb) {
				if (mb.getType() == MATERIAL_TYPES.WOOD) {
					ChairBlockEntity.back.forEach(b -> {
						withExistingParent("block/material/wood/" + mb.name + "/chair/back/" + b.toLowerCase(),
								modLoc("block/furniture/chair/back/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name + "_planks"));
					});

					ChairBlockEntity.legs.forEach(b -> {
						withExistingParent("block/material/wood/" + mb.name + "/chair/legs/" + b.toLowerCase(),
								modLoc("block/furniture/chair/legs/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name + "_planks"));
					});

					ChairBlockEntity.seat.forEach(b -> {
						withExistingParent("block/material/wood/" + mb.name + "/chair/seat/" + b.toLowerCase(),
								modLoc("block/furniture/chair/seat/" + b.toLowerCase()))
								.texture("0", mcLoc("block/" + mb.name + "_planks"));
					});
				}
			}
		});

		singleTexture("block/material/glass/clear_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/glass")).renderType("cutout");

		singleTexture("block/material/glass/white_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/white_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/light_gray_glass/window", Compendium.modLoc("block/slightlysmallerblock"),
				"0", mcLoc("block/light_gray_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/gray_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/gray_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/black_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/black_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/brown_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/brown_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/red_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/red_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/orange_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/orange_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/yellow_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/yellow_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/lime_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/lime_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/green_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/green_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/cyan_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/cyan_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/light_blue_glass/window", Compendium.modLoc("block/slightlysmallerblock"),
				"0", mcLoc("block/light_blue_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/blue_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/blue_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/purple_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/purple_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/magenta_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/magenta_stained_glass")).renderType("translucent");
		singleTexture("block/material/glass/pink_glass/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				mcLoc("block/pink_stained_glass")).renderType("translucent");

		singleTexture("block/material/glass/invalid/window", Compendium.modLoc("block/slightlysmallerblock"), "0",
				modLoc("block/prototypium_stained_glass")).renderType("translucent");

	}

}
