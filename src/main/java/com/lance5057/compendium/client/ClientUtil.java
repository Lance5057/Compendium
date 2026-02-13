package com.lance5057.compendium.client;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;

import net.minecraft.resources.ResourceLocation;

public class ClientUtil {
	public static ResourceLocation createMaterialStyleLocation(MATERIAL_TYPES type, String material, String style) {
		return Compendium.modLoc("block/material/" + type.toString().toLowerCase() + "/" + material + "/" + style);
	}
}
