package com.lance5057.compendium.index.material.base.gem;

import java.util.Map;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumClient;
import com.lance5057.compendium.client.ClientUtil;
import com.lance5057.compendium.index.material.extensions.ExtensionAdvancedTools;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.material.extensions.gem.ExtensionGemStyleBlocks;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.index.util.CompendiumItemHandler;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.util.TagUtil;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.ModelEvent.ModifyBakingResult;
import net.neoforged.neoforge.client.model.RegistryAwareItemModelShaper;

public class ClientGem {

	public static void doItems(RegistryAwareItemModelShaper shaper, MaterialGem mm) {
		for (CompendiumItemHandler i : mm.ITEMS) {
			if (i.shouldGenerate())
				shaper.register(i.ITEM.asItem(), new ModelResourceLocation(ClientUtil.createItemLocation(i.name), ""));
		}

		for (CompendiumBlockHandler i : mm.BLOCKS) {
			if (i.shouldGenerate())
				shaper.register(i.BLOCK_ITEM.asItem(), ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
		}

		if (mm.BLOCK.shouldGenerate())
			shaper.register(mm.BLOCK.BLOCK_ITEM.asItem(),
					new ModelResourceLocation(TagUtil.modLoc(mm.name + "_block"), ""));

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

	private static void doAdvancedTools(ModifyBakingResult event, MaterialGem mb, ExtensionAdvancedTools eep,
			Map<ModelResourceLocation, BakedModel> models) {
//		if (eep.BOW.shouldGenerate()) {
//			ResourceLocation loc = ClientUtil.createItemLocation(eep.BOW.name);
//			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");
//
//			event.getModels().put(mloc,
//					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/bow"), mloc,
//							BlockModelRotation.X0_Y0,
//							Pair.of("layer1", TagUtil.modLoc("item/material/gem/" + mb.name + "/bow"))));
//		}

		if (eep.ZWEIHANDER.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(eep.ZWEIHANDER.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc,
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/zweihander"), mloc,
							BlockModelRotation.X0_Y0,
							Pair.of("layer1", TagUtil.modLoc("item/material/gem/" + mb.name + "/zweihander"))));
		}

		if (eep.SHEARS.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(eep.SHEARS.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc,
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/shears"), mloc,
							BlockModelRotation.X0_Y0,
							Pair.of("layer1", TagUtil.modLoc("item/material/gem/" + mb.name + "/shears"))));
		}

		if (eep.SAW.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(eep.SAW.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc,
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/saw"), mloc,
							BlockModelRotation.X0_Y0,
							Pair.of("layer1", TagUtil.modLoc("item/material/gem/" + mb.name + "/saw"))));
		}

		if (eep.HAMMER.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(eep.HAMMER.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc,
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/hammer"), mloc,
							BlockModelRotation.X0_Y0,
							Pair.of("layer1", TagUtil.modLoc("item/material/gem/" + mb.name + "/hammer"))));
		}

		if (eep.PRYBAR.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(eep.PRYBAR.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc,
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/prybar"), mloc,
							BlockModelRotation.X0_Y0,
							Pair.of("layer1", TagUtil.modLoc("item/material/gem/" + mb.name + "/prybar"))));
		}
	}

	public static void doGem(ModifyBakingResult event, MaterialGem mw) {
		ClientGem.doStyleGem(event, mw);
		ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/material/gem/" + mw.name + "/block");
		if (mw.specialLocations != null) {
			if (mw.specialLocations.textures != null)
				if (mw.specialLocations.textures.blockLocation != null)
					texture = mw.specialLocations.textures.blockLocation;
		}

		if (mw.GEM.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(mw.GEM.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc,
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/generated_item"), mloc,
							BlockModelRotation.X0_Y0,
							Pair.of("layer0", TagUtil.modLoc("item/material/gem/" + mw.name + "/gem"))));
		}

		if (mw.SHARD.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(mw.SHARD.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc,
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/generated_item"), mloc,
							BlockModelRotation.X0_Y0,
							Pair.of("layer0", TagUtil.modLoc("item/material/gem/" + mw.name + "/shard"))));
		}

		if (mw.BLOCK.shouldGenerate()) {
			ResourceLocation loc = TagUtil.modLoc("block/cube_all");
			ResourceLocation modelLoc = TagUtil.modLoc(mw.name + "_block");
			ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");

			BakedModel bm = CompendiumClient.basicModelAllTexture(event, texture, loc, m, BlockModelRotation.X0_Y0,
					"all");
			event.getModels().put(m, bm);
		}
	}

	public static void doStyleGem(ModifyBakingResult event, MaterialGem mb) {
		for (_MaterialExtension me : mb.extensions) {
			Map<ModelResourceLocation, BakedModel> models = event.getModels();
			if (me instanceof ExtensionAdvancedTools eep) {
				doAdvancedTools(event, mb, eep, models);
			} else if (me instanceof ExtensionGemStyleBlocks eep) {
				doStyleBlocks(event, mb, eep, models);
			}
		}
	}

	private static void doStyleBlocks(ModifyBakingResult event, MaterialGem mb, ExtensionGemStyleBlocks eep,
			Map<ModelResourceLocation, BakedModel> models) {
		if (eep.BLOCK.isNotIgnored()) {
			CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/styled_gem"),
					mb.name + "_styled_gem", "");

			for (String planks_style : StyleData.GEM_BLOCK.getTypes()) {
				// planks
				ResourceLocation loc = TagUtil.modLoc("block/cube_all");
				ResourceLocation modelLoc = ClientUtil.createStyleBlockLocation(mb.name + "_styled_gem",
						planks_style.toLowerCase());
				ResourceLocation t = Compendium
						.modLoc("block/material/gem/" + mb.name + "/tile/" + planks_style.toLowerCase());
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
