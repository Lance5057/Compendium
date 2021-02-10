package lance5057.compendium;

import java.util.ArrayList;
import java.util.List;

import lance5057.compendium.core.library.materialutilities.MaterialHelper;
import lance5057.compendium.core.materials.CompendiumMaterials;
import lance5057.compendium.core.tileentities.VaultTileEntity;
import lance5057.compendium.core.workstations.tileentities.CraftingAnvilTE;
import lance5057.compendium.core.workstations.tileentities.HammeringStationTE;
import lance5057.compendium.core.workstations.tileentities.SawhorseStationTE;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CompendiumTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister
	    .create(ForgeRegistries.TILE_ENTITIES, Reference.MOD_ID);

    public static final RegistryObject<TileEntityType<HammeringStationTE>> HAMMERING_STATION_TE = TILE_ENTITIES
	    .register("hammering_station_tile", () -> TileEntityType.Builder
		    .create(HammeringStationTE::new, CompendiumBlocks.HAMMERING_STATION.get()).build(null));
    public static final RegistryObject<TileEntityType<CraftingAnvilTE>> CRAFTING_ANVIL_TE = TILE_ENTITIES
	    .register("crafting_anvil_tile", () -> TileEntityType.Builder
		    .create(CraftingAnvilTE::new, CompendiumBlocks.CRAFTING_ANVIL.get()).build(null));
    public static final RegistryObject<TileEntityType<SawhorseStationTE>> SAWHORSE_STATION_TE = TILE_ENTITIES
	    .register("sawhorse_station_tile", () -> TileEntityType.Builder
		    .create(SawhorseStationTE::new, CompendiumBlocks.SAWHORSE_STATION.get()).build(null));

    public static final RegistryObject<TileEntityType<VaultTileEntity>> VAULT_TE = TILE_ENTITIES.register("vault_tile",
	    () -> TileEntityType.Builder.create(VaultTileEntity::new, getAllVaults()).build(null));

    public static void register(IEventBus modBus) {
	TILE_ENTITIES.register(modBus);
    }
    
    static Block[] getAllVaults()
    {
	List<Block> blocks = new ArrayList<Block>();
	for(MaterialHelper mh: CompendiumMaterials.materials)
	{
	    if(mh.getAdvancedComponents() != null)
		blocks.add(mh.getAdvancedComponents().VAULT.get());
	}
	Block[] b = {};
	return  blocks.toArray(b);
    }
}
