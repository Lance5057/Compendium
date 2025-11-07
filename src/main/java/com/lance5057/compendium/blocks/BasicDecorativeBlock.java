package com.lance5057.compendium.blocks;

import java.util.List;

import com.lance5057.compendium.blocks.entities.StyledMultiMaterialBlockEntity;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.styleblock.IStyleBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BasicDecorativeBlock extends Block implements EntityBlock, IStyleBlock {
	int materials = 0;
	int styles = 0;
	
	final ResourceLocation itemRendererLocation;

	public BasicDecorativeBlock(Properties properties, int materials, int styles, ResourceLocation itemRendererLocation) {
		
		super(properties);
		this.materials = materials;
		this.styles = styles;
		this.itemRendererLocation = itemRendererLocation;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new StyledMultiMaterialBlockEntity(pos, state, styles, materials, StyleData.WINDOW_GLASS,
				StyleData.WINDOW_TRIM);
	}

	@Override
	public List<String> getStyles(List<Integer> current) {
		return List.of(StyleData.WINDOW_TRIM.getTypes().get(current.get(0)));
	}

	@Override
	public ResourceLocation getItemModelLocation() {
		// TODO Auto-generated method stub
		return itemRendererLocation;
	}
}
