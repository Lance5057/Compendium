package com.lance5057.compendium.index.material.base.wood;

import java.lang.reflect.Type;
import java.util.Optional;

import javax.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.annotations.Since;
import com.lance5057.compendium.CompendiumItems;
import com.lance5057.compendium.data.ItemModels;
import com.lance5057.compendium.index.CompendiumIndex.Generate;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.index.json.IndexInitialResourceLoader;
import com.lance5057.compendium.index.material.base.MaterialTypeSerializer;
import com.lance5057.compendium.index.material.base._MaterialBase;
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
	public final CompendiumBlockHandler PLANKS;
	public final CompendiumBlockHandler LOG;
	public final CompendiumBlockHandler STRIPPED_LOG;
	public final CompendiumBlockHandler WOOD;
	public final CompendiumBlockHandler STRIPPED_WOOD;

	@Nullable
	@Since(1.1)
	public SpecialLocationsWood specialLocations;

	public MaterialWood(String name, String namespace) {
		this(name, namespace, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE, Generate.GENERATE,
				Generate.GENERATE);
	}

	public MaterialWood(String name, String namespace, Generate planks, Generate log, Generate stripped_log,
			Generate wood, Generate stripped_wood) {
		this(name, namespace, planks, log, stripped_log, wood, stripped_wood, null);
	}

	public MaterialWood(String name, String namespace, Generate planks, Generate log, Generate stripped_log,
			Generate wood, Generate stripped_wood, SpecialLocationsWood loc) {
		super(name, namespace);

		PLANKS = new CompendiumBlockHandler(name + "_planks");
		LOG = new CompendiumBlockHandler(name + "_log");
		STRIPPED_LOG = new CompendiumBlockHandler("stripped_" + name + "_log");
		WOOD = new CompendiumBlockHandler(name + "_wood");
		STRIPPED_WOOD = new CompendiumBlockHandler("stripped_" + name + "_wood");

		PLANKS.setGenerate(planks);
		LOG.setGenerate(log);
		STRIPPED_LOG.setGenerate(stripped_log);
		WOOD.setGenerate(wood);
		STRIPPED_WOOD.setGenerate(stripped_wood);

		specialLocations = loc;
	}

	@Override
	public String getName() {
		return this.name;
	}

	private ResourceLocation fileLoc(ResourceLocation standardLoc, ResourceLocation strippedWoodLocation) {
		if (strippedWoodLocation != null) {
			return strippedWoodLocation;
		}

		return standardLoc;
	}

	@Override
	public void setup() {
		boolean isNether = this.name.equalsIgnoreCase("crimson") || this.name.equalsIgnoreCase("warped");

		ExistsLocationsWood existsItem = null;
		ExistsLocationsWood existsBlock = null;

		if (this.specialLocations != null) {
			if (specialLocations.existsItem != null)
				existsItem = specialLocations.existsItem;
			if (specialLocations.existsBlock != null)
				existsBlock = specialLocations.existsBlock;
		}

		setupPlanks(existsItem, existsBlock);
		setupLogs(isNether, existsItem, existsBlock);
		setupStrippedLogs(isNether, existsItem, existsBlock);
		setupWood(isNether, existsItem, existsBlock);
		setupStrippedWood(isNether, existsItem, existsBlock);

		this.extensions.forEach(i -> i.setup(this));
	}

	public void setupStrippedWood(boolean isNether, ExistsLocationsWood existsItem, ExistsLocationsWood existsBlock) {
		ResourceLocation standardLoc = ResourceLocation.fromNamespaceAndPath(this.namespace,
				"stripped_" + this.name + (!isNether ? "_wood" : "_hyphae"));

		STRIPPED_WOOD.setup(this, () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_LOG)),
				existsItem != null ? fileLoc(standardLoc, existsItem.strippedWoodLocation) : standardLoc,
				existsBlock != null ? fileLoc(standardLoc, existsBlock.strippedWoodLocation) : standardLoc);

		STRIPPED_WOOD.setupItemTag(Tags.Items.STRIPPED_LOGS);
		STRIPPED_WOOD.setupItemTag(TagUtil.neoTag("stripped_log/" + name));
		STRIPPED_WOOD.setupItemTag(TagUtil.neoTag("stripped_woods"));
		STRIPPED_WOOD.setupItemTag(TagUtil.neoTag("stripped_woods/" + name));
		STRIPPED_WOOD.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
	}

	public void setupWood(boolean isNether, ExistsLocationsWood existsItem, ExistsLocationsWood existsBlock) {
		ResourceLocation standardLoc = ResourceLocation.fromNamespaceAndPath(this.namespace,
				this.name + (!isNether ? "_wood" : "_hyphae"));

		WOOD.setup(this, () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_LOG)),
				existsItem != null ? fileLoc(standardLoc, existsItem.woodLocation) : standardLoc,
				existsBlock != null ? fileLoc(standardLoc, existsBlock.woodLocation) : standardLoc);

		WOOD.setupItemTag(ItemTags.LOGS);
		WOOD.setupItemTag(TagUtil.neoTag("logs/" + name));
		WOOD.setupItemTag(TagUtil.neoTag("woods"));
		WOOD.setupItemTag(TagUtil.neoTag("woods/" + name));
		WOOD.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
	}

	public void setupStrippedLogs(boolean isNether, ExistsLocationsWood existsItem, ExistsLocationsWood existsBlock) {
		ResourceLocation standardLoc = ResourceLocation.fromNamespaceAndPath(this.namespace,
				"stripped_" + this.name + (!isNether ? "_log" : "_stem"));

		STRIPPED_LOG.setup(this, () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_LOG)),
				existsItem != null ? fileLoc(standardLoc, existsItem.strippedLogLocation) : standardLoc,
				existsBlock != null ? fileLoc(standardLoc, existsBlock.strippedLogLocation) : standardLoc);

		STRIPPED_LOG.setupItemTag(Tags.Items.STRIPPED_LOGS);
		STRIPPED_LOG.setupItemTag(TagUtil.neoTag("stripped_log/" + name));
		STRIPPED_LOG.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
	}

	public void setupLogs(boolean isNether, ExistsLocationsWood existsItem, ExistsLocationsWood existsBlock) {
		ResourceLocation standardLoc = ResourceLocation.fromNamespaceAndPath(this.namespace,
				this.name + (!isNether ? "_log" : "_stem"));

		LOG.setup(this, () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_LOG)),
				existsItem != null ? fileLoc(standardLoc, existsItem.logLocation) : standardLoc,
				existsBlock != null ? fileLoc(standardLoc, existsBlock.logLocation) : standardLoc);

		LOG.setupItemTag(ItemTags.LOGS);
		LOG.setupItemTag(TagUtil.neoTag("logs/" + name));
		LOG.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
	}

	public void setupPlanks(ExistsLocationsWood existsItem, ExistsLocationsWood existsBlock) {
		ResourceLocation standardLoc = ResourceLocation.fromNamespaceAndPath(this.namespace, this.name + "_planks");

		ResourceLocation specialItem = existsItem != null ? fileLoc(standardLoc, existsItem.plankLocation)
				: standardLoc;
		ResourceLocation specialBlock = existsBlock != null ? fileLoc(standardLoc, existsBlock.plankLocation)
				: standardLoc;

		PLANKS.setup(this, () -> new Block(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)), specialItem,
				specialBlock);

		PLANKS.setupItemTag(ItemTags.PLANKS);
		PLANKS.setupItemTag(TagUtil.neoTag("planks/" + name));
		PLANKS.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
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
		ResourceLocation plankTexture = ResourceLocation.fromNamespaceAndPath(namespace, "block/" + name + "_planks");
		ResourceLocation logTexture = ResourceLocation.fromNamespaceAndPath(namespace, "block/" + name + "_log");
		ResourceLocation logTopTexture = ResourceLocation.fromNamespaceAndPath(namespace, "block/" + name + "_log_top");
		ResourceLocation strippedLogTexture = ResourceLocation.fromNamespaceAndPath(namespace,
				"block/stripped_" + name + "_log");
		ResourceLocation strippedLogTopTexture = ResourceLocation.fromNamespaceAndPath(namespace,
				"block/stripped_" + name + "_log_top");

		if (this.specialLocations != null) {
			if (specialLocations.textures != null) {
				if (specialLocations.textures.plankLocation != null)
					plankTexture = specialLocations.textures.plankLocation;
				if (specialLocations.textures.logLocation != null)
					logTexture = specialLocations.textures.logLocation;
				if (specialLocations.textures.logTopLocation != null)
					logTopTexture = specialLocations.textures.logTopLocation;
				if (specialLocations.textures.strippedLogLocation != null)
					strippedLogTexture = specialLocations.textures.strippedLogLocation;
				if (specialLocations.textures.strippedLogTopLocation != null)
					strippedLogTopTexture = specialLocations.textures.strippedLogTopLocation;
			}
		}
		if (PLANKS.shouldGenerate()) {
			bsp.getVariantBuilder(PLANKS.BLOCK.get()).partialState().addModels(
					new ConfiguredModel(bsp.models().cubeAll(this.blockFolder() + PLANKS.name, plankTexture)));
		}

		if (LOG.shouldGenerate()) {
			bsp.axisBlock((RotatedPillarBlock) LOG.BLOCK.get(), logTexture, logTopTexture);
		}

		if (STRIPPED_LOG.shouldGenerate()) {
			bsp.axisBlock((RotatedPillarBlock) STRIPPED_LOG.BLOCK.get(), strippedLogTexture, strippedLogTopTexture);
		}

		if (WOOD.shouldGenerate()) {
			bsp.axisBlock((RotatedPillarBlock) WOOD.BLOCK.get(), logTexture, logTexture);
		}

		if (STRIPPED_WOOD.shouldGenerate()) {
			bsp.axisBlock((RotatedPillarBlock) STRIPPED_WOOD.BLOCK.get(), strippedLogTexture, strippedLogTexture);
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
		StringBuilder locName = new StringBuilder();
		for (String word : this.name.split("_")) {
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			locName.append(word).append(" ");
		}
		lp.add("compendium.tooltip.material." + this.name, locName.toString());
		if (PLANKS.shouldGenerate())
			lp.add(this.PLANKS.BLOCK_ITEM.get(), locName + " Planks");

		this.extensions.forEach(i -> i.engLoc(this, lp));
	}

	@Override
	public void recipes(RecipeOutput consumer) {
//		if (!LOG.isIgnored() && !STRIPPED_LOG.isIgnored())
//			CuttingBoardRecipeBuilder
//					.cuttingRecipe(Ingredient.of(LOG.BLOCK_ITEM), Ingredient.of(CommonTags.TOOLS_KNIFE),
//							STRIPPED_LOG.BLOCK_ITEM, 1)
//					.addResult(ModItems.TREE_BARK.get())
//					.build(consumer, Compendium.modLoc("cutting/" + name + "_scrape_log"));
//
//		if (!WOOD.isIgnored() && !STRIPPED_WOOD.isIgnored())
//			CuttingBoardRecipeBuilder
//					.cuttingRecipe(Ingredient.of(WOOD.BLOCK_ITEM), Ingredient.of(CommonTags.TOOLS_KNIFE),
//							STRIPPED_WOOD.BLOCK_ITEM, 1)
//					.addResult(ModItems.TREE_BARK.get())
//					.build(consumer, Compendium.modLoc("cutting/" + name + "_scrape_wood"));

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
		LOG.itemTag(itp);
		PLANKS.itemTag(itp);
		STRIPPED_LOG.itemTag(itp);
		STRIPPED_WOOD.itemTag(itp);
		WOOD.itemTag(itp);

		this.extensions.forEach(i -> i.setupItemTags(this, itp));
	}

	@Override
	public void setupBlockTags(BlockTagsProvider btp) {
		LOG.blockTag(btp);
		PLANKS.blockTag(btp);
		STRIPPED_LOG.blockTag(btp);
		STRIPPED_WOOD.blockTag(btp);
		WOOD.blockTag(btp);

		this.extensions.forEach(i -> i.setupBlockTags(this, btp));
	}

	public static class Serializer extends MaterialTypeSerializer<MaterialWood> {

		public Serializer() {
			super("WOOD");
		}

		@Override
		public MaterialWood deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			MaterialWood w = null;
			JsonObject j = json.getAsJsonObject();

			String name = j.get("name").getAsString();
			String namespace = j.get("namespace").getAsString();
			String type = j.get("type").getAsString();

			String plank = j.get("loadPlanks").getAsString();
			String log = j.get("loadLog").getAsString();
			String stripped_log = j.get("loadStrippedLog").getAsString();
			String wood = j.get("loadWood").getAsString();
			String stripped_wood = j.get("loadStrippedWood").getAsString();

			if (j.get("version") != null) {
				Double version = j.get("version").getAsDouble();
				if (version >= 1.1) {
					SpecialLocationsWood sp = null;
					if (j.get("specialLocations") != null)
						sp = context.deserialize(j.get("specialLocations"), SpecialLocationsWood.class);

					w = new MaterialWood(name, namespace, Generate.valueOf(plank), Generate.valueOf(log),
							Generate.valueOf(stripped_log), Generate.valueOf(wood), Generate.valueOf(stripped_wood),
							sp);
				}

			} else
				w = new MaterialWood(name, namespace, Generate.valueOf(plank), Generate.valueOf(log),
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
			j.addProperty("version", IndexInitialResourceLoader.VERSION);
			j.addProperty("loadPlanks", src.PLANKS.getGeneration().toString());
			j.addProperty("loadLog", src.LOG.getGeneration().toString());
			j.addProperty("loadStrippedLog", src.STRIPPED_LOG.getGeneration().toString());
			j.addProperty("loadWood", src.WOOD.getGeneration().toString());
			j.addProperty("loadStrippedWood", src.STRIPPED_WOOD.getGeneration().toString());

			if (src.specialLocations != null) {
				j.add("specialLocations", context.serialize(src.specialLocations, SpecialLocationsWood.class));
			}

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
