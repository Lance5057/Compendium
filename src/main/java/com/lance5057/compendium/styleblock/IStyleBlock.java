package com.lance5057.compendium.styleblock;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.style.StyleData;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface IStyleBlock {
	public default List<String> getStyles(List<Integer> current) {
		List<String> s = new ArrayList<String>();

		StyleData[] d = this.getStyleData();
		for (int i = 0; i < d.length; i++) {
			if (current.size() > i)
				if (d[i].getTypes().size() > current.get(i))
					s.add(d[i].getTypes().get(current.get(i)));
		}

		return s;
	}

	public ResourceLocation getItemModelLocation();

	public void onStyleChanged(Level level, BlockPos pos, BlockState state);

	public String getBaseStyleName(int current);

	public StyleData[] getStyleData();
}
