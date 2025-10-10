package com.lance5057.compendium.workstations.scrappingtable;

import java.util.Optional;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.util.ItemUtil;
import com.lance5057.compendium.workstations.WorkstationRecipes;
import com.lance5057.compendium.workstations._bases.blockentities.MultiToolRecipeStation;
import com.lance5057.compendium.workstations._bases.components.item.BlockEntityItemHandler;
import com.lance5057.compendium.workstations.containers.MultiToolRecipeWrapper;
import com.lance5057.compendium.workstations.scrappingtable.scrapping_rules.IScrappingRule;
import com.lance5057.compendium.workstations.scrappingtable.scrapping_rules.ScrappingRulesRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeManager.CachedCheck;
import net.minecraft.world.level.block.state.BlockState;

public class ScrappingTableBlockEntity extends MultiToolRecipeStation<ScrappingTableRecipe> {

	public ScrappingTableBlockEntity(BlockPos pos, BlockState state) {
		super(1, 1, 1, CompendiumBlockEntities.SCRAPPING_TABLE.get(), pos, state);
	}

	private final CachedCheck<MultiToolRecipeWrapper, ScrappingTableRecipe> quickCheck = RecipeManager
			.createCheck(WorkstationRecipes.SCRAPPINGTABLE_RECIPE.get());

	@Override
	public Optional<RecipeHolder<ScrappingTableRecipe>> matchRecipe() {
		Optional<RecipeHolder<ScrappingTableRecipe>> recipe = Optional.empty();
		if (this.level != null && this.getInventory() != null) {
			recipe = this.quickCheck.getRecipeFor(MultiToolRecipeWrapper.of(this.getInventory()), level);
			if (recipe.isEmpty()) {
				// search for item's recipe
				Optional<RecipeHolder<?>> other = this.level.getRecipeManager().getRecipes().stream()
						.filter(r -> ItemStack.isSameItem(r.value().getResultItem(level.registryAccess()),
								this.getInventory().getStackInSlot(0)))
						.findFirst();

				if (other.isPresent()) {
					Optional<IScrappingRule> rule = ScrappingRulesRegistry.getRule(other.get());
					if (rule.isPresent()) {
						rule.get().scrap(other.get(), this.inventory.getStackInSlot(0));
					}
				}
			}
		}
		return recipe;
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
	public void finishRecipe(Player Player, ScrappingTableRecipe recipe) {
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

	@Override
	protected void setupRecipe() {

	}

}
