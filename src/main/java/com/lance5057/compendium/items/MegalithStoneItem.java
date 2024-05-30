package com.lance5057.compendium.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;

public class MegalithStoneItem extends Item {

	public MegalithStoneItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		if (!context.getLevel().isClientSide()) {
			AABB aabb = new AABB(
					context.getClickedPos().above(1).north(1).east(1).relative(context.getClickedFace(), 2).getCenter(),
					context.getClickedPos().below(1).south(1).west(1).relative(context.getClickedFace(), 2)
							.getCenter());
			boolean invalid = BlockPos.betweenClosedStream(aabb)
					.allMatch(pos -> !context.getLevel().getBlockState(pos).canBeReplaced());

			if (invalid) {
				BlockPos.betweenClosedStream(aabb)
						.forEach(b -> context.getLevel().setBlockAndUpdate(b, Blocks.STONE.defaultBlockState()));
				return InteractionResult.PASS;
			}
		}
		return InteractionResult.FAIL;
	}

}