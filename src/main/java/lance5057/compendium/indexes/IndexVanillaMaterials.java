package lance5057.compendium.indexes;

import java.util.List;

import lance5057.compendium.appendixes.gemology.materialhelper.GemologyMaterialHelper;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.core.data.builders.TCItemTags;
import lance5057.compendium.core.library.CompendiumItemTier;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public class IndexVanillaMaterials {
    public static List<MaterialHelperBase> IRON;
    public static List<MaterialHelperBase> GOLD;
    public static List<MaterialHelperBase> DIAMOND;

    public static List<MaterialHelperBase> EMERALD;
    public static IItemTier EMERALD_TIER = new CompendiumItemTier(3, 320, 10f, 4f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("gems/emerald"));
    });

    public static List<MaterialHelperBase> BONE;
    public static IItemTier BONE_TIER = new CompendiumItemTier(1, 120, 3f, 2f, 22, () -> {
	return Ingredient.fromItems(Items.BONE);
    });

    public static List<MaterialHelperBase> QUARTZ;
    public static IItemTier QUARTZ_TIER = new CompendiumItemTier(1, 430, 5.5f, 7f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("gems/quartz"));
    });

    public static List<MaterialHelperBase> LAPIS;
    public static IItemTier LAPIS_TIER = new CompendiumItemTier(0, 120, 2f, 2f, 22, () -> {
	return Ingredient.fromItems(Items.LAPIS_LAZULI);
    });

    public static List<MaterialHelperBase> OBSIDIAN;
    public static IItemTier OBSIDIAN_TIER = new CompendiumItemTier(1, 120, 3f, 2f, 22, () -> {
	return Ingredient.fromItems(Items.OBSIDIAN);
    });

    public static List<MaterialHelperBase> ICE;
    public static IItemTier ICE_TIER = new CompendiumItemTier(1, 120, 4.00f, 3.00f, 22, () -> {
	return Ingredient.fromTag(TCItemTags.ItemTag("ingots/ice"));
    });

    public IndexVanillaMaterials() {
	IRON.add(new MetallurgyMaterialHelper("iron", ItemTier.IRON));
	
	GOLD.add(new MetallurgyMaterialHelper("gold", ItemTier.GOLD));
	
	DIAMOND.add(new GemologyMaterialHelper("diamond", ItemTier.DIAMOND));
	
	EMERALD.add(new GemologyMaterialHelper("emerald", EMERALD_TIER));
	
	BONE.add(new GemologyMaterialHelper("bone", BONE_TIER));
	
	QUARTZ.add(new GemologyMaterialHelper("quartz", QUARTZ_TIER));
	
	LAPIS.add(new GemologyMaterialHelper("lapis", LAPIS_TIER));
	
	OBSIDIAN.add(new GemologyMaterialHelper("obsidian", OBSIDIAN_TIER));
	
	ICE.add(new GemologyMaterialHelper("ice", ICE_TIER));
    }
}
