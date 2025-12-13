package com.lance5057.compendium.blocks.window;

import com.lance5057.compendium.blocks.BasicDecorativeBlock;
import com.lance5057.compendium.style.StyleData;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WindowBlock extends BasicDecorativeBlock {

	public WindowBlock(Properties properties, int materials, int styles, ResourceLocation itemRendererLocation,
			StyleData... styleData) {
		super(properties, materials, styles, itemRendererLocation, styleData);
	}

	@Override
	protected VoxelShape getVisualShape(BlockState p_309057_, BlockGetter p_308936_, BlockPos p_308956_,
			CollisionContext p_309006_) {
		return Shapes.empty();
	}

	@Override
	protected float getShadeBrightness(BlockState p_308911_, BlockGetter p_308952_, BlockPos p_308918_) {
		return 1.0F;
	}

	@Override
	protected boolean propagatesSkylightDown(BlockState p_309084_, BlockGetter p_309133_, BlockPos p_309097_) {
		return true;
	}
}
