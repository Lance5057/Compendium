package com.lance5057.compendium.index.material.extentions;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.index.material.MaterialTypeRegistry;
import com.lance5057.compendium.index.material.base._MaterialBase;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public abstract class _MaterialExtension {
	public abstract void setup(_MaterialBase base);

	public abstract void tab(_MaterialBase base, Output output);

	public abstract void blockModel(_MaterialBase base, BlockStateProvider bsp);

	public abstract void itemModel(_MaterialBase base, ItemModelProvider tmp);

	public abstract void engLoc(_MaterialBase base, LanguageProvider lp);

	public abstract void recipes(_MaterialBase base, RecipeOutput consumer);

	public abstract void blockLoot(_MaterialBase base, BlockLootSubProvider blp);

	public abstract void setupItemTags(_MaterialBase base, ItemTagsProvider itp);

	public abstract void setupBlockTags(_MaterialBase base, BlockTagsProvider itp);

	public abstract void setupClient(_MaterialBase base, FMLClientSetupEvent event);

	public static class Serializer extends MaterialExtensionSerializer<_MaterialExtension> {

		public Serializer() {
			super("EXTENSION");
		}

		@Override
		public _MaterialExtension deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			String type = j.get("type").getAsString();

			return MaterialTypeRegistry.getExtensionSerializer(type).deserialize(json, typeOfT, context);
		}

		@Override
		public JsonElement serialize(_MaterialExtension src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject obj = new JsonObject();
			obj.addProperty("type", type);

			context.serialize(src);
			return obj;
		}

	}

}
