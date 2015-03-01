package phoenix.pathfinder;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import phoenix.util.ReflectionUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class PathHandler {
	
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
			ReflectionUtil.changeFieldFromObj(e.world.getWorldInfo(), "randomSeed", (long) (Math.random() * 10000 + 1));
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
}
