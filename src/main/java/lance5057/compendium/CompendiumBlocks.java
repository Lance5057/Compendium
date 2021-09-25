package lance5057.compendium;

import lance5057.compendium.core.workstations.blocks.CraftingAnvilBlock;
import lance5057.compendium.core.workstations.blocks.HammeringStationBlock;
import lance5057.compendium.core.workstations.blocks.SawhorseStationBlock;
import lance5057.compendium.core.workstations.blocks.ScrappingTableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CompendiumBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
	    Reference.MOD_ID);

    public static final RegistryObject<HammeringStationBlock> HAMMERING_STATION = BLOCKS.register("hammering_station",
	    () -> new HammeringStationBlock());
    public static final RegistryObject<CraftingAnvilBlock> CRAFTING_ANVIL = BLOCKS.register("crafting_anvil",
	    () -> new CraftingAnvilBlock());
    public static final RegistryObject<SawhorseStationBlock> SAWHORSE_STATION = BLOCKS.register("sawhorse_station",
	    () -> new SawhorseStationBlock());
    public static final RegistryObject<ScrappingTableBlock> SCRAPPING_TABLE = BLOCKS.register("scrapping_table",
	    () -> new ScrappingTableBlock());

    public static final RegistryObject<Block> DRYLAKEBED = BLOCKS.register("dry_lake_bed",
	    () -> new Block(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.5F).sound(SoundType.GROUND)));

    public static void register(IEventBus modBus) {

	BLOCKS.register(modBus);
    }
}
