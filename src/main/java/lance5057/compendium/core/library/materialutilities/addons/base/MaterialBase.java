package lance5057.compendium.core.library.materialutilities.addons.base;

import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public abstract interface MaterialBase {

	public abstract void setupClient(MaterialHelper mat);

	public abstract void setup(final FMLCommonSetupEvent event);
}
