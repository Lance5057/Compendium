package lance5057.compendium.core.workstations.scrappingtable;
//package lance5057.compendium.core.workstations.blocks;
//
//import java.util.stream.IntStream;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//import lance5057.compendium.core.items.tools.HammerItem;
//import lance5057.compendium.core.workstations.tileentities.CraftingAnvilTE;
//import lance5057.compendium.core.workstations.tileentities.ScrappingTableTE;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.InteractionResult;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.context.BlockPlaceContext;
//import net.minecraft.world.level.BlockGetter;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.BaseEntityBlock;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.HorizontalDirectionalBlock;
//import net.minecraft.world.level.block.SimpleWaterloggedBlock;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.block.state.StateDefinition;
//import net.minecraft.world.level.block.state.properties.BlockStateProperties;
//import net.minecraft.world.level.block.state.properties.BooleanProperty;
//import net.minecraft.world.level.block.state.properties.DirectionProperty;
//import net.minecraft.world.level.material.FluidState;
//import net.minecraft.world.level.material.Fluids;
//import net.minecraft.world.level.material.Material;
//import net.minecraft.world.phys.BlockHitResult;
//import net.minecraft.world.phys.shapes.CollisionContext;
//import net.minecraft.world.phys.shapes.VoxelShape;
//import net.minecraftforge.items.CapabilityItemHandler;
//
//public class ScrappingTableBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
//	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
//	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
//	protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);
//
//	public ScrappingTableBlock() {
//		super(Block.Properties.of(Material.STONE).strength(3, 4).noOcclusion());
//	}
//
//	@Override
//	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
//		builder.add(FACING, WATERLOGGED);
//	}
//
//	@SuppressWarnings("deprecation")
//	@Override
//	public FluidState getFluidState(BlockState state) {
//		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
//	}
//
//	@Override
//	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
//		return !state.getValue(WATERLOGGED);
//	}
//
////	@Override
////	public boolean hasBlockEntity(BlockState state) {
////		return true;
////	}
//
//	@Nullable
//	@Override
//	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
//		return new ScrappingTableTE();
//	}
//
//	@Override
//	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
//		return SHAPE;
//	}
//
//	@Nonnull
//	@Override
//	public InteractionResult use(@Nonnull BlockState blockState, Level world,
//			@Nonnull BlockPos blockPos, @Nonnull Player Player, @Nonnull InteractionHand interactionHand,
//			@Nonnull BlockHitResult BlockHitResult) {
//		if (interactionHand == InteractionHand.MAIN_HAND) {
//			BlockEntity entity = world.getBlockEntity(blockPos);
//			if (entity instanceof ScrappingTableTE) {
//
//				ScrappingTableTE te = ((ScrappingTableTE) entity);
//				if (!Player.isCrouching()) {
//					boolean success = false;
//					// Get item in both InteractionHands, ignore sent InteractionHand
//					ItemStack heldmain = Player.getItemInHand(InteractionHand.MAIN_HAND);
//					ItemStack heldoff = Player.getItemInHand(InteractionHand.OFF_HAND);
//
//					// Try inserting main InteractionHand item
//					if (!(heldmain.getItem() instanceof HammerItem)) {
//						te.insertItem(heldmain);
//						success = true;
//					}
//					// Try inserting off InteractionHand item
//					if (!(heldoff.getItem() instanceof HammerItem)) {
//						te.insertItem(heldoff);
//						success = true;
//					}
//
//					// Hit it!
//					// Try main InteractionHand, only try off InteractionHand if that fails
//					if (heldmain.getItem() instanceof HammerItem) {
//						te.hammer(Player, heldmain);
//						success = true;
//					} else if (heldoff.getItem() instanceof HammerItem) {
//						te.hammer(Player, heldoff);
//						success = true;
//					}
//
//					if (success)
//						return InteractionResult.SUCCESS;
//				} else // If crouching, take item off table
//				{
//					te.extractItem(Player);
//					return InteractionResult.SUCCESS;
//				}
//			}
//		}
//		return super.use(blockState, world, blockPos, Player, interactionHand, BlockHitResult);
//
//	}
//
//	@Override
//	public void onRemove(BlockState state, @Nonnull Level worldIn, @Nonnull BlockPos pos, BlockState newState,
//			boolean isMoving) {
//		if (state.getBlock() != newState.getBlock()) {
//			BlockEntity tileentity = worldIn.getBlockEntity(pos);
//			if (tileentity instanceof CraftingAnvilTE) {
//				tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(
//						itemInteractionHandler -> IntStream.range(0, itemInteractionHandler.getSlots()).forEach(
//								i -> Block.popResource(worldIn, pos, itemInteractionHandler.getStackInSlot(i))));
//
//				worldIn.updateNeighbourForOutputSignal(pos, this);
//			}
//
//			super.onRemove(state, worldIn, pos, newState, isMoving);
//		}
//	}
//
//	@Override
//	public BlockState getStateForPlacement(BlockPlaceContext context) {
//		FluidState ifluidstate = context.getLevel().getFluidState(context.getClickedPos());
//
//		BlockState blockstate = this.defaultBlockState()
//				.setValue(FACING, context.getHorizontalDirection().getOpposite())
//				.setValue(WATERLOGGED, Boolean.valueOf(ifluidstate.getType() == Fluids.WATER));
//		return blockstate;
//	}
//}
