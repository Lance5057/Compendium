package lance5057.compendium.appendixes.oredressing.materialhelper;

import lance5057.compendium.Reference;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import net.minecraft.item.IItemTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class OreDressingMaterialHelper extends MaterialHelperBase {
    public OreDressingMaterialHelper(String name) {
	this(name, Reference.MOD_ID);
    }

    public OreDressingMaterialHelper(String name, String parentMod) {
	this.name = name;
	this.parentMod = parentMod;

	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
	ITEMS.register(modEventBus);
	BLOCKS.register(modEventBus);
    }
}
