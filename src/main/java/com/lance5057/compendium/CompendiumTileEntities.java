package com.lance5057.compendium;

import com.lance5057.compendium.workstations.workstation.WorkbenchBlockEntity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompendiumTileEntities {
	public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister
			.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Compendium.MOD_ID);

//	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<OvenBlockEntity>> OVEN = TILES.register(
//			"oven", () -> BlockEntityType.Builder.of(OvenBlockEntity::new, ExtraDelightBlocks.OVEN.get()).build(null));
	
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WorkbenchBlockEntity>> WORKSTATION_TE = TILES.register(
			"workbench", () -> BlockEntityType.Builder.of(WorkbenchBlockEntity::new, CompendiumBlocks.WORKBENCH.get()).build(null));

}
