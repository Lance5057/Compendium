package com.lance5057.compendium.index.material.base.textile;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.components.block.IndexEntryComponent;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.material.base.MaterialTypeSerializer;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.base.gem.locations.SpecialLocationsGem;
import com.lance5057.compendium.index.material.base.textile.locations.SpecialLocationsTextile;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

public class MaterialTextile extends _MaterialBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2331511780363081257L;
	public final CompendiumBlockHandler BLOCK;
	public final CompendiumItemHandler STRING;
	public final CompendiumBlockHandler CARPET;

	public SpecialLocationsGem specialLocations;

	public MaterialTextile(String name, String namespace) {
		this(name, namespace, null);
	}

	public MaterialTextile(String name, String namespace, SpecialLocationsGem loc) {
		super(name, namespace);

		this.BLOCKS.add(BLOCK = new CompendiumBlockHandler());
		this.ITEMS.add(STRING = new CompendiumItemHandler());
		this.BLOCKS.add(CARPET = new CompendiumBlockHandler());
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setup() {
		setupBlock();
		setupString();
		setupCarpet();

	}

	private void setupCarpet() {
		CARPET.setName(name + "_carpet");
		CARPET.setup(this, () -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET)));

		CARPET.setupItemTag(TagUtil.neoTag("carpet/" + name));
		CARPET.setupItemTag(ItemTags.WOOL_CARPETS);
		CARPET.setupBlockTag(ResourceLocation.fromNamespaceAndPath("farmersdelight", "mineable/knife"));
	}

	private void setupString() {
		STRING.setName(name + "_string");
		STRING.setup(this);
	}

	private void setupBlock() {
		BLOCK.setName(name + "_block");
		BLOCK.setup(this, () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL)));

		BLOCK.setupItemTag(CompendiumTags.TEXTILES);
		BLOCK.setupItemTag(TagUtil.neoTag("textiles/" + name));
	}

//	@Override
//	public void tab(Output output) {
//		BLOCK.tab(this, output);
//		STRING.tab(this, output);
//		CARPET.tab(this, output);
//	}

//	@Override
//	public void blockStateModel(BlockStateProvider bsp) {
//		if (BLOCK.shouldGenerate()) {
//			bsp.getVariantBuilder(BLOCK.BLOCK.get()).partialState()
//					.addModels(new ConfiguredModel(bsp.models().cubeAll(this.blockFolder() + BLOCK.name,
//							ResourceLocation.fromNamespaceAndPath(namespace, "block/" + this.name))));
//		}
//
//		if (CARPET.shouldGenerate()) {
//			bsp.getVariantBuilder(CARPET.BLOCK.get()).partialState()
//					.addModels(new ConfiguredModel(bsp.models().carpet(this.blockFolder() + CARPET.name,
//							ResourceLocation.fromNamespaceAndPath(namespace, "block/" + this.name))));
//		}
//		this.extensions.forEach(i -> i.blockStateModel(this, bsp));
//	}
//
//	@Override
//	public void itemModel(ItemModelProvider tmp) {
//		if (BLOCK.shouldGenerate())
//			tmp.getBuilder(BLOCK.BLOCK_ITEM.getId().getPath()).parent(new ModelFile.UncheckedModelFile(
//					ResourceLocation.fromNamespaceAndPath(namespace, this.blockFolder() + BLOCK.name)));
//
//		if (CARPET.shouldGenerate())
//			tmp.getBuilder(CARPET.BLOCK_ITEM.getId().getPath()).parent(new ModelFile.UncheckedModelFile(
//					ResourceLocation.fromNamespaceAndPath(namespace, this.blockFolder() + CARPET.name)));
//
//		this.extensions.forEach(i -> i.itemModel(this, tmp));
//	}

	@Override
	public void engLoc(LanguageProvider lp) {
		StringBuilder locName = new StringBuilder();
		for (String word : this.name.split("_")) {
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			locName.append(word).append(" ");
		}
		lp.add("compendium.tooltip.material." + this.name, locName.toString());

		this.extensions.forEach(i -> i.engLoc(this, lp));
	}

	@Override
	public void recipes(RecipeOutput consumer) {
		if (CARPET.shouldGenerate()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, CARPET.BLOCK_ITEM, 3).define('p', BLOCK.BLOCK_ITEM)
					.pattern("pp")
					.unlockedBy("block", CriteriaTriggers.INVENTORY_CHANGED
							.createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(),
									InventoryChangeTrigger.TriggerInstance.Slots.ANY,
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

//	@Override
//	public void setupItemTags(ItemTagsProvider itp) {
//		BLOCK.itemTag(itp);
//		CARPET.itemTag(itp);
//		STRING.itemTag(itp);
//
//		this.extensions.forEach(i -> i.setupItemTags(this, itp));
//	}
//
//	@Override
//	public void setupBlockTags(BlockTagsProvider btp) {
//		BLOCK.blockTag(btp);
//		CARPET.blockTag(btp);
//		this.extensions.forEach(i -> i.setupBlockTags(this, btp));
//	}
//
//	@Override
//	public void setupClient(FMLClientSetupEvent event) {
//		// TODO Auto-generated method stub
//
//	}

	@Override
	public Ingredient getBaseItem() {
		return Ingredient.of(this.BLOCK.BLOCK_ITEM.get());
	}

	@Override
	public MATERIAL_TYPES getType() {
		return MATERIAL_TYPES.TEXTILE;
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

			SpecialLocationsTextile sp = null;

			if (j.get("specialLocations") != null)
				sp = context.deserialize(j.get("specialLocations"), SpecialLocationsTextile.class);

			w = new MaterialTextile(name, tagNamespace);

			if (j.has("block"))
				w.BLOCK.deserialize(j.get("block").getAsJsonObject());
			if (j.has("string"))
				w.STRING.deserialize(j.get("string").getAsJsonObject());
			if (j.has("carpet"))
				w.CARPET.deserialize(j.get("carpet").getAsJsonObject());

			JsonArray extensionsArray = j.getAsJsonArray("extensions");

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

			j.add("block", src.BLOCK.serialize());
			j.add("string", src.STRING.serialize());
			j.add("carpet", src.CARPET.serialize());

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

//	@Override
//	public boolean isIndexItem(ItemStack stack) {
//		if (BLOCK.is(stack))
//			return true;
//		if (STRING.is(stack))
//			return true;
//
//		for (_MaterialExtension m : extensions) {
//			boolean o = m.isIndexItem(this, stack);
//
//			if (o)
//				return o;
//		}
//		return false;
//	}

//	@Override
//	public Optional<IIndexEntry> getEntryItemBelongsTo(ItemStack stack) {
//		if (BLOCK.is(stack))
//			return Optional.of(this);
//		if (STRING.is(stack))
//			return Optional.of(this);
//
//		for (_MaterialExtension m : extensions) {
//			Optional<IIndexEntry> o = m.getEntryItemBelongsTo(this, stack);
//
//			if (o.isPresent())
//				return o;
//		}
//		return Optional.empty();
//	}

//	@Override
//	public ItemStack breakDownItem(Ingredient ingredient) {
//		// TODO Auto-generated method stub
//		return ItemStack.EMPTY;
//	}
//
//	@Override
//	public ItemStack buildUpItem(Ingredient ingredient) {
//		// TODO Auto-generated method stub
//		return ItemStack.EMPTY;
//	}

	@Override
	public void attachComponents(ModifyDefaultComponentsEvent event) {
		if (BLOCK.isNotIgnored())
			event.modify(BLOCK.BLOCK_ITEM.get(),
					builder -> builder.set(CompendiumComponents.INDEX.get(), new IndexEntryComponent(getType(), name)));

		if (STRING.isNotIgnored())
			event.modify(STRING.ITEM.get(),
					builder -> builder.set(CompendiumComponents.INDEX.get(), new IndexEntryComponent(getType(), name)));

		if (CARPET.isNotIgnored())
			event.modify(CARPET.BLOCK_ITEM.get(),
					builder -> builder.set(CompendiumComponents.INDEX.get(), new IndexEntryComponent(getType(), name)));

		this.extensions.forEach(i -> i.attachComponents(this, event));
	}

}
