package lance5057.compendium.appendixes.metallurgy.materialhelper.addons;

import java.util.function.Consumer;

import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.data.builders.loottables.BlockLoot;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MetalMaterialBasic implements MaterialBase {

    public RegistryObject<Item> INGOT;
    public RegistryObject<Item> NUGGET;
    public RegistryObject<Block> STORAGE_BLOCK;
    public RegistryObject<BlockNamedItem> STORAGE_ITEMBLOCK;

    public MetalMaterialBasic(MetallurgyMaterialHelper metallurgyMaterialHelper) {

    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setup(MaterialHelperBase mat) {
	INGOT = mat.ITEMS.register(mat.name + "_ingot",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	NUGGET = mat.ITEMS.register(mat.name + "_nugget",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	STORAGE_BLOCK = mat.BLOCKS.register(mat.name + "_block", () -> new Block(Block.Properties.create(Material.IRON)
		.harvestLevel(1).hardnessAndResistance(3, 4).harvestTool(ToolType.PICKAXE)));
	STORAGE_ITEMBLOCK = mat.ITEMS.register(mat.name + "_itemblock", () -> new BlockNamedItem(STORAGE_BLOCK.get(),
		new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

    }

    public static void registerBlockModels(MetalMaterialBasic m, TCBlockModels model, String name) {
	model.simpleBlock(m.STORAGE_BLOCK.get(),
		model.models().cubeAll(m.STORAGE_BLOCK.get().getRegistryName().getPath(),
			new ResourceLocation(Reference.MOD_ID, "block/material/" + name + "/" + name + "block")));
    }

    public static void registerItemModels(MetalMaterialBasic m, TCItemModels model, String name) {
	model.forMaterialItem(m.INGOT, name);
	model.forMaterialItem(m.NUGGET, name);
	model.forBlockItem(m.STORAGE_ITEMBLOCK, name);
    }

    public static void addTranslations(MetalMaterialBasic m, TCEnglishLoc loc, String capName) {
	loc.add(m.INGOT.get(), capName + " Ingot");
	loc.add(m.NUGGET.get(), capName + " Nugget");
	loc.add(m.STORAGE_ITEMBLOCK.get(), capName + " Block");
    }

    public static void registerTags(MetalMaterialBasic m, TCItemTags itp, String name) {
	// Ingot
	itp.getOrCreateBuilder(Tags.Items.INGOTS).add(m.INGOT.get());
	INamedTag<Item> INGOT_MATERIAL = TCItemTags.ItemTag("ingots/" + name);
	itp.getOrCreateBuilder(INGOT_MATERIAL).add(m.INGOT.get());

	// Nugget
	itp.getOrCreateBuilder(Tags.Items.NUGGETS).add(m.NUGGET.get());
	INamedTag<Item> NUGGET_MATERIAL = TCItemTags.ItemTag("nuggets/" + name);
	itp.getOrCreateBuilder(NUGGET_MATERIAL).add(m.NUGGET.get());

	// Storage Block
	itp.getOrCreateBuilder(Tags.Items.STORAGE_BLOCKS).add(m.STORAGE_ITEMBLOCK.get());
	INamedTag<Item> BLOCK_MATERIAL = TCItemTags.ItemTag("storage_blocks/" + name);
	itp.getOrCreateBuilder(BLOCK_MATERIAL).add(m.STORAGE_ITEMBLOCK.get());
    }

    public static void buildLootTable(MetalMaterialBasic m, BlockLoot table, String name) {
	table.registerDropSelfLootTable(m.STORAGE_BLOCK.get());
    }

    public static void buildRecipes(MetalMaterialBasic m, TCRecipes recipes, Consumer<IFinishedRecipe> consumer,
	    String name) {
	ShapelessRecipeBuilder.shapelessRecipe(m.NUGGET.get(), 9)
		.addIngredient(Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + name)), 1)
		.addCriterion(name + "ingot", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_nugget_from_ingot"));

	ShapelessRecipeBuilder.shapelessRecipe(m.INGOT.get(), 9)
		.addIngredient(Ingredient.fromTag(TCItemTags.ItemTag("storage_blocks/" + name)), 1)
		.addCriterion(name + "storage_block",
			RecipeProvider.hasItem(TCItemTags.ItemTag("storage_blocks/" + name)))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_ingot_from_storage_block"));

	ShapelessRecipeBuilder.shapelessRecipe(m.INGOT.get(), 1)
		.addIngredient(Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + name)), 9)
		.addCriterion(name + "nugget", RecipeProvider.hasItem(TCItemTags.ItemTag("nuggets/" + name)))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_ingot_from_nuggets"));

	ShapelessRecipeBuilder.shapelessRecipe(m.STORAGE_ITEMBLOCK.get(), 1)
		.addIngredient(Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + name)), 9)
		.addCriterion(name + "storage_block", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + name)))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, name + "_storage_block_from_ingots"));
    }
}
