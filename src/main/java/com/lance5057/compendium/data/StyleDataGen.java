package com.lance5057.compendium.data;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.lance5057.compendium.CompendiumBlocks;
import com.lance5057.compendium.CompendiumStyles;
import com.lance5057.compendium.style.StyleData;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;

public class StyleDataGen extends DataMapProvider {

	// Defaults
	public static StyleData CLOTHED_TABLE_TOP = new StyleData("top", List.of("basic", "trimmed", "smooth"));
	public static StyleData CLOTHED_TABLE_LEGS = new StyleData("legs", List.of("basic", "bar", "fancy"));
	public static StyleData CLOTHED_TABLE_CLOTH = new StyleData("cloth",
			List.of("basic", "long", "short", "angled", "angled_short", "angled_long"));

	public static StyleData TABLE_TOP = new StyleData("top", List.of("basic", "trimmed", "smooth"));
	public static StyleData TABLE_LEGS = new StyleData("legs", List.of("basic", "bar", "fancy"));

	public static StyleData CHAIR_BACK = new StyleData("back",
			List.of("basic", "basic_panel", "branch", "braced", "checker", "contemporary", "contemporary_slats",
					"criss-cross", "cross", "cross_framed", "fan", "fancy", "flat", "flat_extra", "full", "laced",
					"laced_tall", "ladder", "ladder_tall", "lath", "lath_extra", "lattice", "live_edge", "lozenge",
					"open", "panel", "panel_weave", "planks_horizontal", "planks_horizontal_angled", "slats",
					"slats_chunky", "solid", "splat", "splat_double", "splat_slat", "turned_panel",
					"turned_panel_weave", "vienna", "weave", "windsor"));
	public static StyleData CHAIR_SEAT = new StyleData("back",
			List.of("basic", "planks_horizontal", "planks_horizontal_angled", "weave", "framed"));
	public static StyleData CHAIR_LEGS = new StyleData("back",
			List.of("basic", "angled", "crosstie", "pedestal", "pedestal_cross", "pedestal_cross_tilted",
					"rails_connected", "rails_connected_fine", "rails_connected_double_fine", "rails_connected_side",
					"rails_connected_side_fine", "rails_double_fine", "rails_double_triple_fine", "rails_end",
					"rails_end_fine", "rails_full", "rails_full_fine", "rails_full_offset", "rails_full_offset_fine",
					"rails_side", "rails_side_fine", "rails_side_lath", "rails_side_lath_connected",
					"rails_single_double", "rails_single_double_fine", "solid", "solid_ends", "solid_sides", "fancy",
					"bar"));

	public static StyleData FENCE_POST = new StyleData("post", List.of("basic", "none", "pillar", "pillar_bottom",
			"pillar_double_cap", "pillar_flat_cap", "pillar_top", "thick", "thin"));
	public static StyleData FENCE_SIDE = new StyleData("side", List.of("basic", "3_spoke", "4_spoke", "diamond",
			"picket", "privacy", "short", "slats", "slats_concave", "slats_convex", "solid"));

	public static StyleData BED_BLANKET = new StyleData("blanket", List.of("basic"));
	public static StyleData BED_PILLOW = new StyleData("pillow", List.of("basic"));
	public static StyleData BED_SHEET = new StyleData("sheet", List.of("basic"));
	public static StyleData BED_MATTRESS = new StyleData("mattress", List.of("basic"));
	public static StyleData BED_FRAME = new StyleData("frame", List.of("basic"));

	protected StyleDataGen(PackOutput packOutput, CompletableFuture<Provider> lookupProvider) {
		super(packOutput, lookupProvider);
	}

	@Override
	protected void gather() {

		this.builder(CompendiumStyles.STYLE_DATA).add(CompendiumBlocks.CLOTHED_TABLE,
				List.of(CLOTHED_TABLE_TOP, CLOTHED_TABLE_LEGS, CLOTHED_TABLE_CLOTH), false).build();
		this.builder(CompendiumStyles.STYLE_DATA).add(CompendiumBlocks.TABLE, List.of(TABLE_TOP, TABLE_LEGS), false)
				.build();
	}

}
