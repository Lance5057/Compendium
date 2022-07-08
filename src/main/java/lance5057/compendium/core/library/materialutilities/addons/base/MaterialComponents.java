package lance5057.compendium.core.library.materialutilities.addons.base;

import java.util.function.Consumer;

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
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void addTranslations(TCEnglishLoc loc, String capName) {
		// TODO Auto-generated method stub

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
