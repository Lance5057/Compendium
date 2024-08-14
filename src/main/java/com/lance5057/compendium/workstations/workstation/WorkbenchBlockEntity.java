package com.lance5057.compendium.workstations.workstation;

import java.util.Optional;

import com.lance5057.compendium.CompendiumTileEntities;
import com.lance5057.compendium.workstations._bases.blockentities.MultiToolRecipeStation;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandlerModifiable;

public class WorkbenchBlockEntity extends MultiToolRecipeStation<WorkbenchRecipe> {
	public static final String SCREEN_TITLE = "";
//  private final LazyOptional<IItemInteractionHandlerModifiable> InteractionHandler = LazyOptional.of(this::createInteractionHandler);
	private ItemStack ghostStack = ItemStack.EMPTY;

	public static final int PRODUCT_DISPLAY_SLOT = 0;
	public static final int OUTPUT_SLOT = 1;

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

	public WorkbenchBlockEntity(BlockPos pos, BlockState state) {
		super(27, 5, 5, CompendiumTileEntities.WORKSTATION.get(), pos, state);
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
	public void finishRecipe(Player Player, WorkbenchRecipe r) {

	}

	@Override
	protected Optional<WorkbenchRecipe> matchRecipe() {
//		if (level != null) {
//
//			Optional<WorkstationRecipe> recipe = handler.map(i -> {
//				return level.getRecipeManager().getRecipeFor(WorkstationRecipes.WORKSTATION_RECIPE.get(),
//						new WorkstationRecipeWrapper(gridLevel, gridLevel, i), level);
//			}).get();
//
//			// setRecipe(recipe);
//			return recipe;
//		}
		return null;
	}

	@Override
	protected IItemHandlerModifiable createInteractionHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void readNBT(CompoundTag nbt) {
		// TODO Auto-generated method stub

	}

	@Override
	public CompoundTag writeNBT(CompoundTag tag) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDisplayName() {
		return "screen.workbench.name";
	}
}
