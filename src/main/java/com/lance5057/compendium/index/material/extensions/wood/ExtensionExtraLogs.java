package com.lance5057.compendium.index.material.extensions.wood;

import static com.lance5057.compendium.util.TagUtil.mcLoc;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.blocks.PipeStyleBlock;
import com.lance5057.compendium.blocks.RotatedPillarStyleBlock;
import com.lance5057.compendium.blocks.SlabStyleBlock;
import com.lance5057.compendium.blocks.StairStyleBlock;
import com.lance5057.compendium.client.BlacklistedModel;
import com.lance5057.compendium.client.models.style.StyleBlockModelBuilder;
import com.lance5057.compendium.client.models.style.model.StyleModelBuilder;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.data.IndexBlockModelProvider;
import com.lance5057.compendium.data.Recipes;
import com.lance5057.compendium.data.loottables.RecipeLootTables;
import com.lance5057.compendium.data.recipebuilders.SawBuckRecipeBuilder;
import com.lance5057.compendium.index.CompendiumIndex.Generate;
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

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
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
import net.neoforged.neoforge.client.model.generators.ConfiguredModel.Builder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ExtensionExtraLogs extends _MaterialExtension {
	public static final VoxelShape smallLogHori1 = Block.box(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 16.0D);
	public static final VoxelShape smallLogHori2 = Block.box(0.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D);
	public static final VoxelShape smallLogVert = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);

	public final CompendiumBlockHandler SMALL_LOG;
	public final CompendiumBlockHandler LOG;
	public final CompendiumBlockHandler LOG_SLAB;
	public final CompendiumBlockHandler LOG_STAIRS;

	public final CompendiumBlockHandler STRIPPED_SMALL_LOG;
	public final CompendiumBlockHandler STRIPPED_LOG;
	public final CompendiumBlockHandler STRIPPED_LOG_SLAB;
	public final CompendiumBlockHandler STRIPPED_LOG_STAIRS;

	public ExtensionExtraLogs(Generate smallLog, Generate smallLogs, Generate smallLogsSlab, Generate smallLogsStairs,
			Generate strippedSmallLog, Generate strippedSmallLogs, Generate strippedSmallLogsSlab,
			Generate strippedSmallLogsStairs) {

		SMALL_LOG = new CompendiumBlockHandler("small_log");
		LOG = new CompendiumBlockHandler("logs");
		LOG_SLAB = new CompendiumBlockHandler("logs_slab");
		LOG_STAIRS = new CompendiumBlockHandler("logs_stairs");

		STRIPPED_SMALL_LOG = new CompendiumBlockHandler("stripped_small_log");
		STRIPPED_LOG = new CompendiumBlockHandler("stripped_logs");
		STRIPPED_LOG_SLAB = new CompendiumBlockHandler("stripped_logs_slab");
		STRIPPED_LOG_STAIRS = new CompendiumBlockHandler("stripped_logs_stairs");

		SMALL_LOG.setGenerate(smallLog);
		LOG.setGenerate(smallLogs);
		LOG_SLAB.setGenerate(smallLogsSlab);
		LOG_STAIRS.setGenerate(smallLogsStairs);

		STRIPPED_SMALL_LOG.setGenerate(strippedSmallLog);
		STRIPPED_LOG.setGenerate(strippedSmallLogs);
		STRIPPED_LOG_SLAB.setGenerate(strippedSmallLogsSlab);
		STRIPPED_LOG_STAIRS.setGenerate(strippedSmallLogsStairs);
	}

	@Override
	public void setup(_MaterialBase base) {
		SMALL_LOG.setup(base,
				() -> new PipeStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS), StyleData.SMALL_LOG),
				() -> new BlockItem(SMALL_LOG.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				base.namespace, "log/small",
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_small_log"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_small_log"));
		LOG.setup(base,
				() -> new RotatedPillarStyleBlock(Block.Properties.ofFullCopy(Blocks.DARK_OAK_LOG), StyleData.LOG),
				() -> new BlockItem(LOG.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				base.namespace, "log", ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_styled_log"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_styled_log"));
		LOG_SLAB.setup(base,
				() -> new SlabStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_SLAB), StyleData.LOG_SLAB),
				() -> new BlockItem(LOG_SLAB.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				base.namespace, "log/slab",
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_log_slab"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_log_slab"));
		LOG_STAIRS.setup(base,
				() -> new StairStyleBlock(LOG.BLOCK.get().defaultBlockState(),
						Block.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS), StyleData.LOG_STAIRS),
				() -> new BlockItem(LOG_STAIRS.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				base.namespace, "log/stairs",
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_log_stairs"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_log_stairs"));

		CompendiumBlockEntities.validStyleBlocks.add(SMALL_LOG.BLOCK);
		CompendiumBlockEntities.validStyleBlocks.add(LOG.BLOCK);
		CompendiumBlockEntities.validStyleBlocks.add(LOG_SLAB.BLOCK);
		CompendiumBlockEntities.validStyleBlocks.add(LOG_STAIRS.BLOCK);

		Compendium.styleItemRenderers.add(LOG.BLOCK_ITEM);

		STRIPPED_SMALL_LOG.setup(base,
				() -> new PipeStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS), StyleData.SMALL_LOG),
				() -> new BlockItem(STRIPPED_SMALL_LOG.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				base.namespace, "stripped_log/small",
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_stripped_small_log"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_stripped_small_log"));
		STRIPPED_LOG.setup(base,
				() -> new RotatedPillarStyleBlock(Block.Properties.ofFullCopy(Blocks.DARK_OAK_LOG), StyleData.LOG),
				() -> new BlockItem(STRIPPED_LOG.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				base.namespace, "stripped_log",
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_stripped_log"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_stripped_log"));

		STRIPPED_LOG_SLAB.setup(base,
				() -> new SlabStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_SLAB), StyleData.LOG_SLAB),
				() -> new BlockItem(STRIPPED_LOG_SLAB.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				base.namespace, "stripped_log/slab",
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_stripped_log_slab"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_stripped_log_slab"));

		STRIPPED_LOG_STAIRS.setup(base,
				() -> new StairStyleBlock(LOG.BLOCK.get().defaultBlockState(),
						Block.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS), StyleData.LOG_STAIRS),
				() -> new BlockItem(STRIPPED_LOG_STAIRS.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				base.namespace, "stripped_log/stairs",
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_stripped_log_stairs"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_stripped_log_stairs"));

		CompendiumBlockEntities.validStyleBlocks.add(STRIPPED_SMALL_LOG.BLOCK);
		CompendiumBlockEntities.validStyleBlocks.add(STRIPPED_LOG.BLOCK);
		CompendiumBlockEntities.validStyleBlocks.add(STRIPPED_LOG_SLAB.BLOCK);
		CompendiumBlockEntities.validStyleBlocks.add(STRIPPED_LOG_STAIRS.BLOCK);
	}

	@Override
	public void tab(_MaterialBase base, Output output) {
		SMALL_LOG.tab(base, output);
		LOG.tab(base, output);
		LOG_SLAB.tab(base, output);
		LOG_STAIRS.tab(base, output);

		STRIPPED_SMALL_LOG.tab(base, output);
		STRIPPED_LOG.tab(base, output);
		STRIPPED_LOG_SLAB.tab(base, output);
		STRIPPED_LOG_STAIRS.tab(base, output);
	}

	@Override
	public void blockModel(_MaterialBase base, IndexBlockModelProvider ibmp) {
//		String logstem;
//		if (base.name.equals("warped") || base.name.equals("crimson")) {
//			logstem = "stem";
//		} else {
//			logstem = "log";
//		}

		ibmp.withExistingParent(SMALL_LOG.location(base) + "small_log_block", ibmp.modLoc("item/small_log"))
				.texture("0", ibmp.modLoc(SMALL_LOG.location(base) + "logs/" + "small_logs_corner"));

		ibmp.withExistingParent(base.itemFolder() + "small_log_inventory", ibmp.modLoc("item/small_log_inventory"))
				.texture("0", ibmp.modLoc(SMALL_LOG.location(base) + "logs/" + "small_logs_corner"));

		ibmp.withExistingParent(STRIPPED_SMALL_LOG.location(base) + "small_log_block", ibmp.modLoc("item/small_log"))
				.texture("0", ibmp.modLoc(STRIPPED_SMALL_LOG.location(base) + "logs/" + "small_logs_corner"));

		ibmp.withExistingParent(base.itemFolder() + "stripped_small_log_inventory",
				ibmp.modLoc("item/small_log_inventory"))
				.texture("0", ibmp.modLoc(STRIPPED_SMALL_LOG.location(base) + "logs/" + "stripped_small_logs_corner"));

		ibmp.withExistingParent(LOG.location(base) + "/log/basic", ibmp.mcLoc("block/cube_column"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs_top"));
		ibmp.withExistingParent(LOG.location(base) + "/log/basic_horizontal",
				ibmp.mcLoc("block/cube_column_horizontal"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs_top"));

		ibmp.withExistingParent(LOG.location(base) + "/log/corner", ibmp.modLoc("block/small_logs_corner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs_corner"));
		ibmp.withExistingParent(LOG.location(base) + "/log/corner_horizontal",
				ibmp.modLoc("block/small_logs_corner_horizontal"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs_corner"));

		ibmp.withExistingParent(LOG.location(base) + "/log/small_wood", ibmp.mcLoc("block/cube_column"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"));
		ibmp.withExistingParent(LOG.location(base) + "/log/small_wood_horizontal",
				ibmp.mcLoc("block/cube_column_horizontal"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"));

		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/basic", ibmp.mcLoc("block/cube_column"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs_top"));

		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/basic_horizontal",
				ibmp.mcLoc("block/cube_column_horizontal"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs_top"));

		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/corner",
				ibmp.modLoc("block/small_logs_corner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs_corner"));

		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/corner_horizontal",
				ibmp.modLoc("block/small_logs_corner_horizontal"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs_corner"));

		logSlabBlockModel(base, ibmp);

		logStairsBlockModel(base, ibmp);
	}

	private void logSlabBlockModel(_MaterialBase base, IndexBlockModelProvider ibmp) {
		styledModel(base, ibmp, "slab", "small_logs", "small_logs", "small_logs_top", true);
		styledModel(base, ibmp, "slab", "small_logs_rotated", "small_logs", "small_logs_top", true);
		styledModel(base, ibmp, "slab", "split", "log", "log_top", "log_split_side", true);
		styledModel(base, ibmp, "slab", "split_rotated", "log", "log_top", "log_split_side", true);
		styledModel(base, ibmp, "slab", "crosscut", "log", "log_top", true);
		styledModel(base, ibmp, "slab", "crosscut_small", "small_logs", "small_logs_top", true);
		styledModel(base, ibmp, "slab", "small_wood", "small_logs", "small_logs", true, "small_logs");
		styledModel(base, ibmp, "slab", "small_wood_rotated", "small_logs", "small_logs", true, "small_logs_rotated");
		styledModel(base, ibmp, "slab", "wood", "log", "log", true, "small_logs");
		styledModel(base, ibmp, "slab", "wood_rotated", "log", "log", true, "small_logs_rotated");
//		styledModel(base, ibmp, "slab", "campfire", "log", "extra_caps", true);
		styledModel(base, ibmp, "slab", "firewood", "log", "log_top", "log_split_side", true);
//		styledModel(base, ibmp, "slab", "smaller_logs", "log", "extra_caps", true);
//		styledModel(base, ibmp, "slab", "smaller_logs_rotated", "log", "extra_caps", true);
//		styledModel(base, ibmp, "slab", "smallest_logs", "log", "extra_caps", true);
//		styledModel(base, ibmp, "slab", "smallest_logs_rotated", "log", "extra_caps", true);
	}

	private void logStairsBlockModel(_MaterialBase base, IndexBlockModelProvider ibmp) {
		styledModel(base, ibmp, "stairs", "small_logs", "small_logs", "small_logs_top", true);
		styledModel(base, ibmp, "stairs", "small_logs_rotated_side", "small_logs", "small_logs_top", true);
		styledModel(base, ibmp, "stairs", "small_logs_rotated_front", "small_logs", "small_logs_top", true);
		styledModel(base, ibmp, "stairs", "small_logs_rotated_top", "small_logs", "small_logs_top", true);
		styledModel(base, ibmp, "stairs", "split_log_rotated_side", "log", "log_top", "log_split_side", true);
		styledModel(base, ibmp, "stairs", "split_log_rotated_front", "log", "log_top", "log_split_side", true);
		styledModel(base, ibmp, "stairs", "split_log_rotated_top", "log", "log_top", "log_split_side", true);
		styledModel(base, ibmp, "stairs", "small_wood", "small_logs", "small_logs", true, "small_logs_rotated_side");
		styledModel(base, ibmp, "stairs", "small_wood_rotated", "small_logs", "small_logs", true,
				"small_logs_rotated_front");
		styledModel(base, ibmp, "stairs", "wood", "log", "log", true, "small_logs_rotated_side");
		styledModel(base, ibmp, "stairs", "wood_rotated", "log", "log", true, "small_logs_rotated_front");
	}

	private void styledModel(_MaterialBase base, IndexBlockModelProvider ibmp, String block, String modelName,
			String texture0, String texture1, Boolean stripped) {
		styledModel(base, ibmp, block, modelName, texture0, texture1, stripped, modelName);
	}

	private void styledModel(_MaterialBase base, IndexBlockModelProvider ibmp, String block, String modelName,
			String texture0, String texture1, Boolean stripped, String modelSource) {
		String[] types = new String[0];
		if (block.equals("slab")) {
			types = new String[] { "_bottom", "_top", "_full" };
		} else if (block.equals("stairs")) {
			types = new String[] { "", "_inner", "_outer" };
		}

		for (String type : types) {
			ibmp.withExistingParent(LOG.location(base) + "/" + block + "/" + modelName + type,
					ibmp.modLoc("block/bases/" + block + "/" + modelSource + type))
					.texture("0", textureLocation(texture0, base, false))
					.texture("1", textureLocation(texture1, base, false))
					.texture("particle", textureLocation(texture0, base, false));

			if (stripped) {
				ibmp.withExistingParent(LOG.location(base) + "/stripped_" + block + "/" + modelName + type,
						ibmp.modLoc("block/bases/" + block + "/" + modelSource + type))
						.texture("0", textureLocation(texture0, base, true))
						.texture("1", textureLocation(texture1, base, true))
						.texture("particle", textureLocation(texture0, base, true));
			}
		}
	}

	private void styledModel(_MaterialBase base, IndexBlockModelProvider ibmp, String block, String modelName,
			String texture0, String texture1, String texture2, Boolean stripped) {
		String[] types = new String[0];
		if (block.equals("slab")) {
			types = new String[] { "_bottom", "_top", "_full" };
		} else if (block.equals("stairs")) {
			types = new String[] { "", "_inner", "_outer" };
		}

		for (String type : types) {
			ibmp.withExistingParent(LOG.location(base) + "/" + block + "/" + modelName + type,
					ibmp.modLoc("block/bases/" + block + "/" + modelName + type))
					.texture("0", textureLocation(texture0, base, false))
					.texture("1", textureLocation(texture1, base, false))
					.texture("2", textureLocation(texture2, base, false))
					.texture("particle", textureLocation(texture0, base, false));

			if (stripped) {
				ibmp.withExistingParent(LOG.location(base) + "/stripped_" + block + "/" + modelName + type,
						ibmp.modLoc("block/bases/" + block + "/" + modelName + type))
						.texture("0", textureLocation(texture0, base, true))
						.texture("1", textureLocation(texture1, base, true))
						.texture("2", textureLocation(texture2, base, true))
						.texture("particle", textureLocation(texture0, base, true));
			}
		}
	}

	private ResourceLocation textureLocation(String textureName, _MaterialBase base, Boolean stripped) {
		String logstem;
		String stripped_text;
		if (base.name.equals("warped") || base.name.equals("crimson")) {
			logstem = "stem";
		} else {
			logstem = "log";
		}
		if (stripped) {
			stripped_text = "stripped_";
		} else {
			stripped_text = "";
		}
		if (textureName.equals("log")) {
			return mcLoc("block/" + stripped_text + base.name + "_" + logstem);
		} else if (textureName.equals("log_top")) {
			return mcLoc("block/" + stripped_text + base.name + "_" + logstem + "_top");
		} else if (textureName.split("_", 1)[0].equals("planks")) {
			return mcLoc("block/" + stripped_text + base.name + "_planks");
		} else {
			return Compendium.modLoc(LOG.location(base) + "logs/" + stripped_text + textureName);
		}
	}

	@Override
	public void blockStateModel(_MaterialBase base, BlockStateProvider bsp) {
		if (this.autoGenBlockModel) {
			smallLogsModel(SMALL_LOG, base, bsp, "");
			smallLogsModel(STRIPPED_SMALL_LOG, base, bsp, "stripped_");

			if (LOG.shouldGenerate()) {
				bsp.getVariantBuilder(LOG.BLOCK.get()).forAllStates(state -> {
					Direction.Axis axis = state.getValue(RotatedPillarBlock.AXIS);

					if (axis == Direction.Axis.X || axis == Direction.Axis.Z) {
						Builder<?> b = ConfiguredModel.builder();
						StyleBlockModelBuilder<BlockModelBuilder> msmb = bsp.models()
								.getBuilder(LOG.location(base) + "log_horizontal")
								.customLoader(StyleBlockModelBuilder::begin);
						msmb.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

						for (String s : StyleData.LOG.getTypes())
							msmb.add(new StyleModelBuilder(s,
									bsp.modLoc(LOG.location(base) + "log/" + s.toLowerCase() + "_horizontal")));

						BlockModelBuilder bmb = msmb.end();
						b.modelFile(bmb);
						if (axis == Direction.Axis.X)
							b.rotationY(90);
						return b.rotationX(90).build();
					}

					Builder<?> b = ConfiguredModel.builder();
					StyleBlockModelBuilder<BlockModelBuilder> msmb = bsp.models().getBuilder(LOG.location(base) + "log")
							.customLoader(StyleBlockModelBuilder::begin);
					msmb.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

					for (String s : StyleData.LOG.getTypes())
						msmb.add(new StyleModelBuilder(s, bsp.modLoc(LOG.location(base) + "log/" + s.toLowerCase())));

					BlockModelBuilder bmb = msmb.end();
					b.modelFile(bmb);
					return b.build();

				});
			}
			if (STRIPPED_LOG.shouldGenerate()) {
				bsp.getVariantBuilder(STRIPPED_LOG.BLOCK.get()).forAllStates(state -> {
					Direction.Axis axis = state.getValue(RotatedPillarBlock.AXIS);

					if (axis == Direction.Axis.X || axis == Direction.Axis.Z) {
						Builder<?> b = ConfiguredModel.builder();
						StyleBlockModelBuilder<BlockModelBuilder> msmb = bsp.models()
								.getBuilder(LOG.location(base) + "stripped_log_horizontal")
								.customLoader(StyleBlockModelBuilder::begin);
						msmb.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

						for (String s : StyleData.LOG.getTypes())
							msmb.add(new StyleModelBuilder(s, bsp
									.modLoc(LOG.location(base) + "stripped_log/" + s.toLowerCase() + "_horizontal")));

						BlockModelBuilder bmb = msmb.end();
						b.modelFile(bmb);
						if (axis == Direction.Axis.X)
							b.rotationY(90);
						return b.build();
					}

					Builder<?> b = ConfiguredModel.builder();
					StyleBlockModelBuilder<BlockModelBuilder> msmb = bsp.models()
							.getBuilder(LOG.location(base) + "stripped_log")
							.customLoader(StyleBlockModelBuilder::begin);
					msmb.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

					for (String s : StyleData.LOG.getTypes())
						msmb.add(new StyleModelBuilder(s,
								bsp.modLoc(LOG.location(base) + "stripped_log/" + s.toLowerCase())));

					BlockModelBuilder bmb = msmb.end();
					b.modelFile(bmb);
					return b.build();

				});
			}
			if (LOG_SLAB.shouldGenerate()) {
				StyleBlockModelBuilder<BlockModelBuilder> log_slab_bottom = bsp.models()
						.getBuilder(LOG.location(base) + "log_slab_bottom").customLoader(StyleBlockModelBuilder::begin);
				log_slab_bottom.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_bottom.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "slab/" + s.toLowerCase() + "_bottom")));

				StyleBlockModelBuilder<BlockModelBuilder> log_slab_top = bsp.models()
						.getBuilder(LOG.location(base) + "log_slab_top").customLoader(StyleBlockModelBuilder::begin);
				log_slab_top.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_top.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "slab/" + s.toLowerCase() + "_top")));

				StyleBlockModelBuilder<BlockModelBuilder> log_slab_full = bsp.models()
						.getBuilder(LOG.location(base) + "log_slab_full").customLoader(StyleBlockModelBuilder::begin);
				log_slab_full.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_full.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "slab/" + s.toLowerCase() + "_full")));

				bsp.slabBlock((SlabBlock) LOG_SLAB.BLOCK.get(), log_slab_bottom.end(), log_slab_top.end(),
						log_slab_full.end());
			}

			if (STRIPPED_LOG_SLAB.shouldGenerate()) {
				StyleBlockModelBuilder<BlockModelBuilder> log_slab_bottom = bsp.models()
						.getBuilder(LOG.location(base) + "stripped_log_slab_bottom")
						.customLoader(StyleBlockModelBuilder::begin);
				log_slab_bottom.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_bottom.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "stripped_slab/" + s.toLowerCase() + "_bottom")));

				StyleBlockModelBuilder<BlockModelBuilder> log_slab_top = bsp.models()
						.getBuilder(LOG.location(base) + "stripped_log_slab_top")
						.customLoader(StyleBlockModelBuilder::begin);
				log_slab_top.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_top.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "stripped_slab/" + s.toLowerCase() + "_top")));

				StyleBlockModelBuilder<BlockModelBuilder> log_slab_full = bsp.models()
						.getBuilder(LOG.location(base) + "stripped_log_slab_full")
						.customLoader(StyleBlockModelBuilder::begin);
				log_slab_full.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_full.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "stripped_slab/" + s.toLowerCase() + "_full")));

				bsp.slabBlock((SlabBlock) STRIPPED_LOG_SLAB.BLOCK.get(), log_slab_bottom.end(), log_slab_top.end(),
						log_slab_full.end());
			}
			if (LOG_STAIRS.shouldGenerate()) {
				StyleBlockModelBuilder<BlockModelBuilder> log_stairs_standard = bsp.models()
						.getBuilder(LOG.location(base) + "log_stairs").customLoader(StyleBlockModelBuilder::begin);
				log_stairs_standard.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_STAIRS.getTypes())
					log_stairs_standard.add(
							new StyleModelBuilder(s, bsp.modLoc(LOG.location(base) + "stairs/" + s.toLowerCase())));

				StyleBlockModelBuilder<BlockModelBuilder> log_stairs_inner = bsp.models()
						.getBuilder(LOG.location(base) + "log_stairs_inner")
						.customLoader(StyleBlockModelBuilder::begin);
				log_stairs_inner.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_STAIRS.getTypes())
					log_stairs_inner.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "stairs/" + s.toLowerCase() + "_inner")));

				StyleBlockModelBuilder<BlockModelBuilder> log_stairs_outer = bsp.models()
						.getBuilder(LOG.location(base) + "log_stairs_outer")
						.customLoader(StyleBlockModelBuilder::begin);
				log_stairs_outer.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_STAIRS.getTypes())
					log_stairs_outer.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "stairs/" + s.toLowerCase() + "_outer")));

				stairsBlock((StairBlock) LOG_STAIRS.BLOCK.get(), log_stairs_standard.end(), log_stairs_inner.end(),
						log_stairs_outer.end(), bsp);
			}

			if (STRIPPED_LOG_STAIRS.shouldGenerate()) {
				StyleBlockModelBuilder<BlockModelBuilder> log_stairs_standard = bsp.models()
						.getBuilder(LOG.location(base) + "stripped_log_stairs")
						.customLoader(StyleBlockModelBuilder::begin);
				log_stairs_standard.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_STAIRS.getTypes())
					log_stairs_standard.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "stripped_stairs/" + s.toLowerCase())));

				StyleBlockModelBuilder<BlockModelBuilder> log_stairs_inner = bsp.models()
						.getBuilder(LOG.location(base) + "stripped_log_stairs_inner")
						.customLoader(StyleBlockModelBuilder::begin);
				log_stairs_inner.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_STAIRS.getTypes())
					log_stairs_inner.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "stripped_stairs/" + s.toLowerCase() + "_inner")));

				StyleBlockModelBuilder<BlockModelBuilder> log_stairs_outer = bsp.models()
						.getBuilder(LOG.location(base) + "stripped_log_stairs_outer")
						.customLoader(StyleBlockModelBuilder::begin);
				log_stairs_outer.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_STAIRS.getTypes())
					log_stairs_outer.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "stripped_stairs/" + s.toLowerCase() + "_outer")));

				stairsBlock((StairBlock) STRIPPED_LOG_STAIRS.BLOCK.get(), log_stairs_standard.end(),
						log_stairs_inner.end(), log_stairs_outer.end(), bsp);
			}
		}
	}

	private void smallLogsModel(CompendiumBlockHandler block, _MaterialBase base, BlockStateProvider bsp,
			String extra) {
		if (block.shouldGenerate()) {
			BlockModelBuilder base_model_horizontal = bsp.models()
					.withExistingParent(block.location(base) + extra + "horizontal",
							bsp.modLoc("block/small_log_horizontal"))
					.texture("0", bsp.modLoc(base.blockFolder() + "logs/" + extra + "small_logs_corner"));
			BlockModelBuilder base_model_horizontal2 = bsp.models()
					.withExistingParent(block.location(base) + extra + "horizontal_rot",
							bsp.modLoc("block/small_log_horizontal2"))
					.texture("0", bsp.modLoc(base.blockFolder() + "logs/" + extra + "small_logs_corner"));
			BlockModelBuilder base_model_vertical = bsp.models()
					.withExistingParent(base.blockFolder() + extra + "vertical", bsp.modLoc("block/small_log_vertical"))
					.texture("0", bsp.modLoc(base.blockFolder() + "logs/" + extra + "small_logs_corner"));
			BlockModelBuilder model_cap = bsp.models()
					.withExistingParent(block.location(base) + extra + "cap", bsp.modLoc("block/small_log_cap"))
					.texture("0", bsp.modLoc(base.blockFolder() + "logs/" + extra + "small_logs_corner"));

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
			if (SMALL_LOG.shouldGenerate()) {
				DataUtil.basicMaterialInventoryBlockItem(tmp, SMALL_LOG.BLOCK_ITEM, base.name, "small_log",
						base.getType());
			}
			if (LOG.shouldGenerate()) {
				tmp.withExistingParent(LOG.BLOCK_ITEM.getRegisteredName(), tmp.modLoc("item/chair"));
			}
			if (LOG_SLAB.shouldGenerate()) {
				tmp.withExistingParent(LOG.BLOCK_ITEM.getRegisteredName(), tmp.modLoc("item/chair"));
			}
			if (LOG_STAIRS.shouldGenerate()) {
				tmp.withExistingParent(LOG.BLOCK_ITEM.getRegisteredName(), tmp.modLoc("item/chair"));
			}

			if (STRIPPED_SMALL_LOG.shouldGenerate()) {
				DataUtil.basicMaterialInventoryBlockItem(tmp, STRIPPED_SMALL_LOG.BLOCK_ITEM, base.name,
						"stripped_small_log", base.getType());
			}
			if (STRIPPED_LOG.shouldGenerate()) {
				tmp.withExistingParent(LOG.BLOCK_ITEM.getRegisteredName(), tmp.modLoc("item/chair"));
			}
			if (STRIPPED_LOG_SLAB.shouldGenerate()) {
				tmp.withExistingParent(LOG.BLOCK_ITEM.getRegisteredName(), tmp.modLoc("item/chair"));
			}
			if (STRIPPED_LOG_STAIRS.shouldGenerate()) {
				tmp.withExistingParent(LOG.BLOCK_ITEM.getRegisteredName(), tmp.modLoc("item/chair"));
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
		if (SMALL_LOG.shouldGenerate()) {
			lp.add(this.SMALL_LOG.BLOCK_ITEM.asItem(), material_name + "Small Log");
		}
		if (LOG.shouldGenerate()) {
			lp.add(this.LOG.BLOCK_ITEM.asItem(), material_name + "Styled Log");
		}
		if (LOG_SLAB.shouldGenerate()) {
			lp.add(this.LOG_SLAB.BLOCK_ITEM.asItem(), material_name + "Styled Log Slab");
		}
		if (LOG_STAIRS.shouldGenerate()) {
			lp.add(this.LOG_STAIRS.BLOCK_ITEM.asItem(), material_name + "Styled Log Stairs");
		}

		if (STRIPPED_SMALL_LOG.shouldGenerate()) {
			lp.add(this.STRIPPED_SMALL_LOG.BLOCK_ITEM.asItem(), "Stripped " + material_name + "Small Log");
		}
		if (STRIPPED_LOG.shouldGenerate()) {
			lp.add(this.STRIPPED_LOG.BLOCK_ITEM.asItem(), "Stripped " + material_name + "Styled Logs");
		}
		if (STRIPPED_LOG_SLAB.shouldGenerate()) {
			lp.add(this.STRIPPED_LOG_SLAB.BLOCK_ITEM.asItem(), "Stripped " + material_name + "Styled Log Slab");
		}
		if (STRIPPED_LOG_STAIRS.shouldGenerate()) {
			lp.add(this.STRIPPED_LOG_STAIRS.BLOCK_ITEM.asItem(), "Stripped " + material_name + "Styled Log Stairs");
		}
	}

	@Override
	public void recipes(_MaterialBase base, RecipeOutput consumer) {
		if (SMALL_LOG.shouldGenerate()) {
			SawBuckRecipeBuilder
					.saw(Ingredient.of(
							TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace(base.name + "_logs"))),
							new ItemStack(SMALL_LOG.BLOCK_ITEM.get(), 4), Vec3.ZERO)
					.tool(Ingredient.of(ItemTags.AXES), 1, true, RecipeLootTables.SAW_DUST, List.of(),
							new BlacklistedModel(mcLoc("iron_axe"), false,
									new AnimationFloatTransform()
											.setRotation(new AnimatedFloatVector3()
													.setY(new AnimatedFloat(180.000F, 270.000F, 0.000F, 0.000F, false,
															false))
													.setZ(new AnimatedFloat(0.000F, 64.000F, 0.000F, 1.500F, true,
															true)))
											.setLocation(new AnimatedFloatVector3()
													.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false,
															false))
													.setY(new AnimatedFloat(-9.000F, -5.000F, 0.000F, 0.000F, false,
															false))
													.setZ(new AnimatedFloat(-8.000F, 33.000F, 0.000F, 0.000F, false,
															false)))
											.setScale(new AnimatedFloatVector3()
													.setX(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false,
															false))
													.setY(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false,
															false))
													.setZ(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false,
															false)))
											.setPivot(new AnimatedFloatVector3()
													.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false,
															false))
													.setY(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false,
															false)))),
							new BlacklistedModel(TagUtil.modLoc("extra/split_log_stage0"), true,
									new AnimationFloatTransform()
											.setLocation(new AnimatedFloatVector3()
													.setX(new AnimatedFloat(8.000F, -8.000F, 0.000F, 0.000F, false,
															false))
													.setY(new AnimatedFloat(-18.000F, -11.600F, 0.000F, 0.000F, false,
															false))
													.setZ(new AnimatedFloat(0.000F, -8.000F, 0.000F, 0.000F, false,
															false)))
											.setScale(new AnimatedFloatVector3()
													.setX(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false,
															false))
													.setY(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false,
															false))
													.setZ(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false,
															false)))))
					.tool(Ingredient.of(ItemTags.AXES), 1, true, RecipeLootTables.SAW_DUST, List.of(),
							new BlacklistedModel(mcLoc("iron_axe"), false,
									new AnimationFloatTransform()
											.setRotation(new AnimatedFloatVector3()
													.setY(new AnimatedFloat(180.000F, 270.000F, 0.000F, 0.000F, false,
															false))
													.setZ(new AnimatedFloat(0.000F, 64.000F, 0.000F, 1.500F, true,
															true)))
											.setLocation(new AnimatedFloatVector3()
													.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false,
															false))
													.setY(new AnimatedFloat(-9.000F, -5.000F, 0.000F, 0.000F, false,
															false))
													.setZ(new AnimatedFloat(-8.000F, 33.000F, 0.000F, 0.000F, false,
															false)))
											.setScale(new AnimatedFloatVector3()
													.setX(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false,
															false))
													.setY(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false,
															false))
													.setZ(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false,
															false)))
											.setPivot(new AnimatedFloatVector3()
													.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false,
															false))
													.setY(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false,
															false)))),
							new BlacklistedModel(TagUtil.modLoc("extra/split_log_stage1"), true,
									new AnimationFloatTransform()
											.setLocation(new AnimatedFloatVector3()
													.setX(new AnimatedFloat(8.000F, -8.000F, 0.000F, 0.000F, false,
															false))
													.setY(new AnimatedFloat(-18.000F, -11.600F, 0.000F, 0.000F, false,
															false))
													.setZ(new AnimatedFloat(0.000F, -8.000F, 0.000F, 0.000F, false,
															false)))
											.setScale(new AnimatedFloatVector3()
													.setX(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false,
															false))
													.setY(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false,
															false))
													.setZ(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false,
															false)))))
					.tool(Ingredient.of(ItemTags.AXES), 1, true, RecipeLootTables.SAW_DUST, List.of(),
							new BlacklistedModel(mcLoc("iron_axe"), false,
									new AnimationFloatTransform()
											.setRotation(new AnimatedFloatVector3()
													.setY(new AnimatedFloat(180.000F, 270.000F, 0.000F, 0.000F, false,
															false))
													.setZ(new AnimatedFloat(0.000F, 64.000F, 0.000F, 1.500F, true,
															true)))
											.setLocation(new AnimatedFloatVector3()
													.setX(new AnimatedFloat(0.000F, 7.000F, 0.000F, 0.000F, false,
															false))
													.setY(new AnimatedFloat(-9.000F, -5.000F, 0.000F, 0.000F, false,
															false))
													.setZ(new AnimatedFloat(-8.000F, 33.000F, 0.000F, 0.000F, false,
															false)))
											.setScale(new AnimatedFloatVector3()
													.setX(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false,
															false))
													.setY(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false,
															false))
													.setZ(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false,
															false)))
											.setPivot(new AnimatedFloatVector3()
													.setX(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false,
															false))
													.setY(new AnimatedFloat(0.000F, 8.000F, 0.000F, 0.000F, false,
															false)))),
							new BlacklistedModel(TagUtil.modLoc("extra/split_log_stage2"), true,
									new AnimationFloatTransform()
											.setLocation(new AnimatedFloatVector3()
													.setX(new AnimatedFloat(8.000F, -8.000F, 0.000F, 0.000F, false,
															false))
													.setY(new AnimatedFloat(-18.000F, -11.600F, 0.000F, 0.000F, false,
															false))
													.setZ(new AnimatedFloat(0.000F, -8.000F, 0.000F, 0.000F, false,
															false)))
											.setScale(new AnimatedFloatVector3()
													.setX(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false,
															false))
													.setY(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false,
															false))
													.setZ(new AnimatedFloat(0.500F, 1.000F, 0.000F, 0.000F, false,
															false)))))
					.save(consumer);
		}
		if (LOG.shouldGenerate()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LOG.BLOCK_ITEM, 1).pattern("bb").pattern("bb")
					.define('b', SMALL_LOG.BLOCK_ITEM)
					.unlockedBy("has_small_log", InventoryChangeTrigger.TriggerInstance.hasItems(SMALL_LOG.BLOCK_ITEM))
					.save(consumer);
		}
		if (LOG_SLAB.shouldGenerate()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LOG_SLAB.BLOCK_ITEM, 1).pattern("bb")
					.define('b', SMALL_LOG.BLOCK_ITEM)
					.unlockedBy("has_small_log", InventoryChangeTrigger.TriggerInstance.hasItems(SMALL_LOG.BLOCK_ITEM))
					.save(consumer);
		}
		if (LOG_STAIRS.shouldGenerate()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LOG_STAIRS.BLOCK_ITEM, 1).pattern("b ")
					.pattern("bb").define('b', SMALL_LOG.BLOCK_ITEM)
					.unlockedBy("has_small_log", InventoryChangeTrigger.TriggerInstance.hasItems(SMALL_LOG.BLOCK_ITEM))
					.save(consumer);
		}

		if (STRIPPED_SMALL_LOG.shouldGenerate()) {
			SawBuckRecipeBuilder
					.saw(Ingredient.of(
							TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace(base.name + "_logs"))),
							new ItemStack(STRIPPED_SMALL_LOG.BLOCK_ITEM.get(), 4), Vec3.ZERO)
					.tool(Ingredient.of(ItemTags.AXES), 4, true, RecipeLootTables.SAW_DUST, List.of(),
							Recipes.standardSawBuckAxeModel(TagUtil.modLoc("iron_axe"), 0))
					.save(consumer);
		}
		if (STRIPPED_LOG.shouldGenerate()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, STRIPPED_LOG.BLOCK_ITEM, 1).pattern("bb")
					.pattern("bb").define('b', STRIPPED_SMALL_LOG.BLOCK_ITEM)
					.unlockedBy("has_stripped_small_log",
							InventoryChangeTrigger.TriggerInstance.hasItems(STRIPPED_SMALL_LOG.BLOCK_ITEM))
					.save(consumer);
		}
		if (STRIPPED_LOG_SLAB.shouldGenerate()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, STRIPPED_LOG_SLAB.BLOCK_ITEM, 1).pattern("bb")
					.define('b', STRIPPED_SMALL_LOG.BLOCK_ITEM)
					.unlockedBy("has_striepped_small_log",
							InventoryChangeTrigger.TriggerInstance.hasItems(STRIPPED_SMALL_LOG.BLOCK_ITEM))
					.save(consumer);
		}
		if (STRIPPED_LOG_STAIRS.shouldGenerate()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, STRIPPED_LOG_STAIRS.BLOCK_ITEM, 1).pattern("b ")
					.pattern("bb").define('b', STRIPPED_SMALL_LOG.BLOCK_ITEM)
					.unlockedBy("has_stripped_small_log",
							InventoryChangeTrigger.TriggerInstance.hasItems(STRIPPED_SMALL_LOG.BLOCK_ITEM))
					.save(consumer);
		}
	}

	@Override
	public void blockLoot(_MaterialBase base, BlockLootSubProvider blp) {
		if (SMALL_LOG.shouldGenerate()) {
			blp.dropSelf(SMALL_LOG.BLOCK.get());
		}
		if (LOG.shouldGenerate()) {
			blp.dropSelf(this.LOG.BLOCK.get());
		}
		if (LOG_SLAB.shouldGenerate()) {
			blp.dropSelf(this.LOG_SLAB.BLOCK.get());
		}
		if (LOG_STAIRS.shouldGenerate()) {
			blp.dropSelf(this.LOG_STAIRS.BLOCK.get());
		}

		if (STRIPPED_SMALL_LOG.shouldGenerate()) {
			blp.dropSelf(STRIPPED_SMALL_LOG.BLOCK.get());
		}
		if (STRIPPED_LOG.shouldGenerate()) {
			blp.dropSelf(this.STRIPPED_LOG.BLOCK.get());
		}
		if (STRIPPED_LOG_SLAB.shouldGenerate()) {
			blp.dropSelf(this.STRIPPED_LOG_SLAB.BLOCK.get());
		}
		if (STRIPPED_LOG_STAIRS.shouldGenerate()) {
			blp.dropSelf(this.STRIPPED_LOG_STAIRS.BLOCK.get());
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

			j.addProperty("loadSmallLog", src.SMALL_LOG.getGeneration().toString());
			j.addProperty("loadSmallLogs", src.LOG.getGeneration().toString());
			j.addProperty("loadSmallLogsSlab", src.LOG_SLAB.getGeneration().toString());
			j.addProperty("loadSmallLogsStairs", src.LOG_STAIRS.getGeneration().toString());

			j.addProperty("loadStrippedSmallLog", src.STRIPPED_SMALL_LOG.getGeneration().toString());
			j.addProperty("loadStrippedSmallLogs", src.STRIPPED_LOG.getGeneration().toString());
			j.addProperty("loadStrippedSmallLogsSlab", src.STRIPPED_LOG_SLAB.getGeneration().toString());
			j.addProperty("loadStrippedSmallLogsStairs", src.STRIPPED_LOG_STAIRS.getGeneration().toString());

			return j;
		}

		@Override
		public ExtensionExtraLogs deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			String loadSmallLog = j.get("loadSmallLog").getAsString();
			String loadSmallLogs = j.get("loadSmallLogs").getAsString();
			String loadSmallLogsSlab = j.get("loadSmallLogsSlab").getAsString();
			String loadSmallLogsStairs = j.get("loadSmallLogsStairs").getAsString();

			String loadStrippedSmallLog = j.get("loadStrippedSmallLog").getAsString();
			String loadStrippedSmallLogs = j.get("loadStrippedSmallLogs").getAsString();
			String loadStrippedSmallLogsSlab = j.get("loadStrippedSmallLogsSlab").getAsString();
			String loadStrippedSmallLogsStairs = j.get("loadStrippedSmallLogsStairs").getAsString();

			return new ExtensionExtraLogs(Generate.valueOf(loadSmallLog), Generate.valueOf(loadSmallLogs),
					Generate.valueOf(loadSmallLogsSlab), Generate.valueOf(loadSmallLogsStairs),
					Generate.valueOf(loadStrippedSmallLog), Generate.valueOf(loadStrippedSmallLogs),
					Generate.valueOf(loadStrippedSmallLogsSlab), Generate.valueOf(loadStrippedSmallLogsStairs));
		}

	}

	@Override
	public void otherLoot(_MaterialBase base, LootTableSubProvider lsp) {
		// TODO Auto-generated method stub

	}

}
