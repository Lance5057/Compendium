package com.lance5057.compendium.blocks.door;

import com.lance5057.compendium.blocks.entities.StyledMultiMaterialBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class FancyDoorBlock extends DoorBlock implements EntityBlock {

	public FancyDoorBlock(BlockSetType type, Properties properties) {
		super(type, properties);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		// TODO Auto-generated method stub
		return new StyledMultiMaterialBlockEntity(pos, state, 2, 2);
	}

}
