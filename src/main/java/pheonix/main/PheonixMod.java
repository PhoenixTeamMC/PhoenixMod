package pheonix.main;

import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Logger;

import pheonix.filter.Filter;
import pheonix.main.lib.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class PheonixMod {
	
	public static final Map<IIntializer, Boolean> modules = new HashMap<IIntializer, Boolean>(); //List of modules, to be initialized later
	
	static{ //Use this to add a module.
		modules.put(new Filter(), true);
	}
	
	Configuration config;
	
	@Instance(Reference.MODID)
	public static PheonixMod instance;
	
	public static final PheonixCreativeTab creativeTab = new PheonixCreativeTab();
	
	public Logger log;
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event){
        
		log = event.getModLog();
		
		config = new Configuration(event.getSuggestedConfigurationFile());
		
		for(IIntializer module : modules.keySet()){
			
			boolean shouldInit = config.get("Modules", module.getModuleName(), modules.get(module)).getBoolean();
			
			if(shouldInit){
				module.preInit(event);
			}
			
			modules.put(module, shouldInit);
			
		}
		
    }
    
	@EventHandler
	public void init(FMLInitializationEvent event){
		for(IIntializer module : modules.keySet()){

			if(modules.get(module)){
				module.init(event);
			}

		}
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		for(IIntializer module : modules.keySet()){

			if(modules.get(module)){
				module.postInit(event);
			}

		}
	}

}
