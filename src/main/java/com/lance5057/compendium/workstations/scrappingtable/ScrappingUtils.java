package com.lance5057.compendium.workstations.scrappingtable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.index.IIndexEntry;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class ScrappingUtils {
	public static ItemStack breakDownItem(Ingredient ingredient) {

//		for (IIndexEntry i : CompendiumIndex.index) {
//			ItemStack broken = i.breakDownItem(ingredient);
//			if (!broken.isEmpty())
//				return broken;
//		}

		return ItemStack.EMPTY;
	}

	public static ItemStack convertBasedOnTag(Ingredient ingredient, TagKey<Item> tagIn, TagKey<Item> tagOut,
			int countOut) {
		if (tagIn != null) {
			Optional<ItemStack> r = Arrays.asList(ingredient.getItems()).stream().filter(i -> i.is(tagIn)).findFirst();
			if (r.isPresent())
				return Ingredient.of(tagOut).getItems()[0].copyWithCount(countOut);
		}
		return ItemStack.EMPTY;
	}
	
	public static ItemStack convertBasedOnTag(Ingredient ingredient, List<TagKey<Item>> tagIn, List<TagKey<Item>> tagOut,
			int countOut) {
		if (tagIn != null) {
			Optional<ItemStack> r = Arrays.asList(ingredient.getItems()).stream().filter(i -> i.is(tagIn.get(0))).findFirst();
			if (r.isPresent())
				return Ingredient.of(tagOut.get(0)).getItems()[0].copyWithCount(countOut);
		}
		return ItemStack.EMPTY;
	}

	public static ItemStack convertBasedOnTag(Ingredient ingredient, TagKey<Item> tag, Item itemOut, int countOut) {
		if (tag != null) {
			Optional<ItemStack> r = Arrays.asList(ingredient.getItems()).stream().filter(i -> i.is(tag)).findFirst();
			if (r.isPresent())
				return new ItemStack(itemOut, countOut);
		}
		return ItemStack.EMPTY;
	}
	
	public static ItemStack convertBasedOnTag(Ingredient ingredient, List<TagKey<Item>> tag, Item itemOut, int countOut) {
		if (tag != null) {
			Optional<ItemStack> r = Arrays.asList(ingredient.getItems()).stream().filter(i -> i.is(tag.get(0))).findFirst();
			if (r.isPresent())
				return new ItemStack(itemOut, countOut);
		}
		return ItemStack.EMPTY;
	}

	public static ItemStack convertBasedOnStack(Ingredient ingredient, Item itemIn, Item itemOut, int countOut) {
		if (ingredient.test(new ItemStack(itemIn)))
			return new ItemStack(itemOut, countOut);

		return ItemStack.EMPTY;
	}
}
