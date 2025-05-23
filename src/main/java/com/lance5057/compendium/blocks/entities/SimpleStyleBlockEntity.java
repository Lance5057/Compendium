package com.lance5057.compendium.blocks.entities;

import java.util.ArrayList;
import java.util.List;

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
		return StyleModelData.builder(styles).build();
	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.Builder builder) {
		super.collectImplicitComponents(builder);
		builder.set(CompendiumComponents.STYLE.get(), new StyleBlockComponent(List.of(styles)));
	}

	@Override
	protected void applyImplicitComponents(DataComponentInput input) {
		super.applyImplicitComponents(input);
		StyleBlockComponent m = input.getOrDefault(CompendiumComponents.STYLE.get(), null);
		if (m != null) {
			this.styles = m.styles().get(0);
		}
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
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider registries) {
		CompoundTag tag = pkt.getTag();
		// InteractionHandle your Data
		readNBT(tag, registries);
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
