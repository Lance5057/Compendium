package lance5057.compendium.appendixes.metallurgy.materialhelper;

import lance5057.compendium.Reference;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalAdvancedTools;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialBasic;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalMaterialComponents;
import lance5057.compendium.appendixes.metallurgy.materialhelper.addons.MetalVanillaTools;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class MetallurgyMaterialHelper extends MaterialHelperBase {
    public static final ItemGroup GROUP_METAL = new ItemGroup("compendium.materials.metal") {
	@Override
	public ItemStack createIcon() {
	    return new ItemStack(Items.IRON_INGOT);
	}
    };

    private MetalMaterialBasic basic;
    private MetalVanillaTools vtools;
    private MetalMaterialComponents comp;
    private MetalAdvancedTools atools;

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
    
    @OnlyIn(Dist.CLIENT)
    public void client(FMLClientSetupEvent event)
    {
	basic.setupClient(this);
	vtools.setupClient(this);
	comp.setupClient(this);
	atools.setupClient(this);
    }

    // Base
    public MetallurgyMaterialHelper withBase() {
	basic = new MetalMaterialBasic(this);
	return this;
    }

    public boolean hasBase() {
	return basic != null;
    }

    public MetalMaterialBasic getBase() {
	return basic;
    }

    // Vanilla Tools
    public MetallurgyMaterialHelper withVanillaTools() {
	vtools = new MetalVanillaTools(this);
	return this;
    }

    public boolean hasVanillaTools() {
	return vtools != null;
    }

    public MetalVanillaTools getVanillaTools() {
	return vtools;
    }

    // Vanilla Tools
    public MetallurgyMaterialHelper withAdvancedTools() {
	atools = new MetalAdvancedTools(this);
	return this;
    }

    public boolean hasAdvancedTools() {
	return atools != null;
    }

    public MetalAdvancedTools getAdvancedTools() {
	return atools;
    }

    // Components
    public MetallurgyMaterialHelper withComponents() {
	comp = new MetalMaterialComponents(this);
	return this;
    }

    public boolean hasComponents() {
	return comp != null;
    }

    public MetalMaterialComponents getComponents() {
	return comp;
    }
}
