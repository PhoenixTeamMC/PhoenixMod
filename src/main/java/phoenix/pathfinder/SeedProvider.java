package phoenix.pathfinder;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.world.storage.WorldInfo;

import java.lang.reflect.Field;
import java.util.Random;

public abstract class SeedProvider {

    public static void reflectSeed(WorldInfo worldInfo, Random rand) {
        long seed = rand.nextLong();
        FMLLog.info("Trying to set new seed to "+seed);
        Class worldClass = WorldInfo.class;
        Field seedField = null;
        try {
            seedField = worldClass.getField("randomSeed");
        } catch(Exception e) {
            FMLLog.info("Failed getting field");
        }
        if(seedField!=null) {
            try {
                seedField.set(worldInfo, seed);
            } catch (Exception e) {
                FMLLog.info("Failed setting new seed");
            }
        }
    }
}
