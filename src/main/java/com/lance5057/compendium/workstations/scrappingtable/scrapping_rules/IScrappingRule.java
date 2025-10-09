package com.lance5057.compendium.workstations.scrappingtable.scrapping_rules;

import java.util.List;

import net.minecraft.world.item.ItemStack;

public interface IScrappingRule {
	public boolean matches(ItemStack stack);
	public List<ItemStack> scrap(ItemStack stack);
}
