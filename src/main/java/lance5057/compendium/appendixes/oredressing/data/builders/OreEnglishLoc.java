package lance5057.compendium.appendixes.oredressing.data.builders;

import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialBasic;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalVanillaTools;
import lance5057.compendium.appendixes.oredressing.AppendixOreDressing;
import lance5057.compendium.appendixes.oredressing.materialhelper.OreDressingMaterialHelper;
import lance5057.compendium.core.data.builders.TCEnglishLoc;

public class OreEnglishLoc
{
    public static void addTranslations(TCEnglishLoc loc) {
//	for (MetallurgyMaterialHelper mh : AppendixMetallurgy.metals) {
//	    String capName = mh.name.substring(0,1).toUpperCase() + mh.name.substring(1);
//	    
//	    if(mh.hasBase())
//	    {
//		MetalMaterialBasic b = mh.getBase();
//		
//		loc.add(b.INGOT.get(), capName + " Ingot");
//		loc.add(b.NUGGET.get(), capName + " Nugget");
//		loc.add(b.STORAGE_ITEMBLOCK.get(), capName + " Block");
//	    }
//	    
//	    if(mh.hasVanillaTools())
//	    {
//		MetalVanillaTools b = mh.getVanillaTools();
//		
//		loc.add(b.AXE.get(), capName + " Axe");
//		loc.add(b.HOE.get(), capName + " Hoe");
//		loc.add(b.PICKAXE.get(), capName + " Pickaxe");
//		loc.add(b.SHOVEL.get(), capName + " Shovel");
//		loc.add(b.SWORD.get(), capName + " Sword");
//	    }
//	
//	}
	
	for (OreDressingMaterialHelper mh : AppendixOreDressing.ores) {
	    // MaterialOre ore = mh.getOre();
	    String capName = mh.name.substring(0,1).toUpperCase() + mh.name.substring(1);
	    if (mh.hasOre())
		loc.add(mh.getOre().ORE.get(), capName + " Ore");
	    if (mh.hasDenseOre())
		loc.add(mh.getDenseOre().ORE.get(), "Dense " + capName + " Ore");
	    if (mh.hasSparseOre())
		loc.add(mh.getSparseOre().ORE.get(), "Sparse " + capName + " Ore");
	}
    }
}
