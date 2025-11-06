package com.lance5057.compendium.blocks;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.blocks.entities.SimpleStyleBlockEntity;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.styleblock.IStyleBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SlabStyleBlock extends SlabBlock implements EntityBlock, IStyleBlock {
	public final StyleData[] styles;
	final ResourceLocation itemRendererLocation;

	public SlabStyleBlock(Properties properties, ResourceLocation itemRendererLocation, StyleData... styles) {
		super(properties);
		this.styles = styles;
		this.itemRendererLocation = itemRendererLocation;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SimpleStyleBlockEntity(pos, state, styles.length, styles);
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}
	
	@Override
	public List<String> getStyles(List<Integer> current) {
		List<String> r = new ArrayList<String>();
		for (int i = 0; i < current.size(); i++) {
			if (styles.length > i)
				r.add(this.styles[i].getTypes().get(i));
		}
		return r;
	}

	@Override
	public ResourceLocation getBlockModelLocation() {
		// TODO Auto-generated method stub
		return itemRendererLocation;
	}
}
