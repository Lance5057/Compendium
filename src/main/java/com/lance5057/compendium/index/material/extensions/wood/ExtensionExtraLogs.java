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

	public final CompendiumBlockHandler STRIPPED_SMALL_LOG_PIPE;
	public final CompendiumBlockHandler STRIPPED_LOG;
	public final CompendiumBlockHandler STRIPPED_LOG_SLAB;
	public final CompendiumBlockHandler STRIPPED_LOG_STAIRS;

	public ExtensionExtraLogs(boolean smallLog, boolean smallLogs, boolean smallLogsSlab, boolean smallLogsStairs,
			boolean strippedSmallLog, boolean strippedSmallLogs, boolean strippedSmallLogsSlab,
			boolean strippedSmallLogsStairs) {

		SMALL_LOG = new CompendiumBlockHandler("small_log");
		LOG = new CompendiumBlockHandler("logs");
		LOG_SLAB = new CompendiumBlockHandler("logs_slab");
		LOG_STAIRS = new CompendiumBlockHandler("logs_stairs");

		STRIPPED_SMALL_LOG_PIPE = new CompendiumBlockHandler("stripped_small_log");
		STRIPPED_LOG = new CompendiumBlockHandler("stripped_logs");
		STRIPPED_LOG_SLAB = new CompendiumBlockHandler("stripped_logs_slab");
		STRIPPED_LOG_STAIRS = new CompendiumBlockHandler("stripped_logs_stairs");

		SMALL_LOG.setEnabled(smallLog);
		LOG.setEnabled(smallLogs);
		LOG_SLAB.setEnabled(smallLogsSlab);
		LOG_STAIRS.setEnabled(smallLogsStairs);

		STRIPPED_SMALL_LOG_PIPE.setEnabled(strippedSmallLog);
		STRIPPED_LOG.setEnabled(strippedSmallLogs);
		STRIPPED_LOG_SLAB.setEnabled(strippedSmallLogsSlab);
		STRIPPED_LOG_STAIRS.setEnabled(strippedSmallLogsStairs);
	}

	@Override
	public void setup(_MaterialBase base) {
		SMALL_LOG.setup(base,
				() -> new PipeStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS), StyleData.SMALL_LOG),
				() -> new BlockItem(SMALL_LOG.BLOCK.get(), new Item.Properties().component(CompendiumComponents.STYLE,
						new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))));
		LOG.setup(base,
				() -> new RotatedPillarStyleBlock(Block.Properties.ofFullCopy(Blocks.DARK_OAK_LOG), StyleData.LOG),
				() -> new BlockItem(LOG.BLOCK.get(), new Item.Properties().component(CompendiumComponents.STYLE,
						new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))));
		LOG_SLAB.setup(base,
				() -> new SlabStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_SLAB), StyleData.LOG_SLAB),
				() -> new BlockItem(LOG_SLAB.BLOCK.get(), new Item.Properties().component(CompendiumComponents.STYLE,
						new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))));

		LOG_STAIRS.setup(base,
				() -> new StairStyleBlock(LOG.BLOCK.get().defaultBlockState(),
						Block.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS), StyleData.LOG_STAIRS),
				() -> new BlockItem(LOG_STAIRS.BLOCK.get(), new Item.Properties().component(CompendiumComponents.STYLE,
						new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))));

		CompendiumBlockEntities.validStyleBlocks.add(SMALL_LOG.BLOCK);
		CompendiumBlockEntities.validStyleBlocks.add(LOG.BLOCK);
		CompendiumBlockEntities.validStyleBlocks.add(LOG_SLAB.BLOCK);
		CompendiumBlockEntities.validStyleBlocks.add(LOG_STAIRS.BLOCK);

		Compendium.styleItemRenderers.add(LOG.BLOCK_ITEM);

		STRIPPED_SMALL_LOG_PIPE.setup(base,
				() -> new PipeStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS), StyleData.SMALL_LOG),
				() -> new BlockItem(STRIPPED_SMALL_LOG_PIPE.BLOCK.get(), new Item.Properties().component(CompendiumComponents.STYLE,
						new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))));
		STRIPPED_LOG.setup(base,
				() -> new RotatedPillarStyleBlock(Block.Properties.ofFullCopy(Blocks.DARK_OAK_LOG), StyleData.LOG),
				() -> new BlockItem(STRIPPED_LOG.BLOCK.get(), new Item.Properties().component(CompendiumComponents.STYLE,
						new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))));

		STRIPPED_LOG_SLAB.setup(base,
				() -> new SlabStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_SLAB), StyleData.LOG_SLAB),
				() -> new BlockItem(STRIPPED_LOG_SLAB.BLOCK.get(), new Item.Properties().component(CompendiumComponents.STYLE,
						new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))));

		STRIPPED_LOG_STAIRS.setup(base,
				() -> new StairStyleBlock(LOG.BLOCK.get().defaultBlockState(),
						Block.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS), StyleData.LOG_STAIRS),
				() -> new BlockItem(STRIPPED_LOG_STAIRS.BLOCK.get(), new Item.Properties().component(CompendiumComponents.STYLE,
						new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))));

		CompendiumBlockEntities.validStyleBlocks.add(STRIPPED_SMALL_LOG_PIPE.BLOCK);
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

		STRIPPED_SMALL_LOG_PIPE.tab(base, output);
		STRIPPED_LOG.tab(base, output);
		STRIPPED_LOG_SLAB.tab(base, output);
		STRIPPED_LOG_STAIRS.tab(base, output);
	}

	@Override
	public void blockModel(_MaterialBase base, IndexBlockModelProvider ibmp) {
		String logstem;
		if (base.name.equals("warped") || base.name.equals("crimson")) {
			logstem = "stem";
		} else {
			logstem = "log";
		}

		ibmp.withExistingParent(SMALL_LOG.location(base) + "_block", ibmp.modLoc("item/small_log")).texture("0",
				ibmp.modLoc(SMALL_LOG.location(base) + "small_logs_corner"));

		ibmp.withExistingParent(STRIPPED_SMALL_LOG_PIPE.location(base) + "_block", ibmp.modLoc("item/small_log"))
				.texture("0", ibmp.modLoc(STRIPPED_SMALL_LOG_PIPE.location(base) + "small_logs_corner"));

		ibmp.withExistingParent(LOG.location(base) + "/log/basic", ibmp.mcLoc("block/cube_column"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "small_logs_top"));

		ibmp.withExistingParent(LOG.location(base) + "/log/basic_horizontal",
				ibmp.mcLoc("block/cube_column_horizontal"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "small_logs_top"));

		ibmp.withExistingParent(LOG.location(base) + "/log/corner", ibmp.modLoc("block/small_logs_corner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "small_logs_corner"));

		ibmp.withExistingParent(LOG.location(base) + "/log/corner_horizontal",
				ibmp.modLoc("block/small_logs_corner_horizontal"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "small_logs_corner"));

		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/basic",
				ibmp.mcLoc("block/cube_column"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "stripped_small_logs_top"));

		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/basic_horizontal",
				ibmp.mcLoc("block/cube_column_horizontal"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "stripped_small_logs_top"));

		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/corner",
				ibmp.modLoc("block/small_logs_corner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "stripped_small_logs_corner"));

		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/corner_horizontal",
				ibmp.modLoc("block/small_logs_corner_horizontal"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "stripped_small_logs_corner"));

		ibmp.withExistingParent(LOG_SLAB.location(base) + "/slab/small_logs_bottom",
				ibmp.modLoc("block/bases/slab/small_logs_slab_bottom"))
				.texture("0", Compendium.modLoc(LOG.location(base) + "small_logs"))
				.texture("1", Compendium.modLoc(LOG.location(base) + "small_logs_slab"))
				.texture("particle", Compendium.modLoc(LOG.location(base) + "small_logs"));
		ibmp.withExistingParent(LOG_SLAB.location(base) + "/slab/small_logs_top",
				ibmp.modLoc("block/bases/slab/small_logs_slab_top"))
				.texture("0", Compendium.modLoc(LOG.location(base) + "small_logs"))
				.texture("1", Compendium.modLoc(LOG.location(base) + "small_logs_slab"))
				.texture("particle", Compendium.modLoc(LOG.location(base) + "small_logs"));
		ibmp.withExistingParent(LOG_SLAB.location(base) + "/slab/small_logs_full",
				ibmp.modLoc("block/bases/slab/small_logs_slab_full"))
				.texture("0", Compendium.modLoc(LOG.location(base) + "small_logs"))
				.texture("1", Compendium.modLoc(LOG.location(base) + "small_logs_slab"))
				.texture("particle", Compendium.modLoc(LOG.location(base) + "small_logs"));

		ibmp.withExistingParent(LOG_SLAB.location(base) + "/slab/small_logs_rotated_bottom",
				ibmp.modLoc("block/bases/slab/small_logs_rotated_slab_bottom"))
				.texture("0", Compendium.modLoc(LOG.location(base) + "small_logs"))
				.texture("1", Compendium.modLoc(LOG.location(base) + "small_logs_slab"))
				.texture("particle", Compendium.modLoc(LOG.location(base) + "small_logs"));
		ibmp.withExistingParent(LOG_SLAB.location(base) + "/slab/small_logs_rotated_top",
				ibmp.modLoc("block/bases/slab/small_logs_rotated_slab_top"))
				.texture("0", Compendium.modLoc(LOG.location(base) + "small_logs"))
				.texture("1", Compendium.modLoc(LOG.location(base) + "small_logs_slab"))
				.texture("particle", Compendium.modLoc(LOG.location(base) + "small_logs"));
		ibmp.withExistingParent(LOG_SLAB.location(base) + "/slab/small_logs_rotated_full",
				ibmp.modLoc("block/bases/slab/small_logs_rotated_slab_full"))
				.texture("0", Compendium.modLoc(LOG.location(base) + "small_logs"))
				.texture("1", Compendium.modLoc(LOG.location(base) + "small_logs_slab"))
				.texture("particle", Compendium.modLoc(LOG.location(base) + "small_logs"));

		ibmp.withExistingParent(LOG_SLAB.location(base) + "/slab/split_bottom",
				ibmp.modLoc("block/bases/slab/split_log_slab_bottom"))
				.texture("0", mcLoc("block/" + base.name + "_" + logstem))
				.texture("1", mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "log_split_side"))
				.texture("particle", mcLoc("block/" + base.name + "_" + logstem));
		ibmp.withExistingParent(LOG_SLAB.location(base) + "/slab/split_top",
				ibmp.modLoc("block/bases/slab/split_log_slab_top"))
				.texture("0", mcLoc("block/" + base.name + "_" + logstem))
				.texture("1", mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "log_split_side"))
				.texture("particle", mcLoc("block/" + base.name + "_" + logstem));
		ibmp.withExistingParent(LOG_SLAB.location(base) + "/slab/split_full",
				ibmp.modLoc("block/bases/slab/split_log_slab_full"))
				.texture("0", mcLoc("block/" + base.name + "_" + logstem))
				.texture("1", mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "log_split_side"))
				.texture("particle", mcLoc("block/" + base.name + "_" + logstem));

		ibmp.withExistingParent(LOG_SLAB.location(base) + "/slab/split_rotated_bottom",
				ibmp.modLoc("block/bases/slab/split_log_rotated_slab_bottom"))
				.texture("0", mcLoc("block/" + base.name + "_" + logstem))
				.texture("1", mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "log_split_side"))
				.texture("particle", mcLoc("block/" + base.name + "_" + logstem));
		ibmp.withExistingParent(LOG_SLAB.location(base) + "/slab/split_rotated_top",
				ibmp.modLoc("block/bases/slab/split_log_rotated_slab_top"))
				.texture("0", mcLoc("block/" + base.name + "_" + logstem))
				.texture("1", mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "log_split_side"))
				.texture("particle", mcLoc("block/" + base.name + "_" + logstem));
		ibmp.withExistingParent(LOG_SLAB.location(base) + "/slab/split_rotated_full",
				ibmp.modLoc("block/bases/slab/split_log_rotated_slab_full"))
				.texture("0", mcLoc("block/" + base.name + "_" + logstem))
				.texture("1", mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "log_split_side"))
				.texture("particle", mcLoc("block/" + base.name + "_" + logstem));

		ibmp.withExistingParent(LOG_SLAB.location(base) + "/slab/crosscut_bottom",
						ibmp.modLoc("block/bases/slab/crosscut_log_slab_bottom"))
				.texture("0", mcLoc("block/" + base.name + "_" + logstem))
				.texture("1", mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "log_split_side"))
				.texture("particle", mcLoc("block/" + base.name + "_" + logstem));
		ibmp.withExistingParent(LOG_SLAB.location(base) + "/slab/crosscut_top",
						ibmp.modLoc("block/bases/slab/crosscut_log_slab_top"))
				.texture("0", mcLoc("block/" + base.name + "_" + logstem))
				.texture("1", mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "log_split_side"))
				.texture("particle", mcLoc("block/" + base.name + "_" + logstem));
		ibmp.withExistingParent(LOG_SLAB.location(base) + "/slab/crosscut_full",
						ibmp.modLoc("block/bases/slab/crosscut_log_slab_full"))
				.texture("0", mcLoc("block/" + base.name + "_" + logstem))
				.texture("1", mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "log_split_side"))
				.texture("particle", mcLoc("block/" + base.name + "_" + logstem));

		ibmp.withExistingParent(LOG_SLAB.location(base) + "/slab/crosscut_small_bottom",
						ibmp.modLoc("block/bases/slab/crosscut_small_logs_slab_bottom"))
				.texture("0", Compendium.modLoc(LOG.location(base) + "small_logs"))
				.texture("1", Compendium.modLoc(LOG.location(base) + "small_logs_top"))
				.texture("particle", Compendium.modLoc(LOG.location(base) + "small_logs"));
		ibmp.withExistingParent(LOG_SLAB.location(base) + "/slab/crosscut_small_top",
						ibmp.modLoc("block/bases/slab/crosscut_small_logs_slab_top"))
				.texture("0", Compendium.modLoc(LOG.location(base) + "small_logs"))
				.texture("1", Compendium.modLoc(LOG.location(base) + "small_logs_top"))
				.texture("particle", Compendium.modLoc(LOG.location(base) + "small_logs"));
		ibmp.withExistingParent(LOG_SLAB.location(base) + "/slab/crosscut_small_full",
						ibmp.modLoc("block/bases/slab/crosscut_small_logs_slab_full"))
				.texture("0", Compendium.modLoc(LOG.location(base) + "small_logs"))
				.texture("1", Compendium.modLoc(LOG.location(base) + "small_logs_top"))
				.texture("particle", Compendium.modLoc(LOG.location(base) + "small_logs"));

		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/small_logs",
				ibmp.modLoc("block/bases/stairs/small_logs_stairs"))
				.texture("0", Compendium.modLoc(LOG.location(base) + "small_logs"))
				.texture("1", Compendium.modLoc(LOG.location(base) + "small_logs_top"))
				.texture("particle", Compendium.modLoc(LOG.location(base) + "small_logs"));
		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/small_logs_inner",
				ibmp.modLoc("block/bases/stairs/small_logs_stairs_inner"))
				.texture("0", Compendium.modLoc(LOG.location(base) + "small_logs_corner"))
				.texture("1", Compendium.modLoc(LOG.location(base) + "small_logs_slab"))
				.texture("2", Compendium.modLoc(LOG.location(base) + "small_logs_turned"))
				.texture("particle", Compendium.modLoc(LOG.location(base) + "small_logs"));
		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/small_logs_outer",
				Compendium.modLoc("block/bases/stairs/small_logs_stairs_outer"))
				.texture("0", Compendium.modLoc(LOG.location(base) + "small_logs_turned"))
				.texture("1", Compendium.modLoc(LOG.location(base) + "small_logs"))
				.texture("particle", Compendium.modLoc(LOG.location(base) + "small_logs"));

		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/small_log_rotated_side",
				ibmp.modLoc("block/bases/stairs/small_logs_stairs_x"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "small_logs_top"))
				.texture("particle", ibmp.modLoc(LOG.location(base) + "small_logs"));
		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/small_log_rotated_side_inner",
						ibmp.modLoc("block/bases/stairs/small_logs_stairs_x_inner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "small_logs_top"))
				.texture("particle", ibmp.modLoc(LOG.location(base) + "small_logs"));
		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/small_log_rotated_side_outer",
						ibmp.modLoc("block/bases/stairs/small_logs_stairs_x_outer"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "small_logs_top"))
				.texture("particle", ibmp.modLoc(LOG.location(base) + "small_logs"));

		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/small_log_rotated_front",
				ibmp.modLoc("block/bases/stairs/small_logs_stairs_y"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "small_logs_top"))
				.texture("particle", ibmp.modLoc(LOG.location(base) + "small_logs"));
		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/small_log_rotated_front_inner",
						ibmp.modLoc("block/bases/stairs/small_logs_stairs_y_inner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "small_logs_top"))
				.texture("particle", ibmp.modLoc(LOG.location(base) + "small_logs"));
		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/small_log_rotated_front_outer",
						ibmp.modLoc("block/bases/stairs/small_logs_stairs_y_outer"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "small_logs_top"))
				.texture("particle", ibmp.modLoc(LOG.location(base) + "small_logs"));

		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/small_log_rotated_top",
				ibmp.modLoc("block/bases/stairs/small_logs_stairs_z"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "small_logs_top"))
				.texture("particle", ibmp.modLoc(LOG.location(base) + "small_logs"));
		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/small_log_rotated_top_inner",
						ibmp.modLoc("block/bases/stairs/small_logs_stairs_z_inner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "small_logs_top"))
				.texture("particle", ibmp.modLoc(LOG.location(base) + "small_logs"));
		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/small_log_rotated_top_outer",
						ibmp.modLoc("block/bases/stairs/small_logs_stairs_z_outer"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "small_logs_top"))
				.texture("particle", ibmp.modLoc(LOG.location(base) + "small_logs"));

		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/split_log_rotated_side",
				ibmp.modLoc("block/bases/stairs/split_log_stairs_x"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "log_split_side"))
				.texture("2", mcLoc("block/" + base.name + "_" + logstem))
				.texture("3", mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("particle", mcLoc("block/" + base.name + "_" + logstem));
		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/split_log_rotated_side_inner",
						ibmp.modLoc("block/bases/stairs/split_log_stairs_x_inner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "log_split_side"))
				.texture("2", mcLoc("block/" + base.name + "_" + logstem))
				.texture("3", mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("particle", mcLoc("block/" + base.name + "_" + logstem));
		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/split_log_rotated_side_outer",
						ibmp.modLoc("block/bases/stairs/split_log_stairs_x_outer"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "log_split_side"))
				.texture("2", mcLoc("block/" + base.name + "_" + logstem))
				.texture("3", mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("particle", mcLoc("block/" + base.name + "_" + logstem));

		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/split_log_rotated_front",
				ibmp.modLoc("block/bases/stairs/split_log_stairs_y"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "log_split_side"))
				.texture("2", mcLoc("block/" + base.name + "_" + logstem))
				.texture("3", mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("particle", mcLoc("block/" + base.name + "_" + logstem));
		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/split_log_rotated_front_inner",
						ibmp.modLoc("block/bases/stairs/split_log_stairs_y_inner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "log_split_side"))
				.texture("2", mcLoc("block/" + base.name + "_" + logstem))
				.texture("3", mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("particle", mcLoc("block/" + base.name + "_" + logstem));
		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/split_log_rotated_front_outer",
						ibmp.modLoc("block/bases/stairs/split_log_stairs_y_outer"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "log_split_side"))
				.texture("2", mcLoc("block/" + base.name + "_" + logstem))
				.texture("3", mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("particle", mcLoc("block/" + base.name + "_" + logstem));

		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/split_log_rotated_top",
				ibmp.modLoc("block/bases/stairs/split_log_stairs_z"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "log_split_side"))
				.texture("2", mcLoc("block/" + base.name + "_" + logstem))
				.texture("3", mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("particle", mcLoc("block/" + base.name + "_" + logstem));
		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/split_log_rotated_top_inner",
						ibmp.modLoc("block/bases/stairs/split_log_stairs_z_inner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "log_split_side"))
				.texture("2", mcLoc("block/" + base.name + "_" + logstem))
				.texture("3", mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("particle", mcLoc("block/" + base.name + "_" + logstem));
		ibmp.withExistingParent(LOG_STAIRS.location(base) + "/stairs/split_log_rotated_top_outer",
						ibmp.modLoc("block/bases/stairs/split_log_stairs_z_outer"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "log_split_side"))
				.texture("2", mcLoc("block/" + base.name + "_" + logstem))
				.texture("3", mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("particle", mcLoc("block/" + base.name + "_" + logstem));

		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/small_logs",
						ibmp.modLoc("block/bases/stairs/small_logs_stairs"))
				.texture("0", Compendium.modLoc(LOG.location(base) + "stripped_small_logs"))
				.texture("1", Compendium.modLoc(LOG.location(base) + "stripped_small_logs_top"))
				.texture("particle", Compendium.modLoc(LOG.location(base) + "stripped_small_logs"));
		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/small_logs_inner",
						ibmp.modLoc("block/bases/stairs/small_logs_stairs_inner"))
				.texture("0", Compendium.modLoc(LOG.location(base) + "stripped_small_logs_corner"))
				.texture("1", Compendium.modLoc(LOG.location(base) + "stripped_small_logs_slab"))
				.texture("2", Compendium.modLoc(LOG.location(base) + "stripped_small_logs_turned"))
				.texture("particle", Compendium.modLoc(LOG.location(base) + "small_logs"));
		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/small_logs_outer",
						Compendium.modLoc("block/bases/stairs/small_logs_stairs_outer"))
				.texture("0", Compendium.modLoc(LOG.location(base) + "stripped_small_logs_turned"))
				.texture("1", Compendium.modLoc(LOG.location(base) + "stripped_small_logs"))
				.texture("particle", Compendium.modLoc(LOG.location(base) + "stripped_small_logs"));

		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/small_log_rotated_side",
						ibmp.modLoc("block/bases/stairs/small_logs_stairs_x"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "stripped_small_logs_top"))
				.texture("particle", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"));
		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/small_log_rotated_side_inner",
						ibmp.modLoc("block/bases/stairs/small_logs_stairs_x_inner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "stripped_small_logs_top"))
				.texture("particle", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"));
		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/small_log_rotated_side_outer",
						ibmp.modLoc("block/bases/stairs/small_logs_stairs_x_outer"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "stripped_small_logs_top"))
				.texture("particle", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"));

		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/small_log_rotated_front",
						ibmp.modLoc("block/bases/stairs/small_logs_stairs_y"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "stripped_small_logs_top"))
				.texture("particle", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"));
		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/small_log_rotated_front_inner",
						ibmp.modLoc("block/bases/stairs/small_logs_stairs_y_inner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "stripped_small_logs_top"))
				.texture("particle", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"));
		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/small_log_rotated_front_outer",
						ibmp.modLoc("block/bases/stairs/small_logs_stairs_y_outer"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "stripped_small_logs_top"))
				.texture("particle", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"));

		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/small_log_rotated_top",
						ibmp.modLoc("block/bases/stairs/small_logs_stairs_z"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "stripped_small_logs_top"))
				.texture("particle", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"));
		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/small_log_rotated_top_inner",
						ibmp.modLoc("block/bases/stairs/small_logs_stairs_z_inner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "stripped_small_logs_top"))
				.texture("particle", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"));
		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/small_log_rotated_top_outer",
						ibmp.modLoc("block/bases/stairs/small_logs_stairs_z_outer"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "stripped_small_logs_top"))
				.texture("particle", ibmp.modLoc(LOG.location(base) + "stripped_small_logs"));

		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/split_log_rotated_side",
						ibmp.modLoc("block/bases/stairs/split_log_stairs_x"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "log_split_side")) //make stripped
				.texture("2", mcLoc("block/stripped_" + base.name + "_" + logstem))
				.texture("3", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"))
				.texture("particle", mcLoc("block/stripped_" + base.name + "_" + logstem));
		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/split_log_rotated_side_inner",
						ibmp.modLoc("block/bases/stairs/split_log_stairs_x_inner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "log_split_side")) //make stripped
				.texture("2", mcLoc("block/stripped_" + base.name + "_" + logstem))
				.texture("3", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"))
				.texture("particle", mcLoc("block/stripped_" + base.name + "_" + logstem));
		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/split_log_rotated_side_outer",
						ibmp.modLoc("block/bases/stairs/split_log_stairs_x_outer"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "log_split_side")) //make stripped
				.texture("2", mcLoc("block/stripped_" + base.name + "_" + logstem))
				.texture("3", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"))
				.texture("particle", mcLoc("block/stripped_" + base.name + "_" + logstem));

		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/split_log_rotated_front",
						ibmp.modLoc("block/bases/stairs/split_log_stairs_y"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "log_split_side")) //make stripped
				.texture("2", mcLoc("block/stripped_" + base.name + "_" + logstem))
				.texture("3", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"))
				.texture("particle", mcLoc("block/stripped_" + base.name + "_" + logstem));
		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/split_log_rotated_front_inner",
						ibmp.modLoc("block/bases/stairs/split_log_stairs_y_inner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "log_split_side")) //make stripped
				.texture("2", mcLoc("block/stripped_" + base.name + "_" + logstem))
				.texture("3", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"))
				.texture("particle", mcLoc("block/stripped_" + base.name + "_" + logstem));
		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/split_log_rotated_front_outer",
						ibmp.modLoc("block/bases/stairs/split_log_stairs_y_outer"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "log_split_side")) //make stripped
				.texture("2", mcLoc("block/stripped_" + base.name + "_" + logstem))
				.texture("3", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"))
				.texture("particle", mcLoc("block/stripped_" + base.name + "_" + logstem));

		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/split_log_rotated_top",
						ibmp.modLoc("block/bases/stairs/split_log_stairs_z"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "log_split_side")) //make stripped
				.texture("2", mcLoc("block/stripped_" + base.name + "_" + logstem))
				.texture("3", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"))
				.texture("particle", mcLoc("block/stripped_" + base.name + "_" + logstem));
		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/split_log_rotated_top_inner",
						ibmp.modLoc("block/bases/stairs/split_log_stairs_z_inner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "log_split_side")) //make stripped
				.texture("2", mcLoc("block/stripped_" + base.name + "_" + logstem))
				.texture("3", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"))
				.texture("particle", mcLoc("block/stripped_" + base.name + "_" + logstem));
		ibmp.withExistingParent(STRIPPED_LOG_STAIRS.location(base) + "/stripped_stairs/split_log_rotated_top_outer",
						ibmp.modLoc("block/bases/stairs/split_log_stairs_z_outer"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "log_split_side")) //make stripped
				.texture("2", mcLoc("block/stripped_" + base.name + "_" + logstem))
				.texture("3", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"))
				.texture("particle", mcLoc("block/stripped_" + base.name + "_" + logstem));
	}

	@Override
	public void blockStateModel(_MaterialBase base, BlockStateProvider bsp) {
		if (this.autoGenBlockModel) {
			smallLogsModel(SMALL_LOG, base, bsp, "");
			smallLogsModel(STRIPPED_SMALL_LOG_PIPE, base, bsp, "stripped_");

			if (LOG.enabled()) {
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
						return b.build();
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
			if (STRIPPED_LOG.enabled()) {
				bsp.getVariantBuilder(STRIPPED_LOG.BLOCK.get()).forAllStates(state -> {
					Direction.Axis axis = state.getValue(RotatedPillarBlock.AXIS);

					if (axis == Direction.Axis.X || axis == Direction.Axis.Z) {
						Builder<?> b = ConfiguredModel.builder();
						StyleBlockModelBuilder<BlockModelBuilder> msmb = bsp.models()
								.getBuilder(LOG.location(base) + "stripped_log_horizontal")
								.customLoader(StyleBlockModelBuilder::begin);
						msmb.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

						for (String s : StyleData.LOG.getTypes())
							msmb.add(new StyleModelBuilder(s,
									bsp.modLoc(LOG.location(base) + "stripped_log/" + s.toLowerCase() + "_horizontal")));

						BlockModelBuilder bmb = msmb.end();
						b.modelFile(bmb);
						if (axis == Direction.Axis.X)
							b.rotationY(90);
						return b.build();
					}

					Builder<?> b = ConfiguredModel.builder();
					StyleBlockModelBuilder<BlockModelBuilder> msmb = bsp.models().getBuilder(LOG.location(base) + "stripped_log")
							.customLoader(StyleBlockModelBuilder::begin);
					msmb.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

					for (String s : StyleData.LOG.getTypes())
						msmb.add(new StyleModelBuilder(s, bsp.modLoc(LOG.location(base) + "stripped_log/" + s.toLowerCase())));

					BlockModelBuilder bmb = msmb.end();
					b.modelFile(bmb);
					return b.build();

				});
			}
			if (LOG_SLAB.enabled()) {
				StyleBlockModelBuilder<BlockModelBuilder> log_slab_bottom = bsp.models()
						.getBuilder(LOG.location(base) + "log_slab_bottom").customLoader(StyleBlockModelBuilder::begin);
				log_slab_bottom.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_bottom
							.add(new StyleModelBuilder(s, bsp.modLoc(LOG.location(base) + "slab/" + s.toLowerCase() + "_bottom")));

				StyleBlockModelBuilder<BlockModelBuilder> log_slab_top = bsp.models()
						.getBuilder(LOG.location(base) + "log_slab_top").customLoader(StyleBlockModelBuilder::begin);
				log_slab_top.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_top
							.add(new StyleModelBuilder(s, bsp.modLoc(LOG.location(base) + "slab/" + s.toLowerCase() + "_top")));

				StyleBlockModelBuilder<BlockModelBuilder> log_slab_full = bsp.models()
						.getBuilder(LOG.location(base) + "log_slab_full").customLoader(StyleBlockModelBuilder::begin);
				log_slab_full.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_full
							.add(new StyleModelBuilder(s, bsp.modLoc(LOG.location(base) + "slab/" + s.toLowerCase() + "_full")));

				bsp.slabBlock((SlabBlock) LOG_SLAB.BLOCK.get(), log_slab_bottom.end(), log_slab_top.end(),
						log_slab_full.end());
			}

			if (STRIPPED_LOG_SLAB.enabled()) {
				StyleBlockModelBuilder<BlockModelBuilder> log_slab_bottom = bsp.models()
						.getBuilder(LOG.location(base) + "stripped_log_slab_bottom")
						.customLoader(StyleBlockModelBuilder::begin);
				log_slab_bottom.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_bottom
							.add(new StyleModelBuilder(s, bsp.modLoc(LOG.location(base) + "slab/" + s.toLowerCase() + "_bottom")));

				StyleBlockModelBuilder<BlockModelBuilder> log_slab_top = bsp.models()
						.getBuilder(LOG.location(base) + "stripped_log_slab_top")
						.customLoader(StyleBlockModelBuilder::begin);
				log_slab_top.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_top
							.add(new StyleModelBuilder(s, bsp.modLoc(LOG.location(base) + "slab/" + s.toLowerCase() + "_top")));

				StyleBlockModelBuilder<BlockModelBuilder> log_slab_full = bsp.models()
						.getBuilder(LOG.location(base) + "stripped_log_slab_full")
						.customLoader(StyleBlockModelBuilder::begin);
				log_slab_full.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_full.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "stripped_slab/" + s.toLowerCase()  + "_full")));

				bsp.slabBlock((SlabBlock) STRIPPED_LOG_SLAB.BLOCK.get(), log_slab_bottom.end(), log_slab_top.end(),
						log_slab_full.end());
			}
			if (LOG_STAIRS.enabled()) {
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

			if (STRIPPED_LOG_STAIRS.enabled()) {
				StyleBlockModelBuilder<BlockModelBuilder> log_stairs_standard = bsp.models()
						.getBuilder(LOG.location(base) + "stripped_log_stairs").customLoader(StyleBlockModelBuilder::begin);
				log_stairs_standard.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_STAIRS.getTypes())
					log_stairs_standard.add(
							new StyleModelBuilder(s, bsp.modLoc(LOG.location(base) + "stripped_stairs/" + s.toLowerCase())));

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

				stairsBlock((StairBlock) STRIPPED_LOG_STAIRS.BLOCK.get(), log_stairs_standard.end(), log_stairs_inner.end(),
						log_stairs_outer.end(), bsp);
			}
		}
	}

	private void smallLogsModel(CompendiumBlockHandler block, _MaterialBase base, BlockStateProvider bsp,
			String extra) {
		if (block.enabled()) {
			BlockModelBuilder base_model_horizontal = bsp.models()
					.withExistingParent(block.location(base) + extra + "horizontal",
							bsp.modLoc("block/small_log_horizontal"))
					.texture("0", bsp.modLoc(base.blockFolder() + extra + "small_logs_corner"));
			BlockModelBuilder base_model_horizontal2 = bsp.models()
					.withExistingParent(block.location(base) + extra + "horizontal_rot",
							bsp.modLoc("block/small_log_horizontal2"))
					.texture("0", bsp.modLoc(base.blockFolder() + extra + "small_logs_corner"));
			BlockModelBuilder base_model_vertical = bsp.models()
					.withExistingParent(base.blockFolder() + extra + "vertical", bsp.modLoc("block/small_log_vertical"))
					.texture("0", bsp.modLoc(base.blockFolder() + extra + "small_logs_corner"));
			BlockModelBuilder model_cap = bsp.models()
					.withExistingParent(block.location(base) + extra + "cap", bsp.modLoc("block/small_log_cap"))
					.texture("0", bsp.modLoc(base.blockFolder() + extra + "small_logs_corner"));

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
			if (SMALL_LOG.enabled()) {
				DataUtil.basicMaterialBlockItem(tmp, SMALL_LOG.BLOCK_ITEM, base.name, "small_log", base.getType());
			}
			if (LOG.enabled()) {
				tmp.withExistingParent(LOG.BLOCK_ITEM.getRegisteredName(), tmp.modLoc("item/chair"));
			}
			if (LOG_SLAB.enabled()) {

			}
			if (LOG_STAIRS.enabled()) {
				DataUtil.basicMaterialBlockItem(tmp, LOG_STAIRS.BLOCK_ITEM, base.name, "small_logs_stairs",
						base.getType());
			}

			if (STRIPPED_SMALL_LOG_PIPE.enabled()) {
				DataUtil.basicMaterialBlockItem(tmp, STRIPPED_SMALL_LOG_PIPE.BLOCK_ITEM, base.name,
						"stripped_small_log", base.getType());
			}
			if (STRIPPED_LOG.enabled()) {
				DataUtil.basicMaterialBlockItem(tmp, STRIPPED_LOG.BLOCK_ITEM, base.name, "stripped_small_logs",
						base.getType());
			}
			if (STRIPPED_LOG_SLAB.enabled()) {
				DataUtil.basicMaterialBlockItem(tmp, STRIPPED_LOG_SLAB.BLOCK_ITEM, base.name,
						"stripped_small_logs_slab_bottom", base.getType());
			}
			if (STRIPPED_LOG_STAIRS.enabled()) {
				DataUtil.basicMaterialBlockItem(tmp, STRIPPED_LOG_STAIRS.BLOCK_ITEM, base.name,
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
		if (LOG.enabled()) {
			lp.add(this.LOG.BLOCK_ITEM.asItem(), material_name + "Styled Log");
		}
		if (LOG_SLAB.enabled()) {
			lp.add(this.LOG_SLAB.BLOCK_ITEM.asItem(), material_name + "Styled Log Slab");
		}
		if (LOG_STAIRS.enabled()) {
			lp.add(this.LOG_STAIRS.BLOCK_ITEM.asItem(), material_name + "Styled Log Stairs");
		}

		if (STRIPPED_SMALL_LOG_PIPE.enabled()) {
			lp.add(this.STRIPPED_SMALL_LOG_PIPE.BLOCK_ITEM.asItem(), "Stripped " + material_name + "Small Log");
		}
		if (STRIPPED_LOG.enabled()) {
			lp.add(this.STRIPPED_LOG.BLOCK_ITEM.asItem(), "Stripped " + material_name + "Styled Logs");
		}
		if (STRIPPED_LOG_SLAB.enabled()) {
			lp.add(this.STRIPPED_LOG_SLAB.BLOCK_ITEM.asItem(), "Stripped " + material_name + "Styled Log Slab");
		}
		if (STRIPPED_LOG_STAIRS.enabled()) {
			lp.add(this.STRIPPED_LOG_STAIRS.BLOCK_ITEM.asItem(), "Stripped " + material_name + "Styled Log Stairs");
		}
	}

	@Override
	public void recipes(_MaterialBase base, RecipeOutput consumer) {
		if (SMALL_LOG.enabled()) {
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
		if (LOG.enabled()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LOG.BLOCK_ITEM, 1).pattern("bb").pattern("bb")
					.define('b', SMALL_LOG.BLOCK_ITEM)
					.unlockedBy("has_small_log", InventoryChangeTrigger.TriggerInstance.hasItems(SMALL_LOG.BLOCK_ITEM))
					.save(consumer);
		}
		if (LOG_SLAB.enabled()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LOG_SLAB.BLOCK_ITEM, 1).pattern("bb")
					.define('b', SMALL_LOG.BLOCK_ITEM)
					.unlockedBy("has_small_log", InventoryChangeTrigger.TriggerInstance.hasItems(SMALL_LOG.BLOCK_ITEM))
					.save(consumer);
		}
		if (LOG_STAIRS.enabled()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LOG_STAIRS.BLOCK_ITEM, 1).pattern("b ")
					.pattern("bb").define('b', SMALL_LOG.BLOCK_ITEM)
					.unlockedBy("has_small_log", InventoryChangeTrigger.TriggerInstance.hasItems(SMALL_LOG.BLOCK_ITEM))
					.save(consumer);
		}
//		if (LOG_CORNER.enabled()) {
//			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, LOG_CORNER.BLOCK_ITEM, 1).pattern("b")
//					.pattern("b").define('b', LOG_SLAB.BLOCK_ITEM).unlockedBy("has_small_logs_slab",
//							InventoryChangeTrigger.TriggerInstance.hasItems(LOG_SLAB.BLOCK_ITEM))
//					.save(consumer);
//		}

		if (STRIPPED_SMALL_LOG_PIPE.enabled()) {
			SawBuckRecipeBuilder
					.saw(Ingredient.of(
							TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace(base.name + "_logs"))),
							new ItemStack(STRIPPED_SMALL_LOG_PIPE.BLOCK_ITEM.get(), 4), Vec3.ZERO)
					.tool(Ingredient.of(ItemTags.AXES), 4, true, RecipeLootTables.SAW_DUST, List.of(),
							Recipes.standardSawBuckAxeModel(TagUtil.modLoc("iron_axe"), 0))
					.save(consumer);
		}
		if (STRIPPED_LOG.enabled()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, STRIPPED_LOG.BLOCK_ITEM, 1).pattern("bb")
					.pattern("bb").define('b', STRIPPED_SMALL_LOG_PIPE.BLOCK_ITEM)
					.unlockedBy("has_stripped_small_log",
							InventoryChangeTrigger.TriggerInstance.hasItems(STRIPPED_SMALL_LOG_PIPE.BLOCK_ITEM))
					.save(consumer);
		}
		if (STRIPPED_LOG_SLAB.enabled()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, STRIPPED_LOG_SLAB.BLOCK_ITEM, 1).pattern("bb")
					.define('b', STRIPPED_SMALL_LOG_PIPE.BLOCK_ITEM)
					.unlockedBy("has_striepped_small_log",
							InventoryChangeTrigger.TriggerInstance.hasItems(STRIPPED_SMALL_LOG_PIPE.BLOCK_ITEM))
					.save(consumer);
		}
		if (STRIPPED_LOG_STAIRS.enabled()) {
			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, STRIPPED_LOG_STAIRS.BLOCK_ITEM, 1).pattern("b ")
					.pattern("bb").define('b', STRIPPED_SMALL_LOG_PIPE.BLOCK_ITEM)
					.unlockedBy("has_stripped_small_log",
							InventoryChangeTrigger.TriggerInstance.hasItems(STRIPPED_SMALL_LOG_PIPE.BLOCK_ITEM))
					.save(consumer);
		}
//		if (STRIPPED_LOG_CORNER.enabled()) {
//			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, STRIPPED_LOG_CORNER.BLOCK_ITEM, 1).pattern("b")
//					.pattern("b").define('b', STRIPPED_LOG_SLAB.BLOCK_ITEM)
//					.unlockedBy("has_stripped_small_logs_slab",
//							InventoryChangeTrigger.TriggerInstance.hasItems(STRIPPED_LOG_SLAB.BLOCK_ITEM))
//					.save(consumer);
//		}
	}

	@Override
	public void blockLoot(_MaterialBase base, BlockLootSubProvider blp) {
		if (SMALL_LOG.enabled()) {
			blp.dropSelf(SMALL_LOG.BLOCK.get());
		}
		if (LOG.enabled()) {
			blp.dropSelf(this.LOG.BLOCK.get());
		}
//		if (LOG_CORNER.enabled()) {
//			blp.dropSelf(this.LOG_CORNER.BLOCK.get());
//		}
		if (LOG_SLAB.enabled()) {
			blp.dropSelf(this.LOG_SLAB.BLOCK.get());
		}
		if (LOG_STAIRS.enabled()) {
			blp.dropSelf(this.LOG_STAIRS.BLOCK.get());
		}

		if (STRIPPED_SMALL_LOG_PIPE.enabled()) {
			blp.dropSelf(STRIPPED_SMALL_LOG_PIPE.BLOCK.get());
		}
		if (STRIPPED_LOG.enabled()) {
			blp.dropSelf(this.STRIPPED_LOG.BLOCK.get());
		}
//		if (STRIPPED_LOG_CORNER.enabled()) {
//			blp.dropSelf(this.STRIPPED_LOG_CORNER.BLOCK.get());
//		}
		if (STRIPPED_LOG_SLAB.enabled()) {
			blp.dropSelf(this.STRIPPED_LOG_SLAB.BLOCK.get());
		}
		if (STRIPPED_LOG_STAIRS.enabled()) {
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

			j.addProperty("loadSmallLog", src.SMALL_LOG.enabled());
			j.addProperty("loadSmallLogs", src.LOG.enabled());
//			j.addProperty("loadSmallCornerLogs", src.LOG_CORNER.enabled());
			j.addProperty("loadSmallLogsSlab", src.LOG_SLAB.enabled());
			j.addProperty("loadSmallLogsStairs", src.LOG_STAIRS.enabled());

			j.addProperty("loadStrippedSmallLog", src.STRIPPED_SMALL_LOG_PIPE.enabled());
			j.addProperty("loadStrippedSmallLogs", src.STRIPPED_LOG.enabled());
//			j.addProperty("loadStrippedSmallCornerLogs", src.STRIPPED_LOG_CORNER.enabled());
			j.addProperty("loadStrippedSmallLogsSlab", src.STRIPPED_LOG_SLAB.enabled());
			j.addProperty("loadStrippedSmallLogsStairs", src.STRIPPED_LOG_STAIRS.enabled());

			return j;
		}

		@Override
		public ExtensionExtraLogs deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			boolean loadSmallLog = j.get("loadSmallLog").getAsBoolean();
			boolean loadSmallLogs = j.get("loadSmallLogs").getAsBoolean();
//			boolean loadSmallCornerLogs = j.get("loadSmallCornerLogs").getAsBoolean();
			boolean loadSmallLogsSlab = j.get("loadSmallLogsSlab").getAsBoolean();
			boolean loadSmallLogsStairs = j.get("loadSmallLogsStairs").getAsBoolean();

			boolean loadStrippedSmallLog = j.get("loadStrippedSmallLog").getAsBoolean();
			boolean loadStrippedSmallLogs = j.get("loadStrippedSmallLogs").getAsBoolean();
//			boolean loadStrippedSmallCornerLogs = j.get("loadStrippedSmallCornerLogs").getAsBoolean();
			boolean loadStrippedSmallLogsSlab = j.get("loadStrippedSmallLogsSlab").getAsBoolean();
			boolean loadStrippedSmallLogsStairs = j.get("loadStrippedSmallLogsStairs").getAsBoolean();

			return new ExtensionExtraLogs(loadSmallLog, loadSmallLogs, loadSmallLogsSlab, loadSmallLogsStairs,
					loadStrippedSmallLog, loadStrippedSmallLogs, loadStrippedSmallLogsSlab,
					loadStrippedSmallLogsStairs);
		}

	}

	@Override
	public void otherLoot(_MaterialBase base, LootTableSubProvider lsp) {
		// TODO Auto-generated method stub

	}

}
