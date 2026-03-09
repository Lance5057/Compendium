package com.lance5057.compendium.blocks.entities;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.blocks.IStyleable;
import com.lance5057.compendium.client.models.IndexEntryModelData;
import com.lance5057.compendium.client.models.style.StyleModelData;
import com.lance5057.compendium.components.block.IndexEntryComponent;
import com.lance5057.compendium.components.block.StyleBlockComponent;
import com.lance5057.compendium.index.CompendiumIndex.MATERIAL_TYPES;
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
	MATERIAL_TYPES matType;
	public MATERIAL_TYPES getMatType() {
		return matType;
	}

	public String getMaterialName() {
		return materialName;
	}

	String materialName;

	@Override
	public List<StyleData> getStyles() {
		return styles;
	}

	public void setCurrentStyles(List<Integer> currentStyles) {
		this.currentStyles = currentStyles;
	}

	public SimpleStyleBlockEntity(BlockPos pos, BlockState blockState, MATERIAL_TYPES type, String materialName,
			int styleCount, StyleData... styles) {
		super(CompendiumBlockEntities.STYLE.get(), pos, blockState);
		this.styleCount = styleCount;
		this.styles = List.of(styles);
		this.matType = type;
		this.materialName = materialName;
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

		return ModelData.builder().with(StyleModelData.STYLES, getCurrentAllString())
				.with(IndexEntryModelData.NAME, this.materialName).with(IndexEntryModelData.TYPE, this.matType).build();
	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.Builder builder) {
		super.collectImplicitComponents(builder);

		builder.set(CompendiumComponents.STYLE.get(), new StyleBlockComponent(currentStyles));
		builder.set(CompendiumComponents.INDEX.get(), new IndexEntryComponent(this.matType, this.materialName));
	}

	@Override
	protected void applyImplicitComponents(DataComponentInput input) {
		super.applyImplicitComponents(input);
		StyleBlockComponent m = input.getOrDefault(CompendiumComponents.STYLE.get(), null);
		if (m != null) {
			this.currentStyles = new ArrayList<Integer>(m.styles());
		}
		IndexEntryComponent i = input.getOrDefault(CompendiumComponents.INDEX.get(), null);
		if (i != null) {
			this.matType = i.getType();
			this.materialName = i.getName();
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

		this.matType = MATERIAL_TYPES.valueOf(nbt.get("material_type").getAsString());
		this.materialName = nbt.get("material_name").getAsString();
	}

	protected void writeNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {
		this.writeStyleNBT(nbt, registries);

		nbt.putString("material_name", materialName);
		nbt.putString("material_type", matType.toString().toUpperCase());
	}

}
