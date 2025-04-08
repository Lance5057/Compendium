package com.lance5057.compendium.blocks.entities;

import java.util.stream.Stream;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.entities.SeatEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

public class ChairBlockEntity extends MultiMaterialBlockEntity {

//	List<String> types = new ArrayList<String>();

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

//	protected void readNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {
//		if (nbt.contains("types")) {
//			CompoundTag tag = nbt.getCompound("types");
//
//			for (int i = 0; i < tag.getInt("size"); i++) {
//				types.add(tag.getString("type" + i));
//			}
//		}
//	}
//
//	protected void writeNBTExtra(CompoundTag nbt, HolderLookup.Provider registries) {
//		CompoundTag tag = new CompoundTag();
//
//		for (int i = 0; i < types.size(); i++) {
//			tag.putString("type" + i, types.get(i));
//		}
//
//		tag.putInt("size", types.size());
//
//		nbt.put("types", tag);
//	}
}
