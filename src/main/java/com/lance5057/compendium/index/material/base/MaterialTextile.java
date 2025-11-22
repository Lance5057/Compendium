package com.lance5057.compendium.index.material.base;

import java.lang.reflect.Type;
import java.util.Optional;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.data.IndexBlockModelProvider;
import com.lance5057.compendium.index.CompendiumIndex.Generate;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.index.util.CompendiumItemHandler;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class MaterialTextile extends _MaterialBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2331511780363081257L;
	public CompendiumBlockHandler BLOCK = new CompendiumBlockHandler("block");
	public CompendiumItemHandler STRING = new CompendiumItemHandler("string");

	public MaterialTextile(String name, String tagNamespace, Generate block, Generate string) {
		super(name, tagNamespace);

		BLOCK.setGenerate(block);
		STRING.setGenerate(string);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setup() {

		BLOCK.setup(this, () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL)), namespace, name,
				ResourceLocation.fromNamespaceAndPath(namespace, this.name + "_block"),
				ResourceLocation.fromNamespaceAndPath(namespace, this.name + "_block"));

		STRING.setup(this, namespace, name, ResourceLocation.fromNamespaceAndPath(namespace, this.name + "_string"));

	}

	@Override
	public void tab(Output output) {
		BLOCK.tab(this, output);
		STRING.tab(this, output);
	}

	@Override
	public void blockStateModel(BlockStateProvider bsp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemModel(ItemModelProvider tmp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engLoc(LanguageProvider lp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recipes(RecipeOutput consumer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockLoot(BlockLootSubProvider blp) {
		// TODO Auto-generated method stub

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
			JsonObject j = json.getAsJsonObject();

			String name = j.get("name").getAsString();
			String tagNamespace = j.get("tagNamespace").getAsString();

			String string = j.get("loadString").getAsString();
			String block = j.get("loadBlock").getAsString();

			JsonArray extensionsArray = j.getAsJsonArray("extensions");

			MaterialTextile m = new MaterialTextile(name, tagNamespace, Generate.valueOf(string),
					Generate.valueOf(block));

			if (extensionsArray != null)
				for (JsonElement extensionElement : extensionsArray) {
					m.addExtension(context.deserialize(extensionElement, _MaterialExtension.class));
				}

			return m;
		}

		@Override
		public JsonElement serialize(MaterialTextile src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("name", src.name);
			j.addProperty("tagNamespace", src.namespace);
			j.addProperty("type", type);
			j.addProperty("loadString", src.STRING.getGeneration().toString());
			j.addProperty("loadBlock", src.BLOCK.getGeneration().toString());

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
		if (stack.getItem() == BLOCK.BLOCK_ITEM.asItem())
			return true;
		if (stack.getItem() == STRING.ITEM.asItem())
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
		if (stack.getItem() == BLOCK.BLOCK_ITEM.asItem())
			return Optional.of(this);
		if (stack.getItem() == STRING.ITEM.asItem())
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
