package com.lance5057.compendium.index.material.extentions;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.index.material.base._MaterialBase;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ExtensionVanillaTools extends _MaterialExtension {
	boolean loadSword;
	boolean loadAxe;
	boolean loadShovel;
	boolean loadHoe;
	boolean loadPickaxe;

	public ExtensionVanillaTools(boolean sword, boolean axe, boolean shovel, boolean hoe, boolean pickaxe) {
		this.loadSword = sword;
		this.loadAxe = axe;
		this.loadHoe = hoe;
		this.loadPickaxe = pickaxe;
		this.loadShovel = shovel;
	}

	@Override
	public void setup(_MaterialBase base) {
	}

	@Override
	public void tab(_MaterialBase base, Output output) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockModel(_MaterialBase base, BlockStateProvider bsp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemModel(_MaterialBase base, ItemModelProvider tmp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engLoc(_MaterialBase base, LanguageProvider lp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recipes(_MaterialBase base, RecipeOutput consumer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockLoot(_MaterialBase base, BlockLootSubProvider blp) {
		// TODO Auto-generated method stub

	}

	public static class Serializer extends MaterialExtensionSerializer<ExtensionVanillaTools> {

		public Serializer() {
			super("VANILLATOOLS");
		}

		@Override
		public JsonElement serialize(ExtensionVanillaTools src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("type", type);
			j.addProperty("loadSword", src.loadSword);
			j.addProperty("loadAxe", src.loadAxe);
			j.addProperty("loadShovel", src.loadShovel);
			j.addProperty("loadHoe", src.loadHoe);
			j.addProperty("loadPickaxe", src.loadPickaxe);

			return j;
		}

		@Override
		public ExtensionVanillaTools deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			boolean sword = j.get("loadSword").getAsBoolean();
			boolean axe = j.get("loadAxe").getAsBoolean();
			boolean shovel = j.get("loadShovel").getAsBoolean();
			boolean hoe = j.get("loadHoe").getAsBoolean();
			boolean pickaxe = j.get("loadPickaxe").getAsBoolean();

			return new ExtensionVanillaTools(sword, axe, shovel, hoe, pickaxe);
		}

	}

}
