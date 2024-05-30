package com.lance5057.compendium.index.material.extentions;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.util.DataUtil;
import com.lance5057.compendium.items.tools.PrybarItem;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredItem;

public class ExtensionAdvancedTools extends _MaterialExtension {
	boolean loadPrybar;

	public DeferredItem<Item> PRYBAR;

	public ExtensionAdvancedTools(boolean loadPrybar) {
		this.loadPrybar = loadPrybar;
	}

	@Override
	public void setup(_MaterialBase base) {
		if (this.loadPrybar)
			PRYBAR = CompendiumIndex.ITEMS.register(base.name + "_prybar",
					() -> new PrybarItem(base.tier, new Item.Properties()));
	}

	@Override
	public void tab(_MaterialBase base, Output output) {
		if (this.loadPrybar)
			output.accept(PRYBAR);
	}

	@Override
	public void blockModel(_MaterialBase base, BlockStateProvider bsp) {
		
	}

	@Override
	public void itemModel(_MaterialBase base, ItemModelProvider tmp) {
		if (this.loadPrybar)
			DataUtil.basicMaterialItem(tmp, this.PRYBAR.get(), base.name);
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
	
	public static class Serializer extends MaterialExtensionSerializer<ExtensionAdvancedTools> {

		public Serializer() {
			super("ADVANCEDTOOLS");
		}

		@Override
		public JsonElement serialize(ExtensionAdvancedTools src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("type", type);
			j.addProperty("loadPrybar", src.loadPrybar);

			return j;
		}

		@Override
		public ExtensionAdvancedTools deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			boolean sword = j.get("loadPrybar").getAsBoolean();

			return new ExtensionAdvancedTools(sword);
		}

	}

}
