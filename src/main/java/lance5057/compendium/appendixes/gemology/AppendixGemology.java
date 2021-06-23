package lance5057.compendium.appendixes.gemology;

import java.util.ArrayList;
import java.util.List;

import lance5057.compendium.appendixes.gemology.materialhelper.GemologyMaterialHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class AppendixGemology {
    public static List<GemologyMaterialHelper> gems = new ArrayList<>();
    
    public static void client(FMLClientSetupEvent event)
    {
	for (GemologyMaterialHelper mh : AppendixGemology.gems) {
	    mh.client(event);
	}
    }

    public static void setup() {
	for (GemologyMaterialHelper mh : AppendixGemology.gems) {
	    mh.setup();
	}
    }
}
