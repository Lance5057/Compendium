package lance5057.compendium.indexes.metals;

import java.util.ArrayList;
import java.util.List;

import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.library.CompendiumItemTier;
import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public class IndexBaseMetals {
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

    public IndexBaseMetals() {
	BRASS.add(new MetallurgyMaterialHelper("brass", this.BRASS_TIER));

	BRONZE.add(new MetallurgyMaterialHelper("bronze", this.BRONZE_TIER));

	COPPER.add(new MetallurgyMaterialHelper("copper", this.COPPER_TIER));

	ELECTRUM.add(new MetallurgyMaterialHelper("electrum", this.ELECTRUM_TIER));

	NICKEL.add(new MetallurgyMaterialHelper("nickel", this.NICKEL_TIER));

	STEEL.add(new MetallurgyMaterialHelper("steel", this.STEEL_TIER));

	TIN.add(new MetallurgyMaterialHelper("tin", this.TIN_TIER));

	ZINC.add(new MetallurgyMaterialHelper("zinc", this.ZINC_TIER));

	ROSEGOLD.add(new MetallurgyMaterialHelper("rosegold", this.ROSEGOLD_TIER));

	PLATINUM.add(new MetallurgyMaterialHelper("platinum", this.PLATINUM_TIER));

	SILVER.add(new MetallurgyMaterialHelper("silver", this.SILVER_TIER));
    }
}
