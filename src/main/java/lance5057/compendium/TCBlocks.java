package lance5057.tDefense;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.IForgeRegistry;

public class TCBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();

	public static void register(IEventBus modBus) {
        //BLOCKS.register(modBus);
    }
	
	public void registerBlocks(final RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();

		for (Block i : BLOCKS) {
			registry.register(i);
		}
		
		//TinkersCompendium.proxy.registerItemRenderer(book, 0, "book");
	}
	
//	public static Block registerBlock(String name, Material mat) {
//		Block block = new Block(mat)
//				.setRegistryName(new ResourceLocation(Reference.MOD_ID, name)).setUnlocalizedName(name);
//		blocks.add(block);
//		return block;
//	}
//	
//	public static Block registerBlock(Block block,String name) {
//		block.setRegistryName(new ResourceLocation(Reference.MOD_ID, name)).setUnlocalizedName(name);
//		blocks.add(block);
//		return block;
//	}
//
//	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
//		for (Block i : blocks) {
//			event.getRegistry().register(i);
//		}
//	}
}
