package com.lance5057.compendium.styleblock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public abstract class StyleBlock extends Block {

	public StyleBlock(Properties properties) {
		super(properties);
	}

	public abstract BlockItemStateProperties getStateProperties(BlockState state);

	public abstract int numStyles();

	public abstract int getCurrentStyle(BlockState state);

	public abstract void setNextStyle(Level level, BlockPos pos, BlockState state);

	public abstract void setPrevStyle(Level level, BlockPos pos, BlockState state);

	public abstract BlockState getState(int i);

	public abstract void setStyle(Level level, BlockPos pos, BlockState state, int style);

	public abstract boolean isPatreonStyle(int style);
	
	public String getStyleFromBlock(BlockItemStateProperties bisp) {
		return "";
	}

	public String getStyleFromBlock(int i) {
		return "";
	}

	@Override
	public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		if (!level.isClientSide && !player.isCreative()
				&& level.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS)) {
			ItemStack itemstack = new ItemStack(this);
			itemstack.set(DataComponents.BLOCK_STATE, getStateProperties(state));
			ItemEntity itementity = new ItemEntity(level, (double) pos.getX(), (double) pos.getY(), (double) pos.getZ(),
					itemstack);
			itementity.setDefaultPickUpDelay();
			level.addFreshEntity(itementity);
		}

		return super.playerWillDestroy(level, pos, state, player);
	}

}
