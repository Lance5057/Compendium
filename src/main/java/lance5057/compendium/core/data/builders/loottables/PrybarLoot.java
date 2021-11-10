package lance5057.compendium.core.data.builders.loottables;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import lance5057.compendium.CompendiumItems;
import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.carpentry.AppendixCarpentry;
import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.library.CompendiumTags;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.TagLootEntry;
import net.minecraft.util.ResourceLocation;

public class PrybarLoot implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {
    public static ResourceLocation generic_machine = new ResourceLocation(Reference.MOD_ID,
	    "recipes/prybar/generic_machine");

    @Override
    public void accept(BiConsumer<ResourceLocation, Builder> t) {
	t.accept(generic_machine, LootTable.builder().addLootPool(LootPool.builder().name("main")
		.rolls(ConstantRange.of(1)).addEntry(TagLootEntry.getBuilder(CompendiumTags.CASING))));

	for (MaterialHelperBase m : AppendixMetallurgy.metals) {
	    t.accept(new ResourceLocation(Reference.MOD_ID, "recipes/prybar/" + m.name + "_machine"),
		    LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(RandomValueRange.of(1, 2))
			    .addEntry(TagLootEntry.getBuilder(TCItemTags.ItemTag("casings/" + m.name)))));
	}
    }

}
