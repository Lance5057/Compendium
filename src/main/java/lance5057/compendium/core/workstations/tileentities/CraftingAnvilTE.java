package lance5057.compendium.core.workstations.tileentities;

import java.util.Optional;

import lance5057.compendium.CompendiumTileEntities;
import lance5057.compendium.core.util.recipes.WorkstationRecipeWrapper;
import lance5057.compendium.core.workstations.WorkstationRecipes;
import lance5057.compendium.core.workstations.containers.CraftingAnvilContainer;
import lance5057.compendium.core.workstations.recipes.CraftingAnvilRecipe;
import lance5057.compendium.core.workstations.tileentities.bases.MultiToolRecipeStation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class CraftingAnvilTE extends MultiToolRecipeStation<CraftingAnvilRecipe> implements MenuProvider {
	public static final String SCREEN_TITLE = "screen.workstations.crafting_anvil";
//    private final LazyOptional<IItemInteractionHandlerModifiable> InteractionHandler = LazyOptional.of(this::createInteractionHandler);
	private ItemStack ghostStack = ItemStack.EMPTY;

	public CraftingAnvilTE(BlockPos pos, BlockState state) {
		super(27, 5, 5, CompendiumTileEntities.WORKSTATION_TE.get(), pos, state);
	}

	public void setGhostStack(ItemStack i) {
		this.ghostStack = i;
	}

	public ItemStack getGhostStack() {
		return this.ghostStack;
	}

	private static final TextComponent CONTAINER_NAME = new TextComponent("compendium.workstations.workstation");

	@Override
	protected IItemHandlerModifiable createInteractionHandler() {
		return new ItemStackHandler(27) {

			@Override
			protected void onContentsChanged(int slot) {
				updateInventory();
				if (slot != 25) {

					zeroProgress();
					Optional<CraftingAnvilRecipe> recipe = matchRecipe();

					setGhostStack(ItemStack.EMPTY);

					if (recipe.isPresent()) {
						setGhostStack(recipe.get().getRecipeOutput().copy());
						setRecipe(recipe);
					}
				}
			}
		};
	}

	@Override
	public void addParticle() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Optional<CraftingAnvilRecipe> matchRecipe() {
		if (level != null) {

			Optional<CraftingAnvilRecipe> recipe = handler.map(i -> {
				return level.getRecipeManager().getRecipeFor(WorkstationRecipes.CRAFTING_ANVIL_RECIPE.get(),
						new WorkstationRecipeWrapper(5, 5, i), level);
			}).get();

			// setRecipe(recipe);
			return recipe;
		}
		return Optional.empty();
	}

	@Override
	public void finishRecipe(Player Player, CraftingAnvilRecipe r) {
		handler.ifPresent(h -> {
			ItemStack item = r.getRecipeOutput().copy();
			BlockEntity te = level.getBlockEntity(this.getBlockPos().offset(0, -1, 0));
			if (te != null) {
				LazyOptional<IItemHandler> ih = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
						Direction.UP);

				if (h.getStackInSlot(26) == ItemStack.EMPTY) {

					item = ih.map(h2 -> dropItemBelow(h2, r.getRecipeOutput().copy())).get();
					if (item == null)
						craft();

				}
			}

			if (h.getStackInSlot(26) == ItemStack.EMPTY) {
				h.setStackInSlot(26, item);
				craft();
			}
		});
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
		return new CraftingAnvilContainer(id, this.worldPosition, inv, player);
	}

	@Override
	public Component getDisplayName() {
		// TODO Auto-generated method stub
		return new TranslatableComponent(SCREEN_TITLE);
	}

	@Override
	protected <T> LazyOptional<T> getExtraCapability(Capability<T> cap, Direction side) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void readExtraNBT(CompoundTag nbt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected CompoundTag writeExtraNBT(CompoundTag tag) {
		// TODO Auto-generated method stub
		return tag;
	}
}
