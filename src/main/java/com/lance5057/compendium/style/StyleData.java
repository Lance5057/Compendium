package com.lance5057.compendium.style;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class StyleData {
	// Defaults
	public static StyleData TILES = new StyleData("tile",
			List.of("full_tile", "half_tile", "offset_half_tile", "vertical_half_tile", "quarter", "indented",
					"indented_segmented", "dented", "dented_segmented", "tilted_small_tile", "diamond_tile",
					"eighth_tiles", "offset_eighth_tiles", "brick", "brick_vertical", "aligned_brick",
					"aligned_brick_vertical", "basketweave_bricks", "big_brick", "half_brick", "herringbone_bricks",
					"hex_brick", "slats", "slats_vertical"));

	public static StyleData TABLE_TOP = new StyleData("table_top", List.of("basic", "trimmed", "smooth"));
	public static StyleData TABLE_LEGS = new StyleData("table_legs", List.of("basic", "bar", "fancy"));
	public static StyleData TABLE_CLOTH = new StyleData("table_cloth",
			List.of("basic", "long", "short", "angled", "angled_short", "angled_long"));

	public static StyleData CHAIR_BACK = new StyleData("chair_back",
			List.of("basic", "basic_sheet", "basic_panel", "branch", "braced", /* "checker", */ "contemporary",
					"contemporary_sheet", "contemporary_slats", "contemporary_slats_sheet", "criss-cross", "cross",
					"cross_framed", "fan", "fancy", "flat", "flat_sheet", "flat_extra", "flat_extra_sheet", "full",
					"full_sheet", "laced", "laced_tall", "ladder", "ladder_tall", "lath", "lath_extra", "lattice",
					"live_edge", "lozenge", "open", "panel", "panel_sheet", "panel_weave", "planks_horizontal",
					"planks_horizontal_angled", "slats", "slats_chunky", "solid", "solid_sheet", "splat",
					"splat_double", "splat_slat", "turned_panel", "turned_panel_sheet", "turned_panel_weave", "vienna",
					"weave", "windsor"));
	public static StyleData CHAIR_SEAT = new StyleData("chair_seat", List.of("basic", "basic_sheet",
			"planks_horizontal", "planks_horizontal_angled", "weave", "framed", "framed_sheet", "live_edge"));
	public static StyleData CHAIR_LEGS = new StyleData("chair_legs",
			List.of("basic", "angled", "crosstie", "pedestal", "pedestal_cross", "pedestal_cross_tilted", "rails_end",
					"rails_end_fine", "rails_connected", "rails_connected_fine", "rails_connected_double_fine",
					"rails_side", "rails_side_fine", "rails_connected_side", "rails_connected_side_fine",
					"rails_double_fine", "rails_double_triple_fine", "rails_full", "rails_full_fine",
					"rails_full_offset", "rails_full_offset_fine", "rails_side_lath", "rails_side_lath_connected",
					"rails_single_double", "rails_single_double_fine", "solid", "solid_ends", "solid_sides", "fancy",
					"fancy_half", "bar"));

	public static StyleData FENCE_POST = new StyleData("fence_post", List.of("basic", "none", "pillar", "pillar_bottom",
			"pillar_top", "pillar_flat_cap", "pillar_double_cap", "thick", "thin"));
	public static StyleData FENCE_SIDE = new StyleData("fence_side", List.of("basic", "3_spoke", "4_spoke", "diamond",
			"picket", "privacy", "short", "slats", "slats_concave", "slats_convex", "solid", "solid_sheet"));

	public static StyleData BED_BLANKET = new StyleData("bed_blanket", List.of("basic", "llama", "glazed"));
	public static StyleData BED_PILLOW = new StyleData("bed_pillow", List.of("basic", "big", "angled", "frilled"));
	public static StyleData BED_SHEET = new StyleData("bed_sheet", List.of("basic", "long", "frilled"));
	public static StyleData BED_MATTRESS = new StyleData("bed_mattress", List.of("basic"));
	public static StyleData BED_FRAME = new StyleData("bed_frame",
			List.of("basic", "contemporary_slats", "cross", "fancy", "flat", "flat_extra", "full",
							 "lath", "lath_extra", "live_edge","ornate", "ornate_poster","panelled", "slats", "solid", "weave"));
	public static StyleData BED_BASE = new StyleData("bed_base",
			List.of("basic", "panelled", "slats", "solid", "weave"));

	public static StyleData SHINGLES_SHINGLES = new StyleData("shingles", List.of("basic", "angled", "small_stairs", "taper_left", "taper_right"));
	public static StyleData SUPPORT_SHINGLES = new StyleData("support", List.of("basic" , "braced", "none" ));
	public static StyleData GABLE_SHINGLES = new StyleData("gable", List.of("basic"));

	public static StyleData LOG = new StyleData("log",
			List.of("basic", "corner", "small_wood", "bark_shred_1", "bark_shred_2", "bark_shred_3", "bark_shred_4"));
	public static StyleData LOG_SLAB = new StyleData("log_slab",
			List.of("small_logs", "small_logs_rotated", "split", "split_rotated", "crosscut", "crosscut_small",
					"small_wood", "small_wood_rotated", "wood", "wood_rotated", "campfire", "firewood", "smaller_logs",
					"smaller_logs_rotated", "smallest_logs", "smallest_logs_rotated", "trellis"));
	public static StyleData SMALL_LOG = new StyleData("small_log", List.of("small_log", "smaller_log", "smallest_log"));
	public static StyleData LOG_STAIRS = new StyleData("log_stairs",
			List.of("small_logs", "small_logs_rotated_side", "small_logs_rotated_front", "small_logs_rotated_top",
					"split_log_rotated_side", "split_log_rotated_front", "split_log_rotated_top", "small_wood",
					"small_wood_rotated", "wood", "wood_rotated"));

	public static StyleData PLANK = new StyleData("plank", List.of("basic", "pipe"));

	public static StyleData PLANKS = new StyleData("plank_block",
			List.of("boards", "boards_rotated", "boards_seamless", "boards_seamless_rotated", "boards_stacked",
					"boards_stacked_rotated", "big_weave", "alternate_weave", "close_weave", "double_weave", "blocks",
					"blocks_offset", "box", "chiseled", "cross", "diagonal", "diagonal_rotated", "herringbone",
					"double_herringbone", "panel", "parquet", "planks_stacked", "planks_stacked_rotated",
					"planks_seamless", "planks_seamless_rotated", "sheet", "small_blocks", "small_blocks_offset",
					"thin", "thin_rotated", "vertical", "wainscotting_single", "wainscotting_double",
					"wainscotting_grate", "wainscotting_seamless", "walkway", "walkway_rotated", "wicker", "two_panel",
					"four_panels", "nine_panels", "banded", "domed", "five", "paper", "petal"));

	public static StyleData WINDOW_TRIM = new StyleData("window_trim",
			List.of("quarter", "bars", "bars_trimmed", "bars_horizontal", "bars_horizontal_trimmed", "diamond",
					"diamond_trimmed", "double", "full", "lattice", "lattice_trimmed", "round", "weave", "diagonal",
					"diagonal_trimmed", "caged", "framed", "trimmed", "caged_grate", "framed_grate", "trimmed_grate",
					"grate", "grate_edgeless", "braced", "fancy_frame", "caged_lattice", "grill", "porthole", "warped",
					"woven"));
	public static StyleData WINDOW_GLASS = new StyleData("window_glass", List.of("basic"));

	String name;
	List<String> types;

	public static final Codec<StyleData> CODEC = RecordCodecBuilder.create(p_337946_ -> p_337946_
			.group(Codec.STRING.fieldOf("name").forGetter(StyleData::getName),
					Codec.list(Codec.STRING).fieldOf("types").forGetter(StyleData::getTypes))
			.apply(p_337946_, StyleData::new));

	public StyleData(String name, List<String> types) {
		super();
		this.name = name;
		this.types = types;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTypes() {
		return types;
	}

	public String getTypeSafe(int type) {
		if (types.size() > type) {
			return types.get(type);
		}
		return "";
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

}
