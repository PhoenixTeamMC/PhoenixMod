package phoenix.rp.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import phoenix.rp.utility.DivinityHelper;

public class EntityDivineArrow extends EntityArrow {
    private String archer;

    public EntityDivineArrow(World world, EntityPlayer player, float charge) {
        super(world, player, charge);
        this.archer = player.getDisplayName();
    }

    //TODO: make sure this gets called even if the target is in creative
    public void onImpact(Entity entityHit) {
        if(entityHit != null && entityHit instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase) entityHit;
            EntityLivingBase origin = getArcher();
            if(origin==null || !(origin instanceof EntityPlayer)) {
                this.setDead();
                return;
            }
            EntityPlayer player = (EntityPlayer) origin;
            if(!(entity instanceof EntityPlayer)) {
                DivinityHelper.smiteEntity(player, entity);
            }
            else if(DivinityHelper.isGod((EntityPlayer) entity)) {
                //Custom god logic
            }
            else {
                DivinityHelper.smiteEntity(player, entity);
            }
        }
        this.setDead();
    }

    public EntityLivingBase getArcher() {
        return this.worldObj.getPlayerEntityByName(this.archer);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setString("Rena_Archer", this.archer);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        this.archer = tag.getString("Rena_Archer");
    }

}
