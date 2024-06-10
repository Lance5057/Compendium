package com.lance5057.compendium.index.material.extentions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.util.DataUtil;
import com.lance5057.compendium.items.CompendiumArmorItem;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredItem;

public class ExtensionArmor extends _MaterialExtension {
	boolean loadArmor;
	boolean loadShield;

	int helmDefense;
	int chestDefense;
	int legDefense;
	int bootDefense;
	int toughness;
	int enchantability;
	float knockbackResistance;
	int durabilityMultiplier;

	ArmorMaterial MATERIAL;

	public DeferredItem<Item> HELM;
	public DeferredItem<Item> CHESTPLATE;
	public DeferredItem<Item> LEGGINGS;
	public DeferredItem<Item> BOOTS;
	public DeferredItem<Item> SHIELD;

	public ExtensionArmor(boolean loadArmor, boolean loadShield, int helm, int chest, int leg, int boot, int toughness,
			int enchant, float knockback, int durability) {
		this.loadArmor = loadArmor;
		this.loadShield = loadShield;

		this.helmDefense = helm;
		this.chestDefense = chest;
		this.legDefense = leg;
		this.bootDefense = boot;

		this.toughness = toughness;
		this.enchantability = enchant;
		this.knockbackResistance = knockback;
		this.durabilityMultiplier = durability;
	}

	@Override
	public void setup(_MaterialBase base) {
		if (this.loadArmor) {
			if (this.loadArmor) {
				MATERIAL = new ArmorMaterial() {

					@Override
					public int getDurabilityForType(Type pType) {
						return switch (pType) {
						case HELMET -> 11 * durabilityMultiplier;
						case CHESTPLATE -> 16 * durabilityMultiplier;
						case LEGGINGS -> 15 * durabilityMultiplier;
						case BOOTS -> 13 * durabilityMultiplier;
						};
					}

					@Override
					public int getDefenseForType(Type pType) {
						return switch (pType) {
						case HELMET -> helmDefense;
						case CHESTPLATE -> chestDefense;
						case LEGGINGS -> legDefense;
						case BOOTS -> bootDefense;
						};
					}

					@Override
					public int getEnchantmentValue() {
						return enchantability;
					}

					@Override
					public SoundEvent getEquipSound() {
						return SoundEvents.ARMOR_EQUIP_IRON;
					}

					@Override
					public Ingredient getRepairIngredient() {
						return base.getBaseItem();
					}

					@Override
					public String getName() {
						return base.name;
					}

					@Override
					public float getToughness() {
						return toughness;
					}

					@Override
					public float getKnockbackResistance() {
						return knockbackResistance;
					}

				};
			}

			HELM = CompendiumIndex.ITEMS.register(base.name + "_helm",
					() -> new CompendiumArmorItem(MATERIAL, ArmorItem.Type.HELMET, new Item.Properties(),
							"textures/models/armor/material/" + base.name + "/" + base.name + "_helm.png"));
			CHESTPLATE = CompendiumIndex.ITEMS.register(base.name + "_chestplate",
					() -> new CompendiumArmorItem(MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
							"textures/models/armor/material/" + base.name + "/" + base.name + "_breastplate.png"));
			LEGGINGS = CompendiumIndex.ITEMS.register(base.name + "_leggings",
					() -> new CompendiumArmorItem(MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties(),
							"textures/models/armor/material/" + base.name + "/" + base.name + "_grieves.png"));
			BOOTS = CompendiumIndex.ITEMS.register(base.name + "_boots",
					() -> new CompendiumArmorItem(MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties(),
							"textures/models/armor/material/" + base.name + "/" + base.name + "_sabatons.png"));
		}
		if (this.loadShield)
			SHIELD = CompendiumIndex.ITEMS.register(base.name + "_shield", () -> new ShieldItem(new Item.Properties()));
	}

	@Override
	public void tab(_MaterialBase base, Output output) {
		if (this.loadArmor) {
			output.accept(this.HELM);
			output.accept(this.CHESTPLATE);
			output.accept(this.LEGGINGS);
			output.accept(this.BOOTS);
		}
		if (this.loadShield)
			output.accept(this.SHIELD);
	}

	@Override
	public void blockModel(_MaterialBase base, BlockStateProvider bsp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemModel(_MaterialBase base, ItemModelProvider tmp) {
		if (this.loadArmor) {
			DataUtil.basicMaterialItem(tmp, this.HELM.get(), base.name);
			DataUtil.basicMaterialItem(tmp, this.CHESTPLATE.get(), base.name);
			DataUtil.basicMaterialItem(tmp, this.LEGGINGS.get(), base.name);
			DataUtil.basicMaterialItem(tmp, this.BOOTS.get(), base.name);
		}
//		if (this.loadShield)
//			DataUtil.basicMaterialItem(tmp, this.SHIELD.get(), base.name);
	}

	@Override
	public void engLoc(_MaterialBase base, LanguageProvider lp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void recipes(_MaterialBase base, RecipeOutput consumer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockLoot(_MaterialBase base, BlockLootSubProvider blp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupClient(_MaterialBase base, FMLClientSetupEvent event) {
		// TODO Auto-generated method stub

	}

	public static class Serializer extends MaterialExtensionSerializer<ExtensionArmor> {

		public Serializer() {
			super("ARMOR");
		}

		@Override
		public JsonElement serialize(ExtensionArmor src, java.lang.reflect.Type typeOfSrc,
				JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("type", type);

			j.addProperty("loadArmor", src.loadArmor);
			j.addProperty("loadShield", src.loadShield);

			j.addProperty("helmDefense", src.helmDefense);
			j.addProperty("chestDefense", src.chestDefense);
			j.addProperty("legDefense", src.legDefense);
			j.addProperty("bootDefense", src.bootDefense);

			j.addProperty("toughness", src.toughness);
			j.addProperty("enchantability", src.enchantability);
			j.addProperty("knockbackResistance", src.knockbackResistance);
			j.addProperty("durabilityMultiplier", src.durabilityMultiplier);

			return j;
		}

		@Override
		public ExtensionArmor deserialize(JsonElement json, java.lang.reflect.Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			boolean armor = j.get("loadArmor").getAsBoolean();
			boolean shield = j.get("loadShield").getAsBoolean();

			int helm = j.get("helmDefense").getAsInt();
			int chest = j.get("chestDefense").getAsInt();
			int leg = j.get("legDefense").getAsInt();
			int boot = j.get("bootDefense").getAsInt();

			int tough = j.get("toughness").getAsInt();
			int enchant = j.get("enchantability").getAsInt();
			float knockback = j.get("knockbackResistance").getAsInt();
			int dur = j.get("durabilityMultiplier").getAsInt();

			return new ExtensionArmor(armor, shield, helm, chest, leg, boot, tough, enchant, knockback, dur);
		}

	}

}
