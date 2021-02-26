package lance5057.compendium.appendixes.metallurgy.materialhelper;

import lance5057.compendium.Reference;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import net.minecraft.item.IItemTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class MetallurgyMaterialHelper extends MaterialHelperBase {
    public MetallurgyMaterialHelper(String name) {
	this(name, Reference.MOD_ID, null);
    }

    public MetallurgyMaterialHelper(String name, IItemTier tier) {
	this(name, Reference.MOD_ID, tier);
    }

    public MetallurgyMaterialHelper(String name, String parentMod, IItemTier tier) {
	this.name = name;
	this.parentMod = parentMod;
	this.tier = tier;

	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
	ITEMS.register(modEventBus);
	BLOCKS.register(modEventBus);
    }
}
