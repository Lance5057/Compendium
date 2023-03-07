package lance5057.compendium.core.workstations.blocks;

import java.util.stream.IntStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lance5057.compendium.core.workstations.tileentities.WorkstationTE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.network.NetworkHooks;

public class WorkstationBlock extends Block implements EntityBlock, SimpleWaterloggedBlock {

	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;

	public WorkstationBlock() {
		super(Block.Properties.of(Material.STONE).strength(3, 4).noOcclusion());
		this.registerDefaultState(
				this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(HALF, Half.BOTTOM));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED, HALF);
	}

	@SuppressWarnings("deprecation")
	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return !state.getValue(WATERLOGGED);
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player,
			InteractionHand InteractionHandIn, BlockHitResult hit) {
		if (worldIn.isClientSide)
			return InteractionResult.SUCCESS; // on client side, don't do anything

		if (state.getValue(HALF) != Half.TOP) {
			pos = pos.relative(state.getValue(FACING));
			state = worldIn.getBlockState(pos);
			if (!state.is(this)) {
				return InteractionResult.CONSUME;
			}
		}

		BlockEntity entity = worldIn.getBlockEntity(pos);
		if (entity instanceof WorkstationTE) {

			WorkstationTE te = ((WorkstationTE) entity);
			ItemStack heldmain = player.getItemInHand(InteractionHand.MAIN_HAND);

			if (heldmain != ItemStack.EMPTY) {
				te.hammer(player, heldmain);
			} else {
				if (!(player instanceof ServerPlayer))
					return InteractionResult.FAIL;
				ServerPlayer serverPlayer = (ServerPlayer) player;
				NetworkHooks.openScreen(serverPlayer, te, pos);
			}
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void onRemove(BlockState state, @Nonnull Level worldIn, @Nonnull BlockPos pos, BlockState newState,
			boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof WorkstationTE) {
				tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(
						itemInteractionHandler -> IntStream.range(0, itemInteractionHandler.getSlots()).forEach(
								i -> Block.popResource(worldIn, pos, itemInteractionHandler.getStackInSlot(i))));

				worldIn.updateNeighbourForOutputSignal(pos, this);
			}

			super.onRemove(state, worldIn, pos, newState, isMoving);
		}
	}

	public void setPlacedBy(Level p_49499_, BlockPos p_49500_, BlockState p_49501_, @Nullable LivingEntity p_49502_,
			ItemStack p_49503_) {
		super.setPlacedBy(p_49499_, p_49500_, p_49501_, p_49502_, p_49503_);
		if (!p_49499_.isClientSide) {
			BlockPos blockpos = p_49500_.relative(p_49501_.getValue(FACING));
			p_49499_.setBlock(blockpos, p_49501_.setValue(HALF, Half.TOP), 3);
			p_49499_.blockUpdated(p_49500_, Blocks.AIR);
			p_49501_.updateNeighbourShapes(p_49499_, p_49500_, 3);
		}

	}

	@Override
	@Nullable
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new WorkstationTE(pPos, pState);
	}

	private static Direction getNeighbourDirection(Half p_49534_, Direction p_49535_) {
		return p_49534_ == Half.BOTTOM ? p_49535_ : p_49535_.getOpposite();
	}

	public void playerWillDestroy(Level p_49505_, BlockPos p_49506_, BlockState p_49507_, Player p_49508_) {
		if (!p_49505_.isClientSide && p_49508_.isCreative()) {
			Half bedpart = p_49507_.getValue(HALF);
			if (bedpart == Half.BOTTOM) {
				BlockPos blockpos = p_49506_.relative(getNeighbourDirection(bedpart, p_49507_.getValue(FACING)));
				BlockState blockstate = p_49505_.getBlockState(blockpos);
				if (blockstate.is(this) && blockstate.getValue(HALF) == Half.TOP) {
					p_49505_.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 35);
					p_49505_.levelEvent(p_49508_, 2001, blockpos, Block.getId(blockstate));
				}
			}
		}

		super.playerWillDestroy(p_49505_, p_49506_, p_49507_, p_49508_);
	}

	public BlockState updateShape(BlockState p_49525_, Direction p_49526_, BlockState p_49527_, LevelAccessor p_49528_,
			BlockPos p_49529_, BlockPos p_49530_) {
		if (p_49526_ == getNeighbourDirection(p_49525_.getValue(HALF), p_49525_.getValue(FACING))) {
			return p_49527_.is(this) && p_49527_.getValue(HALF) != p_49525_.getValue(HALF) ? p_49525_
					: Blocks.AIR.defaultBlockState();
		} else {
			return super.updateShape(p_49525_, p_49526_, p_49527_, p_49528_, p_49529_, p_49530_);
		}
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext p_49479_) {
		Direction direction = p_49479_.getHorizontalDirection().getClockWise();
		BlockPos blockpos = p_49479_.getClickedPos();
		BlockPos blockpos1 = blockpos.relative(direction);
		Level level = p_49479_.getLevel();
		return level.getBlockState(blockpos1).canBeReplaced(p_49479_)
				&& level.getWorldBorder().isWithinBounds(blockpos1)
						? this.defaultBlockState().setValue(FACING, direction).setValue(WATERLOGGED, false)
						: null;
	}

}
