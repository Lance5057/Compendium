package lance5057.compendium.core.util.recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.util.NonNullList;

public class RecipeUtil {
    public static List<List<ItemStack>> getMatchingStacks(NonNullList<Ingredient> ingredients) {
	List<List<ItemStack>> matchingStacks = Lists.newArrayList();
	ingredients.forEach(i -> {
	    List<ItemStack> copy = Arrays.asList(i.getMatchingStacks());
	    matchingStacks.add(copy);
	});
	return matchingStacks;
    }

//    public static List<ItemStack> getStacksFromLootPool(LootPool pool) {
//	List<LootEntry> entries = pool.lootEntries;
//	List<ItemStack> items = new ArrayList<ItemStack>();
//
//	for (LootEntry e : entries) {
//	    if (e instanceof ItemLootEntry) {
//		ItemLootEntry i = (ItemLootEntry) e;
//
//		items.add(new ItemStack(i.item));
//	    }
//	}
//	return items;
//
//    }
}
