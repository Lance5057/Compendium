//package lance5057.compendium.core.data.builders.workstationrecipes.loottables;
//
//import java.util.function.BiConsumer;
//import java.util.function.Consumer;
//
//import lance5057.compendium.CompendiumItems;
//import lance5057.compendium.Reference;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.level.storage.loot.LootPool;
//import net.minecraft.world.level.storage.loot.LootTable;
//import net.minecraft.world.level.storage.loot.LootTable.Builder;
//import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
//import net.minecraft.world.level.storage.loot.entries.LootItem;
//import net.minecraft.world.level.storage.loot.entries.LootTableReference;
//import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
//import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
//
//public class ScrappingTableLoottables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {
//
//	public static ResourceLocation gold_nugs = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/nested/gold_nuggets");
//
//	public static ResourceLocation iron_ingots = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/nested/iron_ingots");
//	public static ResourceLocation iron_nugs = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/nested/iron_nuggets");
//
//	public static ResourceLocation strings = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/nested/string");
//
//	// Diamond
//	public static ResourceLocation diamond_helm = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/diamond_helm");
//	public static ResourceLocation diamond_chestplate = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/diamond_chestplate");
//	public static ResourceLocation diamond_leggings = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/diamond_leggings");
//	public static ResourceLocation diamond_boots = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/diamond_boots");
//
//	public static ResourceLocation diamond_horse_armor = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/diamond_horse");
//
//	public static ResourceLocation diamond_sword = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/diamond_sword");
//	public static ResourceLocation diamond_pick = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/diamond_pick");
//	public static ResourceLocation diamond_axe = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/diamond_axe");
//	public static ResourceLocation diamond_shovel = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/diamond_shovel");
//	public static ResourceLocation diamond_hoe = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/diamond_hoe");
//
//	// Gold
//	public static ResourceLocation gold_helm = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/gold_helm");
//	public static ResourceLocation gold_chestplate = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/gold_chestplate");
//	public static ResourceLocation gold_leggings = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/gold_leggings");
//	public static ResourceLocation gold_boots = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/gold_boots");
//
//	public static ResourceLocation gold_horse_armor = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/gold_horse");
//
//	public static ResourceLocation gold_sword = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/gold_sword");
//	public static ResourceLocation gold_pick = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/gold_pick");
//	public static ResourceLocation gold_axe = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/gold_axe");
//	public static ResourceLocation gold_shovel = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/gold_shovel");
//	public static ResourceLocation gold_hoe = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/gold_hoe");
//
//	// Iron
//	public static ResourceLocation iron_helm = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/iron_helm");
//	public static ResourceLocation iron_chestplate = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/iron_chestplate");
//	public static ResourceLocation iron_leggings = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/iron_leggings");
//	public static ResourceLocation iron_boots = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/iron_boots");
//
//	public static ResourceLocation chainmail_helm = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/chainmail_helm");
//	public static ResourceLocation chainmail_chestplate = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/chainmail_chestplate");
//	public static ResourceLocation chainmail_leggings = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/chainmail_leggings");
//	public static ResourceLocation chainmail_boots = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/chainmail_boots");
//
//	public static ResourceLocation iron_horse_armor = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/iron_horse");
//
//	// Leather
//	public static ResourceLocation leather_helm = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/leather_helm");
//	public static ResourceLocation leather_chestplate = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/leather_chestplate");
//	public static ResourceLocation leather_leggings = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/leather_leggings");
//	public static ResourceLocation leather_boots = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/leather_boots");
//
//	public static ResourceLocation leather_horse_armor = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/leather_horse");
//
//	public static ResourceLocation iron_sword = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/iron_sword");
//	public static ResourceLocation iron_pick = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/iron_pick");
//	public static ResourceLocation iron_axe = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/iron_axe");
//	public static ResourceLocation iron_shovel = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/iron_shovel");
//	public static ResourceLocation iron_hoe = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/iron_hoe");
//	public static ResourceLocation iron_shears = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/iron_shears");
//
//	public static ResourceLocation iron_trapdoor = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/iron_trapdoor");
//	public static ResourceLocation iron_door = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/iron_door");
//	public static ResourceLocation iron_bars = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/iron_bars");
//	public static ResourceLocation anvil = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/anvil");
//	public static ResourceLocation chippedanvil = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/chippedanvil");
//	public static ResourceLocation damagedanvil = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/damagedanvil");
//	public static ResourceLocation minecart = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/minecart");
//
//	public static ResourceLocation shield = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/shield");
//	public static ResourceLocation bed = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/bed");
//	public static ResourceLocation bowl = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/bowl");
//	public static ResourceLocation fence = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/fence");
//	public static ResourceLocation chest = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/chest");
//	public static ResourceLocation barrel = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/barrel");
//	public static ResourceLocation craftingtable = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/craftingtable");
//	public static ResourceLocation ladder = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/ladder");
//	public static ResourceLocation jukebox = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/jukebox");
//	public static ResourceLocation sign = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/sign");
//	public static ResourceLocation painting = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/painting");
//	public static ResourceLocation frame = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/frame");
//	public static ResourceLocation banner = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/banner");
//	public static ResourceLocation gate = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/gate");
//	public static ResourceLocation door = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/door");
//	public static ResourceLocation trapdoor = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/trapdoor");
//
//	public static ResourceLocation wooden_sword = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/wooden_sword");
//	public static ResourceLocation wooden_pick = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/wooden_pick");
//	public static ResourceLocation wooden_axe = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/wooden_axe");
//	public static ResourceLocation wooden_shovel = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/wooden_shovel");
//	public static ResourceLocation wooden_hoe = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/wooden_hoe");
//
//	public static ResourceLocation enchantingtable = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/enchantingtable");
//	public static ResourceLocation enderchest = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/enderchest");
//
//	public static ResourceLocation furnace = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/furnace");
//	public static ResourceLocation wall = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/wall");
//
//	public static ResourceLocation stone_sword = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/stone_sword");
//	public static ResourceLocation stone_pick = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/stone_pick");
//	public static ResourceLocation stone_axe = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/stone_axe");
//	public static ResourceLocation stone_shovel = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/stone_shovel");
//	public static ResourceLocation stone_hoe = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/stone_hoe");
//
//	public static ResourceLocation loom = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/loom");
//	public static ResourceLocation composter = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/composter");
//	public static ResourceLocation smoker = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/smoker");
//	public static ResourceLocation blast = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/blast");
//	public static ResourceLocation carto = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/carto");
//	public static ResourceLocation fletch = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/fletch");
//	public static ResourceLocation grindstone = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/grindstone");
//	public static ResourceLocation smithing = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/smithing");
//	public static ResourceLocation stonecutter = new ResourceLocation(Reference.MOD_ID,
//			"recipes/scrapping/stonecutter");
//
//	public static ResourceLocation saddle = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/saddle");
//
//	@Override
//	public void accept(BiConsumer<ResourceLocation, Builder> t) {
//		// TODO Auto-generated method stub
//		t.accept(gold_nugs, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(UniformGenerator.between(1, 8)).add(LootItem.lootTableItem(Items.GOLD_NUGGET))));
//		t.accept(iron_nugs, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(UniformGenerator.between(1, 8)).add(LootItem.lootTableItem(Items.IRON_NUGGET))));
//		t.accept(iron_ingots, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(UniformGenerator.between(1, 8)).add(LootItem.lootTableItem(Items.IRON_INGOT))));
//		t.accept(strings, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(UniformGenerator.between(1, 4)).add(LootItem.lootTableItem(Items.STRING))));
//
//		diamond(t);
//		gold(t);
//		iron(t);
//		wood(t);
//		stone(t);
//		leather(t);
//		villager(t);
//		misc(t);
//	}
//
//	private void leather(BiConsumer<ResourceLocation, Builder> t) {
//		t.accept(leather_helm,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(5))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.LEATHER).setWeight(1)))));
//		t.accept(leather_chestplate,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(8))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.LEATHER).setWeight(1)))));
//		t.accept(leather_leggings,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(7))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.LEATHER).setWeight(1)))));
//		t.accept(leather_boots,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(4))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.LEATHER).setWeight(1)))));
//
//		t.accept(leather_horse_armor,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(7))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.LEATHER).setWeight(1)))));
//
//		t.accept(saddle,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(3))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.LEATHER).setWeight(1)))));
//
//	}
//
//	private void diamond(BiConsumer<ResourceLocation, Builder> t) {
//		t.accept(diamond_helm,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(UniformGenerator.between(4, 5))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.DIAMOND).setWeight(1)))));
//		t.accept(diamond_chestplate,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(UniformGenerator.between(7, 8))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.DIAMOND).setWeight(1)))));
//		t.accept(diamond_leggings,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(UniformGenerator.between(6, 7))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.DIAMOND).setWeight(1)))));
//		t.accept(diamond_boots,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(UniformGenerator.between(3, 4))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.DIAMOND).setWeight(1)))));
//		t.accept(diamond_horse_armor,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(UniformGenerator.between(3, 4)).add(
//								AlternativesEntry.alternatives(LootItem.lootTableItem(Items.DIAMOND).setWeight(1))))
//						.withPool(LootPool.lootPool().name("saddle").setRolls(ConstantValue.exactly(1))
//								.add(LootItem.lootTableItem(Items.SADDLE).setWeight(1))));
//
//		t.accept(diamond_sword,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(UniformGenerator.between(1, 2))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.DIAMOND).setWeight(1)))));
//
//		t.accept(diamond_pick,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(UniformGenerator.between(2, 3))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.DIAMOND).setWeight(1)))));
//
//		t.accept(diamond_axe,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(UniformGenerator.between(2, 3))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.DIAMOND).setWeight(1)))));
//
//		t.accept(diamond_shovel,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(UniformGenerator.between(0, 1))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.DIAMOND).setWeight(1)))));
//
//		t.accept(diamond_hoe,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(UniformGenerator.between(1, 2))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.DIAMOND).setWeight(1)))));
//	}
//
//	private void stone(BiConsumer<ResourceLocation, Builder> t) {
//		t.accept(furnace, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(8)).add(LootItem.lootTableItem(Items.COBBLESTONE).setWeight(1))));
//
//		t.accept(wall, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(8)).add(LootItem.lootTableItem(Items.COBBLESTONE).setWeight(1))));
//
//		t.accept(stone_sword,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(2))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.COBBLESTONE).setWeight(1)))));
//
//		t.accept(stone_pick,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(3))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.COBBLESTONE).setWeight(1)))));
//
//		t.accept(stone_axe,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(3))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.COBBLESTONE).setWeight(1)))));
//
//		t.accept(stone_shovel,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(1))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.COBBLESTONE).setWeight(1)))));
//
//		t.accept(stone_hoe,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(2))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.COBBLESTONE).setWeight(1)))));
//	}
//
//	private void villager(BiConsumer<ResourceLocation, Builder> t) {
//		t.accept(stonecutter,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(3))
//								.add(LootItem.lootTableItem(Items.COBBLESTONE).setWeight(1)))
//						.withPool(LootPool.lootPool().name("iron").setRolls(ConstantValue.exactly(1))
//								.add(LootTableReference.lootTableReference(iron_nugs))));
//
//		t.accept(smithing,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(4))
//								.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1)))
//						.withPool(LootPool.lootPool().name("iron").setRolls(ConstantValue.exactly(2))
//								.add(LootTableReference.lootTableReference(iron_nugs))));
//
//		t.accept(grindstone,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(3))
//						.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1))));
//
//		t.accept(fletch,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(4))
//								.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1)))
//						.withPool(LootPool.lootPool().name("flint").setRolls(ConstantValue.exactly(2))
//								.add(LootItem.lootTableItem(Items.FLINT))));
//
//		t.accept(carto,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(4))
//								.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1)))
//						.withPool(LootPool.lootPool().name("paper").setRolls(ConstantValue.exactly(2))
//								.add(LootItem.lootTableItem(Items.PAPER))));
//
//		t.accept(loom,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(2))
//								.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1)))
//						.withPool(LootPool.lootPool().name("string").setRolls(ConstantValue.exactly(2))
//								.add(LootItem.lootTableItem(Items.STRING))));
//
//		t.accept(composter,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(4))
//						.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1))));
//
//		t.accept(smoker,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(16))
//								.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1)))
//						.withPool(LootPool.lootPool().name("furnace").setRolls(ConstantValue.exactly(8))
//								.add(LootItem.lootTableItem(Items.COBBLESTONE))));
//
//		t.accept(blast,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(5))
//								.add(LootTableReference.lootTableReference(iron_nugs).setWeight(1)))
//						.withPool(LootPool.lootPool().name("furnace").setRolls(ConstantValue.exactly(13))
//								.add(LootItem.lootTableItem(Items.COBBLESTONE))));
//	}
//
//	private void misc(BiConsumer<ResourceLocation, Builder> t) {
//		t.accept(enchantingtable,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(4))
//								.add(LootItem.lootTableItem(Items.OBSIDIAN).setWeight(1)))
//						.withPool(LootPool.lootPool().name("diamond").setRolls(UniformGenerator.between(1, 2))
//								.add(LootItem.lootTableItem(Items.DIAMOND).setWeight(1)))
//						.withPool(LootPool.lootPool().name("book").setRolls(ConstantValue.exactly(1))
//								.add(LootItem.lootTableItem(Items.BOOK).setWeight(1))));
//
//		t.accept(enderchest,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(8))
//								.add(LootItem.lootTableItem(Items.OBSIDIAN).setWeight(1)))
//						.withPool(LootPool.lootPool().name("eye").setRolls(UniformGenerator.between(0, 1))
//								.add(LootItem.lootTableItem(Items.ENDER_EYE).setWeight(1))));
//	}
//
//	private void wood(BiConsumer<ResourceLocation, Builder> t) {
//		t.accept(bed,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(3))
//								.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1)))
//						.withPool(LootPool.lootPool().name("wool").setRolls(ConstantValue.exactly(3))
//								.add(LootTableReference.lootTableReference(strings))));
//
//		t.accept(door,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(2))
//						.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1))));
//
//		t.accept(trapdoor,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(3))
//						.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1))));
//
//		t.accept(gate,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(2))
//						.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1))));
//
//		t.accept(painting, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(6)).add(LootTableReference.lootTableReference(strings))));
//
//		t.accept(frame,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(2))
//								.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1)))
//						.withPool(LootPool.lootPool().name("leather").setRolls(ConstantValue.exactly(1))
//								.add(LootItem.lootTableItem(Items.LEATHER).setWeight(1))));
//
//		t.accept(banner,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(2))
//								.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1)))
//						.withPool(LootPool.lootPool().name("wool").setRolls(ConstantValue.exactly(1))
//								.add(LootTableReference.lootTableReference(strings))));
//
//		t.accept(sign,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(2))
//						.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1))));
//
//		t.accept(jukebox,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(1))
//								.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1)))
//						.withPool(LootPool.lootPool().name("diamond").setRolls(UniformGenerator.between(0, 1))
//								.add(LootItem.lootTableItem(Items.DIAMOND).setWeight(1))));
//
//		t.accept(ladder,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(1))
//						.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1))));
//
//		t.accept(bowl,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(1))
//						.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1))));
//
//		t.accept(barrel,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(7))
//						.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1))));
//
//		t.accept(chest,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(8))
//						.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1))));
//
//		t.accept(fence,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(UniformGenerator.between(1, 2))
//						.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1))));
//
//		t.accept(wooden_sword, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(2))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1)))));
//
//		t.accept(wooden_pick, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(3))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1)))));
//
//		t.accept(wooden_axe, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(3))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1)))));
//
//		t.accept(wooden_shovel, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(1))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1)))));
//
//		t.accept(wooden_hoe, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(2))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()).setWeight(1)))));
//	}
//
//	void gold(BiConsumer<ResourceLocation, Builder> t) {
//		t.accept(gold_helm, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(5))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(gold_nugs).setWeight(2)))));
//		t.accept(gold_chestplate, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(8))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(gold_nugs).setWeight(2)))));
//		t.accept(gold_leggings, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(7))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(gold_nugs).setWeight(2)))));
//		t.accept(gold_boots, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(4))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(gold_nugs).setWeight(2)))));
//
//		t.accept(gold_horse_armor,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(4))
//								.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(1)))
//								.add(AlternativesEntry
//										.alternatives(LootTableReference.lootTableReference(gold_nugs).setWeight(2))))
//						.withPool(LootPool.lootPool().name("saddle").setRolls(ConstantValue.exactly(1))
//								.add(LootItem.lootTableItem(Items.SADDLE).setWeight(1))));
//
//		t.accept(gold_sword, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(2))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(gold_nugs).setWeight(2)))));
//
//		t.accept(gold_pick, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(3))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(gold_nugs).setWeight(2)))));
//
//		t.accept(gold_axe, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(3))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(gold_nugs).setWeight(2)))));
//
//		t.accept(gold_shovel, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(1))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(gold_nugs).setWeight(2)))));
//
//		t.accept(gold_hoe, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(2))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.GOLD_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(gold_nugs).setWeight(2)))));
//	}
//
//	void iron(BiConsumer<ResourceLocation, Builder> t) {
//		t.accept(iron_helm, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(5))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(iron_nugs).setWeight(2)))));
//		t.accept(iron_chestplate, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(8))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(iron_nugs).setWeight(2)))));
//		t.accept(iron_leggings, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(7))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(iron_nugs).setWeight(2)))));
//		t.accept(iron_boots, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(4))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(iron_nugs).setWeight(2)))));
//
//		t.accept(chainmail_helm,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(5))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(1)))));
//		t.accept(chainmail_chestplate,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(8))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(1)))));
//		t.accept(chainmail_leggings,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(7))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(1)))));
//		t.accept(chainmail_boots,
//				LootTable.lootTable().withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(4))
//						.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(1)))));
//
//		t.accept(iron_horse_armor,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(4))
//								.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(1)))
//								.add(AlternativesEntry
//										.alternatives(LootTableReference.lootTableReference(iron_nugs).setWeight(2))))
//						.withPool(LootPool.lootPool().name("saddle").setRolls(ConstantValue.exactly(1))
//								.add(LootItem.lootTableItem(Items.SADDLE).setWeight(1))));
//
//		t.accept(iron_sword, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(2))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(iron_nugs).setWeight(2)))));
//
//		t.accept(iron_pick, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(3))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(iron_nugs).setWeight(2)))));
//
//		t.accept(iron_axe, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(3))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(iron_nugs).setWeight(2)))));
//
//		t.accept(iron_shovel, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(1))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(iron_nugs).setWeight(2)))));
//
//		t.accept(iron_hoe, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(2))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(iron_nugs).setWeight(2)))));
//
//		t.accept(iron_shears, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(2))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(iron_nugs).setWeight(2)))));
//
//		t.accept(iron_trapdoor, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(4))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(iron_nugs).setWeight(2)))));
//
//		t.accept(iron_door, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(2))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(iron_nugs).setWeight(2)))));
//
//		t.accept(minecart, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(5))
//				.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(1)))
//				.add(AlternativesEntry.alternatives(LootTableReference.lootTableReference(iron_nugs).setWeight(2)))));
//
//		t.accept(iron_bars, LootTable.lootTable().withPool(LootPool.lootPool().name("main")
//				.setRolls(ConstantValue.exactly(3)).add(LootItem.lootTableItem(Items.IRON_NUGGET).setWeight(1))));
//
//		t.accept(shield,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(1))
//								.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(1)))
//								.add(AlternativesEntry
//										.alternatives(LootTableReference.lootTableReference(iron_nugs).setWeight(2))))
//						.withPool(LootPool.lootPool().name("wood").setRolls(ConstantValue.exactly(5))
//								.add(LootItem.lootTableItem(CompendiumItems.SAWDUST.get()))));
//
//		t.accept(anvil,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(3))
//								.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_BLOCK).setWeight(1)))
//								.add(AlternativesEntry
//										.alternatives(LootTableReference.lootTableReference(iron_ingots).setWeight(2))))
//						.withPool(LootPool.lootPool().name("base").setRolls(ConstantValue.exactly(4))
//								.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(1)))
//								.add(AlternativesEntry
//										.alternatives(LootTableReference.lootTableReference(iron_nugs).setWeight(2)))));
//
//		t.accept(chippedanvil,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(ConstantValue.exactly(2))
//								.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_BLOCK).setWeight(1)))
//								.add(AlternativesEntry
//										.alternatives(LootTableReference.lootTableReference(iron_ingots).setWeight(2))))
//						.withPool(LootPool.lootPool().name("base").setRolls(ConstantValue.exactly(3))
//								.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(1)))
//								.add(AlternativesEntry
//										.alternatives(LootTableReference.lootTableReference(iron_nugs).setWeight(2)))));
//
//		t.accept(damagedanvil,
//				LootTable.lootTable()
//						.withPool(LootPool.lootPool().name("main").setRolls(UniformGenerator.between(0, 1))
//								.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_BLOCK).setWeight(1)))
//								.add(AlternativesEntry
//										.alternatives(LootTableReference.lootTableReference(iron_ingots).setWeight(2))))
//						.withPool(LootPool.lootPool().name("base").setRolls(ConstantValue.exactly(2))
//								.add(AlternativesEntry.alternatives(LootItem.lootTableItem(Items.IRON_INGOT).setWeight(1)))
//								.add(AlternativesEntry
//										.alternatives(LootTableReference.lootTableReference(iron_nugs).setWeight(2)))));
//	}
//
//}
