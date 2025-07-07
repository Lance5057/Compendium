package com.lance5057.compendium.data.loottables;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;

import com.lance5057.compendium.CompendiumBlocks;
import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.index.CompendiumIndex;
import com.lance5057.compendium.workstations.workbench.WorkbenchBlock;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.registries.DeferredHolder;

public class BlockLootTables extends BlockLootSubProvider {
	public BlockLootTables(HolderLookup.Provider provider) {
		super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), provider);
	}

	@Override
	protected void generate() {
		CompendiumIndex.index.forEach(i -> {
			i.blockLoot(this);
		});

		this.add(CompendiumBlocks.WORKBENCH.get(),
				createSinglePropConditionTable(CompendiumBlocks.WORKBENCH.get(), WorkbenchBlock.HALF, Half.TOP));
		this.dropSelf(CompendiumBlocks.HAMMERING_STATION.get());
		this.dropSelf(CompendiumBlocks.SAW_BUCK.get());
		this.dropSelf(CompendiumBlocks.SCRAPPING_TABLE.get());
		this.dropSelf(CompendiumBlocks.COSMETIC_TOOLBOX.get());
		this.dropSelf(CompendiumBlocks.CHAIR.get());
		this.dropSelf(CompendiumBlocks.TOOLRACK.get());
		this.dropSelf(CompendiumBlocks.COMPONENT_DRAWER.get());
		this.dropSelf(CompendiumBlocks.TABLE.get());
		this.dropSelf(CompendiumBlocks.CLOTHED_TABLE.get());
		this.add(CompendiumBlocks.WINDOW.get(), p_248609_ -> this.createMultiMaterialDrop(p_248609_));
	}

	protected LootTable.Builder createMultiMaterialDrop(Block block) {
		return LootTable.lootTable()
				.withPool(this.applyExplosionCondition(block,
						LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
								.add(LootItem.lootTableItem(block)
										.apply(CopyComponentsFunction
												.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
												.include(CompendiumComponents.MULTI_MATERIAL.get())))));
	}

	@Override
	protected @NotNull Iterable<Block> getKnownBlocks() {
		List<Block> a = CompendiumBlocks.BLOCKS.getEntries().stream().map(DeferredHolder::get)
				.collect(Collectors.toList());
		List<Block> b = CompendiumIndex.BLOCKS.getEntries().stream().map(DeferredHolder::get)
				.collect(Collectors.toList());
		a.addAll(b);
		return a;
	}
}
