package lance5057.compendium.core.workstations.containers;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Nonnull;

import lance5057.compendium.CompendiumContainers;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations.recipes.CraftingAnvilRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class CraftingAnvilContainer extends Container {
	// private final CraftingInventory craftMatrix = new CraftingInventory(this, 5,
	// 5);
	// private final CraftResultInventory craftResult = new CraftResultInventory();
	private final World world;
	private final PlayerEntity player;
	private ItemStackHandler inv;

	private Slot output;
	private Slot view;

	public int strikes = 0;
	public int maxStrikes = 0;

	public static CraftingAnvilContainer createContainerServerSide(int windowID, PlayerInventory playerInventory, ItemStackHandler inv, int strikes) {
		return new CraftingAnvilContainer(windowID, playerInventory, inv, strikes);
	}

	public static CraftingAnvilContainer createContainerClientSide(int windowID, PlayerInventory playerInventory, net.minecraft.network.PacketBuffer extraData) {

		return new CraftingAnvilContainer(windowID, playerInventory, new ItemStackHandler(26), extraData.readInt());
	}

	private CraftingAnvilContainer(int id, PlayerInventory playerInventory, ItemStackHandler inv, int strikes) {
		super(CompendiumContainers.CRAFTING_ANVIL_CONTAINER.get(), id);
		this.player = playerInventory.player;
		this.world = playerInventory.player.world;
		this.inv = inv;
		this.strikes = strikes;
		view = this.addSlot(new Slot(new Inventory(1), 0, 143, 44) {
			@Override
			public boolean canTakeStack(PlayerEntity playerIn) {
				return false;
			}

			@Override
			@OnlyIn(Dist.CLIENT)
			public boolean isEnabled() {
				return true;
			}

			@Override
			public boolean isItemValid(@Nonnull ItemStack stack) {
				return false;
			}
		});

		output = this.addSlot(new SlotItemHandler(this.inv, 25, 143, 70) {
			@Override
			public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
				CraftingAnvilContainer.this.clear();

				return stack;
			}

			@Override
			public boolean isItemValid(@Nonnull ItemStack stack) {
				return false;
			}
		});

		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				this.addSlot(new SlotItemHandler(this.inv, j + i * 5, 13 + j * 18, 8 + i * 18) {

					@Override
					public void onSlotChanged() {
						super.onSlotChanged();
						CraftingAnvilContainer.this.onCraftMatrixChanged(new RecipeWrapper(inv));
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

	public void clear() {
//		for (int i = 0; i < 25; i++) {
//			if (this.getSlot(i).getStack() != ItemStack.EMPTY) {
//				ItemStack item = this.getSlot(i).getStack();
//				item.setCount(item.getCount() - 1);
//				this.getSlot(i).putStack(item);
//			}
//		}
	}

//	protected static void updateCraftingResult(int id, World world, PlayerEntity player, ItemStackHandler inventory, CraftResultInventory inventoryResult) {
//		if (!world.isRemote) {
//			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) player;
//			ItemStack itemstack = ItemStack.EMPTY;
//			Optional<ICraftingRecipe> optional = world.getServer().getRecipeManager().getRecipe(IRecipeType.CRAFTING, new RecipeWrapper(inventory), world);
//			if (optional.isPresent()) {
//				ICraftingRecipe icraftingrecipe = optional.get();
//				if (inventoryResult.canUseRecipe(world, serverplayerentity, icraftingrecipe)) {
//					itemstack = icraftingrecipe.getCraftingResult(inventory);
//				}
//			}
//
//			inventoryResult.setInventorySlotContents(0, itemstack);
//			serverplayerentity.connection.sendPacket(new SSetSlotPacket(id, 0, itemstack));
//		}
//	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return true;
	}

	/**
	 * Callback for when the crafting matrix is changed.
	 */
	@Override
	public void onCraftMatrixChanged(IInventory inventoryIn) {
		Optional<CraftingAnvilRecipe> recipe = this.world.getRecipeManager().getRecipe(WorkstationRecipes.CRAFTING_ANVIL_RECIPE, (RecipeWrapper) inventoryIn, this.world);
		if (recipe.isPresent()) {
			ItemStack result = recipe.get().getCraftingResult((RecipeWrapper) inventoryIn);
			this.view.putStack(result);
			this.maxStrikes = recipe.get().getStrikes();
		} else {
			this.output.putStack(ItemStack.EMPTY);
			this.maxStrikes = 0;
		}

		Collection<CraftingAnvilRecipe> r = this.world.getRecipeManager().getRecipesForType(WorkstationRecipes.CRAFTING_ANVIL_RECIPE);
		CraftingAnvilRecipe r2 = matchRecipe((RecipeWrapper) inventoryIn);
		super.onCraftMatrixChanged(inventoryIn);
	}

	private CraftingAnvilRecipe matchRecipe(RecipeWrapper inventoryIn) {
		if (world != null) {
			return world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe instanceof CraftingAnvilRecipe).map(recipe -> (CraftingAnvilRecipe) recipe).filter(recipe -> recipe.matches(inventoryIn, this.world)).findFirst().orElse(null);
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
//	public void onContainerClosed(PlayerEntity playerIn) {
//		super.onContainerClosed(playerIn);
//	}
//
//	/**
//	 * Determines whether supplied player can use this container
//	 */
//	public boolean canInteractWith(PlayerEntity playerIn) {
//		return true;
//	}
//
	/**
	 * Handle when the stack in slot {@code index} is shift-clicked. Normally this
	 * moves the stack between the player inventory and the other inventory(s).
	 */
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index == 0) {
				if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (index >= 10 && index < 46) {
				if (!this.mergeItemStack(itemstack1, 1, 10, false)) {
					if (index < 37) {
						if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
							return ItemStack.EMPTY;
						}
					} else if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
						return ItemStack.EMPTY;
					}
				}
			} else if (!this.mergeItemStack(itemstack1, 10, 46, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
			if (index == 0) {
				playerIn.dropItem(itemstack2, false);
			}
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
