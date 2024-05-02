package com.lance5057.compendium.workstations._bases.blockentities;

import java.util.List;

import com.lance5057.compendium.workstations._bases.components.WorkstationComponent;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class WorkstationBasicBlockEntity extends BlockEntity {

	List<WorkstationComponent> components;
	
	public WorkstationBasicBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
		super(pType, pPos, pBlockState);
	}

}
