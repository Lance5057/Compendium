package lance5057.compendium.core.workstations.workstation;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Nonnull;

import lance5057.compendium.CompendiumContainers;
import lance5057.compendium.CompendiumTags;
import lance5057.compendium.core.util.recipes.WorkstationRecipeWrapper;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations._bases.recipes.AnimatedRecipeItemUse;
import lance5057.compendium.core.workstations._bases.recipes.multitoolrecipe.MultiToolRecipeShaped;
import net.minecraft.core.BlockPos;
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
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;

public class WorkstationContainer extends AbstractContainerMenu {
	// private final CraftingInventory craftMatrix = new CraftingInventory(this, 5,
	// 5);
	// private final CraftResultInventory craftResult = new CraftResultInventory();
	private WorkstationTE blockEntity;
	private final Level world;
	private final Player player;
	// private ItemStackHandler inv;v

	private Slot output;
	private Slot view;

	public NonNullList<AnimatedRecipeItemUse> toolList;

//		public static CraftingAnvilContainer createContainerServerSide(int windowID, Inventory playerInventory,
//				ItemStackHandler inv) {
//			return new CraftingAnvilContainer(windowID, playerInventory, inv);
//		}
	//
//		public static CraftingAnvilContainer createContainerClientSide(int windowID, Inventory playerInventory,
//				net.minecraft.network.FriendlyByteBuf extraData) {
	//
//			return new CraftingAnvilContainer(windowID, playerInventory, new ItemStackHandler(27));
//		}

	public WorkstationContainer(int windowId, BlockPos pos, Inventory playerInventory, Player player) {
		super(CompendiumContainers.WORKSTATION_CONTAINER.get(), windowId);
		blockEntity = (WorkstationTE) player.getCommandSenderWorld().getBlockEntity(pos);
		this.player = playerInventory.player;
		this.world = playerInventory.player.level;

		if (blockEntity != null) {
			blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {

				int max = blockEntity.getGridLevel();
				for (int i = 0; i < max; ++i) {
					for (int j = 0; j < max; ++j) {
						this.addSlot(new SlotItemHandler(h, WorkstationTE.INVENTORY_SIZE + j + i * max, 31 + j * 18,
								26 + i * 18) {

							@Override
							public void setChanged() {
								super.setChanged();

								WorkstationContainer.this.slotsChanged(
										new WorkstationRecipeWrapper(max, max, (IItemHandlerModifiable) h));
							}
						});
					}
				}

				this.addSlot(new SlotItemHandler(h, WorkstationTE.OUTPUT_SLOT, 143, 70) {
					@Override
					public boolean mayPlace(@Nonnull ItemStack stack) {
						return false;
					}
				});

				this.addSlot(new SlotItemHandler(h, WorkstationTE.UPGRADE_4x4_SLOT, 181, 11) {
					@Override
					public boolean mayPlace(@Nonnull ItemStack stack) {
						return stack.is(CompendiumTags.WORKSTATION_UPGRADE_INV4X) && isGridEmpty(this.getItemHandler());
					}

					@Override
					public boolean mayPickup(Player playerIn) {
						return this.getItemHandler().getStackInSlot(WorkstationTE.UPGRADE_5x5_SLOT).isEmpty()
								&& isGridEmpty(this.getItemHandler());
					}
				});

				this.addSlot(new SlotItemHandler(h, WorkstationTE.UPGRADE_5x5_SLOT, 181, 29) {
					@Override
					public boolean mayPlace(@Nonnull ItemStack stack) {
						return !this.getItemHandler().getStackInSlot(WorkstationTE.UPGRADE_4x4_SLOT).isEmpty()
								&& stack.is(CompendiumTags.WORKSTATION_UPGRADE_INV5X)
								&& isGridEmpty(this.getItemHandler());
					}

					@Override
					public boolean mayPickup(Player playerIn) {
						return isGridEmpty(this.getItemHandler());
					}
				});

				this.addSlot(new SlotItemHandler(h, WorkstationTE.UPGRADE_LIGHT_SLOT, 181, 47) {
					@Override
					public boolean mayPlace(@Nonnull ItemStack stack) {
						return stack.is(CompendiumTags.WORKSTATION_UPGRADE_LIGHT);
					}
				});

				this.addSlot(new SlotItemHandler(h, WorkstationTE.UPGRADE_ENERGY, 181, 65) {
					@Override
					public boolean mayPlace(@Nonnull ItemStack stack) {
						return stack.is(CompendiumTags.WORKSTATION_UPGRADE_POWER);
					}

					@Override
					public boolean mayPickup(Player playerIn) {
						return this.getItemHandler().getStackInSlot(WorkstationTE.UPGRADE_BATTERY).isEmpty();
					}
				});

				this.addSlot(new SlotItemHandler(h, WorkstationTE.UPGRADE_BATTERY, 181, 83) {
					@Override
					public boolean mayPlace(@Nonnull ItemStack stack) {
						return stack.is(CompendiumTags.WORKSTATION_UPGRADE_BATTERY);
					}
				});
			});
		}

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

		for (int k = 0; k < 3; ++k) {
			for (int i1 = 0; i1 < 9; ++i1) {
				this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 111 + k * 18));
			}
		}

		for (int l = 0; l < 9; ++l) {
			this.addSlot(new Slot(playerInventory, l, 8 + l * 18, 169));
		}

	}

	boolean isGridEmpty(IItemHandler iItemHandler) {
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				if (!iItemHandler.getStackInSlot(WorkstationTE.INVENTORY_SIZE + j + i * 5).isEmpty())
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
		Optional<WorkstationRecipe> recipe = this.world.getRecipeManager().getRecipeFor(
				WorkstationRecipes.WORKSTATION_RECIPE.get(), (WorkstationRecipeWrapper) inventoryIn, this.world);
		if (recipe.isPresent()) {
			ItemStack result = recipe.get().getCraftingResult((WorkstationRecipeWrapper) inventoryIn);
			this.view.set(result);
			this.toolList = recipe.get().getToolList();
			// this.maxStrikes = recipe.get().getStrikes();
		} else {
			// this.output.set(ItemStack.EMPTY);
			this.view.set(ItemStack.EMPTY);
		}

		Collection<WorkstationRecipe> r = this.world.getRecipeManager()
				.getAllRecipesFor(WorkstationRecipes.WORKSTATION_RECIPE.get());
		MultiToolRecipeShaped r2 = matchRecipe((WorkstationRecipeWrapper) inventoryIn);
		// zeroStrikes();
		super.slotsChanged(inventoryIn);
	}

	private MultiToolRecipeShaped matchRecipe(WorkstationRecipeWrapper inventoryIn) {
		if (world != null) {
			return world.getRecipeManager().getRecipes().stream()
					.filter(recipe -> recipe instanceof MultiToolRecipeShaped)
					.map(recipe -> (MultiToolRecipeShaped) recipe)
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