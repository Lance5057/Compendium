package com.lance5057.compendium.index.material.base;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.material.extentions._MaterialExtension;
import com.lance5057.compendium.index.util.DataUtil;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
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

	private TagKey<Item> ingotTag;
	private TagKey<Item> nuggetTag;
	private TagKey<Item> blockItemTag;
	private TagKey<Block> blockTag;

	public MaterialMetal(String name) {
		this(name, true, true, true);
	}

	public MaterialMetal(String name, boolean ingot, boolean block, boolean nugget) {
		super(name);
		loadIngot = ingot;
		loadStorageBlock = block;
		loadNugget = nugget;

		ingotTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "ingots/" + name));
		nuggetTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "nuggets/" + name));
		blockItemTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/" + name));
		blockTag = BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", "storage_blocks/" + name));

	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setup() {

		if (premadeTier != null && !premadeTier.isEmpty())
			tier = Tiers.valueOf(premadeTier);
		else {
			useBlockTag = BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", useTag));
			tier = new SimpleTier(useBlockTag, uses, speed, damage, enchantmentValue, () -> Ingredient.of(ingotTag));
		}

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

		this.extensions.forEach(i -> i.setup(this));
	}

	@Override
	public void blockModel(BlockStateProvider bsp) {
		if (this.loadStorageBlock)
			DataUtil.basicMaterialBlock(bsp, this.BLOCK.get(), name);

		this.extensions.forEach(i -> i.blockModel(this, bsp));
	}

	@Override
	public void itemModel(ItemModelProvider tmp) {
		if (this.loadNugget)
			DataUtil.basicMaterialItem(tmp, this.NUGGET.get(), name);
		if (this.loadIngot)
			DataUtil.basicMaterialItem(tmp, this.INGOT.get(), name);
		if (this.loadStorageBlock)
			DataUtil.basicMaterialBlockItem(tmp, BLOCK_ITEM, name);

		this.extensions.forEach(i -> i.itemModel(this, tmp));
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

		this.extensions.forEach(i -> i.engLoc(this, lp));
	}

	@Override
	public void recipes(RecipeOutput consumer) {
		// TODO Auto-generated method stub

		this.extensions.forEach(i -> i.recipes(this, consumer));
	}

	@Override
	public void blockLoot(BlockLootSubProvider blp) {
		blp.dropSelf(this.BLOCK.get());
		this.extensions.forEach(i -> i.blockLoot(this, blp));
	}

	@Override
	public void tab(Output output) {
		if (this.loadStorageBlock)
			output.accept(BLOCK_ITEM);
		if (this.loadIngot)
			output.accept(INGOT);
		if (this.loadNugget)
			output.accept(NUGGET);

		this.extensions.forEach(i -> i.tab(this, output));
	}

	@Override
	public void setupItemTags(ItemTagsProvider itp) {
		this.extensions.forEach(i -> i.setupItemTags(this, itp));
	}

	@Override
	public void setupBlockTags(BlockTagsProvider itp) {
		this.extensions.forEach(i -> i.setupBlockTags(this, itp));
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

			String tier = j.get("tier").getAsString();

			int level = j.get("level").getAsInt();
			int uses = j.get("uses").getAsInt();
			float speed = j.get("speed").getAsFloat();
			float damage = j.get("damage").getAsFloat();
			int enchantmentValue = j.get("enchantmentValue").getAsInt();
			String useTag = j.get("useTag").getAsString();
			String repairTag = j.get("repairTag").getAsString();

			JsonArray extensionsArray = j.getAsJsonArray("extensions");

			MaterialMetal m = new MaterialMetal(name, ingot, block, nugget);

			if (tier != null && !tier.isEmpty())
				m.setupTier(tier);
			else
				m.setupTier(level, uses, speed, damage, enchantmentValue, useTag, repairTag);

			if (extensionsArray != null)
				for (JsonElement extensionElement : extensionsArray) {
					m.addExtension(context.deserialize(extensionElement, _MaterialExtension.class));
				}

			return m;
		}

		@Override
		public JsonElement serialize(MaterialMetal src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("name", src.name);
			j.addProperty("type", type);
			j.addProperty("loadIngot", src.loadIngot);
			j.addProperty("loadStorageBlock", src.loadStorageBlock);
			j.addProperty("loadNugget", src.loadNugget);

			j.addProperty("tier", "DIAMOND");
			j.addProperty("level", 0);
			j.addProperty("uses", 0);
			j.addProperty("speed", 0);
			j.addProperty("damage", 0);
			j.addProperty("enchantmentValue", 0);
			j.addProperty("useTag", "");
			j.addProperty("repairTag", "");

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
		return Ingredient.of(this.INGOT.get());
	}

	@Override
	public MATERIAL_TYPES getType() {
		return MATERIAL_TYPES.METAL;
	}
}
