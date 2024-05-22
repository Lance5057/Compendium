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

public class MaterialMetal extends _MaterialBase {
	public boolean loadIngot;
	public boolean loadStorageBlock;
	public boolean loadNugget;

	public DeferredItem<Item> INGOT;
	public DeferredItem<Item> NUGGET;
	public DeferredItem<BlockItem> BLOCK_ITEM;
	public DeferredBlock<Block> BLOCK;

	public MaterialMetal(String name) {
		this(name, true, true, true);
	}

	public MaterialMetal(String name, boolean ingot, boolean block, boolean nugget) {
		super(name);
		loadIngot = ingot;
		loadStorageBlock = block;
		loadNugget = nugget;
	}

	@Override
	public void setup() {
		if (this.loadIngot)
			INGOT = CompendiumIndex.ITEMS.register(this.name + "_ingot", () -> new Item(new Item.Properties()));
		if (this.loadNugget)
			NUGGET = CompendiumIndex.ITEMS.register(this.name + "_nugget", () -> new Item(new Item.Properties()));
		if (this.loadStorageBlock) {
			BLOCK = CompendiumIndex.BLOCKS.register(this.name + "_block",
					() -> new Block(Block.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
			BLOCK_ITEM = CompendiumIndex.ITEMS.register(this.name + "_block_item",
					() -> new BlockItem(BLOCK.get(), new Item.Properties()));
		}
	}

	@Override
	public void blockModel(BlockStateProvider bsp) {
		if (this.loadStorageBlock)
			bsp.simpleBlock(this.BLOCK.get());
	}

	@Override
	public void itemModel(ItemModelProvider tmp) {
		if (this.loadNugget)
			tmp.basicItem(this.NUGGET.get());
		if (this.loadIngot)
			tmp.basicItem(this.INGOT.get());
		if (this.loadStorageBlock)
			ItemModels.forBlockItem(tmp, BLOCK_ITEM, name);
	}

	@Override
	public void engLoc(LanguageProvider lp) {
		String locName = this.name.substring(0, 1).toUpperCase() + this.name.substring(1);
		if (this.loadNugget)
			lp.add(this.NUGGET.get(), locName + " Nugget");
		if (this.loadIngot)
			lp.add(this.INGOT.get(), locName + " Ingot");
		if (this.loadStorageBlock)
			lp.add(this.BLOCK_ITEM.get(), locName + " Block");

	}

	@Override
	public void recipes(RecipeOutput consumer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockLoot(BlockLootSubProvider blp) {

	}

	@Override
	public void tab(Output output) {
		if (this.loadStorageBlock)
			output.accept(BLOCK_ITEM);
		if (this.loadIngot)
			output.accept(INGOT);
		if (this.loadNugget)
			output.accept(NUGGET);
	}

	public static class Serializer extends MaterialTypeSerializer<MaterialMetal> {
		public Serializer() {
			super("METAL");
		}

		@Override
		public MaterialMetal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			String name = j.get("name").getAsString();
			
			boolean ingot = j.get("loadIngot").getAsBoolean();
			boolean block = j.get("loadStorageBlock").getAsBoolean();
			boolean nugget = j.get("loadNugget").getAsBoolean();

			return new MaterialMetal(name, ingot, block, nugget);
		}

		@Override
		public JsonElement serialize(MaterialMetal src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("name", src.name);
			j.addProperty("type", type);
			j.addProperty("loadIngot", src.loadIngot);
			j.addProperty("loadStorageBlock", src.loadStorageBlock);
			j.addProperty("loadNugget", src.loadNugget);

			src.extensions.forEach(x -> x.serialize(j));
			
			return j;
		}

	}
}
