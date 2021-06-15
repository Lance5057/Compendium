package lance5057.compendium.indexes.metals;

import java.util.ArrayList;
import java.util.List;

import lance5057.compendium.appendixes.construction.AppendixConstruction;
import lance5057.compendium.appendixes.construction.materialhelper.ConstructionMaterialHelper;
import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import net.minecraft.item.ItemTier;

public class IndexVanillaMetals {

    public static List<MaterialHelperBase> IRON = new ArrayList<MaterialHelperBase>();
    public static List<MaterialHelperBase> GOLD = new ArrayList<MaterialHelperBase>();
    public static List<MaterialHelperBase> NETHERITE = new ArrayList<MaterialHelperBase>();

    public IndexVanillaMetals() {
	// IRON ------------------------------------------------------//
	MetallurgyMaterialHelper iron = new MetallurgyMaterialHelper("iron", "minecraft", ItemTier.IRON)
		.withComponents().withAdvancedTools();
	IRON.add(iron);
	AppendixMetallurgy.metals.add(iron);
	
	ConstructionMaterialHelper constructionIron = new ConstructionMaterialHelper("iron").withBase()
		.withDungeon().withShingles();
	AppendixConstruction.constructs.add(constructionIron);

	// GOLD ------------------------------------------------------//
	MetallurgyMaterialHelper gold = new MetallurgyMaterialHelper("gold", "minecraft", ItemTier.GOLD)
		.withComponents().withAdvancedTools();
	GOLD.add(gold);
	AppendixMetallurgy.metals.add(gold);
	
	ConstructionMaterialHelper constructionGold = new ConstructionMaterialHelper("gold").withBase()
		.withDungeon().withShingles();
	AppendixConstruction.constructs.add(constructionGold);

//	// NETHERITE ------------------------------------------------------//
//	MetallurgyMaterialHelper netherite = new MetallurgyMaterialHelper("netherite", "minecraft", ItemTier.NETHERITE)
//		.withComponents().withAdvancedTools();
//	NETHERITE.add(netherite);
//	AppendixMetallurgy.metals.add(netherite);
    }

}
