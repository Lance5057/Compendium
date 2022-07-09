package lance5057.compendium.core.library.materialutilities.addons.base;

import java.util.function.Consumer;

import lance5057.compendium.CompendiumItems;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCBlockTags;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class MaterialComponents extends MaterialBase {

	public RegistryObject<Item> DUST;
	public RegistryObject<Item> PLATE;
	public RegistryObject<Item> COIN;
	public RegistryObject<Item> GEAR;
	public RegistryObject<Item> ROD;
	public RegistryObject<Item> HOOP;
	public RegistryObject<Item> COIL;
	public RegistryObject<Item> CLASP;
	public RegistryObject<Item> WIRE;
	public RegistryObject<Item> FOIL;
	public RegistryObject<Item> FILIGREE;
	public RegistryObject<Item> JUMPRINGS;
	public RegistryObject<Item> RIVETS;
	public RegistryObject<Item> SPRING;
	public RegistryObject<Item> SETTING;
	public RegistryObject<Item> CASING;

	@Override
	public void setup(MaterialHelper helper) {
		 DUST = CompendiumItems.ITEMS.register(helper.name + "_dust", () -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		 PLATE = CompendiumItems.ITEMS.register(helper.name + "_plate", () -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		 COIN = CompendiumItems.ITEMS.register(helper.name + "_coin", () -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		 GEAR = CompendiumItems.ITEMS.register(helper.name + "_gear", () -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		 ROD = CompendiumItems.ITEMS.register(helper.name + "_rod", () -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		 HOOP = CompendiumItems.ITEMS.register(helper.name + "_hoop", () -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		 COIL = CompendiumItems.ITEMS.register(helper.name + "_coil", () -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		 CLASP = CompendiumItems.ITEMS.register(helper.name + "_clasp", () -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		 WIRE = CompendiumItems.ITEMS.register(helper.name + "_wire", () -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		 FOIL = CompendiumItems.ITEMS.register(helper.name + "_foil", () -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		 FILIGREE = CompendiumItems.ITEMS.register(helper.name + "_filigree", () -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		 JUMPRINGS = CompendiumItems.ITEMS.register(helper.name + "_jumprings", () -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		 RIVETS = CompendiumItems.ITEMS.register(helper.name + "_rivets", () -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		 SPRING = CompendiumItems.ITEMS.register(helper.name + "_spring", () -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		 SETTING = CompendiumItems.ITEMS.register(helper.name + "_setting", () -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		 CASING = CompendiumItems.ITEMS.register(helper.name + "_casing", () -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
	}

	@Override
	public void setupClient(MaterialHelper helper) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerBlockModels(TCBlockModels model, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerItemModels(TCItemModels model, String name) {
		model.forMaterialItem(DUST, name, name + "dust");
		model.forMaterialItem(PLATE, name, name + "plate");
		model.forMaterialItem(COIN, name, name + "coin");
		model.forMaterialItem(GEAR, name, name + "gear");
		model.forMaterialItem(ROD, name, name + "rod");
		model.forMaterialItem(HOOP, name, name + "ringshank");
		model.forMaterialItem(COIL, name, name + "coil");
		model.forMaterialItem(CLASP, name, name + "clasp");
		model.forMaterialItem(WIRE, name, name + "wire");
		model.forMaterialItem(FOIL, name, name + "foil");
		model.forMaterialItem(FILIGREE, name, name + "filigree");
		model.forMaterialItem(JUMPRINGS, name, name + "jumprings");
		model.forMaterialItem(RIVETS, name, name + "rivets");
		model.forMaterialItem(SPRING, name, name + "spring");
		model.forMaterialItem(SETTING, name, name + "setting");
		model.forMaterialItem(CASING, name, name + "casing");
	}

	@Override
	public void addTranslations(TCEnglishLoc loc, String capName) {
		loc.add(DUST.get(), capName + " Dust");
		loc.add(PLATE.get(), capName + " Plate");
		loc.add(COIN.get(), capName + " Coin");
		loc.add(GEAR.get(), capName + " Gear");
		loc.add(ROD.get(), capName + " Rod");
		loc.add(HOOP.get(), capName + " Hoop");
		loc.add(COIL.get(), capName + " Coil");
		loc.add(CLASP.get(), capName + " Clasp");
		loc.add(WIRE.get(), capName + " Wire");
		loc.add(FOIL.get(), capName + " Foil");
		loc.add(FILIGREE.get(), capName + " Filigree");
		loc.add(JUMPRINGS.get(), capName + " Jump Rings");
		loc.add(RIVETS.get(), capName + " Rivets");
		loc.add(SPRING.get(), capName + " Spring");
		loc.add(SETTING.get(), capName + " Setting");
		loc.add(CASING.get(), capName + " Casing");
	}

	@Override
	public void registerItemTags(TCItemTags itp, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerBlockTags(TCBlockTags tags, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildLootTable(BlockLoot table, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildRecipes(TCRecipes recipes, Consumer<FinishedRecipe> consumer, String name) {
		// TODO Auto-generated method stub

	}

}
