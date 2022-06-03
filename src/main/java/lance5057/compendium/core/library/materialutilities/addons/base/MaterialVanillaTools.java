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
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.registries.RegistryObject;

public class MaterialVanillaTools extends MaterialBase {
	public RegistryObject<Item> SWORD;
	public RegistryObject<Item> PICKAXE;
	public RegistryObject<Item> SHOVEL;
	public RegistryObject<Item> AXE;
	public RegistryObject<Item> HOE;

	@Override
	public void setup(MaterialHelper helper) {
		SWORD = CompendiumItems.ITEMS.register(helper.name + "sword",
				() -> new SwordItem(helper.tier, 3, -2.4f, new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		PICKAXE = CompendiumItems.ITEMS.register(helper.name + "pickaxe", () -> new PickaxeItem(helper.tier, 1, -2.8F,
				(new Item.Properties()).tab(CompendiumItems.GROUP_MATERIALS)));
		SHOVEL = CompendiumItems.ITEMS.register(helper.name + "shovel", () -> new ShovelItem(helper.tier, 1.5F, -3.0F,
				(new Item.Properties()).tab(CompendiumItems.GROUP_MATERIALS)));
		AXE = CompendiumItems.ITEMS.register(helper.name + "axe", () -> new AxeItem(helper.tier, 5.0F, -3.0F,
				(new Item.Properties()).tab(CompendiumItems.GROUP_MATERIALS)));
		HOE = CompendiumItems.ITEMS.register(helper.name + "hoe",
				() -> new HoeItem(helper.tier, -3, 0.0F, (new Item.Properties()).tab(CompendiumItems.GROUP_MATERIALS)));
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
	public void registerBlockTags(TCBlockTags tags, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerItemTags(TCItemTags itp, String name) {
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
