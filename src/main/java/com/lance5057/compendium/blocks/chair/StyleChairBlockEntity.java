package com.lance5057.compendium.blocks.chair;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.blocks.IStyleable;
import com.lance5057.compendium.blocks.entities.MultiMaterialBlockEntity;
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialModelData;
import com.lance5057.compendium.client.models.style.StyleModelData;
import com.lance5057.compendium.components.block.StyleBlockComponent;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;

public class StyleChairBlockEntity extends MultiMaterialBlockEntity implements IStyleable {

	public static List<String> back = List.of("basic", "basic_panel", "braced", "contemporary",
			"contemporary_slats", "cross", "cross_framed", "fan", "flat", "flat_extra", "full", "laced", "ladder",
			"ladder_tall", "lath", "lattice", "live_edge", "open", "panel", "panel_weave", "planks_horizontal",
			"planks_horizontal_angled", "slats", "slats_chunky", "solid", "splat", "splat_double", "splat_slat",
			"turned_panel",	"turned_panel_weave", "vienna", "weave", "windsor");
	public static List<String> seat = List.of("basic", "planks_horizontal", "planks_horizontal_angled", "tilted",
			"tilted_weave", "weave");
	public static List<String> legs = List.of("basic", "angled", "crosstie", "pedestal", "pedestal_cross",
			"pedestal_cross_tilted", "rails_connected", "rails_end", "rails_full", "rails_full_offset", "rails_side",
			"solid", "solid_ends", "solid_sides");

	List<List<String>> styles = List.of(back, seat, legs); // Immutable!

	List<Integer> currentStyles = new ArrayList<Integer>();

	public StyleChairBlockEntity(BlockPos pos, BlockState blockState) {
		super(CompendiumBlockEntities.CHAIR.get(), pos, blockState);
//		currentStyles = new ArrayList<Integer>(List.of(0, 0, 0));
	}

	@Override
	public int getMaterialsCount() {
		return 3;
	}

	@Override
	public List<List<String>> getStyles() {
		return this.styles;
	}

	@Override
	public List<Integer> getCurrentAll() {
		return this.currentStyles;
	}

	@Override
	public int getCurrent(int index) {
		if (currentStyles.size() > index)
			return currentStyles.get(index);
		return 0;
	}

	@Override
	public void setCurrent(int index, int c) {
		currentStyles.set(index, c);
	}

	@Override
	public List<String> getCurrentAllString() {
		return List.of(back.get(this.getCurrent(0)), seat.get(this.getCurrent(1)), legs.get(this.getCurrent(2)));
	}

	@Override
	public int getStyleCount() {
		return 3;
	}

	@Override
	public ModelData getModelData() {
		return ModelData.builder().with(MultiMaterialModelData.STATE, materials)
				.with(StyleModelData.STYLES, getCurrentAllString()).build();
	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.Builder builder) {
		super.collectImplicitComponents(builder);

		builder.set(CompendiumComponents.STYLE.get(), new StyleBlockComponent(currentStyles));
	}

	@Override
	protected void applyImplicitComponents(DataComponentInput input) {
		super.applyImplicitComponents(input);
		StyleBlockComponent m = input.getOrDefault(CompendiumComponents.STYLE.get(), null);
		if (m != null) {
			this.currentStyles = new ArrayList<Integer>(m.styles());
		}
	}

	@Override
	protected void readNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {
		this.currentStyles = new ArrayList<Integer>(this.readStyleNBT(nbt, registries));
	}

	@Override
	protected void writeNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {
		this.writeStyleNBT(nbt, registries);
	}

}
