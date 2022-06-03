//package lance5057.compendium.indexes.metals;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
//import lance5057.compendium.core.data.builders.TCItemTags;
//import lance5057.compendium.core.library.CompendiumItemTier;
//import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
//import net.minecraft.world.item.Tier;
//import net.minecraft.world.item.crafting.Ingredient;
//
//public class IndexCompendiumAlloys {
//	public static List<MaterialHelperBase> AEONSTEEL = new ArrayList<MaterialHelperBase>();
//	public static Tier AEONSTEEL_TIER = new CompendiumItemTier(4, 500, 15f, 4f, 22, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/aeonsteel"));
//	});
//
//	public static List<MaterialHelperBase> QUEENSGOLD;
//	public static Tier QUEENSGOLD_TIER = new CompendiumItemTier(1, 70, 3.00f, 3.00f, 22, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/queensgold"));
//	});
//
//	public static List<MaterialHelperBase> DOGBEARIUM;
//	public static Tier DOGBEARIUM_TIER = new CompendiumItemTier(2, 150, 5.00f, 9.00f, 22, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/dogbearium"));
//	});
//
//	public static List<MaterialHelperBase> SINISTERIUM;
//	public static Tier SINISTERIUM_TIER = new CompendiumItemTier(3, 224, 5.00f, 5.00f, 22, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/sinisterium"));
//	});
//
//	public static List<MaterialHelperBase> NIHILITE;
//	public static Tier NIHILITE_TIER = new CompendiumItemTier(4, 400, 9.7f, 7.1f, 22, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/nihilite"));
//	});
//
//	public static List<MaterialHelperBase> ORICHALCUM;
//	public static Tier ORICHALCUM_TIER = new CompendiumItemTier(3, 180, 5.3f, 6.23f, 22, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/orichalcum"));
//	});
//
//	public static List<MaterialHelperBase> PANDORIUM;
//	public static Tier PANDORIUM_TIER = new CompendiumItemTier(3, 999, 10.2f, 8.72f, 22, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/pandorium"));
//	});
//
//	public static List<MaterialHelperBase> VALYRIANSTEEL;
//	public static Tier VALYRIANSTEEL_TIER = new CompendiumItemTier(3, 610, 7f, 7f, 22, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/valyriansteel"));
//	});
//
//	public static List<MaterialHelperBase> FROSTSTEEL;
//	public static Tier FROSTSTEEL_TIER = new CompendiumItemTier(3, 610, 7f, 7f, 22, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/froststeel"));
//	});
//
//	// Advanced Materials
//	public static List<MaterialHelperBase> RADIANTGOLD;
//	public static List<MaterialHelperBase> HALLOWEDSILVER;
//	public static List<MaterialHelperBase> ENERGETICELECTRUM;
//
//	// Wanderlust Materials
//	public static List<MaterialHelperBase> MITHRIL;
//	public static Tier MITHRIL_TIER = new CompendiumItemTier(3, 800, 8f, 6f, 22, () -> {
//		return Ingredient.of(TCItemTags.ItemTag("ingots/mithril"));
//	});
//
//	public IndexCompendiumAlloys() {
//		AEONSTEEL.add(new MetallurgyMaterialHelper("aeonsteel", AEONSTEEL_TIER));
//
//		QUEENSGOLD.add(new MetallurgyMaterialHelper("queensgold", QUEENSGOLD_TIER));
//
//		DOGBEARIUM.add(new MetallurgyMaterialHelper("dogbearium", DOGBEARIUM_TIER));
//
//		SINISTERIUM.add(new MetallurgyMaterialHelper("sinisterium", SINISTERIUM_TIER));
//
//		NIHILITE.add(new MetallurgyMaterialHelper("nihilite", NIHILITE_TIER));
//
//		ORICHALCUM.add(new MetallurgyMaterialHelper("orichalcum", ORICHALCUM_TIER));
//
//		PANDORIUM.add(new MetallurgyMaterialHelper("pandorium", PANDORIUM_TIER));
//
//		VALYRIANSTEEL.add(new MetallurgyMaterialHelper("valyriansteel", VALYRIANSTEEL_TIER));
//
//		FROSTSTEEL.add(new MetallurgyMaterialHelper("froststeel", FROSTSTEEL_TIER));
//	}
//}
