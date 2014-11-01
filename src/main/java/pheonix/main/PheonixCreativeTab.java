package pheonix.main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PheonixCreativeTab extends CreativeTabs
{

	public PheonixCreativeTab()
	{
		super("Pheonix Mod");
	}

	@Override
	public ItemStack getIconItemStack()
	{
		return new ItemStack(Items.apple);
	}

	@Override
	public Item getTabIconItem()
	{
		return Items.apple;
	}

}
