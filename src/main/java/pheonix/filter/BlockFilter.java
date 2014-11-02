package pheonix.filter;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import pheonix.lib.BaseBlock;
import pheonix.main.PheonixMod;

public class BlockFilter extends BaseBlock  implements ITileEntityProvider{
	
	protected BlockFilter() {
		super("filter", Material.iron);
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
        
        if(filter.inventory[side].isItemEqual(filter.filters[side])){
        	return 15;
        }
        
        return 0;
    }
    
    @Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if(world.isRemote){
			return true;
		}else{
			player.openGui(PheonixMod.instance, 0, world, x, y, z);
			return true;
		}
	}
	
	

}
