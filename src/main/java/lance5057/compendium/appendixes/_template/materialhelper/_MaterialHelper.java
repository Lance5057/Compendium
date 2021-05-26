package lance5057.compendium.appendixes._template.materialhelper;

import lance5057.compendium.Reference;
import lance5057.compendium.appendixes._template.materialhelper.addons._MaterialBasic;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class _MaterialHelper extends MaterialHelperBase {
    public static final ItemGroup GROUP_METAL = new ItemGroup("compendium.materials.wood") {
	@Override
	public ItemStack createIcon() {
	    return new ItemStack(Items.BRICK);
	}
    };

    private _MaterialBasic basic;

    public _MaterialHelper(String name) {
	this(name, Reference.MOD_ID);
    }

    public _MaterialHelper(String name, String parentMod) {
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
    public _MaterialHelper withBase() {
	basic = new _MaterialBasic(this);
	return this;
    }

    public boolean hasBase() {
	return basic != null;
    }

    public _MaterialBasic getBase() {
	return basic;
    }

}
