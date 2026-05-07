package com.lance5057.compendium.workstations.cosmetictoolbox;

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

public class CosmeticToolboxRecipeData {
	public static WorkbenchRecipeBuilder stage1(WorkbenchRecipeBuilder builder) {
		return builder
				.tool(Ingredient.of(CompendiumTags.HAMMER), 6, true, RecipeLootTables.EMPTY, List.of(),
						new BlacklistedModel(ResourceLocation.parse("compendium:iron_hammer_item"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(-45.000F, 45.000F, 0.000F, 0.000F, false,
														false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -0.700F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 27.500F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.500F, 0.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
												.setZ(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:copper_block"), false,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 4.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:chest"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, -90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 9.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 4.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 24.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setZ(
												new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:oak_pressure_plate"), false,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -5.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:blue_dye"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -90.000F, 0.000F, 0.000F, false,
														false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 9.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -8.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:red_dye"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 5.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -8.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:yellow_dye"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 5.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -8.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:green_dye"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 11.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -8.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:brush"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 19.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder stage2(WorkbenchRecipeBuilder builder) {
		return builder
				.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
						new BlacklistedModel(ResourceLocation.parse("compendium:iron_hammer_item"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(-45.000F, 45.000F, 0.000F, 0.000F, false,
														false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -0.700F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 27.500F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.500F, 0.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
												.setZ(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/cosmetic_toolbox_stage1"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 31.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/cosmetic_toolbox_lid"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 180.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 9.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:oak_pressure_plate"), false,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -5.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:blue_dye"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -90.000F, 0.000F, 0.000F, false,
														false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 9.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -8.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:red_dye"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 5.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -8.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:yellow_dye"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 5.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -8.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:green_dye"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 11.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -8.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:brush"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -9.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 19.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder stage3(WorkbenchRecipeBuilder builder) {
		return builder
				.tool(Ingredient.of(CompendiumTags.SAW), 4, true, RecipeLootTables.EMPTY, List.of(),
						new BlacklistedModel(ResourceLocation.parse("compendium:iron_saw_item"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(-45.000F, 135.000F, 0.000F, 0.000F, false,
														false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -0.700F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -4.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 28.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.500F, 0.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
												.setZ(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/cosmetic_toolbox_stage2"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 29.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:oak_pressure_plate"), false,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 4.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:blue_dye"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -90.000F, 0.000F, 0.000F, false,
														false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 9.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:red_dye"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 5.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:yellow_dye"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 5.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:green_dye"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 11.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.250F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:brush"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))));
	}

}
