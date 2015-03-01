package phoenix.pathfinder;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import phoenix.main.IIntializer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import java.util.HashMap;

public class Pathfinder implements IIntializer {

	@Override
	public String getModuleName() {
		return "Pathfinder";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new PathHandler());
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		// TODO Auto-generated method stub
	}

	public class PathHandler {

		public HashMap<Long, Boolean> not_done = new HashMap<Long, Boolean>();

		@SubscribeEvent
		public final void onLogin(EntityJoinWorldEvent e) {
			Boolean identifier = not_done.get(e.world.getSeed());
			if (identifier == null || identifier) {
				e.world.setRandomSeed((int )(Math. random() * 100 + 1), (int )(Math. random() * 100 + 1), (int )(Math. random() * 100 + 1));
				long seed = e.world.getSeed();
				not_done.put(seed, false);
			}
		}

		@SubscribeEvent
		public final void onUpdate(LivingUpdateEvent event) {
			if (event.entity instanceof EntityPlayer) {
				if (Minecraft.getMinecraft().gameSettings.difficulty != EnumDifficulty.PEACEFUL) {
					Minecraft.getMinecraft().gameSettings.difficulty = EnumDifficulty.PEACEFUL;
					Minecraft.getMinecraft().gameSettings.saveOptions();
				}
			}
		}
	}
}

