package pheonix.lib;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import pheonix.filter.TileEntityFilter;
import pheonix.filter.gui.FilterContainer;
import pheonix.filter.gui.FilterGui;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

	//Will work on this later, just getting Filters Working
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new FilterContainer((TileEntityFilter) world.getTileEntity(x, y, z));
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new FilterGui(new FilterContainer((TileEntityFilter) world.getTileEntity(x, y, z)));
	}
}
