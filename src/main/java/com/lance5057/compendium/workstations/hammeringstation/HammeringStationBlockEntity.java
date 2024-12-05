package com.lance5057.compendium.workstations.hammeringstation;

import java.util.Optional;

import com.lance5057.compendium.CompendiumTileEntities;
import com.lance5057.compendium.workstations._bases.blockentities.MultiToolRecipeStation;
import com.lance5057.compendium.workstations._bases.components.item.BlockEntityItemHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

public class HammeringStationBlockEntity extends MultiToolRecipeStation<HammeringStationRecipe> {

	public HammeringStationBlockEntity(BlockPos pos, BlockState state) {
		super(1, 1, 1, CompendiumTileEntities.HAMMERING_STATION.get(), pos, state);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Optional<HammeringStationRecipe> matchRecipe() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	protected BlockEntityItemHandler createItemHandler() {
		return new BlockEntityItemHandler(this, 1) {

		};
	}

	@Override
	public void addParticle() {
		// TODO Auto-generated method stub

	}

	@Override
	public void finishRecipe(Player Player, HammeringStationRecipe recipe) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void readNBTExtra(CompoundTag arg0, Provider arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeNBTExtra(CompoundTag arg0, Provider arg1) {
		// TODO Auto-generated method stub
		
	}

}
