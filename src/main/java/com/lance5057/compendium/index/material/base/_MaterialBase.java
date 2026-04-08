package com.lance5057.compendium.index.material.base;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.index.material.MaterialTypeRegistry;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.index.util.CompendiumItemHandler;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

public abstract class _MaterialBase implements IIndexEntry, Serializable {
	public final Set<CompendiumItemHandler> ITEMS;
	public final Set<CompendiumBlockHandler> BLOCKS;
	/**
	 * 
	 */
	private static final long serialVersionUID = 7616650773040539965L;
	// public final String TYPE;
	public String namespace;
	public String name;

	protected String premadeTier;
	protected int level;
	protected int uses;
	protected float speed;
	protected float damage;
	protected int enchantmentValue;
	protected String useTag;
	protected String repairTag;

	public TagKey<Block> useBlockTag;

	public Tier tier;

	public List<_MaterialExtension> extensions;

	public _MaterialBase(String name, String namespace) {

		this.name = name;
		this.namespace = namespace;

		extensions = new ArrayList<_MaterialExtension>();

		ITEMS = new HashSet<CompendiumItemHandler>();
		BLOCKS = new HashSet<CompendiumBlockHandler>();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof _MaterialBase m) {
			if (m.name.compareTo(this.name) == 0)
				return true;
		}
		return false;
	}

	public abstract Ingredient getBaseItem();

	public _MaterialBase addExtension(_MaterialExtension x) {
		extensions.add(x);
		return this;
	}

	public _MaterialBase setupTier(String tier) {
		this.premadeTier = tier;
		return this;
	}

	public _MaterialBase setupTier(int level, int uses, float speed, float damage, int enchantmentValue, String useTag,
			String repairTag) {
		this.level = level;
		this.uses = uses;
		this.speed = speed;
		this.damage = damage;
		this.enchantmentValue = enchantmentValue;
		this.useTag = useTag;
		this.repairTag = repairTag;

		return this;
	}

	public String blockFolder() {
		return "block/material/" + this.getType().toString().toLowerCase() + "/" + this.getName() + "/";
	}

	public String itemFolder() {
		return "item/material/" + this.getType().toString().toLowerCase() + "/" + this.getName() + "/";
	}

	public String extraFolder() {
		return "extra/material/" + this.getType().toString().toLowerCase() + "/" + this.getName() + "/";
	}

	public abstract CompendiumIndex.MATERIAL_TYPES getType();

	public abstract void attachComponents(ModifyDefaultComponentsEvent event);

	protected ResourceLocation fileLoc(ResourceLocation standardLoc, ResourceLocation exists) {
		if (exists != null) {
			return exists;
		}

		return standardLoc;
	}

	@Override
	public void tab(Output output) {
		this.BLOCKS.forEach(i -> i.tab(this, output));
		this.ITEMS.forEach(i -> i.tab(this, output));
		this.extensions.forEach(i -> i.tab(this, output));
	}

	@Override
	public void blockLoot(BlockLootSubProvider blp) {
		this.BLOCKS.forEach(i -> i.blockLoot(this, blp));
		this.extensions.forEach(i -> i.blockLoot(this, blp));
	}

	public static class Serializer extends MaterialTypeSerializer<_MaterialBase> {

		public Serializer() {
			super("BASE");
			// TODO Auto-generated constructor stub
		}

		@Override
		public _MaterialBase deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			String type = j.get("type").getAsString();

			return MaterialTypeRegistry.getTypeSerializer(type).deserialize(json, typeOfT, context);
		}

		@Override
		public JsonElement serialize(_MaterialBase src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject obj = new JsonObject();
			obj.addProperty("type", type);

			context.serialize(src);
			return obj;
		}

	}
}
