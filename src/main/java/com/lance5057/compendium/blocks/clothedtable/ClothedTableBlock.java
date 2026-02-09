package com.lance5057.compendium.blocks.clothedtable;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.blocks.entities.StyledMultiMaterialBlockEntity;
import com.lance5057.compendium.blocks.table.TableBase;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.styleblock.IStyleBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ClothedTableBlock extends TableBase implements EntityBlock, IStyleBlock {

	public ClothedTableBlock(Properties properties) {
		super(properties);

	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new StyledMultiMaterialBlockEntity(pos, state, 3, 3, StyleData.TABLE_TOP, StyleData.TABLE_LEGS,
				StyleData.TABLE_CLOTH);
	}

	@Override
	public StyleData[] getStyleData() {
		return new StyleData[] { StyleData.TABLE_TOP, StyleData.TABLE_LEGS, StyleData.TABLE_CLOTH };
	}

	@Override
	public ResourceLocation getItemModelLocation() {
		return Compendium.modLoc("extra/clothed_table");
	}

	@Override
	public void onStyleChanged(Level level, BlockPos pos, BlockState state) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getBaseStyleName(int current) {
		switch (current) {
		case 0:
			return "table_top";
		case 1:
			return "table_legs";
		case 2:
			return "table_cloth";
		}
		return "error";
	}
}
