package com.lance5057.compendium.index.material.base.metal;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.index.CompendiumIndex.Generate;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.index.material.base.MaterialTypeSerializer;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.index.util.CompendiumItemHandler;
import com.lance5057.compendium.index.util.DataUtil;
import com.lance5057.compendium.util.TagUtil;
import com.lance5057.compendium.workstations.scrappingtable.ScrappingUtils;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.CompoundIngredient;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class MaterialMetal extends _MaterialBase {

	private static final long serialVersionUID = -7314131020121747496L;
	public CompendiumItemHandler INGOT = new CompendiumItemHandler("ingot");
	public CompendiumItemHandler NUGGET = new CompendiumItemHandler("nugget");
	public CompendiumBlockHandler BLOCK = new CompendiumBlockHandler("storage_block");

	public MaterialMetal(String name, String tagNamespace, Generate ingot, Generate block, Generate nugget) {
		super(name, tagNamespace);

		INGOT.setGenerate(ingot);
		BLOCK.setGenerate(block);
		NUGGET.setGenerate(nugget);
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

			List<Ingredient> ing = new ArrayList<Ingredient>();
			for (TagKey<Item> i : INGOT.itemTag)
				ing.add(Ingredient.of(i));

			tier = new SimpleTier(useBlockTag, uses, speed, damage, enchantmentValue,
					() -> CompoundIngredient.of(ing.toArray(new Ingredient[0])));
		}

		INGOT.setup(this, ResourceLocation.fromNamespaceAndPath(namespace, this.name + "_ingot"));
		INGOT.setupItemTag(Tags.Items.INGOTS);
		INGOT.setupItemTag(TagUtil.neoTag("ingots/" + name));

		NUGGET.setup(this, ResourceLocation.fromNamespaceAndPath(namespace, this.name + "_nugget"));
		NUGGET.setupItemTag(Tags.Items.NUGGETS);
		NUGGET.setupItemTag(TagUtil.neoTag("nuggets/" + name));

		BLOCK.setup(this, ResourceLocation.fromNamespaceAndPath(namespace, this.name + "_block"),
				ResourceLocation.fromNamespaceAndPath(namespace, this.name + "_block"));
		BLOCK.setupItemTag(Tags.Items.STORAGE_BLOCKS);
		BLOCK.setupItemTag(TagUtil.neoTag("storage_blocks/" + name));
		BLOCK.setupBlockTag(BlockTags.MINEABLE_WITH_PICKAXE);

		this.extensions.forEach(i -> i.setup(this));

	}

	@Override
	public void blockStateModel(BlockStateProvider bsp) {
		if (BLOCK.shouldGenerate())
			DataUtil.basicMaterialBlock(bsp, this.BLOCK.BLOCK.get(), name, this.getType());

		this.extensions.forEach(i -> i.blockStateModel(this, bsp));
	}

	@Override
	public void itemModel(ItemModelProvider tmp) {
		if (NUGGET.shouldGenerate())
			DataUtil.basicMaterialItem(tmp, this.NUGGET.ITEM.get(), this, "nugget", this.getType());
		if (INGOT.shouldGenerate())
			DataUtil.basicMaterialItem(tmp, this.INGOT.ITEM.get(), this, "ingot", this.getType());
		if (BLOCK.shouldGenerate())
			DataUtil.basicMaterialBlockItem(tmp, BLOCK.BLOCK_ITEM, name, this.getType());

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

		if (NUGGET.shouldGenerate())
			lp.add(this.NUGGET.ITEM.get(), locName + " Nugget");
		if (INGOT.shouldGenerate())
			lp.add(this.INGOT.ITEM.get(), locName + " Ingot");
		if (BLOCK.shouldGenerate())
			lp.add(this.BLOCK.BLOCK_ITEM.get(), locName + " Block");

		this.extensions.forEach(i -> i.engLoc(this, lp));
	}

	@Override
	public void recipes(RecipeOutput consumer) {
		// TODO Auto-generated method stub

		this.extensions.forEach(i -> i.recipes(this, consumer));
	}

	@Override
	public void blockLoot(BlockLootSubProvider blp) {
		if (BLOCK.shouldGenerate())
			blp.dropSelf(this.BLOCK.BLOCK.get());

		this.extensions.forEach(i -> i.blockLoot(this, blp));
	}

	@Override
	public void tab(Output output) {
		INGOT.tab(this, output);
		NUGGET.tab(this, output);
		BLOCK.tab(this, output);

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
			String tagNamespace = j.get("tagNamespace").getAsString();

			String ingot = j.get("loadIngot").getAsString();
			String block = j.get("loadStorageBlock").getAsString();
			String nugget = j.get("loadNugget").getAsString();

			String tier = j.get("tier").getAsString();

			int level = j.get("level").getAsInt();
			int uses = j.get("uses").getAsInt();
			float speed = j.get("speed").getAsFloat();
			float damage = j.get("damage").getAsFloat();
			int enchantmentValue = j.get("enchantmentValue").getAsInt();
			String useTag = j.get("useTag").getAsString();
			String repairTag = j.get("repairTag").getAsString();

			JsonArray extensionsArray = j.getAsJsonArray("extensions");

			MaterialMetal m = new MaterialMetal(name, tagNamespace, Generate.valueOf(ingot), Generate.valueOf(block),
					Generate.valueOf(nugget));

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
			j.addProperty("tagNamespace", src.namespace);

			j.addProperty("type", type);
			j.addProperty("loadIngot", src.INGOT.getGeneration().toString());
			j.addProperty("loadStorageBlock", src.BLOCK.getGeneration().toString());
			j.addProperty("loadNugget", src.NUGGET.getGeneration().toString());

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
		return Ingredient.of(this.INGOT.ITEM.get());
	}

	@Override
	public MATERIAL_TYPES getType() {
		return MATERIAL_TYPES.METAL;
	}

	@Override
	public void otherLoot(LootTableSubProvider lsp) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isIndexItem(ItemStack stack) {
		if (BLOCK.is(stack))
			return true;
		if (INGOT.is(stack))
			return true;
		if (NUGGET.is(stack))
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
		if (INGOT.is(stack))
			return Optional.of(this);
		if (NUGGET.is(stack))
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
//		if (!BLOCK.isIgnored())
//			i = ScrappingUtils.convertBasedOnStack(ingredient, BLOCK.BLOCK_ITEM.get(), INGOT.ITEM.get(), 9);
//		else
//			i = ScrappingUtils.convertBasedOnTag(ingredient, BLOCK.itemTag, INGOT.itemTag, 9);
//		if (i.isEmpty()) {
//			if (!INGOT.isIgnored())
//				i = ScrappingUtils.convertBasedOnStack(ingredient, INGOT.ITEM.get(), NUGGET.ITEM.get(), 9);
//			else
//				i = ScrappingUtils.convertBasedOnTag(ingredient, INGOT.itemTag, NUGGET.itemTag, 9);
//		}
		return i;
	}

	@Override
	public ItemStack buildUpItem(Ingredient ingredient) {
		ItemStack i = ItemStack.EMPTY;
		if (!BLOCK.isIgnored())
			i = ScrappingUtils.convertBasedOnStack(ingredient, INGOT.ITEM.get(), BLOCK.BLOCK_ITEM.get(), 1);
		else
			i = ScrappingUtils.convertBasedOnTag(ingredient, INGOT.itemTag, BLOCK.itemTag, 1);
		if (i.isEmpty()) {
			if (!INGOT.isIgnored())
				i = ScrappingUtils.convertBasedOnStack(ingredient, NUGGET.ITEM.get(), INGOT.ITEM.get(), 1);
			else
				i = ScrappingUtils.convertBasedOnTag(ingredient, NUGGET.itemTag, INGOT.itemTag, 1);
		}
		return i;
	}
}
