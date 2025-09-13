package com.lance5057.compendium.index.material.extensions.wood;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.blocks.PipeStyleBlock;
import com.lance5057.compendium.data.IndexBlockModelProvider;
import com.lance5057.compendium.data.Recipes;
import com.lance5057.compendium.data.loottables.RecipeLootTables;
import com.lance5057.compendium.data.recipebuilders.SawBuckRecipeBuilder;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extensions.MaterialExtensionSerializer;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.index.util.DataUtil;
import com.lance5057.compendium.util.TagUtil;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ExtensionExtraLogs extends _MaterialExtension {
	public static final VoxelShape smallLogHori1 = Block.box(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 16.0D);
	public static final VoxelShape smallLogHori2 = Block.box(0.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D);
	public static final VoxelShape smallLogVert = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);

	public final CompendiumBlockHandler SMALL_LOG;
	public final CompendiumBlockHandler SMALL_LOGS;
	public final CompendiumBlockHandler SMALL_LOGS_SLAB;
	public final CompendiumBlockHandler SMALL_LOGS_CORNER;
	public final CompendiumBlockHandler SMALL_LOGS_STAIRS;

	public final CompendiumBlockHandler STRIPPED_SMALL_LOG;
	public final CompendiumBlockHandler STRIPPED_SMALL_LOGS;
	public final CompendiumBlockHandler STRIPPED_SMALL_LOGS_SLAB;
	public final CompendiumBlockHandler STRIPPED_SMALL_LOGS_CORNER;
	public final CompendiumBlockHandler STRIPPED_SMALL_LOGS_STAIRS;

	public ExtensionExtraLogs(boolean smallLog, boolean smallLogs, boolean smallLogsCorner, boolean smallLogsSlab,
			boolean smallLogsStairs, boolean strippedSmallLog, boolean strippedSmallLogs,
			boolean strippedSmallLogsCorner, boolean strippedSmallLogsSlab, boolean strippedSmallLogsStairs) {

		SMALL_LOG = new CompendiumBlockHandler("small_log");
		SMALL_LOGS = new CompendiumBlockHandler("small_logs");
		SMALL_LOGS_SLAB = new CompendiumBlockHandler("small_logs_slab");
		SMALL_LOGS_CORNER = new CompendiumBlockHandler("small_logs_corner");
		SMALL_LOGS_STAIRS = new CompendiumBlockHandler("small_logs_stairs");

		STRIPPED_SMALL_LOG = new CompendiumBlockHandler("stripped_small_log");
		STRIPPED_SMALL_LOGS = new CompendiumBlockHandler("stripped_small_logs");
		STRIPPED_SMALL_LOGS_SLAB = new CompendiumBlockHandler("stripped_small_logs_slab");
		STRIPPED_SMALL_LOGS_CORNER = new CompendiumBlockHandler("stripped_small_logs_corner");
		STRIPPED_SMALL_LOGS_STAIRS = new CompendiumBlockHandler("stripped_small_logs_stairs");

		SMALL_LOG.setEnabled(smallLog);
		SMALL_LOGS.setEnabled(smallLogs);
		SMALL_LOGS_SLAB.setEnabled(smallLogsSlab);
		SMALL_LOGS_CORNER.setEnabled(smallLogsCorner);
		SMALL_LOGS_STAIRS.setEnabled(smallLogsStairs);

		STRIPPED_SMALL_LOG.setEnabled(strippedSmallLog);
		STRIPPED_SMALL_LOGS.setEnabled(strippedSmallLogs);
		STRIPPED_SMALL_LOGS_SLAB.setEnabled(strippedSmallLogsSlab);
		STRIPPED_SMALL_LOGS_CORNER.setEnabled(strippedSmallLogsCorner);
		STRIPPED_SMALL_LOGS_STAIRS.setEnabled(strippedSmallLogsStairs);
	}

	@Override
	public void setup(_MaterialBase base) {
		SMALL_LOG.setup(base, () -> new PipeStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
		SMALL_LOGS.setup(base, () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.DARK_OAK_LOG)));
		SMALL_LOGS_CORNER.setup(base, () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.DARK_OAK_LOG)));
		SMALL_LOGS_SLAB.setup(base, () -> new SlabBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_SLAB)));
		SMALL_LOGS_STAIRS.setup(base, () -> new StairBlock(SMALL_LOGS.BLOCK.get().defaultBlockState(),
				Block.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS)));
		CompendiumBlockEntities.validStyleBlocks.add(SMALL_LOG.BLOCK);

		STRIPPED_SMALL_LOG.setup(base, () -> new PipeStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
		STRIPPED_SMALL_LOGS.setup(base, () -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.DARK_OAK_LOG)));
		STRIPPED_SMALL_LOGS_CORNER.setup(base,
				() -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.DARK_OAK_LOG)));
		STRIPPED_SMALL_LOGS_SLAB.setup(base, () -> new SlabBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_SLAB)));
		STRIPPED_SMALL_LOGS_STAIRS.setup(base, () -> new StairBlock(SMALL_LOGS.BLOCK.get().defaultBlockState(),
				Block.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS)));
		CompendiumBlockEntities.validStyleBlocks.add(STRIPPED_SMALL_LOG.BLOCK);

	}

	@Override
	public void tab(_MaterialBase base, Output output) {
		SMALL_LOG.tab(base, output);
		SMALL_LOGS.tab(base, output);
		SMALL_LOGS_CORNER.tab(base, output);
		SMALL_LOGS_SLAB.tab(base, output);
		SMALL_LOGS_STAIRS.tab(base, output);

		STRIPPED_SMALL_LOG.tab(base, output);
		STRIPPED_SMALL_LOGS.tab(base, output);
		STRIPPED_SMALL_LOGS_CORNER.tab(base, output);
		STRIPPED_SMALL_LOGS_SLAB.tab(base, output);
		STRIPPED_SMALL_LOGS_STAIRS.tab(base, output);
	}

	@Override
	public void blockModel(_MaterialBase base, IndexBlockModelProvider ibmp) {

		ibmp.withExistingParent(SMALL_LOG.location(base) + "_block", ibmp.modLoc("item/small_log")).texture("0",
				ibmp.modLoc(SMALL_LOG.location(base) + "s_corner"));

		ibmp.withExistingParent(STRIPPED_SMALL_LOG.location(base) + "_block", ibmp.modLoc("item/small_log"))
				.texture("0", ibmp.modLoc(STRIPPED_SMALL_LOG.location(base) + "s_corner"));
	}

	@Override
	public void blockStateModel(_MaterialBase base, BlockStateProvider bsp) {
		if (this.autoGenBlockModel) {
			smallLogsModel(SMALL_LOG, base, bsp);
			smallLogsModel(STRIPPED_SMALL_LOG, base, bsp);
			if (SMALL_LOGS.enabled()) {
				DataUtil.axisMaterialBlock(bsp, base, SMALL_LOGS, "", "solid", base.getType());
			}
			if (STRIPPED_SMALL_LOGS.enabled()) {
				DataUtil.axisMaterialBlock(bsp, base, STRIPPED_SMALL_LOGS, "", "solid", base.getType());
			}
			if (SMALL_LOGS_CORNER.enabled()) {

				bsp.axisBlock((RotatedPillarBlock) SMALL_LOGS_CORNER.BLOCK.get(),
						bsp.models()
								.withExistingParent(SMALL_LOGS_CORNER.location(base) + "_corner_block",
										Compendium.modLoc("small_logs_corner"))
								.texture("1", Compendium.modLoc(SMALL_LOGS_CORNER.location(base)))
								.texture("2", Compendium.modLoc(SMALL_LOGS.location(base)))
                                .texture("particle", Compendium.modLoc(SMALL_LOGS.location(base))),

						bsp.models()
								.withExistingParent(SMALL_LOGS_CORNER.location(base) + "_corner_side_block",
										Compendium.modLoc("small_logs_corner_side"))
								.texture("1", Compendium.modLoc(SMALL_LOGS_CORNER.location(base)))
								.texture("2", Compendium.modLoc(SMALL_LOGS.location(base)))
                                .texture("particle", Compendium.modLoc(SMALL_LOGS.location(base))));
			}

			if (STRIPPED_SMALL_LOGS_CORNER.enabled()) {

				bsp.axisBlock((RotatedPillarBlock) STRIPPED_SMALL_LOGS_CORNER.BLOCK.get(),
						bsp.models()
								.withExistingParent(STRIPPED_SMALL_LOGS_CORNER.location(base) + "_corner_block",
										Compendium.modLoc("small_logs_corner"))
								.texture("1", Compendium.modLoc(STRIPPED_SMALL_LOGS_CORNER.location(base)))
								.texture("2", Compendium.modLoc(STRIPPED_SMALL_LOGS.location(base)))
                                .texture("particle", Compendium.modLoc(STRIPPED_SMALL_LOGS.location(base))),

						bsp.models()
								.withExistingParent(STRIPPED_SMALL_LOGS_CORNER.location(base) + "_corner_side_block",
										Compendium.modLoc("small_logs_corner_side"))
								.texture("1", Compendium.modLoc(STRIPPED_SMALL_LOGS_CORNER.location(base)))
								.texture("2", Compendium.modLoc(STRIPPED_SMALL_LOGS.location(base)))
                                .texture("particle", Compendium.modLoc(STRIPPED_SMALL_LOGS.location(base))));
			}
			if (SMALL_LOGS_SLAB.enabled()) {
				bsp.slabBlock((SlabBlock) SMALL_LOGS_SLAB.BLOCK.get(),
						bsp.models()
								.withExistingParent(SMALL_LOGS_SLAB.location(base) + "_bottom_block",
										Compendium.modLoc("small_logs_slab_bottom"))
								.texture("0", Compendium.modLoc(SMALL_LOGS.location(base)))
								.texture("1", Compendium.modLoc(SMALL_LOGS_SLAB.location(base)))
                                .texture("particle", Compendium.modLoc(SMALL_LOGS.location(base))),
						bsp.models()
								.withExistingParent(SMALL_LOGS_SLAB.location(base) + "_top_block",
										Compendium.modLoc("small_logs_slab_top"))
								.texture("0", Compendium.modLoc(SMALL_LOGS.location(base)))
								.texture("1", Compendium.modLoc(SMALL_LOGS_SLAB.location(base)))
                                .texture("particle", Compendium.modLoc(SMALL_LOGS.location(base))),
						bsp.models()
								.withExistingParent(SMALL_LOGS_SLAB.location(base) + "_full_block",
										Compendium.modLoc("small_logs_slab_full"))
								.texture("0", Compendium.modLoc(SMALL_LOGS.location(base)))
								.texture("1", Compendium.modLoc(SMALL_LOGS_SLAB.location(base)))
                                .texture("particle", Compendium.modLoc(SMALL_LOGS.location(base))));
			}

			if (STRIPPED_SMALL_LOGS_SLAB.enabled()) {
				bsp.slabBlock((SlabBlock) STRIPPED_SMALL_LOGS_SLAB.BLOCK.get(),
						bsp.models()
								.withExistingParent(STRIPPED_SMALL_LOGS_SLAB.location(base) + "_bottom_block",
										Compendium.modLoc("small_logs_slab_bottom"))
								.texture("0", Compendium.modLoc(STRIPPED_SMALL_LOGS.location(base)))
								.texture("1", Compendium.modLoc(STRIPPED_SMALL_LOGS_SLAB.location(base)))
                                .texture("particle", Compendium.modLoc(STRIPPED_SMALL_LOGS.location(base))),
						bsp.models()
								.withExistingParent(STRIPPED_SMALL_LOGS_SLAB.location(base) + "_top_block",
										Compendium.modLoc("small_logs_slab_top"))
								.texture("0", Compendium.modLoc(STRIPPED_SMALL_LOGS.location(base)))
								.texture("1", Compendium.modLoc(STRIPPED_SMALL_LOGS_SLAB.location(base)))
                                .texture("particle", Compendium.modLoc(STRIPPED_SMALL_LOGS.location(base))),
						bsp.models()
								.withExistingParent(STRIPPED_SMALL_LOGS_SLAB.location(base) + "_full_block",
										Compendium.modLoc("small_logs_slab_full"))
								.texture("0", Compendium.modLoc(STRIPPED_SMALL_LOGS.location(base)))
								.texture("1", Compendium.modLoc(STRIPPED_SMALL_LOGS_SLAB.location(base)))
                                .texture("particle", Compendium.modLoc(STRIPPED_SMALL_LOGS.location(base))));
			}
			if (SMALL_LOGS_STAIRS.enabled()) {
				stairsBlock((StairBlock) SMALL_LOGS_STAIRS.BLOCK.get(),
						bsp.models()
								.withExistingParent(SMALL_LOGS_STAIRS.location(base) + "_block",
										Compendium.modLoc("small_logs_stairs"))
								.texture("0", Compendium.modLoc(SMALL_LOGS_CORNER.location(base)))
								.texture("1", Compendium.modLoc(SMALL_LOGS.location(base) + "_turned"))
								.texture("2", Compendium.modLoc(SMALL_LOGS_SLAB.location(base)))
                                .texture("particle", Compendium.modLoc(SMALL_LOGS.location(base))),
						bsp.models()
								.withExistingParent(SMALL_LOGS_STAIRS.location(base) + "_inner_block",
										Compendium.modLoc("small_logs_inner_stairs"))
								.texture("0", Compendium.modLoc(SMALL_LOGS_CORNER.location(base)))
								.texture("1", Compendium.modLoc(SMALL_LOGS.location(base) + "_turned"))
								.texture("2", Compendium.modLoc(SMALL_LOGS_SLAB.location(base)))
                                .texture("particle", Compendium.modLoc(SMALL_LOGS.location(base))),
						bsp.models()
								.withExistingParent(SMALL_LOGS_STAIRS.location(base) + "_outer_block",
										Compendium.modLoc("small_logs_outer_stairs"))
								.texture("0", Compendium.modLoc(SMALL_LOGS_CORNER.location(base)))
								.texture("1", Compendium.modLoc(SMALL_LOGS.location(base) + "_turned"))
								.texture("2", Compendium.modLoc(SMALL_LOGS_SLAB.location(base)))
                                .texture("particle", Compendium.modLoc(SMALL_LOGS.location(base))),
						bsp);
			}

			if (STRIPPED_SMALL_LOGS_STAIRS.enabled()) {
				stairsBlock((StairBlock) STRIPPED_SMALL_LOGS_STAIRS.BLOCK.get(),
						bsp.models()
								.withExistingParent(STRIPPED_SMALL_LOGS_STAIRS.location(base) + "_block",
										Compendium.modLoc("small_logs_stairs"))
								.texture("0", Compendium.modLoc(STRIPPED_SMALL_LOGS_CORNER.location(base)))
								.texture("1", Compendium.modLoc(STRIPPED_SMALL_LOGS.location(base) + "_turned"))
								.texture("2", Compendium.modLoc(STRIPPED_SMALL_LOGS_SLAB.location(base)))
                                .texture("particle", Compendium.modLoc(STRIPPED_SMALL_LOGS.location(base))),
						bsp.models()
								.withExistingParent(STRIPPED_SMALL_LOGS_STAIRS.location(base) + "_inner_block",
										Compendium.modLoc("small_logs_inner_stairs"))
								.texture("0", Compendium.modLoc(STRIPPED_SMALL_LOGS_CORNER.location(base)))
								.texture("1", Compendium.modLoc(STRIPPED_SMALL_LOGS.location(base) + "_turned"))
								.texture("2", Compendium.modLoc(STRIPPED_SMALL_LOGS_SLAB.location(base)))
                                .texture("particle", Compendium.modLoc(STRIPPED_SMALL_LOGS.location(base))),
						bsp.models()
								.withExistingParent(STRIPPED_SMALL_LOGS_STAIRS.location(base) + "_outer_block",
										Compendium.modLoc("small_logs_outer_stairs"))
								.texture("0", Compendium.modLoc(STRIPPED_SMALL_LOGS_CORNER.location(base)))
								.texture("1", Compendium.modLoc(STRIPPED_SMALL_LOGS.location(base) + "_turned"))
								.texture("2", Compendium.modLoc(STRIPPED_SMALL_LOGS_SLAB.location(base)))
                                .texture("particle", Compendium.modLoc(STRIPPED_SMALL_LOGS.location(base))),
						bsp);
			}
		}
	}

	private void smallLogsModel(CompendiumBlockHandler block, _MaterialBase base, BlockStateProvider bsp) {
		if (block.enabled()) {
			BlockModelBuilder base_model_horizontal = bsp.models()
					.withExistingParent(block.location(base) + "_horizontal", bsp.modLoc("block/small_log_horizontal"))
					.texture("0", bsp.modLoc(block.location(base) + "s_corner"));
			BlockModelBuilder base_model_horizontal2 = bsp.models()
					.withExistingParent(block.location(base) + "_horizontal_rot",
							bsp.modLoc("block/small_log_horizontal2"))
					.texture("0", bsp.modLoc(block.location(base) + "s_corner"));
			BlockModelBuilder base_model_vertical = bsp.models()
					.withExistingParent(block.location(base) + "_vertical", bsp.modLoc("block/small_log_vertical"))
					.texture("0", bsp.modLoc(block.location(base) + "s_corner"));
			BlockModelBuilder model_cap = bsp.models()
					.withExistingParent(block.location(base) + "_cap", bsp.modLoc("block/small_log_cap"))
					.texture("0", bsp.modLoc(block.location(base) + "s_corner"));

			bsp.getMultipartBuilder(block.BLOCK.get()).part().modelFile(base_model_horizontal2).addModel().nestedGroup()
					.useOr()

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
					.endNestedGroup().end().end().part().modelFile(base_model_horizontal).addModel().nestedGroup()
					.useOr().nestedGroup().condition(BlockStateProperties.WEST, true)
					.condition(BlockStateProperties.EAST, true).condition(BlockStateProperties.UP, false)
					.condition(BlockStateProperties.DOWN, false).endNestedGroup().nestedGroup()
					.condition(BlockStateProperties.EAST, true).condition(BlockStateProperties.UP, false)
					.condition(BlockStateProperties.DOWN, false).endNestedGroup().nestedGroup()
					.condition(BlockStateProperties.WEST, true).condition(BlockStateProperties.UP, false)
					.condition(BlockStateProperties.DOWN, false).endNestedGroup().end().end().part()
					.modelFile(base_model_vertical).addModel().useOr().condition(BlockStateProperties.UP, true)
					.condition(BlockStateProperties.DOWN, true).end().part().modelFile(model_cap).addModel()
					.condition(BlockStateProperties.UP, true).end().part().modelFile(model_cap).rotationX(180)
					.addModel().condition(BlockStateProperties.DOWN, true).end().part().modelFile(model_cap)
					.rotationX(90).addModel().condition(BlockStateProperties.NORTH, true).end().part()
					.modelFile(model_cap).rotationX(90).rotationY(180).addModel()
					.condition(BlockStateProperties.SOUTH, true).end().part().modelFile(model_cap).rotationX(90)
					.rotationY(-90).addModel().condition(BlockStateProperties.WEST, true).end().part()
					.modelFile(model_cap).rotationX(90).rotationY(90).addModel()
					.condition(BlockStateProperties.EAST, true).end();
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
			boolean uvlock = yRot != 0 || half == Half.TOP; // Don't set uvlock for states that have no rotation
			return ConfiguredModel.builder()
					.modelFile(shape == StairsShape.STRAIGHT ? stairs
							: shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT ? stairsInner
									: stairsOuter)
					.rotationX(half == Half.BOTTOM ? 0 : 180).rotationY(yRot).uvLock(false).build();
		}, StairBlock.WATERLOGGED);
	}

	@Override
	public void itemModel(_MaterialBase base, ItemModelProvider tmp) {
		if (this.autoGenItemModel) {
			if (SMALL_LOG.enabled()) {
				DataUtil.basicMaterialBlockItem(tmp, SMALL_LOG.BLOCK_ITEM, base.name, "small_log", base.getType());
			}
			if (SMALL_LOGS.enabled()) {
				DataUtil.basicMaterialBlockItem(tmp, SMALL_LOGS.BLOCK_ITEM, base.name, "small_logs", base.getType());
			}
			if (SMALL_LOGS_CORNER.enabled()) {
				DataUtil.basicMaterialBlockItem(tmp, SMALL_LOGS_CORNER.BLOCK_ITEM, base.name, "small_logs_corner",
						base.getType());
			}
			if (SMALL_LOGS_SLAB.enabled()) {
				DataUtil.basicMaterialBlockItem(tmp, SMALL_LOGS_SLAB.BLOCK_ITEM, base.name, "small_logs_slab_bottom",
						base.getType());
			}
			if (SMALL_LOGS_STAIRS.enabled()) {
				DataUtil.basicMaterialBlockItem(tmp, SMALL_LOGS_STAIRS.BLOCK_ITEM, base.name, "small_logs_stairs",
						base.getType());
			}

			if (STRIPPED_SMALL_LOG.enabled()) {
				DataUtil.basicMaterialBlockItem(tmp, STRIPPED_SMALL_LOG.BLOCK_ITEM, base.name, "stripped_small_log",
						base.getType());
			}
			if (STRIPPED_SMALL_LOGS.enabled()) {
				DataUtil.basicMaterialBlockItem(tmp, STRIPPED_SMALL_LOGS.BLOCK_ITEM, base.name, "stripped_small_logs",
						base.getType());
			}
			if (STRIPPED_SMALL_LOGS_CORNER.enabled()) {
				DataUtil.basicMaterialBlockItem(tmp, STRIPPED_SMALL_LOGS_CORNER.BLOCK_ITEM, base.name,
						"stripped_small_logs_corner", base.getType());
			}
			if (STRIPPED_SMALL_LOGS_SLAB.enabled()) {
				DataUtil.basicMaterialBlockItem(tmp, STRIPPED_SMALL_LOGS_SLAB.BLOCK_ITEM, base.name,
						"stripped_small_logs_slab_bottom", base.getType());
			}
			if (STRIPPED_SMALL_LOGS_STAIRS.enabled()) {
				DataUtil.basicMaterialBlockItem(tmp, STRIPPED_SMALL_LOGS_STAIRS.BLOCK_ITEM, base.name,
						"stripped_small_logs_stairs", base.getType());
			}
		}
	}

	@Override
	public void engLoc(_MaterialBase base, LanguageProvider lp) {
		StringBuilder material_name = new StringBuilder();
		for (String word : base.name.split("_")) {
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			material_name.append(word).append(" ");
		}
        if (SMALL_LOG.enabled()) {
            lp.add(this.SMALL_LOG.BLOCK_ITEM.asItem(), material_name + "Small Log");
        }
		if (SMALL_LOGS.enabled()) {
			lp.add(this.SMALL_LOGS.BLOCK_ITEM.asItem(), material_name + "Small Logs");
		}
		if (SMALL_LOGS_CORNER.enabled()) {
			lp.add(this.SMALL_LOGS_CORNER.BLOCK_ITEM.asItem(), material_name + "Small Logs Corner");
		}
		if (SMALL_LOGS_SLAB.enabled()) {
			lp.add(this.SMALL_LOGS_SLAB.BLOCK_ITEM.asItem(), material_name + "Small Logs Slab");
		}
		if (SMALL_LOGS_STAIRS.enabled()) {
			lp.add(this.SMALL_LOGS_STAIRS.BLOCK_ITEM.asItem(), material_name + "Small Logs Stairs");
		}

		if (STRIPPED_SMALL_LOGS.enabled()) {
			lp.add(this.STRIPPED_SMALL_LOGS.BLOCK_ITEM.asItem(), material_name + "Stripped Small Logs");
		}
		if (STRIPPED_SMALL_LOGS_CORNER.enabled()) {
			lp.add(this.STRIPPED_SMALL_LOGS_CORNER.BLOCK_ITEM.asItem(), material_name + "Stripped Small Logs Corner");
		}
		if (STRIPPED_SMALL_LOGS_SLAB.enabled()) {
			lp.add(this.STRIPPED_SMALL_LOGS_SLAB.BLOCK_ITEM.asItem(), material_name + "Stripped Small Logs Slab");
		}
		if (STRIPPED_SMALL_LOGS_STAIRS.enabled()) {
			lp.add(this.STRIPPED_SMALL_LOGS_STAIRS.BLOCK_ITEM.asItem(), material_name + "Stripped Small Logs Stairs");
		}
	}

	@Override
	public void recipes(_MaterialBase base, RecipeOutput consumer) {
		if (SMALL_LOG.enabled()) {
			SawBuckRecipeBuilder
					.saw(Ingredient.of(
							TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace(base.name + "_logs"))),
							new ItemStack(SMALL_LOG.BLOCK_ITEM.get(), 4), Vec3.ZERO)
					.tool(Ingredient.of(ItemTags.AXES), 4, true, RecipeLootTables.SAW_DUST, List.of(),
							Recipes.standardSawBuckAxeModel(TagUtil.modLoc("iron_axe"), 0))
					.save(consumer);
		}
		if(SMALL_LOGS.enabled()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, this.SMALL_LOGS_ITEM, 1)
					.pattern("bb").pattern("bb")
					.define('b', this.SMALL_LOG_ITEM)
					.unlockedBy("has_small_log",
							InventoryChangeTrigger.TriggerInstance.hasItems(this.SMALL_LOG_ITEM))
					.save(consumer);
		}
		if(SMALL_LOGS_SLAB.enabled()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, this.SMALL_LOGS_SLAB_ITEM, 1)
					.pattern("bb")
					.define('b', this.SMALL_LOG_ITEM)
					.unlockedBy("has_small_log",
							InventoryChangeTrigger.TriggerInstance.hasItems(this.SMALL_LOG_ITEM))
					.save(consumer);
		}
		if(SMALL_LOGS_STAIRS.enabled()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, this.SMALL_LOGS_STAIRS_ITEM, 1)
					.pattern("b ").pattern("bb")
					.define('b', this.SMALL_LOG_ITEM)
					.unlockedBy("has_small_log",
							InventoryChangeTrigger.TriggerInstance.hasItems(this.SMALL_LOG_ITEM))
					.save(consumer);
		}
		if(SMALL_LOGS_CORNER.enabled()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, this.SMALL_LOGS_CORNER_ITEM, 1)
					.pattern("b").pattern("b")
					.define('b', this.SMALL_LOGS_SLAB_ITEM)
					.unlockedBy("has_small_logs_slab",
							InventoryChangeTrigger.TriggerInstance.hasItems(this.SMALL_LOGS_SLAB_ITEM))
					.save(consumer);
		}
	}

	@Override
	public void blockLoot(_MaterialBase base, BlockLootSubProvider blp) {
		if (SMALL_LOG.enabled()) {
			blp.dropSelf(SMALL_LOG.BLOCK.get());
		}
		if (SMALL_LOGS.enabled()) {
			blp.dropSelf(this.SMALL_LOGS.BLOCK.get());
		}
		if (SMALL_LOGS_CORNER.enabled()) {
			blp.dropSelf(this.SMALL_LOGS_CORNER.BLOCK.get());
		}
		if (SMALL_LOGS_SLAB.enabled()) {
			blp.dropSelf(this.SMALL_LOGS_SLAB.BLOCK.get());
		}
		if (SMALL_LOGS_STAIRS.enabled()) {
			blp.dropSelf(this.SMALL_LOGS_STAIRS.BLOCK.get());
		}

		if (STRIPPED_SMALL_LOG.enabled()) {
			blp.dropSelf(STRIPPED_SMALL_LOG.BLOCK.get());
		}
		if (STRIPPED_SMALL_LOGS.enabled()) {
			blp.dropSelf(this.STRIPPED_SMALL_LOGS.BLOCK.get());
		}
		if (STRIPPED_SMALL_LOGS_CORNER.enabled()) {
			blp.dropSelf(this.STRIPPED_SMALL_LOGS_CORNER.BLOCK.get());
		}
		if (STRIPPED_SMALL_LOGS_SLAB.enabled()) {
			blp.dropSelf(this.STRIPPED_SMALL_LOGS_SLAB.BLOCK.get());
		}
		if (STRIPPED_SMALL_LOGS_STAIRS.enabled()) {
			blp.dropSelf(this.STRIPPED_SMALL_LOGS_STAIRS.BLOCK.get());
		}
	}

	@Override
	public void setupItemTags(_MaterialBase base, ItemTagsProvider itp) {

	}

	@Override
	public void setupBlockTags(_MaterialBase base, BlockTagsProvider itp) {

	}

	@Override
	public void setupClient(_MaterialBase base, FMLClientSetupEvent event) {

	}

	public static class Serializer extends MaterialExtensionSerializer<ExtensionExtraLogs> {

		public Serializer() {
			super("EXTRALOGS");
		}

		@Override
		public JsonElement serialize(ExtensionExtraLogs src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject j = new JsonObject();

			j.addProperty("type", type);

			j.addProperty("loadSmallLog", src.SMALL_LOG.enabled());
			j.addProperty("loadSmallLogs", src.SMALL_LOGS.enabled());
			j.addProperty("loadSmallCornerLogs", src.SMALL_LOGS_CORNER.enabled());
			j.addProperty("loadSmallLogsSlab", src.SMALL_LOGS_SLAB.enabled());
			j.addProperty("loadSmallLogsStairs", src.SMALL_LOGS_STAIRS.enabled());

			j.addProperty("loadStrippedSmallLog", src.STRIPPED_SMALL_LOG.enabled());
			j.addProperty("loadStrippedSmallLogs", src.STRIPPED_SMALL_LOGS.enabled());
			j.addProperty("loadStrippedSmallCornerLogs", src.STRIPPED_SMALL_LOGS_CORNER.enabled());
			j.addProperty("loadStrippedSmallLogsSlab", src.STRIPPED_SMALL_LOGS_SLAB.enabled());
			j.addProperty("loadStrippedSmallLogsStairs", src.STRIPPED_SMALL_LOGS_STAIRS.enabled());

			return j;
		}

		@Override
		public ExtensionExtraLogs deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			boolean loadSmallLog = j.get("loadSmallLog").getAsBoolean();
			boolean loadSmallLogs = j.get("loadSmallLogs").getAsBoolean();
			boolean loadSmallCornerLogs = j.get("loadSmallCornerLogs").getAsBoolean();
			boolean loadSmallLogsSlab = j.get("loadSmallLogsSlab").getAsBoolean();
			boolean loadSmallLogsStairs = j.get("loadSmallLogsStairs").getAsBoolean();

			boolean loadStrippedSmallLog = j.get("loadStrippedSmallLog").getAsBoolean();
			boolean loadStrippedSmallLogs = j.get("loadStrippedSmallLogs").getAsBoolean();
			boolean loadStrippedSmallCornerLogs = j.get("loadStrippedSmallCornerLogs").getAsBoolean();
			boolean loadStrippedSmallLogsSlab = j.get("loadStrippedSmallLogsSlab").getAsBoolean();
			boolean loadStrippedSmallLogsStairs = j.get("loadStrippedSmallLogsStairs").getAsBoolean();

			return new ExtensionExtraLogs(loadSmallLog, loadSmallLogs, loadSmallCornerLogs, loadSmallLogsSlab,
					loadSmallLogsStairs, loadStrippedSmallLog, loadStrippedSmallLogs, loadStrippedSmallCornerLogs,
					loadStrippedSmallLogsSlab, loadStrippedSmallLogsStairs);
		}

	}

}
