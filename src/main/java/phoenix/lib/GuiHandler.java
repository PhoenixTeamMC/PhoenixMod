package phoenix.lib;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import phoenix.filter.TileEntityFilter;
import phoenix.filter.gui.FilterContainer;
import phoenix.filter.gui.FilterGui;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

	//Will work on this later, just getting Filters Working
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new FilterContainer(player, (TileEntityFilter) world.getTileEntity(x, y, z));
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new FilterGui(new FilterContainer(player, (TileEntityFilter) world.getTileEntity(x, y, z)));
	}
}
