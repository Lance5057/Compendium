package com.lance5057.compendium.index.util;

import java.util.Objects;

import com.lance5057.compendium.Compendium;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.registries.DeferredItem;

public class DataUtil {
	public static ItemModelBuilder basicMaterialItem(ItemModelProvider tmp, Item item, String name) {
		return basicMaterialItem(tmp, Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)), name);
	}

	public static ItemModelBuilder basicMaterialItem(ItemModelProvider tmp, ResourceLocation item, String name) {
		return tmp.getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile("item/handheld")).texture(
				"layer0", new ResourceLocation(item.getNamespace(), "item/material/" + name + "/" + item.getPath()));
	}

	public static void basicMaterialBlockItem(ItemModelProvider p, DeferredItem<? extends BlockItem> item,
			String name) {
		p.getBuilder(item.getId().getPath()).parent(
				new ModelFile.UncheckedModelFile(new ResourceLocation(Compendium.MOD_ID, "block/block" + name)));
	}

	public static void basicMaterialBlock(BlockStateProvider bsp, Block block, String name) {
		bsp.simpleBlock(block,
				bsp.models().cubeAll("block" + name, bsp.modLoc("block/material/" + name + "/" + name + "block")));
	}

	public static void basicMaterialBow(ItemModelProvider tmp, Item item, String name) {
		ResourceLocation rc = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item));

		tmp.getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile("item/handheld")).texture("layer0",
				new ResourceLocation(rc.getNamespace(), "item/material/" + name + "/" + rc.getPath()));
		tmp.getBuilder(item.toString() + "_pulling_0").parent(new ModelFile.UncheckedModelFile(item.toString()))
				.texture("layer0", new ResourceLocation(rc.getNamespace(),
						"item/material/" + name + "/" + rc.getPath() + "_pulling_0"));
		tmp.getBuilder(item.toString() + "_pulling_1").parent(new ModelFile.UncheckedModelFile(item.toString()))
				.texture("layer0", new ResourceLocation(rc.getNamespace(),
						"item/material/" + name + "/" + rc.getPath() + "_pulling_1"));
		tmp.getBuilder(item.toString() + "_pulling_2").parent(new ModelFile.UncheckedModelFile(item.toString()))
				.texture("layer0", new ResourceLocation(rc.getNamespace(),
						"item/material/" + name + "/" + rc.getPath() + "_pulling_2"));
	}
}
