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

public class CompendiumItems {

	public static final ItemGroup GROUP_MATERIALS=new ItemGroup("compendium.materials"){@Override public ItemStack createIcon(){return new ItemStack(Items.SHIELD);}};

	public static final ItemGroup GROUP_WORKSTATIONS = new ItemGroup("compendium.workstations") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(CRAFTING_ANVIL_ITEMBLOCK.get());
		}
	};

	public static Item book = new Item(new Item.Properties().group(GROUP_MATERIALS));

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

	public static final RegistryObject<BlockNamedItem> HAMMERING_STATION_ITEMBLOCK = ITEMS.register("hammering_station_itemblock", () -> new BlockNamedItem(CompendiumBlocks.HAMMERING_STATION.get(), new Item.Properties().group(CompendiumItems.GROUP_WORKSTATIONS)));
	public static final RegistryObject<BlockNamedItem> SAWHORSE_STATION_ITEMBLOCK = ITEMS.register("sawhorse_station_itemblock", () -> new BlockNamedItem(CompendiumBlocks.SAWHORSE_STATION.get(), new Item.Properties().group(CompendiumItems.GROUP_WORKSTATIONS)));
	public static final RegistryObject<BlockNamedItem> CRAFTING_ANVIL_ITEMBLOCK = ITEMS.register("crafting_anvil_itemblock", () -> new BlockNamedItem(CompendiumBlocks.CRAFTING_ANVIL.get(), new Item.Properties().group(CompendiumItems.GROUP_WORKSTATIONS)));

	public static final RegistryObject<MegalithStoneItem> MEGALITH_STONE = ITEMS.register("megalith_stone", () -> new MegalithStoneItem(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));

	public static void register(IEventBus modBus) {
		ITEMS.register(modBus);
	}
}