package com.lance5057.compendium.blocks.entities;

import java.util.stream.Stream;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.entities.SeatEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

public class ChairBlockEntity extends MultiMaterialBlockEntity {

	public static enum BACK {
		BASIC
	}

	public BACK backStyles = BACK.BASIC;

	public static enum SEAT {
		BASIC
	}

	public SEAT seatStyles = SEAT.BASIC;

	public static enum LEGS {
		BASIC
	}

	public LEGS legsStyles = LEGS.BASIC;
	
	

	public ChairBlockEntity(BlockPos pos, BlockState blockState) {
		super(CompendiumBlockEntities.CHAIR.get(), pos, blockState,
				Stream.of("invalid", "invalid", "invalid").toList());
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

	@Override
	public int getMaterialsCount() {
		return 3;
	}

	protected void readNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {
		if (nbt.contains("types")) {
			CompoundTag tag = nbt.getCompound("types");

			backStyles = BACK.valueOf(tag.get("back").getAsString());
			seatStyles = SEAT.valueOf(tag.get("seat").getAsString());
			legsStyles = LEGS.valueOf(tag.get("legs").getAsString());
		}
	}

	protected void writeNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {
		CompoundTag tag = new CompoundTag();

		tag.putString("back", backStyles.toString());
		tag.putString("seat", seatStyles.toString());
		tag.putString("legs", legsStyles.toString());

		nbt.put("types", tag);
	}
}
