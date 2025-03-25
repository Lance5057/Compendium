package com.lance5057.compendium.blocks;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.blocks.entities.ChairBlockEntity;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;

public class ChairBlock extends HorizontalDirectionalBlock implements EntityBlock {
	public static final MapCodec<ChairBlock> CODEC = simpleCodec(ChairBlock::new);

	public ChairBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
			BlockHitResult hitResult) {
		BlockEntity blockentity = level.getBlockEntity(pos);
		if (blockentity instanceof ChairBlockEntity be) {
			return be.attemptSit(state, level, pos, player, hitResult);
		}
		return InteractionResult.CONSUME;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return CompendiumBlockEntities.CHAIR.get().create(pos, state);
	}

}
