package com.lance5057.compendium.blocks.bed;

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

public class BedRecipeData {
	public static WorkbenchRecipeBuilder bedStage1(WorkbenchRecipeBuilder builder) {
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
												.setX(new AnimatedFloat(0.500F, 0.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
												.setZ(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -16.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -16.000F, 0.000F, 0.000F, false,
														false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setZ(
												new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -16.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 5.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -16.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
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
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 45.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -11.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -10.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, -22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder bedStage2(WorkbenchRecipeBuilder builder) {
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
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -16.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -16.000F, 0.000F, 0.000F, false,
														false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setZ(
												new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -16.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 5.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -16.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/base"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 45.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -11.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -10.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, -22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/head_board"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 65.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -6.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/foot_board"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 22.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 12.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -12.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -14.000F, 0.000F, 0.000F, false,
														false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder bedStage3(WorkbenchRecipeBuilder builder) {
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
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 6.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -16.000F, 0.000F, 0.000F, false,
														false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setZ(
												new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -16.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 5.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -16.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/base"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 6.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 45.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -11.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -10.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, -22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/head_board"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 65.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -6.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/foot_board"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 22.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 12.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -12.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -14.000F, 0.000F, 0.000F, false,
														false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder bedStage4(WorkbenchRecipeBuilder builder) {
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
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 6.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 6.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -16.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 5.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -16.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/base"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 6.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 45.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -11.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -10.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, -22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/head_board"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 65.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -6.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/foot_board"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 22.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 12.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -12.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -14.000F, 0.000F, 0.000F, false,
														false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder bedStage5(WorkbenchRecipeBuilder builder) {
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
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -16.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/base"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 45.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -11.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -10.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, -22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/head_board"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 65.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -6.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/foot_board"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 22.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 12.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -12.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -14.000F, 0.000F, 0.000F, false,
														false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder bedStage6(WorkbenchRecipeBuilder builder) {
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
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/base"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 45.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -11.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -10.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, -22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/head_board"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 65.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -6.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/foot_board"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 22.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 12.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -12.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -14.000F, 0.000F, 0.000F, false,
														false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder bedStage7(WorkbenchRecipeBuilder builder) {
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
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/base"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 45.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -11.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -10.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, -22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/head_board"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 9.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/foot_board"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 22.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 12.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -12.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -14.000F, 0.000F, 0.000F, false,
														false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder bedStage8(WorkbenchRecipeBuilder builder) {
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
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/base"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 45.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -11.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -10.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, -22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/head_board"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 9.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/foot_board"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder bedStage9(WorkbenchRecipeBuilder builder) {
		return builder
				.tool(Ingredient.of(Tags.Items.TOOLS_SHEAR), 2, true, RecipeLootTables.EMPTY, List.of(),
						new BlacklistedModel(ResourceLocation.parse("minecraft:shears"), false,
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
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/base"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/mattress"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 4.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -11.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -10.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, -22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/head_board"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 9.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/foot_board"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder bedStage10(WorkbenchRecipeBuilder builder) {
		return builder
				.tool(Ingredient.of(Tags.Items.TOOLS_SHEAR), 2, true, RecipeLootTables.EMPTY, List.of(),
						new BlacklistedModel(ResourceLocation.parse("minecraft:shears"), false,
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
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/base"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/mattress"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 4.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/pillow"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 5.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 19.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -10.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, -22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/head_board"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 9.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/foot_board"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder bedStage11(WorkbenchRecipeBuilder builder) {
		return builder
				.tool(Ingredient.of(Tags.Items.TOOLS_SHEAR), 2, true, RecipeLootTables.EMPTY, List.of(),
						new BlacklistedModel(ResourceLocation.parse("minecraft:shears"), false,
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
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/base"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/mattress"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 4.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/pillow"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 5.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 19.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/sheet"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -0.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:white_wool"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, -22.500F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.050F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/head_board"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 9.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/foot_board"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder bedStage12(WorkbenchRecipeBuilder builder) {
		return builder
				.tool(Ingredient.of(Tags.Items.TOOLS_SHEAR), 2, true, RecipeLootTables.EMPTY, List.of(),
						new BlacklistedModel(ResourceLocation.parse("minecraft:shears"), false,
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
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/leg"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/base"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/mattress"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 4.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/pillow"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 5.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 19.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/sheet"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -0.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/blanket"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 0.600F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/head_board"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 9.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 23.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/furniture/bed/foot_board"), true,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}
}
