package com.lance5057.compendium.index.material.extentions.extrametalblocks;

import com.lance5057.compendium.styleblock.StyleBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class StyleMetalTileBlock extends StyleBlock {
	public static enum Styles {
		FULL, HALF, VERTICAL_HALF, QUARTER, OFFSET_HALF, OFFSET_QUARTER
	};

	public static final IntegerProperty STYLE = IntegerProperty.create("style", 0, 5);

	public StyleMetalTileBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(STYLE, 0));
	}

	@Override
	protected BlockItemStateProperties getStateProperties(BlockState state) {
		return BlockItemStateProperties.EMPTY.with(STYLE, getCurrentStyle(state));
	}

	@Override
	public int numStyles() {
		return 6;
	}

	@Override
	public int getCurrentStyle(BlockState state) {
		return state.getValue(STYLE);
	}

	@Override
	public void setNextStyle(Level level, BlockPos pos, BlockState state) {
		int next = state.getValue(STYLE) + 1;
		if (state.getValue(STYLE) >= numStyles() - 1) {
			next = 0;
		}

		for (int i = 0; i < 10; i++)
			level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, state), pos.getX() + 0.5f, pos.getY() + 0.5f,
					pos.getZ() + 0.5f, 0, 0, 0);
		level.playSound(null, pos, SoundEvents.METAL_HIT, SoundSource.BLOCKS, 1.0F, 1.0F);

		BlockState nextState = state.setValue(STYLE, next);
		level.setBlock(pos, nextState, 3);
	}

	@Override
	public void setStyle(Level level, BlockPos pos, BlockState state, int style) {
		BlockState nextState = state.setValue(STYLE, style);
		level.setBlock(pos, nextState, 3);
	}

	@Override
	public boolean isPatreonStyle(int style) {
		return false;
	}

	@Override
	public void setPrevStyle(Level level, BlockPos pos, BlockState state) {
		int next = state.getValue(STYLE) - 1;
		if (next < 0) {
			next = this.numStyles() - 1;
		}

		for (int i = 0; i < 10; i++)
			level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, state), pos.getX() + 0.5f, pos.getY() + 0.5f,
					pos.getZ() + 0.5f, 0, 0, 0);
		level.playSound(null, pos, SoundEvents.METAL_HIT, SoundSource.BLOCKS, 1.0F, 1.0F);

		BlockState nextState = state.setValue(STYLE, next);
		level.setBlock(pos, nextState, 3);
	}

	@Override
	public BlockState getState(int i) {
		return this.defaultBlockState().setValue(STYLE, i);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		pBuilder.add(STYLE);
	}

}
