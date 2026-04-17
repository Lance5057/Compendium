package com.lance5057.compendium.index.material.base.wood;

import java.util.Map;

import com.google.common.collect.Maps;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumClient;
import com.lance5057.compendium.blocks.SlabStyleBlock;
import com.lance5057.compendium.client.ClientUtil;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.material.extensions.wood.ExtensionExtraLogs;
import com.lance5057.compendium.index.material.extensions.wood.ExtensionExtraPlanks;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.util.TagUtil;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.MultiPartBakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.neoforged.neoforge.client.event.ModelEvent.ModifyBakingResult;
import net.neoforged.neoforge.client.model.RegistryAwareItemModelShaper;

public class ClientWood {
	public static void doStyleWood(ModifyBakingResult event, MaterialWood mb) {
		Map<ModelResourceLocation, BakedModel> models = event.getModels();
		for (_MaterialExtension me : mb.extensions) {
			if (me instanceof ExtensionExtraPlanks eep) {
				doExtraPlanks(event, mb, eep, models);
			} else if (me instanceof ExtensionExtraLogs eel) {
				doExtraLogs(event, mb, eel, models);
			}
		}
	}

	private static void doExtraPlanks(ModifyBakingResult event, MaterialWood mw, ExtensionExtraPlanks eep,
			Map<ModelResourceLocation, BakedModel> models) {

//		ResourceLocation plankTexture = TagUtil.mcLoc("block/" + mw.name + "_planks");
//		if (mw.specialLocations != null) {
//			if (mw.specialLocations.textures != null)
//				if (mw.specialLocations.textures.plankLocation != null)
//					plankTexture = mw.specialLocations.textures.plankLocation;
//		}

		if (eep.PLANK_BLOCK.isNotIgnored()) {
			CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/planks"),
					mw.name + "_planks", "");

		}

		if (eep.PLANK.isNotIgnored()) {
			for (BlockState state : eep.PLANK.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/plank"),
						mw.name + "_plank", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/plank_inventory"),
					mw.name + "_plank_inventory", "");

			for (String plank_style : StyleData.PLANK.getTypes()) {
				ResourceLocation logModelLoc = ClientUtil.createStyleBlockLocation(mw.name + "_plank",
						plank_style.toLowerCase());
				ResourceLocation logModelLocInventory = ClientUtil
						.createStyleBlockLocation(mw.name + "_plank_inventory", plank_style.toLowerCase());
				CompendiumClient.doStylePipe(event, mw, logModelLoc, logModelLocInventory,
						TagUtil.modLoc("extra/plank/" + plank_style + "_cap"),
						TagUtil.modLoc("extra/plank/" + plank_style),
						TagUtil.modLoc("extra/plank/" + plank_style + "_horizontal2"),
						TagUtil.modLoc("extra/plank/" + plank_style + "_horizontal"),
						TagUtil.modLoc("extra/plank/" + plank_style + "_inventory"),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/planks/plank")));
			}
		}

		if (eep.PLANK_SLAB.isNotIgnored()) {
			for (BlockState state : eep.PLANK_SLAB.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/planks_slab"),
						mw.name + "_planks_slab", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models,
					TagUtil.modLoc("extra/planks_slab_inventory"), mw.name + "_planks_slab_inventory", "");
		}

		if (eep.PLANK_STAIRS.isNotIgnored()) {
			for (BlockState state : eep.PLANK_STAIRS.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/planks_stairs"),
						mw.name + "_planks_stairs", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models,
					TagUtil.modLoc("extra/planks_stairs_inventory"), mw.name + "_planks_stairs_inventory", "");
		}

		for (String planks_style : StyleData.PLANKS.getTypes()) {
			// planks
			ResourceLocation loc = TagUtil.modLoc("block/cube_all");
			ResourceLocation modelLoc = ClientUtil.createStyleBlockLocation(mw.name + "_planks",
					planks_style.toLowerCase());
			ResourceLocation t = Compendium
					.modLoc("block/material/wood/" + mw.name + "/planks/" + planks_style.toLowerCase());
			ModelResourceLocation m = new ModelResourceLocation(modelLoc, "");

			if (eep.PLANK_BLOCK.isNotIgnored()) {
				BakedModel bm = CompendiumClient.basicModelAllTexture(event, t, loc, m, BlockModelRotation.X0_Y0,
						"all");
				event.getModels().put(m, bm);
			}

			if (eep.PLANK_SLAB.isNotIgnored()) {
				// slabs
				ResourceLocation plankSlabModelLoc = ClientUtil.createStyleBlockLocation(mw.name + "_planks_slab",
						planks_style.toLowerCase());
				MultiPartBakedModel.Builder plank_slab = new MultiPartBakedModel.Builder();

				plank_slab.add(s -> s.getValue(SlabStyleBlock.TYPE) == SlabType.BOTTOM,
						CompendiumClient.basicModelManyTexture(event, TagUtil.mcLoc("block/acacia_slab"),
								new ModelResourceLocation(plankSlabModelLoc, ""), BlockModelRotation.X0_Y0,
								Pair.of("side", t), Pair.of("top", t), Pair.of("bottom", t)));
				plank_slab.add(s -> s.getValue(SlabStyleBlock.TYPE) == SlabType.TOP,
						CompendiumClient.basicModelManyTexture(event, TagUtil.mcLoc("block/acacia_slab_top"),
								new ModelResourceLocation(plankSlabModelLoc, ""), BlockModelRotation.X0_Y0,
								Pair.of("side", t), Pair.of("top", t), Pair.of("bottom", t)));
				plank_slab.add(s -> s.getValue(SlabStyleBlock.TYPE) == SlabType.DOUBLE,
						CompendiumClient.basicModelAllTexture(event, t, loc,
								new ModelResourceLocation(plankSlabModelLoc, ""), BlockModelRotation.X0_Y0, "all"));

				event.getModels().put(new ModelResourceLocation(plankSlabModelLoc, ""), plank_slab.build());

				ResourceLocation plankSlabModelLocInventory = ClientUtil
						.createStyleBlockLocation(mw.name + "_planks_slab_inventory", planks_style.toLowerCase());

				event.getModels().put(new ModelResourceLocation(plankSlabModelLocInventory, ""),
						CompendiumClient.basicModelManyTexture(event, TagUtil.mcLoc("block/acacia_slab"),
								new ModelResourceLocation(plankSlabModelLocInventory, ""), BlockModelRotation.X0_Y0,
								Pair.of("side", t), Pair.of("top", t), Pair.of("bottom", t)));
			}

			if (eep.PLANK_STAIRS.isNotIgnored()) {
				// stairs
				ResourceLocation plankStairsModelLoc = ClientUtil.createStyleBlockLocation(mw.name + "_planks_stairs",
						planks_style.toLowerCase());
				ResourceLocation plankStairsModelLocInventory = ClientUtil
						.createStyleBlockLocation(mw.name + "_planks_stairs_inventory", planks_style.toLowerCase());

				ResourceLocation straight = TagUtil.mcLoc("block/acacia_stairs");
				ResourceLocation inner = TagUtil.mcLoc("block/acacia_stairs_inner");
				ResourceLocation outer = TagUtil.mcLoc("block/acacia_stairs_outer");

				CompendiumClient.doStyleStairs(event, planks_style, plankStairsModelLoc, plankStairsModelLocInventory,
						straight, inner, outer, 90, 0, Pair.of("top", t), Pair.of("bottom", t), Pair.of("side", t));
			}
		}

	}

	private static void doExtraLogs(ModifyBakingResult event, MaterialWood mw, ExtensionExtraLogs eel,
			Map<ModelResourceLocation, BakedModel> models) {

		ResourceLocation logSideTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/" + mw.name + "_log");
		ResourceLocation logEndTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/" + mw.name + "_log_top");
		ResourceLocation logStrippedSideTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/stripped_" + mw.name + "_log");
		ResourceLocation logStrippedEndTexture = ResourceLocation.fromNamespaceAndPath(mw.namespace,
				"block/stripped_" + mw.name + "_log_top");

		if (mw.specialLocations != null) {
			if (mw.specialLocations.textures != null) {
				if (mw.specialLocations.textures.logLocation != null)
					logSideTexture = mw.specialLocations.textures.logLocation;
				if (mw.specialLocations.textures.logTopLocation != null)
					logEndTexture = mw.specialLocations.textures.logTopLocation;
				if (mw.specialLocations.textures.strippedLogLocation != null)
					logStrippedSideTexture = mw.specialLocations.textures.strippedLogLocation;
				if (mw.specialLocations.textures.logTopLocation != null)
					logStrippedEndTexture = mw.specialLocations.textures.strippedLogTopLocation;
			}
		}

		doSmallLog(event, mw, eel, models);
		doLog(event, mw, eel, models, logEndTexture, logStrippedEndTexture);
		doLogSlab(event, mw, eel, models, logSideTexture, logEndTexture, logStrippedSideTexture, logStrippedEndTexture);
		doLogStairs(event, mw, eel, models, logSideTexture, logEndTexture, logStrippedSideTexture,
				logStrippedEndTexture);

	}

	public static void doLogStairs(ModifyBakingResult event, MaterialWood mw, ExtensionExtraLogs eel,
			Map<ModelResourceLocation, BakedModel> models, ResourceLocation logSideTexture,
			ResourceLocation logEndTexture, ResourceLocation logStrippedSideTexture,
			ResourceLocation logStrippedEndTexture) {

		if (eel.STRIPPED_LOG_STAIRS.isNotIgnored()) {
			for (BlockState state : eel.STRIPPED_LOG_STAIRS.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models,
						TagUtil.modLoc("extra/stripped_log_stairs"), "stripped_" + mw.name + "_small_logs_stairs", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models,
					TagUtil.modLoc("extra/stripped_log_stairs_inventory"), mw.name + "_stripped_log_stairs_inventory",
					"");
		}

		if (eel.LOG_STAIRS.isNotIgnored()) {
			for (BlockState state : eel.LOG_STAIRS.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/log_stairs"),
						mw.name + "_small_logs_stairs", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models,
					TagUtil.modLoc("extra/log_stairs_inventory"), mw.name + "_log_stairs_inventory", "");
		}
		for (String stair_style : StyleData.LOG_STAIRS.getTypes()) {
			// stairs
			ResourceLocation plankStairsModelLoc = ClientUtil.createStyleBlockLocation(mw.name + "_small_logs_stairs",
					stair_style.toLowerCase());
			ResourceLocation plankStairsModelLocInventory = ClientUtil
					.createStyleBlockLocation(mw.name + "_log_stairs_inventory", stair_style.toLowerCase());

			ResourceLocation plankStrippedStairsModelLoc = ClientUtil
					.createStyleBlockLocation(mw.name + "_stripped_small_logs_stairs", stair_style.toLowerCase());
			ResourceLocation plankStrippedStairsModelLocInventory = ClientUtil
					.createStyleBlockLocation(mw.name + "_stripped_log_stairs_inventory", stair_style.toLowerCase());

			if (stair_style.equals("small_logs")) {
				CompendiumClient.doStyleStairs(event,  stair_style, plankStairsModelLoc,
						plankStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/small_logs"),
						TagUtil.modLoc("extra/log_stairs/small_logs_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_outer"), 90, 0,
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));

				CompendiumClient.doStyleStairs(event,  stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/small_logs"),
						TagUtil.modLoc("extra/log_stairs/small_logs_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_outer"), 90, 0,
						Pair.of("1",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
			} else if (stair_style.equals("small_logs_rotated_side")) {
				CompendiumClient.doStyleStairs(event,  stair_style, plankStairsModelLoc,
						plankStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/small_logs_rotated_side"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_side_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_side_outer"), 90, 0,
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));

				CompendiumClient.doStyleStairs(event,  stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_side"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_side_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_side_outer"), 90, 0,
						Pair.of("1",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
			} else if (stair_style.equals("small_logs_rotated_front")) {
				CompendiumClient.doStyleStairs(event,  stair_style, plankStairsModelLoc,
						plankStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/small_logs_rotated_front"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_front_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_front_outer"), 90, 0,
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));

				CompendiumClient.doStyleStairs(event,  stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_front"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_front_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_front_outer"), 90, 0,
						Pair.of("1",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
			} else if (stair_style.equals("small_logs_rotated_top")) {
				CompendiumClient.doStyleStairs(event,  stair_style, plankStairsModelLoc,
						plankStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/small_logs_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_top_outer"), 90, 0,
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));

				CompendiumClient.doStyleStairs(event,  stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/small_logs_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/small_logs_rotated_top_outer"), 90, 0,
						Pair.of("1",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")),
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
			} else if (stair_style.equals("split_log_rotated_side")) {
				CompendiumClient.doStyleStairs(event,  stair_style, plankStairsModelLoc,
						plankStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/split_log_rotated_side"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_side_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_side_outer"), 90, 0,
						Pair.of("0", logSideTexture), Pair.of("1", logEndTexture),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side")));

				CompendiumClient.doStyleStairs(event,  stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/split_log_rotated_side"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_side_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_side_outer"), 90, 0,
						Pair.of("0", logStrippedSideTexture), Pair.of("1", logStrippedEndTexture), Pair.of("2",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_log_split_side")));
			} else if (stair_style.equals("split_log_rotated_front")) {
				CompendiumClient.doStyleStairs(event,stair_style, plankStairsModelLoc,
						plankStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/split_log_rotated_front"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_front_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_front_outer"), 90, 0,
						Pair.of("0", logSideTexture), Pair.of("1", logEndTexture),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side")));

				CompendiumClient.doStyleStairs(event,  stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory,
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_front"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_front_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_front_outer"), 90, 0,
						Pair.of("0", logStrippedSideTexture), Pair.of("1", logStrippedEndTexture), Pair.of("2",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_log_split_side")));
			} else if (stair_style.equals("split_log_rotated_top")) {
				CompendiumClient.doStyleStairs(event,  stair_style, plankStairsModelLoc,
						plankStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/split_log_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_outer"), 90, 0,
						Pair.of("0", logSideTexture), Pair.of("1", logEndTexture),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side")));

				CompendiumClient.doStyleStairs(event,  stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/split_log_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_outer"), 90, 0,
						Pair.of("0", logStrippedSideTexture), Pair.of("1", logStrippedEndTexture), Pair.of("2",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_log_split_side")));
			} else if (stair_style.equals("small_wood")) {
				CompendiumClient.doStyleStairs(event,  stair_style, plankStairsModelLoc,
						plankStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/split_log_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_outer"), 90, 0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));

				CompendiumClient.doStyleStairs(event,  stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/split_log_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_outer"), 90, 0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
			} else if (stair_style.equals("small_wood_rotated")) {
				CompendiumClient.doStyleStairs(event,  stair_style, plankStairsModelLoc,
						plankStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/stairs_rotated"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_inner"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_outer"), 90, 0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));

				CompendiumClient.doStyleStairs(event,  stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/stairs_rotated"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_inner"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_outer"), 90, 0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
			} else if (stair_style.equals("wood")) {
				CompendiumClient.doStyleStairs(event,  stair_style, plankStairsModelLoc,
						plankStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/split_log_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_outer"), 90, 0,
						Pair.of("0", logSideTexture), Pair.of("1", logSideTexture), Pair.of("2", logSideTexture));

				CompendiumClient.doStyleStairs(event,  stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/split_log_rotated_top"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_inner"),
						TagUtil.modLoc("extra/log_stairs/split_log_rotated_top_outer"), 90, 0,
						Pair.of("0", logStrippedSideTexture),
						Pair.of("1", logStrippedSideTexture),
						Pair.of("2", logStrippedSideTexture));
			} else if (stair_style.equals("wood_rotated")) {
				CompendiumClient.doStyleStairs(event,  stair_style, plankStairsModelLoc,
						plankStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/stairs_rotated"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_inner"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_outer"), 90, 0, Pair.of("0", logSideTexture));

				CompendiumClient.doStyleStairs(event,  stair_style, plankStrippedStairsModelLoc,
						plankStrippedStairsModelLocInventory, TagUtil.modLoc("extra/log_stairs/stairs_rotated"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_inner"),
						TagUtil.modLoc("extra/log_stairs/stairs_rotated_outer"), 90, 0,
						Pair.of("0", logStrippedSideTexture));
			}
		}
	}

	public static void doLogSlab(ModifyBakingResult event, MaterialWood mw, ExtensionExtraLogs eel,
			Map<ModelResourceLocation, BakedModel> models, ResourceLocation logSideTexture,
			ResourceLocation logEndTexture, ResourceLocation logStrippedSideTexture,
			ResourceLocation logStrippedEndTexture) {
		if (eel.STRIPPED_LOG_SLAB.isNotIgnored()) {
			for (BlockState state : eel.STRIPPED_LOG_SLAB.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models,
						TagUtil.modLoc("extra/stripped_log_slab"), "stripped_" + mw.name + "_small_logs_slab", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models,
					TagUtil.modLoc("extra/stripped_log_slab_inventory"), mw.name + "_stripped_log_slab_inventory", "");
		}

		if (eel.LOG_SLAB.isNotIgnored()) {
			for (BlockState state : eel.LOG_SLAB.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/log_slab"),
						mw.name + "_small_logs_slab", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models,
					TagUtil.modLoc("extra/log_slab_inventory"), mw.name + "_log_slab_inventory", "");
		}

		for (String slab_style : StyleData.LOG_SLAB.getTypes()) {
			// slabs
			ResourceLocation plankSlabModelLoc = ClientUtil.createStyleBlockLocation(mw.name + "_small_logs_slab",
					slab_style.toLowerCase());
			ResourceLocation plankSlabIventoryModelLoc = ClientUtil
					.createStyleBlockLocation(mw.name + "_log_slab_inventory", slab_style.toLowerCase());

			ResourceLocation strippedSlabModelLoc = ClientUtil
					.createStyleBlockLocation(mw.name + "_stripped_small_logs_slab", slab_style.toLowerCase());
			ResourceLocation strippedSlabIventoryModelLoc = ClientUtil
					.createStyleBlockLocation(mw.name + "_stripped_log_slab_inventory", slab_style.toLowerCase());

			if (slab_style.equals("small_logs") || slab_style.equals("small_logs_rotated")
					|| slab_style.equals("crosscut_small")) {
				CompendiumClient.doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")),
						Pair.of("particle", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")));

				CompendiumClient.doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0,
						Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
						Pair.of("1",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")),
						Pair.of("particle",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")));

			} else if (slab_style.equals("split") || slab_style.equals("split_rotated")) {
				CompendiumClient.doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logSideTexture), Pair.of("1", logEndTexture),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side")),
						Pair.of("particle", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side")));

				CompendiumClient.doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logStrippedSideTexture),
						Pair.of("1", logStrippedEndTexture),
						Pair.of("2",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_log_split_side")),
						Pair.of("particle",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_log_split_side")));

			} else if (slab_style.equals("crosscut")) {
				CompendiumClient.doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logSideTexture), Pair.of("0", logSideTexture),
						Pair.of("1", logEndTexture), Pair.of("particle", logEndTexture));

				CompendiumClient.doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logStrippedSideTexture),
						Pair.of("0", logStrippedSideTexture), Pair.of("1", logStrippedEndTexture),
						Pair.of("particle", logStrippedEndTexture));

			} else if (slab_style.equals("small_wood") || slab_style.equals("small_wood_rotated")) {
				ResourceLocation logTexture = TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs");
				ResourceLocation logStrippedTexture = TagUtil
						.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs");

				BlockModelRotation rot = BlockModelRotation.X0_Y0;
				if (slab_style.contains("rotated"))
					rot = BlockModelRotation.X0_Y90;

				CompendiumClient.doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc, rot,
						Pair.of("top", logTexture), Pair.of("bottom", logTexture), Pair.of("side", logTexture),
						Pair.of("particle", logTexture));

				CompendiumClient.doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						rot, Pair.of("top", logStrippedTexture), Pair.of("bottom", logStrippedTexture),
						Pair.of("side", logStrippedTexture), Pair.of("particle", logStrippedTexture));

			} else if (slab_style.equals("wood") || slab_style.equals("wood_rotated")) {
				BlockModelRotation rot = BlockModelRotation.X0_Y0;
				if (slab_style.contains("rotated"))
					rot = BlockModelRotation.X0_Y90;

				CompendiumClient.doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc, rot,
						Pair.of("top", logSideTexture), Pair.of("bottom", logSideTexture),
						Pair.of("side", logSideTexture), Pair.of("particle", logSideTexture));

				CompendiumClient.doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						rot, Pair.of("top", logStrippedSideTexture), Pair.of("bottom", logStrippedSideTexture),
						Pair.of("side", logStrippedSideTexture), Pair.of("particle", logStrippedSideTexture));

			} else if (slab_style.equals("campfire")) {
				CompendiumClient.doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logSideTexture),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/extra_caps")),
						Pair.of("particle", logSideTexture));

				CompendiumClient.doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logStrippedSideTexture),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_extra_caps")),
						Pair.of("particle", logStrippedSideTexture));

			} else if (slab_style.equals("firewood")) {
				CompendiumClient.doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logSideTexture), Pair.of("1", logEndTexture),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/log_split_side")),
						Pair.of("particle", logSideTexture));

				CompendiumClient.doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						BlockModelRotation.X0_Y0, Pair.of("0", logStrippedSideTexture), Pair.of("1", logEndTexture),
						Pair.of("2",
								TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_log_split_side")),
						Pair.of("particle", logStrippedSideTexture));

			} else if (slab_style.equals("smaller_logs") || slab_style.equals("smaller_logs_rotated")
					|| slab_style.equals("smallest_logs") || slab_style.equals("smallest_logs_rotated")) {
				BlockModelRotation rot = BlockModelRotation.X0_Y0;

				CompendiumClient.doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc, rot,
						Pair.of("0", logSideTexture),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/extra_caps")),
						Pair.of("particle", logSideTexture));

				CompendiumClient.doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						rot, Pair.of("0", logStrippedSideTexture),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_extra_caps")),
						Pair.of("particle", logStrippedSideTexture));

			} else if (slab_style.equals("trellis")) {
				BlockModelRotation rot = BlockModelRotation.X0_Y0;

				CompendiumClient.doStyleSlab(event, mw, slab_style, plankSlabModelLoc, plankSlabIventoryModelLoc, rot,
						Pair.of("0", logSideTexture),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/extra_caps")),
						Pair.of("particle", logSideTexture));

				CompendiumClient.doStyleSlab(event, mw, slab_style, strippedSlabModelLoc, strippedSlabIventoryModelLoc,
						rot, Pair.of("0", logStrippedSideTexture),
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_extra_caps")),
						Pair.of("particle", logStrippedSideTexture));
			}
		}
	}

	public static void doLog(ModifyBakingResult event, MaterialWood mw, ExtensionExtraLogs eel,
			Map<ModelResourceLocation, BakedModel> models, ResourceLocation logEndTexture,
			ResourceLocation logStrippedEndTexture) {

		if (eel.STRIPPED_LOG.isNotIgnored()) {
			for (BlockState state : eel.STRIPPED_LOG.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/stripped_log"),
						"stripped_" + mw.name + "_small_logs", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models,
					TagUtil.modLoc("extra/stripped_log_inventory"), mw.name + "_stripped_log_inventory", "");

			for (String log_style : StyleData.LOG.getTypes()) {
				ResourceLocation strippedLogModelLoc = ClientUtil.createStyleBlockLocation(mw.name + "_stripped_log",
						log_style.toLowerCase());
				ResourceLocation strippedLogModelLocInventory = ClientUtil
						.createStyleBlockLocation(mw.name + "_stripped_log_inventory", log_style.toLowerCase());

				if (log_style.equals("basic")) {
					ResourceLocation model = TagUtil.mcLoc("block/acacia_log");

					CompendiumClient.doStyleLog(event, mw, strippedLogModelLoc, strippedLogModelLocInventory, model,
							Pair.of("side",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
							Pair.of("end", TagUtil
									.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")));

				} else if (log_style.equals("small_wood")) {
					ResourceLocation model = TagUtil.mcLoc("block/acacia_log");

					CompendiumClient.doStyleLog(event, mw, strippedLogModelLoc, strippedLogModelLocInventory, model,
							Pair.of("side",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
							Pair.of("end",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")));
				} else if (log_style.equals("corner")) {
					ResourceLocation model = TagUtil.modLoc("extra/small_logs_corner");

					CompendiumClient.doStyleLog(event, mw, strippedLogModelLoc, strippedLogModelLocInventory, model,
							Pair.of("1",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
							Pair.of("2", TagUtil
									.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs_top")));
				} else {
					ResourceLocation model = TagUtil.modLoc("extra/cube_column_ends");
					ResourceLocation sideStrippedTexture = TagUtil
							.modLoc("block/material/wood/" + mw.name + "/logs/stripped_" + log_style);
					if (log_style.contains("1") || log_style.contains("2")) {

						CompendiumClient.doStyleLog(event, mw, strippedLogModelLoc, strippedLogModelLocInventory, model,
								Pair.of("side", sideStrippedTexture), Pair.of("bottom", logEndTexture),
								Pair.of("top", logStrippedEndTexture));

					} else {
						CompendiumClient.doStyleLog(event, mw, strippedLogModelLoc, strippedLogModelLocInventory, model,
								Pair.of("side", sideStrippedTexture), Pair.of("top", logEndTexture),
								Pair.of("bottom", logStrippedEndTexture));

					}
				}
			}
		}

		if (eel.LOG.isNotIgnored()) {
			for (BlockState state : eel.LOG.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/log"),
						mw.name + "_small_logs", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/log_inventory"),
					mw.name + "_log_inventory", "");
		}

		for (String log_style : StyleData.LOG.getTypes()) {
			ResourceLocation logModelLoc = ClientUtil.createStyleBlockLocation(mw.name + "_log",
					log_style.toLowerCase());
			ResourceLocation logModelLocInventory = ClientUtil.createStyleBlockLocation(mw.name + "_log_inventory",
					log_style.toLowerCase());

			if (log_style.equals("basic")) {
				ResourceLocation model = TagUtil.mcLoc("block/acacia_log");

				CompendiumClient.doStyleLog(event, mw, logModelLoc, logModelLocInventory, model,
						Pair.of("side", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("end", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")));

			} else if (log_style.equals("small_wood")) {
				ResourceLocation model = TagUtil.mcLoc("block/acacia_log");

				CompendiumClient.doStyleLog(event, mw, logModelLoc, logModelLocInventory, model,
						Pair.of("side", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("end", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")));
			} else if (log_style.equals("corner")) {
				ResourceLocation model = TagUtil.modLoc("extra/small_logs_corner");

				CompendiumClient.doStyleLog(event, mw, logModelLoc, logModelLocInventory, model,
						Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
						Pair.of("2", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs_top")));
			} else {
				ResourceLocation model = TagUtil.modLoc("extra/cube_column_ends");
				ResourceLocation sideTexture = TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/" + log_style);

				if (log_style.contains("1") || log_style.contains("2")) {

					CompendiumClient.doStyleLog(event, mw, logModelLoc, logModelLocInventory, model,
							Pair.of("side", sideTexture), Pair.of("top", logEndTexture),
							Pair.of("bottom", logStrippedEndTexture));

				} else {
					CompendiumClient.doStyleLog(event, mw, logModelLoc, logModelLocInventory, model,
							Pair.of("side", sideTexture), Pair.of("bottom", logEndTexture),
							Pair.of("top", logStrippedEndTexture));

				}
			}
		}

	}

	public static void doSmallLog(ModifyBakingResult event, MaterialWood mw, ExtensionExtraLogs eel,
			Map<ModelResourceLocation, BakedModel> models) {

		if (eel.STRIPPED_SMALL_LOG.isNotIgnored()) {
			for (BlockState state : eel.STRIPPED_SMALL_LOG.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models,
						TagUtil.modLoc("extra/stripped_small_log"), "stripped_" + mw.name + "_small_log", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models,
					TagUtil.modLoc("extra/stripped_small_log_inventory"), mw.name + "_stripped_small_log_inventory",
					"");

			for (String small_log_style : StyleData.SMALL_LOG.getTypes()) {
				ResourceLocation logStrippedModelLoc = ClientUtil
						.createStyleBlockLocation(mw.name + "_stripped_small_log", small_log_style.toLowerCase());
				ResourceLocation logStrippedModelLocInventory = ClientUtil.createStyleBlockLocation(
						mw.name + "_stripped_small_log_inventory", small_log_style.toLowerCase());

				if (small_log_style.equals("small_log")) {
					CompendiumClient.doStylePipe(event, mw, logStrippedModelLoc, logStrippedModelLocInventory,
							TagUtil.modLoc("extra/small_log/small_log_cap"),
							TagUtil.modLoc("extra/small_log/small_log"),
							TagUtil.modLoc("extra/small_log/small_log_horizontal2"),
							TagUtil.modLoc("extra/small_log/small_log_horizontal"),
							TagUtil.modLoc("extra/small_log/small_log_inventory"),
							Pair.of("1",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
							Pair.of("0",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_extra_caps")));
				} else if (small_log_style.equals("smaller_log")) {
					CompendiumClient.doStylePipe(event, mw, logStrippedModelLoc, logStrippedModelLocInventory,
							TagUtil.modLoc("extra/small_log/smaller_log_cap"),
							TagUtil.modLoc("extra/small_log/smaller_log"),
							TagUtil.modLoc("extra/small_log/smaller_log_horizontal2"),
							TagUtil.modLoc("extra/small_log/smaller_log_horizontal"),
							TagUtil.modLoc("extra/small_log/smaller_log_inventory"),
							Pair.of("1",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
							Pair.of("0",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_extra_caps")));
				} else if (small_log_style.equals("smallest_log")) {
					CompendiumClient.doStylePipe(event, mw, logStrippedModelLoc, logStrippedModelLocInventory,
							TagUtil.modLoc("extra/small_log/smallest_log_cap"),
							TagUtil.modLoc("extra/small_log/smallest_log"),
							TagUtil.modLoc("extra/small_log/smallest_log_horizontal2"),
							TagUtil.modLoc("extra/small_log/smallest_log_horizontal"),
							TagUtil.modLoc("extra/small_log/smallest_log_inventory"),
							Pair.of("1",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_small_logs")),
							Pair.of("0",
									TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/stripped_extra_caps")));
				}
			}

		}

		if (eel.SMALL_LOG.isNotIgnored()) {
			for (BlockState state : eel.SMALL_LOG.BLOCK.get().getStateDefinition().getPossibleStates()) {
				Map<Property<?>, Comparable<?>> propertyValues = Maps.newLinkedHashMap(state.getValues());

				String v = CompendiumClient.stateToString(propertyValues);

				CompendiumClient.buildStateModelVariantAltLocation(event, models, TagUtil.modLoc("extra/small_log"),
						mw.name + "_small_log", v);
			}
			CompendiumClient.buildStateModelVariantAltLocation(event, models,
					TagUtil.modLoc("extra/small_log_inventory"), mw.name + "_small_log_inventory", "");

			for (String small_log_style : StyleData.SMALL_LOG.getTypes()) {
				ResourceLocation logModelLoc = ClientUtil.createStyleBlockLocation(mw.name + "_small_log",
						small_log_style.toLowerCase());
				ResourceLocation logModelLocInventory = ClientUtil
						.createStyleBlockLocation(mw.name + "_small_log_inventory", small_log_style.toLowerCase());

				if (small_log_style.equals("small_log")) {
					CompendiumClient.doStylePipe(event, mw, logModelLoc, logModelLocInventory,
							TagUtil.modLoc("extra/small_log/small_log_cap"),
							TagUtil.modLoc("extra/small_log/small_log"),
							TagUtil.modLoc("extra/small_log/small_log_horizontal2"),
							TagUtil.modLoc("extra/small_log/small_log_horizontal"),
							TagUtil.modLoc("extra/small_log/small_log_inventory"),
							Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
							Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/extra_caps")));
				} else if (small_log_style.equals("smaller_log")) {
					CompendiumClient.doStylePipe(event, mw, logModelLoc, logModelLocInventory,
							TagUtil.modLoc("extra/small_log/smaller_log_cap"),
							TagUtil.modLoc("extra/small_log/smaller_log"),
							TagUtil.modLoc("extra/small_log/smaller_log_horizontal2"),
							TagUtil.modLoc("extra/small_log/smaller_log_horizontal"),
							TagUtil.modLoc("extra/small_log/smaller_log_inventory"),
							Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
							Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/extra_caps")));
				} else if (small_log_style.equals("smallest_log")) {
					CompendiumClient.doStylePipe(event, mw, logModelLoc, logModelLocInventory,
							TagUtil.modLoc("extra/small_log/smallest_log_cap"),
							TagUtil.modLoc("extra/small_log/smallest_log"),
							TagUtil.modLoc("extra/small_log/smallest_log_horizontal2"),
							TagUtil.modLoc("extra/small_log/smallest_log_horizontal"),
							TagUtil.modLoc("extra/small_log/smallest_log_inventory"),
							Pair.of("1", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/small_logs")),
							Pair.of("0", TagUtil.modLoc("block/material/wood/" + mw.name + "/logs/extra_caps")));
				}
			}

		}

	}

	public static void doItems(RegistryAwareItemModelShaper shaper, _MaterialBase mb, MaterialWood mw) {

		if (mw.LOG.shouldGenerate())
			shaper.register(mw.LOG.BLOCK_ITEM.asItem(), ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));

		mw.extensions.forEach(i -> {
			if (i instanceof ExtensionExtraPlanks eep) {
				if (eep.PLANK.shouldGenerate())
					shaper.register(eep.PLANK.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.PLANK_BLOCK.shouldGenerate())
					shaper.register(eep.PLANK_BLOCK.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.PLANK_SLAB.shouldGenerate())
					shaper.register(eep.PLANK_SLAB.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.PLANK_STAIRS.shouldGenerate())
					shaper.register(eep.PLANK_STAIRS.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
			}
			if (i instanceof ExtensionExtraLogs eep) {
				if (eep.LOG.shouldGenerate())
					shaper.register(eep.LOG.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.LOG_SLAB.shouldGenerate())
					shaper.register(eep.LOG_SLAB.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.LOG_STAIRS.shouldGenerate())
					shaper.register(eep.LOG_STAIRS.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.SMALL_LOG.shouldGenerate())
					shaper.register(eep.SMALL_LOG.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.STRIPPED_LOG.shouldGenerate())
					shaper.register(eep.STRIPPED_LOG.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.STRIPPED_LOG_SLAB.shouldGenerate())
					shaper.register(eep.STRIPPED_LOG_SLAB.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.STRIPPED_LOG_STAIRS.shouldGenerate())
					shaper.register(eep.STRIPPED_LOG_STAIRS.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
				if (eep.STRIPPED_SMALL_LOG.shouldGenerate())
					shaper.register(eep.STRIPPED_SMALL_LOG.BLOCK_ITEM.get(),
							ModelResourceLocation.standalone(TagUtil.modLoc("item/item")));
			}
		});
	}
}
