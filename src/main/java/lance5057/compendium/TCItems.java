package lance5057.compendium;

import lance5057.compendium.core.items.MegalithStoneItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
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

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

	public static final RegistryObject<BlockNamedItem> HAMMERING_STATION_ITEMBLOCK = ITEMS.register(
			"hammering_station_itemblock",
			() -> new BlockNamedItem(TCBlocks.HAMMERING_STATION.get(), new Item.Properties().group(TCItems.TCITEMS)));

	public static final RegistryObject<MegalithStoneItem> MEGALITH_STONE = ITEMS.register("megalith_stone",
			() -> new MegalithStoneItem(new Item.Properties().group(TCItems.TCITEMS)));

	public static void register(IEventBus modBus) {
		ITEMS.register(modBus);
	}
}
