//package lance5057.compendium.core.data.builders.loottables;
//
//import java.util.function.BiConsumer;
//import java.util.function.Consumer;
//
//import lance5057.compendium.Reference;
//import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
//import lance5057.compendium.core.data.builders.TCItemTags;
//import lance5057.compendium.core.library.CompendiumTags;
//import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.storage.loot.LootPool;
//import net.minecraft.world.level.storage.loot.LootTable;
//import net.minecraft.world.level.storage.loot.LootTable.Builder;
//import net.minecraft.world.level.storage.loot.entries.TagEntry;
//import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
//import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
//
//public class PrybarLoot implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {
//	public static ResourceLocation generic_machine = new ResourceLocation(Reference.MOD_ID,
//			"recipes/prybar/generic_machine");
//
//	@Override
//	public void accept(BiConsumer<ResourceLocation, Builder> t) {
//		t.accept(generic_machine, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(1)).add(TagEntry.expandTag(CompendiumTags.CASING))));
//
//		for (MaterialHelperBase m : AppendixMetallurgy.metals) {
//			t.accept(new ResourceLocation(Reference.MOD_ID, "recipes/prybar/" + m.name + "_machine"),
//					LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(UniformGenerator
//							.between(1, 2))
//							.add(TagEntry.expandTag(TCItemTags.ItemTag("casings/" + m.name)))));
//		}
//	}
//
//}
