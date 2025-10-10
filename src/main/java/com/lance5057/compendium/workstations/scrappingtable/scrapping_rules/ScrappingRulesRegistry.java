package com.lance5057.compendium.workstations.scrappingtable.scrapping_rules;

import java.util.HashSet;
import java.util.Optional;

import net.minecraft.world.item.crafting.RecipeHolder;

public class ScrappingRulesRegistry {
	private static HashSet<IScrappingRule> rules = new HashSet<IScrappingRule>();

	public static void addRule(IScrappingRule rule) {
		rules.add(rule);
	}

	public static Optional<IScrappingRule> getRule(RecipeHolder<?> recipe) {
		return rules.stream().filter(r -> r.matches(recipe)).findFirst();
	}

	static {
		addRule(new CraftingTableScrappingRule());
	}
}
