package phoenix.rp.handler;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import phoenix.rp.entity.EntityDivineArrow;

public class DivineArrowhHandler {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onDivineArrowHit(LivingHurtEvent event) {
        if(isDivineArrow(event.source)) {
            EntityDivineArrow arrow = (EntityDivineArrow) event.source.getEntity();
            arrow.onImpact(event.entity);       
        }
    }

    private static boolean isDivineArrow(DamageSource source) {
        return source.damageType.equals("arrow") && source instanceof EntityDamageSource && source.getEntity() instanceof EntityDivineArrow;
    }

}
