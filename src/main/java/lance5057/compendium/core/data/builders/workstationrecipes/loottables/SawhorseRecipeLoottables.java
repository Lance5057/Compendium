package lance5057.compendium.core.data.builders.workstationrecipes.loottables;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import lance5057.compendium.Reference;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.util.ResourceLocation;

public class SawhorseRecipeLoottables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {

	@Override
	public void accept(BiConsumer<ResourceLocation, Builder> t) {
		// TODO Auto-generated method stub
		t.accept(new ResourceLocation(Reference.MOD_ID, "recipes/sawhorse/oak_planks"), LootTable.builder().addLootPool(LootPool.builder().rolls(ConstantRange.of(4)).addEntry(ItemLootEntry.builder(Items.STICK))));
	}
}
