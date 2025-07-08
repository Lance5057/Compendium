package com.lance5057.compendium.blocks.clothedtable;

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

public class ClothedTableBlockEntity extends MultiMaterialBlockEntity implements IStyleable {

	public static List<String> top = List.of("basic", "trimmed", "smooth");
	public static List<String> legs = List.of("basic", "bar", "fancy");
	public static List<String> cloth = List.of("basic", "long", "short", "angled", "angled_short", "angled_long");

	List<List<String>> styles = List.of(top, legs, cloth);

	List<Integer> currentStyles = new ArrayList<Integer>();

	public ClothedTableBlockEntity(BlockPos pos, BlockState blockState) {
		super(CompendiumBlockEntities.CLOTHED_TABLE.get(), pos, blockState);
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

		if (top.size() > i1)
			l.add(top.get(i1));
		else
			l.add(top.get(0));

		if (legs.size() > i2)
			l.add(legs.get(i2));
		else
			l.add(legs.get(0));

		if (cloth.size() > i3)
			l.add(cloth.get(i3));
		else
			l.add(cloth.get(0));

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
