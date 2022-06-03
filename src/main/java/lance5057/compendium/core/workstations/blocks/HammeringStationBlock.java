//package lance5057.compendium.core.workstations.blocks;
//
//import java.util.stream.IntStream;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//import lance5057.compendium.core.items.tools.HammerItem;
//import lance5057.compendium.core.workstations.tileentities.HammeringStationTE;
//import net.minecraft.core.BlockPos;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.InteractionResult;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.BlockGetter;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.material.Material;
//import net.minecraft.world.phys.BlockHitResult;
//import net.minecraft.world.phys.shapes.CollisionContext;
//import net.minecraft.world.phys.shapes.VoxelShape;
//import net.minecraftforge.common.ToolType;
//import net.minecraftforge.items.CapabilityItemHandler;
//
//public class HammeringStationBlock extends Block {
//	protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D);
//
//	public HammeringStationBlock() {
//		super(Block.Properties.create(Material.STONE).harvestLevel(0).strength(3, 4)
//				.harvestTool(ToolType.PICKAXE).notSolid());
//	}
//
//	@Override
//	public boolean hasBlockEntity(BlockState state) {
//		return true;
//	}
//
//	@Nullable
//	@Override
//	public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
//		return new HammeringStationTE();
//	}
//
//	@Override
//	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
//		return SHAPE;
//	}
//
//	@Nonnull
//	@Override
//	public InteractionResult onBlockActivated(@Nonnull BlockState blockState, Level world, @Nonnull BlockPos blockPos,
//			@Nonnull Player Player, @Nonnull InteractionHand InteractionHand, @Nonnull BlockHitResult BlockHitResult) {
//		if (InteractionHand == InteractionHand.MAIN_InteractionHand) {
//			BlockEntity entity = world.getBlockEntity(blockPos);
//			if (entity instanceof HammeringStationTE) {
//
//				HammeringStationTE te = ((HammeringStationTE) entity);
//				if (!Player.isCrouching()) {
//					boolean success = false;
//					// Get item in both InteractionHands, ignore sent InteractionHand
//					ItemStack heldmain = Player.getHeldItem(InteractionHand.MAIN_InteractionHand);
//					ItemStack heldoff = Player.getHeldItem(InteractionHand.OFF_InteractionHand);
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
//		return super.onBlockActivated(blockState, world, blockPos, Player, InteractionHand, BlockHitResult);
//
//	}
//
//	@Override
//	public void onReplaced(BlockState state, @Nonnull Level worldIn, @Nonnull BlockPos pos, BlockState newState,
//			boolean isMoving) {
//		if (state.getBlock() != newState.getBlock()) {
//			TileEntity tileentity = worldIn.getTileEntity(pos);
//			if (tileentity instanceof HammeringStationTE) {
//				tileentity.getCapability(CapabilityItemHandler.ITEM_InteractionHandLER_CAPABILITY)
//						.ifPresent(itemInteractionHandler -> IntStream.range(0, itemInteractionHandler.getSlots())
//								.forEach(i -> Block.spawnAsEntity(worldIn, pos, itemInteractionHandler.getStackInSlot(i))));
//
//				worldIn.updateComparatorOutputLevel(pos, this);
//			}
//
//			super.onReplaced(state, worldIn, pos, newState, isMoving);
//		}
//	}
//}
