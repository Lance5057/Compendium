package lance5057.compendium.appendixes.metallurgy.materialhelper.addons;

import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.core.library.materialutilities.MaterialHelperBase;
import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class MetalMaterialComponents implements MaterialBase {
    public RegistryObject<Item> SETTING;
    public RegistryObject<Item> JUMPRINGS;
    public RegistryObject<Item> FILIGREE;
    public RegistryObject<Item> FOIL;
    public RegistryObject<Item> COIL;
    public RegistryObject<Item> SPRING;
    public RegistryObject<Item> CASING;
    public RegistryObject<Item> WIRE;
    public RegistryObject<Item> CLASP;
    public RegistryObject<Item> RINGSHANK;
    public RegistryObject<Item> RIVETS;
    public RegistryObject<Item> PLATE;
    public RegistryObject<Item> COIN;
    public RegistryObject<Item> GEAR;
    public RegistryObject<Item> ROD;
    public RegistryObject<Item> DUST;
    public RegistryObject<Item> TINYDUST;

    public MetalMaterialComponents(MetallurgyMaterialHelper mh) {
	PLATE = mh.ITEMS.register(mh.name + "plate",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	COIN = mh.ITEMS.register(mh.name + "coin",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	GEAR = mh.ITEMS.register(mh.name + "gear",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	ROD = mh.ITEMS.register(mh.name + "rod",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	COIL = mh.ITEMS.register(mh.name + "coil",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	SPRING = mh.ITEMS.register(mh.name + "spring",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	CASING = mh.ITEMS.register(mh.name + "casing",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	WIRE = mh.ITEMS.register(mh.name + "wire",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));

	CLASP = mh.ITEMS.register(mh.name + "clasp",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	RINGSHANK = mh.ITEMS.register(mh.name + "ringshank",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	RIVETS = mh.ITEMS.register(mh.name + "rivets",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	SETTING = mh.ITEMS.register(mh.name + "setting",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	JUMPRINGS = mh.ITEMS.register(mh.name + "jumprings",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	FILIGREE = mh.ITEMS.register(mh.name + "filigree",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	FOIL = mh.ITEMS.register(mh.name + "foil",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	DUST = mh.ITEMS.register(mh.name + "dust",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	TINYDUST = mh.ITEMS.register(mh.name + "tinydust",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
    }

    @Override
    public void setupClient(MaterialHelperBase mat) {

    }

    @Override
    public void setup(FMLCommonSetupEvent event) {
	// TODO Auto-generated method stub

    }
}
