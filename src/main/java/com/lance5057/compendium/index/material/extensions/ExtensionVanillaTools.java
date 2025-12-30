package com.lance5057.compendium.index.material.extensions;

import java.lang.reflect.Type;
import java.util.Optional;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.data.IndexBlockModelProvider;
import com.lance5057.compendium.index.CompendiumIndex.Generate;
import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.util.CompendiumItemHandler;
import com.lance5057.compendium.index.util.DataUtil;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ExtensionVanillaTools extends _MaterialExtension {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6058992077769136068L;

	public CompendiumItemHandler SWORD = new CompendiumItemHandler("sword");
	public CompendiumItemHandler AXE = new CompendiumItemHandler("axe");
	public CompendiumItemHandler SHOVEL = new CompendiumItemHandler("shovel");
	public CompendiumItemHandler HOE = new CompendiumItemHandler("hoe");
	public CompendiumItemHandler PICKAXE = new CompendiumItemHandler("pickaxe");

	public ExtensionVanillaTools(Generate sword, Generate axe, Generate shovel,
			Generate hoe, Generate pickaxe) {
		SWORD.setGenerate(sword);
		AXE.setGenerate(axe);
		SHOVEL.setGenerate(shovel);
		PICKAXE.setGenerate(pickaxe);
		HOE.setGenerate(hoe);
	}

	@Override
	public void setup(_MaterialBase base) {
		AXE.setup(base, () -> new AxeItem(base.tier, new Item.Properties()),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_axe"));
		SWORD.setup(base, () -> new SwordItem(base.tier, new Item.Properties()),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_sword"));
		SHOVEL.setup(base, () -> new ShovelItem(base.tier, new Item.Properties()),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_shovel"));
		PICKAXE.setup(base, () -> new PickaxeItem(base.tier, new Item.Properties()),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_pickaxe"));
		HOE.setup(base, () -> new HoeItem(base.tier, new Item.Properties()),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_hoe"));

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
	public void blockStateModel(_MaterialBase base, BlockStateProvider bsp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemModel(_MaterialBase base, ItemModelProvider tmp) {
		if (AXE.shouldGenerate())
			DataUtil.basicMaterialItem(tmp, this.AXE.ITEM.get(), base, "axe", base.getType());
		if (HOE.shouldGenerate())
			DataUtil.basicMaterialItem(tmp, this.HOE.ITEM.get(), base, "hoe", base.getType());
		if (PICKAXE.shouldGenerate())
			DataUtil.basicMaterialItem(tmp, this.PICKAXE.ITEM.get(), base, "pickaxe", base.getType());
		if (SHOVEL.shouldGenerate())
			DataUtil.basicMaterialItem(tmp, this.SHOVEL.ITEM.get(), base, "shovel", base.getType());
		if (SWORD.shouldGenerate())
			DataUtil.basicMaterialItem(tmp, this.SWORD.ITEM.get(), base, "sword", base.getType());
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
			j.addProperty("loadSword", src.SWORD.getGeneration().toString());
			j.addProperty("loadAxe", src.AXE.getGeneration().toString());
			j.addProperty("loadShovel", src.SHOVEL.getGeneration().toString());
			j.addProperty("loadHoe", src.HOE.getGeneration().toString());
			j.addProperty("loadPickaxe", src.PICKAXE.getGeneration().toString());

			return j;
		}

		@Override
		public ExtensionVanillaTools deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			String sword = j.get("loadSword").getAsString();
			String axe = j.get("loadAxe").getAsString();
			String shovel = j.get("loadShovel").getAsString();
			String hoe = j.get("loadHoe").getAsString();
			String pickaxe = j.get("loadPickaxe").getAsString();

			return new ExtensionVanillaTools(Generate.valueOf(sword), Generate.valueOf(axe), Generate.valueOf(shovel),
					Generate.valueOf(hoe), Generate.valueOf(pickaxe));
		}

	}

	@Override
	public void blockModel(_MaterialBase base, IndexBlockModelProvider ibmp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void otherLoot(_MaterialBase base, LootTableSubProvider lsp) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isIndexItem(_MaterialBase base, ItemStack stack) {
		if (stack.getItem() == SWORD.ITEM.asItem())
			return true;
		if (stack.getItem() == AXE.ITEM.asItem())
			return true;
		if (stack.getItem() == SHOVEL.ITEM.asItem())
			return true;
		if (stack.getItem() == HOE.ITEM.asItem())
			return true;
		if (stack.getItem() == PICKAXE.ITEM.asItem())
			return true;

		return false;
	}

	@Override
	public Optional<IIndexEntry> getEntryItemBelongsTo(_MaterialBase base, ItemStack stack) {
		if (stack.getItem() == SWORD.ITEM.asItem())
			return Optional.of(base);
		if (stack.getItem() == AXE.ITEM.asItem())
			return Optional.of(base);
		if (stack.getItem() == SHOVEL.ITEM.asItem())
			return Optional.of(base);
		if (stack.getItem() == HOE.ITEM.asItem())
			return Optional.of(base);
		if (stack.getItem() == PICKAXE.ITEM.asItem())
			return Optional.of(base);

		return Optional.empty();
	}
}
