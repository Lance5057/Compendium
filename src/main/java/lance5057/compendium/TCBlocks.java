package lance5057.compendium;

import lance5057.compendium.core.workstations.blocks.HammeringStationBlock;
import net.minecraft.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TCBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);
	
	public static final RegistryObject<HammeringStationBlock> HAMMERING_STATION = BLOCKS.register("hammering_station", () -> new HammeringStationBlock());

	public static void register(IEventBus modBus) {
		
		BLOCKS.register(modBus);
	}
}
