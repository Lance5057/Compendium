package com.lance5057.compendium.blocks.RecipeToolSupplier;

import com.lance5057.compendium.workstations._bases.blockentities.MultiToolRecipeStation;
import com.lance5057.compendium.workstations._bases.components.item.BlockEntityItemHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.Lazy;

public abstract class RecipeToolSupplierBlockEntity extends BlockEntity {
	public RecipeToolSupplierBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
		super(type, pos, blockState);
//		searchForWorkstations();
	}

	private final BlockEntityItemHandler inventory = createItemHandler();
	private final Lazy<BlockEntityItemHandler> itemHandler = Lazy.of(() -> inventory);

	protected abstract BlockEntityItemHandler createItemHandler();

	protected abstract boolean canAccept(ItemStack stack);

	public ItemStack supply(Player player, InteractionHand hand, Ingredient itemToGet, int amountNeeded) {
		for (int i = 0; i < inventory.getSlots(); i++) {
			if (itemToGet.test(inventory.getStackInSlot(i)) && inventory.getStackInSlot(i).getCount() >= amountNeeded) {

				ItemStack give = inventory.getStackInSlot(i).copy();
				give.setCount(amountNeeded);

				inventory.getStackInSlot(i).shrink(amountNeeded);

				return give;
			}
		}

		return null;
	}

	public void searchForWorkstations(Level l) {
		for (int x = worldPosition.getX() - 5; x <= worldPosition.getX() + 5; x++)
			for (int y = worldPosition.getY() - 5; y <= worldPosition.getY() + 5; y++)
				for (int z = worldPosition.getZ() - 5; z <= worldPosition.getZ() + 5; z++) {
					BlockPos pos = new BlockPos(x, y, z);
					BlockEntity ent = l.getBlockEntity(pos);

					if (ent instanceof MultiToolRecipeStation<?> mtrs)
						mtrs.toolSuppliers.add(this.worldPosition);
				}

	}
}
