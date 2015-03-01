package phoenix.pathfinder;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import phoenix.main.IIntializer;

public class Pathfinder implements IIntializer {

	@Override
	public String getModuleName() {
		return "Pathfinder";
	}

    @Override
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Override
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new PathHandler());
	}

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }
}

