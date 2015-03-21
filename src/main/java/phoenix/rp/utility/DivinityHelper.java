package phoenix.rp.utility;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.HashMap;

public class DivinityHelper {
    public static HashMap<String, String> gods = new HashMap<String, String>();

    /** checks if a player has god status */
    public static boolean isGod(EntityPlayer player)
    {
        return gods.get(player.getDisplayName())!=null;
    }

    /** gets the faction of a god, if the player is not a god, returns null */
    public static String getFaction(EntityPlayer player)
    {
        if(!isGod(player)) {
            return null;
        }
        return gods.get(player.getDisplayName());
    }

    /** invokes lightning on the given position */
    public static void spawnLightningAt(World world, double x, double y, double z){
        world.playSoundEffect(x, y, z,"ambient.weather.thunder", 10000.0F, 0.8F);
        world.playSoundEffect(x, y, z,"random.explode", 10000.0F, 0.8F);
        world.spawnEntityInWorld(new EntityLightningBolt(world, x, y, z));
    }

    /** kills an entity */
    public static void smiteEntity(EntityPlayer attacker, EntityLivingBase target) {
        target.setHealth(0);
        target.onDeath(DamageSource.causePlayerDamage(attacker));
        target.setDead();
    }
}
