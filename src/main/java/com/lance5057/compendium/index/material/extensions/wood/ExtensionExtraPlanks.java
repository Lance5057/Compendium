package com.lance5057.compendium.index.material.extensions.wood;

import static com.lance5057.compendium.util.TagUtil.mcLoc;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.blocks.PipeStyleBlock;
import com.lance5057.compendium.blocks.SimpleStyleBlock;
import com.lance5057.compendium.blocks.SlabStyleBlock;
import com.lance5057.compendium.blocks.StairStyleBlock;
import com.lance5057.compendium.client.models.style.StyleBlockModelBuilder;
import com.lance5057.compendium.client.models.style.model.StyleModelBuilder;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.data.IndexBlockModelProvider;
import com.lance5057.compendium.index.material.base._MaterialBase;
import com.lance5057.compendium.index.material.extensions.MaterialExtensionSerializer;
import com.lance5057.compendium.index.material.extensions._MaterialExtension;
import com.lance5057.compendium.index.util.CompendiumBlockHandler;
import com.lance5057.compendium.index.util.DataUtil;
import com.lance5057.compendium.style.StyleData;

import net.minecraft.core.Direction;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ExtensionExtraPlanks extends _MaterialExtension {
	public final CompendiumBlockHandler PLANK;
	public final CompendiumBlockHandler PLANK_BLOCK;
	public final CompendiumBlockHandler PLANK_SLAB;
	public final CompendiumBlockHandler PLANK_STAIRS;

	private TagKey<Item> plankTag;

	public ExtensionExtraPlanks(boolean plank, boolean plankBlock, boolean plankSlab, boolean plankStairs) {
		PLANK = new CompendiumBlockHandler("plank");
		PLANK_BLOCK = new CompendiumBlockHandler("plank_block");
		PLANK_SLAB = new CompendiumBlockHandler("plank_slab");
		PLANK_STAIRS = new CompendiumBlockHandler("plank_stairs");

		PLANK.setEnabled(plank);
		PLANK_BLOCK.setEnabled(plankBlock);
		PLANK_SLAB.setEnabled(plankSlab);
		PLANK_STAIRS.setEnabled(plankStairs);
	}

	@Override
	public void setup(_MaterialBase base) {
		PLANK.setup(base, () -> new PipeStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)),
				base.tagNamespace, "plank");
		PLANK_BLOCK.setup(base,
				() -> new SimpleStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS), StyleData.PLANKS),
				() -> new BlockItem(PLANK_BLOCK.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				base.tagNamespace, "planks");
		PLANK_SLAB.setup(base,
				() -> new SlabStyleBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_SLAB), StyleData.PLANKS),
				() -> new BlockItem(PLANK_SLAB.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				base.tagNamespace, "planks/slab");
		PLANK_STAIRS.setup(base,
				() -> new StairStyleBlock(PLANK_BLOCK.BLOCK.get().defaultBlockState(),
						Block.Properties.ofFullCopy(Blocks.DARK_OAK_STAIRS), StyleData.PLANKS),
				() -> new BlockItem(PLANK_STAIRS.BLOCK.get(),
						new Item.Properties().component(CompendiumComponents.STYLE,
								new StyleBlockComponent(new ArrayList<Integer>(Arrays.asList(0))))),
				base.tagNamespace, "planks/stairs");
		CompendiumBlockEntities.validStyleBlocks.add(PLANK.BLOCK);
		CompendiumBlockEntities.validStyleBlocks.add(PLANK_BLOCK.BLOCK);
		CompendiumBlockEntities.validStyleBlocks.add(PLANK_SLAB.BLOCK);
		CompendiumBlockEntities.validStyleBlocks.add(PLANK_STAIRS.BLOCK);
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
			if (PLANK_BLOCK.enabled()) {
				ConfiguredModel.Builder<?> b = ConfiguredModel.builder();
				StyleBlockModelBuilder<BlockModelBuilder> msmb = bsp.models()
						.getBuilder(PLANK_BLOCK.location(base) + "planks").customLoader(StyleBlockModelBuilder::begin);
				msmb.base(bsp.models().cubeAll("plank_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.PLANKS.getTypes())
					msmb.add(new StyleModelBuilder(s,
							bsp.modLoc(PLANK_BLOCK.location(base) + "planks/" + s.toLowerCase())));

				BlockModelBuilder bmb = msmb.end();
				b.modelFile(bmb);
				bsp.simpleBlock(PLANK_BLOCK.BLOCK.get(), b.build());
			}
			if (PLANK_SLAB.enabled()) {
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
			}
			if (PLANK_STAIRS.enabled()) {
				StyleBlockModelBuilder<BlockModelBuilder> plank_stairs_standard = bsp.models()
						.getBuilder(PLANK.location(base) + "plank_stairs").customLoader(StyleBlockModelBuilder::begin);
				plank_stairs_standard.base(bsp.models().cubeAll("plank_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.PLANKS.getTypes())
					plank_stairs_standard.add(
							new StyleModelBuilder(s, bsp.modLoc(PLANK.location(base) + "stairs/" + s.toLowerCase())));

				StyleBlockModelBuilder<BlockModelBuilder> plank_stairs_inner = bsp.models()
						.getBuilder(PLANK.location(base) + "plank_stairs_inner")
						.customLoader(StyleBlockModelBuilder::begin);
				plank_stairs_inner.base(bsp.models().cubeAll("plank_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.PLANKS.getTypes())
					plank_stairs_inner.add(new StyleModelBuilder(s,
							bsp.modLoc(PLANK.location(base) + "stairs/" + s.toLowerCase() + "_inner")));

				StyleBlockModelBuilder<BlockModelBuilder> plank_stairs_outer = bsp.models()
						.getBuilder(PLANK.location(base) + "plank_stairs_outer")
						.customLoader(StyleBlockModelBuilder::begin);
				plank_stairs_outer.base(bsp.models().cubeAll("plank_base", bsp.mcLoc("block/oak_planks")));

				for (String s : StyleData.PLANKS.getTypes())
					plank_stairs_outer.add(new StyleModelBuilder(s,
							bsp.modLoc(PLANK.location(base) + "stairs/" + s.toLowerCase() + "_outer")));

				stairsBlock((StairBlock) PLANK_STAIRS.BLOCK.get(), plank_stairs_standard.end(),
						plank_stairs_inner.end(), plank_stairs_outer.end(), bsp);
			}
		}
	}

//	public void styledStairs(Block block, String location, BlockStateProvider bsp) {
//		bsp.getVariantBuilder(block).forAllStates(s -> {
//			Direction facing = s.getValue(StairStyleBlock.FACING);
//			Half half = s.getValue(StairStyleBlock.HALF);
//			StairsShape shape = s.getValue(StairStyleBlock.SHAPE);
//
//			if (half == Half.TOP) {
//				switch (shape) {
//				case StairsShape.STRAIGHT:
//					switch (facing) {
//					case Direction.NORTH:
//						return stair(location, "top_straight_south", bsp);
//					case Direction.SOUTH:
//						return stair(location, "top_straight_north", bsp);
//					case Direction.EAST:
//						return stair(location, "top_straight_west", bsp);
//					default:
//					case Direction.WEST:
//						return stair(location, "top_straight_east", bsp);
//					}
//				case StairsShape.INNER_LEFT:
//					switch (facing) {
//					case Direction.NORTH:
//						return stair(location, "top_inner_corner_north_east", bsp);
//					case Direction.SOUTH:
//						return stair(location, "top_inner_corner_north_west", bsp);
//					case Direction.EAST:
//						return stair(location, "top_inner_corner_north_east", bsp);
//					default:
//					case Direction.WEST:
//						return stair(location, "top_inner_corner_north_east", bsp);
//					}
//
//				case StairsShape.INNER_RIGHT:
//					switch (facing) {
//					case Direction.NORTH:
//						return stair(location, "top_inner_corner_north_east", bsp);
//					case Direction.SOUTH:
//						return stair(location, "top_inner_corner_north_west", bsp);
//					case Direction.EAST:
//						return stair(location, "top_inner_corner_north_east", bsp);
//					default:
//					case Direction.WEST:
//						return stair(location, "top_inner_corner_north_east", bsp);
//					}
//				case StairsShape.OUTER_LEFT:
//					switch (facing) {
//					case Direction.NORTH:
//						return stair(location, "top_outer_corner_north_east", bsp);
//					case Direction.SOUTH:
//						return stair(location, "top_outer_corner_north_west", bsp);
//					case Direction.EAST:
//						return stair(location, "top_outer_corner_north_east", bsp);
//					default:
//					case Direction.WEST:
//						return stair(location, "top_outer_corner_north_east", bsp);
//					}
//				case StairsShape.OUTER_RIGHT:
//					switch (facing) {
//					case Direction.NORTH:
//						return stair(location, "top_outer_corner_north_east", bsp);
//					case Direction.SOUTH:
//						return stair(location, "top_outer_corner_north_west", bsp);
//					case Direction.EAST:
//						return stair(location, "top_outer_corner_north_west", bsp);
//					default:
//					case Direction.WEST:
//						return stair(location, "top_outer_corner_north_east", bsp);
//					}
//				}
//			} else {
//				switch (shape) {
//				case StairsShape.STRAIGHT:
//					switch (facing) {
//					case Direction.NORTH:
//						return stair(location, "bottom_straight_south", bsp);
//					case Direction.SOUTH:
//						return stair(location, "bottom_straight_north", bsp);
//					case Direction.EAST:
//						return stair(location, "bottom_straight_west", bsp);
//					default:
//					case Direction.WEST:
//						return stair(location, "bottom_straight_east", bsp);
//					}
//				case StairsShape.INNER_LEFT:
//					return stair(location, "bottom_inner_corner_north_east", bsp);
//				case StairsShape.INNER_RIGHT:
//					return stair(location, "bottom_inner_corner_north_west", bsp);
//				case StairsShape.OUTER_LEFT:
//					return stair(location, "bottom_outer_corner_south_east", bsp);
//				case StairsShape.OUTER_RIGHT:
//					return stair(location, "bottom_outer_corner_south_west", bsp);
//				}
//			}
//			return null;
//		});
//	}

	private ConfiguredModel[] stair(String location, String suffix, BlockStateProvider bsp) {
		StyleBlockModelBuilder<BlockModelBuilder> model = bsp.models().getBuilder(location + "_stairs_" + suffix)
				.customLoader(StyleBlockModelBuilder::begin);
		model.base(bsp.models().cubeAll("plank_base", bsp.mcLoc("block/oak_planks")));

		for (String style : StyleData.PLANKS.getTypes())
			model.add(new StyleModelBuilder(style,
					bsp.modLoc(location + "stairs/" + style.toLowerCase() + "_" + suffix)));

		return ConfiguredModel.builder().modelFile(model.end()).build();
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
		for (String s : StyleData.PLANKS.getTypes()) {
			if (s.equals("walkway_vertical")) {
				ibmp.withExistingParent(PLANK_BLOCK.location(base) + "/planks/" + s,
						ibmp.modLoc("block/cube_all_rotated")).texture("all", mcLoc("block/" + base.name + "_planks"));

				ibmp.withExistingParent(PLANK.location(base) + "/slab/" + s + "_bottom",
						ibmp.modLoc("block/bases/slab/slab_rotated_bottom"))
						.texture("all", mcLoc("block/" + base.name + "_planks"));
				ibmp.withExistingParent(PLANK.location(base) + "/slab/" + s + "_top",
						ibmp.modLoc("block/bases/slab/slab_rotated_top"))
						.texture("all", mcLoc("block/" + base.name + "_planks"));
				ibmp.withExistingParent(PLANK_BLOCK.location(base) + "/slab/" + s + "_full",
						ibmp.modLoc("block/cube_all_rotated")).texture("all", mcLoc("block/" + base.name + "_planks"));

				ibmp.withExistingParent(PLANK.location(base) + "/stairs/" + s,
						ibmp.modLoc("block/bases/stairs/stairs_rotated"))
						.texture("all", mcLoc("block/" + base.name + "_planks"));

			} else {
				ibmp.withExistingParent(PLANK_BLOCK.location(base) + "/planks/" + s, ibmp.mcLoc("block/cube_all"))
						.texture("all", ibmp.modLoc(PLANK_BLOCK.location(base) + "planks/" + s));

				ibmp.slab(PLANK.location(base) + "/slab/" + s + "_bottom",
						ibmp.modLoc(PLANK.location(base) + "planks/" + s),
						ibmp.modLoc(PLANK.location(base) + "planks/" + s),
						ibmp.modLoc(PLANK.location(base) + "planks/" + s));
				ibmp.slabTop(PLANK.location(base) + "/slab/" + s + "_top",
						ibmp.modLoc(PLANK.location(base) + "planks/" + s),
						ibmp.modLoc(PLANK.location(base) + "planks/" + s),
						ibmp.modLoc(PLANK.location(base) + "planks/" + s));
				ibmp.cubeAll(PLANK.location(base) + "/slab/" + s + "_full",
						ibmp.modLoc(PLANK.location(base) + "planks/" + s)); // can we just make this inherit the plank
																			// one?

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
			}
		}
	}

	@Override
	public void itemModel(_MaterialBase base, ItemModelProvider tmp) {
		DataUtil.basicMaterial3DItem(tmp, PLANK.BLOCK_ITEM.get(), base, Compendium.modLoc("item/plank"), base.getType(),
				tmp.mcLoc("block/" + base.name.toLowerCase() + "_planks"));

		tmp.getBuilder(PLANK_BLOCK.BLOCK_ITEM.getId().getPath())
				.parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
						"block/material/wood/" + base.name.toLowerCase() + "/planks/big")));

		tmp.getBuilder(PLANK_SLAB.BLOCK_ITEM.getId().getPath())
				.parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
						"block/material/wood/" + base.name.toLowerCase() + "/slab/big_bottom")));

		tmp.getBuilder(PLANK_STAIRS.BLOCK_ITEM.getId().getPath())
				.parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
						"block/material/wood/" + base.name.toLowerCase() + "/stairs/big")));
	}

	@Override
	public void engLoc(_MaterialBase base, LanguageProvider lp) {
		StringBuilder material_name = new StringBuilder();
		for (String word : base.name.split("_")) {
			word = word.substring(0, 1).toUpperCase() + word.substring(1);
			material_name.append(word).append(" ");
		}
		if (PLANK.enabled()) {
			lp.add(this.PLANK.BLOCK_ITEM.get(), material_name + "Plank");
		}
		if (PLANK_BLOCK.enabled()) {
			lp.add(this.PLANK_BLOCK.BLOCK_ITEM.get(), material_name + "Styled Planks");
		}
		if (PLANK_SLAB.enabled()) {
			lp.add(this.PLANK_SLAB.BLOCK_ITEM.get(), material_name + "Styled Plank Slab");
		}
		if (PLANK_STAIRS.enabled()) {
			lp.add(this.PLANK_STAIRS.BLOCK_ITEM.get(), material_name + "Styled Plank Stairs");
		}
	}

	@Override
	public void recipes(_MaterialBase base, RecipeOutput consumer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void blockLoot(_MaterialBase base, BlockLootSubProvider blp) {
		blp.dropSelf(PLANK.BLOCK.get());
		blp.dropSelf(PLANK_BLOCK.BLOCK.get());
		blp.dropSelf(PLANK_SLAB.BLOCK.get());
//		blp.dropSelf(PLANK_CORNER.BLOCK.get());
		blp.dropSelf(PLANK_STAIRS.BLOCK.get());
	}

	@Override
	public void setupItemTags(_MaterialBase base, ItemTagsProvider itp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupBlockTags(_MaterialBase base, BlockTagsProvider itp) {
		// TODO Auto-generated method stub

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
			j.addProperty("loadPlank", src.PLANK.enabled());
			j.addProperty("loadPlankBlock", src.PLANK_BLOCK.enabled());
			j.addProperty("loadPlankSlab", src.PLANK_SLAB.enabled());
//			j.addProperty("loadPlankCorner", src.PLANK_CORNER.enabled());
			j.addProperty("loadPlankStairs", src.PLANK_STAIRS.enabled());

			return j;
		}

		@Override
		public ExtensionExtraPlanks deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject j = json.getAsJsonObject();

			boolean loadPlank = j.get("loadPlank").getAsBoolean();
			boolean loadPlankBlock = j.get("loadPlankBlock").getAsBoolean();
			boolean loadPlankSlab = j.get("loadPlankSlab").getAsBoolean();
//			boolean loadPlankCorner = j.get("loadPlankCorner").getAsBoolean();
			boolean loadPlankStairs = j.get("loadPlankStairs").getAsBoolean();

			return new ExtensionExtraPlanks(loadPlank, loadPlankBlock, loadPlankSlab, loadPlankStairs);
		}

	}

	@Override
	public void otherLoot(_MaterialBase base, LootTableSubProvider lsp) {
		// TODO Auto-generated method stub

	}

}
