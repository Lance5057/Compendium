package lance5057.compendium;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TCItems {

	public static final ItemGroup TCITEMS = new ItemGroup("compendium") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(Items.SHIELD);
		}
	};

	public static Item book = new Item(new Item.Properties().group(TCITEMS));

	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MOD_ID);

	public static void register(IEventBus modBus) {
		ITEMS.register(modBus);
	}
}
