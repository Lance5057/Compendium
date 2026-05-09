package com.lance5057.compendium.blocks.RecipeToolSupplier.drawer;

import java.util.List;

import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.client.BlacklistedModel;
import com.lance5057.compendium.data.loottables.RecipeLootTables;
import com.lance5057.compendium.data.recipebuilders.WorkbenchRecipeBuilder;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloat;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloatVector3;
import com.lance5057.compendium.util.rendering.animation.floats.AnimationFloatTransform;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class ComponentDrawerRecipeData {
	public static WorkbenchRecipeBuilder stage0(WorkbenchRecipeBuilder builder) {
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
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/component_drawer_stage1"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/component_drawer_drawer"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -10.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 13.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/component_drawer_drawer"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -10.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 19.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/component_drawer_drawer"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -10.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 25.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/component_drawer_drawer"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -10.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 31.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false,
														false)))));
	}

	public static WorkbenchRecipeBuilder stage1(WorkbenchRecipeBuilder builder) {
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
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/component_drawer_stage0"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 35.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/component_drawer_drawer"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 35.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.700F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/component_drawer_drawer"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 35.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -13.300F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.700F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 12.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/component_drawer_drawer"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 35.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 6.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 12.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/component_drawer_drawer"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 35.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -13.300F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 6.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false,
														false)))));
	}

	public static WorkbenchRecipeBuilder stage2(WorkbenchRecipeBuilder builder) {
		return builder
				.tool(Ingredient.of(Items.ENDER_PEARL), 1, true, RecipeLootTables.EMPTY, List.of(),
						new BlacklistedModel(ResourceLocation.parse("minecraft:ender_pearl"), false,
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
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/component_drawer_stage0"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 35.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -2.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/component_drawer_drawer"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 35.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.700F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 11.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/component_drawer_drawer"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 35.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -13.300F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.700F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 12.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/component_drawer_drawer"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 35.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 6.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 12.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/component_drawer_drawer"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 35.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -13.300F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 6.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 14.000F, 0.000F, 0.000F, false,
														false)))),
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
						new BlacklistedModel(ResourceLocation.parse("compendium:recipes/toolrack_stage1"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 5.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.200F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.750F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:ender_pearl"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 360.000F, 0.000F, 1.000F, true, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 10.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 21.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 0.500F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setZ(
												new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false)))));
	}
}
