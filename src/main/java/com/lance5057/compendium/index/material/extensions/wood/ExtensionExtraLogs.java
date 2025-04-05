package com.lance5057.compendium.index.material.extensions.wood;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extensions.MaterialExtensionSerializer;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.DataUtil;

import net.minecraft.core.Direction;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class ExtensionExtraLogs extends _MaterialExtension {
	boolean smallLogs;
	boolean smallLogsSlab;
	boolean smallLogsCorner;
	boolean smallLogsStairs;

	public DeferredBlock<RotatedPillarBlock> SMALL_LOGS;
	public DeferredItem<BlockItem> SMALL_LOGS_ITEM;

	public DeferredBlock<SlabBlock> SMALL_LOGS_SLAB;
	public DeferredItem<BlockItem> SMALL_LOGS_SLAB_ITEM;

	public DeferredBlock<RotatedPillarBlock> SMALL_LOGS_CORNER;
	public DeferredItem<BlockItem> SMALL_LOGS_CORNER_ITEM;

	public DeferredBlock<StairBlock> SMALL_LOGS_STAIRS;
	public DeferredItem<BlockItem> SMALL_LOGS_STAIRS_ITEM;

	public ExtensionExtraLogs(boolean smallLogs, boolean smallLogsCorner, boolean smallLogsSlab,
			boolean smallLogsStairs) {
		this.smallLogs = smallLogs;
		this.smallLogsCorner = smallLogsCorner;
		this.smallLogsSlab = smallLogsSlab;
		this.smallLogsStairs = smallLogsStairs;
	}

	@Override
	public void setup(_MaterialBase base) {
		if (smallLogs) {
			SMALL_LOGS = CompendiumIndex.BLOCKS.register(base.name + "_small_logs",
					() -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.DARK_OAK_LOG)));
			SMALL_LOGS_ITEM = CompendiumIndex.ITEMS.register(base.name + "_small_logs_item",
					() -> new BlockItem(SMALL_LOGS.get(), new Item.Properties()));
		}
		if (smallLogs) {
			SMALL_LOGS_CORNER = CompendiumIndex.BLOCKS.register(base.name + "_small_logs_corner",
					() -> new RotatedPillarBlock(Block.Properties.ofFullCopy(Blocks.DARK_OAK_LOG)));
			SMALL_LOGS_CORNER_ITEM = CompendiumIndex.ITEMS.register(base.name + "_small_logs_corner_item",
					() -> new BlockItem(SMALL_LOGS_CORNER.get(), new Item.Properties()));
		}
		if (smallLogsSlab) {
			SMALL_LOGS_SLAB = CompendiumIndex.BLOCKS.register(base.name + "_small_logs_slab",
					() -> new SlabBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_SLAB)));
			SMALL_LOGS_SLAB_ITEM = CompendiumIndex.ITEMS.register(base.name + "_small_logs_slab_item",
					() -> new BlockItem(SMALL_LOGS_SLAB.get(), new Item.Properties()));
		}
		if (smallLogsStairs) {
			SMALL_LOGS_STAIRS = CompendiumIndex.BLOCKS.register(base.name + "_small_logs_stairs",
					() -> new StairBlock(SMALL_LOGS.get().defaultBlockState(),
							Block.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS)));
			SMALL_LOGS_STAIRS_ITEM = CompendiumIndex.ITEMS.register(base.name + "_small_logs_stairs_item",
					() -> new BlockItem(SMALL_LOGS_STAIRS.get(), new Item.Properties()));
		}
	}

	@Override
	public void tab(_MaterialBase base, Output output) {
		if (this.smallLogs) {
			output.accept(SMALL_LOGS_ITEM);
		}
		if (this.smallLogsCorner) {
			output.accept(SMALL_LOGS_CORNER_ITEM);
		}
		if (this.smallLogsSlab) {
			output.accept(SMALL_LOGS_SLAB_ITEM);
		}
		if (this.smallLogsStairs) {
			output.accept(SMALL_LOGS_STAIRS_ITEM);
		}
	}

	@Override
	public void blockModel(_MaterialBase base, BlockStateProvider bsp) {
		if (this.autoGenBlockModel) {
			if (this.smallLogs) {
				DataUtil.axisMaterialBlock(bsp, SMALL_LOGS.get(), base.name, "_small_logs", "solid", base.getType());
			}
			if (this.smallLogsCorner) {

				bsp.axisBlock(SMALL_LOGS_CORNER.get(),
						bsp.models()
								.withExistingParent(
										"block/material/" + base.getType().toString().toLowerCase() + "/" + base.name
												+ "/" + base.name + "_small_logs_corner" + "_block",
										Compendium.modLoc("small_logs_corner"))
								.texture("1",
										Compendium.modLoc("block/material/" + base.getType().toString().toLowerCase()
												+ "/" + base.name + "/" + base.name + "_small_logs_corner"))
								.texture("2",
										Compendium.modLoc("block/material/" + base.getType().toString().toLowerCase()
												+ "/" + base.name + "/" + base.name + "_small_logs")),

						bsp.models()
								.withExistingParent(
										"block/material/" + base.getType().toString().toLowerCase() + "/" + base.name
												+ "/" + base.name + "_small_logs_corner_side" + "_block",
										Compendium.modLoc("small_logs_corner_side"))
								.texture("1",
										Compendium.modLoc("block/material/" + base.getType().toString().toLowerCase()
												+ "/" + base.name + "/" + base.name + "_small_logs_corner"))
								.texture("2",
										Compendium.modLoc("block/material/" + base.getType().toString().toLowerCase()
												+ "/" + base.name + "/" + base.name + "_small_logs")));
			}
			if (this.smallLogsSlab) {
				bsp.slabBlock(SMALL_LOGS_SLAB.get(),
						bsp.models()
								.withExistingParent(
										"block/material/" + base.getType().toString().toLowerCase() + "/" + base.name
												+ "/" + base.name + "_small_logs_slab_bottom_block",
										Compendium.modLoc("small_logs_slab_bottom"))
								.texture("0",
										Compendium.modLoc("block/material/" + base.getType().toString().toLowerCase()
												+ "/" + base.name + "/" + base.name + "_small_logs"))
								.texture("1",
										Compendium.modLoc("block/material/" + base.getType().toString().toLowerCase()
												+ "/" + base.name + "/" + base.name + "_small_logs_slab")),
						bsp.models()
								.withExistingParent(
										"block/material/" + base.getType().toString().toLowerCase() + "/" + base.name
												+ "/" + base.name + "_small_logs_slab_top_block",
										Compendium.modLoc("small_logs_slab_top"))
								.texture("0",
										Compendium.modLoc("block/material/" + base.getType().toString().toLowerCase()
												+ "/" + base.name + "/" + base.name + "_small_logs"))
								.texture("1",
										Compendium.modLoc("block/material/" + base.getType().toString().toLowerCase()
												+ "/" + base.name + "/" + base.name + "_small_logs_slab")),
						bsp.models()
								.withExistingParent(
										"block/material/" + base.getType().toString().toLowerCase() + "/" + base.name
												+ "/" + base.name + "_small_logs_slab_full_block",
										Compendium.modLoc("small_logs_slab_full"))
								.texture("0",
										Compendium.modLoc("block/material/" + base.getType().toString().toLowerCase()
												+ "/" + base.name + "/" + base.name + "_small_logs"))
								.texture("1",
										Compendium.modLoc("block/material/" + base.getType().toString().toLowerCase()
												+ "/" + base.name + "/" + base.name + "_small_logs_slab")));
//				DataUtil.slabMaterialBlock(bsp, SMALL_LOGS_SLAB.get(), base.name, "_small_logs_slab", "solid",
//						base.getType());
			}
			if (this.smallLogsStairs) {
//				DataUtil.stairsMaterialBlock(bsp, SMALL_LOGS_STAIRS.get(), base.name, "_small_logs_stairs", "solid",
//						base.getType());
				stairsBlock(SMALL_LOGS_STAIRS.get(), bsp.models()
						.withExistingParent("block/material/" + base.getType().toString().toLowerCase() + "/"
								+ base.name + "/" + base.name + "_small_logs_stairs_block",
								Compendium.modLoc("small_logs_stairs"))
						.texture("0",
								Compendium.modLoc("block/material/" + base.getType().toString().toLowerCase() + "/"
										+ base.name + "/" + base.name + "_small_logs_corner"))
						.texture("1",
								Compendium.modLoc("block/material/" + base.getType().toString().toLowerCase() + "/"
										+ base.name + "/" + base.name + "_small_logs_turned"))
						.texture("2",
								Compendium.modLoc("block/material/" + base.getType().toString().toLowerCase() + "/"
										+ base.name + "/" + base.name + "_small_logs_slab")),
						bsp.models()
								.withExistingParent(
										"block/material/" + base.getType().toString().toLowerCase() + "/" + base.name
												+ "/" + base.name + "_small_logs_inner_stairs_block",
										Compendium.modLoc("small_logs_inner_stairs"))
								.texture("0",
										Compendium.modLoc("block/material/" + base.getType().toString().toLowerCase()
												+ "/" + base.name + "/" + base.name + "_small_logs_corner"))
								.texture("2",
										Compendium.modLoc("block/material/" + base.getType().toString().toLowerCase()
												+ "/" + base.name + "/" + base.name + "_small_logs_turned"))
								.texture("1",
										Compendium.modLoc("block/material/" + base.getType().toString().toLowerCase()
												+ "/" + base.name + "/" + base.name + "_small_logs_slab")),
						bsp.models()
								.withExistingParent(
										"block/material/" + base.getType().toString().toLowerCase() + "/" + base.name
												+ "/" + base.name + "_small_logs_outer_stairs_block",
										Compendium.modLoc("small_logs_outer_stairs"))
								.texture("1",
										Compendium.modLoc("block/material/" + base.getType().toString().toLowerCase()
												+ "/" + base.name + "/" + base.name + "_small_logs_corner"))
								.texture("0",
										Compendium.modLoc("block/material/" + base.getType().toString().toLowerCase()
												+ "/" + base.name + "/" + base.name + "_small_logs_turned")),
						bsp);
			}
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
			if (this.smallLogs) {
				DataUtil.basicMaterialBlockItem(tmp, SMALL_LOGS_ITEM, base.name, "small_logs", base.getType());
			}
			if (this.smallLogsCorner) {
				DataUtil.basicMaterialBlockItem(tmp, SMALL_LOGS_CORNER_ITEM, base.name, "small_logs_corner",
						base.getType());
			}
			if (this.smallLogsSlab) {
				DataUtil.basicMaterialBlockItem(tmp, SMALL_LOGS_SLAB_ITEM, base.name, "small_logs_slab_bottom",
						base.getType());
			}
			if (this.smallLogsStairs) {
				DataUtil.basicMaterialBlockItem(tmp, SMALL_LOGS_STAIRS_ITEM, base.name, "small_logs_stairs",
						base.getType());
			}
		}
	}

	@Override
	public void engLoc(_MaterialBase base, LanguageProvider lp) {
		lp.add(this.SMALL_LOGS.asItem(), base.name.substring(0, 1).toUpperCase() + " Small Logs");
	}

	@Override
	public void recipes(_MaterialBase base, RecipeOutput consumer) {

	}

	@Override
	public void blockLoot(_MaterialBase base, BlockLootSubProvider blp) {
		if (this.smallLogs) {
			blp.dropSelf(this.SMALL_LOGS.get());
		}
		if (this.smallLogsCorner) {
			blp.dropSelf(this.SMALL_LOGS_CORNER.get());
		}
		if (this.smallLogsSlab) {
			blp.dropSelf(this.SMALL_LOGS_SLAB.get());
		}
		if (this.smallLogsStairs) {
			blp.dropSelf(this.SMALL_LOGS_STAIRS.get());
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
			j.addProperty("loadSmallLogs", src.smallLogs);
			j.addProperty("loadSmallCornerLogs", src.smallLogsCorner);
			j.addProperty("loadSmallLogsSlab", src.smallLogsSlab);
			j.addProperty("loadSmallLogsStairs", src.smallLogsStairs);

			return j;
		}

		@Override
		public ExtensionExtraLogs deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			boolean loadSmallLogs = j.get("loadSmallLogs").getAsBoolean();
			boolean loadSmallCornerLogs = j.get("loadSmallCornerLogs").getAsBoolean();
			boolean loadSmallLogsSlab = j.get("loadSmallLogsSlab").getAsBoolean();
			boolean loadSmallLogsStairs = j.get("loadSmallLogsStairs").getAsBoolean();

			return new ExtensionExtraLogs(loadSmallLogs, loadSmallCornerLogs, loadSmallLogsSlab, loadSmallLogsStairs);
		}

	}
}
