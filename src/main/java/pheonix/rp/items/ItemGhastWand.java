package pheonix.rp.items;

import pheonix.rp.utility.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;


public class ItemGhastWand extends ItemR
{
    public ItemGhastWand()
    {
        super();
        this.setUnlocalizedName("ghastWand");
        this.setMaxStackSize(1);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.epic;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!world.isRemote) {
            EntityPlayer entityplayer = player;
            MovingObjectPosition mop = entityplayer.rayTrace(160D, 1.0F);
            int x = 0, y = 0, z = 0;
            try {
                x = mop.blockX;
                y = mop.blockY;
                z = mop.blockZ;
            } catch (NullPointerException ex) {
                LogHelper.error("Why you point at sky?");
                return itemStack;
            }

            double d5 = x - entityplayer.posX;
            double d6 = (y + (double) (1 / 2.0F)) - (entityplayer.posY + (double) (1 / 2.0F));
            double d7 = z - entityplayer.posZ;

            EntityLargeFireball entityfireball = new EntityLargeFireball(world, entityplayer, d5, d6 - 2.5D, d7);
            double d8 = 4D;
            Vec3 vec3d = entityplayer.getLook(1.0F);
            entityfireball.posX = entityplayer.posX + vec3d.xCoord * d8;
            entityfireball.posY = entityplayer.posY + (double) (1 / 2.0F) + 0.5D;
            entityfireball.posZ = entityplayer.posZ + vec3d.zCoord * d8;
            world.spawnEntityInWorld(entityfireball);
            return itemStack;
        }
        return itemStack;
    }
}
