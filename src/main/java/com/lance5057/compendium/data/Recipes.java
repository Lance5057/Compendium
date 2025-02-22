package com.lance5057.compendium.data;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.client.BlacklistedModel;
import com.lance5057.compendium.data.loottables.RecipeLootTables;
import com.lance5057.compendium.data.recipebuilders.HammeringRecipeBuilder;
import com.lance5057.compendium.util.TagUtil;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloat;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloatVector3;
import com.lance5057.compendium.util.rendering.animation.floats.AnimationFloatTransform;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
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
		hammering(consumer);
	}

	BlacklistedModel standardHammeringModel(ResourceLocation i, float yOffset) {
		return new BlacklistedModel(i, false,
				new AnimationFloatTransform()
						.setRotation(new AnimatedFloatVector3().setZ(new AnimatedFloat(-45, 45, 0, 0.5f, true, true)))
						.setLocation(new AnimatedFloatVector3().setX(new AnimatedFloat(8, 0))
								.setY(new AnimatedFloat(10 + yOffset, 0)).setZ(new AnimatedFloat(8, 0)))
						.setScale(new AnimatedFloatVector3().setAll(new AnimatedFloat(0.5f))));
	}

	private void hammering(RecipeOutput consumer) {
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.STONE), new ItemStack(Items.COBBLESTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.STONE_TO_COBBLE, List.of(),
						standardHammeringModel(TagUtil.modLoc("tin_hammer"), 0))
				.save(consumer);
	}
}
