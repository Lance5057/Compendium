package com.lance5057.compendium.blocks.bed;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumBlocks;
import com.lance5057.compendium.CompendiumItems;
import com.lance5057.compendium.blocks.entities.StyledMultiMaterialBlockEntity;
import com.lance5057.compendium.multimaterial.MultiMaterialType;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.styleblock.IStyleBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;

public class FancyBedBlock extends BedBlock implements IStyleBlock {
	public static final EnumProperty<BedSideType> SIDE = EnumProperty.create("type", BedSideType.class);

	public FancyBedBlock(Properties properties) {
		super(DyeColor.BLACK, properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(PART, BedPart.FOOT)
				.setValue(OCCUPIED, Boolean.valueOf(false)).setValue(SIDE, BedSideType.SINGLE));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new StyledMultiMaterialBlockEntity(pos, state, 6, 6, StyleData.BED_FRAME, StyleData.BED_BASE,
				StyleData.BED_MATTRESS, StyleData.BED_PILLOW, StyleData.BED_SHEET, StyleData.BED_BLANKET);
	}

	@Override
	public StyleData[] getStyleData() {
		return new StyleData[] { StyleData.BED_FRAME, StyleData.BED_BASE, StyleData.BED_MATTRESS, StyleData.BED_PILLOW,
				StyleData.BED_SHEET, StyleData.BED_BLANKET };
	}

	@Override
	protected RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
			Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (stack.is(CompendiumItems.COSMETIC_TOOLBOX))
			return ItemInteractionResult.FAIL;
		return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer,
			ItemStack stack) {
//		super.setPlacedBy(level, pos, state, placer, stack);
		if (!level.isClientSide) {
			BlockPos blockpos = pos.relative(state.getValue(FACING));
			level.setBlock(blockpos, state.setValue(PART, BedPart.HEAD), 3);

			BlockEntity head = level.getBlockEntity(blockpos);
			BlockEntity foot = level.getBlockEntity(pos);

			if (head != null && foot != null) {
				if (head instanceof StyledMultiMaterialBlockEntity heade
						&& foot instanceof StyledMultiMaterialBlockEntity feete) {
					heade.setCurrentStyles(feete.getCurrentAll());
					heade.setMaterials(feete.getMaterials());
				}
			}

			level.blockUpdated(pos, CompendiumBlocks.FANCY_BED.get());
			state.updateNeighbourShapes(level, pos, 3);
		}
	}

	@Override
	public void onStyleChanged(Level level, BlockPos pos, BlockState state) {
		if (!level.isClientSide) {
			if (state.getValue(PART) == BedPart.HEAD) {
				BlockPos blockpos = pos.relative(state.getValue(FACING).getOpposite());
//				level.setBlock(blockpos, state.setValue(PART, BedPart.HEAD), 3);

				BlockEntity head = level.getBlockEntity(pos);
				BlockEntity foot = level.getBlockEntity(blockpos);

				if (head != null && foot != null) {
					if (head instanceof StyledMultiMaterialBlockEntity heade
							&& foot instanceof StyledMultiMaterialBlockEntity feete) {
						feete.setCurrentStyles(heade.getCurrentAll());
					}
				}

				level.blockUpdated(blockpos, Blocks.AIR);
				level.sendBlockUpdated(blockpos, level.getBlockState(blockpos), level.getBlockState(blockpos),
						Block.UPDATE_ALL);
				state.updateNeighbourShapes(level, pos, 3);
			} else {
				BlockPos blockpos = pos.relative(state.getValue(FACING));
//				level.setBlock(blockpos, state.setValue(PART, BedPart.HEAD), 3);

				BlockEntity head = level.getBlockEntity(blockpos);
				BlockEntity foot = level.getBlockEntity(pos);

				if (head != null && foot != null) {
					if (head instanceof StyledMultiMaterialBlockEntity heade
							&& foot instanceof StyledMultiMaterialBlockEntity feete) {
						heade.setCurrentStyles(feete.getCurrentAll());
					}
				}

				level.blockUpdated(blockpos, Blocks.AIR);
				level.sendBlockUpdated(blockpos, level.getBlockState(blockpos), level.getBlockState(blockpos),
						Block.UPDATE_ALL);
				state.updateNeighbourShapes(level, pos, 3);
			}

		}
	}

//	@Override
//	public BlockState getStateForPlacement(BlockPlaceContext context) {
//		BlockState state = super.getStateForPlacement(context);
//		BlockPos pos = context.getClickedPos();
//		Level level = context.getLevel();
//		Direction facing = state.getValue(FACING);
//
//		if (!state.isEmpty())
//			return updateBedShape(state, facing, level, pos);
//		return state;
//	}

	@Override
	protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level,
			BlockPos pos, BlockPos facingPos) {
		state = super.updateShape(state, facing, facingState, level, pos, facingPos);

		if (!state.isEmpty())
			return updateBedShape(state, state.getValue(FACING), level, pos);
		return state;
	}

	private BlockState updateBedShape(BlockState state, Direction facing, LevelAccessor level, BlockPos pos) {

		BedPart p = state.getValue(PART);

		BlockState ls = level.getBlockState(pos.relative(facing.getCounterClockWise()));
		BlockState rs = level.getBlockState(pos.relative(facing.getClockWise()));

		boolean left = ls.is(CompendiumBlocks.FANCY_BED) && ls.getValue(PART) == p
				&& ls.getValue(FACING) == state.getValue(FACING);
		boolean right = rs.is(CompendiumBlocks.FANCY_BED) && rs.getValue(PART) == p
				&& rs.getValue(FACING) == state.getValue(FACING);

		if (left && right)
			state = state.setValue(SIDE, BedSideType.CENTER);
		else if (left)
			state = state.setValue(SIDE, BedSideType.RIGHT);
		else if (right)
			state = state.setValue(SIDE, BedSideType.LEFT);
		else
			state = state.setValue(SIDE, BedSideType.SINGLE);

		return state;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, PART, OCCUPIED, SIDE);
	}

	@Override
	public ResourceLocation getItemModelLocation() {
		return Compendium.modLoc("fancy_bed_inventory");
	}

	@Override
	public String getBaseStyleName(int current) {
		switch (current) {
		case 0:
			return "bed_base";
		case 1:
			return "bed_frame";
		case 2:
			return "bed_mattress";
		case 3:
			return "bed_pillow";
		case 4:
			return "bed_sheet";
		case 5:
			return "bed_blanket";
		}
		return "error";
	}

	public BlockPos buildCommand(BlockPos pos, Level level, BlockItem bi, IStyleBlock sb, List<MultiMaterialType> mm,
			List<Integer> styles, int style_index, int style, List<String> mats, int material_index) {
		BlockPos headPos = new BlockPos(pos.getX() + (style_index * 2), pos.getY() + (material_index * 2),
				pos.getZ() + (style * 2));
		BlockPos footPos = new BlockPos(pos.getX() + (style_index * 2), pos.getY() + (material_index * 2),
				pos.getZ() + (style * 2) + 1);

		build(pos, level, bi, sb, mm, styles, style_index, style, mats, material_index,
				bi.getBlock().defaultBlockState().setValue(FancyBedBlock.PART, BedPart.HEAD), headPos);
		build(pos, level, bi, sb, mm, styles, style_index, style, mats, material_index,
				bi.getBlock().defaultBlockState().setValue(FancyBedBlock.PART, BedPart.FOOT), footPos);
		return footPos;

	}

	private void build(BlockPos pos, Level level, BlockItem bi, IStyleBlock sb, List<MultiMaterialType> mm,
			List<Integer> styles, int style_index, int style, List<String> mats, int material_index,
			BlockState blockState, BlockPos nPos) {
		level.setBlock(nPos, blockState, Block.UPDATE_ALL);
		StyledMultiMaterialBlockEntity bentity = (StyledMultiMaterialBlockEntity) level.getBlockEntity(nPos);

		List<Integer> newStyles = new ArrayList<Integer>(styles);
		newStyles.set(style_index, style);
		bentity.setMaterials(mm);
		bentity.setMaterial(style_index, mats.get(material_index));
		bentity.setCurrentStyles(newStyles);

		BlockState state = level.getBlockState(nPos);
		sb.onStyleChanged(level, pos, state);
		level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);
	}
}
