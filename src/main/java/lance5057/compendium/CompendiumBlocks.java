package lance5057.compendium;

import lance5057.compendium.core.workstations.blocks.CraftingAnvilBlock;
import lance5057.compendium.core.workstations.blocks.SawBuckBlock;
import lance5057.compendium.core.workstations.blocks.WorkstationBlock;
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
	public static final RegistryObject<WorkstationBlock> WORKSTATION = BLOCKS.register("workstation",
			() -> new WorkstationBlock());

	public static final RegistryObject<CraftingAnvilBlock> CRAFTING_ANVIL = BLOCKS.register("crafting_anvil",
			() -> new CraftingAnvilBlock());

	public static final RegistryObject<SawBuckBlock> SAW_BUCK = BLOCKS.register("saw_buck",
			() -> new SawBuckBlock());

	public static void register(IEventBus modBus) {

		BLOCKS.register(modBus);
	}
}
