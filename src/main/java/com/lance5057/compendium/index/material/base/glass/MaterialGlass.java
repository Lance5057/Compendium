package com.lance5057.compendium.index.material.base.glass;

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
import com.lance5057.compendium.index.material.base.MaterialTypeSerializer;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.base.glass.locations.SpecialLocationsGlass;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.util.TagUtil;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class MaterialGlass extends _MaterialBase {

	private static final long serialVersionUID = 8859553079700017238L;
	public CompendiumBlockHandler BLOCK = new CompendiumBlockHandler();

	public SpecialLocationsGlass specialLocations;

	public MaterialGlass(String name, String namespace) {
		this(name, namespace, null);
	}

	public MaterialGlass(String name, String namespace, SpecialLocationsGlass loc) {
		super(name, namespace);

		this.BLOCKS.add(BLOCK = new CompendiumBlockHandler());

		specialLocations = loc;
	}

	@Override
	public void setup() {
		BLOCK.setName(name + "_block");
		BLOCK.setup(this, () -> new Block(Block.Properties.ofFullCopy(Blocks.GLASS)),
				() -> new BlockItem(BLOCK.BLOCK.get(), new Item.Properties().component(CompendiumComponents.INDEX,
						new IndexEntryComponent(this.getType(), this.name))));
		BLOCK.setupItemTag(Tags.Items.GLASS_BLOCKS);
		BLOCK.setupItemTag(TagUtil.neoTag("glass_blocks/" + name));

		this.extensions.forEach(i -> i.setup(this));
	}

	@Override
	public void tab(Output output) {
		BLOCK.tab(this, output);
	}

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

			SpecialLocationsGlass sp = null;
			if (j.get("specialLocations") != null)
				sp = context.deserialize(j.get("specialLocations"), SpecialLocationsGlass.class);

			g = new MaterialGlass(name, tagNamespace, sp);

			if (j.has("block"))
				g.BLOCK.deserialize(j.get("block").getAsJsonObject());

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

			j.add("block", src.BLOCK.serialize());

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
//
//	@Override
//	public void attachComponents(ModifyDefaultComponentsEvent event) {
//		if (BLOCK.isNotIgnored())
//			event.modify(BLOCK.BLOCK_ITEM.get(),
//					builder -> builder.set(CompendiumComponents.INDEX.get(), new IndexEntryComponent(getType(), name)));
//
//		this.extensions.forEach(i -> i.attachComponents(this, event));
//	}

}
