package com.lance5057.compendium.blocks.entity;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.blocks.IStyleable;
import com.lance5057.compendium.blocks.entities.MultiMaterialBlockEntity;
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialModelData;
import com.lance5057.compendium.client.models.style.StyleModelData;
import com.lance5057.compendium.components.block.MultiMaterialBlockComponent;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.style.StyleData;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;

public class StyledMultiMaterialBlockEntity extends MultiMaterialBlockEntity implements IStyleable {

	List<StyleData> styles = new ArrayList<StyleData>();
	List<Integer> currentStyles = new ArrayList<Integer>();

	final int styleCount;
	final int materialCount;

	public StyledMultiMaterialBlockEntity(BlockPos pos, BlockState blockState, int styleCount, int materialCount,
			StyleData... styles) {
		super(CompendiumBlockEntities.WINDOW.get(), pos, blockState);
		this.styleCount = styleCount;
		this.materialCount = materialCount;
		this.styles = List.of(styles);
	}

	@Override
	public List<StyleData> getStyles() {
		return styles;
	}

	@Override
	public int getCurrent(int index) {
		if (currentStyles.size() > index)
			return currentStyles.get(index);
		return 0;
	}

	@Override
	public List<Integer> getCurrentAll() {
		return this.currentStyles;
	}

	public void setCurrentStyles(List<Integer> currentStyles) {
		this.currentStyles = currentStyles;
	}

	@Override
	public List<String> getCurrentAllString() {
		List<String> l = new ArrayList<>();

		for (int i = 0; i < styles.size(); i++) {
			int c = this.getCurrent(i);

			List<String> s = styles.get(i).getTypes();
			if (s.size() > c)
				l.add(s.get(c));
			else
				l.add(s.get(0));
		}

		return l;
	}

	@Override
	public void setCurrent(int index, int c) {
		currentStyles.set(index, c);
		this.setChanged();
		getLevel().sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
	}

	@Override
	public int getStyleCount() {
		return styleCount;
	}

	@Override
	public int getMaterialsCount() {
		return materialCount;
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
		builder.set(CompendiumComponents.MULTI_MATERIAL.get(), new MultiMaterialBlockComponent(materials));
	}

	@Override
	protected void applyImplicitComponents(DataComponentInput input) {
		super.applyImplicitComponents(input);
		StyleBlockComponent m = input.getOrDefault(CompendiumComponents.STYLE.get(), null);
		if (m != null) {
			this.currentStyles = new ArrayList<Integer>(m.styles());
		}

		MultiMaterialBlockComponent mm = input.getOrDefault(CompendiumComponents.MULTI_MATERIAL.get(), null);
		if (mm != null) {
			this.setMaterials(mm.types());
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
