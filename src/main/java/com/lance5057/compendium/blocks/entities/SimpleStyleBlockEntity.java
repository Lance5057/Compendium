package com.lance5057.compendium.blocks.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.blocks.IStyleable;
import com.lance5057.compendium.client.models.style.StyleModelData;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.styleblock.StyleType;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;

public class SimpleStyleBlockEntity extends BlockEntity implements IStyleable {

	public static List<String> style = List.of("FULL_TILE", "HALF_TILE",
			/* "OFFSET_HALF_TILE", */ "VERTICAL_HALF_TILE", /* "QUARTER", */
			"INDENTED", "INDENTED_SEGMENTED", "DENTED", "DENTED_SEGMENTED"/* , */ /* "TILTED_SMALL_TILE", */
//			/*"DIAMOND_TILE"*/, /*"EIGHTH_TILES"*/, /* "OFFSET_EIGHTH_TILES", */ /*"BRICK", "BRICK_VERTICAL", "ALIGNED_BRICK",*/
//			"ALIGNED_BRICK_VERTICAL", "BASKETWEAVE_BRICKS", "BIG_BRICK", /* "HALF_BRICK", */ "HERRINGBONE_BRICKS",
	/* "HEX_BRICK", "SLATS", "SLATS_VERTICAL" */);
//	String name;

	List<List<String>> styles = List.of(style);

//	public String getName() {
//		return name;
//	}

//	List<List<String>> styles; // Immutable!

	List<Integer> currentStyles = new ArrayList<Integer>();

	@Override
	public List<List<String>> getStyles() {
		return styles;
	}

	public SimpleStyleBlockEntity(BlockPos pos, BlockState blockState) {
		super(CompendiumBlockEntities.STYLE.get(), pos, blockState);
		currentStyles = new ArrayList<Integer>(Arrays.asList(0));
	}

//	public SimpleStyleBlockEntity(BlockPos pos, BlockState blockState, String name, List<List<String>> styles) {
//		super(CompendiumBlockEntities.STYLE.get(), pos, blockState);
//		if (styles != null)
//			this.styles = styles;
//		this.name = name;
//	}

	@Override
	public int getCurrent(int index) {
		return currentStyles.get(index);
	}

	@Override
	public void setCurrent(int index, int c) {
		currentStyles.set(index, c);
		this.setChanged();
		getLevel().sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
	}

	@Override
	public List<String> getCurrentAllString() {
		return style;
	}

	@Override
	public int getStyleCount() {
		return 1;
	}

	@Override
	public ModelData getModelData() {

		return ModelData.builder().with(StyleModelData.STYLES, getCurrentAllString()).build();
	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.Builder builder) {
		super.collectImplicitComponents(builder);

		builder.set(CompendiumComponents.STYLE.get(), new StyleBlockComponent(currentStyles));
	}

	@Override
	protected void applyImplicitComponents(DataComponentInput input) {
		super.applyImplicitComponents(input);
		StyleBlockComponent m = input.getOrDefault(CompendiumComponents.STYLE.get(), null);
		if (m != null) {
			this.currentStyles = m.styles();
		}
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		CompoundTag nbt = super.getUpdateTag(registries);

		writeNBT(nbt, registries);

		return nbt;
	}

	@Override
	public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
		readNBT(tag, registries);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		this.requestModelDataUpdate();

		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider registries) {

		// InteractionHandle your Data

		setChanged();
		if (getLevel() != null) {
			CompoundTag tag = pkt.getTag();
			readNBT(tag, registries);

			BlockState state = getLevel().getBlockState(getBlockPos());
			requestModelDataUpdate();
			getLevel().sendBlockUpdated(getBlockPos(), state, state, 3);
		}
	}

	void readNBT(CompoundTag nbt, HolderLookup.Provider registries) {

		readNBTExtra(nbt, registries);
	}

	CompoundTag writeNBT(CompoundTag tag, HolderLookup.Provider registries) {

		writeNBTExtra(tag, registries);

		return tag;
	}

	@Override
	public void loadAdditional(@Nonnull CompoundTag nbt, HolderLookup.Provider registries) {
		super.loadAdditional(nbt, registries);
		readNBT(nbt, registries);
	}

	@Override
	public void saveAdditional(@Nonnull CompoundTag nbt, HolderLookup.Provider registries) {
		super.saveAdditional(nbt, registries);
		writeNBT(nbt, registries);
	}

	protected void readNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {
		this.readStyleNBT(nbt, registries);
	}

	protected void writeNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {
		this.writeStyleNBT(nbt, registries);
	}

	@Override
	public List<Integer> getCurrentAll() {
		return this.currentStyles;
	}

}
