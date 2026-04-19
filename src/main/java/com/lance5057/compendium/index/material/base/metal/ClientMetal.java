package com.lance5057.compendium.index.material.base.metal;

import java.util.Map;

import com.lance5057.compendium.CompendiumClient;
import com.lance5057.compendium.client.ClientUtil;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extensions.ExtensionAdvancedTools;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumItemHandler;
import com.lance5057.compendium.util.TagUtil;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.ModelEvent.ModifyBakingResult;
import net.neoforged.neoforge.client.model.RegistryAwareItemModelShaper;

public class ClientMetal {
	public static void doItems(RegistryAwareItemModelShaper shaper, _MaterialBase mb, MaterialMetal mm) {
		for (CompendiumItemHandler i : mm.ITEMS) {
			if (i.shouldGenerate())
				shaper.register(i.ITEM.asItem(), new ModelResourceLocation(ClientUtil.createItemLocation(i.name), ""));
		}

//		if (mm.NUGGET.shouldGenerate())
//			shaper.register(mm.NUGGET.ITEM.asItem(),
//					new ModelResourceLocation(ClientUtil.createItemLocation(mb.name + "_nugget_item"), ""));

		for (_MaterialExtension me : mb.extensions) {
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
