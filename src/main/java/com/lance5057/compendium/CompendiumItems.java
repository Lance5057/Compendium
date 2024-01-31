package com.lance5057.compendium;

import javax.annotation.Nullable;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompendiumItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Compendium.MOD_ID);

	public static final DeferredItem<Item> SAWDUST = ITEMS.register("sawdust", () -> new Item(new Item.Properties()) {
		@Override
		public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
			return 300;
		}
	});
}
