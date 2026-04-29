package com.lance5057.compendium.index.material.base.metal;

import java.util.Map;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumClient;
import com.lance5057.compendium.client.ClientUtil;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extensions.ExtensionAdvancedTools;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
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

public class ClientMetal {
	public static void doMetal(ModifyBakingResult event, _MaterialBase mb) {
		if (mb instanceof MaterialMetal mm) {

			ClientMetal.doStyleMetal(event, mm);
			ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(mb.namespace,
					"block/material/metal/" + mb.name + "/block");
			if (mm.specialLocations != null) {
				if (mm.specialLocations.textures != null)
					if (mm.specialLocations.textures.blockLocation != null)
						texture = mm.specialLocations.textures.blockLocation;
			}

//			ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(mb.namespace, "block/" + mb.name);

			if (mm.BLOCK.shouldGenerate()) {
				ResourceLocation loc = TagUtil.modLoc("block/cube_all");
				ResourceLocation modelLoc = TagUtil.modLoc(mb.name + "_block");
				ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");

				BakedModel bm = CompendiumClient.basicModelAllTexture(event, texture, loc, m, BlockModelRotation.X0_Y0,
						"all");
				event.getModels().put(m, bm);
			}

			StyleData.WINDOW_TRIM.getTypes().forEach(b -> {
				ResourceLocation loc = Compendium.modLoc("extra/window/window_frame");
				ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerBlockLocation("window", "trim", mb.name,
						b.toLowerCase());

				ResourceLocation t = Compendium
						.modLoc("block/material/metal/" + mb.name + "/windows/" + b.toLowerCase());

				event.getModels().put(new ModelResourceLocation(modelLoc, ""), CompendiumClient.basicModelAllTexture(
						event, t, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, "all"));

//				ResourceLocation modelLoc_inv = ClientUtil.createMaterialStyleLayerLocation("window", "trim", mb.name,
//						b.toLowerCase(), "_inventory");
//				ResourceLocation loc_inv = Compendium.modLoc("extra/window/trim/" + b + "_inventory");
//
////				Compendium.LOGGER.debug(modelLoc_inv.toString());
////				Compendium.LOGGER.debug(loc_inv.toString());
//
//				event.getModels().put(new ModelResourceLocation(modelLoc_inv, ""),
//						basicModelAllTexture(event, texture, loc_inv, new ModelResourceLocation(modelLoc_inv, ""),
//								BlockModelRotation.X0_Y0, "all"));
			});
		}
	}

	public static void doItems(RegistryAwareItemModelShaper shaper, MaterialMetal mm) {
		for (CompendiumItemHandler i : mm.ITEMS) {
			if (i.shouldGenerate())
				shaper.register(i.ITEM.asItem(), new ModelResourceLocation(ClientUtil.createItemLocation(i.name), ""));
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

		}
	}

	public static void doStyleMetal(ModifyBakingResult event, MaterialMetal mb) {

		if (mb.INGOT.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(mb.INGOT.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc,
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/generated_item"), mloc,
							BlockModelRotation.X0_Y0,
							Pair.of("layer0", TagUtil.modLoc("item/material/metal/" + mb.name + "/ingot"))));
		}

		if (mb.NUGGET.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(mb.NUGGET.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc,
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/generated_item"), mloc,
							BlockModelRotation.X0_Y0,
							Pair.of("layer0", TagUtil.modLoc("item/material/metal/" + mb.name + "/nugget"))));
		}

		for (_MaterialExtension me : mb.extensions) {
			Map<ModelResourceLocation, BakedModel> models = event.getModels();
			if (me instanceof ExtensionAdvancedTools eep) {
				doAdvancedTools(event, mb, eep, models);
			}
		}
	}

	private static void doAdvancedTools(ModifyBakingResult event, MaterialMetal mb, ExtensionAdvancedTools eep,
			Map<ModelResourceLocation, BakedModel> models) {
		if (eep.BOW.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(eep.BOW.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc,
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/bow"), mloc,
							BlockModelRotation.X0_Y0,
							Pair.of("layer1", TagUtil.modLoc("item/material/metal/" + mb.name + "/bow"))));
		}

		if (eep.ZWEIHANDER.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(eep.ZWEIHANDER.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc,
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/zweihander"), mloc,
							BlockModelRotation.X0_Y0,
							Pair.of("layer1", TagUtil.modLoc("item/material/metal/" + mb.name + "/zweihander"))));
		}

		if (eep.SHEARS.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(eep.SHEARS.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc,
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/shears"), mloc,
							BlockModelRotation.X0_Y0,
							Pair.of("layer1", TagUtil.modLoc("item/material/metal/" + mb.name + "/shears"))));
		}

		if (eep.SAW.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(eep.SAW.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc,
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/saw"), mloc,
							BlockModelRotation.X0_Y0,
							Pair.of("layer1", TagUtil.modLoc("item/material/metal/" + mb.name + "/saw"))));
		}

		if (eep.HAMMER.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(eep.HAMMER.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc,
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/hammer"), mloc,
							BlockModelRotation.X0_Y0,
							Pair.of("layer1", TagUtil.modLoc("item/material/metal/" + mb.name + "/hammer"))));
		}

		if (eep.PRYBAR.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(eep.PRYBAR.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc,
					CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/prybar"), mloc,
							BlockModelRotation.X0_Y0,
							Pair.of("layer1", TagUtil.modLoc("item/material/metal/" + mb.name + "/prybar"))));
		}
	}
}
