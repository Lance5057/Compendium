package lance5057.compendium.appendixes.oredressing.data.builders;

import java.util.function.Consumer;

import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.oredressing.AppendixOreDressing;
import lance5057.compendium.appendixes.oredressing.materialhelper.OreDressingMaterialHelper;
import lance5057.compendium.core.data.builders.TCRecipes;
import lance5057.compendium.core.recipes.CustomCookingRecipeBuilder;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class OreRecipes {
    public static void build(TCRecipes recipes, Consumer<IFinishedRecipe> consumer) {

	for (OreDressingMaterialHelper mh : AppendixOreDressing.ores) {

//	    ITagCollection<Item> tags = ItemTags.getCollection();
//
//	    ResourceLocation tag = new ResourceLocation("forge", "ingots/" + mh.name);
//	    ITag<Item> t = tags.get(tag);
//	    Item i = t.getAllElements().get(0);

	    if (mh.hasOre()) {

//		if (i != null)
		CustomCookingRecipeBuilder
			.smeltingRecipe(Ingredient.fromItems(mh.getOre().ITEM_ORE.get()),
				Registry.ITEM.getOrDefault(new ResourceLocation(Reference.MOD_ID, mh.name + "_ingot")),
				1, 0.7F, 200)
			.addCriterion("has_ore", recipes.hasItem(mh.getOre().ITEM_ORE.get())).build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_ore_smelt"));
		
		//HammeringStationRecipeProvider.
	    }
	    if (mh.hasDenseOre()) {

		CustomCookingRecipeBuilder
			.smeltingRecipe(Ingredient.fromItems(mh.getDenseOre().ITEM_ORE.get()),
				Registry.ITEM.getOrDefault(new ResourceLocation(Reference.MOD_ID, mh.name + "_ingot")),
				3, 0.7F, 200)
			.addCriterion("has_ore", recipes.hasItem(mh.getOre().ITEM_ORE.get())).build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_dense_ore_smelt"));
	    }
	    if (mh.hasSparseOre()) {

		CustomCookingRecipeBuilder
			.smeltingRecipe(Ingredient.fromItems(mh.getSparseOre().ITEM_ORE.get()),
				Registry.ITEM.getOrDefault(new ResourceLocation(Reference.MOD_ID, mh.name + "_nugget")),
				3, 0.7F, 200)
			.addCriterion("has_ore", recipes.hasItem(mh.getOre().ITEM_ORE.get())).build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_sparse_ore_smelt"));
	    }
	}
//	for (MetallurgyMaterialHelper mh : AppendixMetallurgy.metals) {
//
//	    if (mh.hasBase()) {
//		MetalMaterialBasic b = mh.getBase();
//
//		ShapelessRecipeBuilder.shapelessRecipe(b.NUGGET.get(), 9).addIngredient(b.INGOT.get(), 1)
//			.addCriterion(mh.name + "ingot", RecipeProvider.hasItem(b.INGOT.get()))
//			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_nugget_from_ingot"));
//		ShapelessRecipeBuilder.shapelessRecipe(b.INGOT.get(), 9).addIngredient(b.STORAGE_ITEMBLOCK.get(), 1)
//			.addCriterion(mh.name + "storage_block", RecipeProvider.hasItem(b.STORAGE_ITEMBLOCK.get()))
//			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_ingot_from_storage_block"));
//
//		ShapelessRecipeBuilder.shapelessRecipe(b.INGOT.get(), 1).addIngredient(b.NUGGET.get(), 9)
//			.addCriterion(mh.name + "nugget", RecipeProvider.hasItem(b.NUGGET.get()))
//			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_ingot_from_nuggets"));
//		ShapelessRecipeBuilder.shapelessRecipe(b.STORAGE_ITEMBLOCK.get(), 1).addIngredient(b.INGOT.get(), 9)
//			.addCriterion(mh.name + "storage_block", RecipeProvider.hasItem(b.INGOT.get())).build(consumer,
//				new ResourceLocation(Reference.MOD_ID, mh.name + "_storage_block_from_ingots"));
//
//	    }
//	    if (mh.hasVanillaTools()) {
//		MetalVanillaTools b = mh.getVanillaTools();
//
//	    }
//	    if (mh.hasComponents()) {
//		MetalMaterialComponents b = mh.getComponents();
//
//		// Coins
//		AnvilShapedRecipeBuilder.shapedRecipe(b.COIN.get(), 4, 4).key('p', mh.getBase().NUGGET.get()).patternLine("pp")
//			.patternLine("pp")
//			.addCriterion(mh.name + "nugget", RecipeProvider.hasItem(mh.getBase().NUGGET.get()))
//			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_coin_from_nuggets"));
//
//		// Gears
//		AnvilShapedRecipeBuilder.shapedRecipe(b.GEAR.get(), 12, 1).key('p', mh.getBase().INGOT.get()).patternLine(" p ")
//			.patternLine("p p").patternLine(" p ")
//			.addCriterion(mh.name + "ingot", RecipeProvider.hasItem(mh.getBase().INGOT.get()))
//			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_gear_from_ingots"));
//
//		// Plates
//		AnvilShapedRecipeBuilder.shapedRecipe(b.PLATE.get(), 8, 1).key('p', mh.getBase().INGOT.get()).patternLine("p")
//			.addCriterion(mh.name + "ingot", RecipeProvider.hasItem(mh.getBase().INGOT.get()))
//			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_plates_from_ingots"));
//
//		// Rods
//		AnvilShapedRecipeBuilder.shapedRecipe(b.ROD.get(), 12, 6).key('p', mh.getBase().INGOT.get()).patternLine("p")
//			.patternLine("p").patternLine("p")
//			.addCriterion(mh.name + "ingot", RecipeProvider.hasItem(mh.getBase().INGOT.get()))
//			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_rods_from_ingots"));
//
//		// Springs
//		AnvilShapedRecipeBuilder.shapedRecipe(b.SPRING.get(), 4, 1).key('p', b.WIRE.get()).key('s', Items.STICK)
//			.patternLine("p").patternLine("s")
//			.addCriterion(mh.name + "wire", RecipeProvider.hasItem(b.WIRE.get()))
//			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_spring_from_wire"));
//
//		// Wire
//		AnvilShapedRecipeBuilder.shapedRecipe(b.WIRE.get(), 8, 4).key('p', b.ROD.get()).patternLine("p")
//			.addCriterion(mh.name + "rod", RecipeProvider.hasItem(b.ROD.get()))
//			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_wires_from_rod"));
//
//		// Casing
//		AnvilShapedRecipeBuilder.shapedRecipe(b.CASING.get(), 10, 1).key('p', b.PLATE.get()).patternLine("ppp")
//			.patternLine("p p").addCriterion(mh.name + "casing", RecipeProvider.hasItem(b.PLATE.get()))
//			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_casing_from_plates"));
//
//		// Coil
//		AnvilShapedRecipeBuilder.shapedRecipe(b.COIL.get(), 4, 1).key('p', b.WIRE.get()).key('s', Items.STICK)
//			.patternLine(" p ").patternLine("psp").patternLine(" p ")
//			.addCriterion(mh.name + "wire", RecipeProvider.hasItem(b.WIRE.get()))
//			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_coil_from_wire"));
//
//		// CLASP
//		AnvilShapedRecipeBuilder.shapedRecipe(b.CLASP.get(), 8, 4).key('p', b.WIRE.get())
//			.key('s', mh.getBase().NUGGET.get()).patternLine("p").patternLine("s")
//			.addCriterion(mh.name + "wire", RecipeProvider.hasItem(b.WIRE.get()))
//			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_clasp_from_wire"));
//
////		RINGSHANK
//		AnvilShapedRecipeBuilder.shapedRecipe(b.RINGSHANK.get(), 8, 4).key('s', b.WIRE.get()).patternLine(" s ")
//			.patternLine("s s").patternLine(" s ")
//			.addCriterion(mh.name + "wire", RecipeProvider.hasItem(b.WIRE.get()))
//			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_ringshank_from_wire"));
//
////		RIVETS
//		AnvilShapedRecipeBuilder.shapedRecipe(b.RIVETS.get(), 8, 4).key('s', b.ROD.get()).patternLine("s")
//			.addCriterion(mh.name + "rod", RecipeProvider.hasItem(b.WIRE.get()))
//			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_rivets_from_rod"));
//
////		SETTING
//		AnvilShapedRecipeBuilder.shapedRecipe(b.SETTING.get(), 8, 4).key('s', b.FILIGREE.get())
//			.key('p', mh.getBase().NUGGET.get()).patternLine(" s ").patternLine("sps").patternLine(" s ")
//			.addCriterion(mh.name + "rod", RecipeProvider.hasItem(b.FILIGREE.get()))
//			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_setting_from_filigree"));
//
////		JUMPRINGS
//		AnvilShapedRecipeBuilder.shapedRecipe(b.JUMPRINGS.get(), 2, 8).key('s', b.WIRE.get()).patternLine("ss")
//			.patternLine("ss")
//			.addCriterion(mh.name + "wire", RecipeProvider.hasItem(b.JUMPRINGS.get()))
//			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_jumprings_from_wire"));
//
////		FILIGREE
//		AnvilShapedRecipeBuilder.shapedRecipe(b.FILIGREE.get(), 4, 6).key('s', b.WIRE.get()).patternLine("s")
//			.patternLine("s").patternLine("s")
//			.addCriterion(mh.name + "wire", RecipeProvider.hasItem(b.WIRE.get()))
//			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_filigree_from_wire"));
//
////		FOIL
//		AnvilShapedRecipeBuilder.shapedRecipe(b.FOIL.get(), 4, 4).key('s', b.PLATE.get()).patternLine("s")
//			.addCriterion(mh.name + "plate", RecipeProvider.hasItem(b.PLATE.get()))
//			.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_foil_from_plate"));
//	    }
//	}
    }
}
