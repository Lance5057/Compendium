package com.lance5057.compendium.workstations.scrappingtable;

import java.util.Arrays;
import java.util.Optional;

import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.IIndexEntry;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredItem;

public class ScrappingUtils {
	public static ItemStack breakDownItem(Ingredient ingredient) {

		for (IIndexEntry i : CompendiumIndex.index) {
			ItemStack broken = i.breakDownItem(ingredient);
			if (!broken.isEmpty())
				return broken;
		}

		return ItemStack.EMPTY;
	}

	public static ItemStack convertBasedOnTagOrStack(Ingredient ingredient, TagKey<Item> tag, boolean loaded,
			DeferredItem<? extends Item> itemIn, DeferredItem<? extends Item> itemOut, int countOut) {
		if (loaded && itemIn != null && itemOut != null) {
			if (ingredient.test(itemIn.toStack()))
				return itemOut.toStack(countOut);
		} else {
			Optional<ItemStack> r = Arrays.asList(ingredient.getItems()).stream().filter(i -> i.is(tag)).findFirst();
			if (r.isPresent())
				return r.get().copyWithCount(countOut);
		}
		return ItemStack.EMPTY;
	}
}
