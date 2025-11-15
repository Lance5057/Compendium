package com.lance5057.compendium.data;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.lance5057.compendium.CompendiumBlocks;
import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.client.BlacklistedModel;
import com.lance5057.compendium.data.loottables.RecipeLootTables;
import com.lance5057.compendium.data.recipebuilders.HammeringRecipeBuilder;
import com.lance5057.compendium.index.CompendiumIndex;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
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
				.define('p', CompendiumTags.PLANK).pattern("s s").pattern(" s ").pattern("s s")
				.unlockedBy(getName(), has(Items.STICK)).save(consumer);

//		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CompendiumBlocks.HAMMERING_STATION.toStack())
//				.define('p', CompendiumTags.PLANK).define('s', Items.SMOOTH_STONE_SLAB).define('l', ItemTags.LOGS)
//				.pattern("psp").pattern("plp").unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);
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

	}
}
