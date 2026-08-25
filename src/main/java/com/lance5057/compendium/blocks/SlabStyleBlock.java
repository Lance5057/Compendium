package com.lance5057.compendium.blocks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.lance5057.compendium.blocks.entities.SimpleStyleBlockEntity;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
import com.lance5057.compendium.style.StyleData;
import com.lance5057.compendium.styleblock.IStyleBlock;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class SlabStyleBlock extends DirectionalBlock implements EntityBlock, IStyleBlock, SimpleWaterloggedBlock {
	public static final Codec<SlabStyleBlock> CODEC = RecordCodecBuilder.create(inst -> inst
			.group(BlockBehaviour.Properties.CODEC.fieldOf("properties").forGetter(BlockBehaviour::properties),
					Codec.list(StyleData.CODEC).fieldOf("styles").forGetter(s -> List.of(s.getStyles())),
					ResourceLocation.CODEC.fieldOf("itemRendererLocation")
							.forGetter(SlabStyleBlock::getItemRendererLocation),
					Codec.STRING.fieldOf("matType").forGetter(SlabStyleBlock::getMatType),
					Codec.STRING.fieldOf("materialName").forGetter(SlabStyleBlock::getMaterialName),
					Codec.list(Codec.STRING).fieldOf("styleBases").forGetter(SlabStyleBlock::getStyleBases))
			.apply(inst, SlabStyleBlock::new));

	public void ye() {

	}

	// public SlabStyleBlock(Properties properties, List<StyleData> styles,
	// ResourceLocation itemRendererLocation,
	// MATERIAL_TYPES matType, String materialName, List<String> styleBases)

	public final StyleData[] styles;
	final ResourceLocation itemRendererLocation;
	List<String> styleBases;
	public final MATERIAL_TYPES matType;
	public final String materialName;

	public StyleData[] getStyles() {
		return styles;
	}

	public ResourceLocation getItemRendererLocation() {
		return itemRendererLocation;
	}

	public List<String> getStyleBases() {
		return styleBases;
	}

	public String getMatType() {
		return matType.toString();
	}

	public String getMaterialName() {
		return materialName;
	}

	public static BooleanProperty getFullSlab() {
		return FULL_SLAB;
	}

	public static BooleanProperty getWaterlogged() {
		return WATERLOGGED;
	}

	public static final BooleanProperty FULL_SLAB = BooleanProperty.create("full");
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

//	public SlabStyleBlock(BlockBehaviour.Properties properties) {
//		super(properties);
//		this.registerDefaultState(this.defaultBlockState().setValue(FULL_SLAB, false)
//				.setValue(WATERLOGGED, Boolean.valueOf(false)).setValue(FACING, Direction.SOUTH));
//	}

	public SlabStyleBlock(Properties properties, List<StyleData> styles, ResourceLocation itemRendererLocation,
			String matType, String materialName, List<String> styleBases) {
		super(properties);
		this.styles = styles;
		this.itemRendererLocation = itemRendererLocation;
		this.styleBases = styleBases;
		this.materialName = materialName;
		this.matType = MATERIAL_TYPES.valueOf(matType);

		this.registerDefaultState(this.defaultBlockState().setValue(FULL_SLAB, false)
				.setValue(WATERLOGGED, Boolean.valueOf(false)).setValue(FACING, Direction.SOUTH));
	}

	public SlabStyleBlock(Properties properties, List<StyleData> styles, ResourceLocation itemRendererLocation,
			MATERIAL_TYPES matType, String materialName, List<String> styleBases) {
		super(properties);
		this.styles = styles;
		this.itemRendererLocation = itemRendererLocation;
		this.styleBases = styleBases;
		this.materialName = materialName;
		this.matType = matType;

		this.registerDefaultState(this.defaultBlockState().setValue(FULL_SLAB, false)
				.setValue(WATERLOGGED, Boolean.valueOf(false)).setValue(FACING, Direction.SOUTH));
	}

	@Override
	protected boolean useShapeForLightOcclusion(BlockState state) {
		return !state.getValue(FULL_SLAB);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FULL_SLAB, WATERLOGGED);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SimpleStyleBlockEntity(pos, state, matType, materialName, styles);
	}

	@Override
	public RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Override
	public List<String> getStyles(List<Integer> current) {
		List<String> s = new ArrayList<String>();
		for (int i = 0; i < current.size(); i++) {
			if (styles.size() > i) {
				if (styles.get(i).getTypes().size() > current.get(i))
					s.add(styles.get(i).getTypes().get(current.get(i)));
			}
		}

		return s;
	}

	@Override
	public ResourceLocation getItemModelLocation() {
		// TODO Auto-generated method stub
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
	public List<StyleData> getStyleData() {
		return this.styles;
	}

	@Override
	protected MapCodec<? extends DirectionalBlock> codec() {
		return null;
	}
}
