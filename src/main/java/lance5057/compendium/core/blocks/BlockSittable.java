//package lance5057.compendium.core.blocks;
//
//import lance5057.compendium.core.entities.SeatEntity;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.InteractionResult;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.phys.BlockHitResult;
//
//public class BlockSittable extends Block {
//
//	public BlockSittable(Properties properties) {
//		super(properties);
//	}
//
//	@Override
//	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player Player,
//			InteractionHand InteractionHand, BlockHitResult result) {
//		return SeatEntity.create(world, pos, 0.4, Player);
//	}
//}
