package com.lance5057.compendium.items.tools.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public interface IAOETool {
	public int getWidth();

	public int getHeight();

	public int getDepth();

	default List<BlockPos> getAllBlocks(Level level, BlockHitResult hit, Player player, ItemStack stack) {

		Direction dir = hit.getDirection();

		BlockPos pos = hit.getBlockPos();
		List<BlockPos> blocks = new ArrayList<BlockPos>();

		Direction.Axis dirX, dirY, dirZ;
		switch (dir) {
		case Direction.WEST:
		case Direction.EAST:
			dirY = Axis.X;
			dirX = Axis.Y;
			dirZ = Axis.Z;
			break;

		case Direction.NORTH:
		case Direction.SOUTH:
			dirY = Axis.Z;
			dirX = Axis.X;
			dirZ = Axis.Y;
			break;

		case Direction.UP:
		case Direction.DOWN:
		default:
			dirY = Axis.Y;
			dirX = Axis.X;
			dirZ = Axis.Z;
			break;
		}

		for (int x = -getWidth(); x < getWidth() + 1; x++)
			for (int y = -getDepth(); y < getDepth() + 1; y++) {
				for (int z = -getHeight(); z < getHeight() + 1; z++) {
					addBlock(level, pos.relative(dirX, x).relative(dirY, y).relative(dirZ, z), player, stack, blocks);
				}
			}

		return blocks;
	}

	default void addBlock(Level level, BlockPos relative, Player player, ItemStack stack, List<BlockPos> blocks) {
		if (level.isEmptyBlock(relative))
			return;
		if (stack.getItem().isCorrectToolForDrops(stack, level.getBlockState(relative))) {
			blocks.add(relative);
		}
	}
}
