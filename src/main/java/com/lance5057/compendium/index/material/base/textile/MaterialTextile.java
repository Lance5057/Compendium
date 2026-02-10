package com.lance5057.compendium.index.material.base.textile;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.annotations.Since;
import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.data.IndexBlockModelProvider;
import com.lance5057.compendium.index.CompendiumIndex.Generate;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.index.json.IndexInitialResourceLoader;
import com.lance5057.compendium.index.material.base.MaterialTypeSerializer;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.index.util.CompendiumItemHandler;
import com.lance5057.compendium.util.TagUtil;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class MaterialTextile extends _MaterialBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2331511780363081257L;
	public final CompendiumBlockHandler BLOCK;
	public final CompendiumItemHandler STRING;
	public final CompendiumBlockHandler CARPET;

	@Nullable
	@Since(1.1)
	public SpecialLocationsTextile specialLocations;

	public MaterialTextile(String name, String tagNamespace, Generate block, Generate string, Generate carpet) {
		this(name, tagNamespace, block, string, carpet, null);
	}

	public MaterialTextile(String name, String tagNamespace, Generate block, Generate string, Generate carpet,
			SpecialLocationsTextile loc) {
		super(name, tagNamespace);
		BLOCK = new CompendiumBlockHandler(name + "_block");
		STRING = new CompendiumItemHandler(name + "_string");
		CARPET = new CompendiumBlockHandler(name + "_carpet");

		BLOCK.setGenerate(block);
		STRING.setGenerate(string);
		CARPET.setGenerate(carpet);

		this.specialLocations = loc;
	}

	@Override
	public String getName() {
		return this.name;
	}

	private ResourceLocation fileLoc(ResourceLocation standard, ResourceLocation exists) {
		if (exists != null) {
			return exists;
		}

		return standard;
	}

	@Override
	public void setup() {
		ExistsLocationsTextile existsItem = null;
		ExistsLocationsTextile existsBlock = null;

		if (this.specialLocations != null) {
			if (specialLocations.existsItem != null)
				existsItem = specialLocations.existsItem;
			if (specialLocations.existsBlock != null)
				existsBlock = specialLocations.existsBlock;
		}

		setupBlock(existsItem, existsBlock);
		setupString(existsItem);
		setupCarpet(existsItem, existsBlock);

	}

	private void setupCarpet(ExistsLocationsTextile existsItem, ExistsLocationsTextile existsBlock) {
		ResourceLocation standardItemLoc = ResourceLocation.fromNamespaceAndPath(this.namespace, this.name);
		ResourceLocation standardBlockLoc = ResourceLocation.fromNamespaceAndPath(this.namespace, this.name);

		CARPET.setup(this, () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET)),

				existsItem != null ? fileLoc(standardItemLoc, existsItem.blockLocation) : standardItemLoc,

				existsBlock != null ? fileLoc(standardBlockLoc, existsBlock.blockLocation) : standardBlockLoc);

		CARPET.setupItemTag(TagUtil.neoTag("carpet/" + name));
		CARPET.setupItemTag(ItemTags.WOOL_CARPETS);
		CARPET.setupBlockTag(ResourceLocation.fromNamespaceAndPath("farmersdelight", "mineable/knife"));
	}

	private void setupString(ExistsLocationsTextile existsItem) {
		ResourceLocation standardItemLoc = ResourceLocation.fromNamespaceAndPath(this.namespace, this.name + "_string");

		STRING.setup(this, existsItem != null ? fileLoc(standardItemLoc, existsItem.blockLocation) : standardItemLoc);
	}

	private void setupBlock(ExistsLocationsTextile existsItem, ExistsLocationsTextile existsBlock) {
		ResourceLocation standardItemLoc = ResourceLocation.fromNamespaceAndPath(this.namespace, this.name);
		ResourceLocation standardBlockLoc = ResourceLocation.fromNamespaceAndPath(this.namespace, this.name);

		BLOCK.setup(this, () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL)),
				existsItem != null ? fileLoc(standardItemLoc, existsItem.carpetLocation) : standardItemLoc,
				existsBlock != null ? fileLoc(standardBlockLoc, existsBlock.carpetLocation) : standardBlockLoc);

		BLOCK.setupItemTag(CompendiumTags.TEXTILES);
		BLOCK.setupItemTag(TagUtil.neoTag("textiles/" + name));
	}

	@Override
	public void tab(Output output) {
		BLOCK.tab(this, output);
		STRING.tab(this, output);
		CARPET.tab(this, output);
	}

	@Override
	public void blockStateModel(BlockStateProvider bsp) {
		if (BLOCK.shouldGenerate()) {
			bsp.getVariantBuilder(BLOCK.BLOCK.get()).partialState()
					.addModels(new ConfiguredModel(bsp.models().cubeAll(this.blockFolder() + BLOCK.name,
							ResourceLocation.fromNamespaceAndPath(namespace, "block/" + this.name))));
		}

		if (CARPET.shouldGenerate()) {
			bsp.getVariantBuilder(CARPET.BLOCK.get()).partialState()
					.addModels(new ConfiguredModel(bsp.models().carpet(this.blockFolder() + CARPET.name,
							ResourceLocation.fromNamespaceAndPath(namespace, "block/" + this.name))));
		}
		this.extensions.forEach(i -> i.blockStateModel(this, bsp));
	}

	@Override
	public void itemModel(ItemModelProvider tmp) {
		if (BLOCK.shouldGenerate())
			tmp.getBuilder(BLOCK.BLOCK_ITEM.getId().getPath()).parent(new ModelFile.UncheckedModelFile(
					ResourceLocation.fromNamespaceAndPath(namespace, this.blockFolder() + BLOCK.name)));

		if (CARPET.shouldGenerate())
			tmp.getBuilder(CARPET.BLOCK_ITEM.getId().getPath()).parent(new ModelFile.UncheckedModelFile(
					ResourceLocation.fromNamespaceAndPath(namespace, this.blockFolder() + CARPET.name)));

		this.extensions.forEach(i -> i.itemModel(this, tmp));
	}

	@Override
	public void engLoc(LanguageProvider lp) {

		this.extensions.forEach(i -> i.engLoc(this, lp));
	}

	@Override
	public void recipes(RecipeOutput consumer) {
		if (CARPET.shouldGenerate()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, CARPET.BLOCK_ITEM, 3).define('p', BLOCK.BLOCK_ITEM)
					.pattern("pp")
					.unlockedBy("block",
							CriteriaTriggers.INVENTORY_CHANGED.createCriterion(new InventoryChangeTrigger.TriggerInstance(
									Optional.empty(), InventoryChangeTrigger.TriggerInstance.Slots.ANY,
									List.of(ItemPredicate.Builder.item().of(BLOCK.BLOCK_ITEM.asItem()).build()))))
					.save(consumer, TagUtil.modLoc(this.name + "_carpet"));
		}
	}

	@Override
	public void blockLoot(BlockLootSubProvider blp) {
		if (BLOCK.shouldGenerate())
			blp.dropSelf(this.BLOCK.BLOCK.get());

		if (CARPET.shouldGenerate())
			blp.dropSelf(this.CARPET.BLOCK.get());
	}

	@Override
	public void setupItemTags(ItemTagsProvider itp) {
		BLOCK.itemTag(itp);
		CARPET.itemTag(itp);
		STRING.itemTag(itp);

		this.extensions.forEach(i -> i.setupItemTags(this, itp));
	}

	@Override
	public void setupBlockTags(BlockTagsProvider btp) {
		BLOCK.blockTag(btp);
		CARPET.blockTag(btp);
		this.extensions.forEach(i -> i.setupBlockTags(this, btp));
	}

	@Override
	public void setupClient(FMLClientSetupEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public Ingredient getBaseItem() {
		return Ingredient.of(this.BLOCK.BLOCK_ITEM.get());
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
			MaterialTextile w = null;
			JsonObject j = json.getAsJsonObject();

			String name = j.get("name").getAsString();
			String tagNamespace = j.get("tagNamespace").getAsString();
			String type = j.get("type").getAsString();

			String string = j.get("loadString").getAsString();
			String block = j.get("loadBlock").getAsString();
			String carpet = j.get("loadCarpet").getAsString();

			JsonArray extensionsArray = j.getAsJsonArray("extensions");

			if (j.get("version") != null) {
				Double version = j.get("version").getAsDouble();
				if (version >= 1.1) {
					SpecialLocationsTextile sp = null;
					if (j.get("specialLocations") != null)
						sp = context.deserialize(j.get("specialLocations"), SpecialLocationsTextile.class);

					w = new MaterialTextile(name, tagNamespace, Generate.valueOf(block), Generate.valueOf(string),
							Generate.valueOf(carpet), sp);
				}

			} else
				w = new MaterialTextile(name, tagNamespace, Generate.valueOf(block), Generate.valueOf(string),
						Generate.valueOf(carpet));

			if (extensionsArray != null)
				for (JsonElement extensionElement : extensionsArray) {
					w.addExtension(context.deserialize(extensionElement, _MaterialExtension.class));
				}

			return w;
		}

		@Override
		public JsonElement serialize(MaterialTextile src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("name", src.name);
			j.addProperty("tagNamespace", src.namespace);
			j.addProperty("type", type);
			j.addProperty("version", IndexInitialResourceLoader.VERSION);
			j.addProperty("loadString", src.STRING.getGeneration().toString());
			j.addProperty("loadBlock", src.BLOCK.getGeneration().toString());
			j.addProperty("loadCarpet", src.CARPET.getGeneration().toString());

			if (src.specialLocations != null) {
				j.add("specialLocations", context.serialize(src.specialLocations, SpecialLocationsTextile.class));
			}

			JsonArray ext = new JsonArray();

			for (_MaterialExtension e : src.extensions)
				ext.add(context.serialize(e));

			j.add("extensions", ext);

			return j;
		}

	}

	@Override
	public void otherLoot(LootTableSubProvider lsp) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isIndexItem(ItemStack stack) {
		if (BLOCK.is(stack))
			return true;
		if (STRING.is(stack))
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
		if (BLOCK.is(stack))
			return Optional.of(this);
		if (STRING.is(stack))
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
		// TODO Auto-generated method stub
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack buildUpItem(Ingredient ingredient) {
		// TODO Auto-generated method stub
		return ItemStack.EMPTY;
	}

}
