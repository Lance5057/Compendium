package com.lance5057.compendium.workstations.workbench;

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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeManager.CachedCheck;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class WorkbenchBlockEntity extends MultiToolRecipeStation<WorkbenchBaseRecipe> {
	public static final String SCREEN_TITLE = "";
//  private final LazyOptional<IItemInteractionHandlerModifiable> InteractionHandler = LazyOptional.of(this::createInteractionHandler);
	private ItemStack ghostStack = ItemStack.EMPTY;

	public static final int CRAFTING_SLOTS = 25;

	public static final int PRODUCT_DISPLAY_SLOT = CRAFTING_SLOTS + 1;
	public static final int OUTPUT_SLOT = PRODUCT_DISPLAY_SLOT + 1;

	public static final int UPGRADE_4x4_SLOT = OUTPUT_SLOT + 1;
	public static final int UPGRADE_5x5_SLOT = UPGRADE_4x4_SLOT + 1;
	public static final int UPGRADE_LIGHT_SLOT = UPGRADE_5x5_SLOT + 1;
	public static final int UPGRADE_ENERGY = UPGRADE_LIGHT_SLOT + 1;
	public static final int UPGRADE_BATTERY = UPGRADE_ENERGY + 1;

	public static final int INVENTORY_SIZE = UPGRADE_BATTERY + 1;

	int gridLevel = 3; // 3=3x3 4=4x4 5=5x5
	boolean light = false;
	boolean powered = false;
	int powerLevel = 0;

	private final CachedCheck<MultiToolRecipeWrapper, WorkbenchBaseRecipe> quickCheck = RecipeManager
			.createCheck(WorkstationRecipes.WORKBENCH_BASE_RECIPE.get());

	public WorkbenchBlockEntity(BlockPos pos, BlockState state) {
		super(27, 5, 5, CompendiumBlockEntities.WORKBENCH.get(), pos, state);
	}

	public void setGhostStack(ItemStack i) {
		this.ghostStack = i;
	}

	public ItemStack getGhostStack() {
		return this.ghostStack;
	}

	public int getGridLevel() {
		return gridLevel;
	}

	@Override
	public void addParticle() {
		// TODO Auto-generated method stub

	}

	@Override
	public void finishRecipe(Player Player, WorkbenchBaseRecipe r) {

		ItemStack s = this.getInventory().insertItem(OUTPUT_SLOT, r.getItemOut(), false);
		if (!s.isEmpty())
			ItemUtil.giveOrDrop(s, Player);
		this.getInventory().shrinkRange(0, 25);
	}

	@Override
	public Optional<RecipeHolder<WorkbenchBaseRecipe>> matchRecipe() {
		if (this.level != null && this.getInventory() != null) {
			return this.quickCheck.getRecipeFor(MultiToolRecipeWrapper.of(5, 5, this.getInventory()), level);
		}
		return Optional.empty();
	}

	@Override
	protected void setupRecipe() {
		Optional<RecipeHolder<WorkbenchBaseRecipe>> recipe = this.matchRecipe();
		if (recipe.isPresent()) {
			WorkbenchBaseRecipe curRecipe = recipe.get().value();
			this.getInventory().setStackInSlot(PRODUCT_DISPLAY_SLOT,
					curRecipe.getResultItem(this.level.registryAccess()).copy());
		} else {
			this.getInventory().setStackInSlot(PRODUCT_DISPLAY_SLOT, ItemStack.EMPTY.copy());
		}
	}

	public String getDisplayName() {
		return "screen.workbench.name";
	}

	@Override
	protected BlockEntityItemHandler createItemHandler() {
		return new BlockEntityItemHandler(this, INVENTORY_SIZE) {
			@Override
			protected void onContentsChanged(int slot) {
				if (this.getBe() instanceof WorkbenchBlockEntity wb) {
					if (!this.getStackInSlot(UPGRADE_5x5_SLOT).isEmpty())
						wb.gridLevel = 5;
					else if (!this.getStackInSlot(UPGRADE_4x4_SLOT).isEmpty())
						wb.gridLevel = 4;
					else
						wb.gridLevel = 3;

					if (slot >= 0 && slot < 25) {
						if (slot != PRODUCT_DISPLAY_SLOT) {
							zeroProgress();
							updateInventory();
						}
					}

					if (slot == UPGRADE_LIGHT_SLOT) {
						if (this.getStackInSlot(slot).isEmpty()) {
							wb.level.setBlock(worldPosition, getBlockState().setValue(WorkbenchBlock.LIT, false),
									Block.UPDATE_ALL);
							BlockPos p = worldPosition.relative(getBlockState().getValue(WorkbenchBlock.FACING));
							wb.level.setBlock(p, wb.level.getBlockState(p).setValue(WorkbenchBlock.LIT, false),
									Block.UPDATE_ALL);
						} else {
							wb.level.setBlock(worldPosition, getBlockState().setValue(WorkbenchBlock.LIT, true),
									Block.UPDATE_ALL);
							BlockPos p = worldPosition.relative(getBlockState().getValue(WorkbenchBlock.FACING));
							wb.level.setBlock(p, wb.level.getBlockState(p).setValue(WorkbenchBlock.LIT, true),
									Block.UPDATE_ALL);
						}
					}
				}
			}
		};
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
