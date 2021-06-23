package lance5057.compendium.core.data.builders;

import java.util.function.Consumer;

import javax.annotation.Nonnull;

import lance5057.compendium.Compendium;
import lance5057.compendium.CompendiumItems;
import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.carpentry.data.CarpentryRecipes;
import lance5057.compendium.appendixes.construction.data.ConstructionRecipes;
import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
import lance5057.compendium.appendixes.metallurgy.data.builders.MetalRecipes;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.appendixes.oredressing.data.builders.OreRecipes;
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
import net.minecraft.tags.BlockTags;
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

	MetalRecipes.build(this, consumer);
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
    }

}
