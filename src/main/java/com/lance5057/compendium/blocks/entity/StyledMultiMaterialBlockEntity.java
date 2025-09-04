package com.lance5057.compendium.blocks.entity;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.blocks.IStyleable;
import com.lance5057.compendium.blocks.entities.MultiMaterialBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class StyledMultiMaterialBlockEntity extends MultiMaterialBlockEntity implements IStyleable {

	List<List<String>> styles = new ArrayList<List<String>>();
	List<Integer> currentStyles = new ArrayList<Integer>();

	final int styleCount;
	final int materialCount;

	public StyledMultiMaterialBlockEntity(BlockPos pos, BlockState blockState, int styleCount, int materialCount) {
		super(CompendiumBlockEntities.STYLE.get(), pos, blockState);
		this.styleCount = styleCount;
		this.materialCount = materialCount;

	}

	@Override
	public List<List<String>> getStyles() {
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

			List<String> s = styles.get(i);
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

}
