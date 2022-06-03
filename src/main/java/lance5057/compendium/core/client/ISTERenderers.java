//package lance5057.compendium.core.client;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.Callable;
//
//import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
//
//public class ISTERenderers {
//
//    public static Map<String, Callable<ItemStackTileEntityRenderer>> shields = new HashMap<String, Callable<ItemStackTileEntityRenderer>>();
//    
//    public static Callable<ItemStackTileEntityRenderer> makeShield(String name)
//    {
//	shields.put(name, () -> {return new ShieldRenderer(name);});
//	
//	return shields.get(name);
//    }
//    
//}
