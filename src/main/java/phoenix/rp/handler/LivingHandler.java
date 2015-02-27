package phoenix.rp.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import phoenix.rp.items.ItemHandler;
import phoenix.rp.potion.PotionHandler;
import phoenix.rp.reference.ConfigSettings;
import phoenix.rp.utility.LogHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

public class LivingHandler
{
    private static EntityPlayer prevPlayer;
    private static EntityPlayer player1;
    private static int d = 0;

    @SubscribeEvent
    public void onAttack(AttackEntityEvent event)
    {
        EntityPlayer player = (EntityPlayer)event.entity;
        if (event.target instanceof EntityPlayer) {
            EntityPlayer target = (EntityPlayer) event.target;
            for (int i = 0; i < ConfigSettings.playerList.length; i++) {
                if (target.getDisplayName().equals(ConfigSettings.playerList[i]) && !(player.getDisplayName()).equals(ConfigSettings.playerList[i])) {
                    if (target.isPotionActive(PotionHandler.multipleAttackers)) {
                        if (player != prevPlayer && player != player1 || player.getDisplayName().equals(ConfigSettings.playerList[i])) {
                            EntityPlayer player2 = prevPlayer;
                            prevPlayer = player;
                            player1 = player2;
                        } else {
                            if (event.isCancelable()) {
                                event.setCanceled(true);
                            }
                        }
                    }
                }
            }
        }

        for (int j = 0; j < ConfigSettings.playerList.length; j++)
        {
            LogHelper.info("went through for loop name: " + player.getDisplayName());
            if (player.getDisplayName().equals(ConfigSettings.playerList[j]))
            {
                LogHelper.info(player.getDisplayName() + " is god");
                if (player.getHeldItem() != null) {
                    if (player.getHeldItem().getItem() == ItemHandler.apocalypse || player.getHeldItem().getItem() == ItemHandler.genesis) {
                        LogHelper.info("God has god sword");
                        if (!(event.target instanceof EntityLiving)) {
                            event.target.setDead();
                        }
                        if (event.target instanceof EntityLiving) {
                            EntityLiving livingTarget = (EntityLiving) event.target;
                            livingTarget.setHealth(0);
                            livingTarget.onDeath(DamageSource.causePlayerDamage(player));
                        }
                    }
                }
            }
        }
    }
}
