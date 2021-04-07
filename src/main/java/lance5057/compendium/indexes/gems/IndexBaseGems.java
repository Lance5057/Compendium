package lance5057.compendium.indexes.gems;

import java.util.List;

import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.library.CompendiumItemTier;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public class IndexBaseGems {
    public static List<MaterialHelperBase> SAPPHIRE;
    public static IItemTier SAPPHIRE_TIER = new CompendiumItemTier(3, 320, 4f, 4f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("gems/sapphire"));
    });

    public static List<MaterialHelperBase> RUBY;
    public static IItemTier RUBY_TIER = new CompendiumItemTier(3, 320, 4f, 10f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("gems/ruby"));
    });

    public static List<MaterialHelperBase> CITRINE;
    public static IItemTier CITRINE_TIER = new CompendiumItemTier(3, 430, 5.5f, 7f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("gems/citrine"));
    });

    public static List<MaterialHelperBase> AMETHYST;
    public static IItemTier AMETHYST_TIER = new CompendiumItemTier(1, 530, 5.5f, 6.2f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("gems/amethyst"));
    });

    public static List<MaterialHelperBase> TOPAZ;
    public static IItemTier TOPAZ_TIER = new CompendiumItemTier(1, 530, 5.5f, 6.2f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("gems/topaz"));
    });
    public static List<MaterialHelperBase> GARNET;
    public static IItemTier GARNET_TIER = new CompendiumItemTier(1, 530, 4.5f, 7.2f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("gems/garnet"));
    });

    public static List<MaterialHelperBase> OPAL;
    public static IItemTier OPAL_TIER = new CompendiumItemTier(1, 530, 6.5f, 6.2f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("gems/opal"));
    });

    public static List<MaterialHelperBase> TANZINITE;
    public static IItemTier TANZINITE_TIER = new CompendiumItemTier(1, 630, 5.5f, 6.2f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("gems/tanzinite"));
    });

    public static List<MaterialHelperBase> AMBER;
    public static IItemTier AMBER_TIER = new CompendiumItemTier(0, 120, 2f, 3f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("gems/amber"));
    });

}
