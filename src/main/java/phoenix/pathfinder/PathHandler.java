package phoenix.pathfinder;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;

import java.io.*;
import java.util.Random;
import java.util.HashMap;

public class PathHandler {
    private static boolean change = false;

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

    private void setNot_Done(long seed) {
        try {
            HashMap<Long, Boolean> map = getNot_Done();
            map.put(seed, false);
            FileOutputStream fileOut = new FileOutputStream("/pathfinder/not_done.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(map);
            out.close();
            fileOut.close();
            FMLLog.info("[PathFinder] Saved not_done hashmap");
        } catch(Exception e) {}
    }


    @SubscribeEvent
    public final void onWorldLoad(WorldEvent.Load event) {
        if(!change) {
            FMLLog.info("[PathFinder] world load triggered");
            Boolean identifier = getNot_Done().get(event.world.getSeed());
            if (identifier == null || identifier) {
                long oldSeed = event.world.getSeed();
                SeedProvider.reflectSeed(event.world.getWorldInfo(), new Random());
                long newSeed = event.world.getSeed();

                if(newSeed!=oldSeed) {
                    setNot_Done(newSeed);
                    change = true;
                    FMLLog.info("[PathFinder] randomized seed. Seed was: " + oldSeed + ". Seed is now: " + newSeed);
                }
                else {
                    FMLLog.info("Changing seed failed, this is the old seed");
                }
            }
            else {
                FMLLog.info("Seed for this world has been changed already");
                change = true;
            }
        }
    }
}

