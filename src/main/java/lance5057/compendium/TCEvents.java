package lance5057.compendium;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber()
public class TCEvents {
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {

//		TinkersCompendium.parts.registerItems(event);
//		TinkersCompendium.tools.registerItems(event);
//		TinkersCompendium.workstations.registerItems(event);
//		TinkersCompendium.textiles.registerItems(event);
//
////		if (TinkersCompendium.bloodmagic != null)
////			TinkersCompendium.bloodmagic.registerItems(event);
////		if (TinkersCompendium.botania != null)
////			TinkersCompendium.botania.registerItems(event);

		//TinkersCompendium.items.registerItems(event);
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
//		TinkersCompendium.mats.registerBlocks(event);
//		TinkersCompendium.tools.registerBlocks(event);
//		TinkersCompendium.workstations.registerBlocks(event);
//		TinkersCompendium.textiles.registerBlocks(event);
//
////		if (TinkersCompendium.bloodmagic != null)
////			TinkersCompendium.bloodmagic.registerBlocks(event);
////		if (TinkersCompendium.botania != null)
////			TinkersCompendium.botania.registerBlocks(event);

		//TinkersCompendium.blocks.registerBlocks(event);
	}
}
