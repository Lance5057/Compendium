package com.lance5057.compendium.index.material.base.stone;

import java.util.Map;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumClient;
import com.lance5057.compendium.client.ClientUtil;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.material.extensions.gem.ExtensionGemStyleBlocks;
import com.lance5057.compendium.index.material.extensions.stone.ExtensionStoneStyleBlocks;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.index.util.CompendiumItemHandler;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.util.TagUtil;

import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.ModelEvent.ModifyBakingResult;
import net.neoforged.neoforge.client.model.RegistryAwareItemModelShaper;

public class ClientStone {

	public static void doItems(RegistryAwareItemModelShaper shaper, MaterialStone mm) {
		for (CompendiumItemHandler i : mm.ITEMS) {
			if (i.shouldGenerate())
				shaper.register(i.ITEM.asItem(), new ModelResourceLocation(ClientUtil.createItemLocation(i.name), ""));
		}

		for (CompendiumBlockHandler i : mm.BLOCKS) {
			if (i.shouldGenerate())
				shaper.register(i.BLOCK_ITEM.asItem(), ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
		}

		for (_MaterialExtension me : mm.extensions) {
			for (CompendiumItemHandler i : me.ITEMS) {
				if (i.shouldGenerate())
					shaper.register(i.ITEM.asItem(),
							new ModelResourceLocation(ClientUtil.createItemLocation(i.name), ""));
			}

			for (CompendiumBlockHandler i : me.BLOCKS) {
				if (i.shouldGenerate())
					shaper.register(i.BLOCK_ITEM.asItem(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
			}
		}
	}

	public static void doStone(ModifyBakingResult event, MaterialStone mw) {
		ClientStone.doStyleStone(event, mw);
		ResourceLocation stoneTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/material/stone/" + mw.name + "/stone");
		ResourceLocation cobblestoneTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/material/stone/" + mw.name + "/cobblestone");
		ResourceLocation smoothstoneTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/material/stone/" + mw.name + "/smooth_stone");

		if (mw.specialLocations != null) {
			if (mw.specialLocations.textures != null) {
				if (mw.specialLocations.textures.stoneLocation != null)
					stoneTexture = mw.specialLocations.textures.stoneLocation;
				if (mw.specialLocations.textures.cobblestoneLocation != null)
					cobblestoneTexture = mw.specialLocations.textures.cobblestoneLocation;
				if (mw.specialLocations.textures.smoothstoneLocation != null)
					smoothstoneTexture = mw.specialLocations.textures.smoothstoneLocation;
			}
		}

		if (mw.STONE.shouldGenerate()) {
			ResourceLocation loc = TagUtil.modLoc("block/cube_all");
			ResourceLocation modelLoc = TagUtil.modLoc(mw.name + "_block");
			ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");

			BakedModel bm = CompendiumClient.basicModelAllTexture(event, stoneTexture, loc, m, BlockModelRotation.X0_Y0,
					"all");
			event.getModels().put(m, bm);
		}

		if (mw.COBBLESTONE.shouldGenerate()) {
			ResourceLocation loc = TagUtil.modLoc("block/cube_all");
			ResourceLocation modelLoc = TagUtil.modLoc(mw.name + "_block");
			ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");

			BakedModel bm = CompendiumClient.basicModelAllTexture(event, cobblestoneTexture, loc, m,
					BlockModelRotation.X0_Y0, "all");
			event.getModels().put(m, bm);
		}

		if (mw.SMOOTH_STONE.shouldGenerate()) {
			ResourceLocation loc = TagUtil.modLoc("block/cube_all");
			ResourceLocation modelLoc = TagUtil.modLoc(mw.name + "_block");
			ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");

			BakedModel bm = CompendiumClient.basicModelAllTexture(event, smoothstoneTexture, loc, m,
					BlockModelRotation.X0_Y0, "all");
			event.getModels().put(m, bm);
		}
	}

	private static void doStyleStone(ModifyBakingResult event, MaterialStone mw) {
		for (_MaterialExtension me : mw.extensions) {
			Map<ModelResourceLocation, BakedModel> models = event.getModels();

			if (me instanceof ExtensionStoneStyleBlocks eep) {
				doStyleBlocks(event, mw, eep, models);
			}
		}
	}

	private static void doStyleBlocks(ModifyBakingResult event, MaterialStone mw, ExtensionStoneStyleBlocks eep,
			Map<ModelResourceLocation, BakedModel> models) {
		if (eep.BLOCK.isNotIgnored()) {
			CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/styled_stone"),
					mw.name + "_styled_stone", "");

			for (String planks_style : StyleData.STONE_BLOCK.getTypes()) {
				// planks
				ResourceLocation loc = TagUtil.modLoc("block/cube_all");
				ResourceLocation modelLoc = ClientUtil.createStyleBlockLocation(mw.name + "_styled_stone",
						planks_style.toLowerCase());
				ResourceLocation t = Compendium
						.modLoc("block/material/stone/" + mw.name + "/tile/" + planks_style.toLowerCase());
				ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");

				if (eep.BLOCK.isNotIgnored()) {
					BakedModel bm = CompendiumClient.basicModelAllTexture(event, t, loc, m, BlockModelRotation.X0_Y0,
							"all");
					event.getModels().put(m, bm);
				}
			}
		}
	}

}
