package pheonix.rain;

import java.lang.reflect.Field;

import net.minecraft.launchwrapper.Launch;
import net.minecraft.world.biome.BiomeGenBase;
import pheonix.main.IIntializer;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class RainInit implements IIntializer{

	public static final boolean developmentEnvironment = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
	
	@Override
	public String getModuleName() {
		return "rainEverywhere";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		
	}

	@Override
	public void init(FMLInitializationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		BiomeGenBase[] biomeList = BiomeGenBase.getBiomeGenArray();
		for(BiomeGenBase base : biomeList){
			
			try{
				if(developmentEnvironment){
					Field field = BiomeGenBase.class.getDeclaredField("enableRain");
					field.setAccessible(true);
					field.set(base, true);
				}else{
					Field field = BiomeGenBase.class.getDeclaredField("field_76765_S");
					field.setAccessible(true);
					field.set(base, true);
				}
				
				
			}catch(Exception e){
				
			}
			
			
		}
		
	}

}
