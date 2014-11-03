package pheonix.filter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFilter extends TileEntity implements IInventory{

	public ItemStack[] inventory;

	public ItemStack[] filters;

	public TileEntityFilter(){
		inventory = new ItemStack[6];

		filters = new ItemStack[6];
	}

	@Override
	public int getSizeInventory() {
		return 6;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		try {
			return inventory[slot];
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ItemStack decrStackSize(int slotIndex, int decrementAmount)
	{
		ItemStack itemStack = getStackInSlot(slotIndex);
		if (itemStack != null)
		{
			if (itemStack.stackSize <= decrementAmount)
			{
				setInventorySlotContents(slotIndex, null);
			}
			else
			{
				itemStack = itemStack.splitStack(decrementAmount);
				if (itemStack.stackSize == 0)
				{
					setInventorySlotContents(slotIndex, null);
				}
			}
		}

		return itemStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex)
	{
		if (inventory[slotIndex] != null)
		{
			ItemStack itemStack = inventory[slotIndex];
			inventory[slotIndex] = null;
			return itemStack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
	{
		inventory[slotIndex] = itemStack;

		if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
		{
			itemStack.stackSize = this.getInventoryStackLimit();
		}


		this.markDirty();
	}

	@Override
	public String getInventoryName() {
		return "filter";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}

}
