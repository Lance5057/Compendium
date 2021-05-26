package lance5057.compendium.appendixes.carpentry;

import java.util.ArrayList;
import java.util.List;

import lance5057.compendium.appendixes.carpentry.materialhelper.CarpentryMaterialHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class AppendixCarpentry {
    public static List<CarpentryMaterialHelper> woods = new ArrayList<>();
    
    public static void client(FMLClientSetupEvent event)
    {
	for (CarpentryMaterialHelper mh : AppendixCarpentry.woods) {
	    mh.client(event);
	}
    }
}
