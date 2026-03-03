package com.lance5057.compendium.blocks;

import java.util.List;

import com.lance5057.compendium.blocks.entities.StyledMultiMaterialBlockEntity;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.styleblock.IStyleBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BasicDecorativeBlock extends Block implements EntityBlock, IStyleBlock {
	int materials = 0;
	int styles = 0;

	public final StyleData[] styleData;
	final ResourceLocation itemRendererLocation;
	List<String> styleBases;

	public BasicDecorativeBlock(Properties properties, int materials, int styles, ResourceLocation itemRendererLocation,
			List<String> styleBases, StyleData... styleData) {

		super(properties);
		this.materials = materials;
		this.styles = styles;
		this.itemRendererLocation = itemRendererLocation;
		this.styleData = styleData;
		this.styleBases = styleBases;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new StyledMultiMaterialBlockEntity(pos, state, styles, materials, styleData);
	}

	@Override
	public ResourceLocation getItemModelLocation() {
		return itemRendererLocation;
	}

	@Override
	public void onStyleChanged(Level level, BlockPos pos, BlockState state) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getBaseStyleName(int current) {
		return this.styleBases.get(current);
	}

	@Override
	public StyleData[] getStyleData() {
		return this.styleData;
	}
}
