package com.lance5057.compendium.blocks.clothedtable;

import java.util.List;

import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.client.BlacklistedModel;
import com.lance5057.compendium.data.loottables.RecipeLootTables;
import com.lance5057.compendium.data.recipebuilders.WorkbenchRecipeBuilder;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloat;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloatVector3;
import com.lance5057.compendium.util.rendering.animation.floats.AnimationFloatTransform;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;

public class ClothedTableRecipeData {
	public static WorkbenchRecipeBuilder tableStage1(WorkbenchRecipeBuilder builder) {
		return builder
				.tool(Ingredient.of(CompendiumTags.SAW), 2, true, RecipeLootTables.EMPTY, List.of(),
						new BlacklistedModel(ResourceLocation.parse("compendium:iron_saw_item"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(-45.000F, 135.000F, 0.000F, 0.000F, false,
														false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -0.700F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -4.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 27.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
												.setY(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
												.setZ(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setZ(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 4.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setZ(
												new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -3.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:oak_slab"), false,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 5.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 24.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder tableStage2(WorkbenchRecipeBuilder builder) {
		return builder
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						new BlacklistedModel(ResourceLocation.parse("compendium:iron_hammer_item"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(-45.000F, 45.000F, 0.000F, 0.000F, false,
														false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -0.700F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 27.500F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
												.setY(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
												.setZ(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 5.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 18.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 17.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 4.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setZ(
												new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -3.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/top"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 16.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder tableStage3(WorkbenchRecipeBuilder builder) {
		return builder
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						new BlacklistedModel(ResourceLocation.parse("compendium:iron_hammer_item"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(-45.000F, 45.000F, 0.000F, 0.000F, false,
														false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -0.700F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 27.500F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
												.setY(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
												.setZ(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 5.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 18.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 17.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -5.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 20.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 17.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setZ(
												new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -3.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/top"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 16.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder tableStage4(WorkbenchRecipeBuilder builder) {
		return builder
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						new BlacklistedModel(ResourceLocation.parse("compendium:iron_hammer_item"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(-45.000F, 45.000F, 0.000F, 0.000F, false,
														false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -0.700F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 27.500F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
												.setY(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
												.setZ(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 5.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 18.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 17.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -5.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 20.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 17.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setZ(
												new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -5.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 18.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 28.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/top"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 16.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder tableStage5(WorkbenchRecipeBuilder builder) {
		return builder
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						new BlacklistedModel(ResourceLocation.parse("compendium:iron_hammer_item"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(-45.000F, 45.000F, 0.000F, 0.000F, false,
														false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -0.700F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 27.500F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
												.setY(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
												.setZ(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 5.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 18.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 17.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -5.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 20.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 17.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setZ(
												new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -5.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 18.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 28.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 5.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 18.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 27.500F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/top"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 16.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder tableStage6(WorkbenchRecipeBuilder builder) {
		return builder
				.tool(Ingredient.of(Tags.Items.TOOLS_SHEAR), 2, true, RecipeLootTables.EMPTY, List.of(),
						new BlacklistedModel(ResourceLocation.parse("compendium:iron_hammer_item"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(-45.000F, 45.000F, 0.000F, 0.000F, false,
														false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -0.700F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 27.500F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
												.setY(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
												.setZ(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 5.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 18.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 17.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -5.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 20.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 17.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setZ(
												new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -5.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 18.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 28.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 5.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 18.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 27.500F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:extra/furniture/table/top"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 16.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 16.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}
}
