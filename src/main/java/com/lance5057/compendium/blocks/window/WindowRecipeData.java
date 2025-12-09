package com.lance5057.compendium.blocks.window;

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

public class WindowRecipeData {
	public static WorkbenchRecipeBuilder stage0(WorkbenchRecipeBuilder builder) {
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
						new BlacklistedModel(ResourceLocation.parse("compendium:plank"), false,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, 15.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -4.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -3.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:plank"), false,
								new AnimationFloatTransform()
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -3.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -1.900F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setPivot(new AnimatedFloatVector3().setZ(
												new AnimatedFloat(0.000F, 2.000F, 0.000F, 0.000F, false, false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:plank"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3().setY(
												new AnimatedFloat(0.000F, -30.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 3.500F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -1.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -4.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("compendium:item/plank"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -3.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, -5.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -3.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))),
						new BlacklistedModel(ResourceLocation.parse("minecraft:glass"), true,
								new AnimationFloatTransform()
										.setRotation(new AnimatedFloatVector3()
												.setY(new AnimatedFloat(0.000F, -90.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 65.000F, 0.000F, 0.000F, false, false)))
										.setLocation(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, -5.500F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, -10.500F, 0.000F, 0.000F, false,
														false)))
										.setScale(new AnimatedFloatVector3()
												.setX(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setY(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false, false))
												.setZ(new AnimatedFloat(0.000F, 1.000F, 0.000F, 0.000F, false,
														false)))));
	}
}
