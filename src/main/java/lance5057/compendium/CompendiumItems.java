package lance5057.compendium;

import lance5057.compendium.core.items.BurnableItem;
import lance5057.compendium.core.items.MegalithStoneItem;
import lance5057.compendium.core.items.projectiles.GrenadeItem;
import lance5057.compendium.core.items.tools.HammerItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CompendiumItems {

    public static final ItemGroup GROUP_MATERIALS = new ItemGroup("compendium.materials") {
	@Override
	public ItemStack createIcon() {
	    return new ItemStack(Items.SHIELD);
	}
    };
    public static final ItemGroup GROUP_ITEMS = new ItemGroup("compendium.items") {
	@Override
	public ItemStack createIcon() {
	    return new ItemStack(BARK.get());
	}
    };
    public static final ItemGroup GROUP_WORKSTATIONS = new ItemGroup("compendium.workstations") {
	@Override
	public ItemStack createIcon() {
	    return new ItemStack(CRAFTING_ANVIL_ITEMBLOCK.get());
	}
    };

    public static Item book = new Item(new Item.Properties().group(GROUP_MATERIALS));

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    public static final RegistryObject<BlockNamedItem> HAMMERING_STATION_ITEMBLOCK = ITEMS
	    .register("hammering_station_itemblock", () -> new BlockNamedItem(CompendiumBlocks.HAMMERING_STATION.get(),
		    new Item.Properties().group(CompendiumItems.GROUP_WORKSTATIONS)));
    public static final RegistryObject<BlockNamedItem> SAWHORSE_STATION_ITEMBLOCK = ITEMS
	    .register("sawhorse_station_itemblock", () -> new BlockNamedItem(CompendiumBlocks.SAWHORSE_STATION.get(),
		    new Item.Properties().group(CompendiumItems.GROUP_WORKSTATIONS)));
    public static final RegistryObject<BlockNamedItem> CRAFTING_ANVIL_ITEMBLOCK = ITEMS
	    .register("crafting_anvil_itemblock", () -> new BlockNamedItem(CompendiumBlocks.CRAFTING_ANVIL.get(),
		    new Item.Properties().group(CompendiumItems.GROUP_WORKSTATIONS)));
    public static final RegistryObject<BlockNamedItem> SCRAPPING_TABLE_ITEMBLOCK = ITEMS
	    .register("scrapping_table_itemblock", () -> new BlockNamedItem(CompendiumBlocks.SCRAPPING_TABLE.get(),
		    new Item.Properties().group(CompendiumItems.GROUP_WORKSTATIONS)));

    public static final RegistryObject<MegalithStoneItem> MEGALITH_STONE = ITEMS.register("megalith_stone",
	    () -> new MegalithStoneItem(new Item.Properties().group(CompendiumItems.GROUP_MATERIALS)));

    public static final RegistryObject<Item> SAWDUST = ITEMS.register("sawdust",
	    () -> new BurnableItem(new Item.Properties().group(CompendiumItems.GROUP_ITEMS), 100));
    public static final RegistryObject<Item> BARK = ITEMS.register("bark",
	    () -> new BurnableItem(new Item.Properties().group(CompendiumItems.GROUP_ITEMS), 100));

    public static final RegistryObject<Item> CRUDE_HAMMER = ITEMS.register("crude_hammer",
	    () -> new HammerItem(ItemTier.STONE, 0, 0, new Item.Properties().group(CompendiumItems.GROUP_ITEMS)));

    public static final RegistryObject<GrenadeItem> MINER_GRENADE = ITEMS.register("grenade",
	    () -> new GrenadeItem(new Item.Properties().group(CompendiumItems.GROUP_ITEMS)));

    public static final RegistryObject<BlockNamedItem> DRYLAKEBED_ITEMBLOCK = ITEMS.register("drylakebed_itemblock",
	    () -> new BlockNamedItem(CompendiumBlocks.DRYLAKEBED.get(),
		    new Item.Properties().group(CompendiumItems.GROUP_WORKSTATIONS)));

//    public static RegistryObject<BlockItem> ITEM_SHINGLES_BLOCK = ITEMS.register("item_empty_shinglesblock",
//	    () -> new BlockItem(CompendiumBlocks.SHINGLES_BLOCK.get(),
//		    new Item.Properties().group(CompendiumItems.GROUP_ITEMS)));
//    public static RegistryObject<BlockItem> ITEM_SHINGLES = ITEMS.register("item_empty_shingles",
//	    () -> new BlockItem(CompendiumBlocks.SHINGLES.get(),
//		    new Item.Properties().group(CompendiumItems.GROUP_ITEMS)));
//    public static RegistryObject<BlockItem> ITEM_SHINGLES_ALT = ITEMS.register("item_empty_shinglesalt",
//	    () -> new BlockItem(CompendiumBlocks.SHINGLES_ALT.get(),
//		    new Item.Properties().group(CompendiumItems.GROUP_ITEMS)));

    public static void register(IEventBus modBus) {
	ITEMS.register(modBus);
    }
}
