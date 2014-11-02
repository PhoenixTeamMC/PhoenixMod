package pheonix.filter.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import pheonix.filter.TileEntityFilter;

public class FilterContainer extends Container{

	TileEntityFilter filter;
	
	public FilterContainer(TileEntityFilter filter){
		this.filter = filter;
		
		
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

}
