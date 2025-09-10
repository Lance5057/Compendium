package com.lance5057.compendium.index.material.extensions.wood;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.data.IndexBlockModelProvider;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extensions.MaterialExtensionSerializer;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.DataUtil;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredItem;

public class ExtensionExtraPlanks extends _MaterialExtension {
	public boolean loadPlank;
	public DeferredItem<Item> PLANK;
	private TagKey<Item> plankTag;

	public ExtensionExtraPlanks(boolean ingot) {
		loadPlank = ingot;

	}

	@Override
	public void setup(_MaterialBase base) {
		if (this.loadPlank) {
			PLANK = CompendiumIndex.ITEMS.register(base.name + "_plank", () -> new Item(new Item.Properties()));
		}
	}

	@Override
	public void tab(_MaterialBase base, Output output) {
		if (this.loadPlank)
			output.accept(PLANK);
	}

	@Override
	public void blockStateModel(_MaterialBase base, BlockStateProvider bsp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockModel(_MaterialBase base, IndexBlockModelProvider ibmp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemModel(_MaterialBase base, ItemModelProvider tmp) {
		DataUtil.basicMaterial3DItem(tmp, PLANK.get(), base, Compendium.modLoc("item/plank"), base.getType(),
				tmp.mcLoc("block/" + base.name.toLowerCase() + "_planks"));
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

	@Override
	public void setupItemTags(_MaterialBase base, ItemTagsProvider itp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupBlockTags(_MaterialBase base, BlockTagsProvider itp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupClient(_MaterialBase base, FMLClientSetupEvent event) {
		// TODO Auto-generated method stub

	}

	public static class Serializer extends MaterialExtensionSerializer<ExtensionExtraPlanks> {

		public Serializer() {
			super("EXTRAPLANKS");
		}

		@Override
		public JsonElement serialize(ExtensionExtraPlanks src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();
			
			j.addProperty("type", type);
			j.addProperty("loadPlank", src.loadPlank);

			return j;
		}

		@Override
		public ExtensionExtraPlanks deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			boolean prybar = j.get("loadPlank").getAsBoolean();

			return new ExtensionExtraPlanks(prybar);
		}

	}

}
