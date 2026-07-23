package com.lance5057.compendium.client;

import com.lance5057.compendium.Compendium;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.resources.ResourceLocation;

public class ClientUtil {
	public static ResourceLocation createMaterialStyleBlockLocation(String block, String material, String style) {
		return Compendium.modLoc("block/" + material + "/" + block + "/" + style);
	}

	public static ResourceLocation createMaterialStyleLayerBlockLocation(String block, String layer, String material,
			String style) {
		return Compendium.modLoc("block/" + material + "/" + block + "/" + layer + "/" + style);
	}

	public static ResourceLocation createMaterialStyleLayerBlockLocation(String block, String layer, String material,
			String style, String extra) {
		return Compendium.modLoc("block/" + material + "/" + block + "/" + layer + "/" + style + extra);
	}

	public static ResourceLocation createStyleBlockLocation(String block, String style) {
		return Compendium.modLoc("block/" + block + "/" + style);
	}

	public static ResourceLocation createBlockLocation(String block) {
		return Compendium.modLoc("block/" + block);
	}

	public static ResourceLocation createItemLocation(String item) {
		return Compendium.modLoc("item/" + item);
	}

}
