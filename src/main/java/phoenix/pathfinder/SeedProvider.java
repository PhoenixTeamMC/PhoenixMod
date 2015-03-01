package phoenix.pathfinder;

import net.minecraft.world.storage.WorldInfo;

import java.lang.reflect.Field;
import java.util.Random;

public abstract class SeedProvider {
    private static long seed = 0;

    private static void initSeed(Random rand) {
        if(seed!=0) {
            seed = rand.nextLong();
        }
    }

    public static void reflectSeed(WorldInfo worldInfo, Random rand) {
        initSeed(rand);
        Class worldClass = WorldInfo.class;
        Field seedField = null;
        try {
            seedField = worldClass.getField("randomSeed");
        } catch(Exception e) {}
        if(seedField!=null) {
            try {
                seedField.set(worldInfo, seed);
            } catch (Exception e) {
            }
        }
    }
}
