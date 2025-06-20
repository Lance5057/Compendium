package com.lance5057.compendium.blocks.chair;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.blocks.IStyleable;
import com.lance5057.compendium.blocks.entities.MultiMaterialBlockEntity;
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialModelData;
import com.lance5057.compendium.client.models.style.StyleModelData;
import com.lance5057.compendium.styleblock.StyleType;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;

public class StyleChairBlockEntity extends MultiMaterialBlockEntity implements IStyleable {
	public static StyleType back = new StyleType("back", "basic_heavy", "basic_medium", "basic_light");
	public static StyleType seat = new StyleType("seat", "basic_heavy", "basic_medium", "basic_light");
	public static StyleType legs = new StyleType("legs", "basic_heavy", "basic_medium", "basic_light");

	List<StyleType> styles = new ArrayList<StyleType>();

	public StyleChairBlockEntity(BlockPos pos, BlockState blockState) {
		super(CompendiumBlockEntities.CHAIR.get(), pos, blockState);

		styles.add(back);
		styles.add(seat);
		styles.add(legs);
	}

	@Override
	public int getMaterialsCount() {
		return 3;
	}

	@Override
	public List<StyleType> getStyles() {
		return this.styles;
	}

	@Override
	public void setStyles(List<StyleType> style) {
		this.styles = style;
	}

	@Override
	public ModelData getModelData() {
		return ModelData.builder().with(MultiMaterialModelData.STATE, materials)
				.with(StyleModelData.STYLES, this.styles).build();
	}

}
