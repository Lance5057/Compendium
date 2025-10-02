package com.lance5057.compendium.index.util;

import java.util.Objects;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.index.material.base._MaterialBase;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.registries.DeferredItem;

public class DataUtil {
	public static ResourceLocation standardResource(String name, String path) {
		return Compendium.modLoc("item/material/" + name + "/" + path);
	}

//	public static ItemModelBuilder basicMaterialItem(ItemModelProvider tmp, Item item, String name,
//			MATERIAL_TYPES type) {
//		return basicMaterialItem(tmp, Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)), name, type);
//	}

	public static ItemModelBuilder basicMaterialItem(ItemModelProvider tmp, Item item, _MaterialBase base, String name,
			MATERIAL_TYPES type) {
		ResourceLocation rc = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item));
		return tmp.getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile("item/handheld"))
				.texture("layer0", ResourceLocation.fromNamespaceAndPath(rc.getNamespace(),
						"item/material/" + type.toString().toLowerCase() + "/" + base.name + "/" + name));
	}

	public static ItemModelBuilder basicMaterialItemWithExtraLayer(ItemModelProvider tmp, Item item, _MaterialBase base,
			String name, MATERIAL_TYPES type, ResourceLocation extra) {
		ResourceLocation rc = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item));
		return tmp.getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile("item/handheld"))
				.texture("layer0",
						ResourceLocation.fromNamespaceAndPath(rc.getNamespace(),
								"item/material/" + type.toString().toLowerCase() + "/" + base.name + "/" + name))
				.texture("layer1", extra);
	}

	public static void basicMaterial3DItem(ItemModelProvider p, Item item, _MaterialBase base,
			ResourceLocation resourceLocation, MATERIAL_TYPES type, ResourceLocation texture) {
		p.getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile(resourceLocation)).texture("0", texture);
	}

	public static void basicMaterialInventoryBlockItem(ItemModelProvider p, DeferredItem<? extends BlockItem> item,
			String name, MATERIAL_TYPES type) {
		p.getBuilder(item.getId().getPath())
				.parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
						"item/material/" + type.toString().toLowerCase() + "/" + name + "/" + name + "_inventory")));
	}

	public static void basicMaterialInventoryBlockItem(ItemModelProvider p, DeferredItem<? extends BlockItem> item,
			String name, String extra, MATERIAL_TYPES type) {
		p.getBuilder(item.getId().getPath())
				.parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
						"item/material/" + type.toString().toLowerCase() + "/" + name + "/" + extra + "_inventory")));
	}

	public static void basicMaterialBlockItem(ItemModelProvider p, DeferredItem<? extends BlockItem> item, String name,
			MATERIAL_TYPES type) {
		p.getBuilder(item.getId().getPath())
				.parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
						"block/material/" + type.toString().toLowerCase() + "/" + name + "/" + name + "_block")));
	}

	public static void basicMaterialBlockItem(ItemModelProvider p, DeferredItem<? extends BlockItem> item, String name,
			String extra, MATERIAL_TYPES type) {
		p.getBuilder(item.getId().getPath())
				.parent(new ModelFile.UncheckedModelFile(
						ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "block/material/"
								+ type.toString().toLowerCase() + "/" + name + "/" + name + "_" + extra + "_block")));
	}

	public static void basicMaterialBlock(BlockStateProvider bsp, Block block, String name, MATERIAL_TYPES type) {
		bsp.simpleBlock(block, bsp.models().cubeAll(
				"block/material/" + type.toString().toLowerCase() + "/" + name + "/" + name + "_block",
				bsp.modLoc("block/material/" + type.toString().toLowerCase() + "/" + name + "/" + name + "_block")));
	}

	public static void basicMaterialBlock(BlockStateProvider bsp, Block block, String name, String extra,
			String rendertype, MATERIAL_TYPES type) {
		bsp.simpleBlock(block,
				bsp.models().cubeAll(
						"block/material/" + type.toString().toLowerCase() + "/" + name + "/" + name + extra + "_block",
						bsp.modLoc("block/material/" + type.toString().toLowerCase() + "/" + name + "/" + name + extra
								+ "_block")));
	}

	public static void axisMaterialBlock(BlockStateProvider bsp, _MaterialBase base, CompendiumBlockHandler b,
			String texture, String rendertype, MATERIAL_TYPES type) {
		if (b.BLOCK.get() instanceof RotatedPillarBlock)
			bsp.axisBlock((RotatedPillarBlock) b.BLOCK.get(), bsp.models()
					.cubeColumn(b.location(base) + texture + "_block", Compendium.modLoc(b.location(base) + texture),
							Compendium.modLoc(b.location(base) + texture + "_top"))
					.renderType(rendertype),
					bsp.models()
							.cubeColumnHorizontal(b.location(base) + texture + "_block",
									Compendium.modLoc(base.blockFolder() + texture),
									Compendium.modLoc(base.blockFolder() + texture + "_top"))
							.renderType(rendertype));
	}

	public static void slabMaterialBlock(BlockStateProvider bsp, SlabBlock block, String name, String extra,
			String rendertype, MATERIAL_TYPES type) {

		bsp.getVariantBuilder(block).partialState().with(SlabBlock.TYPE, SlabType.BOTTOM)
				.addModels(new ConfiguredModel(bsp.models()
						.withExistingParent("block/material/" + type.toString().toLowerCase() + "/" + name + "/" + name
								+ extra + "_slab_bottom", Compendium.modLoc("small_logs_slab_bottom"))))
				.partialState().with(SlabBlock.TYPE, SlabType.TOP)
				.addModels(new ConfiguredModel(bsp.models()
						.withExistingParent("block/material/" + type.toString().toLowerCase() + "/" + name + "/" + name
								+ extra + "_slab_top", Compendium.modLoc("small_logs_slab_top"))))
				.partialState().with(SlabBlock.TYPE, SlabType.DOUBLE)
				.addModels(new ConfiguredModel(
						bsp.models().withExistingParent("block/material/" + type.toString().toLowerCase() + "/" + name
								+ "/" + name + extra + "_slab_full", Compendium.modLoc("small_logs_slab_full"))));
	}

	public static void stairsMaterialBlock(BlockStateProvider bsp, StairBlock block, String name, String extra,
			String rendertype, MATERIAL_TYPES type) {

		ModelFile stairs = bsp.models().withExistingParent(
				"block/material/" + type.toString().toLowerCase() + "/" + name + "/" + name + extra + "_stairs",
				Compendium.modLoc("small_logs_stairs"));
		ModelFile stairsOuter = bsp.models().withExistingParent(
				"block/material/" + type.toString().toLowerCase() + "/" + name + "/" + name + extra + "_outer_stairs",
				Compendium.modLoc("small_logs_outer_stairs"));
		ModelFile stairsInner = bsp.models().withExistingParent(
				"block/material/" + type.toString().toLowerCase() + "/" + name + "/" + name + extra + "_inner_stairs",
				Compendium.modLoc("small_logs_inner_stairs"));

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
			return ConfiguredModel.builder().modelFile(shape == StairsShape.STRAIGHT ? stairs
					: shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT ? stairsInner : stairsOuter)
					.rotationX(half == Half.BOTTOM ? 0 : 180).rotationY(yRot).build();
		}, StairBlock.WATERLOGGED);
	}

	public static void basicMaterialBow(ItemModelProvider tmp, Item item, _MaterialBase base, MATERIAL_TYPES type) {
		ResourceLocation rc = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item));

		tmp.getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile("item/handheld"))
				.texture("layer0",
						ResourceLocation.fromNamespaceAndPath(rc.getNamespace(),
								"item/material/" + type.toString().toLowerCase() + "/" + base.name + "/bow"))
				.texture("layer1", ResourceLocation.fromNamespaceAndPath(rc.getNamespace(), "item/bow_base"));
		tmp.getBuilder(item.toString() + "_pulling_0").parent(new ModelFile.UncheckedModelFile(item.toString()))
				.texture("layer0",
						ResourceLocation.fromNamespaceAndPath(rc.getNamespace(),
								"item/material/" + type.toString().toLowerCase() + "/" + base.name + "/bow_pulling_0"))
				.texture("layer1", ResourceLocation.fromNamespaceAndPath(rc.getNamespace(), "item/bow_base_pulling_0"));
		tmp.getBuilder(item.toString() + "_pulling_1").parent(new ModelFile.UncheckedModelFile(item.toString()))
				.texture("layer0",
						ResourceLocation.fromNamespaceAndPath(rc.getNamespace(),
								"item/material/" + type.toString().toLowerCase() + "/" + base.name + "/bow_pulling_1"))
				.texture("layer1", ResourceLocation.fromNamespaceAndPath(rc.getNamespace(), "item/bow_base_pulling_1"));
		tmp.getBuilder(item.toString() + "_pulling_2").parent(new ModelFile.UncheckedModelFile(item.toString()))
				.texture("layer0",
						ResourceLocation.fromNamespaceAndPath(rc.getNamespace(),
								"item/material/" + type.toString().toLowerCase() + "/" + base.name + "/bow_pulling_2"))
				.texture("layer1", ResourceLocation.fromNamespaceAndPath(rc.getNamespace(), "item/bow_base_pulling_2"));
	}
}
