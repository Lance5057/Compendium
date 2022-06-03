package lance5057.compendium;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CompendiumItems {

	public static final CreativeModeTab GROUP_MATERIALS = new CreativeModeTab("compendium.materials") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(Items.SHIELD);
		}
	};
//	public static final CreativeModeTab GROUP_ITEMS = new CreativeModeTab("compendium.items") {
//		@Override
//		public ItemStack makeIcon() {
//			return new ItemStack(BARK.get());
//		}
//	};
//	public static final CreativeModeTab GROUP_WORKSTATIONS = new CreativeModeTab("compendium.workstations") {
//		@Override
//		public ItemStack makeIcon() {
//			return new ItemStack(CRAFTING_ANVIL_ITEMBLOCK.get());
//		}
//	};

//	public static Item book = new Item(new Item.Properties().tab(GROUP_MATERIALS));

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

//	public static final RegistryObject<BlockItem> HAMMERING_STATION_ITEMBLOCK = ITEMS
//			.register("hammering_station_itemblock", () -> new BlockItem(CompendiumBlocks.HAMMERING_STATION.get(),
//					new Item.Properties().tab(CompendiumItems.GROUP_WORKSTATIONS)));
//	public static final RegistryObject<BlockItem> SAWHORSE_STATION_ITEMBLOCK = ITEMS
//			.register("sawhorse_station_itemblock", () -> new BlockItem(CompendiumBlocks.SAWHORSE_STATION.get(),
//					new Item.Properties().tab(CompendiumItems.GROUP_WORKSTATIONS)));
//	public static final RegistryObject<BlockItem> CRAFTING_ANVIL_ITEMBLOCK = ITEMS.register("crafting_anvil_itemblock",
//			() -> new BlockItem(CompendiumBlocks.CRAFTING_ANVIL.get(),
//					new Item.Properties().tab(CompendiumItems.GROUP_WORKSTATIONS)));
//	public static final RegistryObject<BlockItem> SCRAPPING_TABLE_ITEMBLOCK = ITEMS
//			.register("scrapping_table_itemblock", () -> new BlockItem(CompendiumBlocks.SCRAPPING_TABLE.get(),
//					new Item.Properties().tab(CompendiumItems.GROUP_WORKSTATIONS)));

//	public static final RegistryObject<MegalithStoneItem> MEGALITH_STONE = ITEMS.register("megalith_stone",
//			() -> new MegalithStoneItem(new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
//
//	public static final RegistryObject<Item> SAWDUST = ITEMS.register("sawdust",
//			() -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_ITEMS)) {
//				@Override
//				public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
//					return 100;
//				}
//			});
//	public static final RegistryObject<Item> BARK = ITEMS.register("bark",
//			() -> new Item(new Item.Properties().tab(CompendiumItems.GROUP_ITEMS)) {
//				@Override
//				public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
//					return 100;
//				}
//			});

//	public static final RegistryObject<Item> CRUDE_HAMMER = ITEMS.register("crude_hammer",
//			() -> new HammerItem(Tiers.STONE, 0, 0, new Item.Properties().tab(CompendiumItems.GROUP_ITEMS)));
//
//	public static final RegistryObject<GrenadeItem> MINER_GRENADE = ITEMS.register("grenade",
//			() -> new GrenadeItem(new Item.Properties().tab(CompendiumItems.GROUP_ITEMS)));
//
//	public static final RegistryObject<BlockItem> DRYLAKEBED_ITEMBLOCK = ITEMS.register("drylakebed_itemblock",
//			() -> new BlockItem(CompendiumBlocks.DRYLAKEBED.get(),
//					new Item.Properties().tab(CompendiumItems.GROUP_WORKSTATIONS)));

//    public static RegistryObject<BlockItem> ITEM_SHINGLES_BLOCK = ITEMS.register("item_empty_shinglesblock",
//	    () -> new BlockItem(CompendiumBlocks.SHINGLES_BLOCK.get(),
//		    new Item.Properties().tab(CompendiumItems.GROUP_ITEMS)));
//    public static RegistryObject<BlockItem> ITEM_SHINGLES = ITEMS.register("item_empty_shingles",
//	    () -> new BlockItem(CompendiumBlocks.SHINGLES.get(),
//		    new Item.Properties().tab(CompendiumItems.GROUP_ITEMS)));
//    public static RegistryObject<BlockItem> ITEM_SHINGLES_ALT = ITEMS.register("item_empty_shinglesalt",
//	    () -> new BlockItem(CompendiumBlocks.SHINGLES_ALT.get(),
//		    new Item.Properties().tab(CompendiumItems.GROUP_ITEMS)));

	public static void register(IEventBus modBus) {
		ITEMS.register(modBus);
	}
}
