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
import com.lance5057.compendium.index.util.DataUtil;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class MaterialGlass extends _MaterialBase {

	public boolean loadPane;
	public boolean loadBlock;

	public DeferredItem<BlockItem> PANE_ITEM;
	public DeferredBlock<IronBarsBlock> PANE;
	public DeferredItem<BlockItem> BLOCK_ITEM;
	public DeferredBlock<TransparentBlock> BLOCK;

	private TagKey<Item> paneItemTag;
	private TagKey<Block> paneTag;
	private TagKey<Item> blockItemTag;
	private TagKey<Block> blockTag;

	public MaterialGlass(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public MaterialGlass(String name, boolean pane, boolean block) {
		super(name);
		loadPane = pane;
		loadBlock = block;

		blockItemTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "glass/pane/" + name));
		blockTag = BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", "glass/pane/" + name));
		blockItemTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "glass/" + name));
		blockTag = BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", "glass/" + name));

	}

	@Override
	public void setup() {
		if (this.loadBlock) {
			BLOCK = CompendiumIndex.BLOCKS.register(this.name + "_block",
					() -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)));
			BLOCK_ITEM = CompendiumIndex.ITEMS.register(this.name + "_block_item",
					() -> new BlockItem(BLOCK.get(), new Item.Properties()));
		}
		if (this.loadPane) {
			PANE = CompendiumIndex.BLOCKS.register(this.name + "_pane",
					() -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS_PANE)));
			PANE_ITEM = CompendiumIndex.ITEMS.register(this.name + "_pane_item",
					() -> new BlockItem(PANE.get(), new Item.Properties()));
		}
	}

	@Override
	public void tab(Output output) {

	}

	@Override
	public void blockStateModel(BlockStateProvider bsp) {
		if (this.loadBlock)
			DataUtil.basicMaterialBlock(bsp, this.BLOCK.get(), name, "", "transparent", this.getType());
		if (this.loadPane)
			bsp.paneBlock(this.PANE.get(), DataUtil.standardResource(name, "block"),
					DataUtil.standardResource(name, "pane_top"));

		this.extensions.forEach(i -> i.blockStateModel(this, bsp));
	}

	@Override
	public void itemModel(ItemModelProvider tmp) {
		if (this.loadBlock)
			DataUtil.basicMaterialBlockItem(tmp, BLOCK_ITEM, name, this.getType());
	}

	@Override
	public void engLoc(LanguageProvider lp) {
		String locName = this.name.substring(0, 1).toUpperCase() + this.name.substring(1);

		if (this.loadBlock)
			lp.add(this.BLOCK_ITEM.get(), locName + " Block");
		if (this.loadPane)
			lp.add(this.PANE_ITEM.get(), locName + " Pane Block");

		this.extensions.forEach(i -> i.engLoc(this, lp));
	}

	@Override
	public void recipes(RecipeOutput consumer) {

		this.extensions.forEach(i -> i.recipes(this, consumer));
	}

	@Override
	public void blockLoot(BlockLootSubProvider blp) {
		if (this.loadBlock)
			blp.dropSelf(this.BLOCK.get());
		if (this.loadPane)
			blp.dropSelf(this.PANE.get());

		this.extensions.forEach(i -> i.blockLoot(this, blp));
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
		this.extensions.forEach(i -> i.setupClient(this, event));
	}

	@Override
	public Ingredient getBaseItem() {
		return Ingredient.of(this.BLOCK_ITEM.get());
	}

	@Override
	public MATERIAL_TYPES getType() {
		return MATERIAL_TYPES.GLASS;
	}

	public static class Serializer extends MaterialTypeSerializer<MaterialGlass> {
		public Serializer() {
			super("GLASS");
		}

		@Override
		public MaterialGlass deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			String name = j.get("name").getAsString();

			boolean pane = j.get("loadPane").getAsBoolean();
			boolean block = j.get("loadBlock").getAsBoolean();

			JsonArray extensionsArray = j.getAsJsonArray("extensions");

			MaterialGlass m = new MaterialGlass(name, pane, block);

			if (extensionsArray != null)
				for (JsonElement extensionElement : extensionsArray) {
					m.addExtension(context.deserialize(extensionElement, _MaterialExtension.class));
				}

			return m;
		}

		@Override
		public JsonElement serialize(MaterialGlass src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("name", src.name);
			j.addProperty("type", type);
			j.addProperty("loadPane", src.loadPane);
			j.addProperty("loadBlock", src.loadBlock);

			JsonArray ext = new JsonArray();

			for (_MaterialExtension e : src.extensions)
				ext.add(context.serialize(e));

			j.add("extensions", ext);

			return j;
		}

	}

	@Override
	public String getName() {
		return name;
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
