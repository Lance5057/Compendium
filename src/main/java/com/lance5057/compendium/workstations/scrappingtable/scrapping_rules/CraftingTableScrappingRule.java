package com.lance5057.compendium.workstations.scrappingtable.scrapping_rules;

import java.util.List;

import net.minecraft.world.item.ItemStack;

public class CraftingTableScrappingRule implements IScrappingRule {

	@Override
	public List<ItemStack> scrap(ItemStack stack) {
		// Take apart
		return null;
	}

	@Override
	public boolean matches(ItemStack stack) {
		// Does this item match this scrapping rule?
		return false;
	}

}
