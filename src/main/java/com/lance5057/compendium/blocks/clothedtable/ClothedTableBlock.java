package com.lance5057.compendium.blocks.clothedtable;

import java.util.List;
import java.util.stream.Stream;

import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.blocks.entities.StyledMultiMaterialBlockEntity;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.styleblock.IStyleBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ClothedTableBlock extends Block implements EntityBlock, IStyleBlock {

	// connections
	public static final BooleanProperty NW = BooleanProperty.create("nw");
	public static final BooleanProperty N = BooleanProperty.create("n");
	public static final BooleanProperty NE = BooleanProperty.create("ne");
	public static final BooleanProperty E = BooleanProperty.create("e");
	public static final BooleanProperty SE = BooleanProperty.create("se");
	public static final BooleanProperty S = BooleanProperty.create("s");
	public static final BooleanProperty SW = BooleanProperty.create("sw");
	public static final BooleanProperty W = BooleanProperty.create("w");

	protected static final VoxelShape BASE = Block.box(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	public ClothedTableBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(NW, false).setValue(N, false).setValue(NE, false)
				.setValue(E, false).setValue(SE, false).setValue(S, false).setValue(SW, false).setValue(W, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(N);
		builder.add(S);
		builder.add(E);
		builder.add(W);
		builder.add(NW);
		builder.add(NE);
		builder.add(SW);
		builder.add(SE);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new StyledMultiMaterialBlockEntity(pos, state, 3, 3, StyleData.TABLE_TOP,
				StyleData.TABLE_LEGS, StyleData.TABLE_CLOTH);
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = this.defaultBlockState();
		BlockPos pos = context.getClickedPos();// .relative(context.getClickedFace());
		Level level = context.getLevel();

		boolean n = level.getBlockState(pos.north()).is(CompendiumTags.TABLE);
		boolean s = level.getBlockState(pos.south()).is(CompendiumTags.TABLE);
		boolean w = level.getBlockState(pos.west()).is(CompendiumTags.TABLE);
		boolean e = level.getBlockState(pos.east()).is(CompendiumTags.TABLE);

		boolean nw = level.getBlockState(pos.north().west()).is(CompendiumTags.TABLE);
		boolean sw = level.getBlockState(pos.south().west()).is(CompendiumTags.TABLE);
		boolean ne = level.getBlockState(pos.north().east()).is(CompendiumTags.TABLE);
		boolean se = level.getBlockState(pos.south().east()).is(CompendiumTags.TABLE);

		return state.setValue(N, n).setValue(S, s).setValue(W, w).setValue(E, e).setValue(NW, nw).setValue(SW, sw)
				.setValue(NE, ne).setValue(SE, se);
	}

	@Override
	protected void updateIndirectNeighbourShapes(BlockState state, LevelAccessor level, BlockPos pos, int flags,
			int recursionLeft) {
		List<BlockPos> dirs = Stream
				.of(new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(1, 0, 1), new BlockPos(-1, 0, -1),
						new BlockPos(-1, 0, 1), new BlockPos(1, 0, -1), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1))
				.toList();

		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
		for (BlockPos direction : dirs) {

			blockpos$mutableblockpos.setWithOffset(pos, direction);
			BlockState nState = level.getBlockState(blockpos$mutableblockpos);

			if (nState.is(this)) {

				boolean n = level.getBlockState(blockpos$mutableblockpos.north()).is(CompendiumTags.TABLE);
				boolean s = level.getBlockState(blockpos$mutableblockpos.south()).is(CompendiumTags.TABLE);
				boolean w = level.getBlockState(blockpos$mutableblockpos.west()).is(CompendiumTags.TABLE);
				boolean e = level.getBlockState(blockpos$mutableblockpos.east()).is(CompendiumTags.TABLE);

				boolean nw = level.getBlockState(blockpos$mutableblockpos.north().west()).is(CompendiumTags.TABLE);
				boolean sw = level.getBlockState(blockpos$mutableblockpos.south().west()).is(CompendiumTags.TABLE);
				boolean ne = level.getBlockState(blockpos$mutableblockpos.north().east()).is(CompendiumTags.TABLE);
				boolean se = level.getBlockState(blockpos$mutableblockpos.south().east()).is(CompendiumTags.TABLE);

				level.setBlock(blockpos$mutableblockpos, nState.setValue(N, n).setValue(S, s).setValue(W, w)
						.setValue(E, e).setValue(NW, nw).setValue(SW, sw).setValue(NE, ne).setValue(SE, se),
						Block.UPDATE_ALL);
			}
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return BASE;
	}

	@Override
	public List<String> getStyles(List<Integer> current) {

		return List.of(
				StyleData.TABLE_TOP.getTypes().get(current.get(0)),
				StyleData.TABLE_LEGS.getTypes().get(current.get(1)),
				StyleData.TABLE_CLOTH.getTypes().get(current.get(2)));
	}

}
