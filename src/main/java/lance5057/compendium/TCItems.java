package lance5057.compendium;

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

	public void registerItems(final RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();

		for (Item i : ITEMS) {
			registry.register(i);
		}
	}

}
