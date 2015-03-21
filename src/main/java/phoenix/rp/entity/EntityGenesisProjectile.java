package phoenix.rp.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import phoenix.rp.utility.DivinityHelper;

public class EntityGenesisProjectile extends EntityThrowable{
    private static final int LIFETIME = 200;
    private EntityPlayer origin;
    private int counter = 0;

    public EntityGenesisProjectile(EntityPlayer player) {
        super(player.getEntityWorld());
        this.origin = player;
        this.setSize(0F, 0F);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    @Override
    protected void onImpact(MovingObjectPosition movingObjectPosition) {
        if(movingObjectPosition.entityHit != null && movingObjectPosition.entityHit instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase) movingObjectPosition.entityHit;
            if(!(entity instanceof EntityPlayer)) {
                DivinityHelper.smiteEntity(origin, entity);
                return;
            }
            EntityPlayer target = (EntityPlayer) entity;
            if(DivinityHelper.isGod(target)) {
                //Custom god logic
            }
            else {
                DivinityHelper.smiteEntity(origin, target);
            }
        }

    }
}
