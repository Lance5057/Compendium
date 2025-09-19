package com.lance5057.compendium;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.lance5057.compendium.blocks.RecipeToolSupplier.drawer.ComponentDrawerBlockEntity;
import com.lance5057.compendium.blocks.RecipeToolSupplier.toolrack.ToolRackBlockEntity;
import com.lance5057.compendium.blocks.entities.SimpleStyleBlockEntity;
import com.lance5057.compendium.blocks.entities.StyledMultiMaterialBlockEntity;
import com.lance5057.compendium.workstations.hammeringstation.HammeringStationBlockEntity;
import com.lance5057.compendium.workstations.sawbuck.SawBuckBlockEntity;
import com.lance5057.compendium.workstations.scrappingtable.ScrappingTableBlockEntity;
import com.lance5057.compendium.workstations.workbench.WorkbenchBlockEntity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompendiumBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
			.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Compendium.MOD_ID);

	public static List<DeferredBlock<?>> validStyleBlocks = new ArrayList<DeferredBlock<?>>();

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SimpleStyleBlockEntity>> STYLE = BLOCK_ENTITIES
			.register("style", () -> BlockEntityType.Builder.of(SimpleStyleBlockEntity::new,

					validStyleBlocks.stream().map(i -> i.get()).collect(Collectors.toList()).toArray(new Block[0]))
					.build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HammeringStationBlockEntity>> HAMMERING_STATION = BLOCK_ENTITIES
			.register("hammering_station", () -> BlockEntityType.Builder
					.of(HammeringStationBlockEntity::new, CompendiumBlocks.HAMMERING_STATION.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SawBuckBlockEntity>> SAW_BUCK = BLOCK_ENTITIES
			.register("saw_buck", () -> BlockEntityType.Builder
					.of(SawBuckBlockEntity::new, CompendiumBlocks.SAW_BUCK.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ScrappingTableBlockEntity>> SCRAPPING_TABLE = BLOCK_ENTITIES
			.register("scrapping_table", () -> BlockEntityType.Builder
					.of(ScrappingTableBlockEntity::new, CompendiumBlocks.SCRAPPING_TABLE.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WorkbenchBlockEntity>> WORKBENCH = BLOCK_ENTITIES
			.register("workbench", () -> BlockEntityType.Builder
					.of(WorkbenchBlockEntity::new, CompendiumBlocks.WORKBENCH.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ToolRackBlockEntity>> TOOLRACK = BLOCK_ENTITIES
			.register("toolrack", () -> BlockEntityType.Builder
					.of(ToolRackBlockEntity::new, CompendiumBlocks.TOOLRACK.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ComponentDrawerBlockEntity>> COMPONENT_DRAWER = BLOCK_ENTITIES
			.register("component_drawer", () -> BlockEntityType.Builder
					.of(ComponentDrawerBlockEntity::new, CompendiumBlocks.COMPONENT_DRAWER.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StyledMultiMaterialBlockEntity>> STYLED_MULTI_MATERIAL = BLOCK_ENTITIES
			.register("window", () -> BlockEntityType.Builder.of((p, s) -> {
				return new StyledMultiMaterialBlockEntity(p, s, 0, 0);
			}, CompendiumBlocks.WINDOW.get(), CompendiumBlocks.CHAIR.get(), CompendiumBlocks.TABLE.get(),
					CompendiumBlocks.CLOTHED_TABLE.get(), CompendiumBlocks.FANCY_BED.get(),
					CompendiumBlocks.FANCY_FENCE.get(), CompendiumBlocks.SHINGLES_SLANTED.get(),
					CompendiumBlocks.SHINGLES_CAP_SLANTED.get(), CompendiumBlocks.FANCY_DOOR.get()).build(null));

}
