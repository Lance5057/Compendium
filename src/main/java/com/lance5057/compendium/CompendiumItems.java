package com.lance5057.compendium;

import javax.annotation.Nullable;

import com.lance5057.compendium.items.MegalithStoneItem;
import com.lance5057.compendium.items.tools.CosmeticToolbox;

import net.minecraft.world.item.BlockItem;
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

	public static final DeferredItem<Item> MEGALITH_STONE = ITEMS.register("megalith_stone",
			() -> new MegalithStoneItem(new Item.Properties()));

	public static final DeferredItem<Item> HAMMERING_STATION = ITEMS.register("hammering_station",
			() -> new BlockItem(CompendiumBlocks.HAMMERING_STATION.get(), new Item.Properties()));

	public static final DeferredItem<Item> COSMETIC_TOOLBOX = ITEMS.register("cosmetic_toolbox",
			() -> new CosmeticToolbox(new Item.Properties()));

	public static final DeferredItem<Item> CHAIR = ITEMS.register("chair",
			() -> new BlockItem(CompendiumBlocks.CHAIR.get(), new Item.Properties()));
}
