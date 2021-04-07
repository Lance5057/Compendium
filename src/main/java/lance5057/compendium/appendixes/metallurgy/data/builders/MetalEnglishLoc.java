package lance5057.compendium.appendixes.metallurgy.data.builders;

import lance5057.compendium.appendixes.metallurgy.AppendixMetallurgy;
import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialBasic;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalVanillaTools;
import lance5057.compendium.core.data.builders.TCEnglishLoc;

public class MetalEnglishLoc
{
    public static void addTranslations(TCEnglishLoc loc) {
	for (MetallurgyMaterialHelper mh : AppendixMetallurgy.metals) {
	    String capName = mh.name.substring(0,1).toUpperCase() + mh.name.substring(1);
	    
	    if(mh.hasBase())
	    {
		MetalMaterialBasic b = mh.getBase();
		
		loc.add(b.INGOT.get(), capName + " Ingot");
		loc.add(b.NUGGET.get(), capName + " Nugget");
		loc.add(b.STORAGE_ITEMBLOCK.get(), capName + " Block");
	    }
	    
	    if(mh.hasVanillaTools())
	    {
		MetalVanillaTools b = mh.getVanillaTools();
		
		loc.add(b.AXE.get(), capName + " Axe");
		loc.add(b.HOE.get(), capName + " Hoe");
		loc.add(b.PICKAXE.get(), capName + " Pickaxe");
		loc.add(b.SHOVEL.get(), capName + " Shovel");
		loc.add(b.SWORD.get(), capName + " Sword");
	    }
	
	}
    }
}