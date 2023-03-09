package lance5057.compendium.core.workstations.hammeringstation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import lance5057.compendium.CompendiumTileEntities;
import lance5057.compendium.core.util.recipes.WorkstationRecipeWrapper;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations._bases.blockentities.MultiToolRecipeStation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class HammeringStationTE extends MultiToolRecipeStation<HammeringStationRecipe> {

	public HammeringStationTE(BlockPos pos, BlockState state) {
		super(1, 1, 1, CompendiumTileEntities.HAMMERING_STATION_TE.get(), pos, state);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected <T> LazyOptional<T> getExtraCapability(Capability<T> cap, Direction side) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<HammeringStationRecipe> matchRecipe() {
		if (level != null) {

			List<HammeringStationRecipe> recipe = handler.map(i -> {
				return level.getRecipeManager().getRecipesFor(WorkstationRecipes.HAMMERINGSTATION_RECIPE.get(),
						new WorkstationRecipeWrapper(1, 1, i), level);
			}).get();

			// setRecipe(recipe);
			return recipe;
		}
		return new ArrayList<HammeringStationRecipe>();
	}

	public List<HammeringStationRecipe> matchRecipe(ItemStack itemstack) {
		if (level != null) {

			List<HammeringStationRecipe> recipe = handler.map(i -> {
				return level.getRecipeManager().getRecipesFor(WorkstationRecipes.HAMMERINGSTATION_RECIPE.get(),
						new WorkstationRecipeWrapper(1, 1, i), level);
			}).get();

			// setRecipe(recipe);
			return recipe;
		}
		return new ArrayList<HammeringStationRecipe>();
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

				if (!this.getStackInSlot(slot).isEmpty()) {
					setRecipe(matchRecipe());
				}

				updateInventory();
			}
		};
	}

	@Override
	public void addParticle() {
		// TODO Auto-generated method stub

	}

	@Override
	public void finishRecipe(Player player, HammeringStationRecipe recipe) {
		if (level != null && !level.isClientSide()) {

			// drop main output
			dropItems(recipe.getItemOut().copy());

			final LootContext pContext = new LootContext.Builder((ServerLevel) level)
					.withParameter(LootContextParams.TOOL, player.getMainHandItem())
					.withParameter(LootContextParams.THIS_ENTITY, player).withRandom(level.getRandom())
					.withLuck(player.getLuck() + EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE,
							player.getMainHandItem()))
					.create(LootContextParamSets.EMPTY);
			// TODO Investigate how to make block not drop things so violently
			player.getServer().getLootTables().get(recipe.getLootTableOut()).getRandomItems(pContext)
					.forEach(itemStack -> {
						dropItems(itemStack);
					});

			this.handler.ifPresent(it -> {
				ItemStack stack = it.getStackInSlot(0);
				stack.setCount(stack.getCount() - 1);
				it.setStackInSlot(0, stack);
			});

		}
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
