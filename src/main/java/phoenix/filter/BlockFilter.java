package phoenix.filter;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import phoenix.lib.BaseBlock;
import phoenix.main.PheonixMod;

public class BlockFilter extends BaseBlock  implements ITileEntityProvider{
	
	protected BlockFilter() {
		super("filter", Material.iron);
		this.setHardness(4);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityFilter();
	}
	
	public boolean canProvidePower(){
        return true;
    }

    public int isProvidingWeakPower(IBlockAccess block, int x, int y, int z, int side){
        TileEntityFilter filter  = (TileEntityFilter) block.getTileEntity(x, y, z);
        
        int slotToCheck;
        
        switch(side){
        
        case 0: slotToCheck = 4; break;
        case 1: slotToCheck = 5; break;
        case 2: slotToCheck = 1; break;
        case 3: slotToCheck = 0; break;
        case 4: slotToCheck = 3; break;
        case 5: slotToCheck = 2; break;
        default: slotToCheck = 0; break;
        
        }
        
        if(filter.filters[slotToCheck] == null){
        	return 0;
        }
        
        ItemStack[] inventory = filter.inventory;
        ItemStack stack =  filter.filters[slotToCheck];
        
        for(int i = 0; i < inventory.length; i++){
        	
        	ItemStack inv = inventory[i];
        	
        	if(inv != null && inv.isItemEqual(stack)){
        		return 15;
        	}
        }
        
        return 0;
    }
    
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if(!world.isRemote){
			player.openGui(PheonixMod.instance, 0, world, x, y, z);
			return true;
		}else{
			
			return true;
		}
	}

    public void breakBlock(World world, int x, int y, int z, Block block, int fortune)
    {
    	TileEntityFilter filter = (TileEntityFilter) world.getTileEntity(x, y, z);
    	
        if (filter != null)
        {
            for (int i1 = 0; i1 < filter.getSizeInventory(); ++i1)
            {
            	dropItemStack(filter.getStackInSlot(i1), world, x, y, z);

            }
            
            for(int i = 0; i < filter.filters.length; i++){
            	dropItemStack(filter.filters[i], world, x, y, z);
            }
        }

        super.breakBlock(world, x, y, z, block, fortune);
    }
    
    private Random random = new Random();
    
    private void dropItemStack(ItemStack itemstack, World world, int x, int y, int z){
    	if (itemstack != null)
        {
            float f = random.nextFloat() * 0.8F + 0.1F;
            float f1 = random.nextFloat() * 0.8F + 0.1F;
            EntityItem entityitem;

            for (float f2 = random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem))
            {
                int j1 = random.nextInt(21) + 10;

                if (j1 > itemstack.stackSize)
                {
                    j1 = itemstack.stackSize;
                }

                itemstack.stackSize -= j1;
                entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                float f3 = 0.05F;
                entityitem.motionX = (double)((float)random.nextGaussian() * f3);
                entityitem.motionY = (double)((float)random.nextGaussian() * f3 + 0.2F);
                entityitem.motionZ = (double)((float)random.nextGaussian() * f3);

                if (itemstack.hasTagCompound())
                {
                    entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                }
            }
        }
    }
	
	

}
