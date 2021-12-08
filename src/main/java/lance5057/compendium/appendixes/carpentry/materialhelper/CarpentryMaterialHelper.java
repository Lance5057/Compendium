package lance5057.compendium.appendixes.carpentry.materialhelper;

import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryFurniture;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryMaterialBasic;
import lance5057.compendium.appendixes.carpentry.materialhelper.addons.CarpentryEmptyShingles;
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
    
    public String log = "log";

    private CarpentryMaterialBasic basic;
    private CarpentryEmptyShingles comp;

    private CarpentryFurniture furniture;

    public CarpentryMaterialHelper(String name) {
	this(name, Reference.MOD_ID, "log");
    }
    
    public CarpentryMaterialHelper(String name, String log) {
	this(name, Reference.MOD_ID, log);
    }

    public CarpentryMaterialHelper(String name, String parentMod, String log) {
	this.name = name;
	this.parentMod = parentMod;
	this.log = log;

	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
	ITEMS.register(modEventBus);
	BLOCKS.register(modEventBus);
    }

    @Override
    public void setup() {
	if(this.hasBase()) basic.setup(this);
	if(this.hasShingles()) comp.setup(this);
	if(this.hasFurniture()) furniture.setup(this);
    }

    @OnlyIn(Dist.CLIENT)
    public void client(FMLClientSetupEvent event) {
	if(this.hasBase()) basic.setupClient(this);
	if(this.hasShingles()) comp.setupClient(this);
	if(this.hasFurniture()) furniture.setupClient(this);
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
    public CarpentryMaterialHelper withShingles(String log) {
	comp = new CarpentryEmptyShingles(this, log);
	return this;
    }

    public boolean hasShingles() {
	return comp != null;
    }

    public CarpentryEmptyShingles getShingles() {
	return comp;
    }
    
 // Components
    public CarpentryMaterialHelper withFurniture() {
	furniture = new CarpentryFurniture(this);
	return this;
    }

    public boolean hasFurniture() {
	return furniture != null;
    }

    public CarpentryFurniture getFurniture() {
	return furniture;
    }

}
