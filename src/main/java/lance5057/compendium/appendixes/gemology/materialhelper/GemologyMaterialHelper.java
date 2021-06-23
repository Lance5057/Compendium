package lance5057.compendium.appendixes.gemology.materialhelper;

import lance5057.compendium.Reference;
import lance5057.compendium.appendixes._template.materialhelper.addons._MaterialBasic;
import lance5057.compendium.appendixes.gemology.materialhelper.addons.BasicGemMaterial;
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

public class GemologyMaterialHelper extends MaterialHelperBase {
    public static final ItemGroup GROUP = new ItemGroup("compendium.materials.gems") {
	@Override
	public ItemStack createIcon() {
	    return new ItemStack(Items.BRICK);
	}
    };

    private BasicGemMaterial basic;

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

    @OnlyIn(Dist.CLIENT)
    public void client(FMLClientSetupEvent event) {
	basic.setupClient(this);
    }

    // Base
    public GemologyMaterialHelper withBase(int drops) {
	basic = new BasicGemMaterial(this, drops);
	return this;
    }

    public boolean hasBase() {
	return basic != null;
    }

    public BasicGemMaterial getBase() {
	return basic;
    }

}
