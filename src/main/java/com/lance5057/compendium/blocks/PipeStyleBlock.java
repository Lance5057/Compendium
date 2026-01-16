package com.lance5057.compendium.blocks;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.blocks.entities.SimpleStyleBlockEntity;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.styleblock.IStyleBlock;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class PipeStyleBlock extends PipeBlock implements EntityBlock, IStyleBlock, SimpleWaterloggedBlock {
	public static final MapCodec<PipeStyleBlock> CODEC = simpleCodec(PipeStyleBlock::new);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public final StyleData[] styles;
	final ResourceLocation itemRendererLocation;
	List<String> styleBases;

	public PipeStyleBlock(Properties properties, StyleData... styles) {
		this(0.25f, properties, ResourceLocation.withDefaultNamespace("air"), List.of("error"), styles);
	}

	public PipeStyleBlock(float apothem, Properties properties, ResourceLocation itemRenderer, List<String> styleBases,
			StyleData... styles) {
		super(apothem, properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(true))
				.setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(true))
				.setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false))
				.setValue(DOWN, Boolean.valueOf(false)).setValue(WATERLOGGED, false));
		this.styles = styles;
		this.itemRendererLocation = itemRenderer;
		this.styleBases = styleBases;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SimpleStyleBlockEntity(pos, state, styles.length, styles);
	}

	@Override
	protected MapCodec<? extends PipeBlock> codec() {
		return CODEC;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();

		FluidState fluidstate = context.getLevel().getFluidState(pos);

		return checkNeighbors(level, pos).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}

	@Override
	protected FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	private BlockState checkNeighbors(LevelAccessor level, BlockPos pos) {
		BlockState down = level.getBlockState(pos.below());
		BlockState up = level.getBlockState(pos.above());
		BlockState north = level.getBlockState(pos.north());
		BlockState east = level.getBlockState(pos.east());
		BlockState south = level.getBlockState(pos.south());
		BlockState west = level.getBlockState(pos.west());
		FluidState fluidstate = level.getFluidState(pos);

		return this.defaultBlockState().trySetValue(DOWN, down.isSolidRender(level, pos) || down.is(this))
				.trySetValue(UP, up.isSolidRender(level, pos) || up.is(this))
				.trySetValue(NORTH, north.isSolidRender(level, pos) || north.is(this))
				.trySetValue(EAST, east.isSolidRender(level, pos) || east.is(this))
				.trySetValue(SOUTH, south.isSolidRender(level, pos) || south.is(this))
				.trySetValue(WEST, west.isSolidRender(level, pos) || west.is(this))
				.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}

	@Override
	protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level,
			BlockPos currentPos, BlockPos facingPos) {
		return checkNeighbors(level, currentPos);
	}

	@Override
	public List<String> getStyles(List<Integer> current) {
		List<String> r = new ArrayList<String>();
		for (int i = 0; i < current.size(); i++) {
			if (styles.length > i)
				r.add(this.styles[i].getTypes().get(i));
		}
		return r;
	}

	@Override
	public ResourceLocation getItemModelLocation() {
		return this.itemRendererLocation;
	}

	@Override
	public void onStyleChanged(Level level, BlockPos pos, BlockState state) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getBaseStyleName(int current) {
		return this.styleBases.get(current);
	}

	@Override
	public StyleData[] getStyleData() {
		return this.styles;
	}
}
