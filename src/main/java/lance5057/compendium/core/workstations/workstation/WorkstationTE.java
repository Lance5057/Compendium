package lance5057.compendium.core.workstations.workstation;

import java.util.List;
import java.util.Optional;

import lance5057.compendium.CompendiumTileEntities;
import lance5057.compendium.core.util.recipes.WorkstationRecipeWrapper;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations._bases.blockentities.MultiToolRecipeStation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class WorkstationTE extends MultiToolRecipeStation<WorkstationRecipe> implements MenuProvider {
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

	protected final LazyOptional<IEnergyStorage> EnergyHandler = LazyOptional.of(this::createEnergyHandler);

	public WorkstationTE(BlockPos pos, BlockState state) {
		super(27, 5, 5, CompendiumTileEntities.WORKSTATION_TE.get(), pos, state);
	}

	public void setGhostStack(ItemStack i) {
		this.ghostStack = i;
	}

	public ItemStack getGhostStack() {
		return this.ghostStack;
	}
	
	public int getGridLevel()
	{
		return gridLevel;
	}

	@Override
	protected IItemHandlerModifiable createInteractionHandler() {
		return new ItemStackHandler(INVENTORY_SIZE + (5 * 5)) {

			@Override
			protected void onContentsChanged(int slot) {
				updateInventory();
//				if (slot < PRODUCT_DISPLAY_SLOT) {
//
//					zeroProgress();
//					Optional<WorkstationRecipe> recipe = matchRecipe();
//
//					setGhostStack(ItemStack.EMPTY);
//
//					if (recipe.isPresent()) {
//						setGhostStack(recipe.get().getRecipeOutput().copy());
//						//setRecipe(recipe);
//					}
//				}
			}
		};
	}

	protected IEnergyStorage createEnergyHandler() {
		return new EnergyStorage(powerLevel);
	}

	@Override
	public void addParticle() {
		// TODO Auto-generated method stub

	}

	@Override
	public void finishRecipe(Player Player, WorkstationRecipe r) {
		handler.ifPresent(h -> {
			ItemStack item = r.getRecipeOutput().copy();
			BlockEntity te = level.getBlockEntity(this.getBlockPos().offset(0, -1, 0));
			if (te != null) {
				LazyOptional<IItemHandler> ih = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
						Direction.UP);

				if (h.getStackInSlot(OUTPUT_SLOT) == ItemStack.EMPTY) {

					item = ih.map(h2 -> dropItemBelow(h2, r.getRecipeOutput().copy())).get();
					if (item == null)
						craft();

				}
			}

			if (h.getStackInSlot(OUTPUT_SLOT) == ItemStack.EMPTY) {
				h.setStackInSlot(OUTPUT_SLOT, item);
				craft();
			}
		});
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
		return new WorkstationContainer(id, this.worldPosition, inv, player);
	}

	@Override
	public Component getDisplayName() {
		// TODO Auto-generated method stub
		return Component.translatable(SCREEN_TITLE);
	}

	@Override
	protected void readExtraNBT(CompoundTag nbt) {
		// TODO Auto-generated method stub

	}

	@Override
	protected CompoundTag writeExtraNBT(CompoundTag tag) {
		tag.putInt("level", gridLevel);
		tag.putBoolean("light", light);
		return tag;
	}

	@Override
	protected <T> LazyOptional<T> getExtraCapability(Capability<T> cap, Direction side) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Optional<WorkstationRecipe> matchRecipe() {
		if (level != null) {

			Optional<WorkstationRecipe> recipe = handler.map(i -> {
				return level.getRecipeManager().getRecipeFor(WorkstationRecipes.WORKSTATION_RECIPE.get(),
						new WorkstationRecipeWrapper(gridLevel, gridLevel, i), level);
			}).get();

			// setRecipe(recipe);
			return recipe;
		}
		return null;
	}

}
