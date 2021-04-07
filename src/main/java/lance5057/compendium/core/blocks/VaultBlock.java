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
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.BlockItemUseContext;
//import net.minecraft.state.DirectionProperty;
//import net.minecraft.state.StateContainer;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.ActionResultType;
//import net.minecraft.util.Direction;
//import net.minecraft.util.Hand;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.BlockRayTraceResult;
//import net.minecraft.world.IBlockReader;
//import net.minecraft.world.World;
//import net.minecraftforge.common.ToolType;
//import net.minecraftforge.items.CapabilityItemHandler;
//
//public class VaultBlock extends Block {
//    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
//
//    public VaultBlock() {
//	super(Block.Properties.create(Material.IRON).harvestLevel(0).hardnessAndResistance(3, 4)
//		.harvestTool(ToolType.PICKAXE).notSolid());
//	this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
//    }
//
//    @Override
//    public boolean hasTileEntity(BlockState state) {
//	return true;
//    }
//
//    @Nullable
//    @Override
//    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
//	return new VaultTileEntity();
//    }
//
//    @Override
//    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
//	builder.add(FACING);
//    }
//
//    @Nonnull
//    @Override
//    public ActionResultType onBlockActivated(@Nonnull BlockState blockState, World world, @Nonnull BlockPos blockPos,
//	    @Nonnull PlayerEntity playerEntity, @Nonnull Hand hand, @Nonnull BlockRayTraceResult blockRayTraceResult) {
//
//	TileEntity entity = world.getTileEntity(blockPos);
//	if (entity instanceof VaultTileEntity) {
//
//	    VaultTileEntity te = ((VaultTileEntity) entity);
//
//	    if (playerEntity.getHeldItem(hand).isEmpty()) {
//		for (int i = 0; i < playerEntity.inventory.getSizeInventory(); i++) {
//		    te.insertItem(playerEntity.inventory.getStackInSlot(i));
//		}
//	    } else
//		te.insertItem(playerEntity.getHeldItem(hand));
//
//	    return ActionResultType.SUCCESS;
//	}
//
//	return super.onBlockActivated(blockState, world, blockPos, playerEntity, hand, blockRayTraceResult);
//
//    }
//
//    @Override
//    public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
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
//    public void onReplaced(BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos, BlockState newState,
//	    boolean isMoving) {
//	if (state.getBlock() != newState.getBlock()) {
//	    TileEntity tileentity = worldIn.getTileEntity(pos);
//	    if (tileentity instanceof VaultTileEntity) {
//		tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
//			.ifPresent(itemHandler -> IntStream.range(0, itemHandler.getSlots())
//				.forEach(i -> Block.spawnAsEntity(worldIn, pos, itemHandler.getStackInSlot(i))));
//
//		worldIn.updateComparatorOutputLevel(pos, this);
//	    }
//
//	    super.onReplaced(state, worldIn, pos, newState, isMoving);
//	}
//    }
//
//    @Override
//    public BlockState getStateForPlacement(BlockItemUseContext context) {
//	BlockState blockstate = this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
//	return blockstate;
//    }
//}
