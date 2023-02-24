package lance5057.compendium.core.workstations.tileentities;

import java.util.Optional;

import javax.annotation.Nonnull;

import lance5057.compendium.CompendiumTileEntities;
import lance5057.compendium.core.util.recipes.WorkstationRecipeWrapper;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations.recipes.SawBuckRecipe;
import lance5057.compendium.core.workstations.tileentities.bases.MultiToolRecipeStation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class SawBuckTE extends MultiToolRecipeStation<SawBuckRecipe> {

	public SawBuckTE(BlockPos pos, BlockState state) {
		super(1, 1, 1, CompendiumTileEntities.SAW_BUCK_TE.get(), pos, state);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected <T> LazyOptional<T> getExtraCapability(Capability<T> cap, Direction side) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Optional<SawBuckRecipe> matchRecipe() {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<SawBuckRecipe> matchRecipe(ItemStack itemstack) {
		if (level != null) {

			Optional<SawBuckRecipe> recipe = handler.map(i -> {
				return level.getRecipeManager().getRecipeFor(WorkstationRecipes.SAWBUCK_RECIPE.get(),
						new WorkstationRecipeWrapper(1, 1, i), level);
			}).get();

			// setRecipe(recipe);
			return recipe;
		}
		return Optional.empty();
	}

	@Override
	protected IItemHandlerModifiable createInteractionHandler() {
		return new ItemStackHandler(1) {
			@Override
			protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
				return 1;
			}

			@Override
			protected void onContentsChanged(int slot) {
				zeroProgress();
				updateInventory();
			}
		};
	}

	@Override
	public void addParticle() {
		// TODO Auto-generated method stub

	}

	@Override
	public void finishRecipe(Player Player, SawBuckRecipe recipe) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void readExtraNBT(CompoundTag nbt) {
		// TODO Auto-generated method stub

	}

	@Override
	protected CompoundTag writeExtraNBT(CompoundTag tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void extractItem(Player playerEntity, IItemHandler inventory) {
		if (!inventory.getStackInSlot(0).isEmpty()) {
			ItemStack itemStack = inventory.extractItem(0, inventory.getStackInSlot(0).getCount(), false);
			playerEntity.addItem(itemStack);
			updateInventory();
			return;

		}
		updateInventory();
	}

	@Override
	public void insertItem(IItemHandler inventory, ItemStack heldItem) {
		if (inventory.isItemValid(0, heldItem))
			if (!inventory.insertItem(0, heldItem, true).equals(heldItem, false)) {
				final int leftover = inventory.insertItem(0, heldItem.copy(), false).getCount();
				heldItem.setCount(leftover);
				updateInventory();
				return;
			}
		updateInventory();

	}

}
