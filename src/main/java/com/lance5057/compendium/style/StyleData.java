package com.lance5057.compendium.style;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class StyleData {
	// Defaults
	public static StyleData TILES = new StyleData("base",
			List.of("FULL_TILE", "HALF_TILE", /* "OFFSET_HALF_TILE", */ "VERTICAL_HALF_TILE", /* "QUARTER", */
					"INDENTED", "INDENTED_SEGMENTED", "DENTED", "DENTED_SEGMENTED"/* , */ /* "TILTED_SMALL_TILE", */
//			/*"DIAMOND_TILE"*/, /*"EIGHTH_TILES"*/, /* "OFFSET_EIGHTH_TILES", */ /*"BRICK", "BRICK_VERTICAL", "ALIGNED_BRICK",*/
//			"ALIGNED_BRICK_VERTICAL", "BASKETWEAVE_BRICKS", "BIG_BRICK", /* "HALF_BRICK", */ "HERRINGBONE_BRICKS",
			/* "HEX_BRICK", "SLATS", "SLATS_VERTICAL" */));

	public static StyleData CLOTHED_TABLE_TOP = new StyleData("top", List.of("basic", "trimmed", "smooth"));
	public static StyleData CLOTHED_TABLE_LEGS = new StyleData("legs", List.of("basic", "bar", "fancy"));
	public static StyleData CLOTHED_TABLE_CLOTH = new StyleData("cloth",
			List.of("basic", "long", "short", "angled", "angled_short", "angled_long"));

	public static StyleData TABLE_TOP = new StyleData("top", List.of("basic", "trimmed", "smooth"));
	public static StyleData TABLE_LEGS = new StyleData("legs", List.of("basic", "bar", "fancy"));

	public static StyleData CHAIR_BACK = new StyleData("back", List.of("basic", "basic_panel", "branch", "braced",
			"checker", "contemporary", "contemporary_slats", "criss-cross", "cross", "cross_framed", "fan", "fancy",
			"flat", "flat_extra", "full", "laced", "laced_tall", "ladder", "ladder_tall", "lath", "lath_extra",
			"lattice", "live_edge", /* "lozenge", */
			"open", "panel", "panel_weave", "planks_horizontal", "planks_horizontal_angled", "slats", "slats_chunky",
			"solid", "splat", "splat_double", "splat_slat", "turned_panel", "turned_panel_weave", "vienna", "weave",
			"windsor"));
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
	public static StyleData BED_FRAME = new StyleData("frame",
			List.of("basic", "basic_panel", "contemporary_slats", "cross", "fancy", "flat", "flat_extra", "full",
					"lath", "lath_extra", "live_edge", "panelled", "slats", "solid", "weave"));
	public static StyleData BED_BASE = new StyleData("base", List.of("basic", "panelled", "slats", "solid", "weave"));

	public static StyleData SHINGLES = new StyleData("shingles", List.of("basic"));
	public static StyleData SUPPORT = new StyleData("support", List.of("basic"/* , "braced", "none" */));
	public static StyleData GABLE = new StyleData("gable", List.of("basic"));

	public static StyleData LOG = new StyleData("log", List.of("basic", "corner", "small_wood", "bark_shred_1",
			"bark_shred_2", "bark_shred_3", "bark_shred_4"));
	public static StyleData LOG_SLAB = new StyleData("log_slab",
			List.of("small_logs", "small_logs_rotated", "split", "split_rotated", "crosscut", "crosscut_small",
					"small_wood", "small_wood_rotated", "wood", "wood_rotated", "campfire", "firewood", "smaller_logs",
					"smaller_logs_rotated", "smallest_logs", "smallest_logs_rotated"));
	public static StyleData SMALL_LOG = new StyleData("log_slab", List.of("basic", "offset"));
	public static StyleData LOG_STAIRS = new StyleData("log_stairs",
			List.of("small_logs", "small_logs_rotated_side", "small_logs_rotated_front", "small_logs_rotated_top",
					"split_log_rotated_side", "split_log_rotated_front", "split_log_rotated_top", "small_wood",
					"small_wood_rotated", "wood", "wood_rotated"));

	public static StyleData PLANKS = new StyleData("planks",
			List.of("big_weave", "blocks", "blocks_offset", "boards", "boards_rotated", "boards_seamless",
					"boards_seamless_rotated", "boards_stacked", "boards_stacked_rotated", "box", "chiseled", "cross",
					"diagonal", "diagonal_rotated", "herringbone", "double_herringbone", "panel", "parquet",
					"planks_stacked", "planks_stacked_rotated", "planks_seamless", "planks_seamless_rotated", "sheet",
					"small_blocks", "small_blocks_offset", "thin", "thin_rotated", "vertical", "wainscotting_single",
					"wainscotting_double", "wainscotting_grate", "wainscotting_seamless", "walkway", "walkway_rotated",
					"wicker"));

	public static StyleData WINDOW_TRIM = new StyleData("window_trim", List.of("basic"));
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

	public void setTypes(List<String> types) {
		this.types = types;
	}

}
