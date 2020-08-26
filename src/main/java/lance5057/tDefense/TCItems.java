package lance5057.tDefense;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class TCItems {
	
	public static final ItemGroup TCITEMS = new ItemGroup("compendium") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.SHIELD);
        }
    };
	
	public static Item book = new Item(new Item.Properties().group(TCITEMS));
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public TCItems()
	{
		book.setRegistryName(new ResourceLocation(Reference.MOD_ID, "tcbook"));
		ITEMS.add(book);
	}
	
	//public static final RegistryObject<Item> book = ITEMS.register("tcbook", () -> new Item(new Item.Properties().group(TCITEMS)));
	
//	public static void register(IEventBus modBus) {
//        ITEMS.register(modBus);
//    }
	
//	public void preInit(FMLPreInitializationEvent e) {
//		book = registerItem("book", new ItemCompendiumBook(), TinkersCompendium.tab );
//		
//	}
//
//	public void init(FMLInitializationEvent e) {
//		// TODO Auto-generated method stub
//
//	}
//
//	public void postInit(FMLPostInitializationEvent e) {
//		// TODO Auto-generated method
//
//	}
//	
//	public static Item registerItem(String name, CreativeTabs tab) {
//		Item item = new Item().setRegistryName(new ResourceLocation(Reference.MOD_ID, name)).setUnlocalizedName(name);
//		item.setCreativeTab(tab);
//		items.add(item);
//		return item;
//	}
//	
//	public static Item registerItem(String name, Item item, CreativeTabs tab) {
//		item.setRegistryName(new ResourceLocation(Reference.MOD_ID, name)).setUnlocalizedName(name);
//		item.setCreativeTab(tab);
//		items.add(item);
//		return item;
//	}
//
//	public static Item registerItemBlock(String name, Block b, CreativeTabs tab) {
//		Item item = new ItemBlock(b).setRegistryName(new ResourceLocation(Reference.MOD_ID, name))
//				.setUnlocalizedName(name);
//		item.setCreativeTab(tab);
//		items.add(item);
//		return item;
//	}
//	
//	public static Item registerItemBlock(String name, Item item, Block b, CreativeTabs tab) {
//		item.setRegistryName(new ResourceLocation(Reference.MOD_ID, name))
//				.setUnlocalizedName(name);
//		item.setCreativeTab(tab);
//		items.add(item);
//		return item;
//	}
//	
	public void registerItems(final RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();

		for (Item i : ITEMS) {
			registry.register(i);
		}
		
		//TinkersCompendium.proxy.registerItemRenderer(book, 0, "book");
	}

}
