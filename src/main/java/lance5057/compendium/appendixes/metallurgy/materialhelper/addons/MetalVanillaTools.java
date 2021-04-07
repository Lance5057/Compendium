package lance5057.compendium.appendixes.metallurgy.materialhelper.addons;

import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MetalVanillaTools implements MaterialBase {
    public RegistryObject<Item> SWORD;
    public RegistryObject<Item> PICKAXE;
    public RegistryObject<Item> SHOVEL;
    public RegistryObject<Item> AXE;
    public RegistryObject<Item> HOE;
    
    public MetalVanillaTools(MetallurgyMaterialHelper mm)
    {
	SWORD = mm.ITEMS.register(mm.name + "sword",
		() -> new SwordItem(mm.tier, 3, -2.4f, new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	PICKAXE = mm.ITEMS.register(mm.name + "pickaxe", () -> new PickaxeItem(mm.tier, 1, -2.8F,
		(new Item.Properties()).group(MetallurgyMaterialHelper.GROUP_METAL)));
	SHOVEL = mm.ITEMS.register(mm.name + "shovel", () -> new ShovelItem(mm.tier, 1.5F, -3.0F,
		(new Item.Properties()).group(MetallurgyMaterialHelper.GROUP_METAL)));
	AXE = mm.ITEMS.register(mm.name + "axe", () -> new AxeItem(mm.tier, 5.0F, -3.0F,
		(new Item.Properties()).group(MetallurgyMaterialHelper.GROUP_METAL)));
	HOE = mm.ITEMS.register(mm.name + "hoe",
		() -> new HoeItem(mm.tier, -3, 0.0F, (new Item.Properties()).group(MetallurgyMaterialHelper.GROUP_METAL)));
    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void setup(FMLCommonSetupEvent event) {
	// TODO Auto-generated method stub
	
    }
}
