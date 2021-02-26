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
    

    public CompendiumMaterials() {

	// Compendium
	materials.add(AEONSTEEL = new MaterialHelper("aeonsteel", new CompendiumItemTier(4, 500, 15f, 4f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/aeonsteel"));
	})).withIngot().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools().withExtraTools());

	materials.add(
		QUEENSGOLD = new MaterialHelper("queensgold", new CompendiumItemTier(1, 70, 3.00f, 3.00f, 22, () -> {
		    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/queensgold"));
		})).withIngot().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools().withExtraTools());

	materials.add(
		DOGBEARIUM = new MaterialHelper("dogbearium", new CompendiumItemTier(2, 150, 5.00f, 9.00f, 22, () -> {
		    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/dogbearium"));
		})).withIngot().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools().withExtraTools());

	materials.add(
		SINISTERIUM = new MaterialHelper("sinisterium", new CompendiumItemTier(3, 224, 5.00f, 5.00f, 22, () -> {
		    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/sinisterium"));
		})).withIngot().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools().withExtraTools());

	materials.add(NIHILITE = new MaterialHelper("nihilite", new CompendiumItemTier(4, 400, 9.7f, 7.1f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/nihilite"));
	})).withIngot().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools().withExtraTools());

	materials.add(
		ORICHALCUM = new MaterialHelper("orichalcum", new CompendiumItemTier(3, 180, 5.3f, 6.23f, 22, () -> {
		    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/orichalcum"));
		})).withIngot().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools().withExtraTools());

	materials.add(
		PANDORIUM = new MaterialHelper("pandorium", new CompendiumItemTier(3, 999, 10.2f, 8.72f, 22, () -> {
		    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/pandorium"));
		})).withIngot().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools().withExtraTools());

	materials.add(
		VALYRIANSTEEL = new MaterialHelper("valyriansteel", new CompendiumItemTier(3, 610, 7f, 7f, 22, () -> {
		    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/valyriansteel"));
		})).withIngot().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools().withExtraTools());

	materials.add(ICE = new MaterialHelper("ice", new CompendiumItemTier(1, 120, 4.00f, 3.00f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/ice"));
	})).withIngot().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools().withExtraTools());

	materials.add(FROSTSTEEL = new MaterialHelper("froststeel", new CompendiumItemTier(3, 610, 7f, 7f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/froststeel"));
	})).withIngot().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools().withExtraTools());

	// WLR
	materials.add(MITHRIL = new MaterialHelper("mithril", new CompendiumItemTier(3, 800, 8f, 6f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("ingots/mithril"));
	})).withIngot().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools().withExtraTools());

	// Vanilla
	materials.add(
		IRON = new MaterialHelper("iron", ItemTier.IRON).withPremade(Ingredient.fromItems(Items.IRON_INGOT),
			Ingredient.fromItems(Items.IRON_NUGGET), Ingredient.fromItems(Items.IRON_BLOCK))
			.withExtraComponents().withAdvancedComponents().withExtraTools());

	materials.add(GOLD = new MaterialHelper("gold", ItemTier.GOLD)
		.withPremade(Ingredient.fromItems(Items.GOLD_INGOT), Ingredient.fromItems(Items.GOLD_NUGGET),
			Ingredient.fromItems(Items.GOLD_BLOCK))
		.withVanillaComponents().withExtraComponents().withAdvancedComponents().withExtraTools());

	materials.add(EMERALD = new MaterialHelper("emerald", new CompendiumItemTier(3, 320, 10f, 4f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/emerald"));
	})).withPremade(Ingredient.fromItems(Items.EMERALD), null, Ingredient.fromItems(Items.EMERALD_BLOCK))
		.withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools().withExtraTools());

	materials.add(DIAMOND = new MaterialHelper("diamond", ItemTier.DIAMOND)
		.withPremade(Ingredient.fromItems(Items.DIAMOND), null, Ingredient.fromItems(Items.DIAMOND_BLOCK))
		.withVanillaComponents().withExtraComponents().withAdvancedComponents().withExtraTools());

	materials.add(LAPIS = new MaterialHelper("lapis", new CompendiumItemTier(0, 120, 2f, 2f, 22, () -> {
	    return Ingredient.fromItems(Items.LAPIS_LAZULI);
	})).withPremade(Ingredient.fromItems(Items.LAPIS_LAZULI), null, Ingredient.fromItems(Items.LAPIS_BLOCK))
		.withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools().withExtraTools());

	materials.add(QUARTZ = new MaterialHelper("quartz", new CompendiumItemTier(1, 430, 5.5f, 7f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/quartz"));
	})).withPremade(Ingredient.fromItems(Items.QUARTZ), null, Ingredient.fromItems(Items.QUARTZ_BLOCK))
		.withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools().withExtraTools());

	materials.add(BONE = new MaterialHelper("bone", new CompendiumItemTier(1, 120, 3f, 2f, 22, () -> {
	    return Ingredient.fromItems(Items.BONE);
	})).withPremade(Ingredient.fromItems(Items.BONE), Ingredient.fromItems(Items.BONE_MEAL),
		Ingredient.fromItems(Items.BONE_BLOCK)).withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools()
		.withExtraTools());

	materials.add(OBSIDIAN = new MaterialHelper("obsidian", new CompendiumItemTier(1, 120, 3f, 2f, 22, () -> {
	    return Ingredient.fromItems(Items.OBSIDIAN);
	})).withPremade(null, null, Ingredient.fromItems(Items.OBSIDIAN)).withVanillaComponents().withExtraComponents().withAdvancedComponents()
		.withVanillaTools().withExtraTools());

	// Gems
	materials.add(SAPPHIRE = new MaterialHelper("sapphire", new CompendiumItemTier(3, 320, 4f, 4f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/sapphire"));
	})).withGem().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools()
		.withOre(5, 2, ToolType.PICKAXE, 4, 16, 5, 2, 2, Category.OCEAN).withExtraTools());

	materials.add(RUBY = new MaterialHelper("ruby", new CompendiumItemTier(3, 320, 4f, 10f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/ruby"));
	})).withGem().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools()
		.withOre(5, 2, ToolType.PICKAXE, 4, 16, 5, 2, 2, Category.MESA).withExtraTools());

	materials.add(CITRINE = new MaterialHelper("citrine", new CompendiumItemTier(3, 430, 5.5f, 7f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/citrine"));
	})).withGem().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools()
		.withOre(5, 2, ToolType.PICKAXE, 4, 16, 5, 2, 2, Category.DESERT).withExtraTools());

	materials.add(AMETHYST = new MaterialHelper("amethyst", new CompendiumItemTier(1, 530, 5.5f, 6.2f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/amethyst"));
	})).withGem().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools()
		.withOre(5, 2, ToolType.PICKAXE, 4, 16, 5, 2, 2, Category.TAIGA).withExtraTools());

	materials.add(TOPAZ = new MaterialHelper("topaz", new CompendiumItemTier(1, 530, 5.5f, 6.2f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/topaz"));
	})).withGem().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools()
		.withOre(5, 0, ToolType.PICKAXE, 4, 16, 5, 2, 2, Category.PLAINS).withExtraTools());

	materials.add(GARNET = new MaterialHelper("garnet", new CompendiumItemTier(1, 530, 4.5f, 7.2f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/garnet"));
	})).withGem().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools()
		.withOre(5, 2, ToolType.PICKAXE, 4, 16, 5, 2, 2, Category.SAVANNA).withExtraTools());

	materials.add(OPAL = new MaterialHelper("opal", new CompendiumItemTier(1, 530, 6.5f, 6.2f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/opal"));
	})).withGem().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools()
		.withOre(5, 1, ToolType.PICKAXE, 4, 16, 5, 2, 2, Category.SWAMP).withExtraTools());

	materials.add(TANZINITE = new MaterialHelper("tanzinite", new CompendiumItemTier(1, 630, 5.5f, 6.2f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/tanzinite"));
	})).withGem().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools()
		.withOre(5, 1, ToolType.PICKAXE, 4, 16, 5, 2, 2, Category.JUNGLE).withExtraTools());

	materials.add(AMBER = new MaterialHelper("amber", new CompendiumItemTier(0, 120, 2f, 3f, 22, () -> {
	    return Ingredient.fromTag(TCItemTags.ItemTag("gems/amber"));
	})).withGem().withVanillaComponents().withExtraComponents().withAdvancedComponents().withVanillaTools()
		.withOre(5, 0, ToolType.PICKAXE, 4, 16, 5, 2, 2, Category.FOREST).withExtraTools());

	
    }
}
