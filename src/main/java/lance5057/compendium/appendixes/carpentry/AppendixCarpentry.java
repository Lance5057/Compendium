package lance5057.compendium.appendixes.carpentry;

import java.util.ArrayList;
import java.util.List;

import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class AppendixCarpentry {
    public static List<MetallurgyMaterialHelper> metals = new ArrayList<>();
    
    public static void client(FMLClientSetupEvent event)
    {
	for (MetallurgyMaterialHelper mh : AppendixCarpentry.metals) {
	    mh.client(event);
	}
    }
}
