package com.lance5057.compendium.index.material.extensions.metal;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.blocks.SimpleStyleBlock;
import com.lance5057.compendium.components.block.IndexEntryComponent;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.data.loottables.BlockLootTables;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extensions.MaterialExtensionSerializer;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.style.StyleData;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ExtensionMetalStyleBlocks extends _MaterialExtension {

	private static final long serialVersionUID = 9038154957807121881L;

	public CompendiumBlockHandler BLOCK;

	public ExtensionMetalStyleBlocks() {
		this.BLOCKS.add(BLOCK = new CompendiumBlockHandler());
	}

	@Override
	public void setup(_MaterialBase base) {
		BLOCK.setName(base.name + "_styled_metal");
		BLOCK.setup(base,
				() -> new SimpleStyleBlock(Block.Properties.ofFullCopy(Blocks.IRON_BLOCK),
						Compendium.modLoc(base.name + "_styled_metal"), base.getType(), base.name, List
								.of("metal_block"),
						StyleData.METAL_BLOCK),
				() -> new BlockItem(BLOCK.BLOCK.get(), new Item.Properties()
						.component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))
						.component(CompendiumComponents.INDEX, new IndexEntryComponent(base.getType(), base.name))));
//		BLOCK.setupItemTag(CompendiumTags.);
//		BLOCK.setupItemTag(TagUtil.neoTag("BLOCK/" + base.name));
		BLOCK.setupBlockTag(BlockTags.MINEABLE_WITH_PICKAXE);
		BLOCK.setupBlockTag(CompendiumTags.CREATE_SAFE_NBT);
		BLOCK.setAsValidStyleBlock();
		BLOCK.setAsValidStyleItem();
	}

	@Override
	public void tab(_MaterialBase base, Output out) {
		BLOCK.tab(base, out);
	}

	@Override
	public void engLoc(_MaterialBase base, LanguageProvider lp) {
		StringBuilder material_name = new StringBuilder();
		for (String word : base.name.split("_")) {
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			material_name.append(word).append(" ");
		}
		if (BLOCK.shouldGenerate()) {
			lp.add(this.BLOCK.BLOCK_ITEM.get(), material_name + "Style Block");
		}
	}

	@Override
	public void recipes(_MaterialBase base, RecipeOutput consumer) {

	}

	@Override
	public void blockLoot(_MaterialBase base, BlockLootSubProvider blp) {
		if (!this.BLOCK.isIgnored()) {
			blp.add(BLOCK.BLOCK.get(), BlockLootTables.createStyleItemDrop(BLOCK.BLOCK.get()));
		}
	}

	public static class Serializer extends MaterialExtensionSerializer<ExtensionMetalStyleBlocks> {

		public Serializer() {
			super("EXTRAMETALBLOCKS");
		}

		@Override
		public JsonElement serialize(ExtensionMetalStyleBlocks src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("type", type);

			j.add("block", src.BLOCK.serialize());

			return j;
		}

		@Override
		public ExtensionMetalStyleBlocks deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			ExtensionMetalStyleBlocks emsb = new ExtensionMetalStyleBlocks();
			if (j.has("block"))
				emsb.BLOCK.deserialize(j.get("block").getAsJsonObject());

			return emsb;
		}

	}

	@Override
	public void otherLoot(_MaterialBase base, LootTableSubProvider lsp) {
		// TODO Auto-generated method stub

	}

}
