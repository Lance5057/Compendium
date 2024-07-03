package com.lance5057.compendium.workstations.workstation;

import java.util.Collection;
import java.util.Objects;

import com.lance5057.compendium.CompendiumMenus;
import com.lance5057.compendium.util.recipes.WorkstationRecipeWrapper;
import com.lance5057.compendium.workstations.WorkstationRecipes;
import com.lance5057.compendium.workstations._bases.recipes.AnimatedRecipeItemUse;
import com.lance5057.compendium.workstations._bases.recipes.multitoolrecipe.MultiToolRecipeShaped;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.IItemHandler;

public class WorkbenchMenu extends AbstractContainerMenu {
	// private final CraftingInventory craftMatrix = new CraftingInventory(this, 5,
	// 5);
	// private final CraftResultInventory craftResult = new CraftResultInventory();
	private WorkbenchBlockEntity blockEntity;
	private final Level world;
	private final Player player;
	// private ItemStackHandler inv;v

	private Slot output;
	private Slot view;

	public NonNullList<AnimatedRecipeItemUse> toolList;

	private static WorkbenchBlockEntity getTileEntity(final Inventory playerInventory, final FriendlyByteBuf data) {
		Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final BlockEntity tileAtPos = playerInventory.player.level().getBlockEntity(data.readBlockPos());
		if (tileAtPos instanceof WorkbenchBlockEntity) {
			return (WorkbenchBlockEntity) tileAtPos;
		}
		throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
	}

	public WorkbenchMenu(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}

	public WorkbenchMenu(final int windowId, final Inventory playerInventory, final WorkbenchBlockEntity entity) {
		super(CompendiumMenus.WORKBENCH_MENU.get(), windowId);
		this.player = playerInventory.player;
		this.world = playerInventory.player.level();
		this.blockEntity = entity;

//		if (blockEntity != null) {
//			blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
//
//				int max = blockEntity.getGridLevel();
//				for (int i = 0; i < max; ++i) {
//					for (int j = 0; j < max; ++j) {
//						this.addSlot(new SlotItemHandler(h, WorkbenchBlockEntity.INVENTORY_SIZE + j + i * max,
//								31 + j * 18, 26 + i * 18) {
//
//							@Override
//							public void setChanged() {
//								super.setChanged();
//
//								WorkbenchMenu.this.slotsChanged(
//										new WorkstationRecipeWrapper(max, max, (IItemHandlerModifiable) h));
//							}
//						});
//					}
//				}
//
//				this.addSlot(new SlotItemHandler(h, WorkbenchBlockEntity.OUTPUT_SLOT, 143, 70) {
//					@Override
//					public boolean mayPlace(@Nonnull ItemStack stack) {
//						return false;
//					}
//				});
//
//				this.addSlot(new SlotItemHandler(h, WorkbenchBlockEntity.UPGRADE_4x4_SLOT, 181, 11) {
//					@Override
//					public boolean mayPlace(@Nonnull ItemStack stack) {
//						return stack.is(CompendiumTags.WORKSTATION_UPGRADE_INV4X) && isGridEmpty(this.getItemHandler());
//					}
//
//					@Override
//					public boolean mayPickup(Player playerIn) {
//						return this.getItemHandler().getStackInSlot(WorkbenchBlockEntity.UPGRADE_5x5_SLOT).isEmpty()
//								&& isGridEmpty(this.getItemHandler());
//					}
//				});
//
//				this.addSlot(new SlotItemHandler(h, WorkbenchBlockEntity.UPGRADE_5x5_SLOT, 181, 29) {
//					@Override
//					public boolean mayPlace(@Nonnull ItemStack stack) {
//						return !this.getItemHandler().getStackInSlot(WorkbenchBlockEntity.UPGRADE_4x4_SLOT).isEmpty()
//								&& stack.is(CompendiumTags.WORKSTATION_UPGRADE_INV5X)
//								&& isGridEmpty(this.getItemHandler());
//					}
//
//					@Override
//					public boolean mayPickup(Player playerIn) {
//						return isGridEmpty(this.getItemHandler());
//					}
//				});
//
//				this.addSlot(new SlotItemHandler(h, WorkbenchBlockEntity.UPGRADE_LIGHT_SLOT, 181, 47) {
//					@Override
//					public boolean mayPlace(@Nonnull ItemStack stack) {
//						return stack.is(CompendiumTags.WORKSTATION_UPGRADE_LIGHT);
//					}
//				});
//
//				this.addSlot(new SlotItemHandler(h, WorkbenchBlockEntity.UPGRADE_ENERGY, 181, 65) {
//					@Override
//					public boolean mayPlace(@Nonnull ItemStack stack) {
//						return stack.is(CompendiumTags.WORKSTATION_UPGRADE_POWER);
//					}
//
//					@Override
//					public boolean mayPickup(Player playerIn) {
//						return this.getItemHandler().getStackInSlot(WorkbenchBlockEntity.UPGRADE_BATTERY).isEmpty();
//					}
//				});
//
//				this.addSlot(new SlotItemHandler(h, WorkbenchBlockEntity.UPGRADE_BATTERY, 181, 83) {
//					@Override
//					public boolean mayPlace(@Nonnull ItemStack stack) {
//						return stack.is(CompendiumTags.WORKSTATION_UPGRADE_BATTERY);
//					}
//				});
//			});
//		}
//
//		toolList = NonNullList.create();
//		view = this.addSlot(new Slot(new SimpleContainer(1), 0, 143, 44) {
//			@Override
//			public boolean mayPickup(Player playerIn) {
//				return false;
//			}
//
//			@Override
//			@OnlyIn(Dist.CLIENT)
//			public boolean isActive() {
//				return true;
//			}
//
//			@Override
//			public boolean mayPlace(@Nonnull ItemStack stack) {
//				return false;
//			}
//		});
//
//		for (int k = 0; k < 3; ++k) {
//			for (int i1 = 0; i1 < 9; ++i1) {
//				this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 111 + k * 18));
//			}
//		}
//
//		for (int l = 0; l < 9; ++l) {
//			this.addSlot(new Slot(playerInventory, l, 8 + l * 18, 169));
//		}

	}

	boolean isGridEmpty(IItemHandler iItemHandler) {
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				if (!iItemHandler.getStackInSlot(WorkbenchBlockEntity.INVENTORY_SIZE + j + i * 5).isEmpty())
					return false;
			}
		}
		return true;
	}

	public void zeroStrikes() {

	}

	public void clear() {
		zeroStrikes();
	}

	@Override
	public boolean stillValid(Player playerIn) {
		return true;
	}

	/**
	 * Callback for when the crafting matrix is changed.
	 */
	@Override
	public void slotsChanged(Container inventoryIn) {
//		RecipeHolder<WorkbenchRecipe> recipe = this.world.getRecipeManager().getRecipeFor(
//				WorkstationRecipes.WORKSTATION_RECIPE.get(), (WorkstationRecipeWrapper) inventoryIn, this.world).get();
//		if (recipe != null) {
//			ItemStack result = recipe.value().getCraftingResult((WorkstationRecipeWrapper) inventoryIn);
//			this.view.set(result);
//			this.toolList = recipe.value().getToolList();
//			// this.maxStrikes = recipe.get().getStrikes();
//		} else {
//			// this.output.set(ItemStack.EMPTY);
//			this.view.set(ItemStack.EMPTY);
//		}
//
//		Collection<RecipeHolder<WorkbenchRecipe>> r = this.world.getRecipeManager()
//				.getAllRecipesFor(WorkstationRecipes.WORKSTATION_RECIPE.get());
//		MultiToolRecipeShaped r2 = matchRecipe((WorkstationRecipeWrapper) inventoryIn);
		// zeroStrikes();
		super.slotsChanged(inventoryIn);
	}

	private MultiToolRecipeShaped matchRecipe(WorkstationRecipeWrapper inventoryIn) {
		if (world != null) {
			return world.getRecipeManager().getRecipes().stream()
					.filter(recipe -> recipe.value() instanceof MultiToolRecipeShaped)
					.map(recipe -> (MultiToolRecipeShaped) recipe.value())
					.filter(recipe -> recipe.matches(inventoryIn, this.world)).findFirst().orElse(null);
		}
		return null;
	}

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
//				if (index == 0) {
//					playerIn.dropItem(itemstack2, false);
//				}
		}

		return itemstack;
	}
}