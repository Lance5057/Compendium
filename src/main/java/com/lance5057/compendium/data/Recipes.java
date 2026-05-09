package com.lance5057.compendium.data;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.lance5057.compendium.CompendiumBlocks;
import com.lance5057.compendium.CompendiumItems;
import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.blocks.RecipeToolSupplier.drawer.ComponentDrawerRecipeData;
import com.lance5057.compendium.blocks.RecipeToolSupplier.toolrack.ToolRackRecipeData;
import com.lance5057.compendium.blocks.bed.BedRecipeData;
import com.lance5057.compendium.blocks.chair.ChairRecipeData;
import com.lance5057.compendium.blocks.clothedtable.ClothedTableRecipeData;
import com.lance5057.compendium.blocks.fence.FenceRecipeData;
import com.lance5057.compendium.blocks.shingles.slanted.ShinglesSlantedRecipeData;
import com.lance5057.compendium.blocks.shingles.slanted.cap.ShinglesSlantedCapRecipeData;
import com.lance5057.compendium.blocks.table.TableRecipeData;
import com.lance5057.compendium.blocks.window.WindowRecipeData;
import com.lance5057.compendium.client.BlacklistedModel;
import com.lance5057.compendium.data.loottables.RecipeLootTables;
import com.lance5057.compendium.data.recipebuilders.HammeringRecipeBuilder;
import com.lance5057.compendium.data.recipebuilders.WorkbenchRecipeBuilder;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.util.SlotToMaterial;
import com.lance5057.compendium.util.TagUtil;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloat;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloatVector3;
import com.lance5057.compendium.util.rendering.animation.floats.AnimationFloatTransform;
import com.lance5057.compendium.workstations.cosmetictoolbox.CosmeticToolboxRecipeData;

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

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CompendiumItems.CRUDE_HAMMER).define('s', Items.STICK)
				.define('r', Tags.Items.COBBLESTONES).pattern("r ").pattern(" s")
				.unlockedBy(getName(), has(Tags.Items.COBBLESTONES)).save(consumer);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CompendiumItems.CRUDE_SAW).define('s', Items.STICK)
				.define('r', Items.FLINT).pattern("rs").pattern(" r").unlockedBy(getName(), has(Items.FLINT))
				.save(consumer);

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
								.setZ(new AnimatedFloat(0.000F, 64.000F, 0.000F, 0.500F, true, true)))
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
		return new BlacklistedModel(ResourceLocation.parse("compendium:iron_saw_item"), false,
				new AnimationFloatTransform()
						.setRotation(new AnimatedFloatVector3()
								.setY(new AnimatedFloat(0.000F, 180.000F, 0.000F, 0.000F, false, false))
								.setZ(new AnimatedFloat(0.000F, 45.000F, 0.000F, 0.000F, false, false)))
						.setLocation(new AnimatedFloatVector3()
								.setX(new AnimatedFloat(-14.000F, -6.000F, 0.000F, 0.100F, true, true))
								.setY(new AnimatedFloat(0.000F, 5.000F, 0.000F, 0.000F, true, true))
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
								.setX(new AnimatedFloat(1.000F, 2.000F, 0.000F, 0.000F, false, false))
								.setY(new AnimatedFloat(0.500F, 2.000F, 0.000F, 0.000F, false, false))
								.setZ(new AnimatedFloat(0.500F, 2.000F, 0.000F, 0.000F, false, false))));
	}

	public static BlacklistedModel standardWorkbenchRightHandItemModel(ResourceLocation i, float yOffset) {
		return new BlacklistedModel(i, false,
				new AnimationFloatTransform()
						.setRotation(new AnimatedFloatVector3()
								.setY(new AnimatedFloat(0.000F, -90.000F, 0.000F, 0.000F, false, false))
								.setZ(new AnimatedFloat(-45.000F, 45.000F, 0.000F, 0.000F, false, false)))
						.setLocation(new AnimatedFloatVector3()
								.setX(new AnimatedFloat(0.000F, -0.700F, 0.000F, 0.000F, false, false))
								.setY(new AnimatedFloat(0.000F, -1.000F, 0.000F, 0.000F, false, false))
								.setZ(new AnimatedFloat(0.000F, 27.500F, 0.000F, 0.000F, false, false)))
						.setScale(new AnimatedFloatVector3()
								.setX(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
								.setY(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
								.setZ(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))));
	}

	private void hammering(RecipeOutput consumer) {
		BlacklistedModel standardHammeringModel = new BlacklistedModel(
				ResourceLocation.parse("compendium:gold_hammer_item"), false,
				new AnimationFloatTransform()
						.setRotation(new AnimatedFloatVector3()
								.setZ(new AnimatedFloat(-45.000F, 45.000F, 0.000F, 0.500F, true, true)))
						.setLocation(new AnimatedFloatVector3()
								.setX(new AnimatedFloat(-8.000F, 0.000F, 0.000F, 0.000F, false, false))
								.setY(new AnimatedFloat(-10.000F, 10.000F, 0.000F, 0.000F, false, false))
								.setZ(new AnimatedFloat(-8.000F, 8.000F, 0.000F, 0.000F, false, false)))
						.setScale(new AnimatedFloatVector3()
								.setX(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
								.setY(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false))
								.setZ(new AnimatedFloat(0.500F, 0.500F, 0.000F, 1.000F, false, false)))
						.setPivot(new AnimatedFloatVector3()
								.setX(new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false))
								.setY(new AnimatedFloat(0.000F, 3.000F, 0.000F, 0.000F, false, false))));

		HammeringRecipeBuilder.hammer(Ingredient.of(Items.STONE_BRICK_STAIRS), new ItemStack(Items.STONE_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("stone_bricks_from_stairs"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.STONE_BRICK_WALL), new ItemStack(Items.STONE_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("stone_bricks_from_wall"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.CHISELED_STONE_BRICKS), new ItemStack(Items.STONE_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("stone_bricks_from_chiseled"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.STONE_BRICKS), new ItemStack(Items.CRACKED_STONE_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.CRACKED_STONE_BRICKS), new ItemStack(Items.STONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.SMOOTH_STONE), new ItemStack(Items.STONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("stone_from_smooth"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.STONE_STAIRS), new ItemStack(Items.STONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("stone_from_stairs"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.STONE), new ItemStack(Items.COBBLESTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.COBBLESTONE_STAIRS), new ItemStack(Items.COBBLESTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("cobblestone_from_stairs"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.COBBLESTONE), new ItemStack(Items.GRAVEL))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.GRAVEL), new ItemStack(Items.SAND))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);

		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.MOSSY_STONE_BRICK_STAIRS), new ItemStack(Items.MOSSY_STONE_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("mossy_brick_from_stairs"));
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.MOSSY_STONE_BRICK_WALL), new ItemStack(Items.MOSSY_STONE_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("mossy_brick_from_wall"));
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.MOSSY_COBBLESTONE_STAIRS), new ItemStack(Items.MOSSY_COBBLESTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("mossy_cobblestone_from_stairs"));
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.MOSSY_COBBLESTONE_WALL), new ItemStack(Items.MOSSY_COBBLESTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("mossy_cobblestone_from_wall"));

		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.POLISHED_ANDESITE_STAIRS), new ItemStack(Items.POLISHED_ANDESITE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.POLISHED_ANDESITE), new ItemStack(Items.ANDESITE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);

		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.POLISHED_DIORITE_STAIRS), new ItemStack(Items.POLISHED_DIORITE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.POLISHED_DIORITE), new ItemStack(Items.DIORITE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);

		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.POLISHED_GRANITE_STAIRS), new ItemStack(Items.POLISHED_GRANITE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.POLISHED_GRANITE), new ItemStack(Items.GRANITE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);

		HammeringRecipeBuilder.hammer(Ingredient.of(Items.SMOOTH_BASALT), new ItemStack(Items.BASALT))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("basalt_from_smooth"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.POLISHED_BASALT), new ItemStack(Items.BASALT))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("basalt_from_polished"));

		HammeringRecipeBuilder.hammer(Ingredient.of(Items.DEEPSLATE_TILE_STAIRS), new ItemStack(Items.DEEPSLATE_TILES))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("deepslate_tile_from_stairs"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.DEEPSLATE_TILE_WALL), new ItemStack(Items.DEEPSLATE_TILES))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("deepslate_tile_from_wall"));
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.DEEPSLATE_TILES), new ItemStack(Items.CRACKED_DEEPSLATE_TILES))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.CRACKED_DEEPSLATE_TILES), new ItemStack(Items.DEEPSLATE_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.DEEPSLATE_BRICK_STAIRS), new ItemStack(Items.DEEPSLATE_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("deepslate_brick_from_stairs"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.DEEPSLATE_BRICK_WALL), new ItemStack(Items.DEEPSLATE_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("deepslate_brick_from_wall"));
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.DEEPSLATE_BRICKS), new ItemStack(Items.CRACKED_DEEPSLATE_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.CRACKED_DEEPSLATE_BRICKS), new ItemStack(Items.POLISHED_DEEPSLATE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.POLISHED_DEEPSLATE_STAIRS), new ItemStack(Items.POLISHED_DEEPSLATE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("polished_deepslate_from_stairs"));
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.POLISHED_DEEPSLATE_WALL), new ItemStack(Items.POLISHED_DEEPSLATE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("polished_deepslate_from_wall"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.POLISHED_DEEPSLATE), new ItemStack(Items.DEEPSLATE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.DEEPSLATE), new ItemStack(Items.COBBLED_DEEPSLATE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.COBBLED_DEEPSLATE_STAIRS), new ItemStack(Items.COBBLED_DEEPSLATE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("cobbled_deepslate_from_stairs"));
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.COBBLED_DEEPSLATE_WALL), new ItemStack(Items.COBBLED_DEEPSLATE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("cobbled_deepslate_from_wall"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.CHISELED_DEEPSLATE), new ItemStack(Items.COBBLED_DEEPSLATE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("cobbled_deepslate_from_chiseled"));

		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.POLISHED_BLACKSTONE_BRICK_STAIRS),
						new ItemStack(Items.POLISHED_BLACKSTONE_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("polished_blackstone_bricks_from_stairs"));
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.POLISHED_BLACKSTONE_BRICK_WALL),
						new ItemStack(Items.POLISHED_BLACKSTONE_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("polished_blackstone_bricks_from_wall"));
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.POLISHED_BLACKSTONE_BRICKS),
						new ItemStack(Items.CRACKED_POLISHED_BLACKSTONE_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.CRACKED_POLISHED_BLACKSTONE_BRICKS),
						new ItemStack(Items.POLISHED_BLACKSTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.POLISHED_BLACKSTONE_STAIRS), new ItemStack(Items.POLISHED_BLACKSTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("polished_blackstone_from_stairs"));
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.POLISHED_BLACKSTONE_WALL), new ItemStack(Items.POLISHED_BLACKSTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("polished_blackstone_from_wall"));
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.CHISELED_POLISHED_BLACKSTONE), new ItemStack(Items.POLISHED_BLACKSTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("polished_blackstone_from_chiseled"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.POLISHED_BLACKSTONE), new ItemStack(Items.BLACKSTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.BLACKSTONE_STAIRS), new ItemStack(Items.BLACKSTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("blackstone_from_stairs"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.BLACKSTONE_WALL), new ItemStack(Items.BLACKSTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("blackstone_from_wall"));

		HammeringRecipeBuilder.hammer(Ingredient.of(Items.TUFF_BRICK_STAIRS), new ItemStack(Items.TUFF_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("tuff_bricks_from_stairs"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.TUFF_BRICK_WALL), new ItemStack(Items.TUFF_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("tuff_bricks_from_wall"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.CHISELED_TUFF_BRICKS), new ItemStack(Items.TUFF_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("tuff_bricks_from_chiseled"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.TUFF_BRICKS), new ItemStack(Items.POLISHED_TUFF))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.POLISHED_TUFF_STAIRS), new ItemStack(Items.POLISHED_TUFF))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("polished_tuff_from_stairs"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.POLISHED_TUFF_WALL), new ItemStack(Items.POLISHED_TUFF))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("polished_tuff_from_wall"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.POLISHED_TUFF), new ItemStack(Items.TUFF))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.TUFF_STAIRS), new ItemStack(Items.TUFF))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("tuff_from_stairs"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.TUFF_WALL), new ItemStack(Items.TUFF))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("tuff_from_wall"));

		HammeringRecipeBuilder.hammer(Ingredient.of(Items.BRICK_STAIRS), new ItemStack(Items.BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("bricks_from_stairs"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.BRICK_WALL), new ItemStack(Items.BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("bricks_from_wall"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.BRICKS), new ItemStack(Items.BRICK, 4))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.BRICK_SLAB), new ItemStack(Items.BRICK, 2))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("brick_from_slab"));

		HammeringRecipeBuilder.hammer(Ingredient.of(Items.MUD_BRICK_STAIRS), new ItemStack(Items.MUD_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("mud_bricks_from_stairs"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.MUD_BRICK_WALL), new ItemStack(Items.MUD_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("mud_bricks_from_wall"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.MUD_BRICKS), new ItemStack(Items.PACKED_MUD))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);

		HammeringRecipeBuilder.hammer(Ingredient.of(Items.PRISMARINE), new ItemStack(Items.PRISMARINE_SHARD, 4))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.PRISMARINE_STAIRS), new ItemStack(Items.PRISMARINE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("prismarine_from_stair"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.PRISMARINE_WALL), new ItemStack(Items.PRISMARINE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("prismarine_from_wall"));
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.PRISMARINE_BRICK_STAIRS), new ItemStack(Items.PRISMARINE_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.PRISMARINE_BRICKS), new ItemStack(Items.PRISMARINE_SHARD, 9))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("prismarine_shard_from_brick"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.DARK_PRISMARINE_STAIRS), new ItemStack(Items.DARK_PRISMARINE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.DARK_PRISMARINE), new ItemStack(Items.PRISMARINE_SHARD, 8))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("prismarine_shard_from_dark"));

		HammeringRecipeBuilder.hammer(Ingredient.of(Items.NETHER_BRICK_STAIRS), new ItemStack(Items.NETHER_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("nether_bricks_from_stair"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.NETHER_BRICK_WALL), new ItemStack(Items.NETHER_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("nether_bricks_from_wall"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.CHISELED_NETHER_BRICKS), new ItemStack(Items.NETHER_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("nether_bricks_from_chiseled"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.NETHER_BRICK_FENCE), new ItemStack(Items.NETHER_BRICK, 3))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("nether_brick_from_fence"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.NETHER_BRICK_SLAB), new ItemStack(Items.NETHER_BRICK, 2))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("nether_brick_from_slab"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.NETHER_BRICKS), new ItemStack(Items.CRACKED_NETHER_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.CRACKED_NETHER_BRICKS), new ItemStack(Items.NETHER_BRICK, 4))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.RED_NETHER_BRICK_STAIRS), new ItemStack(Items.RED_NETHER_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("red_nether_bricks_from_stair"));
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.RED_NETHER_BRICK_WALL), new ItemStack(Items.RED_NETHER_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("red_nether_bricks_from_wall"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.RED_NETHER_BRICKS), new ItemStack(Items.NETHER_BRICK, 2))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("nether_brick_from_red"));

		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.END_STONE_BRICK_STAIRS), new ItemStack(Items.END_STONE_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("end_stone_bricks_from_stair"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.END_STONE_BRICK_WALL), new ItemStack(Items.END_STONE_BRICKS))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("end_stone_bricks_from_wall"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.END_STONE_BRICKS), new ItemStack(Items.END_STONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);

		HammeringRecipeBuilder.hammer(Ingredient.of(Items.SMOOTH_QUARTZ_STAIRS), new ItemStack(Items.SMOOTH_QUARTZ))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.SMOOTH_QUARTZ), new ItemStack(Items.QUARTZ_BLOCK))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.QUARTZ_STAIRS), new ItemStack(Items.QUARTZ_BLOCK))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("quartz_block_from_stair"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.CHISELED_QUARTZ_BLOCK), new ItemStack(Items.QUARTZ_BLOCK))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("quartz_block_from_chiseled"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.QUARTZ_BRICKS), new ItemStack(Items.QUARTZ_BLOCK))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("quartz_block_from_bricks"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.QUARTZ_PILLAR), new ItemStack(Items.QUARTZ_BLOCK))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("quartz_block_from_pillar"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.QUARTZ_BLOCK), new ItemStack(Items.QUARTZ, 4))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.QUARTZ_SLAB), new ItemStack(Items.QUARTZ, 2))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("quartz_from_slab"));

		HammeringRecipeBuilder.hammer(Ingredient.of(Items.PURPUR_STAIRS), new ItemStack(Items.PURPUR_BLOCK))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("purpur_block_from_stair"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.PURPUR_PILLAR), new ItemStack(Items.PURPUR_BLOCK))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("purpur_block_from_pillar"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.PURPUR_BLOCK), new ItemStack(Items.POPPED_CHORUS_FRUIT, 4))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.PURPUR_SLAB), new ItemStack(Items.POPPED_CHORUS_FRUIT, 2))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("popped_purpur_from_slab"));

		HammeringRecipeBuilder.hammer(Ingredient.of(Items.WHITE_CONCRETE), new ItemStack(Items.WHITE_CONCRETE_POWDER))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.LIGHT_GRAY_CONCRETE), new ItemStack(Items.LIGHT_GRAY_CONCRETE_POWDER))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.GRAY_CONCRETE), new ItemStack(Items.GRAY_CONCRETE_POWDER))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.BLACK_CONCRETE), new ItemStack(Items.BLACK_CONCRETE_POWDER))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.BROWN_CONCRETE), new ItemStack(Items.BROWN_CONCRETE_POWDER))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.RED_CONCRETE), new ItemStack(Items.RED_CONCRETE_POWDER))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.ORANGE_CONCRETE), new ItemStack(Items.ORANGE_CONCRETE_POWDER))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.YELLOW_CONCRETE), new ItemStack(Items.YELLOW_CONCRETE_POWDER))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.LIME_CONCRETE), new ItemStack(Items.LIME_CONCRETE_POWDER))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.GREEN_CONCRETE), new ItemStack(Items.GREEN_CONCRETE_POWDER))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.CYAN_CONCRETE), new ItemStack(Items.CYAN_CONCRETE_POWDER))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.LIGHT_BLUE_CONCRETE), new ItemStack(Items.LIGHT_BLUE_CONCRETE_POWDER))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.BLUE_CONCRETE), new ItemStack(Items.BLUE_CONCRETE_POWDER))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.PURPLE_CONCRETE), new ItemStack(Items.PURPLE_CONCRETE_POWDER))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.MAGENTA_CONCRETE), new ItemStack(Items.MAGENTA_CONCRETE_POWDER))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.PINK_CONCRETE), new ItemStack(Items.PINK_CONCRETE_POWDER))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);

		HammeringRecipeBuilder.hammer(Ingredient.of(Items.GLOWSTONE), new ItemStack(Items.GLOWSTONE_DUST, 4))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);

		HammeringRecipeBuilder.hammer(Ingredient.of(Items.AMETHYST_BLOCK), new ItemStack(Items.AMETHYST_SHARD, 4))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);

		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.SMOOTH_SANDSTONE_STAIRS), new ItemStack(Items.SMOOTH_SANDSTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.SMOOTH_SANDSTONE_SLAB), new ItemStack(Items.SAND, 2))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("sand_from_smooth_slab"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.SMOOTH_SANDSTONE), new ItemStack(Items.SANDSTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.CHISELED_SANDSTONE), new ItemStack(Items.SANDSTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("sandstone_from_chiseled"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.CUT_SANDSTONE), new ItemStack(Items.SANDSTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("sandstone_from_cut"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.CUT_STANDSTONE_SLAB), new ItemStack(Items.SAND, 2))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("sand_from_cut_slab"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.SANDSTONE_STAIRS), new ItemStack(Items.SANDSTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("sandstone_from_stair"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.SANDSTONE_WALL), new ItemStack(Items.SANDSTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("sandstone_from_wall"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.SANDSTONE), new ItemStack(Items.SAND, 4))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("sand_from_sandstone"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.SANDSTONE_SLAB), new ItemStack(Items.SAND, 2))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("sand_from_sandstone_slab"));

		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.SMOOTH_RED_SANDSTONE_STAIRS), new ItemStack(Items.SMOOTH_RED_SANDSTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.SMOOTH_RED_SANDSTONE_SLAB), new ItemStack(Items.RED_SAND, 2))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("red_sand_from_smooth_slab"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.SMOOTH_RED_SANDSTONE), new ItemStack(Items.RED_SANDSTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.CHISELED_RED_SANDSTONE), new ItemStack(Items.RED_SANDSTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("red_sandstone_from_chiseled"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.CUT_RED_SANDSTONE), new ItemStack(Items.RED_SANDSTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("red_sandstone_from_cut"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.CUT_RED_SANDSTONE_SLAB), new ItemStack(Items.RED_SAND, 2))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("red_sand_from_cut_slab"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.RED_SANDSTONE_STAIRS), new ItemStack(Items.RED_SANDSTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("red_sandstone_from_stair"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.RED_SANDSTONE_WALL), new ItemStack(Items.RED_SANDSTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("red_sandstone_from_wall"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.RED_SANDSTONE), new ItemStack(Items.RED_SAND, 4))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.RED_SANDSTONE_SLAB), new ItemStack(Items.RED_SAND, 2))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("red_sand_from_sandstone_slab"));

		HammeringRecipeBuilder.hammer(Ingredient.of(Items.COPPER_DOOR), new ItemStack(Items.COPPER_INGOT, 2))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("copper_ingot_from_door"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.LIGHTNING_ROD), new ItemStack(Items.COPPER_INGOT, 3))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("copper_ingot_from_lightning_rod"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.COPPER_TRAPDOOR), new ItemStack(Items.COPPER_INGOT, 3))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("copper_ingot_from_trapdoor"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.CUT_COPPER_STAIRS), new ItemStack(Items.CUT_COPPER))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("cut_copper_from_stair"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.CUT_COPPER), new ItemStack(Items.COPPER_BLOCK))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("copper_block_from_cut"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.COPPER_GRATE), new ItemStack(Items.COPPER_BLOCK))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("copper_block_from_grate"));

		HammeringRecipeBuilder.hammer(Ingredient.of(Items.DIAMOND_HOE), new ItemStack(Items.DIAMOND))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("diamond_from_hoe"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.DIAMOND_SWORD), new ItemStack(Items.DIAMOND))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("diamond_from_sword"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.DIAMOND_AXE), new ItemStack(Items.DIAMOND))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("diamond_from_axe"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.DIAMOND_PICKAXE), new ItemStack(Items.DIAMOND))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("diamond_from_pickaxe"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.DIAMOND_BOOTS), new ItemStack(Items.DIAMOND, 2))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("diamond_from_boots"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.DIAMOND_HELMET), new ItemStack(Items.DIAMOND, 2))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("diamond_from_helmet"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.DIAMOND_LEGGINGS), new ItemStack(Items.DIAMOND, 3))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("diamond_from_leggings"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.DIAMOND_CHESTPLATE), new ItemStack(Items.DIAMOND, 4))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("diamond_from_chestplate"));

		HammeringRecipeBuilder.hammer(Ingredient.of(Items.POWERED_RAIL), new ItemStack(Items.GOLD_INGOT))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("gold_ingot_from_powered_rail"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.GOLDEN_HOE), new ItemStack(Items.GOLD_NUGGET, 9))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("gold_nugget_from_hoe"));
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.LIGHT_WEIGHTED_PRESSURE_PLATE), new ItemStack(Items.GOLD_INGOT, 2))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("gold_ingot_from_pressure_plate"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.GOLDEN_SWORD), new ItemStack(Items.GOLD_NUGGET, 9))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("gold_nugget_from_sword"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.GOLDEN_AXE), new ItemStack(Items.GOLD_NUGGET, 13))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("gold_nugget_from_axe"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.GOLDEN_PICKAXE), new ItemStack(Items.GOLD_NUGGET, 13))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("gold_nugget_from_pickaxe"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.GOLDEN_BOOTS), new ItemStack(Items.GOLD_NUGGET, 18))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("gold_nugget_from_boots"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.GOLDEN_HELMET), new ItemStack(Items.GOLD_NUGGET, 22))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("gold_nugget_from_helmet"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.GOLDEN_LEGGINGS), new ItemStack(Items.GOLD_NUGGET, 31))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("gold_nugget_from_leggings"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.GOLDEN_CHESTPLATE), new ItemStack(Items.GOLD_NUGGET, 36))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("gold_nugget_from_chestplate"));

		HammeringRecipeBuilder.hammer(Ingredient.of(Items.CHAIN), new ItemStack(Items.IRON_NUGGET, 11))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_nugget_from_chain"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.IRON_BARS), new ItemStack(Items.IRON_NUGGET, 3))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_nugget_from_bars"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.RAIL), new ItemStack(Items.IRON_NUGGET, 3))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_nugget_from_rail"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.ACTIVATOR_RAIL), new ItemStack(Items.IRON_INGOT))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_ingot_from_activator_rail"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.DETECTOR_RAIL), new ItemStack(Items.IRON_INGOT))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_ingot_from_detector_rail"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.SHIELD), new ItemStack(Items.IRON_NUGGET, 4))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_nugget_from_shield"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.IRON_SHOVEL), new ItemStack(Items.IRON_NUGGET, 4))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_nugget_from_shovel"));
		HammeringRecipeBuilder
				.hammer(Ingredient.of(Items.HEAVY_WEIGHTED_PRESSURE_PLATE), new ItemStack(Items.IRON_INGOT, 2))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_ingot_from_pressure_plate"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.IRON_DOOR), new ItemStack(Items.IRON_INGOT, 2))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_ingot_from_door"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.IRON_HOE), new ItemStack(Items.IRON_NUGGET, 9))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_nugget_from_hoe"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.SHEARS), new ItemStack(Items.IRON_NUGGET, 9))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_nugget_from_shears"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.IRON_SWORD), new ItemStack(Items.IRON_NUGGET, 9))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_nugget_from_sword"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.IRON_AXE), new ItemStack(Items.IRON_NUGGET, 13))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_nugget_from_axe"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.BUCKET), new ItemStack(Items.IRON_INGOT, 3))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_ingot_from_bucket"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.IRON_PICKAXE), new ItemStack(Items.IRON_NUGGET, 13))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_nugget_from_pickaxe"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.IRON_BOOTS), new ItemStack(Items.IRON_NUGGET, 18))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_nugget_from_boots"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.IRON_TRAPDOOR), new ItemStack(Items.IRON_INGOT, 4))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_ingot_from_trapdoor"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.IRON_HELMET), new ItemStack(Items.IRON_NUGGET, 22))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_nugget_from_helmet"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.HOPPER), new ItemStack(Items.IRON_INGOT, 5))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_ingot_from_hopper"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.MINECART), new ItemStack(Items.IRON_INGOT, 5))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_ingot_from_minecart"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.CAULDRON), new ItemStack(Items.IRON_INGOT, 7))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_ingot_from_cauldron"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.IRON_LEGGINGS), new ItemStack(Items.IRON_NUGGET, 31))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_nugget_from_leggings"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.IRON_CHESTPLATE), new ItemStack(Items.IRON_NUGGET, 36))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_nugget_from_chestplate"));
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.ANVIL), new ItemStack(Items.IRON_INGOT, 15))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 2, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer, TagUtil.modLoc("iron_ingot_from_anvil"));

		HammeringRecipeBuilder.hammer(Ingredient.of(Items.BLUE_ICE), new ItemStack(Items.PACKED_ICE, 9))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 1, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.PACKED_ICE), new ItemStack(Items.ICE, 9))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 1, true, RecipeLootTables.EMPTY, List.of(),
						standardHammeringModel)
				.save(consumer);
	}

	private void workbench(RecipeOutput consumer) {
		WorkbenchRecipeBuilder.shaped(CompendiumBlocks.HAMMERING_STATION.toStack()).define('p', CompendiumTags.PLANK)
				.define('s', Items.SMOOTH_STONE_SLAB).define('l', ItemTags.LOGS).pattern("psp").pattern("plp")
				.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
						Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer_item"), 0))
				.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

//		WorkbenchRecipeBuilder.shaped(CompendiumBlocks.SCRAPPING_TABLE.toStack()).define('p', CompendiumTags.PLANK)
//				.define('h', Items.HOPPER).define('c', Items.COPPER_GRATE).pattern("php").pattern("pcp")
//				.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
//						Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer_item"), 0))
//				.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder toolrack = WorkbenchRecipeBuilder.shaped(CompendiumBlocks.TOOLRACK.toStack())
				.define('p', CompendiumTags.PLANK).define('n', Items.IRON_NUGGET).define('e', Items.ENDER_PEARL)
				.pattern("npn").pattern("pep").pattern("npn");

		toolrack = ToolRackRecipeData.stage0(toolrack);
		toolrack = ToolRackRecipeData.stage1(toolrack);
		toolrack = ToolRackRecipeData.stage2(toolrack);
		toolrack = ToolRackRecipeData.stage3(toolrack);
		toolrack = ToolRackRecipeData.stage4(toolrack);

		toolrack.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder drawer = WorkbenchRecipeBuilder.shaped(CompendiumBlocks.COMPONENT_DRAWER.toStack())
				.define('p', CompendiumTags.PLANK).define('n', Items.CHEST).define('e', Items.ENDER_PEARL)
				.pattern("npn").pattern("pep").pattern("npn");

		drawer = ComponentDrawerRecipeData.stage0(drawer);
		drawer = ComponentDrawerRecipeData.stage1(drawer);
		drawer = ComponentDrawerRecipeData.stage2(drawer);

		drawer.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder toolbox = WorkbenchRecipeBuilder.shaped(CompendiumBlocks.COSMETIC_TOOLBOX.toStack())
				.define('c', Items.COPPER_BLOCK).define('h', Items.CHEST).define('b', Items.BRUSH)
				.define('p', ItemTags.WOODEN_PRESSURE_PLATES).define('l', Items.BLUE_DYE).define('g', Items.GREEN_DYE)
				.define('r', Items.RED_DYE).define('y', Items.YELLOW_DYE).define('s', Items.STICK).pattern("psb")
				.pattern("lhg").pattern("rcy");

		toolbox = CosmeticToolboxRecipeData.stage1(toolbox);
		toolbox = CosmeticToolboxRecipeData.stage2(toolbox);
		toolbox = CosmeticToolboxRecipeData.stage3(toolbox);

		toolbox.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder chair = WorkbenchRecipeBuilder.shaped(CompendiumItems.CHAIR)
				.define('p', Ingredient.of(CompendiumTags.PLANK)).define('s', Ingredient.of(ItemTags.WOODEN_SLABS))
				.slotToMat(new SlotToMaterial(1, 0)).slotToMat(new SlotToMaterial(0, 2))
				.slotToMat(new SlotToMaterial(4, 1)).pattern("psp").pattern("psp").pattern("p p");

		chair = ChairRecipeData.chairStage0(chair);
		chair = ChairRecipeData.chairStage1(chair);
		chair = ChairRecipeData.chairStage2(chair);
		chair = ChairRecipeData.chairStage3(chair);
		chair = ChairRecipeData.chairStage4(chair);
		chair = ChairRecipeData.chairStage5(chair);
		chair = ChairRecipeData.chairStage6(chair);
//		chair = ChairRecipeData.chairStage8(chair); // saw back
//		chair = ChairRecipeData.chairStage9(chair); // back
//		chair = ChairRecipeData.chairStage10(chair); // finish

		chair.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder table = WorkbenchRecipeBuilder.shaped(CompendiumItems.TABLE)
				.define('p', Ingredient.of(CompendiumTags.PLANK)).define('s', Ingredient.of(ItemTags.WOODEN_SLABS))
				.slotToMat(new SlotToMaterial(1, 0)).slotToMat(new SlotToMaterial(0, 1)).pattern("psp").pattern("p p");

		table = TableRecipeData.tableStage1(table); // saw top
		table = TableRecipeData.tableStage2(table); // leg 1
		table = TableRecipeData.tableStage3(table); // leg 2
		table = TableRecipeData.tableStage4(table); // leg 3
		table = TableRecipeData.tableStage5(table); // leg 4

		table.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder clothed_table = WorkbenchRecipeBuilder.shaped(CompendiumItems.CLOTHED_TABLE)
				.define('p', Ingredient.of(CompendiumTags.PLANK)).define('s', Ingredient.of(ItemTags.WOODEN_SLABS))
				.define('c', Ingredient.of(ItemTags.WOOL)).slotToMat(new SlotToMaterial(4, 0))
				.slotToMat(new SlotToMaterial(3, 1)).slotToMat(new SlotToMaterial(1, 2)).pattern(" c ").pattern("psp")
				.pattern("p p");

		clothed_table = ClothedTableRecipeData.tableStage1(clothed_table); // saw top
		clothed_table = ClothedTableRecipeData.tableStage2(clothed_table); // leg 1
		clothed_table = ClothedTableRecipeData.tableStage3(clothed_table); // leg 2
		clothed_table = ClothedTableRecipeData.tableStage4(clothed_table); // leg 3
		clothed_table = ClothedTableRecipeData.tableStage5(clothed_table); // leg 4

		clothed_table.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder bed = WorkbenchRecipeBuilder.shaped(CompendiumItems.FANCY_BED)
				.define('p', Ingredient.of(CompendiumTags.PLANK)).define('s', Ingredient.of(ItemTags.PLANKS))
				.define('c', Ingredient.of(ItemTags.WOOL)).slotToMat(new SlotToMaterial(7, 1))
				.slotToMat(new SlotToMaterial(3, 0)).slotToMat(new SlotToMaterial(4, 2))
				.slotToMat(new SlotToMaterial(2, 3)).slotToMat(new SlotToMaterial(1, 4))
				.slotToMat(new SlotToMaterial(0, 5)).pattern("ccc").pattern("pcp").pattern("psp");

		bed = BedRecipeData.bedStage1(bed);
		bed = BedRecipeData.bedStage2(bed);
		bed = BedRecipeData.bedStage3(bed);
		bed = BedRecipeData.bedStage4(bed);
		bed = BedRecipeData.bedStage5(bed);
		bed = BedRecipeData.bedStage6(bed);
		bed = BedRecipeData.bedStage7(bed);
		bed = BedRecipeData.bedStage8(bed);
		bed = BedRecipeData.bedStage9(bed);
		bed = BedRecipeData.bedStage10(bed);
		bed = BedRecipeData.bedStage11(bed);
		bed = BedRecipeData.bedStage12(bed);

		bed.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder window = WorkbenchRecipeBuilder.shaped(CompendiumItems.WINDOW, 4)
				.define('p', Ingredient.of(CompendiumTags.PLANK)).define('g', Ingredient.of(Tags.Items.GLASS_BLOCKS))
				.slotToMat(new SlotToMaterial(4, 0)).slotToMat(new SlotToMaterial(1, 1)).pattern(" p ").pattern("pgp")
				.pattern(" p ");

		window = WindowRecipeData.stage0(window);
		window = WindowRecipeData.stage1(window);
		window = WindowRecipeData.stage2(window);
		window = WindowRecipeData.stage3(window);

		window.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder window_metal = WorkbenchRecipeBuilder.shaped(CompendiumItems.WINDOW, 4)
				.define('p', Ingredient.of(Tags.Items.NUGGETS)).define('g', Ingredient.of(Tags.Items.GLASS_BLOCKS))
				.slotToMat(new SlotToMaterial(4, 0)).slotToMat(new SlotToMaterial(1, 1)).pattern(" p ").pattern("pgp")
				.pattern(" p ");

		window_metal = WindowRecipeData.stage0(window_metal);
		window_metal = WindowRecipeData.stage1(window_metal);
		window_metal = WindowRecipeData.stage2(window_metal);
		window_metal = WindowRecipeData.stage3(window_metal);

		window_metal.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer, TagUtil.modLoc("window_from_nugget"));

		WorkbenchRecipeBuilder fence = WorkbenchRecipeBuilder.shaped(CompendiumItems.FANCY_FENCE, 3)
				.define('p', Ingredient.of(CompendiumTags.PLANK)).define('b', Ingredient.of(ItemTags.PLANKS))
				.slotToMat(new SlotToMaterial(0, 0)).slotToMat(new SlotToMaterial(1, 1)).pattern("bpb").pattern("bpb");

		fence = FenceRecipeData.stage0(fence);
		fence = FenceRecipeData.stage1(fence);

		fence.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder shingles = WorkbenchRecipeBuilder.shaped(CompendiumItems.SHINGLES_SLANTED, 6)
				.define('b', Ingredient.of(CompendiumTags.PLANK)).define('l', Ingredient.of(CompendiumTags.SMALL_LOG))
				.slotToMat(new SlotToMaterial(2, 0)).slotToMat(new SlotToMaterial(8, 1)).pattern("  b").pattern(" b ")
				.pattern("b l");

		shingles = ShinglesSlantedRecipeData.stage0(shingles);
		shingles = ShinglesSlantedRecipeData.stage1(shingles);
		shingles = ShinglesSlantedRecipeData.stage2(shingles);
		shingles = ShinglesSlantedRecipeData.stage3(shingles);

		shingles.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder shingles_cap = WorkbenchRecipeBuilder.shaped(CompendiumItems.SHINGLES_CAP_SLANTED, 6)
				.define('b', Ingredient.of(CompendiumTags.PLANK)).define('l', Ingredient.of(CompendiumTags.SMALL_LOG))
				.slotToMat(new SlotToMaterial(1, 0)).slotToMat(new SlotToMaterial(5, 1)).pattern(" b ").pattern("blb");

		shingles_cap = ShinglesSlantedCapRecipeData.stage0(shingles_cap);
		shingles_cap = ShinglesSlantedCapRecipeData.stage1(shingles_cap);
		shingles_cap = ShinglesSlantedCapRecipeData.stage2(shingles_cap);
		shingles_cap = ShinglesSlantedCapRecipeData.stage3(shingles_cap);

		shingles_cap.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);
	}

}
