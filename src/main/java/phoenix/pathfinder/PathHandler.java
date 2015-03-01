package phoenix.pathfinder;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.HashMap;

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
	public final void onUpdate(LivingEvent.LivingUpdateEvent event) {
		if (event.entity instanceof EntityPlayer) {
			if (Minecraft.getMinecraft().gameSettings.difficulty != EnumDifficulty.PEACEFUL) {
				Minecraft.getMinecraft().gameSettings.difficulty = EnumDifficulty.PEACEFUL;
				Minecraft.getMinecraft().gameSettings.saveOptions();
			}
		}
	}
}