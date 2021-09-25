package lance5057.compendium.core.data.builders;

import java.util.function.Consumer;

import javax.annotation.Nonnull;

import lance5057.compendium.Compendium;
import lance5057.compendium.CompendiumItems;
import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.carpentry.data.CarpentryRecipes;
import lance5057.compendium.appendixes.construction.data.ConstructionRecipes;
import lance5057.compendium.appendixes.gemology.data.GemRecipes;
import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
import lance5057.compendium.appendixes.metallurgy.data.builders.MetalRecipes;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.appendixes.oredressing.data.builders.OreRecipes;
import lance5057.compendium.core.data.builders.workstationrecipes.builders.ScrappingTableRecipeBuilder;
import lance5057.compendium.core.data.builders.workstationrecipes.loottables.ScrappingTableLoottables;
import lance5057.compendium.core.library.CompendiumTags;
import net.minecraft.data.CustomRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.data.SingleItemRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class TCRecipes extends RecipeProvider {

    public TCRecipes(DataGenerator generatorIn) {
	super(generatorIn);
	Compendium.logger.info("\t - Recipes");
    }

    @Override
    protected void registerRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {

	CustomRecipeBuilder.customRecipe(IRecipeSerializer.CRAFTING_SPECIAL_SHIELD).build(consumer,
		"shield_decoration");

	ShapedRecipeBuilder.shapedRecipe(CompendiumItems.CRUDE_HAMMER.get(), 1)
		.key('s', Ingredient.fromTag(Tags.Items.STONE)).key('a', Ingredient.fromTag(Tags.Items.STRING))
		.key('w', Ingredient.fromTag(Tags.Items.RODS_WOODEN)).patternLine(" s ").patternLine("awa")
		.patternLine(" w ").addCriterion("stick_get", hasItem(Items.STICK))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "craft_crude_hammer"));

	ShapedRecipeBuilder.shapedRecipe(CompendiumItems.MINER_GRENADE.get(), 1)
		.key('t', Ingredient.fromItems(Items.TNT)).key('p', Ingredient.fromItems(Items.IRON_INGOT))
		.key('f', Ingredient.fromItems(Items.FLINT)).patternLine("tft").patternLine("fpf").patternLine("tft")
		.addCriterion("iron_ingot_aquired", hasItem(Items.IRON_INGOT))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "craft_grenade"));

	SingleItemRecipeBuilder
		.stonecuttingRecipe(Ingredient.fromItems(CompendiumItems.MEGALITH_STONE.get()), Items.STONE, 9)
		.addCriterion("has_megalith_stone", hasItem(CompendiumItems.MEGALITH_STONE.get()))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "crack_megalith"));

	workstations(consumer);
	hammer(consumer);
	alloys(consumer);
	scrapping(consumer);

	MetalRecipes.build(this, consumer);
	GemRecipes.build(this, consumer);
	OreRecipes.build(this, consumer);
	CarpentryRecipes.build(this, consumer);
	ConstructionRecipes.build(this, consumer);
//	for (MaterialHelper mh : CompendiumMaterials.materials) {
//
//	    if (mh.getIngot() != null)
//		ingot(mh, consumer);
//	    else if (mh.getGem() != null)
//		gem(mh, consumer);
////	    else
////		neither(mh, consumer);
//	}
    }

    private void alloys(Consumer<IFinishedRecipe> consumer) {

	// Brass
	MetallurgyMaterialHelper brass = AppendixMetallurgy.metals.stream().filter(i -> i.name == "brass").findFirst()
		.orElse(null);
	MetallurgyMaterialHelper copper = AppendixMetallurgy.metals.stream().filter(i -> i.name == "copper").findFirst()
		.orElse(null);
	MetallurgyMaterialHelper zinc = AppendixMetallurgy.metals.stream().filter(i -> i.name == "zinc").findFirst()
		.orElse(null);
	MetallurgyMaterialHelper tin = AppendixMetallurgy.metals.stream().filter(i -> i.name == "tin").findFirst()
		.orElse(null);
	MetallurgyMaterialHelper pewter = AppendixMetallurgy.metals.stream().filter(i -> i.name == "pewter").findFirst()
		.orElse(null);
	MetallurgyMaterialHelper bronze = AppendixMetallurgy.metals.stream().filter(i -> i.name == "bronze").findFirst()
		.orElse(null);
	MetallurgyMaterialHelper lead = AppendixMetallurgy.metals.stream().filter(i -> i.name == "lead").findFirst()
		.orElse(null);
	MetallurgyMaterialHelper rosegold = AppendixMetallurgy.metals.stream().filter(i -> i.name == "rosegold")
		.findFirst().orElse(null);
	MetallurgyMaterialHelper electrum = AppendixMetallurgy.metals.stream().filter(i -> i.name == "electrum")
		.findFirst().orElse(null);
	MetallurgyMaterialHelper silver = AppendixMetallurgy.metals.stream().filter(i -> i.name == "silver").findFirst()
		.orElse(null);
	MetallurgyMaterialHelper gold = AppendixMetallurgy.metals.stream().filter(i -> i.name == "gold").findFirst()
		.orElse(null);
	MetallurgyMaterialHelper iron = AppendixMetallurgy.metals.stream().filter(i -> i.name == "iron").findFirst()
		.orElse(null);
	MetallurgyMaterialHelper steel = AppendixMetallurgy.metals.stream().filter(i -> i.name == "steel").findFirst()
		.orElse(null);

	// Brass
	ShapelessRecipeBuilder.shapelessRecipe(brass.getComponents().DUST.get(), 3)
		.addIngredient(copper.getComponents().DUST.get(), 2).addIngredient(zinc.getComponents().DUST.get(), 1)
		.addCriterion("brass_dust_alloy", RecipeProvider.hasItem(copper.getComponents().DUST.get()))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "brass_alloy_dust"));

	// Bronze
	ShapelessRecipeBuilder.shapelessRecipe(bronze.getComponents().DUST.get(), 4)
		.addIngredient(copper.getComponents().DUST.get(), 3).addIngredient(tin.getComponents().DUST.get(), 1)
		.addCriterion("bronze_dust_alloy", RecipeProvider.hasItem(copper.getComponents().DUST.get()))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "bronze_alloy_dust"));

	// Rosegold
	ShapelessRecipeBuilder.shapelessRecipe(rosegold.getComponents().DUST.get(), 4)
		.addIngredient(gold.getComponents().DUST.get(), 3).addIngredient(copper.getComponents().DUST.get(), 1)
		.addCriterion("rosegold_dust_alloy", RecipeProvider.hasItem(copper.getComponents().DUST.get()))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "rosegold_alloy_dust"));

	// Electrum
	ShapelessRecipeBuilder.shapelessRecipe(electrum.getComponents().DUST.get(), 2)
		.addIngredient(gold.getComponents().DUST.get(), 1).addIngredient(silver.getComponents().DUST.get(), 1)
		.addCriterion("electrum_dust_alloy", RecipeProvider.hasItem(silver.getComponents().DUST.get()))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "electrum_alloy_dust"));

	// Pewter
	ShapelessRecipeBuilder.shapelessRecipe(pewter.getComponents().DUST.get(), 3)
		.addIngredient(tin.getComponents().DUST.get(), 1).addIngredient(lead.getComponents().DUST.get(), 1)
		.addIngredient(zinc.getComponents().DUST.get(), 1)
		.addCriterion("pewter_dust_alloy", RecipeProvider.hasItem(tin.getComponents().DUST.get()))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "pewter_alloy_dust"));

	// Steel
	ShapelessRecipeBuilder.shapelessRecipe(steel.getComponents().DUST.get(), 1)
		.addIngredient(iron.getComponents().DUST.get(), 1).addIngredient(Ingredient.fromTag(ItemTags.COALS))
		.addCriterion("electrum_dust_alloy", RecipeProvider.hasItem(silver.getComponents().DUST.get()))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "steel_alloy_dust"));
    }

    private void hammer(Consumer<IFinishedRecipe> consumer) {
	// TODO Auto-generated method stub

    }

    private void scrapping(Consumer<IFinishedRecipe> consumer) {

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.ANVIL), ScrappingTableLoottables.anvil)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_anvil"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromTag(ItemTags.BANNERS), ScrappingTableLoottables.banner)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(Tags.Items.SHEARS), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_banner"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.BARREL), ScrappingTableLoottables.barrel)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_barrel"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromTag(ItemTags.BEDS), ScrappingTableLoottables.bed)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_bed"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.BLAST_FURNACE), ScrappingTableLoottables.blast)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_blast"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.BOWL), ScrappingTableLoottables.bowl)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_bowl"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.CARTOGRAPHY_TABLE), ScrappingTableLoottables.carto)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_carto"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.CHAINMAIL_BOOTS), ScrappingTableLoottables.chainmail_boots)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_chainmail_boots"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.CHAINMAIL_CHESTPLATE), ScrappingTableLoottables.chainmail_chestplate)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_chainmail_chestplate"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.CHAINMAIL_HELMET), ScrappingTableLoottables.chainmail_helm)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_chainmail_helm"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.CHAINMAIL_LEGGINGS), ScrappingTableLoottables.chainmail_leggings)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_chainmail_leggings"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.CHEST), ScrappingTableLoottables.chest)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_chest"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.CHIPPED_ANVIL), ScrappingTableLoottables.chippedanvil)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_chippedanvil"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.COMPOSTER), ScrappingTableLoottables.composter)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_composter"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.CRAFTING_TABLE), ScrappingTableLoottables.craftingtable)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_craftingtable"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.DAMAGED_ANVIL), ScrappingTableLoottables.damagedanvil)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_damagedanvil"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.DIAMOND_AXE), ScrappingTableLoottables.diamond_axe)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_axe"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.DIAMOND_BOOTS), ScrappingTableLoottables.diamond_boots)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_boots"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.DIAMOND_CHESTPLATE), ScrappingTableLoottables.diamond_chestplate)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_chestplate"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.DIAMOND_HELMET), ScrappingTableLoottables.diamond_helm)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_helm"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.DIAMOND_HOE), ScrappingTableLoottables.diamond_hoe)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_hoe"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.DIAMOND_HORSE_ARMOR), ScrappingTableLoottables.diamond_horse_armor)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_horse_armor"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.DIAMOND_LEGGINGS), ScrappingTableLoottables.diamond_leggings)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_leggings"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.DIAMOND_PICKAXE), ScrappingTableLoottables.diamond_pick)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_pick"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.DIAMOND_SHOVEL), ScrappingTableLoottables.diamond_shovel)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_shovel"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.DIAMOND_SWORD), ScrappingTableLoottables.diamond_sword)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_sword"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromTag(ItemTags.WOODEN_DOORS), ScrappingTableLoottables.door)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "door"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.ENCHANTING_TABLE), ScrappingTableLoottables.enchantingtable)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "enchantingtable"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.ENDER_CHEST), ScrappingTableLoottables.enderchest)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "enderchest"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromTag(ItemTags.WOODEN_FENCES), ScrappingTableLoottables.fence)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "fence"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.FLETCHING_TABLE), ScrappingTableLoottables.fletch)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "fletch"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.ITEM_FRAME), ScrappingTableLoottables.frame)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "frame"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.FURNACE), ScrappingTableLoottables.furnace)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "furnace"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromTag(Tags.Items.FENCE_GATES_WOODEN), ScrappingTableLoottables.gate)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gate"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.GOLDEN_AXE), ScrappingTableLoottables.gold_axe)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_axe"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.GOLDEN_BOOTS), ScrappingTableLoottables.gold_boots)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_boots"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.GOLDEN_CHESTPLATE), ScrappingTableLoottables.gold_chestplate)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_chestplate"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.GOLDEN_HELMET), ScrappingTableLoottables.gold_helm)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_helm"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.GOLDEN_HOE), ScrappingTableLoottables.gold_hoe)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_hoe"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.GOLDEN_HORSE_ARMOR), ScrappingTableLoottables.gold_horse_armor)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_horse_armor"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.GOLDEN_LEGGINGS), ScrappingTableLoottables.gold_leggings)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_leggings"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.GOLDEN_PICKAXE), ScrappingTableLoottables.gold_pick)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_pick"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.GOLDEN_SHOVEL), ScrappingTableLoottables.gold_shovel)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_shovel"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.GOLDEN_SWORD), ScrappingTableLoottables.gold_sword)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_sword"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.GRINDSTONE), ScrappingTableLoottables.grindstone)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "grindstone"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.IRON_AXE), ScrappingTableLoottables.iron_axe)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_axe"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.IRON_BARS), ScrappingTableLoottables.iron_bars)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_bars"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.IRON_BOOTS), ScrappingTableLoottables.iron_boots)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_boots"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.IRON_CHESTPLATE), ScrappingTableLoottables.iron_chestplate)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_chestplate"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.IRON_DOOR), ScrappingTableLoottables.iron_door)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_door"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.IRON_HELMET), ScrappingTableLoottables.iron_helm)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_helm"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.IRON_HOE), ScrappingTableLoottables.iron_hoe)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_hoe"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.IRON_HORSE_ARMOR), ScrappingTableLoottables.iron_horse_armor)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_horse_armor"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.IRON_LEGGINGS), ScrappingTableLoottables.iron_leggings)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_leggings"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.IRON_PICKAXE), ScrappingTableLoottables.iron_pick)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_pick"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.SHEARS), ScrappingTableLoottables.iron_shears)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_shears"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.IRON_SHOVEL), ScrappingTableLoottables.iron_shovel)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_shovel"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.IRON_SWORD), ScrappingTableLoottables.iron_sword)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_sword"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.IRON_TRAPDOOR), ScrappingTableLoottables.iron_trapdoor)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_trapdoor"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.JUKEBOX), ScrappingTableLoottables.jukebox)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "jukebox"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.LADDER), ScrappingTableLoottables.ladder)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "ladder"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.LEATHER_BOOTS), ScrappingTableLoottables.leather_boots)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(Tags.Items.SHEARS), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "leather_boots"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.LEATHER_CHESTPLATE), ScrappingTableLoottables.leather_chestplate)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(Tags.Items.SHEARS), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "leather_chestplate"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.LEATHER_HELMET), ScrappingTableLoottables.leather_helm)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(Tags.Items.SHEARS), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "leather_helm"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.LEATHER_HORSE_ARMOR), ScrappingTableLoottables.leather_horse_armor)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(Tags.Items.SHEARS), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "leather_horse_armor"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.LEATHER_LEGGINGS), ScrappingTableLoottables.leather_leggings)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(Tags.Items.SHEARS), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "leather_leggings"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.LOOM), ScrappingTableLoottables.loom)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "loom"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.MINECART), ScrappingTableLoottables.minecart)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "minecart"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.PAINTING), ScrappingTableLoottables.painting)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "painting"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.SADDLE), ScrappingTableLoottables.saddle)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "saddle"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.SHIELD), ScrappingTableLoottables.shield)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "shield"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromTag(ItemTags.SIGNS), ScrappingTableLoottables.sign)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "sign"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.SMITHING_TABLE), ScrappingTableLoottables.smithing)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "smithing"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.SMOKER), ScrappingTableLoottables.smoker)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "smoker"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.STONE_AXE), ScrappingTableLoottables.stone_axe)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "stone_axe"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.STONE_HOE), ScrappingTableLoottables.stone_hoe)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "stone_hoe"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.STONE_PICKAXE), ScrappingTableLoottables.stone_pick)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "stone_pick"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.STONE_SHOVEL), ScrappingTableLoottables.stone_shovel)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "stone_shovel"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.STONE_SWORD), ScrappingTableLoottables.stone_sword)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "stone_sword"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.STONECUTTER), ScrappingTableLoottables.stonecutter)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "stonecutter"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromTag(ItemTags.WOODEN_TRAPDOORS), ScrappingTableLoottables.trapdoor)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "trapdoor"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromTag(ItemTags.WALLS), ScrappingTableLoottables.wall)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "wall"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.WOODEN_AXE), ScrappingTableLoottables.wooden_axe)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "wooden_axe"));

	ScrappingTableRecipeBuilder.start(Ingredient.fromItems(Items.WOODEN_HOE), ScrappingTableLoottables.wooden_hoe)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "wooden_hoe"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.WOODEN_PICKAXE), ScrappingTableLoottables.wooden_pick)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "wooden_pick"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.WOODEN_SHOVEL), ScrappingTableLoottables.wooden_shovel)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "wooden_shovel"));

	ScrappingTableRecipeBuilder
		.start(Ingredient.fromItems(Items.WOODEN_SWORD), ScrappingTableLoottables.wooden_sword)
		.addCriterion("has_scrapper", RecipeProvider.hasItem(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
		.tool(Ingredient.fromTag(CompendiumTags.SAW), 2, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "wooden_sword"));
    }

    private void workstations(Consumer<IFinishedRecipe> consumer) {
	ShapedRecipeBuilder.shapedRecipe(CompendiumItems.CRAFTING_ANVIL_ITEMBLOCK.get(), 1)
		.key('c', Items.CRAFTING_TABLE).key('a', Items.ANVIL).key('l', Ingredient.fromTag(ItemTags.LOGS))
		.patternLine("c").patternLine("a").patternLine("l").addCriterion("crafting_anvil", hasItem(Items.ANVIL))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "craft_crafting_anvil"));

	ShapedRecipeBuilder.shapedRecipe(CompendiumItems.HAMMERING_STATION_ITEMBLOCK.get(), 1)
		.key('s', Items.SMOOTH_STONE_SLAB).key('i', Items.STICK).key('l', Ingredient.fromTag(ItemTags.LOGS))
		.patternLine(" s ").patternLine("ili").addCriterion("hammering_station", hasItem(Items.STONE))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "craft_hammering_station"));

	ShapedRecipeBuilder.shapedRecipe(CompendiumItems.SAWHORSE_STATION_ITEMBLOCK.get(), 1).key('s', Items.STICK)
		.key('w', Ingredient.fromTag(ItemTags.PLANKS)).patternLine("w w").patternLine("sws").patternLine("w w")
		.addCriterion("saw_horse", hasItem(Items.STICK))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "craft_saw_horse"));

	ShapedRecipeBuilder.shapedRecipe(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get(), 1)
		.key('c', Ingredient.fromTag(Tags.Items.CHESTS_WOODEN)).key('h', Items.HOPPER).key('b', Items.IRON_BARS)
		.key('i', Items.IRON_INGOT).patternLine(" b ").patternLine("ihi").patternLine("ici")
		.addCriterion("scrapping_table", hasItem(Items.HOPPER))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "craft_scrappingtable"));
    }

}
