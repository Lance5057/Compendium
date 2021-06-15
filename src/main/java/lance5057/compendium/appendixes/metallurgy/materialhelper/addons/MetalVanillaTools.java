package lance5057.compendium.appendixes.metallurgy.materialhelper.addons;

import lance5057.compendium.appendixes.metallurgy.materialhelper.MetallurgyMaterialHelper;
import lance5057.compendium.core.data.builders.TCBlockModels;
import lance5057.compendium.core.data.builders.TCEnglishLoc;
import lance5057.compendium.core.data.builders.TCItemModels;
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

    public MetalVanillaTools(MetallurgyMaterialHelper mm) {
	
    }

    @Override
    public void setupClient(MaterialHelperBase mat) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setup(MaterialHelperBase mm) {
	SWORD = mm.ITEMS.register(mm.name + "sword", () -> new SwordItem(mm.tier, 3, -2.4f,
		new Item.Properties().group(MetallurgyMaterialHelper.GROUP_METAL)));
	PICKAXE = mm.ITEMS.register(mm.name + "pickaxe", () -> new PickaxeItem(mm.tier, 1, -2.8F,
		(new Item.Properties()).group(MetallurgyMaterialHelper.GROUP_METAL)));
	SHOVEL = mm.ITEMS.register(mm.name + "shovel", () -> new ShovelItem(mm.tier, 1.5F, -3.0F,
		(new Item.Properties()).group(MetallurgyMaterialHelper.GROUP_METAL)));
	AXE = mm.ITEMS.register(mm.name + "axe", () -> new AxeItem(mm.tier, 5.0F, -3.0F,
		(new Item.Properties()).group(MetallurgyMaterialHelper.GROUP_METAL)));
	HOE = mm.ITEMS.register(mm.name + "hoe", () -> new HoeItem(mm.tier, -3, 0.0F,
		(new Item.Properties()).group(MetallurgyMaterialHelper.GROUP_METAL)));
    }

    public static void registerBlockModels(TCBlockModels model, String name) {

    }

    public static void addTranslations(MetalVanillaTools m, TCEnglishLoc loc, String capName) {
	loc.add(m.AXE.get(), capName + " Axe");
	loc.add(m.HOE.get(), capName + " Hoe");
	loc.add(m.PICKAXE.get(), capName + " Pickaxe");
	loc.add(m.SHOVEL.get(), capName + " Shovel");
	loc.add(m.SWORD.get(), capName + " Sword");
    }

    public static void registerItemModels(MetalVanillaTools m,TCItemModels model, String name) {
	model.withExistingParent(m.AXE.getId().getPath(), model.mcLoc("item/handheld"))
		.texture("layer0", model.modLoc("item/material/" + name + "/" + m.AXE.getId().getPath()))
		.texture("layer1", model.modLoc("item/axebase"));

	model.withExistingParent(m.HOE.getId().getPath(), model.mcLoc("item/handheld"))
		.texture("layer0", model.modLoc("item/material/" + name + "/" + m.HOE.getId().getPath()))
		.texture("layer1", model.modLoc("item/hoebase"));

	model.withExistingParent(m.PICKAXE.getId().getPath(), model.mcLoc("item/handheld"))
		.texture("layer0", model.modLoc("item/material/" + name + "/" + m.PICKAXE.getId().getPath()))
		.texture("layer1", model.modLoc("item/pickaxebase"));

	model.withExistingParent(m.SHOVEL.getId().getPath(), model.mcLoc("item/handheld"))
		.texture("layer0", model.modLoc("item/material/" + name + "/" + m.SHOVEL.getId().getPath()))
		.texture("layer1", model.modLoc("item/shovelbase"));

	model.withExistingParent(m.SWORD.getId().getPath(), model.mcLoc("item/handheld"))
		.texture("layer0", model.modLoc("item/material/" + name + "/" + m.SWORD.getId().getPath()))
		.texture("layer1", model.modLoc("item/swordbase"));
    }
}
