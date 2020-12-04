package lance5057.compendium.core.workstations.tileentities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lance5057.compendium.TCTileEntities;
import lance5057.compendium.core.workstations.recipes.HammeringStationRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class HammeringStationTE extends TileEntity {
	private final LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);
	private ItemStack lastStack = ItemStack.EMPTY;
	private int progress = 0;

	public HammeringStationTE() {
		super(TCTileEntities.HAMMERING_STATION_TE.get());
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
		if (side != Direction.DOWN)
			if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
				return handler.cast();
			}
		return super.getCapability(cap, side);
	}

	private HammeringStationRecipe matchRecipe(ItemStack stackInSlot) {
		if (world != null) {
			return world.getRecipeManager().getRecipes().stream()
					.filter(recipe -> recipe instanceof HammeringStationRecipe)
					.map(recipe -> (HammeringStationRecipe) recipe).filter(recipe -> recipe.matches(stackInSlot))
					.findFirst().orElse(null);
		}
		return null;
	}

	private IItemHandler createHandler() {
		return new ItemStackHandler(1) {
			@Override
			protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
				return stack.getMaxStackSize();
			}

			@Override
			protected void onContentsChanged(int slot) {
				updateInventory();
			}

			@Override
			public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
				HammeringStationRecipe r = matchRecipe(stack);
				return r != null && super.isItemValid(slot, stack);
			}
		};
	}

	public void extractInsertItem(PlayerEntity playerEntity, Hand hand) {
		handler.ifPresent(inventory -> {
			ItemStack held = playerEntity.getHeldItem(hand);
			if (!held.isEmpty()) {
				insertItem(inventory, held);
			} else {
				extractItem(playerEntity, inventory);
			}
		});
		updateInventory();
	}

	public void extractItem(PlayerEntity playerEntity, IItemHandler inventory) {
		if (!inventory.getStackInSlot(0).isEmpty()) {
			ItemStack itemStack = inventory.extractItem(0, inventory.getStackInSlot(0).getCount(), false);
			playerEntity.addItemStackToInventory(itemStack);
		}
		updateInventory();
	}

	public void insertItem(IItemHandler inventory, ItemStack heldItem) {
		if (inventory.isItemValid(0, heldItem))
			if (!inventory.insertItem(0, heldItem, true).isItemEqual(heldItem)) {
				final int leftover = inventory.insertItem(0, heldItem.copy(), false).getCount();
				heldItem.setCount(leftover);
			}
		updateInventory();
	}

	// External extract handler
	public void extractItem(PlayerEntity playerEntity) {
		handler.ifPresent(inventory -> {
			this.extractItem(playerEntity, inventory);
		});
	}

	// External insert handler
	public void insertItem(ItemStack heldItem) {
		handler.ifPresent(inventory -> {
			this.insertItem(inventory, heldItem);
		});
	}

	public void updateInventory() {
		requestModelDataUpdate();
		this.markDirty();
		if (this.getWorld() != null) {
			this.getWorld().notifyBlockUpdate(pos, this.getBlockState(), this.getBlockState(), 3);
		}
	}

	public void hammer(PlayerEntity playerEntity, ItemStack hammer) {
		handler.ifPresent(inventory -> {
			if (lastStack != inventory.getStackInSlot(0)) {
				progress = 0;
				lastStack = inventory.getStackInSlot(0);
			} else if (lastStack != ItemStack.EMPTY && lastStack.getItem() != Items.AIR) {
				this.progress++;
				hammer.damageItem(1, playerEntity, null);
				world.addParticle(new ItemParticleData(ParticleTypes.ITEM, lastStack), pos.getX() + 0.5f,
						pos.getY() + 1, pos.getZ() + 0.5f, (world.rand.nextFloat() - 0.5f) / 2,
						(world.rand.nextFloat() - 0.5f) / 2, (world.rand.nextFloat() - 0.5f) / 2);
				world.playSound(playerEntity, pos, SoundEvents.BLOCK_STONE_HIT, SoundCategory.BLOCKS,
						world.rand.nextFloat() + 0.5f, 0);
			}
			HammeringStationRecipe recipe = matchRecipe(inventory.getStackInSlot(0));
			if (recipe != null) {
				if (this.progress >= recipe.getStrikes()) {

					for (int i = 0; i < 5; i++) {
						world.addParticle(new ItemParticleData(ParticleTypes.ITEM, lastStack), pos.getX() + 0.5f,
								pos.getY() + 1, pos.getZ() + 0.5f, (world.rand.nextFloat() - 0.5f) / 2,
								(world.rand.nextFloat() - 0.5f) / 2, (world.rand.nextFloat() - 0.5f) / 2);
					}
					world.playSound(playerEntity, pos, SoundEvents.BLOCK_BASALT_BREAK, SoundCategory.BLOCKS,
							1, 0);

					progress = 0;

					TileEntity te = world.getTileEntity(this.getPos().add(0, -1, 0));
					ItemStack item = recipe.getRecipeOutput().copy();
					if (te != null) {
						LazyOptional<IItemHandler> ih = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
								Direction.UP);

						item = ih.map(h -> dropItemBelow(h, recipe.getRecipeOutput().copy())).get();
					}

					InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), item);
					// this.lastStack.shrink(1);
					inventory.getStackInSlot(0).shrink(1);

				}
			}
		});
		this.updateInventory();
	}

	ItemStack dropItemBelow(IItemHandler handler, ItemStack insert) {
		for (int i = 0; i < handler.getSlots(); i++) {
			insert = handler.insertItem(i, insert, false);

			if (insert.isEmpty()) {
				return ItemStack.EMPTY;
			}
		}

		return insert;
	}

	@Nullable
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(this.getPos(), -1, this.getUpdateTag());
	}

//    @Override
//    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
//        handleUpdateTag(pkt.getNbtCompound());
//    }

	@Override
	@Nonnull
	public CompoundNBT getUpdateTag() {
		CompoundNBT updateTag = new CompoundNBT();
		final IItemHandler itemHandler = getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				.orElseGet(this::createHandler);
		CompoundNBT itemSlot = new CompoundNBT();
		itemHandler.getStackInSlot(0).write(itemSlot);
		updateTag.put("item", itemSlot);
		return updateTag;
	}

//    @Override
//    public void handleUpdateTag(CompoundNBT tag) {
//        final IItemHandler itemHandler = getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseGet(this::createHandler);
//        ((ItemStackHandler) itemHandler).setStackInSlot(1, ItemStack.read(tag.getCompound("item")));
//    }

	@Override
	public void read(BlockState state, @Nonnull CompoundNBT nbt) {
		super.read(state, nbt);
		progress = nbt.getInt("progress");
		lastStack.deserializeNBT(nbt.getCompound("lastStack"));
		handler.ifPresent(iItemHandler -> {
			if (iItemHandler instanceof ItemStackHandler) {
				((ItemStackHandler) iItemHandler).deserializeNBT(nbt.getCompound("inventory"));
			}
		});
	}

	@Override
	@Nonnull
	public CompoundNBT write(@Nonnull CompoundNBT nbt) {
		super.write(nbt);
		nbt.putInt("progress", progress);
		nbt.put("lastStack", lastStack.serializeNBT());
		handler.ifPresent(iItemHandler -> {
			if (iItemHandler instanceof ItemStackHandler) {
				nbt.put("inventory", ((ItemStackHandler) iItemHandler).serializeNBT());
			}
		});
		return nbt;
	}
}
