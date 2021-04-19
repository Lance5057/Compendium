package lance5057.compendium.appendixes.metallurgy.data.builders;

import java.util.function.Consumer;

import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalAdvancedTools;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialBasic;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialComponents;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalVanillaTools;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.data.builders.workstationrecipes.AnvilShapedRecipeBuilder;
import lance5057.compendium.core.data.builders.workstationrecipes.HammeringStationRecipeProvider;
import lance5057.compendium.core.recipes.CustomCookingRecipeBuilder;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class MetalRecipes {
    public static void build(TCRecipes recipes, Consumer<IFinishedRecipe> consumer) {
	for (MetallurgyMaterialHelper mh : AppendixMetallurgy.metals) {

	    if (mh.hasBase()) {
		MetalMaterialBasic b = mh.getBase();

		ShapelessRecipeBuilder.shapelessRecipe(b.NUGGET.get(), 9)
			.addIngredient(Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + mh.name)), 1)
			.addCriterion(mh.name + "ingot",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_nugget_from_ingot"));

		ShapelessRecipeBuilder.shapelessRecipe(b.INGOT.get(), 9)
			.addIngredient(Ingredient.fromTag(TCItemTags.ItemTag("storage_blocks/" + mh.name)), 1)
			.addCriterion(mh.name + "storage_block",
				RecipeProvider.hasItem(TCItemTags.ItemTag("storage_blocks/" + mh.name)))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_ingot_from_storage_block"));

		ShapelessRecipeBuilder.shapelessRecipe(b.INGOT.get(), 1)
			.addIngredient(Ingredient.fromTag(TCItemTags.ItemTag("nugget/" + mh.name)), 9)
			.addCriterion(mh.name + "nugget",
				RecipeProvider.hasItem(TCItemTags.ItemTag("nuggets/" + mh.name)))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_ingot_from_nuggets"));

		ShapelessRecipeBuilder.shapelessRecipe(b.STORAGE_ITEMBLOCK.get(), 1)
			.addIngredient(Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + mh.name)), 9)
			.addCriterion(mh.name + "storage_block",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))
			.build(consumer,
				new ResourceLocation(Reference.MOD_ID, mh.name + "_storage_block_from_ingots"));

	    }
	    if (mh.hasVanillaTools()) {
		MetalVanillaTools b = mh.getVanillaTools();

		// Sword
		AnvilShapedRecipeBuilder.shapedRecipe(b.SWORD.get(), 16, 1)
			.key('i', Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + mh.name)))
			.key('r', Ingredient.fromTag(TCItemTags.ItemTag("rods/" + mh.name))).key('s', Items.STICK)
			.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + mh.name)))
			.key('n', Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + mh.name))).patternLine(" i ")
			.patternLine(" i ").patternLine("rso").patternLine(" n ")
			.addCriterion(mh.name + "ingot",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))

			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_sword"));

		// Pickaxe
		AnvilShapedRecipeBuilder.shapedRecipe(b.PICKAXE.get(), 16, 1)
			.key('i', Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + mh.name))).key('s', Items.STICK)
			.key('r', Ingredient.fromTag(TCItemTags.ItemTag("rods/" + mh.name)))
			.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + mh.name)))
			.key('n', Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + mh.name))).patternLine(" o ")
			.patternLine("rir").patternLine(" s ").patternLine(" s ").patternLine(" n ")
			.addCriterion(mh.name + "ingot",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))

			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_pickaxe"));

		// Axe
		AnvilShapedRecipeBuilder.shapedRecipe(b.AXE.get(), 16, 1)
			.key('i', Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + mh.name))).key('s', Items.STICK)
			.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + mh.name)))
			.key('n', Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + mh.name))).patternLine(" o ")
			.patternLine("ii ").patternLine("is ").patternLine(" s ").patternLine(" n ")
			.addCriterion(mh.name + "ingot",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))

			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_axe"));

		// Shovel
		AnvilShapedRecipeBuilder.shapedRecipe(b.SHOVEL.get(), 16, 1)
			.key('i', Ingredient.fromTag(TCItemTags.ItemTag("plates/" + mh.name))).key('s', Items.STICK)
			.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + mh.name)))
			.key('n', Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + mh.name))).patternLine(" i ")
			.patternLine(" so").patternLine(" s ").patternLine(" n ")
			.addCriterion(mh.name + "ingot",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))

			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_shovel"));

		// Hoe
		AnvilShapedRecipeBuilder.shapedRecipe(b.HOE.get(), 16, 1)
			.key('i', Ingredient.fromTag(TCItemTags.ItemTag("plates/" + mh.name))).key('s', Items.STICK)
			.key('r', Ingredient.fromTag(TCItemTags.ItemTag("rods/" + mh.name)))
			.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + mh.name)))
			.key('n', Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + mh.name))).patternLine(" o ")
			.patternLine("ir ").patternLine(" s ").patternLine(" s ").patternLine(" n ")
			.addCriterion(mh.name + "ingot",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))

			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_hoe"));
	    }
	    if (mh.hasComponents()) {
		MetalMaterialComponents b = mh.getComponents();

		// Coins
		AnvilShapedRecipeBuilder.shapedRecipe(b.COIN.get(), 4, 4)
			.key('p', Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + mh.name))).patternLine("pp")
			.patternLine("pp")
			.addCriterion(mh.name + "nugget",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_coin_from_nuggets"));

		// Gears
		AnvilShapedRecipeBuilder.shapedRecipe(b.GEAR.get(), 12, 1)
			.key('p', Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + mh.name))).patternLine(" p ")
			.patternLine("p p").patternLine(" p ")
			.addCriterion(mh.name + "ingot",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))

			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_gear_from_ingots"));

		// Plates
		AnvilShapedRecipeBuilder.shapedRecipe(b.PLATE.get(), 8, 1)
			.key('p', Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + mh.name))).patternLine("p")
			.addCriterion(mh.name + "ingot",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))

			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_plates_from_ingots"));

		// Rods
		AnvilShapedRecipeBuilder.shapedRecipe(b.ROD.get(), 12, 6)
			.key('p', Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + mh.name))).patternLine("p")
			.patternLine("p").patternLine("p")
			.addCriterion(mh.name + "ingot",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))

			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_rods_from_ingots"));

		// Springs
		AnvilShapedRecipeBuilder.shapedRecipe(b.SPRING.get(), 4, 1)
			.key('p', Ingredient.fromTag(TCItemTags.ItemTag("wires/" + mh.name))).key('s', Items.STICK)
			.patternLine("p").patternLine("s")
			.addCriterion(mh.name + "wire", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_spring_from_wire"));

		// Wire
		AnvilShapedRecipeBuilder.shapedRecipe(b.WIRE.get(), 8, 4)
			.key('p', Ingredient.fromTag(TCItemTags.ItemTag("rods/" + mh.name))).patternLine("p")
			.addCriterion(mh.name + "rod", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_wires_from_rod"));

		// Casing
		AnvilShapedRecipeBuilder.shapedRecipe(b.CASING.get(), 10, 1)
			.key('p', Ingredient.fromTag(TCItemTags.ItemTag("plates/" + mh.name))).patternLine("ppp")
			.patternLine("p p")
			.addCriterion(mh.name + "casing",
				RecipeProvider.hasItem(TCItemTags.ItemTag("plates/" + mh.name)))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_casing_from_plates"));

		// Coil
		AnvilShapedRecipeBuilder.shapedRecipe(b.COIL.get(), 4, 1)
			.key('p', Ingredient.fromTag(TCItemTags.ItemTag("wires/" + mh.name))).key('s', Items.STICK)
			.patternLine(" p ").patternLine("psp").patternLine(" p ")
			.addCriterion(mh.name + "wire", RecipeProvider.hasItem(TCItemTags.ItemTag("wires/" + mh.name)))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_coil_from_wire"));

		// CLASP
		AnvilShapedRecipeBuilder.shapedRecipe(b.CLASP.get(), 8, 4)
			.key('p', Ingredient.fromTag(TCItemTags.ItemTag("wires/" + mh.name)))
			.key('s', Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + mh.name))).patternLine("p")
			.patternLine("s")
			.addCriterion(mh.name + "wire", RecipeProvider.hasItem(TCItemTags.ItemTag("wires/" + mh.name)))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_clasp_from_wire"));

//		RINGSHANK
		AnvilShapedRecipeBuilder.shapedRecipe(b.RINGSHANK.get(), 8, 4)
			.key('s', Ingredient.fromTag(TCItemTags.ItemTag("wires/" + mh.name))).patternLine(" s ")
			.patternLine("s s").patternLine(" s ")
			.addCriterion(mh.name + "wire", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_ringshank_from_wire"));

//		RIVETS
		AnvilShapedRecipeBuilder.shapedRecipe(b.RIVETS.get(), 8, 4)
			.key('s', Ingredient.fromTag(TCItemTags.ItemTag("rods/" + mh.name))).patternLine("s")
			.addCriterion(mh.name + "rod", RecipeProvider.hasItem(TCItemTags.ItemTag("wires/" + mh.name)))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_rivets_from_rod"));

//		SETTING
		AnvilShapedRecipeBuilder.shapedRecipe(b.SETTING.get(), 8, 4)
			.key('s', Ingredient.fromTag(TCItemTags.ItemTag("filigree/" + mh.name)))
			.key('p', Ingredient.fromTag(TCItemTags.ItemTag("nuggets/" + mh.name))).patternLine(" s ")
			.patternLine("sps").patternLine(" s ")
			.addCriterion(mh.name + "rod",
				RecipeProvider.hasItem(TCItemTags.ItemTag("filigree/" + mh.name)))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_setting_from_filigree"));

//		JUMPRINGS
		AnvilShapedRecipeBuilder.shapedRecipe(b.JUMPRINGS.get(), 2, 8)
			.key('s', Ingredient.fromTag(TCItemTags.ItemTag("wires/" + mh.name))).patternLine("ss")
			.patternLine("ss")
			.addCriterion(mh.name + "wire", RecipeProvider.hasItem(TCItemTags.ItemTag("wires/" + mh.name)))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_jumprings_from_wire"));

//		FILIGREE
		AnvilShapedRecipeBuilder.shapedRecipe(b.FILIGREE.get(), 4, 6)
			.key('s', Ingredient.fromTag(TCItemTags.ItemTag("wires/" + mh.name))).patternLine("s")
			.patternLine("s").patternLine("s")
			.addCriterion(mh.name + "wire", RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_filigree_from_wire"));

//		FOIL
		AnvilShapedRecipeBuilder.shapedRecipe(b.FOIL.get(), 4, 4)
			.key('s', Ingredient.fromTag(TCItemTags.ItemTag("plates/" + mh.name))).patternLine("s")
			.addCriterion(mh.name + "plate",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_foil_from_plate"));

		// Dust
		ShapelessRecipeBuilder.shapelessRecipe(b.DUST.get(), 1)
			.addIngredient(Ingredient.fromTag(TCItemTags.ItemTag("tiny_dusts/" + mh.name)), 9)
			.addCriterion(mh.name + "tinydust",
				RecipeProvider.hasItem(TCItemTags.ItemTag("tiny_dusts/" + mh.name)))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_dust_from_tinydust"));

		ShapelessRecipeBuilder.shapelessRecipe(b.TINYDUST.get(), 9)
			.addIngredient(Ingredient.fromTag(TCItemTags.ItemTag("dusts/" + mh.name)), 1)
			.addCriterion(mh.name + "dust", RecipeProvider.hasItem(TCItemTags.ItemTag("dusts/" + mh.name)))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_tinydust_from_dust"));

		CustomCookingRecipeBuilder
			.smeltingRecipe(Ingredient.fromItems(b.TINYDUST.get()),
				Registry.ITEM.getOrDefault(new ResourceLocation(mh.parentMod, mh.name + "_nugget")), 1,
				0.7F, 200)
			.addCriterion("has_tinydust",
				RecipeProvider.hasItem(TCItemTags.ItemTag("tiny_dusts/" + mh.name)))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_tinydust_smelt"));

		CustomCookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(b.DUST.get()),
			Registry.ITEM.getOrDefault(new ResourceLocation(mh.parentMod, mh.name + "_ingot")), 1, 0.7F, 200)
			.addCriterion("has_tinydust", RecipeProvider.hasItem(TCItemTags.ItemTag("dusts/" + mh.name)))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_dust_smelt"));
	    }
	    if (mh.hasAdvancedTools()) {
		MetalAdvancedTools b = mh.getAdvancedTools();

		// Hammer
		AnvilShapedRecipeBuilder.shapedRecipe(b.HAMMER.get(), 16, 1)
			.key('i', Ingredient.fromTag(TCItemTags.ItemTag("ingots/" + mh.name)))
			.key('r', Ingredient.fromTag(TCItemTags.ItemTag("rods/" + mh.name))).key('s', Items.STICK)
			.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + mh.name)))
			.key('n', Ingredient.fromTag(TCItemTags.ItemTag("nuggetss/" + mh.name))).patternLine("ioi")
			.patternLine("iri").patternLine(" s ").patternLine(" n ")
			.addCriterion(mh.name + "ingot",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))

			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_hammer"));

		// Bow
		AnvilShapedRecipeBuilder.shapedRecipe(b.BOW.get(), 16, 1).key('i', mh.getComponents().PLATE.get())
			.key('r', Ingredient.fromTag(TCItemTags.ItemTag("rods/" + mh.name))).key('s', Items.STICK)
			.key('o', Items.STRING).key('n', Ingredient.fromTag(TCItemTags.ItemTag("nuggetss/" + mh.name)))
			.patternLine("  n").patternLine("iso").patternLine("r o").patternLine("iso").patternLine("  n")
			.addCriterion(mh.name + "ingot",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))

			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_bow"));

		// Saw
		AnvilShapedRecipeBuilder.shapedRecipe(b.SAW.get(), 16, 1).key('i', mh.getComponents().PLATE.get())
			.key('r', Ingredient.fromTag(TCItemTags.ItemTag("rods/" + mh.name))).key('s', Items.STICK)
			.key('o', Ingredient.fromTag(TCItemTags.ItemTag("rivets/" + mh.name))).patternLine("ir ")
			.patternLine(" ir").patternLine(" os")
			.addCriterion(mh.name + "ingot",
				RecipeProvider.hasItem(TCItemTags.ItemTag("ingots/" + mh.name)))

			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_saw"));
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
