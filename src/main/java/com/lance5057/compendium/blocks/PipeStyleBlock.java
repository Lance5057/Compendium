package com.lance5057.compendium.blocks;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.blocks.entities.SimpleStyleBlockEntity;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.styleblock.IStyleBlock;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.WallBlock;
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
	public final MATERIAL_TYPES matType;
	public final String materialName;

	public PipeStyleBlock(Properties properties, StyleData... styles) {
		this(0.25f, properties, ResourceLocation.withDefaultNamespace("air"), MATERIAL_TYPES.CERAMIC, "",
				List.of("error"), styles);
	}

	public PipeStyleBlock(float apothem, Properties properties, ResourceLocation itemRenderer, MATERIAL_TYPES matType,
			String materialName, List<String> styleBases, StyleData... styles) {
		super(apothem, properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(true))
				.setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(true))
				.setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false))
				.setValue(DOWN, Boolean.valueOf(false)).setValue(WATERLOGGED, false));
		this.styles = styles;
		this.itemRendererLocation = itemRenderer;
		this.styleBases = styleBases;
		this.materialName = materialName;
		this.matType = matType;

	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SimpleStyleBlockEntity(pos, state, matType, materialName, styles.length, styles);
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

		return this.defaultBlockState().trySetValue(DOWN, canAttach(level, pos, down, Direction.DOWN))
				.trySetValue(UP, canAttach(level, pos, up, Direction.UP))
				.trySetValue(NORTH, canAttach(level, pos, north, Direction.NORTH))
				.trySetValue(EAST, canAttach(level, pos, east, Direction.EAST))
				.trySetValue(SOUTH, canAttach(level, pos, south, Direction.SOUTH))
				.trySetValue(WEST, canAttach(level, pos, west, Direction.WEST))
				.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}

	private boolean canAttach(LevelAccessor level, BlockPos pos, BlockState state, Direction direction) {
		return state.isFaceSturdy(level, pos, direction.getOpposite()) || state.getBlock() instanceof PipeBlock
				|| specialCases(state, direction);
	}

	private boolean specialCases(BlockState state, Direction direction) {
		if (state.getBlock() instanceof CrossCollisionBlock || state.getBlock() instanceof WallBlock)
			if (direction == Direction.DOWN || direction == Direction.UP)
				return true;

		if (state.is(Blocks.HOPPER))
			if (state.getValue(HopperBlock.FACING) == Direction.DOWN)
				if (direction == Direction.UP)
					return true;

		if (state.getBlock() instanceof DirectionalBlock) {
			Axis axis = state.getValue(DirectionalBlock.FACING).getAxis();
			switch (axis) {
			case X:
				if (direction == Direction.EAST || direction == Direction.WEST)
					return true;
				break;
			case Y:
				if (direction == Direction.DOWN || direction == Direction.UP)
					return true;
				break;
			case Z:
				if (direction == Direction.NORTH || direction == Direction.SOUTH)
					return true;
				break;
			}
		}

		if (state.getBlock() instanceof RotatedPillarBlock) {
			Axis axis = state.getValue(RotatedPillarBlock.AXIS);
			switch (axis) {
			case X:
				if (direction == Direction.EAST || direction == Direction.WEST)
					return true;
				break;
			case Y:
				if (direction == Direction.DOWN || direction == Direction.UP)
					return true;
				break;
			case Z:
				if (direction == Direction.NORTH || direction == Direction.SOUTH)
					return true;
				break;
			}
		}

		return false;
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
				if (styles[i].getTypes().size() > current.get(i))
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
