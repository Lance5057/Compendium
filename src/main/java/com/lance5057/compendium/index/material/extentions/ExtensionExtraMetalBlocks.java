package com.lance5057.compendium.index.material.extentions;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.util.BasicBlockItem;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ExtensionExtraMetalBlocks extends _MaterialExtension {

	public BasicBlockItem TILE = new BasicBlockItem("full_tile");
	public BasicBlockItem HALF_TILE = new BasicBlockItem("half_tile");
	public BasicBlockItem VERTICAL_HALF_TILE = new BasicBlockItem("vertical_half_tile");
	public BasicBlockItem QUARTER_TILE = new BasicBlockItem("quarter_tile");
	public BasicBlockItem OFFSET_HALF_TILE = new BasicBlockItem("offset_half_tile");
	public BasicBlockItem OFFSET_QUARTER_TILE = new BasicBlockItem("offset_quarter_tile");

	private TagKey<Item> blockItemTag;
	private TagKey<Block> blockTag;

	public ExtensionExtraMetalBlocks(boolean loadTile, boolean loadVerticalHalfTile, boolean loadHalfTile,
			boolean loadQuarterTile, boolean loadOffsetHalfTile, boolean loadOffsetQuarterTile) {
		TILE.setEnabled(loadTile);
		HALF_TILE.setEnabled(loadHalfTile);
		VERTICAL_HALF_TILE.setEnabled(loadVerticalHalfTile);
		QUARTER_TILE.setEnabled(loadQuarterTile);
		OFFSET_HALF_TILE.setEnabled(loadHalfTile);
		OFFSET_QUARTER_TILE.setEnabled(loadQuarterTile);
	}

	@Override
	public void setup(_MaterialBase base) {
		TILE.setup(base);
		HALF_TILE.setup(base);
		VERTICAL_HALF_TILE.setup(base);
		QUARTER_TILE.setup(base);
		OFFSET_HALF_TILE.setup(base);
		OFFSET_QUARTER_TILE.setup(base);
	}

	@Override
	public void tab(_MaterialBase base, Output output) {
		TILE.tab(base, output);
		HALF_TILE.tab(base, output);
		VERTICAL_HALF_TILE.tab(base, output);
		QUARTER_TILE.tab(base, output);
		OFFSET_HALF_TILE.tab(base, output);
		OFFSET_QUARTER_TILE.tab(base, output);
	}

	@Override
	public void blockModel(_MaterialBase base, BlockStateProvider bsp) {
		TILE.blockModel(base, bsp);
		HALF_TILE.blockModel(base, bsp);
		VERTICAL_HALF_TILE.blockModel(base, bsp);
		QUARTER_TILE.blockModel(base, bsp);
		OFFSET_HALF_TILE.blockModel(base, bsp);
		OFFSET_QUARTER_TILE.blockModel(base, bsp);
	}

	@Override
	public void itemModel(_MaterialBase base, ItemModelProvider tmp) {
		TILE.itemModel(base, tmp);
		HALF_TILE.itemModel(base, tmp);
		VERTICAL_HALF_TILE.itemModel(base, tmp);
		QUARTER_TILE.itemModel(base, tmp);
		OFFSET_HALF_TILE.itemModel(base, tmp);
		OFFSET_QUARTER_TILE.itemModel(base, tmp);
	}

	@Override
	public void engLoc(_MaterialBase base, LanguageProvider lp) {
		TILE.engLoc(base, lp);
		HALF_TILE.engLoc(base, lp);
		VERTICAL_HALF_TILE.engLoc(base, lp);
		QUARTER_TILE.engLoc(base, lp);
		OFFSET_HALF_TILE.engLoc(base, lp);
		OFFSET_QUARTER_TILE.engLoc(base, lp);
	}

	@Override
	public void recipes(_MaterialBase base, RecipeOutput consumer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockLoot(_MaterialBase base, BlockLootSubProvider blp) {

	}

	@Override
	public void setupClient(_MaterialBase base, FMLClientSetupEvent event) {
		// TODO Auto-generated method stub

	}

	public static class Serializer extends MaterialExtensionSerializer<ExtensionExtraMetalBlocks> {

		public Serializer() {
			super("EXTRAMETALBLOCKS");
		}

		@Override
		public JsonElement serialize(ExtensionExtraMetalBlocks src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("type", type);
			j.addProperty("loadTile", src.TILE.enabled());
			j.addProperty("loadHalfTile", src.HALF_TILE.enabled());
			j.addProperty("loadVerticalHalfTile", src.VERTICAL_HALF_TILE.enabled());
			j.addProperty("loadQuarterTile", src.QUARTER_TILE.enabled());
			j.addProperty("loadOffsetHalfTile", src.OFFSET_HALF_TILE.enabled());
			j.addProperty("loadOffsetQuarterTile", src.OFFSET_QUARTER_TILE.enabled());

			return j;
		}

		@Override
		public ExtensionExtraMetalBlocks deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			boolean loadTile = j.get("loadTile").getAsBoolean();
			boolean loadHalfTile = j.get("loadHalfTile").getAsBoolean();
			boolean loadVerticalHalfTile = j.get("loadVerticalHalfTile").getAsBoolean();
			boolean loadQuarterTile = j.get("loadQuarterTile").getAsBoolean();
			boolean loadOffsetHalfTile = j.get("loadOffsetHalfTile").getAsBoolean();
			boolean loadOffsetQuarterTile = j.get("loadOffsetQuarterTile").getAsBoolean();

			return new ExtensionExtraMetalBlocks(loadTile, loadHalfTile, loadQuarterTile, loadOffsetHalfTile,
					loadOffsetQuarterTile, loadVerticalHalfTile);
		}

	}
}
