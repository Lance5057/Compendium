package com.lance5057.compendium.items.tools.api;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

/**
 * Thanks SilentChaos512
 * https://mit-license.org/
 */

@EventBusSubscriber
public class AOEEventHandler {
	@SubscribeEvent
	public static void onBreakSpeedEvent(PlayerEvent.BreakSpeed event) {
		if (event.isCanceled())
			return;
		if (event.getPosition().isEmpty())
			return;

		Player player = event.getEntity();
		Level level = player.level();
		ItemStack tool = player.getMainHandItem();
		BlockPos pos = event.getPosition().get();
		BlockState block = event.getState();

		if (!tool.getItem().isCorrectToolForDrops(tool, block))
			return;

		if (tool.getItem() instanceof IAOETool aoe) {

			BlockHitResult hitResult = Item.getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);

			List<BlockPos> blocks = aoe.getAllBlocks(level, hitResult, player, tool);

			if (blocks != null && blocks.isEmpty()) {
				float hardness = block.getDestroySpeed(level, pos);
				for (BlockPos b : blocks) {
					BlockState state = level.getBlockState(b);
					float h = state.getDestroySpeed(level, pos);
					if (h > hardness)
						hardness = h;
				}

				event.setNewSpeed(event.getNewSpeed() * hardness);

			}
		}
	}

	@SubscribeEvent
	public static void onBlockBreakEvent(BlockEvent.BreakEvent event) {
		Player player = event.getPlayer();
		Level level = player.getCommandSenderWorld();
		ItemStack tool = player.getMainHandItem();

		BlockState state = level.getBlockState(event.getPos());

		if (!(player instanceof ServerPlayer))
			return;
		if (level.isClientSide())
			return;

		if (tool.getItem() instanceof IAOETool aoe) {
			BlockHitResult hit = Item.getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);

			if (hit != null) {
				if (hit.getType() == HitResult.Type.BLOCK && tool.getItem().isCorrectToolForDrops(tool, state)) {
					List<BlockPos> blocks = aoe.getAllBlocks(level, hit, player, tool);

					for (BlockPos b : blocks) {
						BlockState nextState = level.getBlockState(b);
						if (player.mayUseItemAt(b, hit.getDirection(), tool)
								&& nextState.canHarvestBlock(level, b, player)) {

							// Creative Mode
							if (player.getAbilities().instabuild) {
								if (nextState.onDestroyedByPlayer(level, b, player, false, nextState.getFluidState())) {
									nextState.getBlock().destroy(level, b, state);
								}
							}
							// No Creative Mode
							else {
								int xp = nextState.getExpDrop(level, b, level.getBlockEntity(b), player, tool);
								tool.getItem().mineBlock(tool, level, state, b, player);

								if (nextState.onDestroyedByPlayer(level, b, player, true, nextState.getFluidState())) {
									nextState.getBlock().destroy(level, b, nextState);
									nextState.getBlock().playerDestroy(level, player, b, nextState,
											level.getBlockEntity(b), tool);
									nextState.getBlock().popExperience((ServerLevel) level, b, xp);
								}
							}

							((ServerPlayer) player).connection.send(new ClientboundBlockUpdatePacket(level, b));
						}
					}
				}
			}
		}

	}
}
