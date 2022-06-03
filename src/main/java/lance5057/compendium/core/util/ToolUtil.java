//package lance5057.compendium.core.util;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.server.level.ServerPlayer;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.enchantment.EnchantmentHelper;
//import net.minecraft.world.item.enchantment.Enchantments;
//import net.minecraft.world.level.ClipContext;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.phys.BlockHitResult;
//import net.minecraft.world.phys.Vec3;

//
//public class ToolUtil {
//	public static void breakInRadius(Level world, Player Player, int radius) {
//		if (!world.isClientSide) {
//			List<BlockPos> brokenBlocks = getBreakBlocks(world, Player, radius);
//			ItemStack heldItem = Player.getMainHandItem();
//			int silktouch = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, heldItem);
//			int fortune = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, heldItem);
//			for (BlockPos pos : brokenBlocks) {
//				BlockState state = world.getBlockState(pos);
//				if (heldItem.getItem().isCorrectToolForDrops(state)) {
//					if (Player.getAbilities().instabuild) {
//						if (state.onDestroyedByPlayer(world, pos, Player, true, state.getFluidState()))
//							state.getBlock().destroy(world, pos, state);
//					} else {
//						heldItem.getItem().mineBlock(heldItem, world, state, pos, Player);
//						BlockEntity tileEntity = world.getBlockEntity(pos);
//						state.getBlock().destroy(world, pos, state);
//						state.getBlock().playerDestroy(world, Player, pos, state, tileEntity, heldItem);
//						state.getBlock().popExperience((ServerLevel) world, pos,
//								state.getBlock().getExpDrop(state, world, pos, fortune, silktouch));
//					}
//					world.removeBlock(pos, false);
//					world.levelEvent(2001, pos, Block.getId(state));
//					((ServerPlayer) Player).connection.send(new ClientboundBlockUpdatePacket(world, pos));
//				}
//			}
//		}
//	}
//
//	public static List<BlockPos> getBreakBlocks(Level world, Player player, int radius) {
//		ArrayList<BlockPos> potentialBrokenBlocks = new ArrayList<>();
//
//		Vec3 eyePosition = player.getEyePosition(1);
//		Vec3 rotation = player.getEyePosition(1);
//		Vec3 combined = eyePosition.add(rotation.x * 5, rotation.y * 5, rotation.z * 5);
//
//		BlockHitResult rayTraceResult = world.clip(
//				new ClipContext(eyePosition, combined, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));
//
//		if (rayTraceResult.getType() == BlockHitResult.Type.BLOCK) {
//			Direction.Axis axis = rayTraceResult.getDirection().getAxis();
//			ArrayList<BlockPos> positions = new ArrayList<>();
//
//			for (int x = -radius; x <= radius; x++) {
//				for (int y = -radius; y <= radius; y++) {
//					for (int z = -radius; z <= radius; z++) {
//						positions.add(new BlockPos(x, y, z));
//					}
//				}
//			}
//
//			BlockPos origin = rayTraceResult.getBlockPos();
//
//			for (BlockPos pos : positions) {
//				if (axis == Direction.Axis.Y) {
//					if (pos.getY() == 0) {
//						potentialBrokenBlocks.add(origin.offset(pos));
//					}
//				} else if (axis == Direction.Axis.X) {
//					if (pos.getX() == 0) {
//						potentialBrokenBlocks.add(origin.offset(pos));
//					}
//				} else if (axis == Direction.Axis.Z) {
//					if (pos.getZ() == 0) {
//						potentialBrokenBlocks.add(origin.offset(pos));
//					}
//				}
//			}
//			potentialBrokenBlocks.remove(origin);
//		}
//
//		return potentialBrokenBlocks;
//	}
//}
