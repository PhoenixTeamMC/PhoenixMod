package pheonix.filter;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

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
		if(slot < 6){
			return inventory[slot];
			
		}else{
			return filters[slot - 6];
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack)
	{

		if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
		{
			itemStack.stackSize = this.getInventoryStackLimit();
		}

		if(slot < 6){
			inventory[slot] = itemStack;
			
		}else{
			
			filters[slot - 6] = itemStack;
		}
		
		this.markDirty();
		
		World world = this.getWorldObj();
		
		Block block = world.getBlock(xCoord, yCoord, zCoord);
		if(!world.isRemote){
			world.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, block);
		}
		
	}

	
	@Override
	public ItemStack decrStackSize(int slot, int decrementAmount)
	{
		ItemStack itemStack = getStackInSlot(slot);
		if (itemStack != null)
		{
			if (itemStack.stackSize <= decrementAmount)
			{
				setInventorySlotContents(slot, null);
			}
			else
			{
				itemStack = itemStack.splitStack(decrementAmount);
				if (itemStack.stackSize == 0)
				{
					setInventorySlotContents(slot, null);
				}
			}
		}

		return itemStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		
		if (this.getStackInSlot(slot) != null)
		{
			ItemStack itemStack = this.getStackInSlot(slot);
			this.setInventorySlotContents(slot, null);
			return itemStack;
		}
		else
		{
			return null;
		}
	}

	
	public ItemStack[] getInventoryForSlot(int slot){
		if(slot < 6){
			return inventory;
			
		}else{
			return filters;
		}
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
		return 64;
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
	
	/**
	 * Reads the inventory from nbt
	 */
	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);

		NBTTagList list = nbt.getTagList("Inventory", Constants.NBT.TAG_COMPOUND);
		this.inventory = new ItemStack[this.inventory.length];
		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound tag = list.getCompoundTagAt(i);
			int slot = tag.getInteger("Slot");

			if ((slot >= 0) && (slot < this.inventory.length)) {
				this.inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
		
		NBTTagList list2 = nbt.getTagList("Filters", Constants.NBT.TAG_COMPOUND);
		this.filters = new ItemStack[this.filters.length];
		for (int i = 0; i < list2.tagCount(); i++) {
			NBTTagCompound tag = list2.getCompoundTagAt(i);
			int slot = tag.getInteger("Slot");

			if ((slot >= 0) && (slot < this.inventory.length)) {
				this.filters[slot] = ItemStack.loadItemStackFromNBT(tag);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);

		if (this.inventory.length <= 0) {
			return;
		}
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < this.inventory.length; i++) {
			if (this.inventory[i] != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("Slot", i);
				this.inventory[i].writeToNBT(tag);
				list.appendTag(tag);
			}
		}
		nbt.setTag("Inventory", list);
		
		NBTTagList list2 = new NBTTagList();
		for (int i = 0; i < this.filters.length; i++) {
			if (this.filters[i] != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("Slot", i);
				this.filters[i].writeToNBT(tag);
				list2.appendTag(tag);
			}
		}
		nbt.setTag("Filters", list2);
		
		
	}

}
