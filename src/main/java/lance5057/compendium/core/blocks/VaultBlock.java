//package lance5057.compendium.core.blocks;
//
//import java.util.stream.IntStream;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//
//import lance5057.compendium.core.tileentities.VaultTileEntity;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.HorizontalBlock;
//import net.minecraft.block.material.Material;
//import net.minecraft.entity.player.Player;
//import net.minecraft.item.BlockPlaceContext;
//import net.minecraft.state.DirectionProperty;
//import net.minecraft.state.StateDefinition;
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
//public class VaultBlock extends Block {
//    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
//
//    public VaultBlock() {
//	super(Block.Properties.create(Material.METAL).harvestLevel(0).strength(3, 4)
//		.harvestTool(ToolType.PICKAXE).notSolid());
//	this.setDefaultState(this.StateDefinition.any().setValue(FACING, Direction.NORTH));
//    }
//
//    @Override
//    public boolean hasTileEntity(BlockState state) {
//	return true;
//    }
//
//    @Nullable
//    @Override
//    public TileEntity createTileEntity(BlockState state, BlockGetter world) {
//	return new VaultTileEntity();
//    }
//
//    @Override
//    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
//	builder.add(FACING);
//    }
//
//    @Nonnull
//    @Override
//    public InteractionResultHolder onBlockActivated(@Nonnull BlockState blockState, Level world, @Nonnull BlockPos blockPos,
//	    @Nonnull Player Player, @Nonnull InteractionHand InteractionHand, @Nonnull BlockHitResult BlockHitResult) {
//
//	TileEntity entity = world.getTileEntity(blockPos);
//	if (entity instanceof VaultTileEntity) {
//
//	    VaultTileEntity te = ((VaultTileEntity) entity);
//
//	    if (Player.getHeldItem(InteractionHand).isEmpty()) {
//		for (int i = 0; i < Player.inventory.getSizeInventory(); i++) {
//		    te.insertItem(Player.inventory.getStackInSlot(i));
//		}
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
//    @Override
//    public void onBlockClicked(BlockState state, Level worldIn, BlockPos pos, Player player) {
//	TileEntity entity = worldIn.getTileEntity(pos);
//	if (entity instanceof VaultTileEntity) {
//
//	    VaultTileEntity te = ((VaultTileEntity) entity);
//
//	    te.extractItem(player);
//	}
//    }
//
//    @Override
//    public void onReplaced(BlockState state, @Nonnull Level worldIn, @Nonnull BlockPos pos, BlockState newState,
//	    boolean isMoving) {
//	if (state.getBlock() != newState.getBlock()) {
//	    TileEntity tileentity = worldIn.getTileEntity(pos);
//	    if (tileentity instanceof VaultTileEntity) {
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
//	BlockState blockstate = this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
//	return blockstate;
//    }
//}
