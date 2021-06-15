package lance5057.compendium.appendixes.oredressing;

import java.util.ArrayList;
import java.util.List;

import lance5057.compendium.appendixes.oredressing.materialhelper.OreDressingMaterialHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class AppendixOreDressing {
    public static List<OreDressingMaterialHelper> ores = new ArrayList<>();
    
    public static void client(FMLClientSetupEvent event)
    {
	for (OreDressingMaterialHelper mh : AppendixOreDressing.ores) {
	    mh.client(event);
	}
    }
    
    public static void setup()
    {
	for (OreDressingMaterialHelper mh : AppendixOreDressing.ores) {
	    mh.setup();
	}
    }
}
