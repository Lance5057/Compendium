//package lance5057.compendium.core.blocks;
//
//import java.util.stream.IntStream;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//import lance5057.compendium.core.tileentities.ItemDisplayTileEntity;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.HorizontalBlock;
//import net.minecraft.block.SimpleWaterloggedBlock;
//import net.minecraft.block.material.Material;
//import net.minecraft.entity.player.Player;
//import net.minecraft.fluid.FluidState;
//import net.minecraft.fluid.Fluids;
//import net.minecraft.item.BlockPlaceContext;
//import net.minecraft.state.BooleanProperty;
//import net.minecraft.state.DirectionProperty;
//import net.minecraft.state.StateDefinition;
//import net.minecraft.state.properties.BlockStateProperties;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.InteractionResultHolder;
//import net.minecraft.util.Direction;
//import net.minecraft.util.InteractionHand;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.BlockHitResult;
//import net.minecraft.world.BlockGetter;
//import net.minecraft.world.Level;
//import net.minecraftforge.common.ToolType;
//import net.minecraftforge.items.CapabilityItemHandler;
//
//public class ItemDisplayBlock extends Block implements SimpleWaterloggedBlock {
//    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
//    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
//    public static final BooleanProperty LIGHT = BlockStateProperties.LIT;
//
//    public ItemDisplayBlock() {
//	super(Block.Properties.create(Material.METAL).harvestLevel(0).strength(3, 4)
//		.harvestTool(ToolType.PICKAXE).notSolid().setLightLevel((state) -> {
//		    if (state.getValue(LIGHT).booleanValue())
//			return 8;
//		    else
//			return 0;
//		}));
//	this.setDefaultState(this.StateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIGHT, false)
//		.setValue(WATERLOGGED, false));
//    }
//
//    @Override
//    public boolean hasTileEntity(BlockState state) {
//	return true;
//    }
//
//    @SuppressWarnings("deprecation")
//    @Override
//    public FluidState getFluidState(BlockState state) {
//	return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
//    }
//
//    @Override
//    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
//	return !state.getValue(WATERLOGGED);
//    }
//
//    @Nullable
//    @Override
//    public TileEntity createTileEntity(BlockState state, BlockGetter world) {
//	return new ItemDisplayTileEntity();
//    }
//
//    @Override
//    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
//	builder.add(FACING, WATERLOGGED, LIGHT);
//    }
//
//    @Nonnull
//    @Override
//    public InteractionResultHolder onBlockActivated(@Nonnull BlockState blockState, Level world, @Nonnull BlockPos blockPos,
//	    @Nonnull Player Player, @Nonnull InteractionHand InteractionHand, @Nonnull BlockHitResult BlockHitResult) {
//
//	TileEntity entity = world.getTileEntity(blockPos);
//	if (entity instanceof ItemDisplayTileEntity) {
//
//	    ItemDisplayTileEntity te = ((ItemDisplayTileEntity) entity);
//
//	    if (Player.getHeldItem(InteractionHand).isEmpty()) {
//		te.extractItem(Player);
//	    } else
//		te.insertItem(Player.getHeldItem(InteractionHand));
//
//	    return InteractionResultHolder.SUCCESS;
//	}
//
//	return super.onBlockActivated(blockState, world, blockPos, Player, InteractionHand, BlockHitResult);
//
//    }
//
////    @Override
////    public void onBlockClicked(BlockState state, Level worldIn, BlockPos pos, Player player) {
////	TileEntity entity = worldIn.getTileEntity(pos);
////	if (entity instanceof ItemDisplayTileEntity) {
////
////	    ItemDisplayTileEntity te = ((ItemDisplayTileEntity) entity);
////
////	    te.extractItem(player);
////	}
////    }
//
//    @Override
//    public void onReplaced(BlockState state, @Nonnull Level worldIn, @Nonnull BlockPos pos, BlockState newState,
//	    boolean isMoving) {
//	if (state.getBlock() != newState.getBlock()) {
//	    TileEntity tileentity = worldIn.getTileEntity(pos);
//	    if (tileentity instanceof ItemDisplayTileEntity) {
//		tileentity.getCapability(CapabilityItemHandler.ITEM_InteractionHandLER_CAPABILITY)
//			.ifPresent(itemInteractionHandler -> IntStream.range(0, itemInteractionHandler.getSlots())
//				.forEach(i -> Block.spawnAsEntity(worldIn, pos, itemInteractionHandler.getStackInSlot(i))));
//
//		worldIn.updateComparatorOutputLevel(pos, this);
//	    }
//
//	    super.onReplaced(state, worldIn, pos, newState, isMoving);
//	}
//    }
//
//    @Override
//    public BlockState getStateForPlacement(BlockPlaceContext context) {
//	FluidState ifluidstate = context.getLevel().getFluidState(context.getPos());
//
//	BlockState blockstate = this.defaultBlockState()
//		.setValue(FACING, context.getHorizontalDirection().getOpposite())
//		.setValue(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
//	return blockstate;
//    }
//
//}
