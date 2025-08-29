package com.lance5057.compendium;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.lance5057.compendium.blocks.RecipeToolSupplier.drawer.ComponentDrawerBlockEntity;
import com.lance5057.compendium.blocks.RecipeToolSupplier.toolrack.ToolRackBlockEntity;
import com.lance5057.compendium.blocks.entities.SimpleStyleBlockEntity;
import com.lance5057.compendium.blocks.entity.StyledMultiMaterialBlockEntity;
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
//
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

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StyledMultiMaterialBlockEntity>> WINDOW = BLOCK_ENTITIES
			.register("window", () -> BlockEntityType.Builder.of((p, s) -> {
				return new StyledMultiMaterialBlockEntity(p, s, 2, 2, 
						List.of("basic"), 
						List.of("basic"));
			}, CompendiumBlocks.WINDOW.get())
					.build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StyledMultiMaterialBlockEntity>> CHAIR = BLOCK_ENTITIES
			.register("chair",
					() -> BlockEntityType.Builder.of((p, s) -> {
						return new StyledMultiMaterialBlockEntity(p, s, 3, 3, 
						List.of("basic", "basic_panel", "branch", "braced", "checker",
								"contemporary", "contemporary_slats", "criss-cross", "cross", "cross_framed", "fan", "fancy", "flat",
								"flat_extra", "full", "laced", "laced_tall", "ladder", "ladder_tall", "lath", "lath_extra", "lattice",
								"live_edge", "lozenge", "open", "panel", "panel_weave", "planks_horizontal", "planks_horizontal_angled",
								"slats", "slats_chunky", "solid", "splat", "splat_double", "splat_slat", "turned_panel",
								"turned_panel_weave", "vienna", "weave", "windsor"),
						List.of("basic", "planks_horizontal", "planks_horizontal_angled", "tilted",
								"tilted_weave", "weave", "framed"),
						List.of("basic", "angled", "crosstie", "pedestal", "pedestal_cross",
								"pedestal_cross_tilted", "rails_connected", "rails_connected_fine", "rails_connected_double_fine",
								"rails_connected_side", "rails_connected_side_fine", "rails_double_fine", "rails_double_triple_fine",
								"rails_end", "rails_end_fine", "rails_full", "rails_full_fine", "rails_full_offset",
								"rails_full_offset_fine", "rails_side", "rails_side_fine", "rails_side_lath", "rails_side_lath_connected",
								"rails_single_double", "rails_single_double_fine", "solid", "solid_ends", "solid_sides", "fancy", "bar"));
					}, CompendiumBlocks.CHAIR.get()).build(null));
	
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StyledMultiMaterialBlockEntity>> TABLE = BLOCK_ENTITIES
			.register("table",
					() -> BlockEntityType.Builder.of((p, s) -> {
						return new StyledMultiMaterialBlockEntity(p, s, 2, 2, 
						List.of("basic", "trimmed", "smooth"),
						List.of("basic", "bar", "fancy"));
					}, CompendiumBlocks.TABLE.get()).build(null));
	
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StyledMultiMaterialBlockEntity>> CLOTHED_TABLE = BLOCK_ENTITIES
			.register("clothed_table", () -> BlockEntityType.Builder
					.of((p, s) -> {
						return new StyledMultiMaterialBlockEntity(p, s, 3, 3, 
						List.of("basic", "trimmed", "smooth"),
						List.of("basic", "bar", "fancy"),
						List.of("basic", "long", "short", "angled", "angled_short", "angled_long"));
					}, CompendiumBlocks.CLOTHED_TABLE.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StyledMultiMaterialBlockEntity>> FANCY_BED = BLOCK_ENTITIES
			.register("fancy_bed", () -> BlockEntityType.Builder.of((p, s) -> {
				return new StyledMultiMaterialBlockEntity(p, s, 3, 3, 
						List.of("basic"), 
						List.of("basic"),
						List.of("basic"), 
						List.of("basic"), 
						List.of("basic"));
			}, CompendiumBlocks.FANCY_BED.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StyledMultiMaterialBlockEntity>> FANCY_FENCE = BLOCK_ENTITIES
			.register("fancy_fence", () -> BlockEntityType.Builder.of((p, s) -> {
				return new StyledMultiMaterialBlockEntity(p, s, 3, 3,
						List.of("basic", "none", "pillar", "pillar_bottom", "pillar_double_cap", "pillar_flat_cap", "pillar_top", "thick", "thin"),
						List.of("basic", "3_spoke", "4_spoke", "diamond", "picket", "privacy", "short", "slats", "slats_concave", "slats_convex", "solid"));
			}, CompendiumBlocks.FANCY_FENCE.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StyledMultiMaterialBlockEntity>> SLANTED_SHINGLES = BLOCK_ENTITIES
			.register("shingles_slanted", () -> BlockEntityType.Builder.of((p, s) -> {
				return new StyledMultiMaterialBlockEntity(p, s, 3, 3, 
						List.of("basic"),
						List.of("basic"/* , "braced", "none" */), 
						List.of("basic"));
			}, CompendiumBlocks.SHINGLES_SLANTED.get(), CompendiumBlocks.SHINGLES_CAP_SLANTED.get()).build(null));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StyledMultiMaterialBlockEntity>> FANCY_DOOR = BLOCK_ENTITIES
			.register("fancy_door", () -> BlockEntityType.Builder.of((p, s) -> {
				return new StyledMultiMaterialBlockEntity(p, s, 2, 2, List.of("basic"), List.of("basic"));
			}, CompendiumBlocks.FANCY_DOOR.get()).build(null));
}
