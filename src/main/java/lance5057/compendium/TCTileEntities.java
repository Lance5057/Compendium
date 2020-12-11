package lance5057.compendium;

import lance5057.compendium.core.workstations.tileentities.CraftingAnvilTE;
import lance5057.compendium.core.workstations.tileentities.HammeringStationTE;
import lance5057.compendium.core.workstations.tileentities.SawhorseStationTE;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TCTileEntities {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Reference.MOD_ID);

    public static final RegistryObject<TileEntityType<HammeringStationTE>> HAMMERING_STATION_TE = TILE_ENTITIES.register("hammering_station_tile", () -> TileEntityType.Builder.create(HammeringStationTE::new, TCBlocks.HAMMERING_STATION.get()).build(null));
    public static final RegistryObject<TileEntityType<CraftingAnvilTE>> CRAFTING_ANVIL_TE = TILE_ENTITIES.register("crafting_anvil_tile", () -> TileEntityType.Builder.create(CraftingAnvilTE::new, TCBlocks.CRAFTING_ANVIL.get()).build(null));
    public static final RegistryObject<TileEntityType<SawhorseStationTE>> SAWHORSE_STATION_TE = TILE_ENTITIES.register("sawhorse_station_tile", () -> TileEntityType.Builder.create(SawhorseStationTE::new, TCBlocks.SAWHORSE_STATION.get()).build(null));

    public static void register(IEventBus modBus) {
        TILE_ENTITIES.register(modBus);
    }
}
