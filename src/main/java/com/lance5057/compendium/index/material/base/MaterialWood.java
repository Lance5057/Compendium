package com.lance5057.compendium.index.material.base;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.data.ItemModels;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.material.MaterialTypeSerializer;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class MaterialWood extends _MaterialBase {
	public boolean loadPlanks;

	public DeferredItem<BlockItem> PLANKS_ITEM;
	public DeferredBlock<Block> PLANKS;

	public MaterialWood(String name) {
		this(name, true);
	}

	public MaterialWood(String name, boolean planks) {
		super(name);

		this.loadPlanks = planks;
	}

	@Override
	public void setup() {
		if (this.loadPlanks) {
			PLANKS = CompendiumIndex.BLOCKS.register(this.name + "_planks",
					() -> new Block(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
			PLANKS_ITEM = CompendiumIndex.ITEMS.register(this.name + "_planks_item",
					() -> new BlockItem(PLANKS.get(), new Item.Properties()));
		}
	}

	@Override
	public void tab(Output output) {
		if (this.loadPlanks)
			output.accept(PLANKS_ITEM);
	}

	@Override
	public void blockModel(BlockStateProvider bsp) {
		if (this.loadPlanks)
			bsp.simpleBlock(PLANKS.get());
	}

	@Override
	public void itemModel(ItemModelProvider tmp) {
		if (this.loadPlanks)
			ItemModels.forBlockItem(tmp, PLANKS_ITEM, name);
	}

	@Override
	public void engLoc(LanguageProvider lp) {
		String locName = this.name.substring(0, 1).toUpperCase() + this.name.substring(1);
		if (this.loadPlanks)
			lp.add(this.PLANKS_ITEM.get(), locName + " Planks");
	}

	@Override
	public void recipes(RecipeOutput consumer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockLoot(BlockLootSubProvider blp) {
		// TODO Auto-generated method stub

	}

	public static class Serializer extends MaterialTypeSerializer<MaterialWood> {

		public Serializer() {
			super("WOOD");
		}

		@Override
		public MaterialWood deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			String name = j.get("name").getAsString();
			boolean plank = j.get("loadPlanks").getAsBoolean();

			return new MaterialWood(name, plank);
		}

		@Override
		public JsonElement serialize(MaterialWood src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("name", src.name);
			j.addProperty("type", type);
			j.addProperty("loadPlanks", src.loadPlanks);

			return j;
		}

	}
}
