package com.lance5057.compendium.data;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.lance5057.compendium.CompendiumBlocks;
import com.lance5057.compendium.CompendiumItems;
import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.blocks.bed.BedRecipeData;
import com.lance5057.compendium.blocks.chair.ChairRecipeData;
import com.lance5057.compendium.blocks.fence.FenceRecipeData;
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
		HammeringRecipeBuilder.hammer(Ingredient.of(Items.STONE), new ItemStack(Items.COBBLESTONE))
				.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.STONE_TO_COBBLE, List.of(),
						standardHammeringModel(TagUtil.modLoc("gold_hammer_item"), 0))
				.save(consumer);
	}

	private void workbench(RecipeOutput consumer) {
		WorkbenchRecipeBuilder.shaped(CompendiumBlocks.HAMMERING_STATION.toStack()).define('p', CompendiumTags.PLANK)
				.define('s', Items.SMOOTH_STONE_SLAB).define('l', ItemTags.LOGS).pattern("psp").pattern("plp")
				.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
						Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer_item"), 0))
				.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder.shaped(CompendiumBlocks.SCRAPPING_TABLE.toStack()).define('p', CompendiumTags.PLANK)
				.define('h', Items.HOPPER).define('c', Items.COPPER_GRATE).pattern("php").pattern("pcp")
				.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
						Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer_item"), 0))
				.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder.shaped(CompendiumBlocks.TOOLRACK.toStack()).define('p', CompendiumTags.PLANK)
				.define('n', Items.IRON_NUGGET).define('e', Items.ENDER_PEARL).pattern("npn").pattern("pep")
				.pattern("npn")
				.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
						Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer_item"), 0))
				.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder.shaped(CompendiumBlocks.COMPONENT_DRAWER.toStack()).define('p', CompendiumTags.PLANK)
				.define('n', Items.CHEST).define('e', Items.ENDER_PEARL).pattern("npn").pattern("pep").pattern("npn")
				.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
						Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer_item"), 0))
				.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder.shaped(CompendiumBlocks.COSMETIC_TOOLBOX.toStack()).define('c', Items.COPPER_BLOCK)
				.define('h', Items.CHEST).define('b', Items.BRUSH).define('p', ItemTags.WOODEN_PRESSURE_PLATES)
				.define('l', Items.BLUE_DYE).define('g', Items.GREEN_DYE).define('r', Items.RED_DYE)
				.define('y', Items.YELLOW_DYE).define('s', Items.STICK).pattern("psb").pattern("lhg").pattern("rcy")
				.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
						Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer_item"), 0))
				.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

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

		clothed_table = TableRecipeData.tableStage1(clothed_table); // saw top
		clothed_table = TableRecipeData.tableStage2(clothed_table); // leg 1
		clothed_table = TableRecipeData.tableStage3(clothed_table); // leg 2
		clothed_table = TableRecipeData.tableStage4(clothed_table); // leg 3
		clothed_table = TableRecipeData.tableStage5(clothed_table); // leg 4

		clothed_table.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder bed = WorkbenchRecipeBuilder.shaped(CompendiumItems.FANCY_BED)
				.define('p', Ingredient.of(CompendiumTags.PLANK)).define('s', Ingredient.of(ItemTags.WOODEN_SLABS))
				.define('c', Ingredient.of(ItemTags.WOOL)).slotToMat(new SlotToMaterial(7, 1))
				.slotToMat(new SlotToMaterial(3, 0)).slotToMat(new SlotToMaterial(4, 2))
				.slotToMat(new SlotToMaterial(2, 3)).slotToMat(new SlotToMaterial(1, 4))
				.slotToMat(new SlotToMaterial(0, 5)).pattern("ccc").pattern("pcp").pattern("psp");

		bed = BedRecipeData.bedStage1(bed); // saw top
		bed = BedRecipeData.bedStage2(bed); // leg 1
//		bed = TableRecipeData.tableStage3(bed); // leg 2
//		bed = TableRecipeData.tableStage4(bed); // leg 3
//		bed = TableRecipeData.tableStage5(bed); // leg 4

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

		WorkbenchRecipeBuilder fence = WorkbenchRecipeBuilder.shaped(CompendiumItems.FANCY_FENCE, 3)
				.define('p', Ingredient.of(CompendiumTags.PLANK)).define('b', Ingredient.of(ItemTags.PLANKS))
				.slotToMat(new SlotToMaterial(0, 0)).slotToMat(new SlotToMaterial(1, 1)).pattern("bpb").pattern("bpb");

		fence = FenceRecipeData.stage0(fence);
		fence = FenceRecipeData.stage1(fence);

		fence.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder shingles = WorkbenchRecipeBuilder.shaped(CompendiumItems.SHINGLES_SLANTED, 6)
				.define('b', Ingredient.of(CompendiumTags.PLANK)).define('l', Ingredient.of(CompendiumTags.SMALL_LOGS))
				.slotToMat(new SlotToMaterial(2, 0)).slotToMat(new SlotToMaterial(8, 1)).pattern("  b").pattern(" b ")
				.pattern("b l");

		shingles = WindowRecipeData.stage0(shingles);

		shingles.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);

		WorkbenchRecipeBuilder shingles_cap = WorkbenchRecipeBuilder.shaped(CompendiumItems.SHINGLES_CAP_SLANTED, 6)
				.define('b', Ingredient.of(CompendiumTags.PLANK)).define('l', Ingredient.of(CompendiumTags.SMALL_LOGS))
				.slotToMat(new SlotToMaterial(1, 0)).slotToMat(new SlotToMaterial(5, 1)).pattern(" b ").pattern("blb");

		shingles_cap = WindowRecipeData.stage0(shingles_cap);

		shingles_cap.unlockedBy(getName(), has(Tags.Items.STONES)).save(consumer);
	}

}
