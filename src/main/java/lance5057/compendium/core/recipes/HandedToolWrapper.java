package lance5057.compendium.core.recipes;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class HandedToolWrapper implements IInventory {

    protected ItemStack tool;
    protected ItemStack block;
    protected ItemStack offhand; //Optional
    
    public HandedToolWrapper(ItemStack tool, ItemStack block, ItemStack offhand)
	{
		this.tool = tool;
		this.block = block;
		this.offhand = offhand;
	}
    
    @Override
    public void clear() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public int getSizeInventory() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public boolean isEmpty() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void markDirty() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
	// TODO Auto-generated method stub
	return false;
    }

}
