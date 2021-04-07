package lance5057.compendium.core.data.builders;

import java.util.function.Consumer;

import javax.annotation.Nonnull;

import lance5057.compendium.Compendium;
import lance5057.compendium.CompendiumItems;
import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
import lance5057.compendium.appendixes.metallurgy.data.builders.MetalRecipes;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.appendixes.oredressing.data.builders.OreRecipes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class TCRecipes extends RecipeProvider {

    public TCRecipes(DataGenerator generatorIn) {
	super(generatorIn);
	Compendium.logger.info("\t - Recipes");
    }

    @Override
    protected void registerRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {

	workstations(consumer);
//	alloys(consumer);
	hammer(consumer);
	alloys(consumer);

	MetalRecipes.build(this, consumer);
	OreRecipes.build(this, consumer);
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
		.addIngredient(Items.GOLD_INGOT, 3).addIngredient(copper.getComponents().DUST.get(), 1)
		.addCriterion("rosegold_dust_alloy", RecipeProvider.hasItem(copper.getComponents().DUST.get()))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "rosegold_alloy_dust"));
	
	// Electrum
		ShapelessRecipeBuilder.shapelessRecipe(electrum.getComponents().DUST.get(), 2)
			.addIngredient(Items.GOLD_INGOT, 1).addIngredient(copper.getComponents().DUST.get(), 1)
			.addCriterion("electrum_dust_alloy", RecipeProvider.hasItem(silver.getComponents().DUST.get()))
			.build(consumer, new ResourceLocation(Reference.MOD_ID, "electrum_alloy_dust"));

	// Pewter
	ShapelessRecipeBuilder.shapelessRecipe(pewter.getComponents().DUST.get(), 4)
		.addIngredient(tin.getComponents().DUST.get(), 4).addIngredient(lead.getComponents().TINYDUST.get(), 1)
		.addCriterion("pewter_dust_alloy", RecipeProvider.hasItem(tin.getComponents().DUST.get()))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "pewter_alloy_dust_lead"));

	ShapelessRecipeBuilder.shapelessRecipe(pewter.getComponents().DUST.get(), 4)
		.addIngredient(tin.getComponents().DUST.get(), 4)
		.addIngredient(copper.getComponents().TINYDUST.get(), 1)
		.addCriterion("pewter_dust_alloy", RecipeProvider.hasItem(zinc.getComponents().DUST.get()))
		.build(consumer, new ResourceLocation(Reference.MOD_ID, "pewter_alloy_dust_zinc"));
    }

    private void hammer(Consumer<IFinishedRecipe> consumer) {
	// TODO Auto-generated method stub

    }

//    private void neither(MaterialHelper mh, Consumer<IFinishedRecipe> consumer) {
//
//    }
//
//    private void gem(MaterialHelper mh, Consumer<IFinishedRecipe> consumer) {
//	BasicGemMaterial cm = mh.getGem();
//
//	ShapelessRecipeBuilder.shapelessRecipe(cm.SHARD.get(), 9).addIngredient(cm.GEM.get(), 1)
//		.addCriterion(mh.name + "ingot", hasItem(cm.GEM.get()))
//		.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_nuggets_from_gem"));
//	ShapelessRecipeBuilder.shapelessRecipe(cm.GEM.get(), 9).addIngredient(cm.STORAGE_ITEMBLOCK.get(), 1)
//		.addCriterion(mh.name + "storage_block", hasItem(cm.STORAGE_ITEMBLOCK.get()))
//		.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_gems_from_storage_block"));
//
//	ShapelessRecipeBuilder.shapelessRecipe(cm.GEM.get(), 1).addIngredient(cm.SHARD.get(), 9)
//		.addCriterion(mh.name + "nugget", hasItem(cm.SHARD.get()))
//		.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_gem_from_nuggets"));
//	ShapelessRecipeBuilder.shapelessRecipe(cm.STORAGE_ITEMBLOCK.get(), 1).addIngredient(cm.GEM.get(), 9)
//		.addCriterion(mh.name + "storage_block", hasItem(cm.GEM.get()))
//		.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_storage_block_from_gems"));
//
//	// Vanilla Components
//	if (mh.getVanillaComponents() != null) {
//	    MaterialVanillaComponents vc = mh.getVanillaComponents();
//
//	    ShapedRecipeBuilder.shapedRecipe(vc.DOOR.get(), 3).key('p', mh.getExtraComponents().PLATE.get())
//		    .patternLine("pp").patternLine("pp").patternLine("pp")
//		    .addCriterion(mh.name + "door", hasItem(mh.getExtraComponents().PLATE.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_door_from_plates"));
//
//	    ShapedRecipeBuilder.shapedRecipe(vc.BARS.get(), 8).key('p', mh.getExtraComponents().ROD.get())
//		    .patternLine("ppp").patternLine("ppp")
//		    .addCriterion(mh.name + "rod", hasItem(mh.getExtraComponents().ROD.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_bars_from_rods"));
//
//	    ShapedRecipeBuilder.shapedRecipe(vc.LANTERN.get(), 1).key('p', mh.getGem().SHARD.get())
//		    .key('t', Items.TORCH).patternLine("ppp").patternLine("ptp").patternLine("ppp")
//		    .addCriterion(mh.name + "nugget", hasItem(mh.getGem().SHARD.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_lantern_from_nuggets"));
//
//	    ShapedRecipeBuilder.shapedRecipe(vc.TRAPDOOR.get(), 1).key('p', mh.getExtraComponents().PLATE.get())
//		    .patternLine("pp").patternLine("pp")
//		    .addCriterion(mh.name + "plate", hasItem(mh.getExtraComponents().PLATE.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_trapdoor_from_plates"));
//	}
//
//	// Extra Components
//	if (mh.getExtraComponents() != null) {
//	    MaterialExtraComponents ec = mh.getExtraComponents();
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.COIN.get(), 4).key('p', mh.getGem().SHARD.get()).patternLine("pp")
//		    .patternLine("pp").addCriterion(mh.name + "nugget", hasItem(mh.getGem().SHARD.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_coin_from_nuggets"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.GEAR.get(), 1).key('p', mh.getGem().GEM.get()).patternLine(" p ")
//		    .patternLine("p p").patternLine(" p ").addCriterion(mh.name + "gem", hasItem(mh.getGem().GEM.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_gear_from_gems"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.PLATE.get(), 2).key('p', mh.getGem().GEM.get()).patternLine("p")
//		    .patternLine("p").addCriterion(mh.name + "gem", hasItem(mh.getGem().GEM.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_plates_from_gems"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.ROD.get(), 6).key('p', mh.getGem().GEM.get()).patternLine("p")
//		    .patternLine("p").patternLine("p").addCriterion(mh.name + "gem", hasItem(mh.getGem().GEM.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_rods_from_gemss"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.ITEM_SHEET.get(), 3).key('p', mh.getExtraComponents().PLATE.get())
//		    .patternLine("ppp").addCriterion(mh.name + "plate", hasItem(mh.getExtraComponents().PLATE.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_sheets_from_plates"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.ITEM_SHINGLES.get(), 4).key('p', mh.getExtraComponents().PLATE.get())
//		    .key('s', Items.STICK).key('l', Ingredient.fromTag(ItemTags.LOGS)).patternLine("  p")
//		    .patternLine(" ps").patternLine("psl")
//		    .addCriterion(mh.name + "plate", hasItem(mh.getExtraComponents().PLATE.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_shingles_from_plates"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.ITEM_SHINGLES_ALT.get(), 4)
//		    .key('p', mh.getExtraComponents().PLATE.get()).key('s', Items.STICK)
//		    .key('l', Ingredient.fromTag(ItemTags.LOGS)).patternLine("  p").patternLine(" pl")
//		    .patternLine("pls").addCriterion(mh.name + "plate", hasItem(mh.getExtraComponents().PLATE.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_shingles_alt_from_plates"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.ITEM_SHINGLES_BLOCK.get(), 4)
//		    .key('p', mh.getExtraComponents().PLATE.get()).key('l', Ingredient.fromTag(ItemTags.LOGS))
//		    .patternLine(" p ").patternLine("plp").patternLine(" p ")
//		    .addCriterion(mh.name + "plate", hasItem(mh.getExtraComponents().PLATE.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_shingles_block_from_plates"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.ITEM_STAKE.get(), 3).key('p', mh.getExtraComponents().ROD.get())
//		    .patternLine("p").patternLine("p").patternLine("p")
//		    .addCriterion(mh.name + "rod", hasItem(mh.getExtraComponents().ROD.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_stakes_from_rods"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.DUST.get(), 1).key('p', mh.getGem().GEM.get())
//		    .key('s', Blocks.COBBLESTONE).patternLine("s").patternLine("p")
//		    .addCriterion(mh.name + "gem", hasItem(mh.getGem().GEM.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_dust_from_gem"));
//
//	    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ec.DUST.get()), mh.getGem().GEM.get(), 1f, 100)
//		    .addCriterion(mh.name + "dust", hasItem(ec.DUST.get()))
//		    .build(consumer, mh.name + "ingot_from_dust_furnace");
//	    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ec.DUST.get()), mh.getGem().GEM.get(), 1f, 100)
//		    .addCriterion(mh.name + "dust", hasItem(ec.DUST.get()))
//		    .build(consumer, mh.name + "ingot_from_dust_blast");
//
//	}
//
//	// Advanced Extra Component Materials
//	if (mh.getAdvancedComponents() != null) {
//	    MaterialAdvancedExtraComponents ec = mh.getAdvancedComponents();
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.CASING.get(), 1).key('p', mh.getExtraComponents().PLATE.get())
//		    .patternLine("ppp").patternLine("p p")
//		    .addCriterion(mh.name + "casing", hasItem(mh.getExtraComponents().PLATE.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_casing_from_plates"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.COIL.get(), 1).key('p', ec.WIRE.get()).key('s', Items.STICK)
//		    .patternLine(" p ").patternLine("psp").patternLine(" p ")
//		    .addCriterion(mh.name + "wire", hasItem(ec.WIRE.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_coil_from_wire"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.SPRING.get(), 1).key('p', ec.WIRE.get()).key('s', Items.STICK)
//		    .patternLine("p").patternLine("s").addCriterion(mh.name + "wire", hasItem(ec.WIRE.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_spring_from_wire"));
//
//	    ShapelessRecipeBuilder.shapelessRecipe(ec.WIRE.get(), 4).addIngredient(mh.getExtraComponents().ROD.get())
//		    .addCriterion(mh.name + "rod", hasItem(mh.getExtraComponents().ROD.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_wires_from_rod"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.VAULT_ITEMBLOCK.get(), 1).key('c', ec.CASING.get())
//		    .key('e', Items.ENDER_PEARL).key('b', Items.BARREL).patternLine(" c ").patternLine("ebe")
//		    .patternLine(" c ").addCriterion(mh.name + "vault", hasItem(ec.CASING.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_vault"));
//	}
//
//	// Vanilla Tools
//	if (mh.getVanillaTools() != null) {
//	    MaterialVanillaTools vt = mh.getVanillaTools();
//
//	    ShapedRecipeBuilder.shapedRecipe(vt.AXE.get(), 1).key('i', mh.getGem().GEM.get()).key('s', Items.STICK)
//		    .patternLine("ii").patternLine("is").patternLine(" s")
//		    .addCriterion(mh.name + "gem", hasItem(mh.getGem().GEM.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_axe_from_gems"));
//
//	    ShapedRecipeBuilder.shapedRecipe(vt.HOE.get(), 1).key('i', mh.getGem().GEM.get()).key('s', Items.STICK)
//		    .patternLine("ii").patternLine(" s").patternLine(" s")
//		    .addCriterion(mh.name + "gem", hasItem(mh.getGem().GEM.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_hoe_from_gems"));
//
//	    ShapedRecipeBuilder.shapedRecipe(vt.PICKAXE.get(), 1).key('i', mh.getGem().GEM.get()).key('s', Items.STICK)
//		    .patternLine("iii").patternLine(" s ").patternLine(" s ")
//		    .addCriterion(mh.name + "gem", hasItem(mh.getGem().GEM.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_pickaxe_from_gems"));
//
//	    ShapedRecipeBuilder.shapedRecipe(vt.SHOVEL.get(), 1).key('i', mh.getGem().GEM.get()).key('s', Items.STICK)
//		    .patternLine("i").patternLine("s").patternLine("s")
//		    .addCriterion(mh.name + "gem", hasItem(mh.getGem().GEM.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_shovel_from_gems"));
//
//	    ShapedRecipeBuilder.shapedRecipe(vt.SWORD.get(), 1).key('i', mh.getGem().GEM.get()).key('s', Items.STICK)
//		    .patternLine("i").patternLine("i").patternLine("s")
//		    .addCriterion(mh.name + "gem", hasItem(mh.getGem().GEM.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_sword_from_gems"));
//	}
//
//	if (mh.getOre() != null) {
//	    MaterialOre mo = mh.getOre();
//
//	    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(mo.ITEM_ORE.get()), mh.getGem().GEM.get(), 1f, 100)
//		    .addCriterion(mh.name + "ore", hasItem(mo.ITEM_ORE.get()))
//		    .build(consumer, mh.name + "gem_from_ore_furnace");
//	    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(mo.ITEM_ORE.get()), mh.getGem().GEM.get(), 1f, 100)
//		    .addCriterion(mh.name + "ore", hasItem(mo.ITEM_ORE.get()))
//		    .build(consumer, mh.name + "gem_from_ore_blast");
//
//	}
//    }
//
//    private void ingot(MaterialHelper mh, Consumer<IFinishedRecipe> consumer) {
//
//	BasicMetalMaterial mm = mh.getIngot();
//
//	ShapelessRecipeBuilder.shapelessRecipe(mm.NUGGET.get(), 9).addIngredient(mm.INGOT.get(), 1)
//		.addCriterion(mh.name + "ingot", hasItem(mm.INGOT.get()))
//		.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_nugget_from_ingot"));
//	ShapelessRecipeBuilder.shapelessRecipe(mm.INGOT.get(), 9).addIngredient(mm.STORAGE_ITEMBLOCK.get(), 1)
//		.addCriterion(mh.name + "storage_block", hasItem(mm.STORAGE_ITEMBLOCK.get()))
//		.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_ingot_from_storage_block"));
//
//	ShapelessRecipeBuilder.shapelessRecipe(mm.INGOT.get(), 1).addIngredient(mm.NUGGET.get(), 9)
//		.addCriterion(mh.name + "nugget", hasItem(mm.NUGGET.get()))
//		.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_ingot_from_nuggets"));
//	ShapelessRecipeBuilder.shapelessRecipe(mm.STORAGE_ITEMBLOCK.get(), 1).addIngredient(mm.INGOT.get(), 9)
//		.addCriterion(mh.name + "storage_block", hasItem(mm.INGOT.get()))
//		.build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_storage_block_from_ingots"));
//
//	// Vanilla Components
//	if (mh.getVanillaComponents() != null) {
//	    MaterialVanillaComponents vc = mh.getVanillaComponents();
//
//	    ShapedRecipeBuilder.shapedRecipe(vc.DOOR.get(), 3).key('p', mh.getExtraComponents().PLATE.get())
//		    .patternLine("pp").patternLine("pp").patternLine("pp")
//		    .addCriterion(mh.name + "door", hasItem(mh.getExtraComponents().PLATE.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_door_from_plates"));
//
//	    ShapedRecipeBuilder.shapedRecipe(vc.BARS.get(), 8).key('p', mh.getExtraComponents().ROD.get())
//		    .patternLine("ppp").patternLine("ppp")
//		    .addCriterion(mh.name + "rod", hasItem(mh.getExtraComponents().ROD.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_bars_from_rods"));
//
//	    ShapedRecipeBuilder.shapedRecipe(vc.LANTERN.get(), 1).key('p', mh.getIngot().NUGGET.get())
//		    .key('t', Items.TORCH).patternLine("ppp").patternLine("ptp").patternLine("ppp")
//		    .addCriterion(mh.name + "nugget", hasItem(mh.getIngot().NUGGET.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_lantern_from_nuggets"));
//
//	    ShapedRecipeBuilder.shapedRecipe(vc.TRAPDOOR.get(), 1).key('p', mh.getExtraComponents().PLATE.get())
//		    .patternLine("pp").patternLine("pp")
//		    .addCriterion(mh.name + "plate", hasItem(mh.getExtraComponents().PLATE.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_trapdoor_from_plates"));
//	}
//
//	// Extra Components
//	if (mh.getExtraComponents() != null) {
//	    MaterialExtraComponents ec = mh.getExtraComponents();
//
//	    // Coins
//	    ShapedRecipeBuilder.shapedRecipe(ec.COIN.get(), 4).key('p', mh.getIngot().NUGGET.get()).patternLine("pp")
//		    .patternLine("pp").addCriterion(mh.name + "nugget", hasItem(mh.getIngot().NUGGET.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_coin_from_nuggets"));
//
//	    // Gears
//	    ShapedRecipeBuilder.shapedRecipe(ec.GEAR.get(), 1).key('p', mh.getIngot().INGOT.get()).patternLine(" p ")
//		    .patternLine("p p").patternLine(" p ")
//		    .addCriterion(mh.name + "ingot", hasItem(mh.getIngot().INGOT.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_gear_from_ingots"));
//
//	    // Plates
//	    ShapedRecipeBuilder.shapedRecipe(ec.PLATE.get(), 2).key('p', mh.getIngot().INGOT.get()).patternLine("p")
//		    .patternLine("p").addCriterion(mh.name + "ingot", hasItem(mh.getIngot().INGOT.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_plates_from_ingots"));
//
//	    // Rods
//	    ShapedRecipeBuilder.shapedRecipe(ec.ROD.get(), 6).key('p', mh.getIngot().INGOT.get()).patternLine("p")
//		    .patternLine("p").patternLine("p")
//		    .addCriterion(mh.name + "ingot", hasItem(mh.getIngot().INGOT.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_rods_from_ingots"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.ITEM_SHEET.get(), 3).key('p', mh.getExtraComponents().PLATE.get())
//		    .patternLine("ppp").addCriterion(mh.name + "plate", hasItem(mh.getExtraComponents().PLATE.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_sheets_from_plates"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.ITEM_SHINGLES.get(), 4).key('p', mh.getExtraComponents().PLATE.get())
//		    .key('s', Items.STICK).key('l', Ingredient.fromTag(ItemTags.LOGS)).patternLine("  p")
//		    .patternLine(" ps").patternLine("psl")
//		    .addCriterion(mh.name + "plate", hasItem(mh.getExtraComponents().PLATE.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_shingles_from_plates"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.ITEM_SHINGLES_ALT.get(), 4)
//		    .key('p', mh.getExtraComponents().PLATE.get()).key('s', Items.STICK)
//		    .key('l', Ingredient.fromTag(ItemTags.LOGS)).patternLine("  p").patternLine(" pl")
//		    .patternLine("pls").addCriterion(mh.name + "plate", hasItem(mh.getExtraComponents().PLATE.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_shingles_alt_from_plates"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.ITEM_SHINGLES_BLOCK.get(), 4)
//		    .key('p', mh.getExtraComponents().PLATE.get()).key('l', Ingredient.fromTag(ItemTags.LOGS))
//		    .patternLine(" p ").patternLine("plp").patternLine(" p ")
//		    .addCriterion(mh.name + "plate", hasItem(mh.getExtraComponents().PLATE.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_shingles_block_from_plates"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.ITEM_STAKE.get(), 3).key('p', mh.getExtraComponents().ROD.get())
//		    .patternLine("p").patternLine("p").patternLine("p")
//		    .addCriterion(mh.name + "rod", hasItem(mh.getExtraComponents().ROD.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_stakes_from_rods"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.DUST.get(), 1).key('p', mh.getIngot().INGOT.get())
//		    .key('s', Blocks.COBBLESTONE).patternLine("s").patternLine("p")
//		    .addCriterion(mh.name + "ingot", hasItem(mh.getIngot().INGOT.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_dust_from_ingot"));
//
//	    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(ec.DUST.get()), mh.getIngot().INGOT.get(), 1f, 100)
//		    .addCriterion(mh.name + "dust", hasItem(ec.DUST.get()))
//		    .build(consumer, mh.name + "ingot_from_dust_furnace");
//	    CookingRecipeBuilder.blastingRecipe(Ingredient.fromItems(ec.DUST.get()), mh.getIngot().INGOT.get(), 1f, 100)
//		    .addCriterion(mh.name + "dust", hasItem(ec.DUST.get()))
//		    .build(consumer, mh.name + "ingot_from_dust_blast");
//
//	}
//
//	// Advanced Extra Component Materials
//	if (mh.getAdvancedComponents() != null) {
//	    MaterialAdvancedExtraComponents ec = mh.getAdvancedComponents();
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.SPRING.get(), 1).key('p', ec.WIRE.get()).key('s', Items.STICK)
//		    .patternLine("p").patternLine("s").addCriterion(mh.name + "wire", hasItem(ec.WIRE.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_spring_from_wire"));
//
//	    ShapelessRecipeBuilder.shapelessRecipe(ec.WIRE.get(), 4).addIngredient(mh.getExtraComponents().ROD.get())
//		    .addCriterion(mh.name + "rod", hasItem(mh.getExtraComponents().ROD.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_wires_from_rod"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.CASING.get(), 1).key('p', mh.getExtraComponents().PLATE.get())
//		    .patternLine("ppp").patternLine("p p")
//		    .addCriterion(mh.name + "casing", hasItem(mh.getExtraComponents().PLATE.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_casing_from_plates"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.COIL.get(), 1).key('p', ec.WIRE.get()).key('s', Items.STICK)
//		    .patternLine(" p ").patternLine("psp").patternLine(" p ")
//		    .addCriterion(mh.name + "wire", hasItem(ec.WIRE.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_coil_from_wire"));
//
//	    ShapedRecipeBuilder.shapedRecipe(ec.VAULT_ITEMBLOCK.get(), 1).key('c', ec.CASING.get())
//		    .key('e', Items.ENDER_PEARL).key('b', Items.BARREL).patternLine(" c ").patternLine("ebe")
//		    .patternLine(" c ").addCriterion(mh.name + "vault", hasItem(ec.CASING.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_vault"));
//	}
//
//	// Vanilla Tools
//	if (mh.getVanillaTools() != null) {
//	    MaterialVanillaTools vt = mh.getVanillaTools();
//
//	    ShapedRecipeBuilder.shapedRecipe(vt.AXE.get(), 1).key('i', mh.getIngot().INGOT.get()).key('s', Items.STICK)
//		    .patternLine("ii").patternLine("is").patternLine(" s")
//		    .addCriterion(mh.name + "ingot", hasItem(mh.getIngot().INGOT.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_axe_from_ingots"));
//
//	    ShapedRecipeBuilder.shapedRecipe(vt.HOE.get(), 1).key('i', mh.getIngot().INGOT.get()).key('s', Items.STICK)
//		    .patternLine("ii").patternLine(" s").patternLine(" s")
//		    .addCriterion(mh.name + "ingot", hasItem(mh.getIngot().INGOT.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_hoe_from_ingots"));
//
//	    ShapedRecipeBuilder.shapedRecipe(vt.PICKAXE.get(), 1).key('i', mh.getIngot().INGOT.get())
//		    .key('s', Items.STICK).patternLine("iii").patternLine(" s ").patternLine(" s ")
//		    .addCriterion(mh.name + "ingot", hasItem(mh.getIngot().INGOT.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_pickaxe_from_ingots"));
//
//	    ShapedRecipeBuilder.shapedRecipe(vt.SHOVEL.get(), 1).key('i', mh.getIngot().INGOT.get())
//		    .key('s', Items.STICK).patternLine("i").patternLine("s").patternLine("s")
//		    .addCriterion(mh.name + "ingot", hasItem(mh.getIngot().INGOT.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_shovel_from_ingots"));
//
//	    ShapedRecipeBuilder.shapedRecipe(vt.SWORD.get(), 1).key('i', mh.getIngot().INGOT.get())
//		    .key('s', Items.STICK).patternLine("i").patternLine("i").patternLine("s")
//		    .addCriterion(mh.name + "ingot", hasItem(mh.getIngot().INGOT.get()))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, mh.name + "_sword_from_ingots"));
//
//	}
//
//	if (mh.getOre() != null) {
//	    MaterialOre mo = mh.getOre();
//	    CookingRecipeBuilder
//		    .smeltingRecipe(Ingredient.fromItems(mo.ITEM_ORE.get()), mh.getIngot().INGOT.get(), 1f, 100)
//		    .addCriterion(mh.name + "ore", hasItem(mo.ITEM_ORE.get()))
//		    .build(consumer, mh.name + "ingot_from_ore_furnace");
//	    CookingRecipeBuilder
//		    .blastingRecipe(Ingredient.fromItems(mo.ITEM_ORE.get()), mh.getIngot().INGOT.get(), 1f, 100)
//		    .addCriterion(mh.name + "ore", hasItem(mo.ITEM_ORE.get()))
//		    .build(consumer, mh.name + "ingot_from_ore_blast");
//
//	}
//    }

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

//    void alloys(Consumer<IFinishedRecipe> consumer) {
//	if (CompendiumMaterials.AEONSTEEL != null)
//	    ShapelessRecipeBuilder.shapelessRecipe(CompendiumMaterials.AEONSTEEL.getExtraComponents().DUST.get(), 1)
//		    .addIngredient(Ingredient.fromTag(TCItemTags.ItemTag("dusts/silver")), 2)
//		    .addIngredient(Ingredient.fromTag(TCItemTags.ItemTag("dusts/obsidian")), 1)
//		    .addIngredient(Items.DRAGON_BREATH, 1).addCriterion("learn_aeonsteel", hasItem(Items.DRAGON_BREATH))
//		    .build(consumer, new ResourceLocation(Reference.MOD_ID, "aeonsteel_alloy"));
//
//    }

}
