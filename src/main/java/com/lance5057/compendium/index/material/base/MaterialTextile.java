package com.lance5057.compendium.index.material.base;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.data.IndexBlockModelProvider;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class MaterialTextile extends _MaterialBase {

	boolean loadBlock;
	boolean loadString;

	public DeferredItem<BlockItem> BLOCK_ITEM;
	public DeferredBlock<Block> BLOCK;

	public DeferredItem<Item> STRING;

	public MaterialTextile(String name, boolean block, boolean string) {
		super(name);
		this.loadBlock = block;
		this.loadString = string;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setup() {
		if (this.loadBlock) {
			BLOCK = CompendiumIndex.BLOCKS.register(this.name + "_block",
					() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL)));
			BLOCK_ITEM = CompendiumIndex.ITEMS.register(this.name + "_block_item",
					() -> new BlockItem(BLOCK.get(), new Item.Properties()));
		}
		if (this.loadString) {
			STRING = CompendiumIndex.ITEMS.register(this.name + "_string", () -> new Item(new Item.Properties()));
		}

	}

	@Override
	public void tab(Output output) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockStateModel(BlockStateProvider bsp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemModel(ItemModelProvider tmp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engLoc(LanguageProvider lp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recipes(RecipeOutput consumer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockLoot(BlockLootSubProvider blp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupItemTags(ItemTagsProvider itp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupBlockTags(BlockTagsProvider itp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupClient(FMLClientSetupEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public Ingredient getBaseItem() {
		return Ingredient.of(this.BLOCK_ITEM.get());
	}

	@Override
	public MATERIAL_TYPES getType() {
		return MATERIAL_TYPES.TEXTILE;
	}

	@Override
	public void blockModel(IndexBlockModelProvider ibmp) {
		this.extensions.forEach(i -> i.blockModel(this, ibmp));
	}
	
	public static class Serializer extends MaterialTypeSerializer<MaterialTextile> {
		public Serializer() {
			super("TEXTILE");
		}

		@Override
		public MaterialTextile deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			String name = j.get("name").getAsString();

			boolean string = j.get("loadString").getAsBoolean();
			boolean block = j.get("loadBlock").getAsBoolean();

			JsonArray extensionsArray = j.getAsJsonArray("extensions");

			MaterialTextile m = new MaterialTextile(name, string, block);

			if (extensionsArray != null)
				for (JsonElement extensionElement : extensionsArray) {
					m.addExtension(context.deserialize(extensionElement, _MaterialExtension.class));
				}

			return m;
		}

		@Override
		public JsonElement serialize(MaterialTextile src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("name", src.name);
			j.addProperty("type", type);
			j.addProperty("loadString", src.loadString);
			j.addProperty("loadBlock", src.loadBlock);

			JsonArray ext = new JsonArray();

			for (_MaterialExtension e : src.extensions)
				ext.add(context.serialize(e));

			j.add("extensions", ext);

			return j;
		}

	}


}
