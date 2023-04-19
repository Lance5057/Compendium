package lance5057.compendium.core.data.builders;

import java.util.function.Consumer;

import javax.annotation.Nonnull;

import lance5057.compendium.Compendium;
import lance5057.compendium.CompendiumTags;
import lance5057.compendium.Reference;
import lance5057.compendium.core.client.BlacklistedModel;
import lance5057.compendium.core.data.builders.workstationrecipes.builders.AnvilShapedRecipeBuilder;
import lance5057.compendium.core.data.builders.workstationrecipes.builders.HammeringStationRecipeBuilder;
import lance5057.compendium.core.data.builders.workstationrecipes.builders.SawBuckRecipeBuilder;
import lance5057.compendium.core.data.builders.workstationrecipes.loottables.HammeringStationLootTables;
import lance5057.compendium.core.data.builders.workstationrecipes.loottables.SawBuckRecipeLoottables;
import lance5057.compendium.core.util.rendering.animation.floats.AnimatedFloat;
import lance5057.compendium.core.util.rendering.animation.floats.AnimatedFloatVector3;
import lance5057.compendium.core.util.rendering.animation.floats.AnimationFloatTransform;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

public class TCRecipes extends RecipeProvider {

	public TCRecipes(DataGenerator generatorIn) {
		super(generatorIn);
		Compendium.logger.info("\t - Recipes");
	}

	@Override
	protected void buildCraftingRecipes(@Nonnull Consumer<FinishedRecipe> consumer) {

//		for (MaterialHelper m : CompendiumMaterials.materials) {
//			m.addRecipes(this, consumer);
//		}

//		AnimationFloatTransform ySpin = new AnimationFloatTransform().setRotation(new AnimatedFloatVector3().setY(new AnimatedFloat(0, 360, 1, 0, true, false)));

		AnvilShapedRecipeBuilder.shapedRecipe(Items.IRON_INGOT, 1).key('s', Items.STICK).pattern("sssss")
				.pattern("s   s").pattern("sssss").addCriterion("stupid_ingot", RecipeProvider.has(Items.STICK))
				.tool(Ingredient.of(Items.STONE), 16, true, new BlacklistedModel(Items.DIAMOND_SWORD,
						new AnimationFloatTransform().setRotation(
								new AnimatedFloatVector3().setY(new AnimatedFloat(0, 360, 0, 0.1f, true, false)))),
						new BlacklistedModel(Items.GOLDEN_SWORD,
								new AnimationFloatTransform().setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0, 360, 60, 0.1f, true, false)))),
						new BlacklistedModel(Items.IRON_SWORD,
								new AnimationFloatTransform().setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0, 360, 120, 0.1f, true, false)))),
						new BlacklistedModel(Items.NETHERITE_SWORD,
								new AnimationFloatTransform().setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0, 360, 180, 0.1f, true, false)))),
						new BlacklistedModel(Items.STONE_SWORD,
								new AnimationFloatTransform().setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0, 360, 240, 0.1f, true, false)))),
						new BlacklistedModel(Items.WOODEN_SWORD,
								new AnimationFloatTransform().setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0, 360, 300, 0.1f, true, false)))))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "lol_hammer"));

		SawBuckRecipeBuilder
				.recipe(Ingredient.of(Items.OAK_LOG), SawBuckRecipeLoottables.oak_log,
						new ItemStack(Items.STRIPPED_OAK_LOG, 1))
				.addCriterion("log", RecipeProvider.has(Items.OAK_LOG))
				.tool(Ingredient.of(CompendiumTags.AXES), 1, true, new BlacklistedModel(Items.DIAMOND_AXE,
						new AnimationFloatTransform().setRotation(
								new AnimatedFloatVector3().setY(new AnimatedFloat(0, 360, 0, 0.1f, true, false)))))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "oak_log"));

		SawBuckRecipeBuilder
				.recipe(Ingredient.of(Items.STRIPPED_OAK_LOG), SawBuckRecipeLoottables.stripped_oak_log,
						new ItemStack(Items.OAK_PLANKS, 4))
				.addCriterion("log", RecipeProvider.has(Items.STRIPPED_OAK_LOG))
				.tool(Ingredient.of(CompendiumTags.AXES), 1, true, new BlacklistedModel(Items.DIAMOND_AXE,
						new AnimationFloatTransform().setRotation(
								new AnimatedFloatVector3().setY(new AnimatedFloat(0, 360, 0, 0.1f, true, false)))))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "oak_stripped_log"));

		SawBuckRecipeBuilder
				.recipe(Ingredient.of(ItemTags.PLANKS), SawBuckRecipeLoottables.allplanks,
						new ItemStack(Items.STICK, 4))
				.addCriterion("log", RecipeProvider.has(Items.STRIPPED_OAK_LOG))
				.tool(Ingredient.of(CompendiumTags.AXES), 1, true, new BlacklistedModel(Items.DIAMOND_AXE,
						new AnimationFloatTransform().setRotation(
								new AnimatedFloatVector3().setY(new AnimatedFloat(0, 360, 0, 0.1f, true, false)))))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "oak_planks"));

		HammeringStationRecipeBuilder
				.recipe(Ingredient.of(Tags.Items.COBBLESTONE), HammeringStationLootTables.cobble_gravel,
						new ItemStack(Items.GRAVEL, 1))
				.addCriterion("cobble", RecipeProvider.has(Tags.Items.COBBLESTONE))
				.tool(Ingredient.of(CompendiumTags.PICKAXES), 2, true, new BlacklistedModel(Items.DIAMOND_PICKAXE,
						new AnimationFloatTransform().setRotation(
								new AnimatedFloatVector3().setY(new AnimatedFloat(0, 360, 0, 0.1f, true, false)))))
				.save(consumer, new ResourceLocation(Reference.MOD_ID, "cobble_gravel_pick"));

//		SpecialRecipeBuilder.special(RecipeSerializer.SHIELD_DECORATION).save(consumer, "shield_decoration");
//
//		ShapedRecipeBuilder.shaped(CompendiumItems.CRUDE_HAMMER.get(), 1).define('s', Ingredient.of(Tags.Items.STONE))
//				.define('a', Ingredient.of(Tags.Items.STRING)).define('w', Ingredient.of(Tags.Items.RODS_WOODEN))
//				.pattern(" s ").pattern("awa").pattern(" w ").unlockedBy("stick_get", has(Items.STICK))
//				.save(consumer, new ResourceLocation(Reference.MOD_ID, "craft_crude_hammer"));
//
//		ShapedRecipeBuilder.shaped(CompendiumItems.MINER_GRENADE.get(), 1).define('t', Ingredient.of(Items.TNT))
//				.define('p', Ingredient.of(Items.IRON_INGOT)).define('f', Ingredient.of(Items.FLINT)).pattern("tft")
//				.pattern("fpf").pattern("tft").unlockedBy("iron_ingot_aquired", has(Items.IRON_INGOT))
//				.save(consumer, new ResourceLocation(Reference.MOD_ID, "craft_grenade"));
//
//		SingleItemRecipeBuilder.stonecutting(Ingredient.of(CompendiumItems.MEGALITH_STONE.get()), Items.STONE, 9)
//				.unlockedBy("has_megalith_stone", has(CompendiumItems.MEGALITH_STONE.get()))
//				.save(consumer, new ResourceLocation(Reference.MOD_ID, "crack_megalith"));
//
//		workstations(consumer);
//		hammer(consumer);
//		alloys(consumer);
//		scrapping(consumer);
//
//		MetalRecipes.build(this, consumer);
//		GemRecipes.build(this, consumer);
//		OreRecipes.build(this, consumer);
//		CarpentryRecipes.build(this, consumer);
//		ConstructionRecipes.build(this, consumer);
////	for (MaterialHelper mh : CompendiumMaterials.materials) {
////
////	    if (mh.getIngot() != null)
////		ingot(mh, consumer);
////	    else if (mh.getGem() != null)
////		gem(mh, consumer);
//////	    else
//////		neither(mh, consumer);
////	}
	}
//
//	private void alloys(Consumer<FinishedRecipe> consumer) {
//
//		// Brass
//		MetallurgyMaterialHelper brass = AppendixMetallurgy.metals.stream().filter(i -> i.name == "brass").findFirst()
//				.orElse(null);
//		MetallurgyMaterialHelper copper = AppendixMetallurgy.metals.stream().filter(i -> i.name == "copper").findFirst()
//				.orElse(null);
//		MetallurgyMaterialHelper zinc = AppendixMetallurgy.metals.stream().filter(i -> i.name == "zinc").findFirst()
//				.orElse(null);
//		MetallurgyMaterialHelper tin = AppendixMetallurgy.metals.stream().filter(i -> i.name == "tin").findFirst()
//				.orElse(null);
//		MetallurgyMaterialHelper pewter = AppendixMetallurgy.metals.stream().filter(i -> i.name == "pewter").findFirst()
//				.orElse(null);
//		MetallurgyMaterialHelper bronze = AppendixMetallurgy.metals.stream().filter(i -> i.name == "bronze").findFirst()
//				.orElse(null);
//		MetallurgyMaterialHelper lead = AppendixMetallurgy.metals.stream().filter(i -> i.name == "lead").findFirst()
//				.orElse(null);
//		MetallurgyMaterialHelper rosegold = AppendixMetallurgy.metals.stream().filter(i -> i.name == "rosegold")
//				.findFirst().orElse(null);
//		MetallurgyMaterialHelper electrum = AppendixMetallurgy.metals.stream().filter(i -> i.name == "electrum")
//				.findFirst().orElse(null);
//		MetallurgyMaterialHelper silver = AppendixMetallurgy.metals.stream().filter(i -> i.name == "silver").findFirst()
//				.orElse(null);
//		MetallurgyMaterialHelper gold = AppendixMetallurgy.metals.stream().filter(i -> i.name == "gold").findFirst()
//				.orElse(null);
//		MetallurgyMaterialHelper iron = AppendixMetallurgy.metals.stream().filter(i -> i.name == "iron").findFirst()
//				.orElse(null);
//		MetallurgyMaterialHelper steel = AppendixMetallurgy.metals.stream().filter(i -> i.name == "steel").findFirst()
//				.orElse(null);
//
//		// Brass
//		ShapelessRecipeBuilder.shapeless(brass.getComponents().DUST.get(), 3)
//				.requires(copper.getComponents().DUST.get(), 2).requires(zinc.getComponents().DUST.get(), 1)
//				.unlockedBy("brass_dust_alloy", RecipeProvider.has(copper.getComponents().DUST.get()))
//				.save(consumer, new ResourceLocation(Reference.MOD_ID, "brass_alloy_dust"));
//
//		// Bronze
//		ShapelessRecipeBuilder.shapeless(bronze.getComponents().DUST.get(), 4)
//				.requires(copper.getComponents().DUST.get(), 3).requires(tin.getComponents().DUST.get(), 1)
//				.unlockedBy("bronze_dust_alloy", RecipeProvider.has(copper.getComponents().DUST.get()))
//				.save(consumer, new ResourceLocation(Reference.MOD_ID, "bronze_alloy_dust"));
//
//		// Rosegold
//		ShapelessRecipeBuilder.shapeless(rosegold.getComponents().DUST.get(), 4)
//				.requires(gold.getComponents().DUST.get(), 3).requires(copper.getComponents().DUST.get(), 1)
//				.unlockedBy("rosegold_dust_alloy", RecipeProvider.has(copper.getComponents().DUST.get()))
//				.save(consumer, new ResourceLocation(Reference.MOD_ID, "rosegold_alloy_dust"));
//
//		// Electrum
//		ShapelessRecipeBuilder.shapeless(electrum.getComponents().DUST.get(), 2)
//				.requires(gold.getComponents().DUST.get(), 1).requires(silver.getComponents().DUST.get(), 1)
//				.unlockedBy("electrum_dust_alloy", RecipeProvider.has(silver.getComponents().DUST.get()))
//				.save(consumer, new ResourceLocation(Reference.MOD_ID, "electrum_alloy_dust"));
//
//		// Pewter
//		ShapelessRecipeBuilder.shapeless(pewter.getComponents().DUST.get(), 3)
//				.requires(tin.getComponents().DUST.get(), 1).requires(lead.getComponents().DUST.get(), 1)
//				.requires(zinc.getComponents().DUST.get(), 1)
//				.unlockedBy("pewter_dust_alloy", RecipeProvider.has(tin.getComponents().DUST.get()))
//				.save(consumer, new ResourceLocation(Reference.MOD_ID, "pewter_alloy_dust"));
//
//		// Steel
//		ShapelessRecipeBuilder.shapeless(steel.getComponents().DUST.get(), 1)
//				.requires(iron.getComponents().DUST.get(), 1).requires(Ingredient.of(ItemTags.COALS))
//				.unlockedBy("electrum_dust_alloy", RecipeProvider.has(silver.getComponents().DUST.get()))
//				.save(consumer, new ResourceLocation(Reference.MOD_ID, "steel_alloy_dust"));
//	}
//
//	private void hammer(Consumer<FinishedRecipe> consumer) {
//		// TODO Auto-generated method stub
//
//	}
//
//	private void scrapping(Consumer<FinishedRecipe> consumer) {
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.ANVIL), ScrappingTableLoottables.anvil)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_anvil"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(ItemTags.BANNERS), ScrappingTableLoottables.banner)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(Tags.Items.SHEARS), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_banner"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.BARREL), ScrappingTableLoottables.barrel)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_barrel"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(ItemTags.BEDS), ScrappingTableLoottables.bed)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_bed"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.BLAST_FURNACE), ScrappingTableLoottables.blast)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_blast"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.BOWL), ScrappingTableLoottables.bowl)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_bowl"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.CARTOGRAPHY_TABLE), ScrappingTableLoottables.carto)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_carto"));
//
//		ScrappingTableRecipeBuilder
//				.start(Ingredient.of(Items.CHAINMAIL_BOOTS), ScrappingTableLoottables.chainmail_boots)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_chainmail_boots"));
//
//		ScrappingTableRecipeBuilder
//				.start(Ingredient.of(Items.CHAINMAIL_CHESTPLATE), ScrappingTableLoottables.chainmail_chestplate)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_chainmail_chestplate"));
//
//		ScrappingTableRecipeBuilder
//				.start(Ingredient.of(Items.CHAINMAIL_HELMET), ScrappingTableLoottables.chainmail_helm)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_chainmail_helm"));
//
//		ScrappingTableRecipeBuilder
//				.start(Ingredient.of(Items.CHAINMAIL_LEGGINGS), ScrappingTableLoottables.chainmail_leggings)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_chainmail_leggings"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.CHEST), ScrappingTableLoottables.chest)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_chest"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.CHIPPED_ANVIL), ScrappingTableLoottables.chippedanvil)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_chippedanvil"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.COMPOSTER), ScrappingTableLoottables.composter)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_composter"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.CRAFTING_TABLE), ScrappingTableLoottables.craftingtable)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_craftingtable"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.DAMAGED_ANVIL), ScrappingTableLoottables.damagedanvil)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_damagedanvil"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.DIAMOND_AXE), ScrappingTableLoottables.diamond_axe)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_axe"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.DIAMOND_BOOTS), ScrappingTableLoottables.diamond_boots)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_boots"));
//
//		ScrappingTableRecipeBuilder
//				.start(Ingredient.of(Items.DIAMOND_CHESTPLATE), ScrappingTableLoottables.diamond_chestplate)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_chestplate"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.DIAMOND_HELMET), ScrappingTableLoottables.diamond_helm)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_helm"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.DIAMOND_HOE), ScrappingTableLoottables.diamond_hoe)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_hoe"));
//
//		ScrappingTableRecipeBuilder
//				.start(Ingredient.of(Items.DIAMOND_HORSE_ARMOR), ScrappingTableLoottables.diamond_horse_armor)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_horse_armor"));
//
//		ScrappingTableRecipeBuilder
//				.start(Ingredient.of(Items.DIAMOND_LEGGINGS), ScrappingTableLoottables.diamond_leggings)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_leggings"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.DIAMOND_PICKAXE), ScrappingTableLoottables.diamond_pick)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_pick"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.DIAMOND_SHOVEL), ScrappingTableLoottables.diamond_shovel)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_shovel"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.DIAMOND_SWORD), ScrappingTableLoottables.diamond_sword)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "diamond_sword"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(ItemTags.WOODEN_DOORS), ScrappingTableLoottables.door)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "door"));
//
//		ScrappingTableRecipeBuilder
//				.start(Ingredient.of(Items.ENCHANTING_TABLE), ScrappingTableLoottables.enchantingtable)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "enchantingtable"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.ENDER_CHEST), ScrappingTableLoottables.enderchest)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "enderchest"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(ItemTags.WOODEN_FENCES), ScrappingTableLoottables.fence)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "fence"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.FLETCHING_TABLE), ScrappingTableLoottables.fletch)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "fletch"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.ITEM_FRAME), ScrappingTableLoottables.frame)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "frame"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.FURNACE), ScrappingTableLoottables.furnace)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "furnace"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Tags.Items.FENCE_GATES_WOODEN), ScrappingTableLoottables.gate)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gate"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.GOLDEN_AXE), ScrappingTableLoottables.gold_axe)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_axe"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.GOLDEN_BOOTS), ScrappingTableLoottables.gold_boots)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_boots"));
//
//		ScrappingTableRecipeBuilder
//				.start(Ingredient.of(Items.GOLDEN_CHESTPLATE), ScrappingTableLoottables.gold_chestplate)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_chestplate"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.GOLDEN_HELMET), ScrappingTableLoottables.gold_helm)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_helm"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.GOLDEN_HOE), ScrappingTableLoottables.gold_hoe)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_hoe"));
//
//		ScrappingTableRecipeBuilder
//				.start(Ingredient.of(Items.GOLDEN_HORSE_ARMOR), ScrappingTableLoottables.gold_horse_armor)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_horse_armor"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.GOLDEN_LEGGINGS), ScrappingTableLoottables.gold_leggings)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_leggings"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.GOLDEN_PICKAXE), ScrappingTableLoottables.gold_pick)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_pick"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.GOLDEN_SHOVEL), ScrappingTableLoottables.gold_shovel)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_shovel"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.GOLDEN_SWORD), ScrappingTableLoottables.gold_sword)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "gold_sword"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.GRINDSTONE), ScrappingTableLoottables.grindstone)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "grindstone"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.IRON_AXE), ScrappingTableLoottables.iron_axe)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_axe"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.IRON_BARS), ScrappingTableLoottables.iron_bars)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_bars"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.IRON_BOOTS), ScrappingTableLoottables.iron_boots)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_boots"));
//
//		ScrappingTableRecipeBuilder
//				.start(Ingredient.of(Items.IRON_CHESTPLATE), ScrappingTableLoottables.iron_chestplate)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_chestplate"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.IRON_DOOR), ScrappingTableLoottables.iron_door)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_door"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.IRON_HELMET), ScrappingTableLoottables.iron_helm)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_helm"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.IRON_HOE), ScrappingTableLoottables.iron_hoe)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_hoe"));
//
//		ScrappingTableRecipeBuilder
//				.start(Ingredient.of(Items.IRON_HORSE_ARMOR), ScrappingTableLoottables.iron_horse_armor)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_horse_armor"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.IRON_LEGGINGS), ScrappingTableLoottables.iron_leggings)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_leggings"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.IRON_PICKAXE), ScrappingTableLoottables.iron_pick)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_pick"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.SHEARS), ScrappingTableLoottables.iron_shears)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_shears"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.IRON_SHOVEL), ScrappingTableLoottables.iron_shovel)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_shovel"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.IRON_SWORD), ScrappingTableLoottables.iron_sword)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_sword"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.IRON_TRAPDOOR), ScrappingTableLoottables.iron_trapdoor)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "iron_trapdoor"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.JUKEBOX), ScrappingTableLoottables.jukebox)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "jukebox"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.LADDER), ScrappingTableLoottables.ladder)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "ladder"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.LEATHER_BOOTS), ScrappingTableLoottables.leather_boots)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(Tags.Items.SHEARS), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "leather_boots"));
//
//		ScrappingTableRecipeBuilder
//				.start(Ingredient.of(Items.LEATHER_CHESTPLATE), ScrappingTableLoottables.leather_chestplate)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(Tags.Items.SHEARS), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "leather_chestplate"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.LEATHER_HELMET), ScrappingTableLoottables.leather_helm)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(Tags.Items.SHEARS), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "leather_helm"));
//
//		ScrappingTableRecipeBuilder
//				.start(Ingredient.of(Items.LEATHER_HORSE_ARMOR), ScrappingTableLoottables.leather_horse_armor)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(Tags.Items.SHEARS), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "leather_horse_armor"));
//
//		ScrappingTableRecipeBuilder
//				.start(Ingredient.of(Items.LEATHER_LEGGINGS), ScrappingTableLoottables.leather_leggings)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(Tags.Items.SHEARS), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "leather_leggings"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.LOOM), ScrappingTableLoottables.loom)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "loom"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.MINECART), ScrappingTableLoottables.minecart)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "minecart"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.PAINTING), ScrappingTableLoottables.painting)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "painting"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.SADDLE), ScrappingTableLoottables.saddle)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "saddle"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.SHIELD), ScrappingTableLoottables.shield)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "shield"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(ItemTags.SIGNS), ScrappingTableLoottables.sign)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "sign"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.SMITHING_TABLE), ScrappingTableLoottables.smithing)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "smithing"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.SMOKER), ScrappingTableLoottables.smoker)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "smoker"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.STONE_AXE), ScrappingTableLoottables.stone_axe)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "stone_axe"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.STONE_HOE), ScrappingTableLoottables.stone_hoe)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "stone_hoe"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.STONE_PICKAXE), ScrappingTableLoottables.stone_pick)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "stone_pick"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.STONE_SHOVEL), ScrappingTableLoottables.stone_shovel)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "stone_shovel"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.STONE_SWORD), ScrappingTableLoottables.stone_sword)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "stone_sword"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.STONECUTTER), ScrappingTableLoottables.stonecutter)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "stonecutter"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(ItemTags.WOODEN_TRAPDOORS), ScrappingTableLoottables.trapdoor)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "trapdoor"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(ItemTags.WALLS), ScrappingTableLoottables.wall)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "wall"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.WOODEN_AXE), ScrappingTableLoottables.wooden_axe)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "wooden_axe"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.WOODEN_HOE), ScrappingTableLoottables.wooden_hoe)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "wooden_hoe"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.WOODEN_PICKAXE), ScrappingTableLoottables.wooden_pick)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "wooden_pick"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.WOODEN_SHOVEL), ScrappingTableLoottables.wooden_shovel)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "wooden_shovel"));
//
//		ScrappingTableRecipeBuilder.start(Ingredient.of(Items.WOODEN_SWORD), ScrappingTableLoottables.wooden_sword)
//				.addCriterion("has_scrapper", RecipeProvider.has(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get()))
//				.tool(Ingredient.of(CompendiumTags.SAW), 2, true)
//				.build(consumer, new ResourceLocation(Reference.MOD_ID, "scrap_" + "wooden_sword"));
//	}
//
//	private void workstations(Consumer<FinishedRecipe> consumer) {
//		ShapedRecipeBuilder.shaped(CompendiumItems.CRAFTING_ANVIL_ITEMBLOCK.get(), 1).define('c', Items.CRAFTING_TABLE)
//				.define('a', Items.ANVIL).define('l', Ingredient.of(ItemTags.LOGS)).pattern("c").pattern("a")
//				.pattern("l").unlockedBy("crafting_anvil", has(Items.ANVIL))
//				.save(consumer, new ResourceLocation(Reference.MOD_ID, "craft_crafting_anvil"));
//
//		ShapedRecipeBuilder.shaped(CompendiumItems.HAMMERING_STATION_ITEMBLOCK.get(), 1)
//				.define('s', Items.SMOOTH_STONE_SLAB).define('i', Items.STICK).define('l', Ingredient.of(ItemTags.LOGS))
//				.pattern(" s ").pattern("ili").unlockedBy("hammering_station", has(Items.STONE))
//				.save(consumer, new ResourceLocation(Reference.MOD_ID, "craft_hammering_station"));
//
//		ShapedRecipeBuilder.shaped(CompendiumItems.SAWHORSE_STATION_ITEMBLOCK.get(), 1).define('s', Items.STICK)
//				.define('w', Ingredient.of(ItemTags.PLANKS)).pattern("w w").pattern("sws").pattern("w w")
//				.unlockedBy("saw_horse", has(Items.STICK))
//				.save(consumer, new ResourceLocation(Reference.MOD_ID, "craft_saw_horse"));
//
//		ShapedRecipeBuilder.shaped(CompendiumItems.SCRAPPING_TABLE_ITEMBLOCK.get(), 1)
//				.define('c', Ingredient.of(Tags.Items.CHESTS_WOODEN)).define('h', Items.HOPPER)
//				.define('b', Items.IRON_BARS).define('i', Items.IRON_INGOT).pattern(" b ").pattern("ihi").pattern("ici")
//				.unlockedBy("scrapping_table", has(Items.HOPPER))
//				.save(consumer, new ResourceLocation(Reference.MOD_ID, "craft_scrappingtable"));
//	}

}
