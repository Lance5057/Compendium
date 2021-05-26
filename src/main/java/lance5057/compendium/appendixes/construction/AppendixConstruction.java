package lance5057.compendium.appendixes.construction;

import java.util.ArrayList;
import java.util.List;

import lance5057.compendium.appendixes.construction.materialhelper.ConstructionMaterialHelper;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class AppendixConstruction {
    public static List<ConstructionMaterialHelper> constructs = new ArrayList<>();    
    public static List<MaterialHelperBase> bases = new ArrayList<>();
    
    public static void client(FMLClientSetupEvent event)
    {
	for (ConstructionMaterialHelper mh : AppendixConstruction.constructs) {
	    mh.client(event);
	}
    }
}
