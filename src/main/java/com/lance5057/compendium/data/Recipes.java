package com.lance5057.compendium.data;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.lance5057.compendium.CompendiumBlocks;
import com.lance5057.compendium.CompendiumItems;
import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.client.BlacklistedModel;
import com.lance5057.compendium.data.loottables.RecipeLootTables;
import com.lance5057.compendium.data.recipebuilders.HammeringRecipeBuilder;
import com.lance5057.compendium.data.recipebuilders.WorkbenchMaterialRecipeBuilder;
import com.lance5057.compendium.data.recipebuilders.WorkbenchRecipeBuilder;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.util.SlotToMaterial;
import com.lance5057.compendium.util.TagUtil;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloat;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloatVector3;
import com.lance5057.compendium.util.rendering.animation.floats.AnimationFloatTransform;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

public class Recipes extends RecipeProvider implements IConditionBuilder {
	public Recipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	@Override
	protected void buildRecipes(RecipeOutput consumer) {
		CompendiumIndex.index.forEach(i -> {
			i.recipes(consumer);
		});

		hammering(consumer);
		workbench(consumer);
		sawing(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CompendiumBlocks.SAW_BUCK.toStack()).define('s', Items.STICK)
				.pattern("s s").pattern(" s ").pattern("s s").unlockedBy(getName(), has(Items.STICK)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CompendiumBlocks.WORKBENCH.toStack())
				.define('p', CompendiumTags.PLANK).define('w', Items.CRAFTING_TABLE).pattern("pp").pattern("pw")
				.unlockedBy(getName(), has(CompendiumTags.PLANK)).save(consumer);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, new ItemStack(Items.STONE, 9))
				.requires(CompendiumItems.MEGALITH_STONE.get())
				.unlockedBy(getName(), has(CompendiumItems.MEGALITH_STONE.get()))
				.save(consumer, TagUtil.modLoc("megalith_to_stone"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CompendiumItems.MEGALITH_STONE.toStack())
				.requires(Items.STONE, 9).unlockedBy(getName(), has(CompendiumItems.MEGALITH_STONE.get()))
				.save(consumer, TagUtil.modLoc("stone_to_megalith"));

	}

	private void sawing(RecipeOutput consumer) {

	}

	public static BlacklistedModel standardHammeringModel(ResourceLocation i, float yOffset) {
		return new BlacklistedModel(i, false,
				new AnimationFloatTransform()
						.setRotation(new AnimatedFloatVector3().setZ(new AnimatedFloat(-45, 45, 0, 0.5f, true, true)))
						.setLocation(new AnimatedFloatVector3().setX(new AnimatedFloat(8, 0))
								.setY(new AnimatedFloat(10 + yOffset, 0)).setZ(new AnimatedFloat(8, 0)))
						.setScale(new AnimatedFloatVector3().setAll(new AnimatedFloat(0.5f))));
	}

	public static BlacklistedModel standardSawBuckAxeModel(ResourceLocation i, float yOffset) {
		return new BlacklistedModel(i, false,
				new AnimationFloatTransform()
						.setRotation(new AnimatedFloatVector3()
								.setY(new AnimatedFloat(0.000F, 180.000F, 0.000F, 0.000F, false, false))
								.setZ(new AnimatedFloat(0.000F, 64.000F, 0.000F, 1.500F, true, true)))
						.setLocation(new AnimatedFloatVector3()
								.setX(new AnimatedFloat(0.000F, -16.000F, 0.000F, 0.000F, false, false))
								.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
						.setScale(new AnimatedFloatVector3()
								.setX(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false, false))
								.setY(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false, false))
								.setZ(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false, false)))
						.setPivot(new AnimatedFloatVector3()
								.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
								.setY(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))));
	}

	public static BlacklistedModel standardSawBuckSawModel(ResourceLocation i, float yOffset) {
		return new BlacklistedModel(i, false,
				new AnimationFloatTransform()
						.setRotation(new AnimatedFloatVector3()
								.setY(new AnimatedFloat(0.000F, 180.000F, 0.000F, 0.000F, false, false))
								.setZ(new AnimatedFloat(0.000F, 45.000F, 0.000F, 0.000F, false, false)))
						.setLocation(new AnimatedFloatVector3()
								.setX(new AnimatedFloat(-14.000F, -6.000F, 0.000F, 0.100F, true, true))
								.setY(new AnimatedFloat(0.000F, 0.000F, 0.000F, 0.100F, true, true))
								.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
						.setScale(new AnimatedFloatVector3()
								.setX(new AnimatedFloat(1.000F, 1.000F, 0.000F, 0.000F, false, false))
								.setY(new AnimatedFloat(1.000F, 1.000F, 0.000F, 0.000F, false, false))
								.setZ(new AnimatedFloat(1.000F, 1.000F, 0.000F, 0.000F, false, false))));
	}

	public static BlacklistedModel standardSawBuckBlockModel(ResourceLocation i, float yOffset) {
		return new BlacklistedModel(i, true,
				new AnimationFloatTransform()
						.setRotation(new AnimatedFloatVector3()
								.setY(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
						.setLocation(new AnimatedFloatVector3()
								.setX(new AnimatedFloat(8.000F, -8.000F, 0.000F, 0.000F, false, false))
								.setY(new AnimatedFloat(-18.000F, -11.600F, 0.000F, 0.000F, false, false))
								.setZ(new AnimatedFloat(0.000F, 24.000F, 0.000F, 0.000F, false, false)))
						.setScale(new AnimatedFloatVector3()
								.setX(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false, false))
								.setY(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false, false))
								.setZ(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false, false))));
	}

	private void hammering(RecipeOutput consumer) {
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.STONE), new ItemStack(Items.COBBLESTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.STONE_TO_COBBLE, List.of(),
						standardHammeringModel(TagUtil.modLoc("gold_hammer"), 0))
				.save(consumer);
	}

	private void workbench(RecipeOutput consumer) {
		WorkbenchRecipeBuilder.shaped(CompendiumBlocks.HAMMERING_STATION.toStack()).define('p', CompendiumTags.PLANK)
				.define('s', Items.SMOOTH_STONE_SLAB).define('l', ItemTags.LOGS).pattern("psp").pattern("plp")
				.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
						Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer"), 0))
				.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder.shaped(CompendiumBlocks.SCRAPPING_TABLE.toStack()).define('p', CompendiumTags.PLANK)
				.define('h', Items.HOPPER).define('c', Items.COPPER_GRATE).pattern("php").pattern("pcp")
				.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
						Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer"), 0))
				.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder.shaped(CompendiumBlocks.TOOLRACK.toStack()).define('p', CompendiumTags.PLANK)
				.define('n', Items.IRON_NUGGET).define('e', Items.ENDER_PEARL).pattern("npn").pattern("pep")
				.pattern("npn")
				.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
						Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer"), 0))
				.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder.shaped(CompendiumBlocks.COMPONENT_DRAWER.toStack()).define('p', CompendiumTags.PLANK)
				.define('n', Items.CHEST).define('e', Items.ENDER_PEARL).pattern("npn").pattern("pep").pattern("npn")
				.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
						Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer"), 0))
				.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder.shaped(CompendiumBlocks.COSMETIC_TOOLBOX.toStack()).define('c', Items.COPPER_BLOCK)
				.define('h', Items.CHEST).define('b', Items.BRUSH).define('p', ItemTags.WOODEN_PRESSURE_PLATES)
				.define('l', Items.BLUE_DYE).define('g', Items.GREEN_DYE).define('r', Items.RED_DYE)
				.define('y', Items.YELLOW_DYE).define('s', Items.STICK).pattern("psb").pattern("lhg").pattern("rcy")
				.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
						Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer"), 0))
				.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchMaterialRecipeBuilder.shaped(CompendiumItems.CHAIR).define('p', Ingredient.of(CompendiumTags.PLANK))
				.define('b', Ingredient.of(ItemTags.PLANKS)).define('s', Ingredient.of(ItemTags.WOODEN_SLABS)).slotToMat(new SlotToMaterial(1, 1))
				.pattern("psp").pattern("pbp").pattern("p p")
				.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
						Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer"), 0))
				.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);
	}
}
