package com.lance5057.compendium.workstations.workbench;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.lance5057.compendium.CompendiumBlockEntities;
import com.lance5057.compendium.CompendiumItems;
import com.lance5057.compendium.util.ItemUtil;
import com.lance5057.compendium.workstations.WorkstationRecipes;
import com.lance5057.compendium.workstations._bases.blockentities.MultiToolRecipeStation;
import com.lance5057.compendium.workstations._bases.components.item.BlockEntityItemHandler;
import com.lance5057.compendium.workstations.containers.MultiToolRecipeWrapper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeManager.CachedCheck;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class WorkbenchBlockEntity extends MultiToolRecipeStation<WorkbenchRecipe> {
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
	public static final int UPGRADE_TIME = UPGRADE_BATTERY + 1;

	public static final int INVENTORY_SIZE = UPGRADE_TIME + 1;

	int gridLevel = 3; // 3=3x3 4=4x4 5=5x5
	boolean light = false;
	boolean powered = false;
	int powerLevel = 0;

	private final CachedCheck<MultiToolRecipeWrapper, WorkbenchRecipe> quickCheck = RecipeManager
			.createCheck(WorkstationRecipes.WORKBENCH_RECIPE.get());

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

	void useDistortion(Level pLevel, Player player, InteractionHand hand, ItemStack tool) {
		boolean b = true;
		while (b) {
			b = use(pLevel, player, hand, player.getItemInHand(hand));
		}

		Direction dir = this.getBlockState().getValue(WorkbenchBlock.FACING);
		int offX = 1, offZ = 1, tweakX = 0, tweakZ = 0;
		if (dir == Direction.EAST || dir == Direction.WEST)
			offX = 2;
		if (dir == Direction.NORTH || dir == Direction.SOUTH)
			offZ = 2;
		if (dir == Direction.WEST)
			tweakX = -1;
		if (dir == Direction.NORTH)
			tweakZ = -1;
		// This is dumb, bad lance

		for (int x = 0; x < 200; x++) {
			level.addParticle(ParticleTypes.REVERSE_PORTAL,
					this.worldPosition.getX() + tweakX + level.random.nextDouble() * offX,
					this.worldPosition.getY() + level.random.nextDouble() + 1,
					this.worldPosition.getZ() + tweakZ + level.random.nextDouble() * offZ, 0,
					0.01 + level.random.nextDouble() * 0.05, 0);
		}
		level.playSound(player, worldPosition, SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS, 1, 0);
	}

	@Override
	protected void playFinalSound(Player player) {
//		level.playSound(player, worldPosition, SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1, 0);
	}

	@Override
	public void finishRecipe(Player player, WorkbenchRecipe r) {
//		 {
//
//			List<Integer> l = new ArrayList<Integer>();
//			for (int i = 0; i < WorkbenchBlockEntity.CRAFTING_SLOTS; i++) {
//
//				int nsize = this.inventory.getStackInSlot(i).getCount();
//				if (nsize != 0)
//					l.add(nsize);
//			}
//
//			Collections.sort(l);
//			int minSize = l.get(0);
//
//			ItemStack i = r.assemble(MultiToolRecipeWrapper.of(5, 5, this.getInventory()), null);
//			i.setCount(minSize);
//			ItemStack s = this.getInventory().insertItem(OUTPUT_SLOT, i, false);
//			if (!s.isEmpty()) {
//				ItemUtil.giveOrDrop(s, player);
//			}
//			this.getInventory().shrinkRange(0, 25, minSize);
//
////			if (player.level().isClientSide) {
//			Level level = player.level();
//			
////			}
//		} else {
		ItemStack s = this.getInventory()
				.insertItem(OUTPUT_SLOT, r.assemble(MultiToolRecipeWrapper.of(5, 5, this.getInventory()), null), false)
				.copy();
		if (!s.isEmpty()) {
			ItemUtil.giveOrDrop(s, player);
		}
		this.getInventory().shrinkRange(0, 25);
//		}
	}

	@Override
	public Optional<RecipeHolder<WorkbenchRecipe>> matchRecipe() {
		if (this.level != null && this.getInventory() != null) {
			return this.quickCheck.getRecipeFor(MultiToolRecipeWrapper.of(5, 5, this.getInventory()), level);
		}
		return Optional.empty();
	}

	@Override
	protected void setupRecipe() {
		Optional<RecipeHolder<WorkbenchRecipe>> recipe = this.matchRecipe();

		if (recipe.isPresent()) {
			WorkbenchRecipe curRecipe = recipe.get().value();
			this.getInventory().setStackInSlot(PRODUCT_DISPLAY_SLOT,
					curRecipe
							.assemble(MultiToolRecipeWrapper.of(5, 5, this.getInventory()), this.level.registryAccess())
							.copy());
		} else {
			this.getInventory().setStackInSlot(PRODUCT_DISPLAY_SLOT, ItemStack.EMPTY.copy());
			this.zeroProgress();
		}
	}

	public String getDisplayName() {
		return "screen.workbench.name";
	}

	@Override
	protected BlockEntityItemHandler createItemHandler() {
		return new BlockEntityItemHandler(this, INVENTORY_SIZE) {
			@Override
			public boolean isItemValid(int slot, ItemStack stack) {
				switch (slot) {
				case UPGRADE_4x4_SLOT:
				case UPGRADE_5x5_SLOT:
				case UPGRADE_LIGHT_SLOT:
				case UPGRADE_ENERGY:
				case UPGRADE_BATTERY:
					return false;
				case UPGRADE_TIME:
					if (stack.is(CompendiumItems.TIME_DISTORTER))
						return true;
					else
						return false;
				}
				return true;
			}

			@Override
			protected void onContentsChanged(int slot) {
//				if (!this.getBe().getLevel().isClientSide) {
					if (this.getBe() instanceof WorkbenchBlockEntity wb) {
						if (!this.getStackInSlot(UPGRADE_5x5_SLOT).isEmpty())
							wb.gridLevel = 5;
						else if (!this.getStackInSlot(UPGRADE_4x4_SLOT).isEmpty())
							wb.gridLevel = 4;
						else
							wb.gridLevel = 3;

						if (slot >= 0 && slot < 25) {
							if (slot != PRODUCT_DISPLAY_SLOT) {
//							zeroProgress();
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
//			}
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
