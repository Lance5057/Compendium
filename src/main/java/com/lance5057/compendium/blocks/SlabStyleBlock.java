package com.lance5057.compendium.blocks;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.lance5057.compendium.blocks.entities.SimpleStyleBlockEntity;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.styleblock.IStyleBlock;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SlabStyleBlock extends Block implements EntityBlock, IStyleBlock, SimpleWaterloggedBlock {
//	public static final Codec<SlabStyleBlock> CODEC = RecordCodecBuilder.create(inst -> inst
//			.group(BlockBehaviour.Properties.CODEC.fieldOf("properties").forGetter(BlockBehaviour::properties),
//					Codec.list(StyleData.CODEC).fieldOf("styles").forGetter(s -> List.of(s.getStyles())),
//					ResourceLocation.CODEC.fieldOf("itemRendererLocation")
//							.forGetter(SlabStyleBlock::getItemRendererLocation),
//					Codec.STRING.fieldOf("matType").forGetter(SlabStyleBlock::getMatType),
//					Codec.STRING.fieldOf("materialName").forGetter(SlabStyleBlock::getMaterialName),
//					Codec.list(Codec.STRING).fieldOf("styleBases").forGetter(SlabStyleBlock::getStyleBases))
//			.apply(inst, SlabStyleBlock::new));

	// public SlabStyleBlock(Properties properties, List<StyleData> styles,
	// ResourceLocation itemRendererLocation,
	// MATERIAL_TYPES matType, String materialName, List<String> styleBases)

	public final StyleData[] styles;
	final ResourceLocation itemRendererLocation;
	List<String> styleBases;
	public final MATERIAL_TYPES matType;
	public final String materialName;

	public StyleData[] getStyles() {
		return styles;
	}

	public ResourceLocation getItemRendererLocation() {
		return itemRendererLocation;
	}

	public List<String> getStyleBases() {
		return styleBases;
	}

	public String getMatType() {
		return matType.toString();
	}

	public String getMaterialName() {
		return materialName;
	}

	public static BooleanProperty getFullSlab() {
		return FULL_SLAB;
	}

	public static BooleanProperty getWaterlogged() {
		return WATERLOGGED;
	}

	public static final BooleanProperty FULL_SLAB = BooleanProperty.create("full");
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final DirectionProperty FACING = BlockStateProperties.FACING;

	protected static final VoxelShape BOTTOM_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
	protected static final VoxelShape TOP_AABB = Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);
	protected static final VoxelShape NORTH_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 8.0);
	protected static final VoxelShape SOUTH_AABB = Block.box(0.0, 0.0, 8.0, 16.0, 16.0, 16.0);
	protected static final VoxelShape EAST_AABB = Block.box(8.0, 0.0, 0.0, 16.0, 16.0, 16.0);
	protected static final VoxelShape WEST_AABB = Block.box(0.0, 0.0, 0.0, 8.0, 16.0, 16.0);

	public SlabStyleBlock(Properties properties, List<StyleData> styles, ResourceLocation itemRendererLocation,
			MATERIAL_TYPES matType, String materialName, List<String> styleBases) {
		super(properties);
		this.styles = styles.toArray(new StyleData[0]);
		this.itemRendererLocation = itemRendererLocation;
		this.styleBases = styleBases;
		this.materialName = materialName;
		this.matType = matType;

		this.registerDefaultState(this.defaultBlockState().setValue(FULL_SLAB, false).setValue(WATERLOGGED, false)
				.setValue(FACING, Direction.DOWN));
	}

	@Override
	protected boolean useShapeForLightOcclusion(BlockState state) {
		return !state.getValue(FULL_SLAB);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, FULL_SLAB, WATERLOGGED);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SimpleStyleBlockEntity(pos, state, matType, materialName, styles.length, styles);
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Override
	public List<String> getStyles(List<Integer> current) {
		List<String> s = new ArrayList<String>();
		for (int i = 0; i < current.size(); i++) {
			if (styles.length > i) {
				if (styles[i].getTypes().size() > current.get(i))
					s.add(styles[i].getTypes().get(current.get(i)));
			}
		}

		return s;
	}

	@Override
	public ResourceLocation getItemModelLocation() {
		// TODO Auto-generated method stub
		return itemRendererLocation;
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

	@Override
	protected MapCodec<? extends DirectionalBlock> codec() {
		return null;
	}

	@Override
	protected FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
		return !state.getValue(FULL_SLAB) ? SimpleWaterloggedBlock.super.placeLiquid(level, pos, state, fluidState)
				: false;
	}

	@Override
	public boolean canPlaceLiquid(@Nullable Player player, BlockGetter level, BlockPos pos, BlockState state,
			Fluid fluid) {
		return !state.getValue(FULL_SLAB)
				? SimpleWaterloggedBlock.super.canPlaceLiquid(player, level, pos, state, fluid)
				: false;
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {

		if (state.getValue(FULL_SLAB))
			return Shapes.block();

		Direction facing = state.getValue(FACING);

		switch (facing) {
		case UP:
			return TOP_AABB;
		case NORTH:
			return NORTH_AABB;
		case SOUTH:
			return SOUTH_AABB;
		case EAST:
			return EAST_AABB;
		case WEST:
			return WEST_AABB;
		case DOWN:
		default:
			return BOTTOM_AABB;
		}
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockPos blockpos = context.getClickedPos();
		BlockState blockstate = context.getLevel().getBlockState(blockpos);

		if (blockstate.is(this)) {
			return blockstate.setValue(FULL_SLAB, true).setValue(WATERLOGGED, Boolean.valueOf(false));
		} else {
			BlockPos clickPos = context.getClickedPos();
			FluidState fluidstate = context.getLevel().getFluidState(blockpos);
			BlockState blockstate1 = this.defaultBlockState().setValue(WATERLOGGED,
					Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
			Direction direction = context.getClickedFace();

			double x = context.getClickLocation().x - blockpos.getX() - 0.5;
			double z = context.getClickLocation().z - blockpos.getZ() - 0.5;

			if (direction == Direction.DOWN || direction == Direction.UP) {
				double fX = Math.abs(x);
				double fZ = Math.abs(z);
				if (fX > fZ) {
					if (x > 0)
						return blockstate1.setValue(FACING, Direction.EAST);
					else
						return blockstate1.setValue(FACING, Direction.WEST);
				} else {
					if (z > 0)
						return blockstate1.setValue(FACING, Direction.SOUTH);
					else
						return blockstate1.setValue(FACING, Direction.NORTH);
				}

			} else {
				if (context.getClickLocation().y - (double) blockpos.getY() > 0.5) {
					return blockstate1.setValue(FACING, Direction.UP);
				} else
					return blockstate1.setValue(FACING, Direction.DOWN);
			}

//			return blockstate1;
//			Direction direction = context.getClickedFace();
//			return direction != Direction.DOWN
//					&& (direction == Direction.UP || 
//							: blockstate1.setValue(TYPE, SlabType.TOP);
		}
	}

	@Override
	protected boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
		ItemStack itemstack = useContext.getItemInHand();
		Boolean slabtype = state.getValue(FULL_SLAB);
		if (slabtype || !itemstack.is(this.asItem()) || useContext.getPlayer().isShiftKeyDown()) {
			return false;
		}
		return true;

	}
}
