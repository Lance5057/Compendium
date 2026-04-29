package com.lance5057.compendium.index.material.base.gem;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.material.base.MaterialTypeSerializer;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.base.gem.locations.SpecialLocationsGem;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.index.util.CompendiumItemHandler;
import com.lance5057.compendium.util.TagUtil;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class MaterialGem extends _MaterialBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6098268951493240388L;

	public CompendiumItemHandler GEM;
	public CompendiumItemHandler SHARD;
	public CompendiumBlockHandler BLOCK;

	public SpecialLocationsGem specialLocations;

	public MaterialGem(String name, String namespace) {
		this(name, namespace, null);
	}

	public MaterialGem(String name, String namespace, SpecialLocationsGem loc) {
		super(name, namespace);

		this.ITEMS.add(GEM = new CompendiumItemHandler());
		this.ITEMS.add(SHARD = new CompendiumItemHandler());
		this.BLOCKS.add(BLOCK = new CompendiumBlockHandler());

		specialLocations = loc;
	}

	@Override
	public void setup() {
		BLOCK.setName(name + "_block");
		BLOCK.setup(this);
		BLOCK.setupItemTag(Tags.Items.STORAGE_BLOCKS);
		BLOCK.setupItemTag(TagUtil.neoTag("storage_blocks/" + name));

		GEM.setName(name + "_gem");
		GEM.setup(this);
		GEM.setupItemTag(Tags.Items.GEMS);
		GEM.setupItemTag(TagUtil.neoTag("gems/" + name));

		SHARD.setName(name + "_shard");
		SHARD.setup(this);
		SHARD.setupItemTag(CompendiumTags.GEM_SHARD);
		SHARD.setupItemTag(TagUtil.neoTag("gems/" + name));

	}

//	@Override
//	public void blockStateModel(BlockStateProvider bsp) {
//		if (this.loadStorageBlock)
//			bsp.simpleBlock(this.BLOCK.get());
//	}
//
//	@Override
//	public void itemModel(ItemModelProvider tmp) {
//		if (this.loadShard)
//			DataUtil.basicMaterialItem(tmp, this.SHARD.get(), this, "shard", this.getType());
//		if (this.loadGem)
//			DataUtil.basicMaterialItem(tmp, this.GEM.get(), this, "gem", this.getType());
//		if (this.loadStorageBlock)
//			DataUtil.basicMaterialBlockItem(tmp, BLOCK_ITEM, name, this.getType());
//	}

	@Override
	public void engLoc(LanguageProvider lp) {
		StringBuilder locName = new StringBuilder();
		for (String word : this.name.split("_")) {
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			locName.append(word).append(" ");
		}
		lp.add("compendium.tooltip.material." + this.name, locName.toString());

		if (SHARD.shouldGenerate())
			lp.add(this.SHARD.ITEM.get(), locName + " Shard");
		if (GEM.shouldGenerate())
			lp.add(this.GEM.ITEM.get(), locName + "");
		if (BLOCK.shouldGenerate())
			lp.add(this.BLOCK.BLOCK_ITEM.get(), locName + " Block");

		this.extensions.forEach(i -> i.engLoc(this, lp));

	}

	@Override
	public void recipes(RecipeOutput consumer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockLoot(BlockLootSubProvider blp) {

	}

	public static class Serializer extends MaterialTypeSerializer<MaterialGem> {

		public Serializer() {
			super("GEM");
		}

		@Override
		public MaterialGem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			String name = j.get("name").getAsString();
			String tagNamespace = j.get("tagNamespace").getAsString();

			SpecialLocationsGem sp = null;
			if (j.get("specialLocations") != null)
				sp = context.deserialize(j.get("specialLocations"), SpecialLocationsGem.class);

			MaterialGem g = new MaterialGem(name, tagNamespace, sp);

			g.BLOCK.deserialize(j.get("block").getAsJsonObject());
			g.SHARD.deserialize(j.get("shard").getAsJsonObject());
			g.GEM.deserialize(j.get("gem").getAsJsonObject());

			JsonArray extensionsArray = j.getAsJsonArray("extensions");

			if (extensionsArray != null)
				for (JsonElement extensionElement : extensionsArray) {
					g.addExtension(context.deserialize(extensionElement, _MaterialExtension.class));
				}

			return g;
		}

		@Override
		public JsonElement serialize(MaterialGem src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("name", src.name);
			j.addProperty("tagNamespace", src.namespace);
			j.addProperty("type", type);

			j.add("block", src.BLOCK.serialize());
			j.add("gem", src.GEM.serialize());
			j.add("shard", src.SHARD.serialize());

			JsonArray ext = new JsonArray();

			for (_MaterialExtension e : src.extensions)
				ext.add(context.serialize(e));

			j.add("extensions", ext);

			return j;
		}

	}

	@Override
	public Ingredient getBaseItem() {
		return Ingredient.of(GEM.ITEM);
	}

	@Override
	public MATERIAL_TYPES getType() {
		return MATERIAL_TYPES.GEM;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void otherLoot(LootTableSubProvider lsp) {
		// TODO Auto-generated method stub

	}

}
