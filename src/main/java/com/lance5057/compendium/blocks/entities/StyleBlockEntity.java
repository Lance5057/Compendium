package com.lance5057.compendium.blocks.entities;

import java.util.List;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.entities.SeatEntity;
import com.lance5057.compendium.styleblock.MultiStyle;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

public class StyleBlockEntity extends BlockEntity {
	String name;

	public String getName() {
		return name;
	}

	List<MultiStyle> styles;

	public List<MultiStyle> getStyles() {
		return styles;
	}

	// public MultiStyle backStyles = new MultiStyle("basic", "runged");
//	public MultiStyle seatStyles = new MultiStyle("basic");
//	public MultiStyle legsStyles = new MultiStyle("basic");
	public StyleBlockEntity(BlockPos pos, BlockState blockState) {
		this(pos, blockState, "", List.of());
	}

	public StyleBlockEntity(BlockPos pos, BlockState blockState, String name, List<MultiStyle> styles) {
		super(CompendiumBlockEntities.STYLE.get(), pos, blockState);
		this.styles = styles;
		this.name = name;
	}

	public InteractionResult attemptSit(BlockState state, Level level, BlockPos pos, Player player,
			BlockHitResult hitResult) {

		if (this.level.getEntities(null,
				new AABB(this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ(),
						this.worldPosition.getX() + 1, this.worldPosition.getY() + 1, this.worldPosition.getZ() + 1))
				.isEmpty()) {
			SeatEntity s = new SeatEntity(level, this.worldPosition,
					this.getBlockState().getValue(HorizontalDirectionalBlock.FACING), -0.0f);
			level.addFreshEntity(s);

			player.startRiding(s);
			return InteractionResult.SUCCESS;
		}

		return InteractionResult.CONSUME;
	}

	protected void readNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {
		if (nbt.contains("types")) {
			CompoundTag tag = nbt.getCompound("types");

			for (int i = 0; i < styles.size(); i++) {
				styles.get(i).readNBT(tag.getCompound("style" + i), registries);
			}
//			backStyles.readNBT(tag.getCompound("back"), registries);
//			seatStyles.readNBT(tag.getCompound("seat"), registries);
//			legsStyles.readNBT(tag.getCompound("legs"), registries);
		}
	}

	protected void writeNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {
		CompoundTag tag = new CompoundTag();

		for (int i = 0; i < styles.size(); i++) {
			tag.put("style" + i, styles.get(i).writeNBT(nbt, registries));
		}
//		tag.put("back", backStyles.writeNBT(nbt, registries));
//		tag.put("seat", seatStyles.writeNBT(nbt, registries));
//		tag.put("legs", legsStyles.writeNBT(nbt, registries));

		nbt.put("types", tag);
	}
}
