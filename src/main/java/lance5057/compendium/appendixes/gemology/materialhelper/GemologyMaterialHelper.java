package lance5057.compendium.appendixes.gemology.materialhelper;

import lance5057.compendium.Reference;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import net.minecraft.item.IItemTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class GemologyMaterialHelper extends MaterialHelperBase {
    public GemologyMaterialHelper(String name) {
	this(name, Reference.MOD_ID, null);
    }

    public GemologyMaterialHelper(String name, IItemTier tier) {
	this(name, Reference.MOD_ID, tier);
    }

    public GemologyMaterialHelper(String name, String parentMod, IItemTier tier) {
	this.name = name;
	this.parentMod = parentMod;
	this.tier = tier;

	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
	ITEMS.register(modEventBus);
	BLOCKS.register(modEventBus);
    }
    
    @Override
    public void setup() {
	// TODO Auto-generated method stub
	
    }
}
