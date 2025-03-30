package com.lance5057.compendium.index.material.extentions;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.util.DataUtil;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredItem;

public class ExtensionVanillaTools extends _MaterialExtension {
	boolean loadSword;
	boolean loadAxe;
	boolean loadShovel;
	boolean loadHoe;
	boolean loadPickaxe;

	public DeferredItem<Item> SWORD;
	public DeferredItem<Item> AXE;
	public DeferredItem<Item> SHOVEL;
	public DeferredItem<Item> HOE;
	public DeferredItem<Item> PICKAXE;

	public ExtensionVanillaTools(boolean sword, boolean axe, boolean shovel, boolean hoe, boolean pickaxe) {
		this.loadSword = sword;
		this.loadAxe = axe;
		this.loadHoe = hoe;
		this.loadPickaxe = pickaxe;
		this.loadShovel = shovel;
	}

	@Override
	public void setup(_MaterialBase base) {
		if (this.loadAxe)
			AXE = CompendiumIndex.ITEMS.register(base.name + "_axe",
					() -> new AxeItem(base.tier, new Item.Properties()));
		if (this.loadHoe)
			HOE = CompendiumIndex.ITEMS.register(base.name + "_hoe",
					() -> new HoeItem(base.tier, new Item.Properties()));
		if (this.loadPickaxe)
			PICKAXE = CompendiumIndex.ITEMS.register(base.name + "_pickaxe",
					() -> new PickaxeItem(base.tier, new Item.Properties()));
		if (this.loadShovel)
			SHOVEL = CompendiumIndex.ITEMS.register(base.name + "_shovel",
					() -> new ShovelItem(base.tier, new Item.Properties()));
		if (this.loadSword)
			SWORD = CompendiumIndex.ITEMS.register(base.name + "_sword",
					() -> new SwordItem(base.tier, new Item.Properties()));
	}

	@Override
	public void tab(_MaterialBase base, Output output) {
		if (this.loadAxe)
			output.accept(AXE);
		if (this.loadHoe)
			output.accept(HOE);
		if (this.loadPickaxe)
			output.accept(PICKAXE);
		if (this.loadShovel)
			output.accept(SHOVEL);
		if (this.loadSword)
			output.accept(SWORD);
	}

	@Override
	public void blockModel(_MaterialBase base, BlockStateProvider bsp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemModel(_MaterialBase base, ItemModelProvider tmp) {
		if (this.loadAxe)
			DataUtil.basicMaterialItem(tmp, this.AXE.get(), base, "axe", base.getType());
		if (this.loadHoe)
			DataUtil.basicMaterialItem(tmp, this.HOE.get(), base, "hoe", base.getType());
		if (this.loadPickaxe)
			DataUtil.basicMaterialItem(tmp, this.PICKAXE.get(), base, "pickaxe", base.getType());
		if (this.loadShovel)
			DataUtil.basicMaterialItem(tmp, this.SHOVEL.get(), base, "shovel", base.getType());
		if (this.loadSword)
			DataUtil.basicMaterialItem(tmp, this.SWORD.get(), base, "sword", base.getType());
	}

	@Override
	public void engLoc(_MaterialBase base, LanguageProvider lp) {
		String locName = base.name.substring(0, 1).toUpperCase() + base.name.substring(1);
		if (this.loadAxe)
			lp.add(this.AXE.get(), locName + " Axe");
		if (this.loadHoe)
			lp.add(this.HOE.get(), locName + " Hoe");
		if (this.loadPickaxe)
			lp.add(this.PICKAXE.get(), locName + " Pickaxe");
		if (this.loadShovel)
			lp.add(this.SHOVEL.get(), locName + " Shovel");
		if (this.loadSword)
			lp.add(this.SWORD.get(), locName + " Sword");
	}

	@Override
	public void recipes(_MaterialBase base, RecipeOutput consumer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockLoot(_MaterialBase base, BlockLootSubProvider blp) {

	}

	@Override
	public void setupItemTags(_MaterialBase base, ItemTagsProvider itp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupBlockTags(_MaterialBase base, BlockTagsProvider itp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupClient(_MaterialBase base, FMLClientSetupEvent event) {
		// TODO Auto-generated method stub

	}

	public static class Serializer extends MaterialExtensionSerializer<ExtensionVanillaTools> {

		public Serializer() {
			super("VANILLATOOLS");
		}

		@Override
		public JsonElement serialize(ExtensionVanillaTools src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("type", type);
			j.addProperty("loadSword", src.loadSword);
			j.addProperty("loadAxe", src.loadAxe);
			j.addProperty("loadShovel", src.loadShovel);
			j.addProperty("loadHoe", src.loadHoe);
			j.addProperty("loadPickaxe", src.loadPickaxe);

			return j;
		}

		@Override
		public ExtensionVanillaTools deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			boolean sword = j.get("loadSword").getAsBoolean();
			boolean axe = j.get("loadAxe").getAsBoolean();
			boolean shovel = j.get("loadShovel").getAsBoolean();
			boolean hoe = j.get("loadHoe").getAsBoolean();
			boolean pickaxe = j.get("loadPickaxe").getAsBoolean();

			return new ExtensionVanillaTools(sword, axe, shovel, hoe, pickaxe);
		}

	}

}
