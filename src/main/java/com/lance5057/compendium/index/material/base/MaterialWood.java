package com.lance5057.compendium.index.material.base;

import java.lang.reflect.Type;
import java.util.Optional;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.CompendiumItems;
import com.lance5057.compendium.data.IndexBlockModelProvider;
import com.lance5057.compendium.data.ItemModels;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.workstations.scrappingtable.ScrappingUtils;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class MaterialWood extends _MaterialBase {

	public CompendiumBlockHandler PLANKS = new CompendiumBlockHandler("planks");
	public CompendiumBlockHandler LOG = new CompendiumBlockHandler("log");
	public CompendiumBlockHandler STRIPPED_LOG = new CompendiumBlockHandler("stripped_log");
	public CompendiumBlockHandler WOOD = new CompendiumBlockHandler("wood");
	public CompendiumBlockHandler STRIPPED_WOOD = new CompendiumBlockHandler("stripped_wood");

	public MaterialWood(String name, String tagNamespace) {
		this(name, tagNamespace, true, true, true, true, true);
	}

	public MaterialWood(String name, String tagNamespace, boolean planks, boolean log, boolean stripped_log,
			boolean wood, boolean stripped_wood) {
		super(name, tagNamespace);

		PLANKS.setEnabled(planks);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setup() {
		PLANKS.setup(this, () -> new Block(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)), this.tagNamespace,
				"planks");
		LOG.setup(this, () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_LOG)), this.tagNamespace,
				"log");
		STRIPPED_LOG.setup(this, () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_LOG)),
				this.tagNamespace, "stripped_log");
		WOOD.setup(this, () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_LOG)),
				this.tagNamespace, "wood");
		STRIPPED_WOOD.setup(this, () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_LOG)),
				this.tagNamespace, "stripped_wood");

		this.extensions.forEach(i -> i.setup(this));
	}

	@Override
	public void tab(Output output) {
		PLANKS.tab(this, output);
		LOG.tab(this, output);
		STRIPPED_LOG.tab(this, output);
		WOOD.tab(this, output);
		STRIPPED_WOOD.tab(this, output);

		this.extensions.forEach(i -> i.tab(this, output));
	}

	@Override
	public void blockStateModel(BlockStateProvider bsp) {
		if (PLANKS.enabled())
			bsp.simpleBlock(PLANKS.BLOCK.get());

		this.extensions.forEach(i -> i.blockStateModel(this, bsp));
	}

	@Override
	public void itemModel(ItemModelProvider tmp) {
		if (PLANKS.enabled())
			ItemModels.forBlockItem(tmp, PLANKS.BLOCK_ITEM, name);

		this.extensions.forEach(i -> i.itemModel(this, tmp));
	}

	@Override
	public void engLoc(LanguageProvider lp) {
		String locName = this.name.substring(0, 1).toUpperCase() + this.name.substring(1);
		if (PLANKS.enabled())
			lp.add(this.PLANKS.BLOCK_ITEM.get(), locName + " Planks");

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

	public static class Serializer extends MaterialTypeSerializer<MaterialWood> {

		public Serializer() {
			super("WOOD");
		}

		@Override
		public MaterialWood deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			String name = j.get("name").getAsString();
			String tagNamespace = j.get("tagNamespace").getAsString();
			boolean plank = j.get("loadPlanks").getAsBoolean();
			boolean log = j.get("loadLog").getAsBoolean();
			boolean stripped_log = j.get("loadStrippedLog").getAsBoolean();
			boolean wood = j.get("loadWood").getAsBoolean();
			boolean stripped_wood = j.get("loadStrippedWood").getAsBoolean();

			MaterialWood w = new MaterialWood(name, tagNamespace, plank, log, stripped_log, wood, stripped_wood);

			JsonArray extensionsArray = j.getAsJsonArray("extensions");

			if (extensionsArray != null)
				for (JsonElement extensionElement : extensionsArray) {
					w.addExtension(context.deserialize(extensionElement, _MaterialExtension.class));
				}

			return w;
		}

		@Override
		public JsonElement serialize(MaterialWood src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("name", src.name);
			j.addProperty("tagNamespace", src.tagNamespace);
			j.addProperty("type", type);
			j.addProperty("loadPlanks", src.PLANKS.enabled());
			j.addProperty("loadLog", src.LOG.enabled());
			j.addProperty("loadStrippedLog", src.STRIPPED_LOG.enabled());
			j.addProperty("loadWood", src.WOOD.enabled());
			j.addProperty("loadStrippedWood", src.STRIPPED_WOOD.enabled());

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
		return Ingredient.of(this.PLANKS.BLOCK_ITEM.get());
	}

	@Override
	public MATERIAL_TYPES getType() {
		return MATERIAL_TYPES.WOOD;
	}

	@Override
	public void blockModel(IndexBlockModelProvider ibmp) {
		this.extensions.forEach(i -> i.blockModel(this, ibmp));
	}

	@Override
	public void otherLoot(LootTableSubProvider lsp) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isIndexItem(ItemStack stack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<IIndexEntry> getEntryItemBelongsTo(ItemStack stack) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public ItemStack breakDownItem(Ingredient ingredient) {
		ItemStack i = ItemStack.EMPTY;
		if (LOG.enabled())
			i = ScrappingUtils.convertBasedOnStack(ingredient, LOG.BLOCK_ITEM.asItem(), PLANKS.BLOCK_ITEM.asItem(), 4);
		else
			i = ScrappingUtils.convertBasedOnTag(ingredient, LOG.itemTag, PLANKS.itemTag, 4);

		if (i.isEmpty())
			if (PLANKS.enabled())
				i = ScrappingUtils.convertBasedOnStack(ingredient, PLANKS.BLOCK_ITEM.asItem(), Items.STICK, 2);
			else
				i = ScrappingUtils.convertBasedOnTag(ingredient, PLANKS.itemTag, Items.STICK, 2);
		if (i.isEmpty())
			i = ScrappingUtils.convertBasedOnStack(ingredient, Items.STICK, CompendiumItems.SAWDUST.asItem(), 2);

		return i;
	}

	@Override
	public ItemStack buildUpItem(Ingredient ingredient) {
		return ItemStack.EMPTY;
	}
}
