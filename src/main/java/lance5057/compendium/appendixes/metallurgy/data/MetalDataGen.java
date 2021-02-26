package lance5057.compendium.appendixes.metallurgy.data;

import lance5057.compendium.Compendium;
import lance5057.compendium.Reference;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MetalDataGen {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
	Compendium.logger.debug("Metallurgy Data Generator Started!");
	DataGenerator generator = event.getGenerator();

	
    }
}
