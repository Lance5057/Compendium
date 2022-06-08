package lance5057.compendium.core.workstations.containers;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Nonnull;

import lance5057.compendium.CompendiumContainers;
import lance5057.compendium.core.recipes.RecipeItemUse;
import lance5057.compendium.core.util.recipes.WorkstationRecipeWrapper;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations.recipes.CraftingAnvilRecipe;
import lance5057.compendium.core.workstations.recipes.bases.MultiToolRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class CraftingAnvilContainer extends AbstractContainerMenu {
	// private final CraftingInventory craftMatrix = new CraftingInventory(this, 5,
	// 5);
	// private final CraftResultInventory craftResult = new CraftResultInventory();
	private final Level world;
	private final Player player;
	private ItemStackHandler inv;

	private Slot output;
	private Slot view;

	public NonNullList<RecipeItemUse> toolList;

	public static CraftingAnvilContainer createContainerServerSide(int windowID, Inventory playerInventory,
			ItemStackHandler inv) {
		return new CraftingAnvilContainer(windowID, playerInventory, inv);
	}

	public static CraftingAnvilContainer createContainerClientSide(int windowID, Inventory playerInventory,
			net.minecraft.network.FriendlyByteBuf extraData) {

		return new CraftingAnvilContainer(windowID, playerInventory, new ItemStackHandler(27));
	}

	public CraftingAnvilContainer(int id, Inventory playerInventory, ItemStackHandler inv) {
		super(CompendiumContainers.CRAFTING_ANVIL_CONTAINER.get(), id);
		this.player = playerInventory.player;
		this.world = playerInventory.player.level;
		this.inv = inv;
		toolList = NonNullList.create();
		view = this.addSlot(new Slot(new SimpleContainer(1), 0, 143, 44) {
			@Override
			public boolean mayPickup(Player playerIn) {
				return false;
			}

			@Override
			@OnlyIn(Dist.CLIENT)
			public boolean isActive() {
				return true;
			}

			@Override
			public boolean mayPlace(@Nonnull ItemStack stack) {
				return false;
			}
		});

		this.addSlot(new SlotItemHandler(this.inv, 25, 143, 22) {

			@Override
			public void setChanged() {
				super.setChanged();

				CraftingAnvilContainer.this.slotsChanged(new WorkstationRecipeWrapper(5, 5, inv));
			}
		});

		output = this.addSlot(new SlotItemHandler(this.inv, 26, 143, 70) {
			@Override
			public void onTake(Player thePlayer, ItemStack stack) {
				CraftingAnvilContainer.this.clear();
			}

			@Override
			public boolean mayPlace(@Nonnull ItemStack stack) {
				return false;
			}
		});

		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				this.addSlot(new SlotItemHandler(this.inv, j + i * 5, 13 + j * 18, 8 + i * 18) {

					@Override
					public void setChanged() {
						super.setChanged();

						CraftingAnvilContainer.this.slotsChanged(new WorkstationRecipeWrapper(5, 5, inv));
					}
				});
			}
		}

		for (int k = 0; k < 3; ++k) {
			for (int i1 = 0; i1 < 9; ++i1) {
				this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 111 + k * 18));
			}
		}

		for (int l = 0; l < 9; ++l) {
			this.addSlot(new Slot(playerInventory, l, 8 + l * 18, 169));
		}

	}

	public void zeroStrikes() {

	}

	public void clear() {
//		for (int i = 0; i < 25; i++) {
//			if (this.getSlot(i).getItem() != ItemStack.EMPTY) {
//				ItemStack item = this.getSlot(i).getItem();
//				item.setCount(item.getCount() - 1);
//				this.getSlot(i).set(item);
//			}
//		}
		zeroStrikes();
	}

//	protected static void updateCraftingResult(int id, Level world, Player player, ItemStackInteractionHandler inventory, CraftResultInventory inventoryResult) {
//		if (!world.isRemote) {
//			ServerPlayer serverPlayer = (ServerPlayer) player;
//			ItemStack itemstack = ItemStack.EMPTY;
//			Optional<ICraftingRecipe> optional = world.getServer().getRecipeManager().getRecipe(RecipeType.CRAFTING, new RecipeWrapper(inventory), world);
//			if (optional.isPresent()) {
//				ICraftingRecipe icraftingrecipe = optional.get();
//				if (inventoryResult.canUseRecipe(world, serverPlayer, icraftingrecipe)) {
//					itemstack = icraftingrecipe.getCraftingResult(inventory);
//				}
//			}
//
//			inventoryResult.setInventorySlotContents(0, itemstack);
//			serverPlayer.connection.sendPacket(new SSetSlotPacket(id, 0, itemstack));
//		}
//	}

	@Override
	public boolean stillValid(Player playerIn) {
		return true;
	}

	/**
	 * Callback for when the crafting matrix is changed.
	 */
	@Override
	public void slotsChanged(Container inventoryIn) {
		Optional<CraftingAnvilRecipe> recipe = this.world.getRecipeManager().getRecipeFor(
				WorkstationRecipes.CRAFTING_ANVIL_RECIPE.get(), (WorkstationRecipeWrapper) inventoryIn, this.world);
		if (recipe.isPresent()) {
			ItemStack result = recipe.get().getCraftingResult((WorkstationRecipeWrapper) inventoryIn);
			this.view.set(result);
			this.toolList = recipe.get().getToolList();
			// this.maxStrikes = recipe.get().getStrikes();
		} else {
			// this.output.set(ItemStack.EMPTY);
		}

		Collection<CraftingAnvilRecipe> r = this.world.getRecipeManager()
				.getAllRecipesFor(WorkstationRecipes.CRAFTING_ANVIL_RECIPE.get());
		MultiToolRecipe r2 = matchRecipe((WorkstationRecipeWrapper) inventoryIn);
		// zeroStrikes();
		super.slotsChanged(inventoryIn);
	}

	private MultiToolRecipe matchRecipe(WorkstationRecipeWrapper inventoryIn) {
		if (world != null) {
			return world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe instanceof MultiToolRecipe)
					.map(recipe -> (MultiToolRecipe) recipe).filter(recipe -> recipe.matches(inventoryIn, this.world))
					.findFirst().orElse(null);
		}
		return null;
	}

//	public void fillStackedContents(RecipeItemHelper itemHelperIn) {
//		this.craftMatrix.fillStackedContents(itemHelperIn);
//	}
//
//	public boolean matches(IRecipe<? super CraftingInventory> recipeIn) {
//		return recipeIn.matches(this.craftMatrix, this.player.world);
//	}

//	/**
//	 * Called when the container is closed.
//	 */
//	public void onContainerClosed(Player playerIn) {
//		super.onContainerClosed(playerIn);
//	}
//
//	/**
//	 * Determines whether supplied player can use this container
//	 */
//	public boolean canInteractWith(Player playerIn) {
//		return true;
//	}
//
	/**
	 * InteractionHandle when the stack in slot {@code index} is shift-clicked.
	 * Normally this moves the stack between the player inventory and the other
	 * inventory(s).
	 */
	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index == 0) {
				if (!this.moveItemStackTo(itemstack1, 10, 46, true)) {
					return ItemStack.EMPTY;
				}

				slot.onQuickCraft(itemstack1, itemstack);
			} else if (index >= 10 && index < 46) {
				if (!this.moveItemStackTo(itemstack1, 1, 10, false)) {
					if (index < 37) {
						if (!this.moveItemStackTo(itemstack1, 37, 46, false)) {
							return ItemStack.EMPTY;
						}
					} else if (!this.moveItemStackTo(itemstack1, 10, 37, false)) {
						return ItemStack.EMPTY;
					}
				}
			} else if (!this.moveItemStackTo(itemstack1, 10, 46, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(playerIn, itemstack1);
//			if (index == 0) {
//				playerIn.dropItem(itemstack2, false);
//			}
		}

		return itemstack;
	}

////	/**
////	 * Called to determine if the current slot is valid for the stack merging
////	 * (double-click) code. The stack passed in is null for the initial slot that
////	 * was double-clicked.
////	 */
////	public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
////		return slotIn.inventory != this.craftResult && super.canMergeSlot(stack, slotIn);
////	}
//
//	public int getOutputSlot() {
//		return 0;
//	}
//
//	public int getWidth() {
//		return 5;
//	}
//
//	public int getHeight() {
//		return 5;
//	}
//
//	@OnlyIn(Dist.CLIENT)
//	public int getSize() {
//		return 10;
//	}
}
