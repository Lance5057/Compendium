package lance5057.compendium.core.data.builders.workstationrecipes.loottables;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import lance5057.compendium.CompendiumItems;
import lance5057.compendium.Reference;
import net.minecraft.item.Items;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.util.ResourceLocation;

public class SawhorseRecipeLoottables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {

    public static ResourceLocation allplanks = new ResourceLocation("recipes/sawhorse/planks");

    @Override
    public void accept(BiConsumer<ResourceLocation, Builder> t) {
	// TODO Auto-generated method stub
	t.accept(allplanks,
		LootTable.builder()
			.addLootPool(LootPool.builder().name("main").rolls(RandomValueRange.of(4, 6))
				.addEntry(ItemLootEntry.builder(Items.STICK).weight(1)))
			.addLootPool(LootPool.builder().name("extra").rolls(RandomValueRange.of(1, 4))
				.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1))));

	t.accept(new ResourceLocation(Reference.MOD_ID, "recipes/sawhorse/oak_log"),
		LootTable.builder()
			.addLootPool(LootPool.builder().name("main").rolls(RandomValueRange.of(4, 6))
				.addEntry(ItemLootEntry.builder(Items.STICK).weight(1)))
			.addLootPool(LootPool.builder().name("extra").rolls(RandomValueRange.of(1, 4))
				.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1))));
    }
}
