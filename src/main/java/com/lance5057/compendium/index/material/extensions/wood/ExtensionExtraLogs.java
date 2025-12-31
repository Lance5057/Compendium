package com.lance5057.compendium.index.material.extensions.wood;

import static com.lance5057.compendium.util.TagUtil.mcLoc;

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
import com.lance5057.compendium.blocks.RotatedPillarStyleBlock;
import com.lance5057.compendium.blocks.SlabStyleBlock;
import com.lance5057.compendium.blocks.StairStyleBlock;
import com.lance5057.compendium.client.models.style.StyleBlockModelBuilder;
import com.lance5057.compendium.client.models.style.model.StyleModelBuilder;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.data.IndexBlockModelProvider;
import com.lance5057.compendium.data.Recipes;
import com.lance5057.compendium.data.loottables.BlockLootTables;
import com.lance5057.compendium.data.loottables.RecipeLootTables;
import com.lance5057.compendium.data.recipebuilders.SawBuckRecipeBuilder;
import com.lance5057.compendium.index.CompendiumIndex.Generate;
import com.lance5057.compendium.index.IIndexEntry;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extensions.MaterialExtensionSerializer;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.index.util.DataUtil;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.util.TagUtil;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel.Builder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ExtensionExtraLogs extends _MaterialExtension {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5373374706645885937L;
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
				() -> new PipeStyleBlock(0.25f, Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS),
						Compendium.modLoc(base.extraFolder() + "small_log"), List.of("small_log"), StyleData.SMALL_LOG),
				() -> new BlockItem(SMALL_LOG.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_small_log"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_small_log"));
		SMALL_LOG.setupItemTag(CompendiumTags.SMALL_LOGS);
		SMALL_LOG.setupItemTag(TagUtil.neoTag("small_logs/" + base.name));
		SMALL_LOG.setupItemTag(TagUtil.neoTag("logs/small"));
		SMALL_LOG.setupItemTag(TagUtil.neoTag("logs/small/" + base.name));
		SMALL_LOG.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
		SMALL_LOG.setAsValidStyleBlock();

		LOG.setup(base,
				() -> new RotatedPillarStyleBlock(Block.Properties.ofFullCopy(Blocks.DARK_OAK_LOG),
						Compendium.modLoc(base.extraFolder() + "log"), List.of("log"), StyleData.LOG),
				() -> new BlockItem(LOG.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_styled_log"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_styled_log"));
		LOG.setupItemTag(ItemTags.LOGS);
		LOG.setupItemTag(TagUtil.neoTag("logs/" + base.name));
		LOG.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
		LOG.setAsValidStyleBlock();
		LOG.setAsValidStyleItem();

		LOG_SLAB.setup(base,
				() -> new SlabStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_SLAB).noOcclusion(),
						Compendium.modLoc(base.extraFolder() + "log_slab"), List.of("log_slab"), StyleData.LOG_SLAB),
				() -> new BlockItem(LOG_SLAB.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_log_slab"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_log_slab"));
		LOG_SLAB.setupItemTag(TagUtil.neoTag("logs/slab"));
		LOG_SLAB.setupItemTag(TagUtil.neoTag("logs/slab/" + base.name));
		LOG_SLAB.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
		LOG_SLAB.setAsValidStyleBlock();
		LOG_SLAB.setAsValidStyleItem();

		LOG_STAIRS.setup(base, () -> new StairStyleBlock(LOG.BLOCK.get().defaultBlockState(),
				Block.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS),
				Compendium.modLoc(base.extraFolder() + "log_stairs"), List.of("log_stairs"), StyleData.LOG_STAIRS),
				() -> new BlockItem(LOG_STAIRS.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_log_stairs"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_log_stairs"));
		LOG_STAIRS.setupItemTag(TagUtil.neoTag("logs/stairs"));
		LOG_STAIRS.setupItemTag(TagUtil.neoTag("logs/stairs/" + base.name));
		LOG_STAIRS.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
		LOG_STAIRS.setAsValidStyleBlock();
		LOG_STAIRS.setAsValidStyleItem();

		STRIPPED_SMALL_LOG.setup(base,
				() -> new PipeStyleBlock(0.25f, Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS),
						Compendium.modLoc(base.extraFolder() + "stripped_small_log"), List.of("small_log"),
						StyleData.SMALL_LOG),
				() -> new BlockItem(STRIPPED_SMALL_LOG.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_stripped_small_log"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_stripped_small_log"));
		STRIPPED_SMALL_LOG.setupItemTag(TagUtil.neoTag("log/stripped/small_logs"));
		STRIPPED_SMALL_LOG.setupItemTag(TagUtil.neoTag("log/stripped/small_logs/" + base.name));
		STRIPPED_SMALL_LOG.setupItemTag(TagUtil.neoTag("stripped_log/small"));
		STRIPPED_SMALL_LOG.setupItemTag(TagUtil.neoTag("stripped_log/small/" + base.name));
		STRIPPED_SMALL_LOG.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
		STRIPPED_SMALL_LOG.setAsValidStyleBlock();

		STRIPPED_LOG.setup(base,
				() -> new RotatedPillarStyleBlock(Block.Properties.ofFullCopy(Blocks.DARK_OAK_LOG),
						Compendium.modLoc(base.extraFolder() + "stripped_log"), List.of("log"), StyleData.LOG),
				() -> new BlockItem(STRIPPED_LOG.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_stripped_log"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_stripped_log"));
		STRIPPED_LOG.setupItemTag(Tags.Items.STRIPPED_LOGS);
		STRIPPED_LOG.setupItemTag(TagUtil.neoTag("stripped_log/" + base.name));
		STRIPPED_LOG.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
		STRIPPED_LOG.setAsValidStyleBlock();
		STRIPPED_LOG.setAsValidStyleItem();

		STRIPPED_LOG_SLAB.setup(base,
				() -> new SlabStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_SLAB).noOcclusion(),
						Compendium.modLoc(base.extraFolder() + "stripped_log_slab"), List.of("log_slab"),
						StyleData.LOG_SLAB),
				() -> new BlockItem(STRIPPED_LOG_SLAB.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_stripped_log_slab"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_stripped_log_slab"));
		STRIPPED_LOG_SLAB.setupItemTag(TagUtil.neoTag("stripped_logs/slab"));
		STRIPPED_LOG_SLAB.setupItemTag(TagUtil.neoTag("stripped_logs/slab/" + base.name));
		STRIPPED_LOG_SLAB.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
		STRIPPED_LOG_SLAB.setAsValidStyleBlock();
		STRIPPED_LOG_SLAB.setAsValidStyleItem();

		STRIPPED_LOG_STAIRS.setup(base,
				() -> new StairStyleBlock(LOG.BLOCK.get().defaultBlockState(),
						Block.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS),
						Compendium.modLoc(base.extraFolder() + "stripped_log_stairs"), List.of("log_stairs"),
						StyleData.LOG_STAIRS),
				() -> new BlockItem(STRIPPED_LOG_STAIRS.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_stripped_log_stairs"),
				ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_stripped_log_stairs"));
		STRIPPED_LOG_STAIRS.setupItemTag(TagUtil.neoTag("stripped_logs/stairs"));
		STRIPPED_LOG_STAIRS.setupItemTag(TagUtil.neoTag("stripped_logs/stairs/" + base.name));
		STRIPPED_LOG_STAIRS.setupBlockTag(BlockTags.MINEABLE_WITH_AXE);
		STRIPPED_LOG_STAIRS.setAsValidStyleBlock();
		STRIPPED_LOG_STAIRS.setAsValidStyleItem();
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
		String logstem;
		if (base.name.equals("warped") || base.name.equals("crimson")) {
			logstem = "stem";
		} else {
			logstem = "log";
		}

		for (String s : StyleData.SMALL_LOG.getTypes()) {
			ibmp.withExistingParent(SMALL_LOG.location(base) + "small_log/" + s,
					ibmp.modLoc("block/bases/small_log/" + s))
					.texture("0", ibmp.modLoc(SMALL_LOG.location(base) + "logs/" + "extra_caps"))
					.texture("1", ibmp.modLoc(SMALL_LOG.location(base) + "logs/" + "small_logs"));

			ibmp.withExistingParent(SMALL_LOG.location(base) + "small_log/" + s + "_horizontal",
					ibmp.modLoc("block/bases/small_log/" + s + "_horizontal"))
					.texture("0", ibmp.modLoc(SMALL_LOG.location(base) + "logs/" + "extra_caps"))
					.texture("1", ibmp.modLoc(SMALL_LOG.location(base) + "logs/" + "small_logs"));

			ibmp.withExistingParent(SMALL_LOG.location(base) + "small_log/" + s + "_horizontal_rot",
					ibmp.modLoc("block/bases/small_log/" + s + "_horizontal2"))
					.texture("0", ibmp.modLoc(SMALL_LOG.location(base) + "logs/" + "extra_caps"))
					.texture("1", ibmp.modLoc(SMALL_LOG.location(base) + "logs/" + "small_logs"));

			ibmp.withExistingParent(SMALL_LOG.location(base) + "small_log/" + s + "_vertical",
					ibmp.modLoc("block/bases/small_log/" + s + "_vertical"))
					.texture("0", ibmp.modLoc(SMALL_LOG.location(base) + "logs/" + "extra_caps"))
					.texture("1", ibmp.modLoc(SMALL_LOG.location(base) + "logs/" + "small_logs"));

			ibmp.withExistingParent(SMALL_LOG.location(base) + "small_log/" + s + "_cap",
					ibmp.modLoc("block/bases/small_log/" + s + "_cap"))
					.texture("0", ibmp.modLoc(SMALL_LOG.location(base) + "logs/" + "extra_caps"))
					.texture("1", ibmp.modLoc(SMALL_LOG.location(base) + "logs/" + "small_logs"));

			ibmp.withExistingParent(STRIPPED_SMALL_LOG.location(base) + "stripped_small_log/" + s,
					ibmp.modLoc("block/bases/stripped_small_log/small_log"))
					.texture("0", ibmp.modLoc(STRIPPED_SMALL_LOG.location(base) + "logs/" + "stripped_extra_caps"))
					.texture("1", ibmp.modLoc(STRIPPED_SMALL_LOG.location(base) + "logs/" + "stripped_small_logs"));

			ibmp.withExistingParent(STRIPPED_SMALL_LOG.location(base) + "stripped_small_log/" + s + "_horizontal",
					ibmp.modLoc("block/bases/stripped_small_log/" + s + "_horizontal"))
					.texture("0", ibmp.modLoc(STRIPPED_SMALL_LOG.location(base) + "logs/" + "stripped_extra_caps"))
					.texture("1", ibmp.modLoc(STRIPPED_SMALL_LOG.location(base) + "logs/" + "stripped_small_logs"));

			ibmp.withExistingParent(STRIPPED_SMALL_LOG.location(base) + "stripped_small_log/" + s + "_horizontal_rot",
					ibmp.modLoc("block/bases/stripped_small_log/" + s + "_horizontal2"))
					.texture("0", ibmp.modLoc(STRIPPED_SMALL_LOG.location(base) + "logs/" + "stripped_extra_caps"))
					.texture("1", ibmp.modLoc(STRIPPED_SMALL_LOG.location(base) + "logs/" + "stripped_small_logs"));

			ibmp.withExistingParent(STRIPPED_SMALL_LOG.location(base) + "stripped_small_log/" + s + "_vertical",
					ibmp.modLoc("block/bases/stripped_small_log/" + s + "_vertical"))
					.texture("0", ibmp.modLoc(STRIPPED_SMALL_LOG.location(base) + "logs/" + "stripped_extra_caps"))
					.texture("1", ibmp.modLoc(STRIPPED_SMALL_LOG.location(base) + "logs/" + "stripped_small_logs"));

			ibmp.withExistingParent(STRIPPED_SMALL_LOG.location(base) + "stripped_small_log/" + s + "_cap",
					ibmp.modLoc("block/bases/stripped_small_log/" + s + "_cap"))
					.texture("0", ibmp.modLoc(STRIPPED_SMALL_LOG.location(base) + "logs/" + "stripped_extra_caps"))
					.texture("1", ibmp.modLoc(STRIPPED_SMALL_LOG.location(base) + "logs/" + "stripped_small_logs"));

			ibmp.withExistingParent("compendium:" + base.itemFolder() + s + "_inventory",
					ibmp.mcLoc("item/" + s + "_inventory"))
					.texture("0", ibmp.modLoc(SMALL_LOG.location(base) + "logs/extra_caps"))
					.texture("1", ibmp.modLoc(SMALL_LOG.location(base) + "logs/small_logs"));

			ibmp.withExistingParent(base.itemFolder() + "stripped_" + s + "_inventory",
					ibmp.modLoc("item/" + s + "_inventory"))
					.texture("0", ibmp.modLoc(STRIPPED_SMALL_LOG.location(base) + "logs/stripped_extra_caps"))
					.texture("1", ibmp.modLoc(STRIPPED_SMALL_LOG.location(base) + "logs/stripped_small_logs"));

		}

//		ibmp.withExistingParent(base.itemFolder() + "small_log_inventory", ibmp.modLoc("item/small_logs_inventory"))
//		.texture("0", ibmp.modLoc(SMALL_LOG.location(base) + "logs/" + "small_logs_corner"));
//		
//		ibmp.withExistingParent(base.itemFolder() + "stripped_small_log_inventory",
//				ibmp.modLoc("item/small_log_inventory")).texture("0",
//						ibmp.modLoc(STRIPPED_SMALL_LOG.location(base) + "logs/" + "stripped_small_logs_corner"));

		ibmp.withExistingParent(LOG.location(base) + "/log/basic", ibmp.mcLoc("block/cube_column"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs_top"));
		ibmp.withExistingParent(LOG.location(base) + "/log/basic_inventory", ibmp.mcLoc("block/cube_column"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs_top"));

		ibmp.withExistingParent(LOG.location(base) + "/log/basic_horizontal",
				ibmp.mcLoc("block/cube_column_horizontal"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs_top"));
		ibmp.withExistingParent(LOG.location(base) + "/log/basic_horizontal_inventory",
				ibmp.mcLoc("block/cube_column_horizontal"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs_top"));

		ibmp.withExistingParent(LOG.location(base) + "/log/corner",
				ibmp.modLoc("block/bases/small_log/small_logs_corner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs_corner"));
		ibmp.withExistingParent(LOG.location(base) + "/log/corner_inventory",
				ibmp.modLoc("block/bases/small_log/small_logs_corner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs_corner"));

		ibmp.withExistingParent(LOG.location(base) + "/log/corner_horizontal",
				ibmp.modLoc("block/bases/small_log/small_logs_corner_horizontal"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs_corner"));
		ibmp.withExistingParent(LOG.location(base) + "/log/corner_horizontal_inventory",
				ibmp.modLoc("block/bases/small_log/small_logs_corner_horizontal"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs_corner"));

		ibmp.withExistingParent(LOG.location(base) + "/log/small_wood", ibmp.mcLoc("block/cube_column"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"));
		ibmp.withExistingParent(LOG.location(base) + "/log/small_wood_inventory", ibmp.mcLoc("block/cube_column"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"));

		ibmp.withExistingParent(LOG.location(base) + "/log/small_wood_horizontal",
				ibmp.mcLoc("block/cube_column_horizontal"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"));
		ibmp.withExistingParent(LOG.location(base) + "/log/small_wood_horizontal_inventory",
				ibmp.mcLoc("block/cube_column_horizontal"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "small_logs"));

		String[] shreds = { "1", "2" };
		for (String shred : shreds) {
			ibmp.withExistingParent(LOG.location(base) + "/log/bark_shred_" + shred,
					ibmp.modLoc("block/cube_column_ends"))
					.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "bark_shred_" + shred))
					.texture("bottom", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"))
					.texture("top", mcLoc("block/" + base.name + "_" + logstem + "_top"));
			ibmp.withExistingParent(LOG.location(base) + "/log/bark_shred_" + shred + "_inventory",
					ibmp.modLoc("block/cube_column_ends"))
					.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "bark_shred_" + shred))
					.texture("bottom", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"))
					.texture("top", mcLoc("block/" + base.name + "_" + logstem + "_top"));

			ibmp.withExistingParent(LOG.location(base) + "/log/bark_shred_" + shred + "_horizontal",
					ibmp.modLoc("block/cube_column_ends"))
					.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "bark_shred_" + shred))
					.texture("bottom", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"))
					.texture("top", mcLoc("block/" + base.name + "_" + logstem + "_top"));
			ibmp.withExistingParent(LOG.location(base) + "/log/bark_shred_" + shred + "_horizontal_inventory",
					ibmp.modLoc("block/cube_column_ends"))
					.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "bark_shred_" + shred))
					.texture("bottom", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"))
					.texture("top", mcLoc("block/" + base.name + "_" + logstem + "_top"));

			ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/bark_shred_" + shred,
					ibmp.modLoc("block/cube_column_ends"))
					.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_bark_shred_" + shred))
					.texture("top", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"))
					.texture("bottom", mcLoc("block/" + base.name + "_" + logstem + "_top"));
			ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/bark_shred_" + shred + "_inventory",
					ibmp.modLoc("block/cube_column_ends"))
					.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_bark_shred_" + shred))
					.texture("top", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"))
					.texture("bottom", mcLoc("block/" + base.name + "_" + logstem + "_top"));

			ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/bark_shred_" + shred + "_horizontal",
					ibmp.modLoc("block/cube_column_ends"))
					.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_bark_shred_" + shred))
					.texture("top", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"))
					.texture("bottom", mcLoc("block/" + base.name + "_" + logstem + "_top"));
			ibmp.withExistingParent(
					STRIPPED_LOG.location(base) + "/stripped_log/bark_shred_" + shred + "_horizontal_inventory",
					ibmp.modLoc("block/cube_column_ends"))
					.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_bark_shred_" + shred))
					.texture("top", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"))
					.texture("bottom", mcLoc("block/" + base.name + "_" + logstem + "_top"));
		}
		shreds = new String[] { "3", "4" };
		for (String shred : shreds) {
			ibmp.withExistingParent(LOG.location(base) + "/log/bark_shred_" + shred,
					ibmp.modLoc("block/cube_column_ends"))
					.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "bark_shred_" + shred))
					.texture("bottom", mcLoc("block/" + base.name + "_" + logstem + "_top"))
					.texture("top", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"));
			ibmp.withExistingParent(LOG.location(base) + "/log/bark_shred_" + shred + "_inventory",
					ibmp.modLoc("block/cube_column_ends"))
					.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "bark_shred_" + shred))
					.texture("bottom", mcLoc("block/" + base.name + "_" + logstem + "_top"))
					.texture("top", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"));

			ibmp.withExistingParent(LOG.location(base) + "/log/bark_shred_" + shred + "_horizontal",
					ibmp.modLoc("block/cube_column_ends"))
					.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "bark_shred_" + shred))
					.texture("bottom", mcLoc("block/" + base.name + "_" + logstem + "_top"))
					.texture("top", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"));
			ibmp.withExistingParent(LOG.location(base) + "/log/bark_shred_" + shred + "_horizontal_inventory",
					ibmp.modLoc("block/cube_column_ends"))
					.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "bark_shred_" + shred))
					.texture("bottom", mcLoc("block/" + base.name + "_" + logstem + "_top"))
					.texture("top", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"));

			ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/bark_shred_" + shred,
					ibmp.modLoc("block/cube_column_ends"))
					.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_bark_shred_" + shred))
					.texture("top", mcLoc("block/" + base.name + "_" + logstem + "_top"))
					.texture("bottom", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"));
			ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/bark_shred_" + shred + "_inventory",
					ibmp.modLoc("block/cube_column_ends"))
					.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_bark_shred_" + shred))
					.texture("top", mcLoc("block/" + base.name + "_" + logstem + "_top"))
					.texture("bottom", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"));

			ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/bark_shred_" + shred + "_horizontal",
					ibmp.modLoc("block/cube_column_ends"))
					.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_bark_shred_" + shred))
					.texture("top", mcLoc("block/" + base.name + "_" + logstem + "_top"))
					.texture("bottom", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"));
			ibmp.withExistingParent(
					STRIPPED_LOG.location(base) + "/stripped_log/bark_shred_" + shred + "_horizontal_inventory",
					ibmp.modLoc("block/cube_column_ends"))
					.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_bark_shred_" + shred))
					.texture("top", mcLoc("block/" + base.name + "_" + logstem + "_top"))
					.texture("bottom", mcLoc("block/stripped_" + base.name + "_" + logstem + "_top"));
		}

		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/basic", ibmp.mcLoc("block/cube_column"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs_top"));
		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/basic_inventory",
				ibmp.mcLoc("block/cube_column"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs_top"));
		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/basic_horizontal",
				ibmp.mcLoc("block/cube_column_horizontal"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs_top"));
		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/basic_horizontal_inventory",
				ibmp.mcLoc("block/cube_column_horizontal"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs_top"));

		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/corner",
				ibmp.modLoc("block/bases/small_log/small_logs_corner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs_corner"));
		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/corner_inventory",
				ibmp.modLoc("block/bases/small_log/small_logs_corner"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs_corner"));
		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/corner_horizontal",
				ibmp.modLoc("block/bases/small_log/small_logs_corner_horizontal"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs_corner"));
		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/corner_horizontal_inventory",
				ibmp.modLoc("block/bases/small_log/small_logs_corner_horizontal"))
				.texture("1", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"))
				.texture("2", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs_corner"));

		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/small_wood",
				ibmp.mcLoc("block/cube_column"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"));
		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/small_wood_inventory",
				ibmp.mcLoc("block/cube_column"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"));
		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/small_wood_horizontal",
				ibmp.mcLoc("block/cube_column_horizontal"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"));
		ibmp.withExistingParent(STRIPPED_LOG.location(base) + "/stripped_log/small_wood_horizontal_inventory",
				ibmp.mcLoc("block/cube_column_horizontal"))
				.texture("side", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"))
				.texture("end", ibmp.modLoc(LOG.location(base) + "logs/" + "stripped_small_logs"));

		logSlabBlockModel(base, ibmp);
		ibmp.withExistingParent(LOG.location(base) + "/log_slab/trellis_bottom",
				ibmp.modLoc("block/trellis/trellis_bottom"))
				.texture("0", ibmp.mcLoc("block/" + base.name + "_" + logstem));
		ibmp.withExistingParent(LOG.location(base) + "/log_slab/trellis_top", ibmp.modLoc("block/trellis/trellis_top"))
				.texture("0", ibmp.mcLoc("block/" + base.name + "_" + logstem));
		ibmp.withExistingParent(LOG.location(base) + "/log_slab/trellis_full",
				ibmp.modLoc("block/trellis/trellis_full"))
				.texture("0", ibmp.mcLoc("block/" + base.name + "_" + logstem));
		ibmp.withExistingParent(LOG.location(base) + "/log_slab/trellis_inventory",
				ibmp.modLoc("block/trellis/trellis_bottom"))
				.texture("0", ibmp.mcLoc("block/" + base.name + "_" + logstem));
		ibmp.withExistingParent(LOG.location(base) + "/stripped_log_slab/trellis_bottom",
				ibmp.modLoc("block/trellis/trellis_bottom"))
				.texture("0", ibmp.mcLoc("block/stripped_" + base.name + "_" + logstem));
		ibmp.withExistingParent(LOG.location(base) + "/stripped_log_slab/trellis_top",
				ibmp.modLoc("block/trellis/trellis_top"))
				.texture("0", ibmp.mcLoc("block/stripped_" + base.name + "_" + logstem));
		ibmp.withExistingParent(LOG.location(base) + "/stripped_log_slab/trellis_full",
				ibmp.modLoc("block/trellis/trellis_full"))
				.texture("0", ibmp.mcLoc("block/stripped_" + base.name + "_" + logstem));
		ibmp.withExistingParent(LOG.location(base) + "/stripped_log_slab/trellis_inventory",
				ibmp.modLoc("block/trellis/trellis_bottom"))
				.texture("0", ibmp.mcLoc("block/stripped_" + base.name + "_" + logstem));

		logStairsBlockModel(base, ibmp);

		ibmp.withExistingParent(base.extraFolder() + "split_log_stage0", ibmp.modLoc("extra/split_log_stage0"))
				.texture("0", ibmp.mcLoc("block/" + base.name + "_" + logstem))
				.texture("1", ibmp.mcLoc("block/" + base.name + "_" + logstem + "_top"));

		ibmp.withExistingParent(base.extraFolder() + "split_log_stage1", ibmp.modLoc("extra/split_log_stage1"))
				.texture("0", ibmp.mcLoc("block/" + base.name + "_" + logstem))
				.texture("1", ibmp.mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("2", ibmp.modLoc(base.blockFolder() + "logs/log_split_side"));

		ibmp.withExistingParent(base.extraFolder() + "split_log_stage2", ibmp.modLoc("extra/split_log_stage2"))
				.texture("0", ibmp.mcLoc("block/" + base.name + "_" + logstem))
				.texture("1", ibmp.mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("2", ibmp.modLoc(base.blockFolder() + "logs/log_split_side"));

		ibmp.withExistingParent(base.extraFolder() + "split_log_stage3", ibmp.modLoc("extra/split_log_stage3"))
				.texture("0", ibmp.mcLoc("block/" + base.name + "_" + logstem))
				.texture("1", ibmp.mcLoc("block/" + base.name + "_" + logstem + "_top"))
				.texture("2", ibmp.modLoc(base.blockFolder() + "logs/log_split_side"));
	}

	private void logSlabBlockModel(_MaterialBase base, IndexBlockModelProvider ibmp) {
		styledModel(LOG_SLAB.location(base), base, ibmp, "log_slab", "small_logs", "small_logs", "small_logs_top",
				true);
		styledModel(LOG_SLAB.location(base), base, ibmp, "log_slab", "small_logs_rotated", "small_logs",
				"small_logs_top", true);
		styledModel(LOG_SLAB.location(base), base, ibmp, "log_slab", "split", "log", "log_top", "log_split_side", true);
		styledModel(LOG_SLAB.location(base), base, ibmp, "log_slab", "split_rotated", "log", "log_top",
				"log_split_side", true);
		styledModel(LOG_SLAB.location(base), base, ibmp, "log_slab", "crosscut", "log", "log_top", true);
		styledModel(LOG_SLAB.location(base), base, ibmp, "log_slab", "crosscut_small", "small_logs", "small_logs_top",
				true);
		styledModel(LOG_SLAB.location(base), base, ibmp, "log_slab", "small_wood", "small_logs", "small_logs", true,
				"small_logs");
		styledModel(LOG_SLAB.location(base), base, ibmp, "log_slab", "small_wood_rotated", "small_logs", "small_logs",
				true, "small_logs_rotated");
		styledModel(LOG_SLAB.location(base), base, ibmp, "log_slab", "wood", "log", "log", true, "small_logs");
		styledModel(LOG_SLAB.location(base), base, ibmp, "log_slab", "wood_rotated", "log", "log", true,
				"small_logs_rotated");
		styledModel(LOG_SLAB.location(base), base, ibmp, "log_slab", "campfire", "log", "extra_caps", true);
		styledModel(LOG_SLAB.location(base), base, ibmp, "log_slab", "firewood", "log", "log_top", "log_split_side",
				true);
		styledModel(LOG_SLAB.location(base), base, ibmp, "log_slab", "smaller_logs", "log", "extra_caps", true);
		styledModel(LOG_SLAB.location(base), base, ibmp, "log_slab", "smaller_logs_rotated", "log", "extra_caps", true);
		styledModel(LOG_SLAB.location(base), base, ibmp, "log_slab", "smallest_logs", "log", "extra_caps", true);
		styledModel(LOG_SLAB.location(base), base, ibmp, "log_slab", "smallest_logs_rotated", "log", "extra_caps",
				true);
	}

	private void logStairsBlockModel(_MaterialBase base, IndexBlockModelProvider ibmp) {
		styledModel(LOG_STAIRS.location(base), base, ibmp, "log_stairs", "small_logs", "small_logs", "small_logs_top",
				true);
		styledModel(LOG_STAIRS.location(base), base, ibmp, "log_stairs", "small_logs_rotated_side", "small_logs",
				"small_logs_top", true);
		styledModel(LOG_STAIRS.location(base), base, ibmp, "log_stairs", "small_logs_rotated_front", "small_logs",
				"small_logs_top", true);
		styledModel(LOG_STAIRS.location(base), base, ibmp, "log_stairs", "small_logs_rotated_top", "small_logs",
				"small_logs_top", true);
		styledModel(LOG_STAIRS.location(base), base, ibmp, "log_stairs", "split_log_rotated_side", "log", "log_top",
				"log_split_side", true);
		styledModel(LOG_STAIRS.location(base), base, ibmp, "log_stairs", "split_log_rotated_front", "log", "log_top",
				"log_split_side", true);
		styledModel(LOG_STAIRS.location(base), base, ibmp, "log_stairs", "split_log_rotated_top", "log", "log_top",
				"log_split_side", true);
		styledModel(LOG_STAIRS.location(base), base, ibmp, "log_stairs", "small_wood", "small_logs", "small_logs", true,
				"small_logs_rotated_side");
		styledModel(LOG_STAIRS.location(base), base, ibmp, "log_stairs", "small_wood_rotated", "small_logs",
				"small_logs", true, "small_logs_rotated_front");
		styledModel(LOG_STAIRS.location(base), base, ibmp, "log_stairs", "wood", "log", "log", true,
				"small_logs_rotated_side");
		styledModel(LOG_STAIRS.location(base), base, ibmp, "log_stairs", "wood_rotated", "log", "log", true,
				"small_logs_rotated_front");
	}

	private void styledModel(String location, _MaterialBase base, IndexBlockModelProvider ibmp, String block,
			String modelName, String texture0, String texture1, Boolean stripped) {
		styledModel(location, base, ibmp, block, modelName, texture0, texture1, stripped, modelName);
	}

	private void styledModel(String location, _MaterialBase base, IndexBlockModelProvider ibmp, String block,
			String modelName, String texture0, String texture1, Boolean stripped, String modelSource) {
		String[] types = new String[0];
		if (block.equals("log_slab")) {
			types = new String[] { "_bottom", "_top", "_full" };
		} else if (block.equals("log_stairs")) {
			types = new String[] { "", "_inner", "_outer" };
		}

		for (String type : types) {
			ibmp.withExistingParent(location + "/" + block + "/" + modelName + type,
					ibmp.modLoc("block/bases/" + block + "/" + modelSource + type))
					.texture("0", textureLocation(texture0, base, false))
					.texture("1", textureLocation(texture1, base, false))
					.texture("particle", textureLocation(texture0, base, false));
			if (type.equals("_bottom") || type.equals("")) {
				ibmp.withExistingParent(location + "/" + block + "/" + modelName + "_inventory",
						ibmp.modLoc("block/bases/" + block + "/" + modelSource + type))
						.texture("0", textureLocation(texture0, base, false))
						.texture("1", textureLocation(texture1, base, false))
						.texture("particle", textureLocation(texture0, base, false));
			}

			if (stripped) {
				ibmp.withExistingParent(location + "/stripped_" + block + "/" + modelName + type,
						ibmp.modLoc("block/bases/" + block + "/" + modelSource + type))
						.texture("0", textureLocation(texture0, base, true))
						.texture("1", textureLocation(texture1, base, true))
						.texture("particle", textureLocation(texture0, base, true));
				if (type.equals("_bottom") || type.equals("")) {
					ibmp.withExistingParent(location + "/stripped_" + block + "/" + modelName + "_inventory",
							ibmp.modLoc("block/bases/" + block + "/" + modelSource + type))
							.texture("0", textureLocation(texture0, base, true))
							.texture("1", textureLocation(texture1, base, true))
							.texture("particle", textureLocation(texture0, base, true));
				}
			}
		}
	}

	private void styledModel(String location, _MaterialBase base, IndexBlockModelProvider ibmp, String block,
			String modelName, String texture0, String texture1, String texture2, Boolean stripped) {
		String[] types = new String[0];
		if (block.equals("log_slab")) {
			types = new String[] { "_bottom", "_top", "_full" };
		} else if (block.equals("log_stairs")) {
			types = new String[] { "", "_inner", "_outer" };
		}

		for (String type : types) {
			ibmp.withExistingParent(location + "/" + block + "/" + modelName + type,
					ibmp.modLoc("block/bases/" + block + "/" + modelName + type))
					.texture("0", textureLocation(texture0, base, false))
					.texture("1", textureLocation(texture1, base, false))
					.texture("2", textureLocation(texture2, base, false))
					.texture("particle", textureLocation(texture0, base, false));
			if (type.equals("_bottom") || type.equals("")) {
				ibmp.withExistingParent(location + "/" + block + "/" + modelName + "_inventory",
						ibmp.modLoc("block/bases/" + block + "/" + modelName + type))
						.texture("0", textureLocation(texture0, base, false))
						.texture("1", textureLocation(texture1, base, false))
						.texture("2", textureLocation(texture2, base, false))
						.texture("particle", textureLocation(texture0, base, false));
			}

			if (stripped) {
				ibmp.withExistingParent(location + "/stripped_" + block + "/" + modelName + type,
						ibmp.modLoc("block/bases/" + block + "/" + modelName + type))
						.texture("0", textureLocation(texture0, base, true))
						.texture("1", textureLocation(texture1, base, true))
						.texture("2", textureLocation(texture2, base, true))
						.texture("particle", textureLocation(texture0, base, true));
				if (type.equals("_bottom") || type.equals("")) {
					ibmp.withExistingParent(location + "/stripped_" + block + "/" + modelName + "_inventory",
							ibmp.modLoc("block/bases/" + block + "/" + modelName + type))
							.texture("0", textureLocation(texture0, base, true))
							.texture("1", textureLocation(texture1, base, true))
							.texture("2", textureLocation(texture2, base, true))
							.texture("particle", textureLocation(texture0, base, true));
				}
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

				StyleBlockModelBuilder<BlockModelBuilder> msmb = bsp.models().getBuilder(base.extraFolder() + "log")
						.customLoader(StyleBlockModelBuilder::begin);
				msmb.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG.getTypes())
					msmb.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "log/" + s.toLowerCase() + "_inventory")));

				ConfiguredModel.builder().modelFile(msmb.end()).build();
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
						return b.rotationX(90).build();
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

				StyleBlockModelBuilder<BlockModelBuilder> msmb = bsp.models()
						.getBuilder(base.extraFolder() + "stripped_log").customLoader(StyleBlockModelBuilder::begin);
				msmb.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG.getTypes())
					msmb.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "stripped_log/" + s.toLowerCase() + "_inventory")));

				ConfiguredModel.builder().modelFile(msmb.end()).build();
			}
			if (LOG_SLAB.shouldGenerate()) {
				StyleBlockModelBuilder<BlockModelBuilder> log_slab_bottom = bsp.models()
						.getBuilder(LOG.location(base) + "log_slab_bottom").customLoader(StyleBlockModelBuilder::begin);
				log_slab_bottom.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_bottom.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "log_slab/" + s.toLowerCase() + "_bottom")));

				StyleBlockModelBuilder<BlockModelBuilder> log_slab_top = bsp.models()
						.getBuilder(LOG.location(base) + "log_slab_top").customLoader(StyleBlockModelBuilder::begin);
				log_slab_top.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_top.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "log_slab/" + s.toLowerCase() + "_top")));

				StyleBlockModelBuilder<BlockModelBuilder> log_slab_full = bsp.models()
						.getBuilder(LOG.location(base) + "log_slab_full").customLoader(StyleBlockModelBuilder::begin);
				log_slab_full.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_full.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "log_slab/" + s.toLowerCase() + "_full")));

				bsp.slabBlock((SlabBlock) LOG_SLAB.BLOCK.get(), log_slab_bottom.end(), log_slab_top.end(),
						log_slab_full.end());

				StyleBlockModelBuilder<BlockModelBuilder> log_slab_inventory = bsp.models()
						.getBuilder(base.extraFolder() + "log_slab").customLoader(StyleBlockModelBuilder::begin);
				log_slab_inventory.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_inventory.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "log_slab/" + s.toLowerCase() + "_inventory")));

				ConfiguredModel.builder().modelFile(log_slab_inventory.end()).build();
			}

			if (STRIPPED_LOG_SLAB.shouldGenerate()) {
				StyleBlockModelBuilder<BlockModelBuilder> log_slab_bottom = bsp.models()
						.getBuilder(LOG.location(base) + "stripped_log_slab_bottom")
						.customLoader(StyleBlockModelBuilder::begin);
				log_slab_bottom.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_bottom.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "stripped_log_slab/" + s.toLowerCase() + "_bottom")));

				StyleBlockModelBuilder<BlockModelBuilder> log_slab_top = bsp.models()
						.getBuilder(LOG.location(base) + "stripped_log_slab_top")
						.customLoader(StyleBlockModelBuilder::begin);
				log_slab_top.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_top.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "stripped_log_slab/" + s.toLowerCase() + "_top")));

				StyleBlockModelBuilder<BlockModelBuilder> log_slab_full = bsp.models()
						.getBuilder(LOG.location(base) + "stripped_log_slab_full")
						.customLoader(StyleBlockModelBuilder::begin);
				log_slab_full.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_full.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "stripped_log_slab/" + s.toLowerCase() + "_full")));

				bsp.slabBlock((SlabBlock) STRIPPED_LOG_SLAB.BLOCK.get(), log_slab_bottom.end(), log_slab_top.end(),
						log_slab_full.end());

				StyleBlockModelBuilder<BlockModelBuilder> log_slab_inventory = bsp.models()
						.getBuilder(base.extraFolder() + "stripped_log_slab")
						.customLoader(StyleBlockModelBuilder::begin);
				log_slab_inventory.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_SLAB.getTypes())
					log_slab_inventory.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "stripped_log_slab/" + s.toLowerCase() + "_inventory")));

				ConfiguredModel.builder().modelFile(log_slab_inventory.end()).build();
			}
			if (LOG_STAIRS.shouldGenerate()) {
				StyleBlockModelBuilder<BlockModelBuilder> log_stairs_standard = bsp.models()
						.getBuilder(LOG.location(base) + "log_stairs").customLoader(StyleBlockModelBuilder::begin);
				log_stairs_standard.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_STAIRS.getTypes())
					log_stairs_standard.add(
							new StyleModelBuilder(s, bsp.modLoc(LOG.location(base) + "log_stairs/" + s.toLowerCase())));

				StyleBlockModelBuilder<BlockModelBuilder> log_stairs_inner = bsp.models()
						.getBuilder(LOG.location(base) + "log_stairs_inner")
						.customLoader(StyleBlockModelBuilder::begin);
				log_stairs_inner.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_STAIRS.getTypes())
					log_stairs_inner.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "log_stairs/" + s.toLowerCase() + "_inner")));

				StyleBlockModelBuilder<BlockModelBuilder> log_stairs_outer = bsp.models()
						.getBuilder(LOG.location(base) + "log_stairs_outer")
						.customLoader(StyleBlockModelBuilder::begin);
				log_stairs_outer.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_STAIRS.getTypes())
					log_stairs_outer.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "log_stairs/" + s.toLowerCase() + "_outer")));

				stairsBlock((StairBlock) LOG_STAIRS.BLOCK.get(), log_stairs_standard.end(), log_stairs_inner.end(),
						log_stairs_outer.end(), bsp);

				StyleBlockModelBuilder<BlockModelBuilder> log_stairs_inventory = bsp.models()
						.getBuilder(base.extraFolder() + "log_stairs").customLoader(StyleBlockModelBuilder::begin);
				log_stairs_inventory.base(bsp.models().stairs("stairs_base", bsp.mcLoc("block/oak_planks"),
						bsp.mcLoc("block/oak_planks"), bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_STAIRS.getTypes())
					log_stairs_inventory.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "log_stairs/" + s.toLowerCase() + "_inventory")));

				ConfiguredModel.builder().modelFile(log_stairs_inventory.end()).build();
			}

			if (STRIPPED_LOG_STAIRS.shouldGenerate()) {
				StyleBlockModelBuilder<BlockModelBuilder> log_stairs_standard = bsp.models()
						.getBuilder(LOG.location(base) + "stripped_log_stairs")
						.customLoader(StyleBlockModelBuilder::begin);
				log_stairs_standard.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_STAIRS.getTypes())
					log_stairs_standard.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "stripped_log_stairs/" + s.toLowerCase())));

				StyleBlockModelBuilder<BlockModelBuilder> log_stairs_inner = bsp.models()
						.getBuilder(LOG.location(base) + "stripped_log_stairs_inner")
						.customLoader(StyleBlockModelBuilder::begin);
				log_stairs_inner.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_STAIRS.getTypes())
					log_stairs_inner.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "stripped_log_stairs/" + s.toLowerCase() + "_inner")));

				StyleBlockModelBuilder<BlockModelBuilder> log_stairs_outer = bsp.models()
						.getBuilder(LOG.location(base) + "stripped_log_stairs_outer")
						.customLoader(StyleBlockModelBuilder::begin);
				log_stairs_outer.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_STAIRS.getTypes())
					log_stairs_outer.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "stripped_log_stairs/" + s.toLowerCase() + "_outer")));

				stairsBlock((StairBlock) STRIPPED_LOG_STAIRS.BLOCK.get(), log_stairs_standard.end(),
						log_stairs_inner.end(), log_stairs_outer.end(), bsp);

				StyleBlockModelBuilder<BlockModelBuilder> log_stairs_inventory = bsp.models()
						.getBuilder(base.extraFolder() + "stripped_log_stairs")
						.customLoader(StyleBlockModelBuilder::begin);
				log_stairs_inventory.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.LOG_STAIRS.getTypes())
					log_stairs_inventory.add(new StyleModelBuilder(s,
							bsp.modLoc(LOG.location(base) + "stripped_log_stairs/" + s.toLowerCase() + "_inventory")));

				ConfiguredModel.builder().modelFile(log_stairs_inventory.end()).build();
			}
		}
	}

	private void smallLogsModel(CompendiumBlockHandler block, _MaterialBase base, BlockStateProvider bsp,
			String extra) {
		if (block.shouldGenerate()) {
			extra = extra + "small_log";
			StyleBlockModelBuilder<BlockModelBuilder> base_model_horizontal = bsp.models()
					.getBuilder(block.location(base) + extra + "horizontal")
					.customLoader(StyleBlockModelBuilder::begin);
			base_model_horizontal.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

			for (String s : StyleData.SMALL_LOG.getTypes())
				base_model_horizontal.add(new StyleModelBuilder(s,
						bsp.modLoc(LOG.location(base) + extra + "/" + s.toLowerCase() + "_horizontal")));

			StyleBlockModelBuilder<BlockModelBuilder> base_model_horizontal2 = bsp.models()
					.getBuilder(block.location(base) + extra + "horizontal2")
					.customLoader(StyleBlockModelBuilder::begin);
			base_model_horizontal2.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

			for (String s : StyleData.SMALL_LOG.getTypes())
				base_model_horizontal2.add(new StyleModelBuilder(s,
						bsp.modLoc(LOG.location(base) + extra + "/" + s.toLowerCase() + "_horizontal_rot")));

//			BlockModelBuilder base_model_vertical = bsp.models()
//					.withExistingParent(base.blockFolder() + extra + "vertical", bsp.modLoc("block/small_log_vertical"))
//					.texture("0", bsp.modLoc(base.blockFolder() + "logs/" + extra + "small_logs_corner"));

			StyleBlockModelBuilder<BlockModelBuilder> base_model_vertical = bsp.models()
					.getBuilder(block.location(base) + extra + "vertical").customLoader(StyleBlockModelBuilder::begin);
			base_model_vertical.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

			for (String s : StyleData.SMALL_LOG.getTypes())
				base_model_vertical.add(new StyleModelBuilder(s,
						bsp.modLoc(LOG.location(base) + extra + "/" + s.toLowerCase() + "_vertical")));

//			BlockModelBuilder model_cap = bsp.models()
//					.withExistingParent(block.location(base) + extra + "cap", bsp.modLoc("block/small_log_cap"))
//					.texture("0", bsp.modLoc(base.blockFolder() + "logs/" + extra + "small_logs_corner"));

			StyleBlockModelBuilder<BlockModelBuilder> model_cap = bsp.models()
					.getBuilder(block.location(base) + extra + "cap").customLoader(StyleBlockModelBuilder::begin);
			model_cap.base(bsp.models().cubeAll("log_base", bsp.mcLoc("block/oak_planks")));

			for (String s : StyleData.SMALL_LOG.getTypes())
				model_cap.add(new StyleModelBuilder(s,
						bsp.modLoc(LOG.location(base) + extra + "/" + s.toLowerCase() + "_cap")));

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
				DataUtil.basicMaterial3DItem(tmp, SMALL_LOG.BLOCK_ITEM.get(), base, Compendium.modLoc("item/small_log"),
						base.getType(), tmp.modLoc("block/" + base.itemFolder() + "small_logs_corner"));
			}
			if (LOG.shouldGenerate()) {
				tmp.withExistingParent(LOG.BLOCK_ITEM.getRegisteredName(), tmp.modLoc("item/window"));
			}
			if (LOG_SLAB.shouldGenerate()) {
				tmp.withExistingParent(LOG_SLAB.BLOCK_ITEM.getRegisteredName(), tmp.modLoc("item/window"));
			}
			if (LOG_STAIRS.shouldGenerate()) {
				tmp.withExistingParent(LOG_STAIRS.BLOCK_ITEM.getRegisteredName(), tmp.modLoc("item/window"));
			}

			if (STRIPPED_SMALL_LOG.shouldGenerate()) {
				DataUtil.basicMaterial3DItem(tmp, SMALL_LOG.BLOCK_ITEM.get(), base, Compendium.modLoc("item/small_log"),
						base.getType(), tmp.modLoc("block/" + base.itemFolder() + "stripped_small_logs_corner"));
			}
			if (STRIPPED_LOG.shouldGenerate()) {
				tmp.withExistingParent(STRIPPED_LOG.BLOCK_ITEM.getRegisteredName(), tmp.modLoc("item/window"));
			}
			if (STRIPPED_LOG_SLAB.shouldGenerate()) {
				tmp.withExistingParent(STRIPPED_LOG_SLAB.BLOCK_ITEM.getRegisteredName(), tmp.modLoc("item/window"));
			}
			if (STRIPPED_LOG_STAIRS.shouldGenerate()) {
				tmp.withExistingParent(STRIPPED_LOG_STAIRS.BLOCK_ITEM.getRegisteredName(), tmp.modLoc("item/window"));
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
		String logstem;
		if (base.name.equals("warped") || base.name.equals("crimson")) {
			logstem = "stem";
		} else {
			logstem = "log";
		}

		if (SMALL_LOG.shouldGenerate()) {
			SawBuckRecipeBuilder.saw(
					Ingredient.of(ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", "logs/" + base.name))),
//							BuiltInRegistries.ITEM
//							.get(ResourceLocation.fromNamespaceAndPath(base.namespace, base.name + "_" + logstem))),
					new ItemStack(SMALL_LOG.BLOCK_ITEM.get(), 4), Vec3.ZERO)
					.tool(Ingredient.of(ItemTags.AXES), 1, true, RecipeLootTables.SAW_DUST, List.of(),
							Recipes.standardSawBuckAxeModel(mcLoc("iron_axe"), 0),
							Recipes.standardSawBuckBlockModel(TagUtil.modLoc(base.extraFolder() + "split_log_stage0"),
									0))
					.tool(Ingredient.of(ItemTags.AXES), 1, true, RecipeLootTables.SAW_DUST, List.of(),
							Recipes.standardSawBuckAxeModel(mcLoc("iron_axe"), 0),
							Recipes.standardSawBuckBlockModel(TagUtil.modLoc(base.extraFolder() + "split_log_stage1"),
									0))
					.tool(Ingredient.of(ItemTags.AXES), 1, true, RecipeLootTables.SAW_DUST, List.of(),
							Recipes.standardSawBuckAxeModel(mcLoc("iron_axe"), 0), Recipes.standardSawBuckBlockModel(
									TagUtil.modLoc(base.extraFolder() + "split_log_stage2"), 0))
					.save(consumer, base.name + "_small_log");
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
					.save(consumer, base.name + "_stripped_small_log");
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
			blp.add(SMALL_LOG.BLOCK.get(), BlockLootTables.createStyleItemDrop(SMALL_LOG.BLOCK.get()));
		}
		if (LOG.shouldGenerate()) {
			blp.add(this.LOG.BLOCK.get(), BlockLootTables.createStyleItemDrop(LOG.BLOCK.get()));
		}
		if (LOG_SLAB.shouldGenerate()) {
			blp.add(LOG_SLAB.BLOCK.get(), this.createSlabItemTable(this.LOG_SLAB.BLOCK.get()));
		}
		if (LOG_STAIRS.shouldGenerate()) {
			blp.add(this.LOG_STAIRS.BLOCK.get(), BlockLootTables.createStyleItemDrop(LOG_STAIRS.BLOCK.get()));
		}

		if (STRIPPED_SMALL_LOG.shouldGenerate()) {
			blp.add(STRIPPED_SMALL_LOG.BLOCK.get(),
					BlockLootTables.createStyleItemDrop(STRIPPED_SMALL_LOG.BLOCK.get()));
		}
		if (STRIPPED_LOG.shouldGenerate()) {
			blp.add(this.STRIPPED_LOG.BLOCK.get(), BlockLootTables.createStyleItemDrop(STRIPPED_LOG.BLOCK.get()));
		}
		if (STRIPPED_LOG_SLAB.shouldGenerate()) {
			blp.add(STRIPPED_LOG_SLAB.BLOCK.get(), this.createSlabItemTable(this.STRIPPED_LOG_SLAB.BLOCK.get()));
		}
		if (STRIPPED_LOG_STAIRS.shouldGenerate()) {
			blp.add(this.STRIPPED_LOG_STAIRS.BLOCK.get(),
					BlockLootTables.createStyleItemDrop(STRIPPED_LOG_STAIRS.BLOCK.get()));
		}

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
		if (!SMALL_LOG.isIgnored()) {
			SMALL_LOG.itemTag(itp);
		}
		if (!LOG.isIgnored()) {
			LOG.itemTag(itp);
		}
		if (!LOG_SLAB.isIgnored()) {
			LOG_SLAB.itemTag(itp);
		}
		if (!LOG_STAIRS.isIgnored()) {
			LOG_STAIRS.itemTag(itp);
		}

		if (!STRIPPED_SMALL_LOG.isIgnored()) {
			STRIPPED_SMALL_LOG.itemTag(itp);
		}
		if (!STRIPPED_LOG.isIgnored()) {
			STRIPPED_LOG.itemTag(itp);
		}
		if (!STRIPPED_LOG_SLAB.isIgnored()) {
			STRIPPED_LOG_SLAB.itemTag(itp);
		}
		if (!STRIPPED_LOG_STAIRS.isIgnored()) {
			STRIPPED_LOG_STAIRS.itemTag(itp);
		}
	}

	@Override
	public void setupBlockTags(_MaterialBase base, BlockTagsProvider btp) {
		if (!SMALL_LOG.isIgnored()) {
			SMALL_LOG.blockTag(btp);
		}
		if (!LOG.isIgnored()) {
			LOG.blockTag(btp);
		}
		if (!LOG_SLAB.isIgnored()) {
			LOG_SLAB.blockTag(btp);
		}
		if (!LOG_STAIRS.isIgnored()) {
			LOG_STAIRS.blockTag(btp);
		}

		if (!STRIPPED_SMALL_LOG.isIgnored()) {
			STRIPPED_SMALL_LOG.blockTag(btp);
		}
		if (!STRIPPED_LOG.isIgnored()) {
			STRIPPED_LOG.blockTag(btp);
		}
		if (!STRIPPED_LOG_SLAB.isIgnored()) {
			STRIPPED_LOG_SLAB.blockTag(btp);
		}
		if (!STRIPPED_LOG_STAIRS.isIgnored()) {
			STRIPPED_LOG_STAIRS.blockTag(btp);
		}
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

	@Override
	public boolean isIndexItem(_MaterialBase base, ItemStack stack) {
		if (SMALL_LOG.is(stack))
			return true;
		if (LOG.is(stack))
			return true;
		if (LOG_SLAB.is(stack))
			return true;
		if (LOG_STAIRS.is(stack))
			return true;

		if (STRIPPED_SMALL_LOG.is(stack))
			return true;
		if (STRIPPED_LOG.is(stack))
			return true;
		if (STRIPPED_LOG_SLAB.is(stack))
			return true;
		if (STRIPPED_LOG_STAIRS.is(stack))
			return true;

		return false;
	}

	@Override
	public Optional<IIndexEntry> getEntryItemBelongsTo(_MaterialBase base, ItemStack stack) {
		if (SMALL_LOG.is(stack))
			return Optional.of(base);
		if (LOG.is(stack))
			return Optional.of(base);
		if (LOG_SLAB.is(stack))
			return Optional.of(base);
		if (LOG_STAIRS.is(stack))
			return Optional.of(base);

		if (STRIPPED_SMALL_LOG.is(stack))
			return Optional.of(base);
		if (STRIPPED_LOG.is(stack))
			return Optional.of(base);
		if (STRIPPED_LOG_SLAB.is(stack))
			return Optional.of(base);
		if (STRIPPED_LOG_STAIRS.is(stack))
			return Optional.of(base);

		return Optional.empty();
	}

}
