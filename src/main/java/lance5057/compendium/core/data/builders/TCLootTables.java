package lance5057.compendium.core.data.builders;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import lance5057.compendium.Compendium;
import lance5057.compendium.appendixes.metallurgy.data.builders.loottables.MetalBlockLoot;
import lance5057.compendium.core.data.builders.loottables.BlockLoot;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTableManager;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.util.ResourceLocation;

public class TCLootTables extends LootTableProvider {

    public TCLootTables(DataGenerator dataGeneratorIn) {
	super(dataGeneratorIn);
    }

    @Override
    @Nonnull
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
	return ImmutableList.of(Pair.of(BlockLoot::new, LootParameterSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, @Nonnull ValidationTracker validationtracker) {
	map.forEach((name, table) -> LootTableManager.validateLootTable(validationtracker, name, table));
    }
}
