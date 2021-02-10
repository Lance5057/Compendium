package lance5057.compendium;

import lance5057.compendium.core.blocks.BlockShingles;
import lance5057.compendium.core.blocks.VaultBlock;
import lance5057.compendium.core.workstations.blocks.CraftingAnvilBlock;
import lance5057.compendium.core.workstations.blocks.HammeringStationBlock;
import lance5057.compendium.core.workstations.blocks.SawhorseStationBlock;
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

    public static RegistryObject<Block> SHINGLES_BLOCK = BLOCKS.register("empty_shinglesblock", () -> new Block(
	    Block.Properties.create(Material.IRON).hardnessAndResistance(5F, 10F).sound(SoundType.METAL)));

    public static RegistryObject<BlockShingles> SHINGLES = BLOCKS.register("empty_shingles", () -> new BlockShingles(
	    () -> SHINGLES_BLOCK.get().getDefaultState(),
	    Block.Properties.create(Material.WOOD).hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));

    public static RegistryObject<BlockShingles> SHINGLES_ALT = BLOCKS.register("empty_shinglesalt",
	    () -> new BlockShingles(() -> SHINGLES_BLOCK.get().getDefaultState(), Block.Properties.create(Material.WOOD)
		    .hardnessAndResistance(5F, 10F).sound(SoundType.METAL).notSolid()));

    public static void register(IEventBus modBus) {

	BLOCKS.register(modBus);
    }
}
