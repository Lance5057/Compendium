package com.lance5057.compendium.index.material.base;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.util.DataUtil;

import net.minecraft.data.loot.BlockLootSubProvider;
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

public class MaterialGem extends _MaterialBase {

	public boolean loadGem;
	public boolean loadStorageBlock;
	public boolean loadShard;

	public DeferredItem<Item> GEM;
	public DeferredItem<Item> SHARD;
	public DeferredItem<BlockItem> BLOCK_ITEM;
	public DeferredBlock<Block> BLOCK;

	public MaterialGem(String name) {
		this(name, true, true, true);
	}

	public MaterialGem(String name, boolean gem, boolean block, boolean shard) {
		super(name);
		loadGem = gem;
		loadStorageBlock = block;
		loadShard = shard;
	}

	@Override
	public void setup() {
		if (this.loadGem)
			GEM = CompendiumIndex.ITEMS.register(this.name + "_gem", () -> new Item(new Item.Properties()));
		if (this.loadShard)
			SHARD = CompendiumIndex.ITEMS.register(this.name + "_shard", () -> new Item(new Item.Properties()));
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
		if (this.loadShard)
			DataUtil.basicMaterialItem(tmp, this.SHARD.get(), name);
		if (this.loadGem)
			DataUtil.basicMaterialItem(tmp, this.GEM.get(), name);
		if (this.loadStorageBlock)
			DataUtil.basicMaterialBlockItem(tmp, BLOCK_ITEM, name);
	}

	@Override
	public void engLoc(LanguageProvider lp) {
		String locName = this.name.substring(0, 1).toUpperCase() + this.name.substring(1);
		if (this.loadShard)
			lp.add(this.SHARD.get(), locName + " Shard");
		if (this.loadGem)
			lp.add(this.GEM.get(), locName + " Gem");
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
		if (this.loadGem)
			output.accept(GEM);
		if (this.loadShard)
			output.accept(SHARD);
	}
	
	@Override
	public void setupItemTags(ItemTagsProvider itp) {
		this.extensions.forEach(i -> i.setupItemTags(this, itp));
	}

	@Override
	public void setupBlockTags(BlockTagsProvider itp) {
		this.extensions.forEach(i -> i.setupBlockTags(this, itp));
	}

	public static class Serializer extends MaterialTypeSerializer<MaterialGem> {

		public Serializer() {
			super("GEM");
		}

		@Override
		public MaterialGem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			String name = j.get("name").getAsString();
			boolean loadGem = j.get("loadGem").getAsBoolean();
			boolean loadStorageBlock = j.get("loadStorageBlock").getAsBoolean();
			boolean loadShard = j.get("loadShard").getAsBoolean();

			return new MaterialGem(name, loadGem, loadStorageBlock, loadShard);
		}

		@Override
		public JsonElement serialize(MaterialGem src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("name", src.name);
			j.addProperty("type", type);
			j.addProperty("loadGem", src.loadGem);
			j.addProperty("loadStorageBlock", src.loadStorageBlock);
			j.addProperty("loadShard", src.loadShard);

			return j;
		}

	}

	@Override
	public void setupClient(FMLClientSetupEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ingredient getBaseItem() {
		return Ingredient.of(GEM.get());
	}

	
}
