package com.lance5057.compendium.index.material.base.glass;

import java.lang.reflect.Type;

import javax.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.annotations.Since;
import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.components.block.IndexEntryComponent;
import com.lance5057.compendium.index.CompendiumIndex.Generate;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.material.base.MaterialTypeSerializer;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.base.glass.locations.SpecialLocationsGlass;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

public class MaterialGlass extends _MaterialBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8859553079700017238L;
	public CompendiumBlockHandler BLOCK = new CompendiumBlockHandler("glass");

	@Nullable
	@Since(1.1)
	public SpecialLocationsGlass specialLocations;

	public MaterialGlass(String name, String tagNamespace) {
		this(name, tagNamespace, null);
	}

	public MaterialGlass(String name, String tagNamespace, SpecialLocationsGlass locations) {
		super(name, tagNamespace);

		this.specialLocations = locations;

		BLOCK.setup(this);
	}

	@Override
	public void setup() {

		setupBlock();

		this.extensions.forEach(i -> i.setup(this));
	}

	private void setupBlock() {
		
	}

	@Override
	public void tab(Output output) {
		BLOCK.tab(this, output);
	}

//	@Override
//	public void blockStateModel(BlockStateProvider bsp) {
//		if (BLOCK.shouldGenerate())
//			DataUtil.basicMaterialBlock(bsp, this.BLOCK.BLOCK.get(), name, "", "transparent", this.getType());
//
//		this.extensions.forEach(i -> i.blockStateModel(this, bsp));
//	}
//
//	@Override
//	public void itemModel(ItemModelProvider tmp) {
//		if (BLOCK.shouldGenerate())
//			DataUtil.basicMaterialBlockItem(tmp, BLOCK.BLOCK_ITEM, name, this.getType());
//	}

	@Override
	public void engLoc(LanguageProvider lp) {
		StringBuilder locName = new StringBuilder();
		for (String word : this.name.split("_")) {
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			locName.append(word).append(" ");
		}
//		locName.append("Glass");
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

//	@Override
//	public void setupItemTags(ItemTagsProvider itp) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void setupBlockTags(BlockTagsProvider itp) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void setupClient(FMLClientSetupEvent event) {
//		this.extensions.forEach(i -> i.setupClient(this, event));
//	}

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
			MaterialGlass g = null;
			String name = j.get("name").getAsString();
			String tagNamespace = j.get("tagNamespace").getAsString();

			String block = j.get("loadBlock").getAsString();

			if (j.get("version") != null) {
				Double version = j.get("version").getAsDouble();
				if (version >= 1.1) {
					SpecialLocationsGlass sp = null;
					if (j.get("specialLocations") != null)
						sp = context.deserialize(j.get("specialLocations"), SpecialLocationsGlass.class);

					g = new MaterialGlass(name, tagNamespace, sp);
				}

			} else
				g = new MaterialGlass(name, tagNamespace);

			JsonArray extensionsArray = j.getAsJsonArray("extensions");

			if (extensionsArray != null)
				for (JsonElement extensionElement : extensionsArray) {
					g.addExtension(context.deserialize(extensionElement, _MaterialExtension.class));
				}

			return g;
		}

		@Override
		public JsonElement serialize(MaterialGlass src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("name", src.name);
			j.addProperty("tagNamespace", src.namespace);
			j.addProperty("type", type);
			j.addProperty("loadBlock", src.BLOCK.getGeneration().toString());

			if (src.specialLocations != null) {
				j.add("specialLocations", context.serialize(src.specialLocations, SpecialLocationsGlass.class));
			}

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

//	@Override
//	public boolean isIndexItem(ItemStack stack) {
//		if (BLOCK.is(stack))
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

		this.extensions.forEach(i -> i.attachComponents(this, event));
	}

}
