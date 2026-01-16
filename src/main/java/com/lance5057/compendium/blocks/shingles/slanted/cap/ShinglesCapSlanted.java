package com.lance5057.compendium.blocks.shingles.slanted.cap;

import java.util.List;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.blocks.entities.StyledMultiMaterialBlockEntity;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.styleblock.IStyleBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class ShinglesCapSlanted extends Block implements EntityBlock, IStyleBlock {
	public static final BooleanProperty NORTH = PipeBlock.NORTH;
	public static final BooleanProperty EAST = PipeBlock.EAST;
	public static final BooleanProperty SOUTH = PipeBlock.SOUTH;
	public static final BooleanProperty WEST = PipeBlock.WEST;
	public static final BooleanProperty TOP = BooleanProperty.create("top");

	public ShinglesCapSlanted(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false))
				.setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false))
				.setValue(WEST, Boolean.valueOf(false)).setValue(TOP, Boolean.valueOf(false)));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new StyledMultiMaterialBlockEntity(pos, state, 3, 3, StyleData.SHINGLES_SHINGLES,
				StyleData.SUPPORT_SHINGLES, StyleData.GABLE_SHINGLES);
	}

	@Override
	public StyleData[] getStyleData() {
		return new StyleData[] { StyleData.SHINGLES_SHINGLES, StyleData.SUPPORT_SHINGLES, StyleData.GABLE_SHINGLES };
	}

	@Override
	protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level,
			BlockPos currentPos, BlockPos facingPos) {
		BlockPos blockpos1 = currentPos.north();
		BlockPos blockpos2 = currentPos.east();
		BlockPos blockpos3 = currentPos.south();
		BlockPos blockpos4 = currentPos.west();
		BlockPos blockpos5 = currentPos.above();
		BlockState blockstate = level.getBlockState(blockpos1);
		BlockState blockstate1 = level.getBlockState(blockpos2);
		BlockState blockstate2 = level.getBlockState(blockpos3);
		BlockState blockstate3 = level.getBlockState(blockpos4);
		BlockState blockstate4 = level.getBlockState(blockpos5);
		return super.updateShape(state, facing, state, level, currentPos, facingPos)
				.setValue(NORTH,
						Boolean.valueOf(this.connectsTo(blockstate,
								blockstate.isFaceSturdy(level, blockpos1, Direction.SOUTH), Direction.SOUTH)))
				.setValue(EAST,
						Boolean.valueOf(this.connectsTo(blockstate1,
								blockstate1.isFaceSturdy(level, blockpos2, Direction.WEST), Direction.WEST)))
				.setValue(SOUTH,
						Boolean.valueOf(this.connectsTo(blockstate2,
								blockstate2.isFaceSturdy(level, blockpos3, Direction.NORTH), Direction.NORTH)))
				.setValue(WEST,
						Boolean.valueOf(this.connectsTo(blockstate3,
								blockstate3.isFaceSturdy(level, blockpos4, Direction.EAST), Direction.EAST)))
				.setValue(TOP, !blockstate4.isAir());
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockGetter blockgetter = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		BlockPos blockpos1 = blockpos.north();
		BlockPos blockpos2 = blockpos.east();
		BlockPos blockpos3 = blockpos.south();
		BlockPos blockpos4 = blockpos.west();
		BlockState blockstate = blockgetter.getBlockState(blockpos1);
		BlockState blockstate1 = blockgetter.getBlockState(blockpos2);
		BlockState blockstate2 = blockgetter.getBlockState(blockpos3);
		BlockState blockstate3 = blockgetter.getBlockState(blockpos4);
		return super.getStateForPlacement(context)
				.setValue(NORTH,
						Boolean.valueOf(this.connectsTo(blockstate,
								blockstate.isFaceSturdy(blockgetter, blockpos1, Direction.SOUTH), Direction.SOUTH)))
				.setValue(EAST,
						Boolean.valueOf(this.connectsTo(blockstate1,
								blockstate1.isFaceSturdy(blockgetter, blockpos2, Direction.WEST), Direction.WEST)))
				.setValue(SOUTH,
						Boolean.valueOf(this.connectsTo(blockstate2,
								blockstate2.isFaceSturdy(blockgetter, blockpos3, Direction.NORTH), Direction.NORTH)))
				.setValue(WEST, Boolean.valueOf(this.connectsTo(blockstate3,
						blockstate3.isFaceSturdy(blockgetter, blockpos4, Direction.EAST), Direction.EAST)));
	}

	public boolean connectsTo(BlockState state, boolean isSideSolid, Direction direction) {
		Block block = state.getBlock();
		boolean flag = block instanceof ShinglesCapSlanted;
		return !isExceptionForConnection(state) && flag;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, SOUTH, WEST, TOP);
	}

	@Override
	public List<String> getStyles(List<Integer> current) {
		return List.of(StyleData.SHINGLES_SHINGLES.getTypes().get(current.get(0)),
				StyleData.SUPPORT_SHINGLES.getTypes().get(current.get(1)));
	}

	@Override
	public ResourceLocation getItemModelLocation() {
		return Compendium.modLoc("extra/shingles_cap_slanted");
	}

	@Override
	public void onStyleChanged(Level level, BlockPos pos, BlockState state) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getBaseStyleName(int current) {
		switch (current) {
		case 0:
			return "shingles";
		case 1:
			return "support";
		}
		return "error";
	}
}
