package phoenix.pathfinder;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import java.util.Random;

public class PathHandler {
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
