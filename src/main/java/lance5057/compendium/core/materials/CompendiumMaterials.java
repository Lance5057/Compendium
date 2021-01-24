package lance5057.compendium.core.materials;

import java.util.ArrayList;
import java.util.List;

import lance5057.compendium.Reference;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.library.CompendiumItemTier;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import net.minecraft.item.ItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.biome.Biome.Category;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class CompendiumMaterials {
    public static List<MaterialHelper> materials = new ArrayList<>();

    // Base
    public static MaterialHelper AEONSTEEL;
    public static MaterialHelper QUEENSGOLD;
    public static MaterialHelper DOGBEARIUM;
    public static MaterialHelper SINISTERIUM;
    public static MaterialHelper NIHILITE;
    public static MaterialHelper ORICHALCUM;
    public static MaterialHelper PANDORIUM;
    public static MaterialHelper VALYRIANSTEEL;
    public static MaterialHelper ICE;
    public static MaterialHelper FROSTSTEEL;

    // Advanced Materials
    public static MaterialHelper RADIANTGOLD;
    public static MaterialHelper HALLOWEDSILVER;
    public static MaterialHelper ENERGETICELECTRUM;

    // Wanderlust Materials
    public static MaterialHelper MITHRIL;

    // Cornucopia Materials
//	public static MaterialHelper gallite;
//	public static MaterialHelper sundrop;
//	public static MaterialHelper voidite;
//	public static MaterialHelper solarium;
//	public static MaterialHelper dragonsteel;
//	public static MaterialHelper blacksteel;
//	public static MaterialHelper abyssalium;
//	public static MaterialHelper depthsilver;
//	public static MaterialHelper moonsilver;
//	public static MaterialHelper novagold;

    // Vanilla Mats
    public static MaterialHelper IRON;
    public static MaterialHelper GOLD;
    public static MaterialHelper EMERALD;
    public static MaterialHelper DIAMOND;
    public static MaterialHelper BONE;
    public static MaterialHelper QUARTZ;
    public static MaterialHelper LAPIS;
    public static MaterialHelper OBSIDIAN;

    // Holiday
//	public static MaterialHelper REDCANDY;
//	public static MaterialHelper GREENCANDY;

    // Gems
    public static MaterialHelper SAPPHIRE;
    public static MaterialHelper RUBY;
//	public static MaterialHelper starsapphire;
//	public static MaterialHelper starruby;
    public static MaterialHelper CITRINE;
    public static MaterialHelper AMETHYST;
    public static MaterialHelper TOPAZ;
    public static MaterialHelper GARNET;
    public static MaterialHelper OPAL;
    public static MaterialHelper TANZINITE;
    public static MaterialHelper AMBER;

    // Other
    public static MaterialHelper BRASS;
    public static MaterialHelper BRONZE;
    public static MaterialHelper COPPER;
    public static MaterialHelper ELECTRUM;
    public static MaterialHelper NICKEL;
    public static MaterialHelper STEEL;
    public static MaterialHelper TIN;
    public static MaterialHelper ZINC;
    public static MaterialHelper ROSEGOLD;
    public static MaterialHelper PLATINUM;
    public static MaterialHelper SILVER;

    public CompendiumMaterials() {

	// Compendium
	materials.add(AEONSTEEL = new MaterialHelper("aeonsteel", new CompendiumItemTier(4, 500, 15f, 4f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/aeonsteel"));
	})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools().withExtraTools());

	materials.add(
		QUEENSGOLD = new MaterialHelper("queensgold", new CompendiumItemTier(1, 70, 3.00f, 3.00f, 22, () -> {
		    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/queensgold"));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools().withExtraTools());

	materials.add(
		DOGBEARIUM = new MaterialHelper("dogbearium", new CompendiumItemTier(2, 150, 5.00f, 9.00f, 22, () -> {
		    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/dogbearium"));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools().withExtraTools());

	materials.add(
		SINISTERIUM = new MaterialHelper("sinisterium", new CompendiumItemTier(3, 224, 5.00f, 5.00f, 22, () -> {
		    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/sinisterium"));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools().withExtraTools());

	materials.add(NIHILITE = new MaterialHelper("nihilite", new CompendiumItemTier(4, 400, 9.7f, 7.1f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/nihilite"));
	})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools().withExtraTools());

	materials.add(
		ORICHALCUM = new MaterialHelper("orichalcum", new CompendiumItemTier(3, 180, 5.3f, 6.23f, 22, () -> {
		    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/orichalcum"));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools().withExtraTools());

	materials.add(
		PANDORIUM = new MaterialHelper("pandorium", new CompendiumItemTier(3, 999, 10.2f, 8.72f, 22, () -> {
		    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/pandorium"));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools().withExtraTools());

	materials.add(
		VALYRIANSTEEL = new MaterialHelper("valyriansteel", new CompendiumItemTier(3, 610, 7f, 7f, 22, () -> {
		    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/valyriansteel"));
		})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools().withExtraTools());

	materials.add(ICE = new MaterialHelper("ice", new CompendiumItemTier(1, 120, 4.00f, 3.00f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/ice"));
	})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools().withExtraTools());

	materials.add(FROSTSTEEL = new MaterialHelper("froststeel", new CompendiumItemTier(3, 610, 7f, 7f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/froststeel"));
	})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools().withExtraTools());

	// WLR
	materials.add(MITHRIL = new MaterialHelper("mithril", new CompendiumItemTier(3, 800, 8f, 6f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/mithril"));
	})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools().withExtraTools());

	// Vanilla
	materials.add(
		IRON = new MaterialHelper("iron", ItemTier.IRON).withPremade(Ingredient.fromItems(Items.IRON_INGOT),
			Ingredient.fromItems(Items.IRON_NUGGET), Ingredient.fromItems(Items.IRON_BLOCK))
			.withExtraComponents().withExtraTools());

	materials.add(GOLD = new MaterialHelper("gold", ItemTier.GOLD)
		.withPremade(Ingredient.fromItems(Items.GOLD_INGOT), Ingredient.fromItems(Items.GOLD_NUGGET),
			Ingredient.fromItems(Items.GOLD_BLOCK))
		.withVanillaComponents().withExtraComponents().withExtraTools());

	materials.add(EMERALD = new MaterialHelper("emerald", new CompendiumItemTier(3, 320, 10f, 4f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/emerald"));
	})).withPremade(Ingredient.fromItems(Items.EMERALD), null, Ingredient.fromItems(Items.EMERALD_BLOCK))
		.withVanillaComponents().withExtraComponents().withVanillaTools().withExtraTools());

	materials.add(DIAMOND = new MaterialHelper("diamond", ItemTier.DIAMOND)
		.withPremade(Ingredient.fromItems(Items.DIAMOND), null, Ingredient.fromItems(Items.DIAMOND_BLOCK))
		.withVanillaComponents().withExtraComponents().withExtraTools());

	materials.add(LAPIS = new MaterialHelper("lapis", new CompendiumItemTier(0, 120, 2f, 2f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/lapis"));
	})).withPremade(Ingredient.fromItems(Items.LAPIS_LAZULI), null, Ingredient.fromItems(Items.LAPIS_BLOCK))
		.withVanillaComponents().withExtraComponents().withVanillaTools().withExtraTools());

	materials.add(QUARTZ = new MaterialHelper("quartz", new CompendiumItemTier(1, 430, 5.5f, 7f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/quartz"));
	})).withPremade(Ingredient.fromItems(Items.QUARTZ), null, Ingredient.fromItems(Items.QUARTZ_BLOCK))
		.withVanillaComponents().withExtraComponents().withVanillaTools().withExtraTools());

	materials.add(BONE = new MaterialHelper("bone", new CompendiumItemTier(1, 120, 3f, 2f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/bone"));
	})).withPremade(Ingredient.fromItems(Items.BONE), Ingredient.fromItems(Items.BONE_MEAL),
		Ingredient.fromItems(Items.BONE_BLOCK)).withVanillaComponents().withExtraComponents().withVanillaTools()
		.withExtraTools());

	materials.add(OBSIDIAN = new MaterialHelper("obsidian", new CompendiumItemTier(1, 120, 3f, 2f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/obsidian"));
	})).withPremade(null, null, Ingredient.fromItems(Items.OBSIDIAN)).withVanillaComponents().withExtraComponents()
		.withVanillaTools().withExtraTools());

	// Gems
	materials.add(SAPPHIRE = new MaterialHelper("sapphire", new CompendiumItemTier(3, 320, 4f, 4f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/sapphire"));
	})).withGem().withVanillaComponents().withExtraComponents().withVanillaTools()
		.withOre(5, 2, ToolType.PICKAXE, 4, 16, 5, 4, 5, Category.OCEAN).withExtraTools());

	materials.add(RUBY = new MaterialHelper("ruby", new CompendiumItemTier(3, 320, 4f, 10f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/ruby"));
	})).withGem().withVanillaComponents().withExtraComponents().withVanillaTools()
		.withOre(5, 2, ToolType.PICKAXE, 4, 16, 5, 4, 5, Category.MESA).withExtraTools());

	materials.add(CITRINE = new MaterialHelper("citrine", new CompendiumItemTier(3, 430, 5.5f, 7f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/citrine"));
	})).withGem().withVanillaComponents().withExtraComponents().withVanillaTools()
		.withOre(5, 2, ToolType.PICKAXE, 4, 16, 5, 4, 5, Category.DESERT).withExtraTools());

	materials.add(AMETHYST = new MaterialHelper("amethyst", new CompendiumItemTier(1, 530, 5.5f, 6.2f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/amethyst"));
	})).withGem().withVanillaComponents().withExtraComponents().withVanillaTools()
		.withOre(5, 2, ToolType.PICKAXE, 4, 16, 5, 4, 5, Category.TAIGA).withExtraTools());

	materials.add(TOPAZ = new MaterialHelper("topaz", new CompendiumItemTier(1, 530, 5.5f, 6.2f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/topaz"));
	})).withGem().withVanillaComponents().withExtraComponents().withVanillaTools()
		.withOre(5, 0, ToolType.PICKAXE, 4, 16, 5, 4, 5, Category.PLAINS).withExtraTools());

	materials.add(GARNET = new MaterialHelper("garnet", new CompendiumItemTier(1, 530, 4.5f, 7.2f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/garnet"));
	})).withGem().withVanillaComponents().withExtraComponents().withVanillaTools()
		.withOre(5, 2, ToolType.PICKAXE, 4, 16, 5, 4, 5, Category.SAVANNA).withExtraTools());

	materials.add(OPAL = new MaterialHelper("opal", new CompendiumItemTier(1, 530, 6.5f, 6.2f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/opal"));
	})).withGem().withVanillaComponents().withExtraComponents().withVanillaTools()
		.withOre(5, 1, ToolType.PICKAXE, 4, 16, 5, 4, 5, Category.SWAMP).withExtraTools());

	materials.add(TANZINITE = new MaterialHelper("tanzinite", new CompendiumItemTier(1, 630, 5.5f, 6.2f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/tanzinite"));
	})).withGem().withVanillaComponents().withExtraComponents().withVanillaTools()
		.withOre(5, 1, ToolType.PICKAXE, 4, 16, 5, 4, 5, Category.JUNGLE).withExtraTools());

	materials.add(AMBER = new MaterialHelper("amber", new CompendiumItemTier(0, 120, 2f, 3f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/amber"));
	})).withGem().withVanillaComponents().withExtraComponents().withVanillaTools()
		.withOre(5, 0, ToolType.PICKAXE, 4, 16, 5, 4, 5, Category.FOREST).withExtraTools());

	// Other
	materials.add(BRASS = new MaterialHelper("brass", new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/brass"));
	})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools().withExtraTools());

	materials.add(BRONZE = new MaterialHelper("bronze", new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/bronze"));
	})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools().withExtraTools());

	materials.add(COPPER = new MaterialHelper("copper", new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/copper"));
	})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools()
		.withOre(5, 1, ToolType.PICKAXE, 4, 32, 5, 15, 6, null).withExtraTools());

	materials.add(ELECTRUM = new MaterialHelper("electrum", new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/electrum"));
	})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools().withExtraTools());

	materials.add(NICKEL = new MaterialHelper("nickel", new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/nickel"));
	})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools()
		.withOre(5, 1, ToolType.PICKAXE, 4, 32, 5, 15, 6, null).withExtraTools());

	materials.add(STEEL = new MaterialHelper("steel", new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/steel"));
	})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools().withExtraTools());

	materials.add(TIN = new MaterialHelper("tin", new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/tin"));
	})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools()
		.withOre(5, 1, ToolType.PICKAXE, 4, 32, 5, 15, 6, null).withExtraTools());

	materials.add(ZINC = new MaterialHelper("zinc", new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/zinc"));
	})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools()
		.withOre(5, 1, ToolType.PICKAXE, 4, 32, 5, 15, 6, null).withExtraTools());

	materials.add(ROSEGOLD = new MaterialHelper("rosegold", new CompendiumItemTier(0, 99, 4f, 1f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/rosegold"));
	})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools().withExtraTools());

	materials.add(PLATINUM = new MaterialHelper("platinum", new CompendiumItemTier(1, 99, 3.7f, 4f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/platinum"));
	})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools()
		.withOre(5, 2, ToolType.PICKAXE, 4, 20, 15, 3, 5, null).withExtraTools());

	materials.add(SILVER = new MaterialHelper("silver", new CompendiumItemTier(3, 150, 5.00f, 9.00f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/silver"));
	})).withIngot().withVanillaComponents().withExtraComponents().withVanillaTools()
		.withOre(5, 1, ToolType.PICKAXE, 4, 32, 5, 15, 6, null).withExtraTools());
    }

//	@Subscribe
//	public void init(FMLInitializationEvent event) {
//
////		if (TConstruct.instance.pulseManager.isPulseLoaded(TinkerSmeltery.PulseId)) {
////			TinkerRegistry.registerMelting(Items.CHORUS_FRUIT, fluidChorusJuice, Material.VALUE_Nugget);
////			TinkerRegistry.registerMelting(Items.DRAGON_BREATH, fluidDragonsBreath, Material.VALUE_Ingot);
////			TinkerRegistry.registerMelting(TinkerCommons.matNecroticBone, fluidVile, Material.VALUE_Nugget);
////			TinkerRegistry.registerMelting(new ItemStack(Items.SKULL, 1, 1), fluidVile, Material.VALUE_Ingot);
////			TinkerRegistry.registerMelting(TinkerCommons.matMendingMoss, fluidVibrant, Material.VALUE_Ingot * 2);
////			TinkerRegistry.registerMelting(Blocks.ICE, fluidSlush, Material.VALUE_Ingot);
////			TinkerRegistry.registerMelting(Blocks.PACKED_ICE, fluidSlush, Material.VALUE_Ingot * 4);
////			TinkerRegistry.registerMelting(Blocks.QUARTZ_BLOCK, fluidQuartz, Material.VALUE_Ingot * 4);
////			TinkerRegistry.registerMelting(Items.QUARTZ, fluidQuartz, Material.VALUE_Ingot);
////
////			TinkerRegistry.registerTableCasting(new ItemStack(Items.QUARTZ, 1, 0), ItemStack.EMPTY, fluidQuartz, Material.VALUE_Ingot);
////			TinkerRegistry.registerBasinCasting(new ItemStack(Blocks.QUARTZ_BLOCK, 1, 0), ItemStack.EMPTY, fluidQuartz, Material.VALUE_Ingot * 4);
////			TinkerRegistry.registerBasinCasting(new ItemStack(Blocks.ICE, 1, 0), ItemStack.EMPTY, fluidSlush, Material.VALUE_Ingot);
////
////			if (aeonsteel != null)
////				registerAlloy(new FluidStack(((MeltableMaterial) aeonsteel.addons.get(0)).fluid, 4), new FluidStack(fluidChorusJuice, 1), new FluidStack(TinkerFluids.cobalt, 3));
////
////			if (queensgold != null)
////				TinkerRegistry.registerAlloy(new FluidStack(((MeltableMaterial) queensgold.addons.get(0)).fluid, 2), new FluidStack(TinkerFluids.gold, 1), new FluidStack(TinkerFluids.knightslime, 1));
////
////			if (dogbearium != null)
////				TinkerRegistry.registerAlloy(new FluidStack(((MeltableMaterial) dogbearium.addons.get(0)).fluid, 4), new FluidStack(TinkerFluids.ardite, 1), new FluidStack(fluidDragonsBreath, 3));
////
////			if (sinisterium != null)
////				TinkerRegistry.registerAlloy(new FluidStack(((MeltableMaterial) sinisterium.addons.get(0)).fluid, 6), new FluidStack(TinkerFluids.blood, 1), new FluidStack(fluidVile, 2), new FluidStack(TinkerFluids.iron, 4));
////
////			if (nihilite != null)
////				TinkerRegistry.registerAlloy(new FluidStack(((MeltableMaterial) nihilite.addons.get(0)).fluid, 3), new FluidStack(TinkerFluids.cobalt, 1), new FluidStack(fluidVile, 2));
////
////			if (orichalcum != null)
////				TinkerRegistry.registerAlloy(new FluidStack(((MeltableMaterial) orichalcum.addons.get(0)).fluid, 6), new FluidStack(TinkerFluids.bronze, 4), new FluidStack(fluidVibrant, 2), new FluidStack(TinkerFluids.gold, 1));
////
////			if (pandorium != null)
////				TinkerRegistry.registerAlloy(new FluidStack(((MeltableMaterial) pandorium.addons.get(0)).fluid, 3), new FluidStack(TinkerFluids.ardite, 1), new FluidStack(fluidVibrant, 2));
////
////			if (rosegold != null)
////				TinkerRegistry.registerAlloy(new FluidStack(((MeltableMaterial) rosegold.addons.get(0)).fluid, 4), new FluidStack(TinkerFluids.gold, 1), new FluidStack(TinkerFluids.copper, 3));
////
////			if (valyriansteel != null)
////				TinkerRegistry.registerAlloy(new FluidStack(((MeltableMaterial) valyriansteel.addons.get(0)).fluid, 4), new FluidStack(TinkerFluids.steel, 2), new FluidStack(TinkerFluids.obsidian, 2), new FluidStack(fluidDragonsBreath, 1));
////
////			if (froststeel != null)
////				TinkerRegistry.registerAlloy(new FluidStack(((MeltableMaterial) froststeel.addons.get(0)).fluid, 4), new FluidStack(TinkerFluids.steel, 2), new FluidStack(TinkerFluids.cobalt, 2), new FluidStack(fluidSlush, 1));
////		}
//
//		// if (!TD_Config.materials.isBlacklisted("pureardite") &&
//		// !TD_Config.materials.isBlacklisted("purifiedgold"))
//		// TinkerRegistry.registerAlloy(new
//		// FluidStack(getMaterialHelper("pureardite").fluid, 1),
//		// new FluidStack(TinkerFluids.ardite, 1), new FluidStack(fluidDragonsBreath,
//		// 2),
//		// new FluidStack(TinkerFluids.blood, 2), new
//		// FluidStack(getMaterialHelper("purifiedgold").fluid, 2));
//		//
//		// if (!TD_Config.materials.isBlacklisted("purecobalt")
//		// &&!TD_Config.materials.isBlacklisted("purifiedsilver"))
//		// TinkerRegistry.registerAlloy(new
//		// FluidStack(getMaterialHelper("purecobalt").fluid, 1),
//		// new FluidStack(TinkerFluids.cobalt, 1), new FluidStack(fluidDragonsBreath,
//		// 2),
//		// new FluidStack(fluidSlush, 2), new
//		// FluidStack(getMaterialHelper("purifiedsilver").fluid, 2));
//		//
//		// if (!TD_Config.materials.isBlacklisted("puremanyullyn") &&
//		// !TD_Config.materials.isBlacklisted("purecobalt")
//		// && !TD_Config.materials.isBlacklisted("pureardite"))
//		// TinkerRegistry.registerAlloy(new
//		// FluidStack(getMaterialHelper("puremanyullyn").fluid, 2),
//		// new FluidStack(getMaterialHelper("pureardite").fluid, 1),
//		// new FluidStack(getMaterialHelper("purecobalt").fluid, 1));
//		//
//		// if (!TD_Config.materials.isBlacklisted("purifiedgold"))
//		// TinkerRegistry.registerAlloy(new
//		// FluidStack(getMaterialHelper("purifiedgold").fluid, 1),
//		// new FluidStack(TinkerFluids.gold, 1), new FluidStack(fluidQuartz, 8),
//		// new FluidStack(TinkerFluids.glass, 2), new
//		// FluidStack(TinkerFluids.purpleSlime, 2));
//		//
//		// if (!TD_Config.materials.isBlacklisted("purifiedsilver"))
//		// TinkerRegistry.registerAlloy(new
//		// FluidStack(getMaterialHelper("purifiedsilver").fluid, 1),
//		// new FluidStack(TinkerFluids.silver, 1), new FluidStack(fluidQuartz, 8),
//		// new FluidStack(TinkerFluids.glass, 2), new
//		// FluidStack(TinkerFluids.purpleSlime, 2));
//
//		// if (!TD_Config.materials.isBlacklisted("nihilite"))
//		// TinkerRegistry.registerAlloy(new FluidStack(fluids.get("solarium"), 1),
//		// new FluidStack(TinkerFluids.steel, 1), new FluidStack(fluids.get("sundrop"),
//		// 1));
//		//
//		// if (!TD_Config.materials.isBlacklisted("nihilite"))
//		// TinkerRegistry.registerAlloy(new FluidStack(fluids.get("dragonsteel"), 1),
//		// new FluidStack(TinkerFluids.steel, 1), new FluidStack(fluids.get("gallite"),
//		// 1));
//		//
//		// if (!TD_Config.materials.isBlacklisted("nihilite"))
//		// TinkerRegistry.registerAlloy(new FluidStack(fluids.get("blacksteel"), 1), new
//		// FluidStack(TinkerFluids.steel, 1),
//		// new FluidStack(fluids.get("voidite"), 1));
//		//
//		// if (!TD_Config.materials.isBlacklisted("nihilite"))
//		// TinkerRegistry.registerAlloy(new FluidStack(fluids.get("abyssalium"), 1),
//		// new FluidStack(fluids.get("voidite"), 1), new
//		// FluidStack(fluids.get("sundrop"), 1));
//		// TinkerRegistry.registerAlloy(new FluidStack(fluids.get("depthsilver"), 1),
//		// new FluidStack(TinkerFluids.silver, 1), new
//		// FluidStack(fluids.get("abyssalium"), 1));
//		// TinkerRegistry.registerAlloy(new FluidStack(fluids.get("moonsilver"), 1),
//		// new FluidStack(TinkerFluids.silver, 1), new FluidStack(fluids.get("voidite"),
//		// 1));
//		// TinkerRegistry.registerAlloy(new FluidStack(fluids.get("novagold"), 1), new
//		// FluidStack(TinkerFluids.gold, 1),
//		// new FluidStack(fluids.get("sundrop"), 1));
//
////		if (TinkersCompendium.bloodmagic)
////			addonbloodmagic.init(event);
//
////		for (MaterialHelper m : materials) {
////			if (!TCConfig.materials.isBlacklisted(m.name))
////				m.setupClient();
////		}
//
//		for (MaterialHelper m : materials)
//			m.init();
//		for (MaterialHelper m : materials)
//			m.client();
//
////		Collection<Material> mats = TinkerRegistry.getAllMaterials();
////		for (Material m : TinkerRegistry.getAllMaterials()) {
////			if (!m.hasStats(SHIELD)) {
////				if (m.hasStats(MaterialTypes.HEAD)) {
////					int dur = ((HeadMaterialStats) m.getStats(MaterialTypes.HEAD)).durability;
////					m.addStats(new ShieldMaterialStats(dur, 33));
////					m.addStats(new HelmMaterialStats(dur, 1, 0, 0));
////					m.addStats(new ChestMaterialStats(dur, 1, 0, 0));
////					m.addStats(new LegsMaterialStats(dur, 1, 0, 0));
////					m.addStats(new FeetMaterialStats(dur, 1, 0, 0));
////					// m.addStats(new ClothMaterialStats(dur, 1, 0, 0));
////				}
////			}
////		}
//
//	}
//
//	@Subscribe
//	public void postInit(FMLPostInitializationEvent event) {
//
//		for (MaterialHelper m : materials)
//			m.post();
//	}
//
//	void oreDictComponent(String name, ComponentPart item) {
//		NonNullList<ItemStack> stacks = null;
//		stacks = NonNullList.create();
//
//		item.getSubItems(item.getCreativeTab(), stacks);
//
//		for (ItemStack s : stacks) {
//			String str = s.getTagCompound().getString("Material");
//			OreDictionary.registerOre(name + StringUtils.capitalize(str), s);
//		}
//	}
//
//	public static void registerAlloy(FluidStack output, FluidStack... components) {
//		AlloyRecipe r = new AlloyRecipe(output, components);
//		TinkerRegistry.registerAlloy(r);
//	}
//
//	@SideOnly(Side.CLIENT)
//	@SubscribeEvent
//	public static void registerModels(ModelRegistryEvent event) {
//		for (MaterialHelper m : materials)
//			m.models();
//	}
//
//	// @SubscribeEvent
//	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
////		for (Block i : blockList) {
////			event.getRegistry().register(i);
////		}
//
//		fluidChorusJuice = regFluid("chorusjuice", 0xd982ff, event);
//		registerBlock(event.getRegistry(), new ChorusJuice(fluidChorusJuice), fluidChorusJuice.getName());
//		TinkersCompendium.proxy.registerFluidModels(fluidChorusJuice);
//
//		fluidVile = regFluid("vile", 0x111111, event);
//		registerBlock(event.getRegistry(), new VileFluid(fluidVile), fluidVile.getName());
//		TinkersCompendium.proxy.registerFluidModels(fluidVile);
//
//		fluidVibrant = regFluid("vibrant", 0x76ff00, event);
//		registerBlock(event.getRegistry(), new VibrantFluid(fluidVibrant), fluidVibrant.getName());
//		TinkersCompendium.proxy.registerFluidModels(fluidVibrant);
//
//		fluidSlush = regFluid("slush", 0xbfefff, event);
//		registerClassicBlock(event.getRegistry(), fluidSlush);
//		TinkersCompendium.proxy.registerFluidModels(fluidSlush);
//
//		fluidQuartz = regMoltenFluid("quartz", 0xdddddd, event);
//		fluidDragonsBreath = regMoltenFluid("dragonsbreath", 0x7f00b7, event);
//	}
//
//	@SubscribeEvent
//	public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
////		for (MaterialHelper m : materials) {
////			if (!TCConfig.materials.isBlacklisted(m.name))
////				m.setupRecipes(event);
////		}
//	}
//
//	static FluidColored regFluid(String name, int color, RegistryEvent.Register<Block> event) {
//		FluidColored f = new FluidColored(name, color);
//		f.setUnlocalizedName(Reference.MOD_ID + "." + name);
//		FluidRegistry.registerFluid(f);
//		FluidRegistry.addBucketForFluid(f);
//		return f;
//	}
//
//	static FluidMolten regMoltenFluid(String name, int color, RegistryEvent.Register<Block> event) {
//		FluidMolten f = new FluidMolten(name, color);
//		f.setUnlocalizedName(Reference.MOD_ID + "." + name);
//		FluidRegistry.registerFluid(f);
//		FluidRegistry.addBucketForFluid(f);
//
//		registerMoltenBlock(event.getRegistry(), f);
//
//		TinkersCompendium.proxy.registerFluidModels(f);
//
//		return f;
//	}
//
//	/** Registers a non-burning water based block for the fluid */
//	public static BlockFluidBase registerClassicBlock(IForgeRegistry<Block> registry, Fluid fluid) {
//		return registerBlock(registry, new BlockTinkerFluid(fluid, net.minecraft.block.material.Material.WATER), fluid.getName());
//	}
//
//	/** Registers a hot lava-based block for the fluid, prefix with molten_ */
//	public static BlockMolten registerMoltenBlock(IForgeRegistry<Block> registry, Fluid fluid) {
//		return registerBlock(registry, new BlockMolten(fluid), "molten_" + fluid.getName()); // molten_foobar prefix
//	}
//
//	protected static <T extends Block> T registerBlock(IForgeRegistry<Block> registry, T block, String name) {
//		if (!name.equals(name.toLowerCase(Locale.US))) {
//			throw new IllegalArgumentException(String.format("Unlocalized names need to be all lowercase! Block: %s", name));
//		}
//
//		String prefixedName = Util.prefix(name);
//		block.setUnlocalizedName(prefixedName);
//
//		register(registry, block, name);
//		return block;
//	}
//
//	protected static <T extends IForgeRegistryEntry<T>> T register(IForgeRegistry<T> registry, T thing, String name) {
//		thing.setRegistryName(Util.getResource(name));
//		registry.register(thing);
//		return thing;
//	}
}
