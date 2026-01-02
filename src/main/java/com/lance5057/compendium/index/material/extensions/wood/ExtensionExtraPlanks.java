package com.lance5057.compendium.index.material.extensions.wood;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.blocks.PipeStyleBlock;
import com.lance5057.compendium.blocks.SimpleStyleBlock;
import com.lance5057.compendium.blocks.SlabStyleBlock;
import com.lance5057.compendium.blocks.StairStyleBlock;
import com.lance5057.compendium.client.BlacklistedModel;
import com.lance5057.compendium.client.models.style.StyleBlockModelBuilder;
import com.lance5057.compendium.client.models.style.model.StyleModelBuilder;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.data.IndexBlockModelProvider;
import com.lance5057.compendium.data.Recipes;
import com.lance5057.compendium.data.loottables.BlockLootTables;
import com.lance5057.compendium.data.loottables.RecipeLootTables;
import com.lance5057.compendium.data.recipebuilders.SawBuckRecipeBuilder;
import com.lance5057.compendium.data.recipebuilders.WorkbenchRecipeBuilder;
import com.lance5057.compendium.index.CompendiumIndex.Generate;
import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.index.material.base.MaterialWood;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extensions.MaterialExtensionSerializer;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.index.util.DataUtil;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.util.TagUtil;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloat;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloatVector3;
import com.lance5057.compendium.util.rendering.animation.floats.AnimationFloatTransform;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.phys.Vec3;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ExtensionExtraPlanks extends _MaterialExtension {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3680413722908106206L;
	public final CompendiumBlockHandler PLANK;
	public final CompendiumBlockHandler PLANK_BLOCK;
	public final CompendiumBlockHandler PLANK_SLAB;
	public final CompendiumBlockHandler PLANK_STAIRS;

	public ExtensionExtraPlanks(Generate plank, Generate plankBlock, Generate plankSlab, Generate plankStairs) {
		PLANK = new CompendiumBlockHandler("plank");
		PLANK_BLOCK = new CompendiumBlockHandler("plank_block");
		PLANK_SLAB = new CompendiumBlockHandler("plank_slab");
		PLANK_STAIRS = new CompendiumBlockHandler("plank_stairs");

		PLANK.setGenerate(plank);
		PLANK_BLOCK.setGenerate(plankBlock);
		PLANK_SLAB.setGenerate(plankSlab);
		PLANK_STAIRS.setGenerate(plankStairs);
	}

	@Override
	public void setup(_MaterialBase base) {
		PLANK.setup(base,
				() -> new PipeStyleBlock(0.125f, Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS),
						Compendium.modLoc(base.extraFolder() + "plank"), List.of("plank"), StyleData.PLANK),
				() -> new BlockItem(PLANK.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_plank"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_plank"));
//		PLANK.setupItemTag(TagUtil.neoTag("plank"));
		PLANK.setupItemTag(TagUtil.neoTag("plank/" + base.name));
		PLANK.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
		PLANK.setAsValidStyleBlock();

		PLANK_BLOCK.setup(base,
				() -> new SimpleStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS),
						Compendium.modLoc(base.extraFolder() + "planks"), List.of("plank_block"), StyleData.PLANKS),
				() -> new BlockItem(PLANK_BLOCK.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_styled_planks"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_styled_planks"));
//		PLANK_BLOCK.setupItemTag(ItemTags.PLANKS);
		PLANK_BLOCK.setupItemTag(TagUtil.neoTag("planks/" + base.name));
		PLANK_BLOCK.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
		PLANK_BLOCK.setAsValidStyleBlock();
		PLANK_BLOCK.setAsValidStyleItem();

		PLANK_SLAB.setup(base,
				() -> new SlabStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_SLAB).noOcclusion(),
						Compendium.modLoc(base.extraFolder() + "plank_slab"), List.of("plank_block"), StyleData.PLANKS),
				() -> new BlockItem(PLANK_SLAB.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_styled_slab"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_styled_slab"));
//		PLANK_SLAB.setupItemTag(ItemTags.WOODEN_SLABS);
		PLANK_SLAB.setupItemTag(TagUtil.neoTag("slabs/planks/" + base.name));
		PLANK_SLAB.setupItemTag(TagUtil.neoTag("wooden_slabs/" + base.name));
		PLANK_SLAB.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
		PLANK_SLAB.setAsValidStyleBlock();
		PLANK_SLAB.setAsValidStyleItem();

		PLANK_STAIRS.setup(base, () -> new StairStyleBlock(PLANK_BLOCK.BLOCK.get().defaultBlockState(),
				Block.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS),
				Compendium.modLoc(base.extraFolder() + "plank_stairs"), List.of("plank_block"), StyleData.PLANKS),
				() -> new BlockItem(PLANK_STAIRS.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_styled_stairs"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_styled_stairs"));
		PLANK_STAIRS.setAsValidStyleBlock();
		PLANK_STAIRS.setAsValidStyleItem();

//		PLANK_STAIRS.setupItemTag(ItemTags.WOODEN_STAIRS);
		PLANK_STAIRS.setupItemTag(TagUtil.neoTag("stairs/planks/" + base.name));
		PLANK_STAIRS.setupItemTag(TagUtil.neoTag("wooden_stairs/" + base.name));
		PLANK_STAIRS.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
	}

	@Override
	public void tab(_MaterialBase base, Output output) {
		PLANK.tab(base, output);
		PLANK_BLOCK.tab(base, output);
		PLANK_SLAB.tab(base, output);
		PLANK_STAIRS.tab(base, output);
	}

	@Override
	public void blockStateModel(_MaterialBase base, BlockStateProvider bsp) {
		if (this.autoGenBlockModel) {
			this.plankModel(PLANK, base, bsp, "");
			if (PLANK_BLOCK.shouldGenerate()) {
				ConfiguredModel.Builder<?> b = ConfiguredModel.builder();
				StyleBlockModelBuilder<BlockModelBuilder> msmb = bsp.models()
						.getBuilder(PLANK_BLOCK.location(base) + "planks").customLoader(StyleBlockModelBuilder::begin);
				msmb.base(bsp.models().cubeAll(base.name + "_plank_base",
						ResourceLocation.fromNamespaceAndPath(base.namespace, "block/" + base.name + "_planks")));

				for (String s : StyleData.PLANKS.getTypes())
					msmb.add(new StyleModelBuilder(s,
							bsp.modLoc(PLANK_BLOCK.location(base) + "planks/" + s.toLowerCase())));

				BlockModelBuilder bmb = msmb.end();
				b.modelFile(bmb);
				bsp.simpleBlock(PLANK_BLOCK.BLOCK.get(), b.build());

				StyleBlockModelBuilder<BlockModelBuilder> msmb2 = bsp.models().getBuilder(base.extraFolder() + "planks")
						.customLoader(StyleBlockModelBuilder::begin);
				msmb2.base(bsp.models().cubeAll("planks_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.PLANKS.getTypes())
					msmb2.add(new StyleModelBuilder(s,
							bsp.modLoc(PLANK_BLOCK.location(base) + "planks/" + s.toLowerCase() + "_inventory")));

				ConfiguredModel.builder().modelFile(msmb2.end()).build();
			}
			if (PLANK_SLAB.shouldGenerate()) {
				StyleBlockModelBuilder<BlockModelBuilder> plank_slab_bottom = bsp.models()
						.getBuilder(PLANK.location(base) + "plank_slab_bottom")
						.customLoader(StyleBlockModelBuilder::begin);
				plank_slab_bottom.base(bsp.models().cubeAll("plank_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.PLANKS.getTypes())
					plank_slab_bottom.add(new StyleModelBuilder(s,
							bsp.modLoc(PLANK.location(base) + "slab/" + s.toLowerCase() + "_bottom")));

				StyleBlockModelBuilder<BlockModelBuilder> plank_slab_top = bsp.models()
						.getBuilder(PLANK.location(base) + "plank_slab_top")
						.customLoader(StyleBlockModelBuilder::begin);
				plank_slab_top.base(bsp.models().cubeAll("plank_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.PLANKS.getTypes())
					plank_slab_top.add(new StyleModelBuilder(s,
							bsp.modLoc(PLANK.location(base) + "slab/" + s.toLowerCase() + "_top")));

				StyleBlockModelBuilder<BlockModelBuilder> plank_slab_full = bsp.models()
						.getBuilder(PLANK.location(base) + "plank_slab_full")
						.customLoader(StyleBlockModelBuilder::begin);
				plank_slab_full.base(bsp.models().cubeAll("plank_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.PLANKS.getTypes())
					plank_slab_full.add(new StyleModelBuilder(s,
							bsp.modLoc(PLANK.location(base) + "slab/" + s.toLowerCase() + "_full")));

				bsp.slabBlock((SlabBlock) PLANK_SLAB.BLOCK.get(), plank_slab_bottom.end(), plank_slab_top.end(),
						plank_slab_full.end());

				StyleBlockModelBuilder<BlockModelBuilder> msmb2 = bsp.models()
						.getBuilder(base.extraFolder() + "plank_slab").customLoader(StyleBlockModelBuilder::begin);
				msmb2.base(bsp.models().cubeAll("planks_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.PLANKS.getTypes())
					msmb2.add(new StyleModelBuilder(s,
							bsp.modLoc(PLANK_BLOCK.location(base) + "slab/" + s.toLowerCase() + "_inventory")));

				ConfiguredModel.builder().modelFile(msmb2.end()).build();
			}
			if (PLANK_STAIRS.shouldGenerate()) {
				StyleBlockModelBuilder<BlockModelBuilder> plank_stairs_standard = bsp.models()
						.getBuilder(PLANK.location(base) + "plank_stairs").customLoader(StyleBlockModelBuilder::begin);
				plank_stairs_standard.base(bsp.models().cubeAll("plank_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.PLANKS.getTypes()) {
					plank_stairs_standard.add(
							new StyleModelBuilder(s, bsp.modLoc(PLANK.location(base) + "stairs/" + s.toLowerCase())));
				}

				StyleBlockModelBuilder<BlockModelBuilder> plank_stairs_inner = bsp.models()
						.getBuilder(PLANK.location(base) + "plank_stairs_inner")
						.customLoader(StyleBlockModelBuilder::begin);
				plank_stairs_inner.base(bsp.models().cubeAll("plank_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.PLANKS.getTypes()) {
					plank_stairs_inner.add(new StyleModelBuilder(s,
							bsp.modLoc(PLANK.location(base) + "stairs/" + s.toLowerCase() + "_inner")));
				}

				StyleBlockModelBuilder<BlockModelBuilder> plank_stairs_outer = bsp.models()
						.getBuilder(PLANK.location(base) + "plank_stairs_outer")
						.customLoader(StyleBlockModelBuilder::begin);
				plank_stairs_outer.base(bsp.models().cubeAll("plank_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.PLANKS.getTypes()) {
					plank_stairs_outer.add(new StyleModelBuilder(s,
							bsp.modLoc(PLANK.location(base) + "stairs/" + s.toLowerCase() + "_outer")));
				}

				stairsBlock((StairBlock) PLANK_STAIRS.BLOCK.get(), plank_stairs_standard.end(),
						plank_stairs_inner.end(), plank_stairs_outer.end(), bsp);

				StyleBlockModelBuilder<BlockModelBuilder> plank_stairs_inventory = bsp.models()
						.getBuilder(base.extraFolder() + "plank_stairs").customLoader(StyleBlockModelBuilder::begin);
				plank_stairs_inventory.base(bsp.models().cubeAll("plank_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.PLANKS.getTypes()) {
					plank_stairs_inventory.add(new StyleModelBuilder(s,
							bsp.modLoc(PLANK.location(base) + "stairs/" + s.toLowerCase() + "_inventory")));

					ConfiguredModel.builder().modelFile(plank_stairs_inventory.end()).build();
				}

			}
		}
	}

	private void plankModel(CompendiumBlockHandler block, _MaterialBase base, BlockStateProvider bsp, String extra) {
		if (block.shouldGenerate()) {
			extra = extra + "plank";
			StyleBlockModelBuilder<BlockModelBuilder> base_model_horizontal = bsp.models()
					.getBuilder(block.location(base) + extra + "/horizontal")
					.customLoader(StyleBlockModelBuilder::begin);
			base_model_horizontal.base(bsp.models().cubeAll("plank_base", bsp.mcLoc("block/oak_planks")));

			for (String s : StyleData.PLANK.getTypes())
				base_model_horizontal.add(new StyleModelBuilder(s,
						bsp.modLoc(PLANK.location(base) + extra + "/" + s.toLowerCase() + "_horizontal")));

			StyleBlockModelBuilder<BlockModelBuilder> base_model_horizontal2 = bsp.models()
					.getBuilder(block.location(base) + extra + "/horizontal_rot")
					.customLoader(StyleBlockModelBuilder::begin);
			base_model_horizontal2.base(bsp.models().cubeAll("plank_base", bsp.mcLoc("block/oak_planks")));

			for (String s : StyleData.PLANK.getTypes())
				base_model_horizontal2.add(new StyleModelBuilder(s,
						bsp.modLoc(PLANK.location(base) + extra + "/" + s.toLowerCase() + "_horizontal_rot")));

			StyleBlockModelBuilder<BlockModelBuilder> base_model_vertical = bsp.models()
					.getBuilder(block.location(base) + extra + "/vertical").customLoader(StyleBlockModelBuilder::begin);
			base_model_vertical.base(bsp.models().cubeAll("plank_base", bsp.mcLoc("block/oak_planks")));

			for (String s : StyleData.PLANK.getTypes())
				base_model_vertical.add(new StyleModelBuilder(s,
						bsp.modLoc(PLANK.location(base) + extra + "/" + s.toLowerCase() + "_vertical")));

			StyleBlockModelBuilder<BlockModelBuilder> model_cap = bsp.models()
					.getBuilder(block.location(base) + extra + "/cap").customLoader(StyleBlockModelBuilder::begin);
			model_cap.base(bsp.models().cubeAll("plank_base", bsp.mcLoc("block/oak_planks")));

			for (String s : StyleData.PLANK.getTypes())
				model_cap.add(new StyleModelBuilder(s,
						bsp.modLoc(PLANK.location(base) + extra + "/" + s.toLowerCase() + "_cap")));

			BlockModelBuilder cap = model_cap.end();

			bsp.getMultipartBuilder(block.BLOCK.get()).part().modelFile(base_model_horizontal2.end()).addModel()
					.nestedGroup().useOr()

					.nestedGroup().condition(BlockStateProperties.NORTH, false)
					.condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false)
					.condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.UP, false)
					.condition(BlockStateProperties.DOWN, false).endNestedGroup()

					.nestedGroup().condition(BlockStateProperties.NORTH, true)
					.condition(BlockStateProperties.SOUTH, true).condition(BlockStateProperties.EAST, false)
					.condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.UP, false)
					.condition(BlockStateProperties.DOWN, false).endNestedGroup()

					.nestedGroup().condition(BlockStateProperties.NORTH, true)
					.condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.WEST, false)
					.condition(BlockStateProperties.UP, false).condition(BlockStateProperties.DOWN, false)
					.endNestedGroup()

					.nestedGroup().condition(BlockStateProperties.SOUTH, true)
					.condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.WEST, false)
					.condition(BlockStateProperties.UP, false).condition(BlockStateProperties.DOWN, false)
					.endNestedGroup().end().end().part().modelFile(base_model_horizontal.end()).addModel().nestedGroup()
					.useOr().nestedGroup().condition(BlockStateProperties.WEST, true)
					.condition(BlockStateProperties.EAST, true).condition(BlockStateProperties.UP, false)
					.condition(BlockStateProperties.DOWN, false).endNestedGroup().nestedGroup()
					.condition(BlockStateProperties.EAST, true).condition(BlockStateProperties.UP, false)
					.condition(BlockStateProperties.DOWN, false).endNestedGroup().nestedGroup()
					.condition(BlockStateProperties.WEST, true).condition(BlockStateProperties.UP, false)
					.condition(BlockStateProperties.DOWN, false).endNestedGroup().end().end().part()
					.modelFile(base_model_vertical.end()).addModel().useOr().condition(BlockStateProperties.UP, true)
					.condition(BlockStateProperties.DOWN, true).end().part().modelFile(cap).addModel()
					.condition(BlockStateProperties.UP, true).end().part().modelFile(cap).rotationX(180).addModel()
					.condition(BlockStateProperties.DOWN, true).end().part().modelFile(cap).rotationX(90).addModel()
					.condition(BlockStateProperties.NORTH, true).end().part().modelFile(cap).rotationX(90)
					.rotationY(180).addModel().condition(BlockStateProperties.SOUTH, true).end().part().modelFile(cap)
					.rotationX(90).rotationY(-90).addModel().condition(BlockStateProperties.WEST, true).end().part()
					.modelFile(cap).rotationX(90).rotationY(90).addModel().condition(BlockStateProperties.EAST, true)
					.end();
		}
	}

	private void stairsBlock(StairBlock block, ModelFile stairs, ModelFile stairsInner, ModelFile stairsOuter,
			BlockStateProvider bsp) {
		bsp.getVariantBuilder(block).forAllStatesExcept(state -> {
			Direction facing = state.getValue(StairBlock.FACING);
			Half half = state.getValue(StairBlock.HALF);
			StairsShape shape = state.getValue(StairBlock.SHAPE);
			int yRot = (int) facing.getClockWise().toYRot(); // Stairs model is rotated 90 degrees clockwise for some
			// reason
			if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT) {
				yRot += 270; // Left facing stairs are rotated 90 degrees clockwise
			}
			if (shape != StairsShape.STRAIGHT && half == Half.TOP) {
				yRot += 90; // Top stairs are rotated 90 degrees clockwise
			}
			yRot %= 360;
//			boolean uvlock = yRot != 0 || half == Half.TOP; // Don't set uvlock for states that have no rotation
			return ConfiguredModel.builder()
					.modelFile(shape == StairsShape.STRAIGHT ? stairs
							: shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT ? stairsInner
									: stairsOuter)
					.rotationX(half == Half.BOTTOM ? 0 : 180).rotationY(yRot).uvLock(true).build();
		}, StairBlock.WATERLOGGED);
	}

	@Override
	public void blockModel(_MaterialBase base, IndexBlockModelProvider ibmp) {

		for (String s : StyleData.PLANK.getTypes()) {
			ibmp.withExistingParent(PLANK.location(base) + "plank/" + s, ibmp.modLoc("block/bases/plank/" + s))
					.texture("0", ibmp.modLoc(PLANK.location(base) + "planks/" + "plank"));

			ibmp.withExistingParent(PLANK.location(base) + "plank/" + s + "_horizontal",
					ibmp.modLoc("block/bases/plank/" + s + "_horizontal"))
					.texture("0", ibmp.modLoc(PLANK.location(base) + "planks/" + "plank"));

			ibmp.withExistingParent(PLANK.location(base) + "plank/" + s + "_horizontal_rot",
					ibmp.modLoc("block/bases/plank/" + s + "_horizontal2"))
					.texture("0", ibmp.modLoc(PLANK.location(base) + "planks/" + "plank"));

			ibmp.withExistingParent(PLANK.location(base) + "plank/" + s + "_vertical",
					ibmp.modLoc("block/bases/plank/" + s + "_vertical"))
					.texture("0", ibmp.modLoc(PLANK.location(base) + "planks/" + "plank"));

			ibmp.withExistingParent(PLANK.location(base) + "plank/" + s + "_cap",
					ibmp.modLoc("block/bases/plank/" + s + "_cap"))
					.texture("0", ibmp.modLoc(PLANK.location(base) + "planks/" + "plank"));

//			ibmp.withExistingParent(base.itemFolder() + s + "_inventory", ibmp.modLoc("item/" + s + "_inventory"))
//					.texture("0", ibmp.modLoc(PLANK.location(base) + "planks/planks_seamless"))
//					.texture("1", ibmp.modLoc(PLANK.location(base) + "planks/planks_seamless"));

		}

		for (String s : StyleData.PLANKS.getTypes()) {
			ibmp.withExistingParent(PLANK_BLOCK.location(base) + "/planks/" + s + "_inventory",
					ibmp.mcLoc("block/cube_all"))
					.texture("all", ibmp.modLoc(PLANK_BLOCK.location(base) + "planks/" + s));

			ibmp.withExistingParent(PLANK_BLOCK.location(base) + "/planks/" + s, ibmp.mcLoc("block/cube_all"))
					.texture("all", ibmp.modLoc(PLANK_BLOCK.location(base) + "planks/" + s));

			ibmp.slab(PLANK.location(base) + "/slab/" + s + "_inventory",
					ibmp.modLoc(PLANK.location(base) + "planks/" + s),
					ibmp.modLoc(PLANK.location(base) + "planks/" + s),
					ibmp.modLoc(PLANK.location(base) + "planks/" + s));
			ibmp.slab(PLANK.location(base) + "/slab/" + s + "_bottom",
					ibmp.modLoc(PLANK.location(base) + "planks/" + s),
					ibmp.modLoc(PLANK.location(base) + "planks/" + s),
					ibmp.modLoc(PLANK.location(base) + "planks/" + s));
			ibmp.slabTop(PLANK.location(base) + "/slab/" + s + "_top",
					ibmp.modLoc(PLANK.location(base) + "planks/" + s),
					ibmp.modLoc(PLANK.location(base) + "planks/" + s),
					ibmp.modLoc(PLANK.location(base) + "planks/" + s));
			ibmp.cubeAll(PLANK.location(base) + "/slab/" + s + "_full",
					ibmp.modLoc(PLANK.location(base) + "planks/" + s));

			ibmp.withExistingParent(PLANK_BLOCK.location(base) + "/slab/" + s, ibmp.mcLoc("block/cube_all"))
					.texture("all", ibmp.modLoc(PLANK_BLOCK.location(base) + "planks/" + s));

			ibmp.stairs(PLANK.location(base) + "/stairs/" + s + "_inventory",
					ibmp.modLoc(PLANK.location(base) + "planks/" + s),
					ibmp.modLoc(PLANK.location(base) + "planks/" + s),
					ibmp.modLoc(PLANK.location(base) + "planks/" + s));
			ibmp.stairs(PLANK.location(base) + "/stairs/" + s, ibmp.modLoc(PLANK.location(base) + "planks/" + s),
					ibmp.modLoc(PLANK.location(base) + "planks/" + s),
					ibmp.modLoc(PLANK.location(base) + "planks/" + s));
			ibmp.stairsInner(PLANK.location(base) + "/stairs/" + s + "_inner",
					ibmp.modLoc(PLANK.location(base) + "planks/" + s),
					ibmp.modLoc(PLANK.location(base) + "planks/" + s),
					ibmp.modLoc(PLANK.location(base) + "planks/" + s));
			ibmp.stairsOuter(PLANK.location(base) + "/stairs/" + s + "_outer",
					ibmp.modLoc(PLANK.location(base) + "planks/" + s),
					ibmp.modLoc(PLANK.location(base) + "planks/" + s),
					ibmp.modLoc(PLANK.location(base) + "planks/" + s));
//			}
		}
	}

	@Override
	public void itemModel(_MaterialBase base, ItemModelProvider tmp) {
		if (this.autoGenItemModel) {
			DataUtil.basicMaterial3DItem(tmp, PLANK.BLOCK_ITEM.get(), base, Compendium.modLoc("item/plank"),
					base.getType(), tmp.mcLoc("block/" + base.name.toLowerCase() + "_planks"));

			if (PLANK_BLOCK.shouldGenerate())
				tmp.withExistingParent(PLANK_BLOCK.BLOCK_ITEM.getRegisteredName(), tmp.modLoc("item/window"));

			if (PLANK_SLAB.shouldGenerate())
				tmp.withExistingParent(PLANK_SLAB.BLOCK_ITEM.getRegisteredName(), tmp.modLoc("item/window"));

			if (PLANK_STAIRS.shouldGenerate())
				tmp.withExistingParent(PLANK_STAIRS.BLOCK_ITEM.getRegisteredName(), tmp.modLoc("item/window"));
		}
	}

	@Override
	public void engLoc(_MaterialBase base, LanguageProvider lp) {
		StringBuilder material_name = new StringBuilder();
		for (String word : base.name.split("_")) {
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			material_name.append(word).append(" ");
		}
		if (PLANK.shouldGenerate()) {
			lp.add(this.PLANK.BLOCK_ITEM.get(), material_name + "Plank");
		}
		if (PLANK_BLOCK.shouldGenerate()) {
			lp.add(this.PLANK_BLOCK.BLOCK_ITEM.get(), material_name + "Styled Planks");
		}
		if (PLANK_SLAB.shouldGenerate()) {
			lp.add(this.PLANK_SLAB.BLOCK_ITEM.get(), material_name + "Styled Plank Slab");
		}
		if (PLANK_STAIRS.shouldGenerate()) {
			lp.add(this.PLANK_STAIRS.BLOCK_ITEM.get(), material_name + "Styled Plank Stairs");
		}
	}

	@Override
	public void recipes(_MaterialBase base, RecipeOutput consumer) {
		if (!this.PLANK.isIgnored()) {
			SawBuckRecipeBuilder
					.saw(Ingredient.of(TagKey.create(Registries.ITEM,
							ResourceLocation.withDefaultNamespace("log/small/" + base.name))),
							new ItemStack(PLANK.BLOCK_ITEM.get(), 2), Vec3.ZERO)
					.tool(Ingredient.of(CompendiumTags.SAW), 1, true, RecipeLootTables.SAW_DUST, List.of(),
							Recipes.standardSawBuckSawModel(TagUtil.modLoc("iron_saw_item"), 0),
							new BlacklistedModel(TagUtil.modLoc("extra/split_log_stage3"), true,
									new AnimationFloatTransform()
											.setRotation(new AnimatedFloatVector3().setY(
													new AnimatedFloat(0.000F, 90.000F, 0.000F, 0.000F, false, false)))
											.setLocation(new AnimatedFloatVector3()
													.setX(new AnimatedFloat(0.000F, -8.000F, 0.000F, 0.000F, false,
															false))
													.setY(new AnimatedFloat(0.000F, -13.000F, 0.000F, 0.000F, false,
															false))
													.setZ(new AnimatedFloat(0.000F, 20.000F, 0.000F, 0.000F, false,
															false)))
											.setScale(new AnimatedFloatVector3()
													.setX(new AnimatedFloat(0.500F, 0.500F, 0.000F, 0.000F, false,
															false))
													.setY(new AnimatedFloat(0.500F, 0.500F, 0.000F, 0.000F, false,
															false))
													.setZ(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false,
															false)))))
					.save(consumer);

			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ((MaterialWood) base).PLANKS.BLOCK_ITEM, 2)
					.define('p', PLANK.BLOCK_ITEM).pattern("pp").pattern("pp")
					.unlockedBy("plank", CriteriaTriggers.INVENTORY_CHANGED
							.createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(),
									InventoryChangeTrigger.TriggerInstance.Slots.ANY,
									List.of(ItemPredicate.Builder.item().of(PLANK.BLOCK_ITEM.asItem()).build()))))
					.save(consumer, TagUtil.modLoc(base.name + "_planks"));

			ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, PLANK_BLOCK.BLOCK_ITEM, 2)
					.define('p', PLANK.BLOCK_ITEM).pattern("p p").pattern("   ").pattern("p p")
					.unlockedBy("plank", CriteriaTriggers.INVENTORY_CHANGED
							.createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(),
									InventoryChangeTrigger.TriggerInstance.Slots.ANY,
									List.of(ItemPredicate.Builder.item().of(PLANK.BLOCK_ITEM.asItem()).build()))))
					.save(consumer);

			WorkbenchRecipeBuilder.shaped(PLANK_SLAB.BLOCK_ITEM, 6).define('p', PLANK.BLOCK_ITEM).pattern("ppp")
					.pattern("ppp")
					.unlockedBy("plank", CriteriaTriggers.INVENTORY_CHANGED
							.createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(),
									InventoryChangeTrigger.TriggerInstance.Slots.ANY,
									List.of(ItemPredicate.Builder.item().of(PLANK.BLOCK_ITEM.asItem()).build()))))
					.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
							Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer"), 0))
					.save(consumer);

			WorkbenchRecipeBuilder.shaped(PLANK_STAIRS.BLOCK_ITEM, 6).define('p', PLANK.BLOCK_ITEM).pattern("p  ")
					.pattern("pp ").pattern("ppp")
					.unlockedBy("plank", CriteriaTriggers.INVENTORY_CHANGED
							.createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(),
									InventoryChangeTrigger.TriggerInstance.Slots.ANY,
									List.of(ItemPredicate.Builder.item().of(PLANK.BLOCK_ITEM.asItem()).build()))))
					.tool(Ingredient.of(CompendiumTags.HAMMER), 4, true, RecipeLootTables.EMPTY, List.of(),
							Recipes.standardHammeringModel(TagUtil.modLoc("gold_hammer"), 0))
					.save(consumer);

			ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, PLANK.BLOCK_ITEM, 1)
					.requires(PLANK_SLAB.BLOCK_ITEM)
					.unlockedBy("plank_slab", CriteriaTriggers.INVENTORY_CHANGED
							.createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(),
									InventoryChangeTrigger.TriggerInstance.Slots.ANY,
									List.of(ItemPredicate.Builder.item().of(PLANK_SLAB.BLOCK_ITEM.asItem()).build()))))
					.save(consumer, TagUtil.modLoc(base.name + "_slab_to_planks"));

			ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, PLANK.BLOCK_ITEM, 1)
					.requires(PLANK_STAIRS.BLOCK_ITEM)
					.unlockedBy("plank_stairs",
							CriteriaTriggers.INVENTORY_CHANGED
									.createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(),
											InventoryChangeTrigger.TriggerInstance.Slots.ANY,
											List.of(ItemPredicate.Builder.item().of(PLANK_STAIRS.BLOCK_ITEM.asItem())
													.build()))))
					.save(consumer, TagUtil.modLoc(base.name + "_stairs_to_planks"));
		}
	}

	@Override
	public void blockLoot(_MaterialBase base, BlockLootSubProvider blp) {
		blp.add(PLANK.BLOCK.get(), BlockLootTables.createStyleItemDrop(PLANK.BLOCK.get()));
		blp.add(PLANK_BLOCK.BLOCK.get(), BlockLootTables.createStyleItemDrop(PLANK_STAIRS.BLOCK.get()));
		blp.add(PLANK_SLAB.BLOCK.get(), this.createSlabItemTable(PLANK_SLAB.BLOCK.get()));
		blp.add(PLANK_STAIRS.BLOCK.get(), BlockLootTables.createStyleItemDrop(PLANK_STAIRS.BLOCK.get()));
	}

	protected LootTable.Builder createSlabItemTable(Block block) {
		return LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
						.add(LootItem.lootTableItem(block)
								.apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
										.when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
												.setProperties(StatePropertiesPredicate.Builder.properties()
														.hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))
								.apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
										.include(CompendiumComponents.STYLE.get()))

						));
	}

	@Override
	public void setupItemTags(_MaterialBase base, ItemTagsProvider itp) {
		if (!PLANK.isIgnored()) {
			PLANK.itemTag(itp);
			itp.tag(CompendiumTags.PLANK).add(PLANK.BLOCK_ITEM.asItem());
		}
		if (!PLANK_BLOCK.isIgnored()) {
			PLANK_BLOCK.itemTag(itp);
		}
		if (!PLANK_SLAB.isIgnored()) {
			PLANK_SLAB.itemTag(itp);
		}
		if (!PLANK_STAIRS.isIgnored()) {
			PLANK_STAIRS.itemTag(itp);
		}
	}

	@Override
	public void setupBlockTags(_MaterialBase base, BlockTagsProvider btp) {
		if (!PLANK.isIgnored()) {
			PLANK.blockTag(btp);
		}
		if (!PLANK_BLOCK.isIgnored()) {
			PLANK_BLOCK.blockTag(btp);
		}
		if (!PLANK_SLAB.isIgnored()) {
			PLANK_SLAB.blockTag(btp);
		}
		if (!PLANK_STAIRS.isIgnored()) {
			PLANK_STAIRS.blockTag(btp);
		}
	}

	@Override
	public void setupClient(_MaterialBase base, FMLClientSetupEvent event) {
		// TODO Auto-generated method stub

	}

	public static class Serializer extends MaterialExtensionSerializer<ExtensionExtraPlanks> {

		public Serializer() {
			super("EXTRAPLANKS");
		}

		@Override
		public JsonElement serialize(ExtensionExtraPlanks src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("type", type);
			j.addProperty("loadPlank", src.PLANK.getGeneration().toString());
			j.addProperty("loadPlankBlock", src.PLANK_BLOCK.getGeneration().toString());
			j.addProperty("loadPlankSlab", src.PLANK_SLAB.getGeneration().toString());
			j.addProperty("loadPlankStairs", src.PLANK_STAIRS.getGeneration().toString());

			return j;
		}

		@Override
		public ExtensionExtraPlanks deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			String loadPlank = j.get("loadPlank").getAsString();
			String loadPlankBlock = j.get("loadPlankBlock").getAsString();
			String loadPlankSlab = j.get("loadPlankSlab").getAsString();
			String loadPlankStairs = j.get("loadPlankStairs").getAsString();

			return new ExtensionExtraPlanks(Generate.valueOf(loadPlank), Generate.valueOf(loadPlankBlock),
					Generate.valueOf(loadPlankSlab), Generate.valueOf(loadPlankStairs));
		}

	}

	@Override
	public void otherLoot(_MaterialBase base, LootTableSubProvider lsp) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isIndexItem(_MaterialBase base, ItemStack stack) {
		if (PLANK.is(stack))
			return true;
		if (PLANK_BLOCK.is(stack))
			return true;
		if (PLANK_SLAB.is(stack))
			return true;
		if (PLANK_STAIRS.is(stack))
			return true;

		return false;
	}

	@Override
	public Optional<IIndexEntry> getEntryItemBelongsTo(_MaterialBase base, ItemStack stack) {
		if (PLANK.is(stack))
			return Optional.of(base);
		if (PLANK_BLOCK.is(stack))
			return Optional.of(base);
		if (PLANK_SLAB.is(stack))
			return Optional.of(base);
		if (PLANK_STAIRS.is(stack))
			return Optional.of(base);

		return Optional.empty();
	}

}
