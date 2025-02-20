package com.lance5057.compendium.data.loottables;

import java.util.function.BiConsumer;

import org.jetbrains.annotations.NotNull;

import com.lance5057.compendium.util.TagUtil;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class RecipeLootTables implements LootTableSubProvider {

	public static final ResourceKey<LootTable> STONE_TO_COBBLE = ResourceKey.create(Registries.LOOT_TABLE,
			TagUtil.modLoc("stone_to_cobble"));
	
	private final HolderLookup.Provider provider;
	public RecipeLootTables(HolderLookup.Provider provider) {
		this.provider = provider;
	}

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, Builder> output) {
		output.accept(STONE_TO_COBBLE, LootTable.lootTable().withPool(createPoolWithItem(Items.COBBLESTONE, 1)));
	}

	@NotNull
	public static LootPool.Builder createPoolWithItem(Item item, int count) {
		return LootPool.lootPool().add(LootItem.lootTableItem(item))
				.apply(SetItemCountFunction.setCount(ConstantValue.exactly(count)));
	}

	@NotNull
	public static LootPool.Builder createPoolWithItem(Item item, int min, int max) {
		return LootPool.lootPool().add(LootItem.lootTableItem(item))
				.apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)));
	}

	@NotNull
	public static LootPool.Builder createPoolWithItem(Item item) {
		return LootPool.lootPool().add(LootItem.lootTableItem(item));
	}

	@NotNull
	public static LootPool.Builder createPoolWithItem(Item item, int count, float lootMin, float lootMax,
			HolderLookup.Provider provider) {
		return LootPool.lootPool().add(LootItem.lootTableItem(item))
				.apply(SetItemCountFunction.setCount(ConstantValue.exactly(count))).apply(EnchantedCountIncreaseFunction
						.lootingMultiplier(provider, UniformGenerator.between(0.0F, 1.0F)));
	}

	@NotNull
	public static LootPool.Builder createPoolWithItem(Item item, int min, int max, float lootMin, float lootMax,
			HolderLookup.Provider provider) {
		return LootPool.lootPool().add(LootItem.lootTableItem(item))
				.apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
				.apply(EnchantedCountIncreaseFunction.lootingMultiplier(provider,
						UniformGenerator.between(0.0F, 1.0F)));
	}
}
