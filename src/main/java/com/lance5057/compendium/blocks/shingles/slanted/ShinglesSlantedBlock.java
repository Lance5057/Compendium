package com.lance5057.compendium.blocks.shingles.slanted;

import java.util.List;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.blocks.entities.StyledMultiMaterialBlockEntity;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.styleblock.IStyleBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ShinglesSlantedBlock extends StairBlock implements EntityBlock, IStyleBlock {

	public ShinglesSlantedBlock(BlockState baseState, Properties properties) {
		super(baseState, properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new StyledMultiMaterialBlockEntity(pos, state, 3, 3, StyleData.SHINGLES_SHINGLES,
				StyleData.SUPPORT_SHINGLES, StyleData.GABLE_SHINGLES);
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Override
	public List<String> getStyles(List<Integer> current) {
		return List.of(StyleData.SHINGLES_SHINGLES.getTypes().get(current.get(0)),
				StyleData.SUPPORT_SHINGLES.getTypes().get(current.get(1)));
	}

	@Override
	public ResourceLocation getItemModelLocation() {
		return Compendium.modLoc("extra/shingles_slanted");
	}

	@Override
	public void onStyleChanged(Level level, BlockPos pos, BlockState state) {
		// TODO Auto-generated method stub

	}
}
