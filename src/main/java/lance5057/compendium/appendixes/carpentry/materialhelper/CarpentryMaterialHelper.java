package lance5057.compendium.appendixes.carpentry.materialhelper;

import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryMaterialBasic;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryMaterialComponents;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class CarpentryMaterialHelper extends MaterialHelperBase {
    public static final ItemGroup GROUP_WOOD = new ItemGroup("compendium.materials.wood") {
	@Override
	public ItemStack createIcon() {
	    return new ItemStack(Items.OAK_LOG);
	}
    };

    private CarpentryMaterialBasic basic;
    private CarpentryMaterialComponents comp;

    public CarpentryMaterialHelper(String name) {
	this(name, Reference.MOD_ID);
    }

    public CarpentryMaterialHelper(String name, String parentMod) {
	this.name = name;
	this.parentMod = parentMod;

	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
	ITEMS.register(modEventBus);
	BLOCKS.register(modEventBus);
    }
    
    @OnlyIn(Dist.CLIENT)
    public void client(FMLClientSetupEvent event)
    {
	basic.setupClient(this);
    }

    // Base
    public CarpentryMaterialHelper withBase() {
	basic = new CarpentryMaterialBasic(this);
	return this;
    }

    public boolean hasBase() {
	return basic != null;
    }

    public CarpentryMaterialBasic getBase() {
	return basic;
    }

    // Components
    public CarpentryMaterialHelper withComponents() {
	comp = new CarpentryMaterialComponents(this);
	return this;
    }

    public boolean hasComponents() {
	return comp != null;
    }

    public CarpentryMaterialComponents getComponents() {
	return comp;
    }
}
