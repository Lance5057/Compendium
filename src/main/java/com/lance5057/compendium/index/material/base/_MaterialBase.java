package com.lance5057.compendium.index.material.base;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.index.material.MaterialTypeRegistry;
import com.lance5057.compendium.index.material.extentions._MaterialExtension;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.SimpleTier;

public abstract class _MaterialBase implements IIndexEntry {
//	public final String TYPE;
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

	public _MaterialBase(String name) {

		this.name = name;

		extensions = new ArrayList<_MaterialExtension>();
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
