package lance5057.compendium.core.data.builders.workstationrecipes.loottables;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import lance5057.compendium.CompendiumItems;
import lance5057.compendium.Reference;
import net.minecraft.item.Items;
import net.minecraft.loot.AlternativesLootEntry;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;

public class ScrappingTableLoottables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {

    public static ResourceLocation gold_nugs = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/nested/gold_nuggets");

    public static ResourceLocation iron_ingots = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/nested/iron_ingots");
    public static ResourceLocation iron_nugs = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/nested/iron_nuggets");

    public static ResourceLocation strings = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/nested/string");

    // Diamond
    public static ResourceLocation diamond_helm = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/diamond_helm");
    public static ResourceLocation diamond_chestplate = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/diamond_chestplate");
    public static ResourceLocation diamond_leggings = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/diamond_leggings");
    public static ResourceLocation diamond_boots = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/diamond_boots");

    public static ResourceLocation diamond_horse_armor = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/diamond_horse");

    public static ResourceLocation diamond_sword = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/diamond_sword");
    public static ResourceLocation diamond_pick = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/diamond_pick");
    public static ResourceLocation diamond_axe = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/diamond_axe");
    public static ResourceLocation diamond_shovel = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/diamond_shovel");
    public static ResourceLocation diamond_hoe = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/diamond_hoe");

    // Gold
    public static ResourceLocation gold_helm = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/gold_helm");
    public static ResourceLocation gold_chestplate = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/gold_chestplate");
    public static ResourceLocation gold_leggings = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/gold_leggings");
    public static ResourceLocation gold_boots = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/gold_boots");

    public static ResourceLocation gold_horse_armor = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/gold_horse");

    public static ResourceLocation gold_sword = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/gold_sword");
    public static ResourceLocation gold_pick = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/gold_pick");
    public static ResourceLocation gold_axe = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/gold_axe");
    public static ResourceLocation gold_shovel = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/gold_shovel");
    public static ResourceLocation gold_hoe = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/gold_hoe");

    // Iron
    public static ResourceLocation iron_helm = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/iron_helm");
    public static ResourceLocation iron_chestplate = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/iron_chestplate");
    public static ResourceLocation iron_leggings = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/iron_leggings");
    public static ResourceLocation iron_boots = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/iron_boots");

    public static ResourceLocation chainmail_helm = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/chainmail_helm");
    public static ResourceLocation chainmail_chestplate = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/chainmail_chestplate");
    public static ResourceLocation chainmail_leggings = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/chainmail_leggings");
    public static ResourceLocation chainmail_boots = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/chainmail_boots");

    public static ResourceLocation iron_horse_armor = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/iron_horse");

    // Leather
    public static ResourceLocation leather_helm = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/leather_helm");
    public static ResourceLocation leather_chestplate = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/leather_chestplate");
    public static ResourceLocation leather_leggings = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/leather_leggings");
    public static ResourceLocation leather_boots = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/leather_boots");

    public static ResourceLocation leather_horse_armor = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/leather_horse");

    public static ResourceLocation iron_sword = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/iron_sword");
    public static ResourceLocation iron_pick = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/iron_pick");
    public static ResourceLocation iron_axe = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/iron_axe");
    public static ResourceLocation iron_shovel = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/iron_shovel");
    public static ResourceLocation iron_hoe = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/iron_hoe");
    public static ResourceLocation iron_shears = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/iron_shears");

    public static ResourceLocation iron_trapdoor = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/iron_trapdoor");
    public static ResourceLocation iron_door = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/iron_door");
    public static ResourceLocation iron_bars = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/iron_bars");
    public static ResourceLocation anvil = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/anvil");
    public static ResourceLocation chippedanvil = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/chippedanvil");
    public static ResourceLocation damagedanvil = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/damagedanvil");
    public static ResourceLocation minecart = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/minecart");

    public static ResourceLocation shield = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/shield");
    public static ResourceLocation bed = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/bed");
    public static ResourceLocation bowl = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/bowl");
    public static ResourceLocation fence = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/fence");
    public static ResourceLocation chest = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/chest");
    public static ResourceLocation barrel = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/barrel");
    public static ResourceLocation craftingtable = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/craftingtable");
    public static ResourceLocation ladder = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/ladder");
    public static ResourceLocation jukebox = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/jukebox");
    public static ResourceLocation sign = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/sign");
    public static ResourceLocation painting = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/painting");
    public static ResourceLocation frame = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/frame");
    public static ResourceLocation banner = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/banner");
    public static ResourceLocation gate = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/gate");
    public static ResourceLocation door = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/door");
    public static ResourceLocation trapdoor = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/trapdoor");

    public static ResourceLocation wooden_sword = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/wooden_sword");
    public static ResourceLocation wooden_pick = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/wooden_pick");
    public static ResourceLocation wooden_axe = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/wooden_axe");
    public static ResourceLocation wooden_shovel = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/wooden_shovel");
    public static ResourceLocation wooden_hoe = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/wooden_hoe");

    public static ResourceLocation enchantingtable = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/enchantingtable");
    public static ResourceLocation enderchest = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/enderchest");

    public static ResourceLocation furnace = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/furnace");
    public static ResourceLocation wall = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/wall");

    public static ResourceLocation stone_sword = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/stone_sword");
    public static ResourceLocation stone_pick = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/stone_pick");
    public static ResourceLocation stone_axe = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/stone_axe");
    public static ResourceLocation stone_shovel = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/stone_shovel");
    public static ResourceLocation stone_hoe = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/stone_hoe");

    public static ResourceLocation loom = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/loom");
    public static ResourceLocation composter = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/composter");
    public static ResourceLocation smoker = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/smoker");
    public static ResourceLocation blast = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/blast");
    public static ResourceLocation carto = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/carto");
    public static ResourceLocation fletch = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/fletch");
    public static ResourceLocation grindstone = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/grindstone");
    public static ResourceLocation smithing = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/smithing");
    public static ResourceLocation stonecutter = new ResourceLocation(Reference.MOD_ID,
	    "recipes/scrapping/stonecutter");

    public static ResourceLocation saddle = new ResourceLocation(Reference.MOD_ID, "recipes/scrapping/saddle");

    @Override
    public void accept(BiConsumer<ResourceLocation, Builder> t) {
	// TODO Auto-generated method stub
	t.accept(gold_nugs, LootTable.builder().addLootPool(LootPool.builder().name("main")
		.rolls(RandomValueRange.of(1, 8)).addEntry(ItemLootEntry.builder(Items.GOLD_NUGGET))));
	t.accept(iron_nugs, LootTable.builder().addLootPool(LootPool.builder().name("main")
		.rolls(RandomValueRange.of(1, 8)).addEntry(ItemLootEntry.builder(Items.IRON_NUGGET))));
	t.accept(iron_ingots, LootTable.builder().addLootPool(LootPool.builder().name("main")
		.rolls(RandomValueRange.of(1, 8)).addEntry(ItemLootEntry.builder(Items.IRON_INGOT))));
	t.accept(strings, LootTable.builder().addLootPool(LootPool.builder().name("main")
		.rolls(RandomValueRange.of(1, 4)).addEntry(ItemLootEntry.builder(Items.STRING))));

	diamond(t);
	gold(t);
	iron(t);
	wood(t);
	stone(t);
	leather(t);
	villager(t);
	misc(t);
    }

    private void leather(BiConsumer<ResourceLocation, Builder> t) {
	t.accept(leather_helm,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(5))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.LEATHER).weight(1)))));
	t.accept(leather_chestplate,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(8))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.LEATHER).weight(1)))));
	t.accept(leather_leggings,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(7))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.LEATHER).weight(1)))));
	t.accept(leather_boots,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(4))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.LEATHER).weight(1)))));

	t.accept(leather_horse_armor,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(7))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.LEATHER).weight(1)))));

	t.accept(saddle, LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(3))
		.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.LEATHER).weight(1)))));

    }

    private void diamond(BiConsumer<ResourceLocation, Builder> t) {
	t.accept(diamond_helm,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(RandomValueRange.of(4, 5))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.DIAMOND).weight(1)))));
	t.accept(diamond_chestplate,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(RandomValueRange.of(7, 8))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.DIAMOND).weight(1)))));
	t.accept(diamond_leggings,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(RandomValueRange.of(6, 7))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.DIAMOND).weight(1)))));
	t.accept(diamond_boots,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(RandomValueRange.of(3, 4))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.DIAMOND).weight(1)))));
	t.accept(diamond_horse_armor,
		LootTable.builder()
			.addLootPool(LootPool.builder().name("main").rolls(RandomValueRange.of(3, 4)).addEntry(
				AlternativesLootEntry.builder(ItemLootEntry.builder(Items.DIAMOND).weight(1))))
			.addLootPool(LootPool.builder().name("saddle").rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(Items.SADDLE).weight(1))));

	t.accept(diamond_sword,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(RandomValueRange.of(1, 2))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.DIAMOND).weight(1)))));

	t.accept(diamond_pick,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(RandomValueRange.of(2, 3))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.DIAMOND).weight(1)))));

	t.accept(diamond_axe,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(RandomValueRange.of(2, 3))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.DIAMOND).weight(1)))));

	t.accept(diamond_shovel,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(RandomValueRange.of(0, 1))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.DIAMOND).weight(1)))));

	t.accept(diamond_hoe,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(RandomValueRange.of(1, 2))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.DIAMOND).weight(1)))));
    }

    private void stone(BiConsumer<ResourceLocation, Builder> t) {
	t.accept(furnace, LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(8))
		.addEntry(ItemLootEntry.builder(Items.COBBLESTONE).weight(1))));

	t.accept(wall, LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(8))
		.addEntry(ItemLootEntry.builder(Items.COBBLESTONE).weight(1))));

	t.accept(stone_sword, LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(2))
		.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.COBBLESTONE).weight(1)))));

	t.accept(stone_pick, LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(3))
		.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.COBBLESTONE).weight(1)))));

	t.accept(stone_axe, LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(3))
		.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.COBBLESTONE).weight(1)))));

	t.accept(stone_shovel,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(1))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.COBBLESTONE).weight(1)))));

	t.accept(stone_hoe, LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(2))
		.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.COBBLESTONE).weight(1)))));
    }

    private void villager(BiConsumer<ResourceLocation, Builder> t) {
	t.accept(stonecutter,
		LootTable.builder()
			.addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(3))
				.addEntry(ItemLootEntry.builder(Items.COBBLESTONE).weight(1)))
			.addLootPool(LootPool.builder().name("iron").rolls(ConstantRange.of(1))
				.addEntry(TableLootEntry.builder(iron_nugs))));

	t.accept(smithing,
		LootTable.builder()
			.addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(4))
				.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1)))
			.addLootPool(LootPool.builder().name("iron").rolls(ConstantRange.of(2))
				.addEntry(TableLootEntry.builder(iron_nugs))));

	t.accept(grindstone, LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(3))
		.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1))));

	t.accept(fletch,
		LootTable.builder()
			.addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(4))
				.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1)))
			.addLootPool(LootPool.builder().name("flint").rolls(ConstantRange.of(2))
				.addEntry(ItemLootEntry.builder(Items.FLINT))));

	t.accept(carto,
		LootTable.builder()
			.addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(4))
				.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1)))
			.addLootPool(LootPool.builder().name("paper").rolls(ConstantRange.of(2))
				.addEntry(ItemLootEntry.builder(Items.PAPER))));

	t.accept(loom,
		LootTable.builder()
			.addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(2))
				.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1)))
			.addLootPool(LootPool.builder().name("string").rolls(ConstantRange.of(2))
				.addEntry(ItemLootEntry.builder(Items.STRING))));

	t.accept(composter, LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(4))
		.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1))));

	t.accept(smoker,
		LootTable.builder()
			.addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(16))
				.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1)))
			.addLootPool(LootPool.builder().name("furnace").rolls(ConstantRange.of(8))
				.addEntry(ItemLootEntry.builder(Items.COBBLESTONE))));

	t.accept(blast,
		LootTable.builder()
			.addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(5))
				.addEntry(TableLootEntry.builder(iron_nugs).weight(1)))
			.addLootPool(LootPool.builder().name("furnace").rolls(ConstantRange.of(13))
				.addEntry(ItemLootEntry.builder(Items.COBBLESTONE))));
    }

    private void misc(BiConsumer<ResourceLocation, Builder> t) {
	t.accept(enchantingtable,
		LootTable.builder()
			.addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(4))
				.addEntry(ItemLootEntry.builder(Items.OBSIDIAN).weight(1)))
			.addLootPool(LootPool.builder().name("diamond").rolls(RandomValueRange.of(1, 2))
				.addEntry(ItemLootEntry.builder(Items.DIAMOND).weight(1)))
			.addLootPool(LootPool.builder().name("book").rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(Items.BOOK).weight(1))));

	t.accept(enderchest,
		LootTable.builder()
			.addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(8))
				.addEntry(ItemLootEntry.builder(Items.OBSIDIAN).weight(1)))
			.addLootPool(LootPool.builder().name("eye").rolls(RandomValueRange.of(0, 1))
				.addEntry(ItemLootEntry.builder(Items.ENDER_EYE).weight(1))));
    }

    private void wood(BiConsumer<ResourceLocation, Builder> t) {
	t.accept(bed,
		LootTable.builder()
			.addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(3))
				.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1)))
			.addLootPool(LootPool.builder().name("wool").rolls(ConstantRange.of(3))
				.addEntry(TableLootEntry.builder(strings))));

	t.accept(door, LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(2))
		.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1))));

	t.accept(trapdoor, LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(3))
		.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1))));

	t.accept(gate, LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(2))
		.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1))));

	t.accept(painting, LootTable.builder().addLootPool(
		LootPool.builder().name("main").rolls(ConstantRange.of(6)).addEntry(TableLootEntry.builder(strings))));

	t.accept(frame,
		LootTable.builder()
			.addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(2))
				.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1)))
			.addLootPool(LootPool.builder().name("leather").rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(Items.LEATHER).weight(1))));

	t.accept(banner,
		LootTable.builder()
			.addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(2))
				.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1)))
			.addLootPool(LootPool.builder().name("wool").rolls(ConstantRange.of(1))
				.addEntry(TableLootEntry.builder(strings))));

	t.accept(sign, LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(2))
		.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1))));

	t.accept(jukebox,
		LootTable.builder()
			.addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1)))
			.addLootPool(LootPool.builder().name("diamond").rolls(RandomValueRange.of(0, 1))
				.addEntry(ItemLootEntry.builder(Items.DIAMOND).weight(1))));

	t.accept(ladder, LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(1))
		.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1))));

	t.accept(bowl, LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(1))
		.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1))));

	t.accept(barrel, LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(7))
		.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1))));

	t.accept(chest, LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(8))
		.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1))));

	t.accept(fence, LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(RandomValueRange.of(1, 2))
		.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1))));

	t.accept(wooden_sword,
		LootTable.builder().addLootPool(
			LootPool.builder().name("main").rolls(ConstantRange.of(2)).addEntry(AlternativesLootEntry
				.builder(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1)))));

	t.accept(wooden_pick,
		LootTable.builder().addLootPool(
			LootPool.builder().name("main").rolls(ConstantRange.of(3)).addEntry(AlternativesLootEntry
				.builder(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1)))));

	t.accept(wooden_axe,
		LootTable.builder().addLootPool(
			LootPool.builder().name("main").rolls(ConstantRange.of(3)).addEntry(AlternativesLootEntry
				.builder(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1)))));

	t.accept(wooden_shovel,
		LootTable.builder().addLootPool(
			LootPool.builder().name("main").rolls(ConstantRange.of(1)).addEntry(AlternativesLootEntry
				.builder(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1)))));

	t.accept(wooden_hoe,
		LootTable.builder().addLootPool(
			LootPool.builder().name("main").rolls(ConstantRange.of(2)).addEntry(AlternativesLootEntry
				.builder(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()).weight(1)))));
    }

    void gold(BiConsumer<ResourceLocation, Builder> t) {
	t.accept(gold_helm,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(5))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.GOLD_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(gold_nugs).weight(2)))));
	t.accept(gold_chestplate,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(8))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.GOLD_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(gold_nugs).weight(2)))));
	t.accept(gold_leggings,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(7))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.GOLD_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(gold_nugs).weight(2)))));
	t.accept(gold_boots,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(4))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.GOLD_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(gold_nugs).weight(2)))));

	t.accept(gold_horse_armor,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(4))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.GOLD_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(gold_nugs).weight(2))))
			.addLootPool(LootPool.builder().name("saddle").rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(Items.SADDLE).weight(1))));

	t.accept(gold_sword,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(2))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.GOLD_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(gold_nugs).weight(2)))));

	t.accept(gold_pick,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(3))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.GOLD_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(gold_nugs).weight(2)))));

	t.accept(gold_axe,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(3))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.GOLD_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(gold_nugs).weight(2)))));

	t.accept(gold_shovel,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(1))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.GOLD_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(gold_nugs).weight(2)))));

	t.accept(gold_hoe,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(2))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.GOLD_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(gold_nugs).weight(2)))));
    }

    void iron(BiConsumer<ResourceLocation, Builder> t) {
	t.accept(iron_helm,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(5))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_nugs).weight(2)))));
	t.accept(iron_chestplate,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(8))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_nugs).weight(2)))));
	t.accept(iron_leggings,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(7))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_nugs).weight(2)))));
	t.accept(iron_boots,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(4))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_nugs).weight(2)))));

	t.accept(chainmail_helm,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(5))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_NUGGET).weight(1)))));
	t.accept(chainmail_chestplate,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(8))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_NUGGET).weight(1)))));
	t.accept(chainmail_leggings,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(7))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_NUGGET).weight(1)))));
	t.accept(chainmail_boots,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(4))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_NUGGET).weight(1)))));

	t.accept(iron_horse_armor,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(4))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_nugs).weight(2))))
			.addLootPool(LootPool.builder().name("saddle").rolls(ConstantRange.of(1))
				.addEntry(ItemLootEntry.builder(Items.SADDLE).weight(1))));

	t.accept(iron_sword,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(2))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_nugs).weight(2)))));

	t.accept(iron_pick,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(3))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_nugs).weight(2)))));

	t.accept(iron_axe,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(3))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_nugs).weight(2)))));

	t.accept(iron_shovel,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(1))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_nugs).weight(2)))));

	t.accept(iron_hoe,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(2))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_nugs).weight(2)))));

	t.accept(iron_shears,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(2))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_nugs).weight(2)))));

	t.accept(iron_trapdoor,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(4))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_nugs).weight(2)))));

	t.accept(iron_door,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(2))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_nugs).weight(2)))));

	t.accept(minecart,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(5))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_nugs).weight(2)))));

	t.accept(iron_bars, LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(3))
		.addEntry(ItemLootEntry.builder(Items.IRON_NUGGET).weight(1))));

	t.accept(shield,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(1))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_nugs).weight(2))))
			.addLootPool(LootPool.builder().name("wood").rolls(ConstantRange.of(5))
				.addEntry(ItemLootEntry.builder(CompendiumItems.SAWDUST.get()))));

	t.accept(anvil,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(3))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_BLOCK).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_ingots).weight(2))))
			.addLootPool(LootPool.builder().name("base").rolls(ConstantRange.of(4))
				.addEntry(AlternativesLootEntry
					.builder(ItemLootEntry.builder(Items.IRON_INGOT).weight(1)))
				.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_nugs).weight(2)))));

	t.accept(chippedanvil,
		LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(2))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_BLOCK).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_ingots).weight(2))))
			.addLootPool(LootPool.builder().name("base").rolls(ConstantRange.of(3))
				.addEntry(AlternativesLootEntry
					.builder(ItemLootEntry.builder(Items.IRON_INGOT).weight(1)))
				.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_nugs).weight(2)))));

	t.accept(damagedanvil, LootTable.builder()
		.addLootPool(LootPool.builder().name("main").rolls(RandomValueRange.of(0, 1))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_BLOCK).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_ingots).weight(2))))
		.addLootPool(LootPool.builder().name("base").rolls(ConstantRange.of(2))
			.addEntry(AlternativesLootEntry.builder(ItemLootEntry.builder(Items.IRON_INGOT).weight(1)))
			.addEntry(AlternativesLootEntry.builder(TableLootEntry.builder(iron_nugs).weight(2)))));
    }

}
