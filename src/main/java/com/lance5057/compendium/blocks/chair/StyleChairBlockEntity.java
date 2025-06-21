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
import com.lance5057.compendium.styleblock.StyleType;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;

public class StyleChairBlockEntity extends MultiMaterialBlockEntity implements IStyleable {
	public static List<String> back = List.of("back", "basic_heavy", "basic_medium", "basic_light", "ladder_heavy",
			"ladder_medium", "ladder_light", "cross", "full", "live_edge", "open", "panel", "panel_weave", "slats",
			"turned_panel", "turned_panel_weave", "weave", "windsor", "cross_heavy", "cross_light", "cross_medium",
			"panel_heavy", "panel_medium", "panel_light", "slat_heavy", "slat_light", "slat_medium", "solid_heavy",
			"solid_light", "solid_medium");
	public static List<String> seat = List.of("seat", "basic_heavy", "basic_medium", "basic_light");
	public static List<String> legs = List.of("legs", "basic_heavy", "basic_medium", "basic_light", "rails_heavy",
			"rails_medium", "rails_light");

	List<List<String>> styles = List.of(back, seat, legs); // Immutable!

	List<Integer> current_styles = new ArrayList<Integer>();

	public StyleChairBlockEntity(BlockPos pos, BlockState blockState) {
		super(CompendiumBlockEntities.CHAIR.get(), pos, blockState);
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
	public int getCurrent(int index) {
		return current_styles.get(index);
	}

	@Override
	public void setCurrent(int index, int c) {
		current_styles.set(index, c);
	}

	@Override
	public int getStyleCount() {
		return 3;
	}

	@Override
	public ModelData getModelData() {
		return ModelData.builder().with(MultiMaterialModelData.STATE, materials)
				.with(StyleModelData.STYLES, current_styles).build();
	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.Builder builder) {
		super.collectImplicitComponents(builder);

		builder.set(CompendiumComponents.STYLE.get(), new StyleBlockComponent(current_styles));
	}

	@Override
	protected void applyImplicitComponents(DataComponentInput input) {
		super.applyImplicitComponents(input);
		StyleBlockComponent m = input.getOrDefault(CompendiumComponents.STYLE.get(), null);
		if (m != null) {
			this.current_styles = m.styles();
		}
	}

	@Override
	protected void readNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {
		this.readStyleNBT(nbt, registries);
	}

	@Override
	protected void writeNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {
		this.writeStyleNBT(nbt, registries);
	}

}
