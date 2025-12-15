package com.lance5057.compendium.data;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumItems;
import com.lance5057.compendium.index.CompendiumIndex;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

public class ItemModels extends ItemModelProvider {

	public ItemModels(PackOutput output, ExistingFileHelper fh) {
		super(output, Compendium.MOD_ID, fh);
	}

	@Override
	protected void registerModels() {
		CompendiumIndex.index.forEach(i -> {
			i.itemModel(this);
		});

		getBuilder(CompendiumItems.COSMETIC_TOOLBOX.getId().getPath()).parent(new ModelFile.UncheckedModelFile(
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "block/cosmetic_toolbox")));

		forItem(CompendiumItems.ADJUSTINATOR, "adjustinator");

		forBlockItem(CompendiumItems.WORKBENCH,
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "block/workstations/workbench_full"));
		forBlockItem(CompendiumItems.HAMMERING_STATION,
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "block/workstations/hammering_station"));
		forBlockItem(CompendiumItems.SAW_BUCK,
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "block/workstations/sawbuck"));
		forBlockItem(CompendiumItems.SCRAPPING_TABLE,
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "block/workstations/dismantling_table"));

		forItem(CompendiumItems.SAWDUST, "sawdust");

		forItem(CompendiumItems.CRUDE_HAMMER, "crude_hammer");
		forItem(CompendiumItems.CRUDE_SAW, "crude_saw");

		for3dItem(CompendiumItems.MEGALITH_STONE,
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "item/megalith_stone_item"));

		forBlockItem(CompendiumItems.TOOLRACK,
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "block/toolrack"));
		forBlockItem(CompendiumItems.COMPONENT_DRAWER,
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "block/component_drawer"));
	}

	public static void forBlockItem(ItemModelProvider p, DeferredItem<? extends BlockItem> item, String name) {
		p.getBuilder(item.getId().getPath())
				.parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
						"block/" + BuiltInRegistries.BLOCK.getKey(item.get().getBlock()).getPath())));

	}

	public void forItem(DeferredItem<? extends Item> item, String name) {
		this.singleTexture(item.getId().getPath(), mcLoc("item/handheld"), "layer0", modLoc("item/" + name));
	}

	public void forMaterialItem(DeferredItem<? extends Item> item, String name) {
		this.singleTexture(item.getId().getPath(), mcLoc("item/handheld"), "layer0",
				modLoc("item/material/" + name + "/" + item.getId().getPath()));
	}

	public void forBlockItem(DeferredItem<Item> item, String name) {
		if (item.get() instanceof BlockItem b)
			getBuilder(item.getId().getPath())
					.parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
							"block/" + BuiltInRegistries.BLOCK.getKey(b.getBlock()).getPath())));
	}

	public void for3dItem(DeferredItem<? extends Item> item, ResourceLocation modelLocation) {
		getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile(modelLocation));
	}

	public void forBlockItem(DeferredItem<? extends BlockItem> item, ResourceLocation modelLocation) {
		getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile(modelLocation));
	}

	public void forBlockItem(DeferredItem<? extends BlockItem> item, ResourceLocation modelLocation, String key,
			ResourceLocation texture) {
		getBuilder(item.getId().getPath()).parent(new ModelFile.UncheckedModelFile(modelLocation)).texture(key,
				texture);
	}

}
