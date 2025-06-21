package com.lance5057.compendium.blocks;

import java.util.List;

import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.blocks.entities.SimpleStyleBlockEntity;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.styleblock.StyleType;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

public class SimpleStyleBlock extends Block implements EntityBlock /* extends StyleBlock */ {
//	public static enum Styles {
//		FULL, HALF, VERTICAL_HALF, QUARTER, OFFSET_HALF, OFFSET_QUARTER, INDENTED, INDENTED_SEGMENT, DENTED,
//		DENTED_SEGMENT
//	};

//	public final List<List<String>> style;

//	public static final IntegerProperty STYLE = IntegerProperty.create("style", 0, Styles.values().length - 1);

	public SimpleStyleBlock(Properties properties) {
		super(properties);
//		this.style = s;
	}

//	@Override
//	public BlockItemStateProperties getStateProperties(BlockState state) {
//		return BlockItemStateProperties.EMPTY.with(STYLE, getCurrentStyle(state));
//	}
//
//	@Override
//	public int numStyles() {
//		return 10;
//	}
//
//	@Override
//	public int getCurrentStyle(BlockState state) {
//		return state.getValue(STYLE);
//	}
//
//	@Override
//	public void setNextStyle(Level level, BlockPos pos, BlockState state) {
//		int next = state.getValue(STYLE) + 1;
//		if (state.getValue(STYLE) >= numStyles() - 1) {
//			next = 0;
//		}
//
//		for (int i = 0; i < 10; i++)
//			level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, state), pos.getX() + 0.5f, pos.getY() + 0.5f,
//					pos.getZ() + 0.5f, 0, 0, 0);
//		level.playSound(null, pos, SoundEvents.METAL_HIT, SoundSource.BLOCKS, 1.0F, 1.0F);
//
//		BlockState nextState = state.setValue(STYLE, next);
//		level.setBlock(pos, nextState, 3);
//	}
//
//	@Override
//	public void setStyle(Level level, BlockPos pos, BlockState state, int style) {
//		BlockState nextState = state.setValue(STYLE, style);
//		level.setBlock(pos, nextState, Block.UPDATE_ALL);
//	}
//
//	@Override
//	public boolean isPatreonStyle(int style) {
//		return false;
//	}
//
//	@Override
//	public void setPrevStyle(Level level, BlockPos pos, BlockState state) {
//		int next = state.getValue(STYLE) - 1;
//		if (next < 0) {
//			next = this.numStyles() - 1;
//		}
//
//		for (int i = 0; i < 10; i++)
//			level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, state), pos.getX() + 0.5f, pos.getY() + 0.5f,
//					pos.getZ() + 0.5f, 0, 0, 0);
//		level.playSound(null, pos, SoundEvents.METAL_HIT, SoundSource.BLOCKS, 1.0F, 1.0F);
//
//		BlockState nextState = state.setValue(STYLE, next);
//		level.setBlock(pos, nextState, 3);
//	}
//
//	@Override
//	public BlockState getState(int i) {
//		return this.defaultBlockState().setValue(STYLE, i);
//	}

//	@Override
//	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
//		pBuilder.add(STYLE);
//	}
//
//	@Override
//	public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos,
//			Player player) {
//		ItemStack stack = new ItemStack(this);
//		if (level.getBlockEntity(pos) != null && level.getBlockEntity(pos) instanceof SimpleStyleBlockEntity ssbe) {
//			stack.set(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY);
//			stack.set(CompendiumComponents.STYLE.get(), new StyleBlockComponent(ssbe.getStyles()));
//		}
//		return stack;
//	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SimpleStyleBlockEntity(pos, state);
	}

}
