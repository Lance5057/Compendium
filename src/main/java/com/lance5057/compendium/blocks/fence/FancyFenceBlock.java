package com.lance5057.compendium.blocks.fence;

import java.util.List;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.blocks.entities.StyledMultiMaterialBlockEntity;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.styleblock.IStyleBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FancyFenceBlock extends FenceBlock implements EntityBlock, IStyleBlock {

	public FancyFenceBlock(Properties p_53302_) {
		super(p_53302_);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new StyledMultiMaterialBlockEntity(pos, state, 2, 2, StyleData.FENCE_POST, StyleData.FENCE_SIDE);
	}

	@Override
	public List<String> getStyles(List<Integer> current) {
		return List.of(StyleData.FENCE_POST.getTypes().get(current.get(0)),
				StyleData.FENCE_SIDE.getTypes().get(current.get(1)));
	}

	@Override
	public ResourceLocation getItemModelLocation() {
		return Compendium.modLoc("extra/fancy_fence");
	}

	@Override
	public void onStyleChanged(Level level, BlockPos pos, BlockState state) {
		// TODO Auto-generated method stub

	}
}
