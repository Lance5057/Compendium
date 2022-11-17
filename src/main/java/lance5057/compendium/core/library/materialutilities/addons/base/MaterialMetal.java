package lance5057.compendium.core.library.materialutilities.addons.base;

import java.util.function.Consumer;

import lance5057.compendium.CompendiumBlocks;
import lance5057.compendium.CompendiumItems;
import lance5057.compendium.Reference;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCBlockTags;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import net.minecraft.core.Registry;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

public class MaterialMetal extends MaterialBase {
	public RegistryObject<Item> INGOT;
	public RegistryObject<Item> NUGGET;
	public RegistryObject<Block> STORAGE_BLOCK;
	public RegistryObject<BlockItem> STORAGE_ITEMBLOCK;

	@Override
	public void setup(MaterialHelper helper) {
		INGOT = CompendiumItems.ITEMS.register(helper.name + "_ingot",
				() -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
		NUGGET = CompendiumItems.ITEMS.register(helper.name + "_nugget",
				() -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));

		STORAGE_BLOCK = CompendiumBlocks.BLOCKS.register(helper.name + "_block",
				() -> new Block(Block.Properties.of(Material.METAL).strength(3, 4)));
		STORAGE_ITEMBLOCK = CompendiumItems.ITEMS.register(helper.name + "_itemblock",
				() -> new BlockItem(STORAGE_BLOCK.get(), new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));

	}

	@Override
	public void registerBlockModels(TCBlockModels model, String name) {
		model.simpleBlock(STORAGE_BLOCK.get(), model.models().cubeAll(STORAGE_BLOCK.get().getRegistryName().getPath(),
				new ResourceLocation(Reference.MOD_ID, "block/material/" + name + "/" + name + "block")));
	}

	@Override
	public void registerItemModels(TCItemModels model, String name) {
		model.forMaterialItem(INGOT, name);
		model.forMaterialItem(NUGGET, name);
		model.forBlockItem(STORAGE_ITEMBLOCK, name);
	}

	@Override
	public void addTranslations(TCEnglishLoc loc, String capName) {
		loc.add(INGOT.get(), capName + " Ingot");
		loc.add(NUGGET.get(), capName + " Nugget");
		loc.add(STORAGE_ITEMBLOCK.get(), capName + " Block");
	}

	@Override
	public void registerItemTags(TCItemTags itp, String name) {

	}

	@Override
	public void registerBlockTags(TCBlockTags tags, String name) {
		tags.getOrCreateRawBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
				.addElement(Registry.BLOCK.getKey(this.STORAGE_BLOCK.get()), Reference.MOD_ID);
	}

	@Override
	public void buildLootTable(BlockLoot table, String name) {
		table.dropSelf(STORAGE_BLOCK.get());

	}

	@Override
	public void buildRecipes(TCRecipes recipes, Consumer<FinishedRecipe> consumer, String name) {
//		ShapelessRecipeBuilder.shapeless(NUGGET.get(), 9)
//				.addIngredient(Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + name)), 1)
//				.addCriterion(name + "ingot", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_nugget_from_ingot"));
//
//		ShapelessRecipeBuilder.shapeless(INGOT.get(), 9)
//				.addIngredient(Ingredient.fromTag(TCItemTags.ItemTag("storage_blocks/" + name)), 1)
//				.addCriterion(name + "storage_block",
//						RecipeProvider.hasItem(TCItemTags.ItemTag("storage_blocks/" + name)))
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_ingot_from_storage_block"));
//
//		ShapelessRecipeBuilder.shapeless(INGOT.get(), 1)
//				.addIngredient(Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + name)), 9)
//				.addCriterion(name + "nugget", RecipeProvider.hasItem(TCItemTags.ItemTag("nuggets/" + name)))
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_ingot_from_nuggets"));
//
//		ShapelessRecipeBuilder.shapeless(STORAGE_ITEMBLOCK.get(), 1)
//				.addIngredient(Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + name)), 9)
//				.addCriterion(name + "storage_block", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_storage_block_from_ingots"));
	}

	@Override
	public void setupClient(MaterialHelper helper) {
		// TODO Auto-generated method stub
		
	}
}
