package com.lance5057.compendium.blocks.shingles.slanted;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.blocks.IStyleable;
import com.lance5057.compendium.blocks.entities.MultiMaterialBlockEntity;
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialModelData;
import com.lance5057.compendium.client.models.style.StyleModelData;
import com.lance5057.compendium.components.block.MultiMaterialBlockComponent;
import com.lance5057.compendium.components.block.StyleBlockComponent;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;

public class ShinglesSlantedBlockEntity extends MultiMaterialBlockEntity implements IStyleable {

	public static List<String> shingles = List.of("basic");
	public static List<String> support = List.of("basic");
	public static List<String> gable = List.of("basic");

	List<List<String>> styles = List.of(shingles, support, gable);

	List<Integer> currentStyles = new ArrayList<Integer>();

	public ShinglesSlantedBlockEntity(BlockPos pos, BlockState blockState) {
		super(CompendiumBlockEntities.SLANTED_SHINGLES.get(), pos, blockState);
	}

	@Override
	public List<List<String>> getStyles() {
		return styles;
	}

	@Override
	public int getCurrent(int index) {
		if (currentStyles.size() > index)
			return currentStyles.get(index);
		return 0;
	}

	@Override
	public List<Integer> getCurrentAll() {
		return this.currentStyles;
	}

	@Override
	public List<String> getCurrentAllString() {
		List<String> l = new ArrayList<>();

		int i1 = this.getCurrent(0);
		int i2 = this.getCurrent(1);
		int i3 = this.getCurrent(2);

		if (shingles.size() > i1)
			l.add(shingles.get(i1));
		else
			l.add(shingles.get(0));

		if (support.size() > i2)
			l.add(support.get(i2));
		else
			l.add(support.get(0));

		if (gable.size() > i3)
			l.add(gable.get(i3));
		else
			l.add(gable.get(0));

		return l;
	}

	@Override
	public void setCurrent(int index, int c) {
		currentStyles.set(index, c);
		this.setChanged();
		getLevel().sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
	}

	@Override
	public int getStyleCount() {
		return 3;
	}

	@Override
	public int getMaterialsCount() {
		return 3;
	}

	@Override
	public ModelData getModelData() {
		return ModelData.builder().with(MultiMaterialModelData.STATE, materials)
				.with(StyleModelData.STYLES, getCurrentAllString()).build();
	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.Builder builder) {
		super.collectImplicitComponents(builder);

		builder.set(CompendiumComponents.STYLE.get(), new StyleBlockComponent(currentStyles));
		builder.set(CompendiumComponents.MULTI_MATERIAL.get(), new MultiMaterialBlockComponent(materials));
	}

	@Override
	protected void applyImplicitComponents(DataComponentInput input) {
		super.applyImplicitComponents(input);
		StyleBlockComponent m = input.getOrDefault(CompendiumComponents.STYLE.get(), null);
		if (m != null) {
			this.currentStyles = new ArrayList<Integer>(m.styles());
		}

		MultiMaterialBlockComponent mm = input.getOrDefault(CompendiumComponents.MULTI_MATERIAL.get(), null);
		if (mm != null) {
			this.setMaterials(mm.types());
		}
	}

	@Override
	protected void readNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {
		this.currentStyles = new ArrayList<Integer>(this.readStyleNBT(nbt, registries));
	}

	@Override
	protected void writeNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {
		this.writeStyleNBT(nbt, registries);
	}

}
