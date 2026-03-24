package com.lance5057.compendium.util;

import com.lance5057.compendium.Compendium;

import net.minecraft.resources.ResourceLocation;

public class TagUtil {
	public static ResourceLocation neoTag(String loc) {
		return ResourceLocation.fromNamespaceAndPath("c", loc);
	}

	public static ResourceLocation modLoc(String loc) {
		return ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, loc);
	}

	public static ResourceLocation mcLoc(String loc) {
		return ResourceLocation.withDefaultNamespace(loc);
	}
}
