package lance5057.compendium;

import lance5057.compendium.core.workstations.blocks.CraftingAnvilBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CompendiumBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			Reference.MOD_ID);

	public static final RegistryObject<Block> TEST = BLOCKS.register("test",
			() -> new Block(Block.Properties.of(Material.METAL).strength(3, 4)));;

	public static final RegistryObject<BlockItem> STORAGE_ITEMBLOCK = CompendiumItems.ITEMS.register("test_itemblock",
			() -> new BlockItem(TEST.get(), new Item.Properties().tab(CompendiumItems.GROUP_MATERIALS)));
	
//    public static final RegistryObject<HammeringStationBlock> HAMMERING_STATION = BLOCKS.register("hammering_station",
//	    () -> new HammeringStationBlock());
	public static final RegistryObject<CraftingAnvilBlock> CRAFTING_ANVIL = BLOCKS.register("crafting_anvil",
			() -> new CraftingAnvilBlock());
//    public static final RegistryObject<SawhorseStationBlock> SAWHORSE_STATION = BLOCKS.register("sawhorse_station",
//	    () -> new SawhorseStationBlock());
//    public static final RegistryObject<ScrappingTableBlock> SCRAPPING_TABLE = BLOCKS.register("scrapping_table",
//	    () -> new ScrappingTableBlock());

//    public static final RegistryObject<Block> DRYLAKEBED = BLOCKS.register("dry_lake_bed",
//	    () -> new Block(Block.Properties.of(Material.DIRT).strength(0.5F).sound(SoundType.GRAVEL)));

	public static void register(IEventBus modBus) {

		BLOCKS.register(modBus);
	}
}
