package com.lance5057.compendium.blocks.bed;

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

public class FancyBedBlockEntity extends MultiMaterialBlockEntity implements IStyleable {
	public static List<String> frame = List.of("basic");
	public static List<String> mattress = List.of("basic");
	public static List<String> pillow = List.of("basic");
	public static List<String> blanket = List.of("basic");
	public static List<String> sheet = List.of("basic");

	List<List<String>> styles = List.of(frame, mattress, pillow, blanket, sheet);

	List<Integer> currentStyles = new ArrayList<Integer>();

	public FancyBedBlockEntity(BlockPos pos, BlockState blockState) {
		super(CompendiumBlockEntities.FANCY_BED.get(), pos, blockState);
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
		int i4 = this.getCurrent(3);
		int i5 = this.getCurrent(4);

		if (frame.size() > i1)
			l.add(frame.get(i1));
		else
			l.add(frame.get(0));

		if (mattress.size() > i2)
			l.add(mattress.get(i2));
		else
			l.add(mattress.get(0));

		if (pillow.size() > i3)
			l.add(pillow.get(i3));
		else
			l.add(pillow.get(0));

		if (blanket.size() > i4)
			l.add(blanket.get(i4));
		else
			l.add(blanket.get(0));

		if (sheet.size() > i5)
			l.add(sheet.get(i5));
		else
			l.add(sheet.get(0));

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
		return 5;
	}

	@Override
	public int getMaterialsCount() {
		return 5;
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
