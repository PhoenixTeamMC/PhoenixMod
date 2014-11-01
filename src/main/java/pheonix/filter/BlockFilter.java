package pheonix.filter;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFilter extends Block  implements ITileEntityProvider{
	
	protected BlockFilter() {
		super(Material.iron);
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
	
	

}
