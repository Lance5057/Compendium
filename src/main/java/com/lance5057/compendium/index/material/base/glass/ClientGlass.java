package com.lance5057.compendium.index.material.base.glass;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumClient;
import com.lance5057.compendium.client.ClientUtil;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.util.TagUtil;

import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.ModelEvent.ModifyBakingResult;
import net.neoforged.neoforge.client.model.RegistryAwareItemModelShaper;

public class ClientGlass {

	public static void doItems(RegistryAwareItemModelShaper shaper, MaterialGlass mm) {
		// TODO Auto-generated method stub

	}

	public static void doGlass(ModifyBakingResult event, MaterialGlass mb) {
//		if (mb instanceof MaterialGlass mg) {
		ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(mb.namespace, "block/" + mb.name);

		for (String b : StyleData.WINDOW_GLASS.getTypes()) {
			ResourceLocation loc = Compendium.modLoc("extra/window/window_glass");
			ResourceLocation modelLoc = ClientUtil.createMaterialStyleLayerBlockLocation("window", "glass", mb.name,
					b.toLowerCase());

//				if (mb.name.equalsIgnoreCase("clear")) {
//					ResourceLocation texture = TagUtil.mcLoc("block/glass");
//					if (!b.equals("basic")) {
//						texture = TagUtil.modLoc("block/material/glass/" + mb.name + "/" + b);
//					}
//
//					event.getModels().put(new ModelResourceLocation(modelLoc, ""), basicModelAllTexture(event,
//							texture, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, "all"));
//
//					ResourceLocation modelLoc_inv = ClientUtil.createMaterialStyleLayerBlockLocation("window", "glass",
//							mb.name, b.toLowerCase(), "_inventory");
//					ResourceLocation loc_inv = Compendium.modLoc("extra/window/glass/" + b + "_inventory");
//
////					Compendium.LOGGER.debug(modelLoc_inv.toString());
////					Compendium.LOGGER.debug(loc_inv.toString());
//
//					event.getModels().put(new ModelResourceLocation(modelLoc_inv, ""),
//							basicModelAllTexture(event, texture, loc_inv,
//									new ModelResourceLocation(modelLoc_inv, ""), BlockModelRotation.X0_Y0, "all"));
//				} else {
//					ResourceLocation texture = TagUtil.mcLoc("block/" + mb.name + "_glass");
			if (!b.equals("basic")) {
				texture = TagUtil.modLoc("block/material/glass/" + mb.name + "/" + b);
			}

			event.getModels().put(new ModelResourceLocation(modelLoc, ""), CompendiumClient.basicModelAllTexture(event,
					texture, loc, new ModelResourceLocation(modelLoc, ""), BlockModelRotation.X0_Y0, "all"));

			ResourceLocation modelLoc_inv = ClientUtil.createMaterialStyleLayerBlockLocation("window", "glass", mb.name,
					b.toLowerCase(), "_inventory");
			ResourceLocation loc_inv = Compendium.modLoc("extra/window/glass/" + b + "_inventory");

//					Compendium.LOGGER.debug(modelLoc_inv.toString());
//					Compendium.LOGGER.debug(loc_inv.toString());

			event.getModels().put(new ModelResourceLocation(modelLoc_inv, ""),
					CompendiumClient.basicModelAllTexture(event, texture, loc_inv,
							new ModelResourceLocation(modelLoc_inv, ""), BlockModelRotation.X0_Y0, "all"));
//				}
		}
	}
//	}

}
