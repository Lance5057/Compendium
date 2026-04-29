package com.lance5057.compendium.index.material.base.wood;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.components.block.IndexEntryComponent;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.json.IndexInitialResourceLoader;
import com.lance5057.compendium.index.material.base.MaterialTypeSerializer;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.base.wood.locations.SpecialLocationsWood;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.util.TagUtil;

import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

public class MaterialWood extends _MaterialBase {

	private static final long serialVersionUID = 9135211794674294863L;
	public final CompendiumBlockHandler PLANKS;
	public final CompendiumBlockHandler LOG;
	public final CompendiumBlockHandler STRIPPED_LOG;
	public final CompendiumBlockHandler WOOD;
	public final CompendiumBlockHandler STRIPPED_WOOD;

//	@Nullable
//	@Since(1.1)
	public SpecialLocationsWood specialLocations;

	public MaterialWood(String name, String namespace) {
		this(name, namespace, null);
	}

	public MaterialWood(String name, String namespace, SpecialLocationsWood loc) {
		super(name, namespace);

		this.BLOCKS.add(PLANKS = new CompendiumBlockHandler(name + "_planks"));
		this.BLOCKS.add(LOG = new CompendiumBlockHandler(name + "_log"));
		this.BLOCKS.add(STRIPPED_LOG = new CompendiumBlockHandler("stripped_" + name + "_log"));
		this.BLOCKS.add(WOOD = new CompendiumBlockHandler(name + "_wood"));
		this.BLOCKS.add(STRIPPED_WOOD = new CompendiumBlockHandler("stripped_" + name + "_wood"));

		specialLocations = loc;
	}

//	public MaterialWood(String name, String namespace, Generate planks, Generate log, Generate stripped_log,
//			Generate wood, Generate stripped_wood) {
//		this(name, namespace, planks, log, stripped_log, wood, stripped_wood, null);
//	}
//
//	public MaterialWood(String name, String namespace, Generate planks, Generate log, Generate stripped_log,
//			Generate wood, Generate stripped_wood, SpecialLocationsWood loc) {
//		super(name, namespace);
//
//		PLANKS = new CompendiumBlockHandler(name + "_planks");
//		LOG = new CompendiumBlockHandler(name + "_log");
//		STRIPPED_LOG = new CompendiumBlockHandler("stripped_" + name + "_log");
//		WOOD = new CompendiumBlockHandler(name + "_wood");
//		STRIPPED_WOOD = new CompendiumBlockHandler("stripped_" + name + "_wood");
//
//		PLANKS.setGenerate(planks);
//		LOG.setGenerate(log);
//		STRIPPED_LOG.setGenerate(stripped_log);
//		WOOD.setGenerate(wood);
//		STRIPPED_WOOD.setGenerate(stripped_wood);
//
////		specialLocations = loc;
//	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setup() {
		boolean isNether = this.name.equalsIgnoreCase("crimson") || this.name.equalsIgnoreCase("warped");

//		ExistsLocationsWood existsItem = null;
//		ExistsLocationsWood existsBlock = null;
//
//		if (this.specialLocations != null) {
//			if (specialLocations.existsItem != null)
//				existsItem = specialLocations.existsItem;
//			if (specialLocations.existsBlock != null)
//				existsBlock = specialLocations.existsBlock;
//		}

		setupPlanks();
		setupLogs(isNether);
		setupStrippedLogs(isNether);
		setupWood(isNether);
		setupStrippedWood(isNether);

		this.extensions.forEach(i -> i.setup(this));
	}

	public void setupStrippedWood(boolean isNether) {
		STRIPPED_WOOD.setup(this,
				() -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_LOG)));

		STRIPPED_WOOD.setupItemTag(Tags.Items.STRIPPED_LOGS);
		STRIPPED_WOOD.setupItemTag(TagUtil.neoTag("stripped_log/" + name));
		STRIPPED_WOOD.setupItemTag(TagUtil.neoTag("stripped_woods"));
		STRIPPED_WOOD.setupItemTag(TagUtil.neoTag("stripped_woods/" + name));
		STRIPPED_WOOD.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);

//		this.BLOCKS.add(STRIPPED_LOG);
	}

	public void setupWood(boolean isNether) {
		WOOD.setup(this, () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_LOG)));

		WOOD.setupItemTag(ItemTags.LOGS);
		WOOD.setupItemTag(TagUtil.neoTag("logs/" + name));
		WOOD.setupItemTag(TagUtil.neoTag("woods"));
		WOOD.setupItemTag(TagUtil.neoTag("woods/" + name));
		WOOD.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);

//		this.BLOCKS.add(WOOD);
	}

	public void setupStrippedLogs(boolean isNether) {
		STRIPPED_LOG.setup(this, () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.STRIPPED_ACACIA_LOG)));

		STRIPPED_LOG.setupItemTag(Tags.Items.STRIPPED_LOGS);
		STRIPPED_LOG.setupItemTag(TagUtil.neoTag("stripped_log/" + name));
		STRIPPED_LOG.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);

//		this.BLOCKS.add(STRIPPED_LOG);
	}

	public void setupLogs(boolean isNether) {
		LOG.setup(this, () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_LOG)));

		LOG.setupItemTag(ItemTags.LOGS);
		LOG.setupItemTag(TagUtil.neoTag("logs/" + name));
		LOG.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);

//		this.BLOCKS.add(LOG);
	}

	public void setupPlanks() {
		PLANKS.setup(this, () -> new Block(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));

		PLANKS.setupItemTag(ItemTags.PLANKS);
		PLANKS.setupItemTag(TagUtil.neoTag("planks/" + name));
		PLANKS.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);

//		this.BLOCKS.add(PLANKS);
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

//	@Override
//	public void setupItemTags(ItemTagsProvider itp) {
//		LOG.itemTag(itp);
//		PLANKS.itemTag(itp);
//		STRIPPED_LOG.itemTag(itp);
//		STRIPPED_WOOD.itemTag(itp);
//		WOOD.itemTag(itp);
//
//		this.extensions.forEach(i -> i.setupItemTags(this, itp));
//	}
//
//	@Override
//	public void setupBlockTags(BlockTagsProvider btp) {
//		LOG.blockTag(btp);
//
//		PLANKS.blockTag(btp);
//
//		STRIPPED_LOG.blockTag(btp);
//		STRIPPED_WOOD.blockTag(btp);
//		WOOD.blockTag(btp);
//
//		this.extensions.forEach(i -> i.setupBlockTags(this, btp));
//	}

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

			SpecialLocationsWood sp = null;

			if (j.get("specialLocations") != null)
				sp = context.deserialize(j.get("specialLocations"), SpecialLocationsWood.class);

			w = new MaterialWood(name, namespace, sp);

			JsonArray extensionsArray = j.getAsJsonArray("extensions");

			if (extensionsArray != null)
				for (JsonElement extensionElement : extensionsArray) {
					w.addExtension(context.deserialize(extensionElement, _MaterialExtension.class));
				}

			w.PLANKS.deserialize(j.get("planks").getAsJsonObject());
			w.LOG.deserialize(j.get("log").getAsJsonObject());
			w.STRIPPED_LOG.deserialize(j.get("stripped_log").getAsJsonObject());
			w.WOOD.deserialize(j.get("wood").getAsJsonObject());
			w.STRIPPED_WOOD.deserialize(j.get("stripped_wood").getAsJsonObject());

			return w;
		}

		@Override
		public JsonElement serialize(MaterialWood src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("name", src.name);
			j.addProperty("namespace", src.namespace);
			j.addProperty("type", type);
			j.addProperty("version", IndexInitialResourceLoader.VERSION);

			j.add("planks", src.PLANKS.serialize());
			j.add("log", src.LOG.serialize());
			j.add("stripped_log", src.STRIPPED_LOG.serialize());
			j.add("wood", src.WOOD.serialize());
			j.add("stripped_wood", src.STRIPPED_WOOD.serialize());

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

//	@Override
//	public void attachComponents(ModifyDefaultComponentsEvent event) {
//
//		this.ITEMS.forEach(i -> {
//			if (i.isNotIgnored())
//				event.modify(i.ITEM.get(), builder -> builder.set(CompendiumComponents.INDEX.get(),
//						new IndexEntryComponent(getType(), name)));
//		});
//		this.BLOCKS.forEach(i -> {
//			if (i.isNotIgnored())
//				event.modify(i.BLOCK_ITEM.get(), builder -> builder.set(CompendiumComponents.INDEX.get(),
//						new IndexEntryComponent(getType(), name)));
//		});
//		this.extensions.forEach(i -> i.attachComponents(this, event));
//	}
}
