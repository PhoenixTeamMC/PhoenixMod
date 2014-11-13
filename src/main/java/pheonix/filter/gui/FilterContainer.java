package pheonix.filter.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import pheonix.filter.TileEntityFilter;
import pheonix.lib.gui.slot.LimitedSlot;

public class FilterContainer extends Container{

	TileEntityFilter filter;
	
	public FilterContainer(EntityPlayer player, TileEntityFilter filter){
		this.filter = filter;
		
		
		
		for(int i = 0; i < 6; i++){
			this.addSlotToContainer(new LimitedSlot(filter, i + 6, 39 + 18 * i, 21).setSize(1));
		}
		
		for(int i = 0; i < 6; i++){
			this.addSlotToContainer(new Slot(filter, i, 39 + 18 * i, 75));
		}
		
		this.bindPlayerInventory(player.inventory, 12, 101);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}
	
	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer, int x, int y) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, x + j * 18, y + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventoryPlayer, i, x + i * 18,
					y + 58));
		}
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		
      return null;
    }

}
