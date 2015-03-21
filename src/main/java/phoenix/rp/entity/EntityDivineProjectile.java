package phoenix.rp.entity;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.server.FMLServerHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import phoenix.rp.utility.DivinityHelper;
import phoenix.rp.utility.LogHelper;
import phoenix.rp.utility.NBTHelper;

public class EntityDivineProjectile extends EntityThrowable{
    private static final int LIFETIME = 200;
    private String origin;
    private int counter = 0;

    public EntityDivineProjectile(EntityPlayer player) {
        super(player.getEntityWorld());
        this.origin = player.getDisplayName();
        LogHelper.info(origin + " spawned projectile");
        this.setSize(0F, 0F);

        //Grabbed from Botania code as I was too lazy to figure the numbers out myself, so kudos to Vazkii
        setLocationAndAngles(player.posX, player.posY + player.getEyeHeight(), player.posZ, player.rotationYaw + 180, -player.rotationPitch);
        posX -= MathHelper.cos((rotationYaw + 180) / 180.0F * (float) Math.PI) * 0.16F;
        posY -= 0.10000000149011612D;
        posZ -= MathHelper.sin((rotationYaw + 180) / 180.0F * (float) Math.PI) * 0.16F;
        setPosition(posX, posY, posZ);
        yOffset = 0.0F;
        float f = 0.4F;
        motionX = MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * f / 2D;
        motionZ = MathHelper.sin((rotationPitch + func_70183_g()) / 180.0F * (float) Math.PI) * f / 2D;
        motionZ = -(MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * f) / 2D;
    }

    public EntityPlayer getOrigin() {
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        LogHelper.info("Origin: "+origin);
        if(side==Side.SERVER) {
            return FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152612_a(origin);
        }
        else {
            return FMLClientHandler.instance().getClientPlayerEntity();
        }
    }

    @Override
    protected void onImpact(MovingObjectPosition movingObjectPosition) {
        if(movingObjectPosition.entityHit != null && movingObjectPosition.entityHit instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase) movingObjectPosition.entityHit;
            if(!(entity instanceof EntityPlayer)) {
                DivinityHelper.smiteEntity(getOrigin(), entity);
            }
            else if(DivinityHelper.isGod((EntityPlayer) entity)) {
                //Custom god logic
            }
            else {
                DivinityHelper.smiteEntity(getOrigin(), entity);
            }
        }
        this.setDead();
    }

    @Override
    protected float getGravityVelocity() {
        return 0;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        counter++;
        if(counter==LIFETIME) {
            this.setDead();
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setString(NBTHelper.NBT_PLAYER, origin);
        tag.setInteger(NBTHelper.NBT_COUNTER, counter);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        origin = tag.getString(NBTHelper.NBT_PLAYER);
        counter = tag.getInteger(NBTHelper.NBT_COUNTER);
    }
}
