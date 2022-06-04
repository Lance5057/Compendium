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
		model.withExistingParent(AXE.getId().getPath(), model.mcLoc("item/generated"))
				.texture("layer0", model.modLoc("item/material/" + name + "/" + AXE.getId().getPath()))
				.texture("layer1", model.modLoc("item/axebase"));

		model.withExistingParent(HOE.getId().getPath(), model.mcLoc("item/generated"))
				.texture("layer0", model.modLoc("item/material/" + name + "/" + HOE.getId().getPath()))
				.texture("layer1", model.modLoc("item/hoebase"));

		model.withExistingParent(PICKAXE.getId().getPath(), model.mcLoc("item/generated"))
				.texture("layer0", model.modLoc("item/material/" + name + "/" + PICKAXE.getId().getPath()))
				.texture("layer1", model.modLoc("item/pickaxebase"));

		model.withExistingParent(SHOVEL.getId().getPath(), model.mcLoc("item/generated"))
				.texture("layer0", model.modLoc("item/material/" + name + "/" + SHOVEL.getId().getPath()))
				.texture("layer1", model.modLoc("item/shovelbase"));

		model.withExistingParent(SWORD.getId().getPath(), model.mcLoc("item/generated"))
				.texture("layer0", model.modLoc("item/material/" + name + "/" + SWORD.getId().getPath()))
				.texture("layer1", model.modLoc("item/swordbase"));
	}

	@Override
	public void addTranslations(TCEnglishLoc loc, String capName) {
		loc.add(AXE.get(), capName + " Axe");
		loc.add(HOE.get(), capName + " Hoe");
		loc.add(PICKAXE.get(), capName + " Pickaxe");
		loc.add(SHOVEL.get(), capName + " Shovel");
		loc.add(SWORD.get(), capName + " Sword");
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

	@Override
	public void setupClient(MaterialHelper helper) {
		// TODO Auto-generated method stub
		
	}

}
