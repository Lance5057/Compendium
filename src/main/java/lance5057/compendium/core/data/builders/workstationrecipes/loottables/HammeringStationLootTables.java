package lance5057.compendium.core.data.builders.workstationrecipes.loottables;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import lance5057.compendium.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class HammeringStationLootTables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {

	public static ResourceLocation cobble_gravel = new ResourceLocation(Reference.MOD_ID,
			"recipes/hammeringstation/cobble_gravel");

	@Override
	public void accept(BiConsumer<ResourceLocation, Builder> t) {
		t.accept(cobble_gravel, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
				.setRolls(UniformGenerator.between(0, 2)).add(LootItem.lootTableItem(Items.FLINT).setWeight(1))));
	}

}
