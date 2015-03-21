package phoenix.rp.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import phoenix.main.PheonixMod;
import phoenix.rp.entity.EntityDivineProjectile;
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
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int time) {
        if(!world.isRemote) {
            world.spawnEntityInWorld(new EntityDivineProjectile(player));
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
