package phoenix.rp.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import phoenix.main.PheonixMod;
import phoenix.rp.utility.DivinityHelper;

public class ItemLightning extends ItemR
{
    public ItemLightning()
    {
        super();
        this.setUnlocalizedName("lightningWand");
        this.setMaxStackSize(1);
        this.setCreativeTab(PheonixMod.creativeTab);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase target)
    {
        DivinityHelper.spawnLightningAt(player.worldObj, target.posX, target.posY, target.posZ);
        return false;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        MovingObjectPosition pos = player.rayTrace(200D, 1.0F);
        int x = pos.blockX;
        int y = pos.blockY;
        int z = pos.blockZ;
        DivinityHelper.spawnLightningAt(world, x, y, z);
        return stack;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        DivinityHelper.spawnLightningAt(world, x, y, z);
        return false;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.epic;
    }
}
