package lance5057.compendium.appendixes.metallurgy.materialhelper.addons;

import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
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
    public RegistryObject<Item> KEY;

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
	KEY = mh.ITEMS.register(mh.name + "key",
		() -> new Item(new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
    }

    @Override
    public void setupClient(MaterialHelperBase mat) {

    }

    @Override
    public void setup(FMLCommonSetupEvent event) {
	// TODO Auto-generated method stub

    }

    public static void registerBlockModels(MetalMaterialComponents m, TCBlockModels model, String name) {

    }

    public static void registerItemModels(MetalMaterialComponents m, TCItemModels model, String name) {
	model.forMaterialItem(m.SETTING, name);
	model.forMaterialItem(m.JUMPRINGS, name);
	model.forMaterialItem(m.FILIGREE, name);
	model.forMaterialItem(m.FOIL, name);
	model.forMaterialItem(m.COIL, name);
	model.forMaterialItem(m.SPRING, name);
	model.forMaterialItem(m.CASING, name);
	model.forMaterialItem(m.WIRE, name);
	model.forMaterialItem(m.CLASP, name);
	model.forMaterialItem(m.RINGSHANK, name);
	model.forMaterialItem(m.RIVETS, name);
	model.forMaterialItem(m.PLATE, name);
	model.forMaterialItem(m.COIN, name);
	model.forMaterialItem(m.GEAR, name);
	model.forMaterialItem(m.ROD, name);
	model.forMaterialItem(m.DUST, name);
	model.forMaterialItem(m.TINYDUST, name);
	model.forMaterialItem(m.KEY, name);
    }

    public static void addTranslations(MetalMaterialComponents m, TCEnglishLoc loc, String capName) {
	loc.add(m.SETTING.get(), capName + " Setting");
	loc.add(m.JUMPRINGS.get(), capName + " Jumprings");
	loc.add(m.FILIGREE.get(), capName + " Filigrees");
	loc.add(m.FOIL.get(), capName + " Foil");
	loc.add(m.COIL.get(), capName + " Coil");
	loc.add(m.SPRING.get(), capName + " Spring");
	loc.add(m.CASING.get(), capName + " Casing");
	loc.add(m.WIRE.get(), capName + " Wire");
	loc.add(m.CLASP.get(), capName + " Clasp");
	loc.add(m.RINGSHANK.get(), capName + " Ring Shank");
	loc.add(m.RIVETS.get(), capName + " Rivets");
	loc.add(m.PLATE.get(), capName + " Plate");
	loc.add(m.COIN.get(), capName + " Coin");
	loc.add(m.GEAR.get(), capName + " Gear");
	loc.add(m.ROD.get(), capName + " Rod");
	loc.add(m.DUST.get(), capName + " Dust");
	loc.add(m.TINYDUST.get(), capName + " Tiny Dust");
	loc.add(m.KEY.get(), capName + " Key");
    }
}
