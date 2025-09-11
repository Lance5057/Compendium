package com.lance5057.compendium.blocks;

import com.lance5057.compendium.blocks.entities.SimpleStyleBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RotatedPillarStyleBlock extends RotatedPillarBlock implements EntityBlock {

	final VoxelShape vert;
	final VoxelShape hori1;
	final VoxelShape hori2;

	public RotatedPillarStyleBlock(Properties properties, VoxelShape vert, VoxelShape hori, VoxelShape hori2) {
		super(properties);
		this.vert = vert;
		this.hori1 = hori;
		this.hori2 = hori2;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		if (state.getValue(BlockStateProperties.AXIS) == Direction.Axis.Z) {
			return hori1;
		}
		if (state.getValue(BlockStateProperties.AXIS) == Direction.Axis.X) {
			return hori2;
		}
		return vert;
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SimpleStyleBlockEntity(pos, state);
	}

}
