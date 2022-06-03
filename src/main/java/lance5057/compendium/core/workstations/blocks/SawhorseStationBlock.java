//package lance5057.compendium.core.workstations.blocks;
//
//import java.util.stream.IntStream;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//import lance5057.compendium.core.items.tools.SawItem;
//import lance5057.compendium.core.workstations.tileentities.SawhorseStationTE;
//import net.minecraft.block.HorizontalBlock;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//import net.minecraft.inventory.container.INamedContainerProvider;
//import net.minecraft.inventory.container.RepairContainer;
//import net.minecraft.inventory.container.SimpleNamedContainerProvider;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.LevelAccessorPosCallable;
//import net.minecraft.util.text.ITextComponent;
//import net.minecraft.util.text.TranslationTextComponent;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.InteractionResult;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.context.BlockPlaceContext;
//import net.minecraft.world.level.BlockGetter;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.block.state.StateDefinition;
//import net.minecraft.world.level.block.state.properties.DirectionProperty;
//import net.minecraft.world.level.material.Material;
//import net.minecraft.world.phys.BlockHitResult;
//import net.minecraftforge.common.ToolType;
//import net.minecraftforge.items.CapabilityItemHandler;
//
//public class SawhorseStationBlock extends Block {
//
//	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
//	private static final ITextComponent containerName = new TranslationTextComponent("compendium.container.anvilcraft");
//
//	public SawhorseStationBlock() {
//		super(Block.Properties.create(Material.WOOD).harvestLevel(0).strength(3, 4)
//				.harvestTool(ToolType.AXE).notSolid());
//		this.setDefaultState(this.StateDefinition.any().setValue(FACING, Direction.NORTH));
//	}
//
//	@Override
//	public BlockState getStateForPlacement(BlockPlaceContext context) {
//		BlockState blockstate = this.defaultBlockState().setValue(FACING,
//				context.getHorizontalDirection().getOpposite());
//		return blockstate;
//	}
//
//	@Nullable
//	@Override
//	public INamedContainerProvider getContainer(BlockState state, Level worldIn, BlockPos pos) {
//		return new SimpleNamedContainerProvider((id, inventory, player) -> {
//			return new RepairContainer(id, inventory, LevelAccessorPosCallable.of(worldIn, pos));
//		}, containerName);
//	}
//
//	@Override
//	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
//		builder.add(FACING);
//	}
//
//	@Override
//	public boolean hasTileEntity(BlockState state) {
//		return true;
//	}
//
//	@Nullable
//	@Override
//	public TileEntity createTileEntity(BlockState state, BlockGetter world) {
//		return new SawhorseStationTE();
//	}
//
//	@Nonnull
//	@Override
//	public InteractionResult onBlockActivated(@Nonnull BlockState blockState, Level world, @Nonnull BlockPos blockPos,
//			@Nonnull Player Player, @Nonnull InteractionHand InteractionHand, @Nonnull BlockHitResult BlockHitResult) {
//		if (InteractionHand == InteractionHand.MAIN_InteractionHand) {
//			TileEntity entity = world.getTileEntity(blockPos);
//			if (entity instanceof SawhorseStationTE) {
//
//				SawhorseStationTE te = ((SawhorseStationTE) entity);
//				if (!Player.isCrouching()) {
//					boolean success = false;
//					// Get item in both InteractionHands, ignore sent InteractionHand
//					ItemStack heldmain = Player.getHeldItem(InteractionHand.MAIN_HAND);
//					ItemStack heldoff = Player.getHeldItem(InteractionHand.OFF_HAND);
//
//					// Try inserting main InteractionHand item
//					if (!(heldmain.getItem() instanceof SawItem)) {
//						te.insertItem(heldmain);
//						success = true;
//					}
//					// Try inserting off InteractionHand item
//					if (!(heldoff.getItem() instanceof SawItem)) {
//						te.insertItem(heldoff);
//						success = true;
//					}
//
//					// Hit it!
//					// Try main InteractionHand, only try off InteractionHand if that fails
//					if (heldmain.getItem() instanceof SawItem) {
//						te.hammer(Player, heldmain);
//						success = true;
//					} else if (heldoff.getItem() instanceof SawItem) {
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
//		return super.onBlockActivated(blockState, world, blockPos, Player, InteractionHand, BlockHitResult);
//
//	}
//
//	@Override
//	public void onReplaced(BlockState state, @Nonnull Level worldIn, @Nonnull BlockPos pos, BlockState newState,
//			boolean isMoving) {
//		if (state.getBlock() != newState.getBlock()) {
//			TileEntity tileentity = worldIn.getTileEntity(pos);
//			if (tileentity instanceof SawhorseStationTE) {
//				tileentity.getCapability(CapabilityItemHandler.ITEM_InteractionHandLER_CAPABILITY).ifPresent(
//						itemInteractionHandler -> IntStream.range(0, itemInteractionHandler.getSlots()).forEach(
//								i -> Block.spawnAsEntity(worldIn, pos, itemInteractionHandler.getStackInSlot(i))));
//
//				worldIn.updateComparatorOutputLevel(pos, this);
//			}
//
//			super.onReplaced(state, worldIn, pos, newState, isMoving);
//		}
//	}
//}
