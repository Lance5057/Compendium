package com.lance5057.compendium.index.material.extensions;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.util.CompendiumItemHandler;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

public class ExtensionVanillaTools extends _MaterialExtension {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6058992077769136068L;

	public CompendiumItemHandler SWORD;
	public CompendiumItemHandler AXE;
	public CompendiumItemHandler SHOVEL;
	public CompendiumItemHandler HOE;
	public CompendiumItemHandler PICKAXE;

	public ExtensionVanillaTools() {
		this.ITEMS.add(SWORD = new CompendiumItemHandler());
		this.ITEMS.add(AXE = new CompendiumItemHandler());
		this.ITEMS.add(SHOVEL = new CompendiumItemHandler());
		this.ITEMS.add(PICKAXE = new CompendiumItemHandler());
		this.ITEMS.add(HOE = new CompendiumItemHandler());
	}

	@Override
	public void setup(_MaterialBase base) {
		AXE.setName(base.name + "_axe");
		AXE.setup(base, () -> new AxeItem(base.tier, new Item.Properties()));
		AXE.setupItemTag(ItemTags.VANISHING_ENCHANTABLE);
		AXE.setupItemTag(Tags.Items.MELEE_WEAPON_TOOLS);
		AXE.setupItemTag(Tags.Items.ENCHANTABLES);
		AXE.setupItemTag(ItemTags.AXES);
		AXE.setupItemTag(ItemTags.WEAPON_ENCHANTABLE);
		AXE.setupItemTag(ItemTags.DURABILITY_ENCHANTABLE);
		AXE.setupItemTag(ItemTags.BREAKS_DECORATED_POTS);
		AXE.setupItemTag(ItemTags.MINING_LOOT_ENCHANTABLE);
		AXE.setupItemTag(ItemTags.MINING_ENCHANTABLE);
		AXE.setupItemTag(ItemTags.SHARP_WEAPON_ENCHANTABLE);
		AXE.setupItemTag(Tags.Items.TOOLS);

		SWORD.setName(base.name + "_sword");
		SWORD.setup(base, () -> new SwordItem(base.tier, new Item.Properties()));
		SWORD.setupItemTag(ItemTags.VANISHING_ENCHANTABLE);
		SWORD.setupItemTag(Tags.Items.MELEE_WEAPON_TOOLS);
		SWORD.setupItemTag(Tags.Items.ENCHANTABLES);
		SWORD.setupItemTag(ItemTags.SWORDS);
		SWORD.setupItemTag(ItemTags.WEAPON_ENCHANTABLE);
		SWORD.setupItemTag(ItemTags.DURABILITY_ENCHANTABLE);
		SWORD.setupItemTag(ItemTags.BREAKS_DECORATED_POTS);
		SWORD.setupItemTag(ItemTags.MINING_LOOT_ENCHANTABLE);
		SWORD.setupItemTag(ItemTags.MINING_ENCHANTABLE);
		SWORD.setupItemTag(ItemTags.SHARP_WEAPON_ENCHANTABLE);
		SWORD.setupItemTag(Tags.Items.TOOLS);

		SHOVEL.setName(base.name + "_shovel");
		SHOVEL.setup(base, () -> new ShovelItem(base.tier, new Item.Properties()));
		SHOVEL.setupItemTag(ItemTags.VANISHING_ENCHANTABLE);
		SHOVEL.setupItemTag(Tags.Items.ENCHANTABLES);
		SHOVEL.setupItemTag(ItemTags.SHOVELS);
		SHOVEL.setupItemTag(ItemTags.DURABILITY_ENCHANTABLE);
		SHOVEL.setupItemTag(ItemTags.BREAKS_DECORATED_POTS);
		SHOVEL.setupItemTag(ItemTags.MINING_LOOT_ENCHANTABLE);
		SHOVEL.setupItemTag(ItemTags.MINING_ENCHANTABLE);
		SHOVEL.setupItemTag(Tags.Items.TOOLS);

		PICKAXE.setName(base.name + "_pickaxe");
		PICKAXE.setup(base, () -> new PickaxeItem(base.tier, new Item.Properties()));
		PICKAXE.setupItemTag(ItemTags.VANISHING_ENCHANTABLE);
		PICKAXE.setupItemTag(Tags.Items.ENCHANTABLES);
		PICKAXE.setupItemTag(ItemTags.PICKAXES);
		PICKAXE.setupItemTag(ItemTags.DURABILITY_ENCHANTABLE);
		PICKAXE.setupItemTag(ItemTags.BREAKS_DECORATED_POTS);
		PICKAXE.setupItemTag(ItemTags.MINING_LOOT_ENCHANTABLE);
		PICKAXE.setupItemTag(ItemTags.MINING_ENCHANTABLE);
		PICKAXE.setupItemTag(Tags.Items.TOOLS);
		PICKAXE.setupItemTag(Tags.Items.MINING_TOOL_TOOLS);
		PICKAXE.setupItemTag(ItemTags.CLUSTER_MAX_HARVESTABLES);

		HOE.setName(base.name + "_hoe");
		HOE.setup(base, () -> new HoeItem(base.tier, new Item.Properties()));
		HOE.setupItemTag(ItemTags.VANISHING_ENCHANTABLE);
		HOE.setupItemTag(Tags.Items.ENCHANTABLES);
		HOE.setupItemTag(ItemTags.HOES);
		HOE.setupItemTag(ItemTags.DURABILITY_ENCHANTABLE);
		HOE.setupItemTag(ItemTags.BREAKS_DECORATED_POTS);
		HOE.setupItemTag(ItemTags.MINING_LOOT_ENCHANTABLE);
		HOE.setupItemTag(ItemTags.MINING_ENCHANTABLE);
		HOE.setupItemTag(Tags.Items.TOOLS);

	}

	@Override
	public void tab(_MaterialBase base, Output output) {
		AXE.tab(base, output);
		SWORD.tab(base, output);
		HOE.tab(base, output);
		PICKAXE.tab(base, output);
		SHOVEL.tab(base, output);
	}

	@Override
	public void engLoc(_MaterialBase base, LanguageProvider lp) {
		String locName = base.name.substring(0, 1).toUpperCase() + base.name.substring(1);
		if (AXE.shouldGenerate())
			lp.add(this.AXE.ITEM.get(), locName + " Axe");
		if (HOE.shouldGenerate())
			lp.add(this.HOE.ITEM.get(), locName + " Hoe");
		if (PICKAXE.shouldGenerate())
			lp.add(this.PICKAXE.ITEM.get(), locName + " Pickaxe");
		if (SHOVEL.shouldGenerate())
			lp.add(this.SHOVEL.ITEM.get(), locName + " Shovel");
		if (SWORD.shouldGenerate())
			lp.add(this.SWORD.ITEM.get(), locName + " Sword");
	}

	@Override
	public void recipes(_MaterialBase base, RecipeOutput consumer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockLoot(_MaterialBase base, BlockLootSubProvider blp) {

	}

//	@Override
//	public void setupItemTags(_MaterialBase base, ItemTagsProvider itp) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void setupBlockTags(_MaterialBase base, BlockTagsProvider itp) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void setupClient(_MaterialBase base, FMLClientSetupEvent event) {
//		// TODO Auto-generated method stub
//
//	}

	public static class Serializer extends MaterialExtensionSerializer<ExtensionVanillaTools> {

		public Serializer() {
			super("VANILLATOOLS");
		}

		@Override
		public JsonElement serialize(ExtensionVanillaTools src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();
			j.addProperty("type", type);
			j.add("sword", src.SWORD.serialize());
			j.add("axe", src.AXE.serialize());
			j.add("shovel", src.SHOVEL.serialize());
			j.add("hoe", src.HOE.serialize());
			j.add("pickaxe", src.PICKAXE.serialize());

			return j;
		}

		@Override
		public ExtensionVanillaTools deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			ExtensionVanillaTools e = new ExtensionVanillaTools();

			if (j.has("axe"))
				e.AXE.deserialize(j.get("axe").getAsJsonObject());
			if (j.has("sword"))
				e.SWORD.deserialize(j.get("sword").getAsJsonObject());
			if (j.has("shovel"))
				e.SHOVEL.deserialize(j.get("shovel").getAsJsonObject());
			if (j.has("hoe"))
				e.HOE.deserialize(j.get("hoe").getAsJsonObject());
			if (j.has("pickaxe"))
				e.PICKAXE.deserialize(j.get("pickaxe").getAsJsonObject());

			return e;
		}

	}

	@Override
	public void otherLoot(_MaterialBase base, LootTableSubProvider lsp) {
		// TODO Auto-generated method stub

	}

//	@Override
//	public boolean isIndexItem(_MaterialBase base, ItemStack stack) {
//		if (stack.getItem() == SWORD.ITEM.asItem())
//			return true;
//		if (stack.getItem() == AXE.ITEM.asItem())
//			return true;
//		if (stack.getItem() == SHOVEL.ITEM.asItem())
//			return true;
//		if (stack.getItem() == HOE.ITEM.asItem())
//			return true;
//		if (stack.getItem() == PICKAXE.ITEM.asItem())
//			return true;
//
//		return false;
//	}
//
//	@Override
//	public Optional<IIndexEntry> getEntryItemBelongsTo(_MaterialBase base, ItemStack stack) {
//		if (stack.getItem() == SWORD.ITEM.asItem())
//			return Optional.of(base);
//		if (stack.getItem() == AXE.ITEM.asItem())
//			return Optional.of(base);
//		if (stack.getItem() == SHOVEL.ITEM.asItem())
//			return Optional.of(base);
//		if (stack.getItem() == HOE.ITEM.asItem())
//			return Optional.of(base);
//		if (stack.getItem() == PICKAXE.ITEM.asItem())
//			return Optional.of(base);
//
//		return Optional.empty();
//	}

	@Override
	public void attachComponents(_MaterialBase base, ModifyDefaultComponentsEvent event) {
		// TODO Auto-generated method stub

	}
}
