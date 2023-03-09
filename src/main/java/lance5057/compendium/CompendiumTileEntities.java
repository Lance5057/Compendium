package lance5057.compendium;

import lance5057.compendium.core.workstations.craftinganvil.CraftingAnvilTE;
import lance5057.compendium.core.workstations.hammeringstation.HammeringStationTE;
import lance5057.compendium.core.workstations.sawbuck.SawBuckTE;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CompendiumTileEntities {
	public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister
			.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Reference.MOD_ID);

	public static final RegistryObject<BlockEntityType<CraftingAnvilTE>> WORKSTATION_TE = TILE_ENTITIES.register(
			"workstation_tile",
			() -> BlockEntityType.Builder.of(CraftingAnvilTE::new, CompendiumBlocks.WORKSTATION.get()).build(null));

	public static final RegistryObject<BlockEntityType<CraftingAnvilTE>> CRAFTING_ANVIL_TE = TILE_ENTITIES.register(
			"crafting_anvil_tile",
			() -> BlockEntityType.Builder.of(CraftingAnvilTE::new, CompendiumBlocks.CRAFTING_ANVIL.get()).build(null));

	public static final RegistryObject<BlockEntityType<SawBuckTE>> SAW_BUCK_TE = TILE_ENTITIES.register("saw_buck_tile",
			() -> BlockEntityType.Builder.of(SawBuckTE::new, CompendiumBlocks.SAW_BUCK.get()).build(null));
	public static final RegistryObject<BlockEntityType<HammeringStationTE>> HAMMERING_STATION_TE = TILE_ENTITIES
			.register("hammering_station_tile", () -> BlockEntityType.Builder
					.of(HammeringStationTE::new, CompendiumBlocks.HAMMERING_STATION.get()).build(null));
//	public static final RegistryObject<BlockEntityType<HammeringStationTE>> HAMMERING_STATION_TE = TILE_ENTITIES
//			.register("hammering_station_tile", () -> BlockEntityType.Builder
//					.of(HammeringStationTE::new, CompendiumBlocks.HAMMERING_STATION.get()).build(null));
//	public static final RegistryObject<BlockEntityType<SawhorseStationTE>> SAWHORSE_STATION_TE = TILE_ENTITIES
//			.register("sawhorse_station_tile", () -> BlockEntityType.Builder
//					.of(SawhorseStationTE::new, CompendiumBlocks.SAWHORSE_STATION.get()).build(null));
//	public static final RegistryObject<BlockEntityType<ScrappingTableTE>> SCRAPPING_TABLE_TE = TILE_ENTITIES
//			.register("scrapping_table_tile", () -> BlockEntityType.Builder
//					.of(ScrappingTableTE::new, CompendiumBlocks.SCRAPPING_TABLE.get()).build(null));

//    public static final RegistryObject<BlockEntityType<VaultTileEntity>> VAULT_TE = TILE_ENTITIES.register("vault_tile",
//	    () -> BlockEntityType.Builder.create(VaultTileEntity::new, getAllVaults()).build(null));

//	public static List<RegistryObject<ItemDisplayBlock>> displayBlocks = new ArrayList<RegistryObject<ItemDisplayBlock>>();
//	public static List<RegistryObject<BlockHand>> displayHandBlocks = new ArrayList<RegistryObject<BlockHand>>();
//	
//	public static final RegistryObject<BlockEntityType<ItemDisplayTileEntity>> ITEM_DISPLAY_TE = TILE_ENTITIES.register(
//			"item_display_tile",
//			() -> BlockEntityType.Builder.of(ItemDisplayTileEntity::new, getAllItemDisplays()).build(null));
//	
//	public static final RegistryObject<BlockEntityType<HandTileEntity>> HAND_DISPLAY_TE = TILE_ENTITIES.register(
//			"hand_display_tile",
//			() -> BlockEntityType.Builder.of(HandTileEntity::new, getAllHandDisplays()).build(null));

	public static void register(IEventBus modBus) {
		TILE_ENTITIES.register(modBus);
	}

//    static Block[] getAllVaults()
//    {
//	List<Block> blocks = new ArrayList<Block>();
//	for(MaterialHelper mh: CompendiumMaterials.materials)
//	{
//	    if(mh.getAdvancedComponents() != null)
//		blocks.add(mh.getAdvancedComponents().VAULT.get());
//	}
//	Block[] b = {};
//	return  blocks.toArray(b);
//    }
//    
//	static Block[] getAllItemDisplays() {
//		List<Block> blocksOut = new ArrayList<Block>();
//		
//		for(RegistryObject<ItemDisplayBlock> block : displayBlocks)
//		{
//			blocksOut.add(block.get());
//		}
//		
//		Block[] b = {};
//		return blocksOut.toArray(b);
//	}
//	
//	static Block[] getAllHandDisplays() {
//		List<Block> blocksOut = new ArrayList<Block>();
//		
//		for(RegistryObject<BlockHand> block : displayHandBlocks)
//		{
//			blocksOut.add(block.get());
//		}
//		
//		Block[] b = {};
//		return blocksOut.toArray(b);
//	}
}
