package lance5057.compendium.appendixes.metallurgy.data.builders;

import java.util.function.Consumer;

import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalAdvancedTools;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialBasic;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialComponents;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialDefense;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalVanillaTools;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.data.builders.workstationrecipes.HammeringStationRecipeProvider;
import lance5057.compendium.core.data.builders.workstationrecipes.builders.AnvilShapedRecipeBuilder;
import lance5057.compendium.core.library.CompendiumTags;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class MetalRecipes {
    public static void build(TCRecipes recipes, Consumer<IFinishedRecipe> consumer) {
	for (MetallurgyMaterialHelper mh : AppendixMetallurgy.metals) {

	    if (mh.hasBase()) {
		MetalMaterialBasic.buildRecipes(mh.getBase(), recipes, consumer, mh.name);

	    }
	    if (mh.hasVanillaTools()) {
		MetalVanillaTools.buildRecipes(mh.getVanillaTools(), recipes, consumer, mh.name);

	    }
	    if (mh.hasComponents()) {
		MetalMaterialComponents.buildRecipes(mh.getComponents(), recipes, consumer, mh.name, mh.parentMod);

	    }
	    if (mh.hasAdvancedTools()) {
		MetalAdvancedTools b = mh.getAdvancedTools();

		// Hammer
		AnvilShapedRecipeBuilder.shapedRecipe(b.HAMMER.get(), 1)
			.key('i', Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + mh.name)))
			.key('r', Ingredient.fromTag(TCItemTags.ItemTag("rods/" + mh.name))).key('s', Items.STICK)
			.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + mh.name)))
			.key('n', Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + mh.name))).patternLine("ioi")
			.patternLine("iri").patternLine(" s ").patternLine(" n ")
			.addCriterion(mh.name + "ingot",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))
			.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 16, true)
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_hammer"));

		// Bow
		AnvilShapedRecipeBuilder.shapedRecipe(b.BOW.get(), 1).key('i', mh.getComponents().PLATE.get())
			.key('r', Ingredient.fromTag(TCItemTags.ItemTag("rods/" + mh.name))).key('s', Items.STICK)
			.key('o', Items.STRING).key('n', Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + mh.name)))
			.patternLine("  n").patternLine("iso").patternLine("r o").patternLine("iso").patternLine("  n")
			.addCriterion(mh.name + "ingot",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))
			.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 16, true)
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_bow"));

		// Saw
		AnvilShapedRecipeBuilder.shapedRecipe(b.SAW.get(), 1).key('i', mh.getComponents().PLATE.get())
			.key('r', Ingredient.fromTag(TCItemTags.ItemTag("rods/" + mh.name))).key('s', Items.STICK)
			.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + mh.name))).patternLine("ir ")
			.patternLine(" ir").patternLine(" os")
			.addCriterion(mh.name + "ingot",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))
			.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 16, true)
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_saw"));

		AnvilShapedRecipeBuilder.shapedRecipe(b.WRENCH.get(), 1).key('s', Items.STICK)
			.key('r', Ingredient.fromTag(TCItemTags.ItemTag("gears/" + mh.name)))
			.key('o', Ingredient.fromTag(TCItemTags.ItemTag("ringshanks/" + mh.name))).patternLine("r  ")
			.patternLine(" s ").patternLine("  o")
			.addCriterion(mh.name + "ingot",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))
			.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 16, true)
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_wrench"));

		AnvilShapedRecipeBuilder.shapedRecipe(b.PLIERS.get(), 1).key('s', Items.STICK)
			.key('r', Ingredient.fromTag(TCItemTags.ItemTag("rods/" + mh.name)))
			.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + mh.name))).patternLine("r r")
			.patternLine(" o ").patternLine("s s")
			.addCriterion(mh.name + "ingot",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))
			.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 16, true)
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_pliers"));
		
		AnvilShapedRecipeBuilder.shapedRecipe(b.PLIERS.get(), 1).key('s', Items.STICK)
		.key('r', Ingredient.fromTag(TCItemTags.ItemTag("rods/" + mh.name))).patternLine("  r")
		.patternLine(" s ").patternLine("r  ")
		.addCriterion(mh.name + "ingot",
			RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))
		.tool(Ingredient.fromTag(CompendiumTags.HAMMER), 16, true)
		.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_prybar"));
	    }
	    
	    if (mh.hasDefense()) {
		MetalMaterialDefense.buildRecipes(mh.getDefense(), recipes, consumer, mh.name);
	    }
	}
    }

    public static void hammeringBuild(HammeringStationRecipeProvider station, Consumer<IFinishedRecipe> consumer) {
	for (MetallurgyMaterialHelper mh : AppendixMetallurgy.metals) {
	    if (mh.hasBase() && mh.hasComponents()) {
		MetalMaterialBasic b = mh.getBase();
		MetalMaterialComponents c = mh.getComponents();
		station.createRecipe(mh.name + "_ingot_to_dust", new ItemStack(c.DUST.get()),
			Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + mh.name)), consumer, 1);
	    }
	}
    }
}
