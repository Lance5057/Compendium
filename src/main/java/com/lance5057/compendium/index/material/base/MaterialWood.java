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
import com.lance5057.compendium.index.CompendiumIndex.Generate;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.util.TagUtil;
import com.lance5057.compendium.workstations.scrappingtable.ScrappingUtils;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class MaterialWood extends _MaterialBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9135211794674294863L;
	public CompendiumBlockHandler PLANKS = new CompendiumBlockHandler("planks");
	public CompendiumBlockHandler LOG = new CompendiumBlockHandler("log");
	public CompendiumBlockHandler STRIPPED_LOG = new CompendiumBlockHandler("stripped_log");
	public CompendiumBlockHandler WOOD = new CompendiumBlockHandler("wood");
	public CompendiumBlockHandler STRIPPED_WOOD = new CompendiumBlockHandler("stripped_wood");

	public MaterialWood(String name, String namespace) {
		this(name, namespace, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
				Generate.GENERATE);
	}

	public MaterialWood(String name, String namespace, Generate planks, Generate log, Generate stripped_log,
			Generate wood, Generate stripped_wood) {
		super(name, namespace);

		PLANKS.setGenerate(planks);
		LOG.setGenerate(log);
		STRIPPED_LOG.setGenerate(stripped_log);
		WOOD.setGenerate(wood);
		STRIPPED_WOOD.setGenerate(stripped_wood);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setup() {
		boolean isNether = this.name.equalsIgnoreCase("crimson") || this.name.equalsIgnoreCase("warped");

		PLANKS.setup(this, () -> new Block(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)),
				ResourceLocation.fromNamespaceAndPath(namespace, this.name + "_planks"),
				ResourceLocation.fromNamespaceAndPath(namespace, this.name + "_planks"));
//		PLANKS.setupItemTag(ItemTags.PLANKS);
		PLANKS.setupItemTag(TagUtil.neoTag("planks/" + name));
		PLANKS.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);

		LOG.setup(this, () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_LOG)),
				ResourceLocation.fromNamespaceAndPath(namespace, this.name + (!isNether ? "_log" : "_stem")),
				ResourceLocation.fromNamespaceAndPath(namespace, this.name + (!isNether ? "_log" : "_stem")));
		LOG.setupItemTag(ItemTags.LOGS);
		LOG.setupItemTag(TagUtil.neoTag("logs/" + name));
		LOG.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);

		STRIPPED_LOG.setup(this, () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_LOG)),
				ResourceLocation.fromNamespaceAndPath(namespace,
						this.name + "_stripped" + (!isNether ? "_log" : "_stem")),
				ResourceLocation.fromNamespaceAndPath(namespace,
						this.name + "_stripped" + (!isNether ? "_log" : "_stem")));
		STRIPPED_LOG.setupItemTag(Tags.Items.STRIPPED_LOGS);
		STRIPPED_LOG.setupItemTag(TagUtil.neoTag("stripped_log/" + name));
		STRIPPED_LOG.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);

		WOOD.setup(this, () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_LOG)),
				ResourceLocation.fromNamespaceAndPath(namespace, this.name + (!isNether ? "_wood" : "_hyphae")),
				ResourceLocation.fromNamespaceAndPath(namespace, this.name + (!isNether ? "_wood" : "_hyphae")));
		WOOD.setupItemTag(ItemTags.LOGS);
		WOOD.setupItemTag(TagUtil.neoTag("logs/" + name));
		WOOD.setupItemTag(TagUtil.neoTag("woods"));
		WOOD.setupItemTag(TagUtil.neoTag("woods/" + name));
		WOOD.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);

		STRIPPED_WOOD.setup(this, () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_LOG)),
				ResourceLocation.fromNamespaceAndPath(namespace,
						this.name + "_stripped" + (!isNether ? "_wood" : "_hyphae")),
				ResourceLocation.fromNamespaceAndPath(namespace,
						this.name + "_stripped" + (!isNether ? "_wood" : "_hyphae")));
		STRIPPED_WOOD.setupItemTag(Tags.Items.STRIPPED_LOGS);
		STRIPPED_WOOD.setupItemTag(TagUtil.neoTag("stripped_log/" + name));
		STRIPPED_WOOD.setupItemTag(TagUtil.neoTag("stripped_woods"));
		STRIPPED_WOOD.setupItemTag(TagUtil.neoTag("stripped_woods/" + name));
		STRIPPED_WOOD.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);

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
		if (PLANKS.shouldGenerate()) {
			bsp.getVariantBuilder(PLANKS.BLOCK.get()).partialState().addModels(new ConfiguredModel(
					bsp.models().cubeAll(this.blockFolder() + PLANKS.name, bsp.blockTexture(PLANKS.BLOCK.get()))));
		}

		if (LOG.shouldGenerate()) {
			bsp.logBlock((RotatedPillarBlock) LOG.BLOCK.get());
		}

		if (STRIPPED_LOG.shouldGenerate()) {
			bsp.logBlock((RotatedPillarBlock) STRIPPED_LOG.BLOCK.get());
		}

		if (WOOD.shouldGenerate()) {
			bsp.axisBlock((RotatedPillarBlock) WOOD.BLOCK.get(),
					ResourceLocation.fromNamespaceAndPath(namespace, "block/" + name + "_log"),
					ResourceLocation.fromNamespaceAndPath(namespace, "block/" + name + "_log"));
		}

		if (STRIPPED_WOOD.shouldGenerate()) {
			bsp.axisBlock((RotatedPillarBlock) STRIPPED_WOOD.BLOCK.get(),
					ResourceLocation.fromNamespaceAndPath(namespace, "block/stripped_" + name + "_log"),
					ResourceLocation.fromNamespaceAndPath(namespace, "block/stripped_" + name + "_log"));
		}

		this.extensions.forEach(i -> i.blockStateModel(this, bsp));
	}

	@Override
	public void itemModel(ItemModelProvider tmp) {
		if (PLANKS.shouldGenerate())
			ItemModels.forBlockItem(tmp, PLANKS.BLOCK_ITEM, name);
		
		if (LOG.shouldGenerate())
			ItemModels.forBlockItem(tmp, LOG.BLOCK_ITEM, name);
		
		if (STRIPPED_LOG.shouldGenerate())
			ItemModels.forBlockItem(tmp, STRIPPED_LOG.BLOCK_ITEM, name);
		
		if (WOOD.shouldGenerate())
			ItemModels.forBlockItem(tmp, WOOD.BLOCK_ITEM, name);
		
		if (STRIPPED_WOOD.shouldGenerate())
			ItemModels.forBlockItem(tmp, STRIPPED_WOOD.BLOCK_ITEM, name);

		this.extensions.forEach(i -> i.itemModel(this, tmp));
	}

	@Override
	public void engLoc(LanguageProvider lp) {
		String locName = this.name.substring(0, 1).toUpperCase() + this.name.substring(1);
		lp.add("compendium.tooltip.material." + this.name, locName);
		if (PLANKS.shouldGenerate())
			lp.add(this.PLANKS.BLOCK_ITEM.get(), locName + " Planks");

		this.extensions.forEach(i -> i.engLoc(this, lp));
	}

	@Override
	public void recipes(RecipeOutput consumer) {
		this.extensions.forEach(i -> i.recipes(this, consumer));
	}

	@Override
	public void blockLoot(BlockLootSubProvider blp) {
		if (LOG.shouldGenerate())
			blp.dropSelf(LOG.BLOCK.get());
		if (PLANKS.shouldGenerate())
			blp.dropSelf(PLANKS.BLOCK.get());
		if (STRIPPED_LOG.shouldGenerate())
			blp.dropSelf(STRIPPED_LOG.BLOCK.get());
		if (STRIPPED_WOOD.shouldGenerate())
			blp.dropSelf(STRIPPED_WOOD.BLOCK.get());
		if (WOOD.shouldGenerate())
			blp.dropSelf(WOOD.BLOCK.get());
		this.extensions.forEach(i -> i.blockLoot(this, blp));
	}

	@Override
	public void setupItemTags(ItemTagsProvider itp) {
		this.extensions.forEach(i -> i.setupItemTags(this, itp));
	}

	@Override
	public void setupBlockTags(BlockTagsProvider btp) {
		this.extensions.forEach(i -> i.setupBlockTags(this, btp));
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
			String namespace = j.get("namespace").getAsString();
			String plank = j.get("loadPlanks").getAsString();
			String log = j.get("loadLog").getAsString();
			String stripped_log = j.get("loadStrippedLog").getAsString();
			String wood = j.get("loadWood").getAsString();
			String stripped_wood = j.get("loadStrippedWood").getAsString();

			MaterialWood w = new MaterialWood(name, namespace, Generate.valueOf(plank), Generate.valueOf(log),
					Generate.valueOf(stripped_log), Generate.valueOf(wood), Generate.valueOf(stripped_wood));

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
			j.addProperty("namespace", src.namespace);
			j.addProperty("type", type);
			j.addProperty("loadPlanks", src.PLANKS.getGeneration().toString());
			j.addProperty("loadLog", src.LOG.getGeneration().toString());
			j.addProperty("loadStrippedLog", src.STRIPPED_LOG.getGeneration().toString());
			j.addProperty("loadWood", src.WOOD.getGeneration().toString());
			j.addProperty("loadStrippedWood", src.STRIPPED_WOOD.getGeneration().toString());

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
		if (PLANKS.is(stack))
			return true;
		if (LOG.is(stack))
			return true;
		if (STRIPPED_LOG.is(stack))
			return true;
		if (WOOD.is(stack))
			return true;
		if (STRIPPED_WOOD.is(stack))
			return true;

		for (_MaterialExtension m : extensions) {
			boolean o = m.isIndexItem(this, stack);

			if (o)
				return o;
		}
		return false;
	}

	@Override
	public Optional<IIndexEntry> getEntryItemBelongsTo(ItemStack stack) {
		if (PLANKS.is(stack))
			return Optional.of(this);
		if (LOG.is(stack))
			return Optional.of(this);
		if (STRIPPED_LOG.is(stack))
			return Optional.of(this);
		if (WOOD.is(stack))
			return Optional.of(this);
		if (STRIPPED_WOOD.is(stack))
			return Optional.of(this);

		for (_MaterialExtension m : extensions) {
			Optional<IIndexEntry> o = m.getEntryItemBelongsTo(this, stack);

			if (o.isPresent())
				return o;
		}
		return Optional.empty();
	}

	@Override
	public ItemStack breakDownItem(Ingredient ingredient) {
		ItemStack i = ItemStack.EMPTY;
		if (LOG.getGeneration() != Generate.IGNORE)
			i = ScrappingUtils.convertBasedOnStack(ingredient, LOG.BLOCK_ITEM.asItem(), PLANKS.BLOCK_ITEM.asItem(), 4);
		else
			i = ScrappingUtils.convertBasedOnTag(ingredient, LOG.itemTag, PLANKS.itemTag, 4);

		if (i.isEmpty())
			if (PLANKS.getGeneration() != Generate.IGNORE)
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
