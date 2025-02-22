package com.lance5057.compendium.workstations.sawbuck;

import java.util.Optional;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.util.ItemUtil;
import com.lance5057.compendium.workstations.WorkstationRecipes;
import com.lance5057.compendium.workstations._bases.blockentities.MultiToolRecipeStation;
import com.lance5057.compendium.workstations._bases.components.item.BlockEntityItemHandler;
import com.lance5057.compendium.workstations.containers.MultiToolRecipeWrapper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeManager.CachedCheck;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.Lazy;

public class SawBuckBlockEntity extends MultiToolRecipeStation<SawBuckRecipe> {

	public SawBuckBlockEntity(BlockPos pos, BlockState state) {
		super(1, 1, 1, CompendiumBlockEntities.SAW_BUCK.get(), pos, state);
	}

	private final CachedCheck<MultiToolRecipeWrapper, SawBuckRecipe> quickCheck = RecipeManager
			.createCheck(WorkstationRecipes.SAWBUCK_RECIPE.get());

	@Override
	public Optional<RecipeHolder<SawBuckRecipe>> matchRecipe() {
		if (this.level != null && this.getInventory() != null) {
			return this.quickCheck.getRecipeFor(MultiToolRecipeWrapper.of(this.getInventory()), level);
		}
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
	public void finishRecipe(Player Player, SawBuckRecipe recipe) {
		ItemUtil.giveOrDrop(recipe.getItemOut(), Player);
		this.getInventory().shrinkAll();
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
