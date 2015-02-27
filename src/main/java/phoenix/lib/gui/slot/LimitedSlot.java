package phoenix.lib.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class LimitedSlot extends Slot{

	int limit = 64;
	
	public LimitedSlot(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}

	public Slot setSize(int i){
		this.limit = i;
		return this;
	}

	@Override
	public int getSlotStackLimit() {
		return limit;
	}

}
