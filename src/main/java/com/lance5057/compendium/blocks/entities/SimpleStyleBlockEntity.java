package com.lance5057.compendium.blocks.entities;

import java.util.List;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.blocks.IStyleable;
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialModelData;
import com.lance5057.compendium.client.models.style.StyleModelData;
import com.lance5057.compendium.styleblock.StyleType;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;

public class SimpleStyleBlockEntity extends BlockEntity implements IStyleable {
	String name;

	public String getName() {
		return name;
	}

	StyleType styles;

	public List<StyleType> getStyles() {
		return List.of(styles);
	}

	@Override
	public void setStyles(List<StyleType> style) {
		styles = style.get(0);
	}

	// public MultiStyle backStyles = new MultiStyle("basic", "runged");
//	public MultiStyle seatStyles = new MultiStyle("basic");
//	public MultiStyle legsStyles = new MultiStyle("basic");
	public SimpleStyleBlockEntity(BlockPos pos, BlockState blockState) {
		this(pos, blockState, "");
	}

	public SimpleStyleBlockEntity(BlockPos pos, BlockState blockState, String name, StyleType... styles) {
		super(CompendiumBlockEntities.STYLE.get(), pos, blockState);
		if (styles != null)
			this.styles = styles[0];
		this.name = name;
	}

	@Override
	public ModelData getModelData() {

		return StyleModelData.builder(styles.getStyles().toArray(new String[0])).build();
	}

//	public InteractionResult attemptSit(BlockState state, Level level, BlockPos pos, Player player,
//			BlockHitResult hitResult) {
//
//		if (this.level.getEntities(null,
//				new AABB(this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ(),
//						this.worldPosition.getX() + 1, this.worldPosition.getY() + 1, this.worldPosition.getZ() + 1))
//				.isEmpty()) {
//			SeatEntity s = new SeatEntity(level, this.worldPosition,
//					this.getBlockState().getValue(HorizontalDirectionalBlock.FACING), -0.0f);
//			level.addFreshEntity(s);
//
//			player.startRiding(s);
//			return InteractionResult.SUCCESS;
//		}
//
//		return InteractionResult.CONSUME;
//	}

	protected void readNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {
		this.readStyleNBT(nbt, registries);
//		if (nbt.contains("types")) {
//			CompoundTag tag = nbt.getCompound("types");
//
//			for (int i = 0; i < styles.size(); i++) {
//				styles.get(i).readNBT(tag.getCompound("style" + i), registries);
//			}
////			backStyles.readNBT(tag.getCompound("back"), registries);
////			seatStyles.readNBT(tag.getCompound("seat"), registries);
////			legsStyles.readNBT(tag.getCompound("legs"), registries);
//		}
	}

	protected void writeNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {
		this.writeStyleNBT(nbt, registries);
	}
//		CompoundTag tag = new CompoundTag();
//
//		for (int i = 0; i < styles.size(); i++) {
//			tag.put("style" + i, styles.get(i).writeNBT(nbt, registries));
//		}
////		tag.put("back", backStyles.writeNBT(nbt, registries));
////		tag.put("seat", seatStyles.writeNBT(nbt, registries));
////		tag.put("legs", legsStyles.writeNBT(nbt, registries));
//
//		nbt.put("types", tag);
//	}

}
