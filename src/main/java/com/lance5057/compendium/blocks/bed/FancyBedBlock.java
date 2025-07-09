package com.lance5057.compendium.blocks.bed;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;

public class FancyBedBlock extends BedBlock {

	public FancyBedBlock(Properties properties) {
		super(DyeColor.BLACK, properties);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new FancyBedBlockEntity(pos, state);
	}

	@Override
	protected RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer,
			ItemStack stack) {
		super.setPlacedBy(level, pos, state, placer, stack);
		if (!level.isClientSide) {
			BlockPos blockpos = pos.relative(state.getValue(FACING));
			level.setBlock(blockpos, state.setValue(PART, BedPart.HEAD), 3);

			BlockEntity head = level.getBlockEntity(blockpos);
			BlockEntity foot = level.getBlockEntity(pos);

			if (head != null && foot != null) {
				if (head instanceof FancyBedBlockEntity heade && foot instanceof FancyBedBlockEntity feete) {
					heade.currentStyles = feete.currentStyles;
					heade.setMaterials(feete.getMaterials());
				}
			}

			level.blockUpdated(pos, Blocks.AIR);
			state.updateNeighbourShapes(level, pos, 3);
		}
	}
}
