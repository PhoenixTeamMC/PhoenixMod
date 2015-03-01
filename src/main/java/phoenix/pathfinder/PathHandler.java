package phoenix.pathfinder;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class PathHandler {

	static {
		try
		{
			FileInputStream fileIn = new FileInputStream("/pathfinder/not_done.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			HashMap<Long, Boolean> hash = (HashMap<Long, Boolean>) in.readObject();
			in.close();
			fileIn.close();
			if (hash == null) { HashMap<Long, Boolean> not_done = new HashMap<Long, Boolean>();}
		}catch(IOException i)
		{
			HashMap<Long, Boolean> not_done = new HashMap<Long, Boolean>();		}catch(ClassNotFoundException c)
		{
			HashMap<Long, Boolean> not_done = new HashMap<Long, Boolean>();		}
	}

	private HashMap<Long, Boolean> getNot_Done() {
		try {
			FileInputStream fileIn = new FileInputStream("/pathfinder/not_done.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Object o = in.readObject();
			if (o instanceof HashMap) {
				return (HashMap<Long, Boolean>) o;
			} else {
				return new HashMap<Long, Boolean>();
			}
		} catch(IOException i) {
			return new HashMap<Long, Boolean>();
		} catch(ClassNotFoundException c) {
			return new HashMap<Long, Boolean>();
		}
	}

	@SubscribeEvent
	public final void onLogin(EntityJoinWorldEvent e) {
		Boolean identifier = getNot_Done().get(e.world.getSeed());
		FMLLog.info("[PathFinder] onLogin triggered");
		if (identifier == null || identifier) {
			long was = e.world.getSeed();
			e.world.setRandomSeed((int )(Math. random() * 100 + 1), (int )(Math. random() * 100 + 1), (int )(Math. random() * 100 + 1));
			long seed = e.world.getSeed();
			getNot_Done().put(seed, false);
			FMLLog.info("[PathFinder] randomized seed. Seed was: " + was + ". Seed is now: " + seed);
			try
			{
				FileOutputStream fileOut = new FileOutputStream("/pathfinder/not_done.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(e);
				out.close();
				fileOut.close();
				FMLLog.info("[PathFinder] Saved not_done hashmap");
			} catch(IOException i)
			{
				i.printStackTrace();
			}
		}
	}

	@SubscribeEvent
	public final void onUpdate(LivingEvent.LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			if (Minecraft.getMinecraft().gameSettings.difficulty != EnumDifficulty.PEACEFUL) {
				Minecraft.getMinecraft().gameSettings.difficulty = EnumDifficulty.PEACEFUL;
				Minecraft.getMinecraft().gameSettings.saveOptions();
			}
		}
	}
}