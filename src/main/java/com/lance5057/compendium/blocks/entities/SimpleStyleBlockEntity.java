package com.lance5057.compendium.blocks.entities;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.blocks.IStyleable;
import com.lance5057.compendium.client.models.style.StyleModelData;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.style.StyleData;

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

	List<StyleData> styles = new ArrayList<StyleData>();

	int styleCount;

	List<Integer> currentStyles;

	@Override
	public List<StyleData> getStyles() {
		return styles;
	}
	
	public void setCurrentStyles(List<Integer> currentStyles) {
		this.currentStyles = currentStyles;
	}

//	public SimpleStyleBlockEntity(BlockPos pos, BlockState blockState) {
//		super(CompendiumBlockEntities.STYLE.get(), pos, blockState);
////		currentStyles = new ArrayList<Integer>(Arrays.asList(0));
//		this.styleCount = 1;
//		this.styles = new ArrayList<StyleData>();
//	}

	public SimpleStyleBlockEntity(BlockPos pos, BlockState blockState, int styleCount, StyleData... styles) {
		super(CompendiumBlockEntities.STYLE.get(), pos, blockState);
		this.styleCount = styleCount;
//		currentStyles = new ArrayList<Integer>(Arrays.asList(new Integer[styleCount]));
		this.styles = List.of(styles);
	}

	@Override
	public int getCurrent(int index) {
		if (currentStyles != null && currentStyles.size() > index)
			return currentStyles.get(index);
		return 0;
	}

	@Override
	public void setCurrent(int index, int c) {
		if (currentStyles != null && currentStyles.size() > index) {
			currentStyles.set(index, c);
			this.setChanged();
			getLevel().sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
		}
	}

	@Override
	public List<String> getCurrentAllString() {
		List<String> l = new ArrayList<>();

		for (int i = 0; i < styles.size(); i++) {
			int c = this.getCurrent(i);

			List<String> s = styles.get(i).getTypes();
			if (s.size() > c)
				l.add(s.get(c));
			else
				l.add(s.get(0));
		}

		if (l.isEmpty()) {

		}

		return l;
	}

	@Override
	public int getStyleCount() {
		if (currentStyles != null) {
			if (styleCount == 0)
				styleCount = this.currentStyles.size();
			return styleCount;
		}
		return 0;
	}

	@Override
	public List<Integer> getCurrentAll() {
		if (this.currentStyles != null)
			return this.currentStyles;
		return new ArrayList<Integer>();
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
			this.currentStyles = new ArrayList<Integer>(m.styles());
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
		this.currentStyles = new ArrayList<Integer>(this.readStyleNBT(nbt, registries));
	}

	protected void writeNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {
		this.writeStyleNBT(nbt, registries);
	}

}
