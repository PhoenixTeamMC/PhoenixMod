package pheonix.filter;

import net.minecraft.block.Block;
import pheonix.main.IIntializer;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class Filter implements IIntializer{

	public Block filter;
	
	@Override
	public String getModuleName() {
		return "Filter";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		filter = new BlockFilter();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerTileEntity(TileEntityFilter.class, "filter");
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		
	}

}
