package lance5057.compendium.appendixes._template;

import java.util.ArrayList;
import java.util.List;

import lance5057.compendium.appendixes._template.materialhelper._MaterialHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class Appendix_ {
    public static List<_MaterialHelper> constructs = new ArrayList<>();
    
    public static void client(FMLClientSetupEvent event)
    {
	for (_MaterialHelper mh : Appendix_.constructs) {
	    mh.client(event);
	}
    }
}
