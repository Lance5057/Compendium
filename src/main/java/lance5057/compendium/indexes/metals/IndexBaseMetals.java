package lance5057.compendium.indexes.metals;

import lance5057.compendium.CompendiumMaterials;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;

public class IndexBaseMetals {
	// List<List<MaterialHelperBase>> ALL = new
	// ArrayList<List<MaterialHelperBase>>();

//	public static List<MaterialHelperBase> ALUMINIUM = new ArrayList<MaterialHelperBase>();
//	public static Tier ALUMINIUM_TIER = new CompendiumItemTier(0, 80, 10, 1, 15, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/aluminium"));
//	});
//	public static IArmorMaterial ALUMINIUM_ARMOR_TIER = new CompendiumArmorTier("aluminium", 15,
//			new int[] { 2, 5, 6, 2 }, 18, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
//				return Ingredient.of(TCItemTags.ItemTag("ingots/aluminium"));
//			});
//
//	public static List<MaterialHelperBase> BRASS = new ArrayList<MaterialHelperBase>();
//	public static Tier BRASS_TIER = new CompendiumItemTier(0, 300, 5, 1.5f, 16, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/brass"));
//	});
//	public static IArmorMaterial BRASS_ARMOR_TIER = new CompendiumArmorTier("brass", 13, new int[] { 2, 6, 4, 3 }, 27,
//			SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
//				return Ingredient.of(TCItemTags.ItemTag("ingots/brass"));
//			});
//
//	public static List<MaterialHelperBase> BRONZE = new ArrayList<MaterialHelperBase>();
//	public static Tier BRONZE_TIER = new CompendiumItemTier(1, 260, 7, 2.5f, 18, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/bronze"));
//	});
//	public static IArmorMaterial BRONZE_ARMOR_TIER = new CompendiumArmorTier("bronze", 17, new int[] { 2, 7, 5, 3 }, 25,
//			SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> {
//				return Ingredient.of(TCItemTags.ItemTag("ingots/bronze"));
//			});
//
//	public static List<MaterialHelperBase> COPPER = new ArrayList<MaterialHelperBase>();
//	public static Tier COPPER_TIER = new CompendiumItemTier(1, 180, 8, 1, 16, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/copper"));
//	});
//	public static IArmorMaterial COPPER_ARMOR_TIER = new CompendiumArmorTier("copper", 13, new int[] { 1, 5, 4, 2 }, 20,
//			SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
//				return Ingredient.of(TCItemTags.ItemTag("ingots/copper"));
//			});
//
//	public static List<MaterialHelperBase> ELECTRUM = new ArrayList<MaterialHelperBase>();
//	public static Tier ELECTRUM_TIER = new CompendiumItemTier(0, 180, 13f, 1, 31, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/electrum"));
//	});
//	public static IArmorMaterial ELECTRUM_ARMOR_TIER = new CompendiumArmorTier("electrum", 9, new int[] { 3, 7, 4, 2 },
//			36, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 1.0F, () -> {
//				return Ingredient.of(TCItemTags.ItemTag("ingots/electrum"));
//			});
//
//	public static List<MaterialHelperBase> NICKEL = new ArrayList<MaterialHelperBase>();
//	public static Tier NICKEL_TIER = new CompendiumItemTier(1, 250, 2, 0.5f, 15, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/nickel"));
//	});
//	public static IArmorMaterial NICKEL_ARMOR_TIER = new CompendiumArmorTier("nickel", 16, new int[] { 2, 6, 5, 2 }, 21,
//			SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> {
//				return Ingredient.of(TCItemTags.ItemTag("ingots/nickel"));
//			});
//
//	public static List<MaterialHelperBase> STEEL = new ArrayList<MaterialHelperBase>();
//	public static Tier STEEL_TIER = new CompendiumItemTier(2, 500, 11, 2, 30, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/steel"));
//	});
//	public static IArmorMaterial STEEL_ARMOR_TIER = new CompendiumArmorTier("steel", 35, new int[] { 3, 8, 6, 3 }, 10,
//			SoundEvents.ARMOR_EQUIP_IRON, 2.0F, 0.0F, () -> {
//				return Ingredient.of(TCItemTags.ItemTag("ingots/steel"));
//			});
//
//	public static List<MaterialHelperBase> TIN = new ArrayList<MaterialHelperBase>();
//	public static Tier TIN_TIER = new CompendiumItemTier(0, 60, 1, 0, 10, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/tin"));
//	});
//	public static IArmorMaterial TIN_ARMOR_TIER = new CompendiumArmorTier("tin", 11, new int[] { 1, 3, 2, 1 }, 5,
//			SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
//				return Ingredient.of(TCItemTags.ItemTag("ingots/tin"));
//			});
//
//	public static List<MaterialHelperBase> PEWTER = new ArrayList<MaterialHelperBase>();
//	public static Tier PEWTER_TIER = new CompendiumItemTier(1, 220, 6, 2, 16, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/pewter"));
//	});
//	public static IArmorMaterial PEWTER_ARMOR_TIER = new CompendiumArmorTier("pewter", 11, new int[] { 2, 7, 6, 3 }, 9,
//			SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
//				return Ingredient.of(TCItemTags.ItemTag("ingots/pewter"));
//			});
//
//	public static List<MaterialHelperBase> ZINC = new ArrayList<MaterialHelperBase>();
//	public static Tier ZINC_TIER = new CompendiumItemTier(0, 120, 1.5f, 0.5f, 18, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/zinc"));
//	});
//	public static IArmorMaterial ZINC_ARMOR_TIER = new CompendiumArmorTier("zinc", 10, new int[] { 1, 3, 2, 1 }, 7,
//			SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
//				return Ingredient.of(TCItemTags.ItemTag("ingots/pewter"));
//			});
//
//	public static List<MaterialHelperBase> ROSEGOLD = new ArrayList<MaterialHelperBase>();
//	public static Tier ROSEGOLD_TIER = new CompendiumItemTier(0, 190, 11, 2, 30, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/rosegold"));
//	});
//	public static IArmorMaterial ROSEGOLD_ARMOR_TIER = new CompendiumArmorTier("rosegold", 11, new int[] { 3, 6, 5, 2 },
//			35, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
//				return Ingredient.of(TCItemTags.ItemTag("ingots/rosegold"));
//			});
//
//	public static List<MaterialHelperBase> PLATINUM = new ArrayList<MaterialHelperBase>();
//	public static Tier PLATINUM_TIER = new CompendiumItemTier(1, 113, 12, 1, 28, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/platinum"));
//	});
//	public static IArmorMaterial PLATINUM_ARMOR_TIER = new CompendiumArmorTier("platinum", 10, new int[] { 2, 6, 5, 2 },
//			25, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> {
//				return Ingredient.of(TCItemTags.ItemTag("ingots/platinum"));
//			});
//
//	public static List<MaterialHelperBase> SILVER = new ArrayList<MaterialHelperBase>();
//	public static Tier SILVER_TIER = new CompendiumItemTier(1, 141, 6, 1, 18, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/silver"));
//	});
//	public static IArmorMaterial SILVER_ARMOR_TIER = new CompendiumArmorTier("silver", 10, new int[] { 2, 5, 3, 1 }, 23,
//			SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> {
//				return Ingredient.of(TCItemTags.ItemTag("ingots/silver"));
//			});
//
//	public static List<MaterialHelperBase> LEAD = new ArrayList<MaterialHelperBase>();
//	public static Tier LEAD_TIER = new CompendiumItemTier(1, 100, 1, 2, 3, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/lead"));
//	});
//	public static IArmorMaterial LEAD_ARMOR_TIER = new CompendiumArmorTier("lead", 15, new int[] { 2, 6, 5, 2 }, 4,
//			SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 2.0F, () -> {
//				return Ingredient.of(TCItemTags.ItemTag("ingots/lead"));
//			});

	public IndexBaseMetals() {
		// ALUMINIUM ------------------------------------------------------//
		CompendiumMaterials.materials.add(new MaterialHelper("aluminium").addMetalBase());
		CompendiumMaterials.materials.add(new MaterialHelper("brass").addMetalBase());
		CompendiumMaterials.materials.add(new MaterialHelper("bronze").addMetalBase());
		CompendiumMaterials.materials.add(new MaterialHelper("copper").addMetalBase());
		CompendiumMaterials.materials.add(new MaterialHelper("electrum").addMetalBase());
		CompendiumMaterials.materials.add(new MaterialHelper("nickel").addMetalBase());
		CompendiumMaterials.materials.add(new MaterialHelper("steel").addMetalBase());
		CompendiumMaterials.materials.add(new MaterialHelper("tin").addMetalBase());
		CompendiumMaterials.materials.add(new MaterialHelper("zinc").addMetalBase());
		CompendiumMaterials.materials.add(new MaterialHelper("rosegold").addMetalBase());
		CompendiumMaterials.materials.add(new MaterialHelper("platinum").addMetalBase());
		CompendiumMaterials.materials.add(new MaterialHelper("silver").addMetalBase());
		CompendiumMaterials.materials.add(new MaterialHelper("pewter").addMetalBase());
		CompendiumMaterials.materials.add(new MaterialHelper("lead").addMetalBase());
//		
//		MetallurgyMaterialHelper aluminium = new MetallurgyMaterialHelper("aluminium", IndexBaseMetals.ALUMINIUM_TIER)
//				.withBase().withVanillaTools().withComponents().withAdvancedTools().withDefense(ALUMINIUM_ARMOR_TIER);
//		ALUMINIUM.add(aluminium);
//		AppendixMetallurgy.metals.add(aluminium);
//
//		OreDressingMaterialHelper oreAluminium = new OreDressingMaterialHelper("aluminium")
//				.withOre(4, 0, ToolType.PICKAXE, 3, 64, 32, 4, 50, Category.NONE)
//				.withDenseOre(5, 0, ToolType.PICKAXE, 4, 64, 32, 4, 25, Category.NONE)
//				.withSparseOre(3, 0, ToolType.PICKAXE, 2, 64, 32, 8, 4, Category.NONE);
//		ALUMINIUM.add(oreAluminium);
//		AppendixOreDressing.ores.add(oreAluminium);
//
//		ConstructionMaterialHelper constructionAluminium = new ConstructionMaterialHelper("aluminium").withBase()
//				.withDungeon().withShingles().withLighting().withBarsAndChains().withDoorsAndGates().withDecorations()
//				.withWindows();
//		AppendixConstruction.constructs.add(constructionAluminium);
//
//		// BRASS ------------------------------------------------------//
//		MetallurgyMaterialHelper brass = new MetallurgyMaterialHelper("brass", IndexBaseMetals.BRASS_TIER).withBase()
//				.withVanillaTools().withComponents().withAdvancedTools().withDefense(BRASS_ARMOR_TIER);
//		BRASS.add(brass);
//		AppendixMetallurgy.metals.add(brass);
//
//		ConstructionMaterialHelper constructionBrass = new ConstructionMaterialHelper("brass").withBase().withDungeon()
//				.withShingles();
//		AppendixConstruction.constructs.add(constructionBrass);
//
//		// BRONZE ------------------------------------------------------//
//		MetallurgyMaterialHelper bronze = new MetallurgyMaterialHelper("bronze", IndexBaseMetals.BRONZE_TIER).withBase()
//				.withVanillaTools().withComponents().withAdvancedTools().withDefense(BRONZE_ARMOR_TIER);
//		BRONZE.add(bronze);
//		AppendixMetallurgy.metals.add(bronze);
//
//		ConstructionMaterialHelper constructionBronze = new ConstructionMaterialHelper("bronze").withBase()
//				.withDungeon().withShingles();
//		AppendixConstruction.constructs.add(constructionBronze);
//
//		// COPPER ------------------------------------------------------//
//		MetallurgyMaterialHelper copper = new MetallurgyMaterialHelper("copper", IndexBaseMetals.COPPER_TIER).withBase()
//				.withVanillaTools().withComponents().withAdvancedTools().withDefense(COPPER_ARMOR_TIER);
//		COPPER.add(copper);
//		AppendixMetallurgy.metals.add(copper);
//		OreDressingMaterialHelper oreCopper = new OreDressingMaterialHelper("copper")
//				.withOre(4, 1, ToolType.PICKAXE, 3, 64, 32, 8, 50, Category.NONE)
//				.withDenseOre(5, 1, ToolType.PICKAXE, 4, 64, 32, 4, 25, Category.NONE)
//				.withSparseOre(3, 1, ToolType.PICKAXE, 2, 64, 32, 8, 4, Category.NONE);
//		COPPER.add(oreCopper);
//		AppendixOreDressing.ores.add(oreCopper);
//
//		ConstructionMaterialHelper constructionCopper = new ConstructionMaterialHelper("copper").withBase()
//				.withDungeon().withShingles();
//		AppendixConstruction.constructs.add(constructionCopper);
//
//		// ELECTRUM ------------------------------------------------------//
//		MetallurgyMaterialHelper electrum = new MetallurgyMaterialHelper("electrum", IndexBaseMetals.ELECTRUM_TIER)
//				.withBase().withVanillaTools().withComponents().withAdvancedTools().withDefense(ELECTRUM_ARMOR_TIER);
//		ELECTRUM.add(electrum);
//		AppendixMetallurgy.metals.add(electrum);
//
//		ConstructionMaterialHelper constructionElectrum = new ConstructionMaterialHelper("electrum").withBase()
//				.withDungeon().withShingles();
//		AppendixConstruction.constructs.add(constructionElectrum);
//
//		// NICKEL ------------------------------------------------------//
//		MetallurgyMaterialHelper nickel = new MetallurgyMaterialHelper("nickel", IndexBaseMetals.NICKEL_TIER).withBase()
//				.withVanillaTools().withComponents().withAdvancedTools().withDefense(NICKEL_ARMOR_TIER);
//		NICKEL.add(nickel);
//		AppendixMetallurgy.metals.add(nickel);
//		OreDressingMaterialHelper oreNickel = new OreDressingMaterialHelper("nickel")
//				.withOre(4, 2, ToolType.PICKAXE, 3, 32, 16, 4, 50, Category.NONE)
//				.withDenseOre(5, 2, ToolType.PICKAXE, 4, 32, 16, 4, 25, Category.NONE)
//				.withSparseOre(3, 2, ToolType.PICKAXE, 2, 32, 16, 4, 4, Category.NONE);
//		NICKEL.add(oreNickel);
//		AppendixOreDressing.ores.add(oreNickel);
//
//		ConstructionMaterialHelper constructionNickel = new ConstructionMaterialHelper("nickel").withBase()
//				.withDungeon().withShingles();
//		AppendixConstruction.constructs.add(constructionNickel);
//
//		// STEEL ------------------------------------------------------//
//		MetallurgyMaterialHelper steel = new MetallurgyMaterialHelper("steel", IndexBaseMetals.STEEL_TIER).withBase()
//				.withVanillaTools().withComponents().withAdvancedTools().withDefense(STEEL_ARMOR_TIER);
//		STEEL.add(steel);
//		AppendixMetallurgy.metals.add(steel);
//
//		ConstructionMaterialHelper constructionSteel = new ConstructionMaterialHelper("steel").withBase().withDungeon()
//				.withShingles();
//		AppendixConstruction.constructs.add(constructionSteel);
//
//		// TIN ------------------------------------------------------//
//		MetallurgyMaterialHelper tin = new MetallurgyMaterialHelper("tin", IndexBaseMetals.TIN_TIER).withBase()
//				.withVanillaTools().withComponents().withAdvancedTools().withDefense(TIN_ARMOR_TIER);
//		TIN.add(tin);
//		AppendixMetallurgy.metals.add(tin);
//		OreDressingMaterialHelper oreTin = new OreDressingMaterialHelper("tin")
//				.withOre(4, 1, ToolType.PICKAXE, 3, 64, 32, 8, 50, Category.NONE)
//				.withDenseOre(5, 1, ToolType.PICKAXE, 4, 64, 32, 4, 25, Category.NONE)
//				.withSparseOre(3, 1, ToolType.PICKAXE, 2, 64, 32, 8, 4, Category.NONE);
//		TIN.add(oreTin);
//		AppendixOreDressing.ores.add(oreTin);
//
//		ConstructionMaterialHelper constructionTin = new ConstructionMaterialHelper("tin").withBase().withDungeon()
//				.withShingles();
//		AppendixConstruction.constructs.add(constructionTin);
//
//		// ZINC ------------------------------------------------------//
//		MetallurgyMaterialHelper zinc = new MetallurgyMaterialHelper("zinc", IndexBaseMetals.ZINC_TIER).withBase()
//				.withVanillaTools().withComponents().withAdvancedTools().withDefense(ZINC_ARMOR_TIER);
//		ZINC.add(zinc);
//		AppendixMetallurgy.metals.add(zinc);
//		OreDressingMaterialHelper oreZinc = new OreDressingMaterialHelper("zinc")
//				.withOre(4, 1, ToolType.PICKAXE, 3, 48, 32, 8, 50, Category.NONE)
//				.withDenseOre(5, 1, ToolType.PICKAXE, 4, 48, 32, 4, 25, Category.NONE)
//				.withSparseOre(3, 1, ToolType.PICKAXE, 2, 48, 32, 8, 4, Category.NONE);
//		ZINC.add(oreZinc);
//		AppendixOreDressing.ores.add(oreZinc);
//
//		ConstructionMaterialHelper constructionZinc = new ConstructionMaterialHelper("zinc").withBase().withDungeon()
//				.withShingles();
//		AppendixConstruction.constructs.add(constructionZinc);
//
//		// ROSEGOLD ------------------------------------------------------//
//		MetallurgyMaterialHelper rosegold = new MetallurgyMaterialHelper("rosegold", IndexBaseMetals.ROSEGOLD_TIER)
//				.withBase().withVanillaTools().withComponents().withAdvancedTools().withDefense(ROSEGOLD_ARMOR_TIER);
//		ROSEGOLD.add(rosegold);
//		AppendixMetallurgy.metals.add(rosegold);
//
//		ConstructionMaterialHelper constructionRosegold = new ConstructionMaterialHelper("rosegold").withBase()
//				.withDungeon().withShingles();
//		AppendixConstruction.constructs.add(constructionRosegold);
//
//		// PLATINUM ------------------------------------------------------//
//		MetallurgyMaterialHelper platinum = new MetallurgyMaterialHelper("platinum", IndexBaseMetals.PLATINUM_TIER)
//				.withBase().withVanillaTools().withComponents().withAdvancedTools().withDefense(PLATINUM_ARMOR_TIER);
//		PLATINUM.add(platinum);
//		AppendixMetallurgy.metals.add(platinum);
//		OreDressingMaterialHelper orePlatinum = new OreDressingMaterialHelper("platinum")
//				.withOre(4, 1, ToolType.PICKAXE, 3, 16, 4, 4, 50, Category.NONE)
//				.withDenseOre(5, 1, ToolType.PICKAXE, 4, 16, 4, 4, 25, Category.NONE)
//				.withSparseOre(3, 1, ToolType.PICKAXE, 2, 16, 4, 4, 4, Category.NONE);
//		PLATINUM.add(orePlatinum);
//		AppendixOreDressing.ores.add(orePlatinum);
//
//		ConstructionMaterialHelper constructionPlatinum = new ConstructionMaterialHelper("platinum").withBase()
//				.withDungeon().withShingles();
//		AppendixConstruction.constructs.add(constructionPlatinum);
//
//		// SILVER ------------------------------------------------------//
//		MetallurgyMaterialHelper silver = new MetallurgyMaterialHelper("silver", IndexBaseMetals.SILVER_TIER).withBase()
//				.withVanillaTools().withComponents().withAdvancedTools().withDefense(SILVER_ARMOR_TIER);
//		SILVER.add(silver);
//		AppendixMetallurgy.metals.add(silver);
//		OreDressingMaterialHelper oreSilver = new OreDressingMaterialHelper("silver")
//				.withOre(4, 1, ToolType.PICKAXE, 3, 48, 16, 8, 50, Category.NONE)
//				.withDenseOre(5, 1, ToolType.PICKAXE, 4, 48, 16, 4, 25, Category.NONE)
//				.withSparseOre(3, 1, ToolType.PICKAXE, 2, 48, 16, 8, 4, Category.NONE);
//		SILVER.add(oreSilver);
//		AppendixOreDressing.ores.add(oreSilver);
//
//		ConstructionMaterialHelper constructionSilver = new ConstructionMaterialHelper("silver").withBase()
//				.withDungeon().withShingles();
//		AppendixConstruction.constructs.add(constructionSilver);
//
//		// PEWTER ------------------------------------------------------//
//		MetallurgyMaterialHelper pewter = new MetallurgyMaterialHelper("pewter", IndexBaseMetals.PEWTER_TIER).withBase()
//				.withVanillaTools().withComponents().withAdvancedTools().withDefense(PEWTER_ARMOR_TIER);
//		PEWTER.add(pewter);
//		AppendixMetallurgy.metals.add(pewter);
//
//		ConstructionMaterialHelper constructionPewter = new ConstructionMaterialHelper("pewter").withBase()
//				.withDungeon().withShingles();
//		AppendixConstruction.constructs.add(constructionPewter);
//
//		// LEAD ------------------------------------------------------//
//		MetallurgyMaterialHelper lead = new MetallurgyMaterialHelper("lead", IndexBaseMetals.LEAD_TIER).withBase()
//				.withVanillaTools().withComponents().withAdvancedTools().withDefense(LEAD_ARMOR_TIER);
//		LEAD.add(lead);
//		AppendixMetallurgy.metals.add(lead);
//		OreDressingMaterialHelper oreLead = new OreDressingMaterialHelper("lead")
//				.withOre(4, 1, ToolType.PICKAXE, 3, 48, 16, 8, 50, Category.NONE)
//				.withDenseOre(5, 1, ToolType.PICKAXE, 4, 48, 16, 4, 25, Category.NONE)
//				.withSparseOre(3, 1, ToolType.PICKAXE, 2, 48, 16, 8, 4, Category.NONE);
//		LEAD.add(oreLead);
//		AppendixOreDressing.ores.add(oreLead);
//
//		ConstructionMaterialHelper constructionLead = new ConstructionMaterialHelper("lead").withBase().withDungeon()
//				.withShingles();
//		AppendixConstruction.constructs.add(constructionLead);
	}
}
