package com.lance5057.compendium.styleblock;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.blocks.entities.StyledMultiMaterialBlockEntity;
import com.lance5057.compendium.multimaterial.MultiMaterialType;
import com.lance5057.compendium.style.StyleData;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
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

	public default BlockPos buildCommand(BlockPos pos, Level level, BlockItem bi, IStyleBlock sb,
			List<MultiMaterialType> mm, List<Integer> styles, int style_index, int style, List<String> mats,
			int material_index) {
		BlockPos nPos = new BlockPos(pos.getX() + (style_index * 2), pos.getY() + (material_index * 2),
				pos.getZ() + (style * 2));

		level.setBlock(nPos, bi.getBlock().defaultBlockState(), Block.UPDATE_ALL);
		StyledMultiMaterialBlockEntity bentity = (StyledMultiMaterialBlockEntity) level.getBlockEntity(nPos);

		List<Integer> newStyles = new ArrayList<Integer>(styles);
		newStyles.set(style_index, style);
		bentity.setMaterials(mm);
		bentity.setMaterial(style_index, mats.get(material_index));
		bentity.setCurrentStyles(newStyles);

		BlockState state = level.getBlockState(nPos);
		sb.onStyleChanged(level, pos, state);
		level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);
		return nPos;
	}
}
