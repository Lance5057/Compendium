package lance5057.compendium.indexes.metals;

import java.util.ArrayList;
import java.util.List;

import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.appendixes.oredressing.AppendixOreDressing;
import lance5057.compendium.appendixes.oredressing.materialhelper.OreDressingMaterialHelper;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.library.CompendiumItemTier;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.biome.Biome.Category;
import net.minecraftforge.common.ToolType;

public class IndexBaseMetals {
    //List<List<MaterialHelperBase>> ALL = new ArrayList<List<MaterialHelperBase>>();

    public static List<MaterialHelperBase> BRASS = new ArrayList<MaterialHelperBase>();
    public static IItemTier BRASS_TIER = new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("ingots/brass"));
    });

    public static List<MaterialHelperBase> BRONZE = new ArrayList<MaterialHelperBase>();
    public static IItemTier BRONZE_TIER = new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("ingots/bronze"));
    });

    public static List<MaterialHelperBase> COPPER = new ArrayList<MaterialHelperBase>();
    public static IItemTier COPPER_TIER = new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("ingots/copper"));
    });

    public static List<MaterialHelperBase> ELECTRUM = new ArrayList<MaterialHelperBase>();
    public static IItemTier ELECTRUM_TIER = new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("ingots/electrum"));
    });

    public static List<MaterialHelperBase> NICKEL = new ArrayList<MaterialHelperBase>();
    public static IItemTier NICKEL_TIER = new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("ingots/nickel"));
    });

    public static List<MaterialHelperBase> STEEL = new ArrayList<MaterialHelperBase>();
    public static IItemTier STEEL_TIER = new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("ingots/steel"));
    });

    public static List<MaterialHelperBase> TIN = new ArrayList<MaterialHelperBase>();
    public static IItemTier TIN_TIER = new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("ingots/tin"));
    });

    public static List<MaterialHelperBase> PEWTER = new ArrayList<MaterialHelperBase>();
    public static IItemTier PEWTER_TIER = new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("ingots/pewter"));
    });

    public static List<MaterialHelperBase> ZINC = new ArrayList<MaterialHelperBase>();
    public static IItemTier ZINC_TIER = new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("ingots/zinc"));
    });

    public static List<MaterialHelperBase> ROSEGOLD = new ArrayList<MaterialHelperBase>();
    public static IItemTier ROSEGOLD_TIER = new CompendiumItemTier(0, 99, 4f, 1f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("ingots/rosegold"));
    });

    public static List<MaterialHelperBase> PLATINUM = new ArrayList<MaterialHelperBase>();
    public static IItemTier PLATINUM_TIER = new CompendiumItemTier(1, 99, 3.7f, 4f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("ingots/platinum"));
    });

    public static List<MaterialHelperBase> SILVER = new ArrayList<MaterialHelperBase>();
    public static IItemTier SILVER_TIER = new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("ingots/silver"));
    });

    public static List<MaterialHelperBase> LEAD = new ArrayList<MaterialHelperBase>();
    public static IItemTier LEAD_TIER = new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("ingots/lead"));
    });

    public IndexBaseMetals() {
	// BRASS ------------------------------------------------------//
	MetallurgyMaterialHelper brass = new MetallurgyMaterialHelper("brass", IndexBaseMetals.BRASS_TIER).withBase()
		.withVanillaTools().withComponents().withAdvancedTools();
	BRASS.add(brass);
	AppendixMetallurgy.metals.add(brass);

	// BRONZE ------------------------------------------------------//
	MetallurgyMaterialHelper bronze = new MetallurgyMaterialHelper("bronze", IndexBaseMetals.BRONZE_TIER).withBase()
		.withVanillaTools().withComponents().withAdvancedTools();
	BRONZE.add(bronze);
	AppendixMetallurgy.metals.add(bronze);

	// COPPER ------------------------------------------------------//
	MetallurgyMaterialHelper copper = new MetallurgyMaterialHelper("copper", IndexBaseMetals.COPPER_TIER).withBase()
		.withVanillaTools().withComponents().withAdvancedTools();
	COPPER.add(copper);
	AppendixMetallurgy.metals.add(copper);
	OreDressingMaterialHelper oreCopper = new OreDressingMaterialHelper("copper")
		.withOre(4, 2, ToolType.PICKAXE, 3, 64, 32, 8, 50, Category.NONE)
		.withDenseOre(5, 2, ToolType.PICKAXE, 4, 64, 32, 4, 25, Category.NONE)
		.withSparseOre(3, 2, ToolType.PICKAXE, 2, 64, 32, 8, 4, Category.NONE);
	COPPER.add(oreCopper);
	AppendixOreDressing.ores.add(oreCopper);

	// ELECTRUM ------------------------------------------------------//
	MetallurgyMaterialHelper electrum = new MetallurgyMaterialHelper("electrum", IndexBaseMetals.ELECTRUM_TIER)
		.withBase().withVanillaTools().withComponents().withAdvancedTools();
	ELECTRUM.add(electrum);
	AppendixMetallurgy.metals.add(electrum);

	// NICKEL ------------------------------------------------------//
	MetallurgyMaterialHelper nickel = new MetallurgyMaterialHelper("nickel", IndexBaseMetals.NICKEL_TIER).withBase()
		.withVanillaTools().withComponents().withAdvancedTools();
	NICKEL.add(nickel);
	AppendixMetallurgy.metals.add(nickel);
	OreDressingMaterialHelper oreNickel = new OreDressingMaterialHelper("nickel")
		.withOre(4, 3, ToolType.PICKAXE, 3, 32, 16, 4, 50, Category.NONE)
		.withDenseOre(5, 3, ToolType.PICKAXE, 4, 32, 16, 4, 25, Category.NONE)
		.withSparseOre(3, 3, ToolType.PICKAXE, 2, 32, 16, 4, 4, Category.NONE);
	NICKEL.add(oreNickel);
	AppendixOreDressing.ores.add(oreNickel);

	// STEEL ------------------------------------------------------//
	MetallurgyMaterialHelper steel = new MetallurgyMaterialHelper("steel", IndexBaseMetals.STEEL_TIER).withBase()
		.withVanillaTools().withComponents().withAdvancedTools();
	STEEL.add(steel);
	AppendixMetallurgy.metals.add(steel);

	// TIN ------------------------------------------------------//
	MetallurgyMaterialHelper tin = new MetallurgyMaterialHelper("tin", IndexBaseMetals.TIN_TIER).withBase()
		.withVanillaTools().withComponents().withAdvancedTools();
	TIN.add(tin);
	AppendixMetallurgy.metals.add(tin);
	OreDressingMaterialHelper oreTin = new OreDressingMaterialHelper("tin")
		.withOre(4, 2, ToolType.PICKAXE, 3, 64, 32, 8, 50, Category.NONE)
		.withDenseOre(5, 2, ToolType.PICKAXE, 4, 64, 32, 4, 25, Category.NONE)
		.withSparseOre(3, 2, ToolType.PICKAXE, 2, 64, 32, 8, 4, Category.NONE);
	TIN.add(oreTin);
	AppendixOreDressing.ores.add(oreTin);

	// ZINC ------------------------------------------------------//
	MetallurgyMaterialHelper zinc = new MetallurgyMaterialHelper("zinc", IndexBaseMetals.ZINC_TIER).withBase()
		.withVanillaTools().withComponents().withAdvancedTools();
	ZINC.add(zinc);
	AppendixMetallurgy.metals.add(zinc);
	OreDressingMaterialHelper oreZinc = new OreDressingMaterialHelper("zinc")
		.withOre(4, 2, ToolType.PICKAXE, 3, 48, 32, 8, 50, Category.NONE)
		.withDenseOre(5, 2, ToolType.PICKAXE, 4, 48, 32, 4, 25, Category.NONE)
		.withSparseOre(3, 2, ToolType.PICKAXE, 2, 48, 32, 8, 4, Category.NONE);
	ZINC.add(oreZinc);
	AppendixOreDressing.ores.add(oreZinc);

	// ROSEGOLD ------------------------------------------------------//
	MetallurgyMaterialHelper rosegold = new MetallurgyMaterialHelper("rosegold", IndexBaseMetals.ROSEGOLD_TIER)
		.withBase().withVanillaTools().withComponents().withAdvancedTools();
	ROSEGOLD.add(rosegold);
	AppendixMetallurgy.metals.add(rosegold);

	// PLATINUM ------------------------------------------------------//
	MetallurgyMaterialHelper platinum = new MetallurgyMaterialHelper("platinum", IndexBaseMetals.PLATINUM_TIER)
		.withBase().withVanillaTools().withComponents().withAdvancedTools();
	PLATINUM.add(platinum);
	AppendixMetallurgy.metals.add(platinum);
	OreDressingMaterialHelper orePlatinum = new OreDressingMaterialHelper("platinum")
		.withOre(4, 2, ToolType.PICKAXE, 3, 16, 4, 8, 50, Category.NONE)
		.withDenseOre(5, 2, ToolType.PICKAXE, 4, 16, 4, 4, 25, Category.NONE)
		.withSparseOre(3, 2, ToolType.PICKAXE, 2, 16, 4, 4, 4, Category.NONE);
	PLATINUM.add(orePlatinum);
	AppendixOreDressing.ores.add(orePlatinum);

	// SILVER ------------------------------------------------------//
	MetallurgyMaterialHelper silver = new MetallurgyMaterialHelper("silver", IndexBaseMetals.SILVER_TIER).withBase()
		.withVanillaTools().withComponents().withAdvancedTools();
	SILVER.add(silver);
	AppendixMetallurgy.metals.add(silver);
	OreDressingMaterialHelper oreSilver = new OreDressingMaterialHelper("silver")
		.withOre(4, 2, ToolType.PICKAXE, 3, 48, 16, 8, 50, Category.NONE)
		.withDenseOre(5, 2, ToolType.PICKAXE, 4, 48, 16, 4, 25, Category.NONE)
		.withSparseOre(3, 2, ToolType.PICKAXE, 2, 48, 16, 8, 4, Category.NONE);
	SILVER.add(oreSilver);
	AppendixOreDressing.ores.add(oreSilver);

	// PEWTER ------------------------------------------------------//
	MetallurgyMaterialHelper pewter = new MetallurgyMaterialHelper("pewter", IndexBaseMetals.PEWTER_TIER).withBase()
		.withVanillaTools().withComponents().withAdvancedTools();
	PEWTER.add(pewter);
	AppendixMetallurgy.metals.add(pewter);

	// LEAD ------------------------------------------------------//
	MetallurgyMaterialHelper lead = new MetallurgyMaterialHelper("lead", IndexBaseMetals.LEAD_TIER).withBase()
		.withVanillaTools().withComponents().withAdvancedTools();
	LEAD.add(lead);
	AppendixMetallurgy.metals.add(lead);
	OreDressingMaterialHelper oreLead = new OreDressingMaterialHelper("lead")
		.withOre(4, 2, ToolType.PICKAXE, 3, 48, 16, 8, 50, Category.NONE)
		.withDenseOre(5, 2, ToolType.PICKAXE, 4, 48, 16, 4, 25, Category.NONE)
		.withSparseOre(3, 2, ToolType.PICKAXE, 2, 48, 16, 8, 4, Category.NONE);
	LEAD.add(oreLead);
	AppendixOreDressing.ores.add(oreLead);
    }
}
