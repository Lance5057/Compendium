package com.lance5057.compendium.index.material.base;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.data.IndexBlockModelProvider;
import com.lance5057.compendium.data.ItemModels;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class MaterialStone extends _MaterialBase {
	public boolean loadCobblestone;
	public boolean loadStone;
	public boolean loadSmooth;

	public DeferredItem<BlockItem> COBBLESTONE_ITEM;
	public DeferredBlock<Block> COBBLESTONE;

	public DeferredItem<BlockItem> STONE_ITEM;
	public DeferredBlock<Block> STONE;

	public DeferredItem<BlockItem> SMOOTH_ITEM;
	public DeferredBlock<Block> SMOOTH;

	public MaterialStone(String name, boolean cobble, boolean stone, boolean smooth) {
		super(name);

		this.loadCobblestone = cobble;
		this.loadSmooth = smooth;
		this.loadStone = stone;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setup() {
		if (this.loadCobblestone) {
			COBBLESTONE = CompendiumIndex.BLOCKS.register(this.name + "_cobblestone",
					() -> new Block(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
			COBBLESTONE_ITEM = CompendiumIndex.ITEMS.register(this.name + "_cobblestone_item",
					() -> new BlockItem(COBBLESTONE.get(), new Item.Properties()));
		}

		if (this.loadStone) {
			STONE = CompendiumIndex.BLOCKS.register(this.name + "_stone",
					() -> new Block(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
			STONE_ITEM = CompendiumIndex.ITEMS.register(this.name + "_stone_item",
					() -> new BlockItem(STONE.get(), new Item.Properties()));
		}

		if (this.loadSmooth) {
			SMOOTH = CompendiumIndex.BLOCKS.register(this.name + "_smooth_stone",
					() -> new Block(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
			SMOOTH_ITEM = CompendiumIndex.ITEMS.register(this.name + "_smooth_stone_item",
					() -> new BlockItem(SMOOTH.get(), new Item.Properties()));
		}

		this.extensions.forEach(i -> i.setup(this));
	}

	@Override
	public void tab(Output output) {
		if (this.loadCobblestone)
			output.accept(COBBLESTONE_ITEM);
		if (this.loadSmooth)
			output.accept(SMOOTH_ITEM);
		if (this.loadStone)
			output.accept(STONE_ITEM);

		this.extensions.forEach(i -> i.tab(this, output));
	}

	@Override
	public void blockStateModel(BlockStateProvider bsp) {
		if (this.loadCobblestone)
			bsp.simpleBlock(COBBLESTONE.get());
		if (this.loadSmooth)
			bsp.simpleBlock(SMOOTH.get());
		if (this.loadStone)
			bsp.simpleBlock(STONE.get());

		this.extensions.forEach(i -> i.blockStateModel(this, bsp));
	}

	@Override
	public void itemModel(ItemModelProvider tmp) {
		if (this.loadCobblestone)
			ItemModels.forBlockItem(tmp, COBBLESTONE_ITEM, name);
		if (this.loadSmooth)
			ItemModels.forBlockItem(tmp, SMOOTH_ITEM, name);
		if (this.loadStone)
			ItemModels.forBlockItem(tmp, STONE_ITEM, name);

		this.extensions.forEach(i -> i.itemModel(this, tmp));
	}

	@Override
	public void engLoc(LanguageProvider lp) {
		String locName = this.name.substring(0, 1).toUpperCase() + this.name.substring(1);
		if (this.loadCobblestone)
			lp.add(this.COBBLESTONE_ITEM.get(), locName + " Cobblestone");
		if (this.loadStone)
			lp.add(this.STONE_ITEM.get(), locName);
		if (this.loadSmooth)
			lp.add(this.SMOOTH_ITEM.get(), "Polished " + locName);

		this.extensions.forEach(i -> i.engLoc(this, lp));
	}

	@Override
	public void recipes(RecipeOutput consumer) {
		this.extensions.forEach(i -> i.recipes(this, consumer));
	}

	@Override
	public void blockLoot(BlockLootSubProvider blp) {
		this.extensions.forEach(i -> i.blockLoot(this, blp));
	}

	@Override
	public void setupItemTags(ItemTagsProvider itp) {
		this.extensions.forEach(i -> i.setupItemTags(this, itp));
	}

	@Override
	public void setupBlockTags(BlockTagsProvider itp) {
		this.extensions.forEach(i -> i.setupBlockTags(this, itp));
	}

	public static class Serializer extends MaterialTypeSerializer<MaterialStone> {

		public Serializer() {
			super("STONE");
		}

		@Override
		public MaterialStone deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			String name = j.get("name").getAsString();
			boolean cobble = j.get("loadCobblestone").getAsBoolean();
			boolean smooth = j.get("loadSmooth").getAsBoolean();
			boolean stone = j.get("loadStone").getAsBoolean();

			MaterialStone w = new MaterialStone(name, cobble, stone, smooth);

			JsonArray extensionsArray = j.getAsJsonArray("extensions");

			if (extensionsArray != null)
				for (JsonElement extensionElement : extensionsArray) {
					w.addExtension(context.deserialize(extensionElement, _MaterialExtension.class));
				}

			return w;
		}

		@Override
		public JsonElement serialize(MaterialStone src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("name", src.name);
			j.addProperty("type", type);
			j.addProperty("loadCobblestone", src.loadCobblestone);
			j.addProperty("loadSmooth", src.loadSmooth);
			j.addProperty("loadStone", src.loadStone);

			JsonArray ext = new JsonArray();

			for (_MaterialExtension e : src.extensions)
				ext.add(context.serialize(e));

			j.add("extensions", ext);

			return j;
		}

	}

	@Override
	public void setupClient(FMLClientSetupEvent event) {
		this.extensions.forEach(i -> i.setupClient(this, event));
	}

	@Override
	public Ingredient getBaseItem() {
		return Ingredient.of(this.STONE.get());
	}

	@Override
	public MATERIAL_TYPES getType() {
		return MATERIAL_TYPES.STONE;
	}

	@Override
	public void blockModel(IndexBlockModelProvider ibmp) {
		this.extensions.forEach(i -> i.blockModel(this, ibmp));
	}

	@Override
	public void otherLoot(LootTableSubProvider lsp) {
		// TODO Auto-generated method stub
		
	}

}
