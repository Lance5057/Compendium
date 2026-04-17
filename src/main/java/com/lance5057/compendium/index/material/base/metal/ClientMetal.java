package com.lance5057.compendium.index.material.base.metal;

import com.lance5057.compendium.CompendiumClient;
import com.lance5057.compendium.client.ClientUtil;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.util.TagUtil;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.ModelEvent.ModifyBakingResult;
import net.neoforged.neoforge.client.model.RegistryAwareItemModelShaper;

public class ClientMetal {
	public static void doItems(RegistryAwareItemModelShaper shaper, _MaterialBase mb, MaterialMetal mm) {
		if (mm.INGOT.shouldGenerate())
			shaper.register(mm.INGOT.ITEM.asItem(),
					ModelResourceLocation.standalone(ClientUtil.createItemLocation(mb.name + "_ingot_item")));
		if (mm.NUGGET.shouldGenerate())
			shaper.register(mm.NUGGET.ITEM.asItem(),
					ModelResourceLocation.standalone(ClientUtil.createItemLocation(mb.name + "_nugget_item")));
	}

	public static void doStyleMetal(ModifyBakingResult event, MaterialMetal mb) {
pretty sure im doing this wrong but im too tired to figure it out atm
		if (mb.INGOT.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(mb.name + "_ingot_item");
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc,
					CompendiumClient.basicModelManyTexture(event, loc, mloc, BlockModelRotation.X0_Y0,
							Pair.of("layer0", TagUtil.mcLoc("item/material/metal/" + mb.name + "/ingot"))));
		}

		if (mb.NUGGET.shouldGenerate()) {
			ResourceLocation loc = ClientUtil.createItemLocation(mb.name + "_nugget_item");
			ModelResourceLocation mloc = new ModelResourceLocation(loc, "");

			event.getModels().put(mloc,
					CompendiumClient.basicModelManyTexture(event, loc, mloc, BlockModelRotation.X0_Y0,
							Pair.of("layer0", TagUtil.mcLoc("item/material/metal/" + mb.name + "/nugget"))));
		}
	}
}
