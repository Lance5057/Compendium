package com.lance5057.compendium.index.material.base;

import java.lang.reflect.Type;
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
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.index.util.DataUtil;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class MaterialGlass extends _MaterialBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8859553079700017238L;
	public CompendiumBlockHandler BLOCK = new CompendiumBlockHandler("glass");

	public MaterialGlass(String name, String tagNamespace) {
		super(name, tagNamespace);
		// TODO Auto-generated constructor stub
	}

	public MaterialGlass(String name, String tagNamespace, Generate block) {
		super(name, tagNamespace);

		BLOCK.setGenerate(block);
//		loadBlock = block;
//
//		blockItemTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "glass/pane/" + name));
//		blockTag = BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", "glass/pane/" + name));
//		blockItemTag = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "glass/" + name));
//		blockTag = BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", "glass/" + name));

	}

	@Override
	public void setup() {
		BLOCK.setup(this, ResourceLocation.fromNamespaceAndPath(namespace, this.name + "_glass"),
				ResourceLocation.fromNamespaceAndPath(namespace, this.name + "_glass"));

		this.extensions.forEach(i -> i.setup(this));
	}

	@Override
	public void tab(Output output) {
		BLOCK.tab(this, output);
	}

	@Override
	public void blockStateModel(BlockStateProvider bsp) {
		if (BLOCK.shouldGenerate())
			DataUtil.basicMaterialBlock(bsp, this.BLOCK.BLOCK.get(), name, "", "transparent", this.getType());

		this.extensions.forEach(i -> i.blockStateModel(this, bsp));
	}

	@Override
	public void itemModel(ItemModelProvider tmp) {
		if (BLOCK.shouldGenerate())
			DataUtil.basicMaterialBlockItem(tmp, BLOCK.BLOCK_ITEM, name, this.getType());
	}

	@Override
	public void engLoc(LanguageProvider lp) {
		StringBuilder locName = new StringBuilder();
		for (String word : this.name.split("_")) {
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			locName.append(word).append(" ");
		}
		locName.append("Glass");
		lp.add("compendium.tooltip.material." + this.name, locName.toString());

		if (BLOCK.shouldGenerate()) {
			lp.add(this.BLOCK.BLOCK.get(), locName + " Block");
			lp.add(this.BLOCK.BLOCK_ITEM.get(), locName + " Block");
		}

		this.extensions.forEach(i -> i.engLoc(this, lp));
	}

	@Override
	public void recipes(RecipeOutput consumer) {

		this.extensions.forEach(i -> i.recipes(this, consumer));
	}

	@Override
	public void blockLoot(BlockLootSubProvider blp) {
		if (BLOCK.shouldGenerate())
			blp.dropSelf(this.BLOCK.BLOCK.get());

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
		return Ingredient.of(this.BLOCK.BLOCK_ITEM.get());
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
			String tagNamespace = j.get("tagNamespace").getAsString();

			String block = j.get("loadBlock").getAsString();

			JsonArray extensionsArray = j.getAsJsonArray("extensions");

			MaterialGlass m = new MaterialGlass(name, tagNamespace, Generate.valueOf(block));

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
			j.addProperty("tagNamespace", src.namespace);
			j.addProperty("type", type);
			j.addProperty("loadBlock", src.BLOCK.getGeneration().toString());

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
	public void otherLoot(LootTableSubProvider lsp) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isIndexItem(ItemStack stack) {
		if (BLOCK.is(stack))
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
