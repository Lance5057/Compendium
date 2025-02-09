package com.lance5057.compendium.data;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumItems;
import com.lance5057.compendium.index.CompendiumIndex;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
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
	}

	public static void forBlockItem(ItemModelProvider p, DeferredItem<? extends BlockItem> item, String name) {
		p.getBuilder(item.getId().getPath())
				.parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
						"block/" + BuiltInRegistries.BLOCK.getKey(item.get().getBlock()).getPath())));

	}

}
