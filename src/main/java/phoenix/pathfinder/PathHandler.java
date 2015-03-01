package phoenix.pathfinder;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import java.util.Random;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
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
		} catch(ClassNotFoundException c) {
			return new HashMap<Long, Boolean>();
		} catch(FileNotFoundException f) {
			return new HashMap<Long, Boolean>();
		} catch (IOException e) {
			return new HashMap<Long, Boolean>();
		}
	}


    @SubscribeEvent
    public final void onLogin(EntityJoinWorldEvent event) {
        FMLLog.info("[PathFinder] onLogin triggered");
        boolean changedSeed = true;
        if (!changedSeed) {
            long oldSeed = event.world.getSeed();
            SeedProvider.reflectSeed(event.world.getWorldInfo(), new Random());
            long newSeed = event.world.getSeed();
            FMLLog.info("[PathFinder] randomized seed. Seed was: " + oldSeed + ". Seed is now: " + newSeed);
        }
    }
}
}
