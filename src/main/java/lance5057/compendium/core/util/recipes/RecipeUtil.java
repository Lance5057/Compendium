//package lance5057.compendium.core.util.recipes;
//
//import java.util.Arrays;
//import java.util.List;
//
//import com.google.common.collect.Lists;
//
//import net.minecraft.core.NonNullList;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.crafting.Ingredient;
//
//public class RecipeUtil {
//    public static List<List<ItemStack>> getMatchingStacks(NonNullList<Ingredient> ingredients) {
//	List<List<ItemStack>> matchingStacks = Lists.newArrayList();
//	ingredients.forEach(i -> {
//	    List<ItemStack> copy = Arrays.asList(i.getItems());
//	    matchingStacks.add(copy);
//	});
//	return matchingStacks;
//    }
//
////    public static List<ItemStack> getStacksFromLootPool(LootPool pool) {
////	List<LootEntry> entries = pool.lootEntries;
////	List<ItemStack> items = new ArrayList<ItemStack>();
////
////	for (LootEntry e : entries) {
////	    if (e instanceof ItemLootEntry) {
////		ItemLootEntry i = (ItemLootEntry) e;
////
////		items.add(new ItemStack(i.item));
////	    }
////	}
////	return items;
////
////    }
//}
