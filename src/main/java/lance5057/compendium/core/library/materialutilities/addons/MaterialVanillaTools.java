//package lance5057.compendium.core.library.materialutilities.addons;
//
//import lance5057.compendium.Reference;
//import lance5057.compendium.CompendiumItems;
//import lance5057.compendium.core.library.materialutilities.MaterialHelper;
//import lance5057.compendium.core.library.materialutilities.addons.base.MaterialBase;
//import net.minecraft.item.AxeItem;
//import net.minecraft.item.HoeItem;
//import net.minecraft.item.Item;
//import net.minecraft.item.PickaxeItem;
//import net.minecraft.item.ShovelItem;
//import net.minecraft.item.SwordItem;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.fml.RegistryObject;
//import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
//
//public class MaterialVanillaTools implements MaterialBase {
//
//    public RegistryObject<Item> SWORD;
//    public RegistryObject<Item> PICKAXE;
//    public RegistryObject<Item> SHOVEL;
//    public RegistryObject<Item> AXE;
//    public RegistryObject<Item> HOE;
//
//    public MaterialVanillaTools(MaterialHelper mh) {
//	SWORD = mh.ITEMS.register(mh.name + "sword",
//		() -> new SwordItem(mh.tier, 3, -2.4f, new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));
//	PICKAXE = mh.ITEMS.register(mh.name + "pickaxe", () -> new PickaxeItem(mh.tier, 1, -2.8F,
//		(new Item.Properties()).group(CompendiumItems.GROUP_MATERIALS)));
//	SHOVEL = mh.ITEMS.register(mh.name + "shovel", () -> new ShovelItem(mh.tier, 1.5F, -3.0F,
//		(new Item.Properties()).group(CompendiumItems.GROUP_MATERIALS)));
//	AXE = mh.ITEMS.register(mh.name + "axe", () -> new AxeItem(mh.tier, 5.0F, -3.0F,
//		(new Item.Properties()).group(CompendiumItems.GROUP_MATERIALS)));
//	HOE = mh.ITEMS.register(mh.name + "hoe",
//		() -> new HoeItem(mh.tier, -3, 0.0F, (new Item.Properties()).group(CompendiumItems.GROUP_MATERIALS)));
////		bow;
////		crossbow;
////		shield;
////		hammer;
////		shears;
////		fishingrod;
////		trident;
////		sickle;
//
////		TCItems.ITEMS.add(sword);
////		TCItems.ITEMS.add(pickaxe);
////		TCItems.ITEMS.add(shovel);
////		TCItems.ITEMS.add(axe);
////		TCItems.ITEMS.add(hoe);
//    }
//
//    @Override
//    public void setupClient(MaterialHelper mat) {
//	// TODO Auto-generated method stub
//	
//    }
//
//    @Override
//    public void setup(FMLCommonSetupEvent event) {
//	// TODO Auto-generated method stub
//	
//    }
//}
