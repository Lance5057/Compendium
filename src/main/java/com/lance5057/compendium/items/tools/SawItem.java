package com.lance5057.compendium.items.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableMap.Builder;
import com.lance5057.compendium.CompendiumTags;
import com.lance5057.compendium.items.HandedAbilityTool;
import com.mojang.blaze3d.vertex.VertexSorting.DistanceFunction;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class SawItem extends HandedAbilityTool {
	// Use eviction by time to remove old entries.
//	private static final Cache<BlockPos, List<Vec3>> cache;

	protected static final Map<Block, Block> STRIPPABLES = new Builder<Block, Block>()
			.put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD).put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG)
			.put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD)
			.put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG).put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD)
			.put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG).put(Blocks.CHERRY_WOOD, Blocks.STRIPPED_CHERRY_WOOD)
			.put(Blocks.CHERRY_LOG, Blocks.STRIPPED_CHERRY_LOG).put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD)
			.put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG).put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD)
			.put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG).put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD)
			.put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG).put(Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM)
			.put(Blocks.WARPED_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE)
			.put(Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM)
			.put(Blocks.CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_HYPHAE)
			.put(Blocks.MANGROVE_WOOD, Blocks.STRIPPED_MANGROVE_WOOD)
			.put(Blocks.MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG)
			.put(Blocks.BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK).build();

	public SawItem(Tier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
		super(attackDamageIn, attackSpeedIn, tier, CompendiumTags.SAWABLE, builder);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected InteractionResult mainInteractionHandAbility(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		Player player = context.getPlayer();
		Optional<BlockState> optional = this.evaluateNewBlockState(level, blockpos, player,
				level.getBlockState(blockpos), context);
		if (optional.isEmpty()) {
			return InteractionResult.PASS;
		} else {
			ItemStack itemstack = context.getItemInHand();
			if (player instanceof ServerPlayer) {
				CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockpos, itemstack);
			}

			level.setBlock(blockpos, optional.get(), 11);
			level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, optional.get()));
			if (player != null) {
				itemstack.hurtAndBreak(1, player, p_150686_ -> p_150686_.broadcastBreakEvent(context.getHand()));
			}

			return InteractionResult.sidedSuccess(level.isClientSide);
		}
	}

	private Optional<BlockState> evaluateNewBlockState(Level p_308922_, BlockPos p_308899_, @Nullable Player p_309192_,
			BlockState p_308900_, UseOnContext p_40529_) {
		Optional<BlockState> optional = Optional.ofNullable(
				p_308900_.getToolModifiedState(p_40529_, net.neoforged.neoforge.common.ToolActions.AXE_STRIP, false));
		if (optional.isPresent()) {
			p_308922_.playSound(p_309192_, p_308899_, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
			return optional;
		} else {
			Optional<BlockState> optional1 = Optional.ofNullable(p_308900_.getToolModifiedState(p_40529_,
					net.neoforged.neoforge.common.ToolActions.AXE_SCRAPE, false));
			if (optional1.isPresent()) {
				p_308922_.playSound(p_309192_, p_308899_, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
				p_308922_.levelEvent(p_309192_, 3005, p_308899_, 0);
				return optional1;
			} else {
				Optional<BlockState> optional2 = Optional.ofNullable(p_308900_.getToolModifiedState(p_40529_,
						net.neoforged.neoforge.common.ToolActions.AXE_WAX_OFF, false));
				if (optional2.isPresent()) {
					p_308922_.playSound(p_309192_, p_308899_, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
					p_308922_.levelEvent(p_309192_, 3004, p_308899_, 0);
					return optional2;
				} else {
					return Optional.empty();
				}
			}
		}
	}

	@org.jetbrains.annotations.Nullable
	public static BlockState getAxeStrippingState(BlockState originalState) {
		Block block = STRIPPABLES.get(originalState.getBlock());
		return block != null
				? block.defaultBlockState().setValue(RotatedPillarBlock.AXIS,
						originalState.getValue(RotatedPillarBlock.AXIS))
				: null;
	}

	private Optional<BlockState> getStripped(BlockState pUnstrippedState) {
		return Optional.ofNullable(STRIPPABLES.get(pUnstrippedState.getBlock()))
				.map(p_150689_ -> p_150689_.defaultBlockState().setValue(RotatedPillarBlock.AXIS,
						pUnstrippedState.getValue(RotatedPillarBlock.AXIS)));
	}

	@Override
	public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ToolAction toolAction) {
		return net.neoforged.neoforge.common.ToolActions.DEFAULT_AXE_ACTIONS.contains(toolAction);
	}

	Cache<BlockPos, List<BlockPos>> treeCache = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES)
			.build();

	@Override
	protected InteractionResult offInteractionHandAbility(UseOnContext context) {
		if (!context.getLevel().isClientSide) {
			List<BlockPos> logLocs = treeCache.getIfPresent(context.getClickedPos());

			if (logLocs == null) {
				logLocs = recurseUpTheTree(context.getLevel(), context.getClickedPos());
				treeCache.put(context.getClickedPos(), logLocs);
			}

			// TODO remove this test
			for (BlockPos p : logLocs)
				context.getLevel().setBlock(p, Blocks.WHITE_WOOL.defaultBlockState(), 3);

			context.getPlayer().getCooldowns().addCooldown(this, 20);

			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

	private List<BlockPos> recurseUpTheTree(Level level, BlockPos clickedPos) {
		BlockState b = level.getBlockState(clickedPos);
		List<BlockPos> vecs = recurse(level, b.getBlock(), clickedPos, new ArrayList<BlockPos>(), 0);

		// sort vecs by distance

		return vecs;
	}

	private List<BlockPos> recurse(Level level, Block block, BlockPos cur, List<BlockPos> visited, int dist) {
		List<BlockPos> l = new ArrayList<BlockPos>();

		if (dist < 3 && level.getBlockState(cur).is(block) && !visited.contains(cur)) {

			for (int x = -1; x < 2; x++)
				for (int y = -1; y < 2; y++)
					for (int z = -1; z < 2; z++)
							l.addAll(recurse(level, block, cur.offset(x, y, z), visited, dist + 1));

			l.add(cur);
			visited.add(cur);
		}

		return l;

	}

	protected class VisitedPos {
		BlockPos pos;
		int depth;

		public VisitedPos(BlockPos pos, int depth) {
			this.pos = pos;
			this.depth = depth;
		}
	}

}