package lance5057.compendium.appendixes.metallurgy;

import java.util.ArrayList;
import java.util.List;

import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class AppendixMetallurgy {
    public static List<MetallurgyMaterialHelper> metals = new ArrayList<>();
    
    public static void client(FMLClientSetupEvent event)
    {
	for (MetallurgyMaterialHelper mh : AppendixMetallurgy.metals) {
	    mh.client(event);
	}
    }

    public static void setup() {
	for (MetallurgyMaterialHelper mh : AppendixMetallurgy.metals) {
	    mh.setup();
	}
    }
}
