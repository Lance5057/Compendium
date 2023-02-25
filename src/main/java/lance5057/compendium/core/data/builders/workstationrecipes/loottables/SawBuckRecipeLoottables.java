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

public class SawBuckRecipeLoottables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {

	public static ResourceLocation allplanks = new ResourceLocation("recipes/sawhorse/planks");

	@Override
	public void accept(BiConsumer<ResourceLocation, Builder> t) {
		// TODO Auto-generated method stub
		t.accept(allplanks, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
				.setRolls(UniformGenerator.between(4, 6)).add(LootItem.lootTableItem(Items.STICK).setWeight(1)))
//						.withPool(LootPool.lootPool().name("extra").setRolls(UniformGenerator.between(1, 4))
//								.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1)))
		);

		t.accept(new ResourceLocation(Reference.MOD_ID, "recipes/sawhorse/oak_log"),
				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(UniformGenerator.between(4, 6))
						.add(LootItem.lootTableItem(Items.STICK).setWeight(1)))
//						.withPool(LootPool.lootPool().name("extra").setRolls(UniformGenerator.between(1, 4))
//								.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1)))
		);
	}
}