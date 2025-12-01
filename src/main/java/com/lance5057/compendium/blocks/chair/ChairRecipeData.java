package com.lance5057.compendium.blocks.chair;

import java.util.List;

import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.client.BlacklistedModel;
import com.lance5057.compendium.data.Recipes;
import com.lance5057.compendium.data.loottables.RecipeLootTables;
import com.lance5057.compendium.data.recipebuilders.WorkbenchRecipeBuilder;
import com.lance5057.compendium.util.TagUtil;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloat;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloatVector3;
import com.lance5057.compendium.util.rendering.animation.floats.AnimationFloatTransform;

import net.minecraft.world.item.crafting.Ingredient;

public class ChairRecipeData {
	public static WorkbenchRecipeBuilder chairStage1(WorkbenchRecipeBuilder builder) {
		return builder
				.tool(Ingredient.of(CompendiumTags.SAW), 2, true, RecipeLootTables.EMPTY, List.of(),
						Recipes.standardWorkbenchRightHandItemModel(TagUtil
								.modLoc("iron_saw_item"), 0),
						new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
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
						new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 25.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setZ(
												new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 4.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false, new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 12.600F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 24.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 7.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setZ(new AnimatedFloat(0.000F, 65.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -4.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -11.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 4.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false,
														false)))));
	}

	public static WorkbenchRecipeBuilder chairStage2(WorkbenchRecipeBuilder builder) {
		return builder.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
				Recipes.standardWorkbenchRightHandItemModel(TagUtil.modLoc("iron_hammer_item"), 0),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 25.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))
								.setPivot(new AnimatedFloatVector3()
										.setZ(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 4.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 12.600F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 24.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
						new AnimationFloatTransform()
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 7.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setZ(new AnimatedFloat(0.000F, 65.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 4.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder chairStage3(WorkbenchRecipeBuilder builder) {
		return builder.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
				Recipes.standardWorkbenchRightHandItemModel(TagUtil.modLoc("iron_hammer_item"), 0),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 25.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))
								.setPivot(new AnimatedFloatVector3()
										.setZ(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 4.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 12.600F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 24.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
						new AnimationFloatTransform()
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 7.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setZ(new AnimatedFloat(0.000F, 65.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 4.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder chairStage4(WorkbenchRecipeBuilder builder) {
		return builder.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
				Recipes.standardWorkbenchRightHandItemModel(TagUtil.modLoc("iron_hammer_item"), 0),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 25.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))
								.setPivot(new AnimatedFloatVector3()
										.setZ(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 4.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 12.600F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 24.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
						new AnimationFloatTransform()
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 7.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setZ(new AnimatedFloat(0.000F, 65.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 4.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder chairStage5(WorkbenchRecipeBuilder builder) {
		return builder.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
				Recipes.standardWorkbenchRightHandItemModel(TagUtil.modLoc("iron_hammer_item"), 0),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 25.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))
								.setPivot(new AnimatedFloatVector3()
										.setZ(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 4.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 12.600F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 24.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
						new AnimationFloatTransform()
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 7.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setZ(new AnimatedFloat(0.000F, 65.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 4.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder chairStage6(WorkbenchRecipeBuilder builder) {
		return builder.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
				Recipes.standardWorkbenchRightHandItemModel(TagUtil.modLoc("iron_hammer_item"), 0),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 25.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))
								.setPivot(new AnimatedFloatVector3()
										.setZ(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 4.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 12.600F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 24.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
						new AnimationFloatTransform()
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 7.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setZ(new AnimatedFloat(0.000F, 65.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 4.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder chairStage7(WorkbenchRecipeBuilder builder) {
		return builder.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
				Recipes.standardWorkbenchRightHandItemModel(TagUtil.modLoc("iron_hammer_item"), 0),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 25.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))
								.setPivot(new AnimatedFloatVector3()
										.setZ(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 4.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 12.600F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 24.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
						new AnimationFloatTransform()
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 7.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setZ(new AnimatedFloat(0.000F, 65.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 4.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder chairStage8(WorkbenchRecipeBuilder builder) {
		return builder.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
				Recipes.standardWorkbenchRightHandItemModel(TagUtil.modLoc("iron_hammer_item"), 0),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 25.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))
								.setPivot(new AnimatedFloatVector3()
										.setZ(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 4.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 12.600F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 24.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
						new AnimationFloatTransform()
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 7.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setZ(new AnimatedFloat(0.000F, 65.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 4.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder chairStage9(WorkbenchRecipeBuilder builder) {
		return builder.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
				Recipes.standardWorkbenchRightHandItemModel(TagUtil.modLoc("iron_hammer_item"), 0),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 25.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))
								.setPivot(new AnimatedFloatVector3()
										.setZ(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 4.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 12.600F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 24.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
						new AnimationFloatTransform()
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 7.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setZ(new AnimatedFloat(0.000F, 65.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 4.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))));
	}

	public static WorkbenchRecipeBuilder chairStage10(WorkbenchRecipeBuilder builder) {
		return builder.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
				Recipes.standardWorkbenchRightHandItemModel(TagUtil.modLoc("iron_hammer_item"), 0),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 25.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))
								.setPivot(new AnimatedFloatVector3()
										.setZ(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 4.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 22.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 12.600F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 24.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.modLoc("oak_plank_item"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -25.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
						new AnimationFloatTransform()
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 7.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))),
				new BlacklistedModel(TagUtil.mcLoc("oak_slab"), false,
						new AnimationFloatTransform()
								.setRotation(new AnimatedFloatVector3()
										.setZ(new AnimatedFloat(0.000F, 65.000F, 0.000F, 0.000F, false, false)))
								.setLocation(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, -12.000F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 4.000F, 0.000F, 0.000F, false, false)))
								.setScale(new AnimatedFloatVector3()
										.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
										.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
										.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))));
	}

}
