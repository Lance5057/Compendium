package com.lance5057.compendium.index.material.extensions.client;

import java.util.Map;

import com.lance5057.compendium.CompendiumClient;
import com.lance5057.compendium.client.ClientUtil;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extensions.ExtensionVanillaTools;
import com.lance5057.compendium.util.TagUtil;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.ModelEvent.ModifyBakingResult;

public class ClientVanillaTools {
	public static void doVanillaTools(ModifyBakingResult event, _MaterialBase mb, ExtensionVanillaTools eep,
			Map<ModelResourceLocation, BakedModel> models) {
		if (eep.AXE.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(eep.AXE.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc, CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/axe"), mloc,
					BlockModelRotation.X0_Y0, Pair.of("layer1", TagUtil.modLoc(
							"item/material/" + mb.getType().toString().toLowerCase() + "/" + mb.name + "/axe"))));
		}

		if (eep.PICKAXE.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(eep.PICKAXE.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc, CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/pickaxe"),
					mloc, BlockModelRotation.X0_Y0, Pair.of("layer1", TagUtil.modLoc(
							"item/material/" + mb.getType().toString().toLowerCase() + "/" + mb.name + "/pickaxe"))));
		}

		if (eep.SHOVEL.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(eep.SHOVEL.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc, CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/shovel"),
					mloc, BlockModelRotation.X0_Y0, Pair.of("layer1", TagUtil.modLoc(
							"item/material/" + mb.getType().toString().toLowerCase() + "/" + mb.name + "/shovel"))));
		}

		if (eep.HOE.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(eep.HOE.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc, CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/hoe"), mloc,
					BlockModelRotation.X0_Y0, Pair.of("layer1", TagUtil.modLoc(
							"item/material/" + mb.getType().toString().toLowerCase() + "/" + mb.name + "/hoe"))));
		}

		if (eep.SWORD.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(eep.SWORD.name);
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc, CompendiumClient.basicModelManyTexture(event, TagUtil.modLoc("extra/sword"),
					mloc, BlockModelRotation.X0_Y0, Pair.of("layer1", TagUtil.modLoc(
							"item/material/" + mb.getType().toString().toLowerCase() + "/" + mb.name + "/sword"))));
		}
	}
}
