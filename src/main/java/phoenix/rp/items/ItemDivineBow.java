package phoenix.rp.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import phoenix.main.PheonixMod;
import phoenix.rp.entity.EntityDivineArrow;
import phoenix.rp.reference.Reference;
import phoenix.rp.utility.DivinityHelper;

public class ItemDivineBow extends ItemBow{
    private EnumRarity rarity;

    public ItemDivineBow(String name) {
        this(name, EnumRarity.epic);
    }

    public ItemDivineBow(String name, EnumRarity rarity) {
        this.setUnlocalizedName(name);
        this.rarity = rarity;
        this.setCreativeTab(PheonixMod.creativeTab);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return this.rarity;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int duration) {
        int charge = this.getMaxItemUseDuration(stack) - duration;
        float drawingSpeed = 2.0F;
        float f = (drawingSpeed*charge) / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if ((double) f < 0.1D) {
            return;
        }
        if (f > 1.0F) {
            f = 1.0F;
        }
        EntityDivineArrow arrow = new EntityDivineArrow(world, player, f * 2.0F);
        arrow.setIsCritical(true);
        int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);
        if (l > 0) {
            arrow.setKnockbackStrength(l);
        }
        stack.damageItem(1, player);
        world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

        if (!world.isRemote) {
            world.spawnEntityInWorld(arrow);
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if(DivinityHelper.isGod(player)) {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        } else {
            player.addChatComponentMessage(new ChatComponentText("info.renascintis.youAreNotWorthy"));
        }
        return stack;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int nr, boolean flag) {
        if(entity !=null && entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            DivinityHelper.enchantWeapon(stack, player, Enchantment.punch, 10);
        }
    }
}
