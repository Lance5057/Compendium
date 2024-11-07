package com.lance5057.compendium;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompendiumTileEntities {
	public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister
			.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Compendium.MOD_ID);

//	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WorkbenchBlockEntity>> WORKSTATION = TILES
//			.register("workbench", () -> BlockEntityType.Builder
//					.of(WorkbenchBlockEntity::new, CompendiumBlocks.WORKBENCH.get()).build(null));
//
//	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HammeringStationBlockEntity>> HAMMERING_STATION = TILES
//			.register("hammering_station", () -> BlockEntityType.Builder
//					.of(HammeringStationBlockEntity::new, CompendiumBlocks.HAMMERING_STATION.get()).build(null));

}
