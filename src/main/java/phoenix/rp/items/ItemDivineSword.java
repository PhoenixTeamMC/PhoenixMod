package phoenix.rp.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import phoenix.main.PheonixMod;
import phoenix.rp.entity.EntityDivineProjectile;
import phoenix.rp.reference.Reference;
import phoenix.rp.utility.DivinityHelper;

public class ItemDivineSword extends ItemSword {
    private static final int ID_GENESIS = 0;
    private static final int ID_APOCALYPSE = 1;

    private int effectId = -1;
    private EnumRarity rarity;

    public ItemDivineSword(ToolMaterial mat, String name) {
        this(mat, name, EnumRarity.epic, -1);
    }

    public ItemDivineSword(ToolMaterial mat, String name, int effect) {
        this(mat, name, EnumRarity.epic, effect);
    }

    public ItemDivineSword(ToolMaterial mat, String name, EnumRarity rarity, int effect) {
        super(mat);
        this.setUnlocalizedName(name);
        this.rarity = rarity;
        this.setMaxStackSize(1);
        this.setCreativeTab(PheonixMod.creativeTab);
        this.effectId = effect;
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
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer attacker, Entity entity) {
        if(entity==null || !(entity instanceof EntityLivingBase)) {
            return false;
        }
        EntityLivingBase target = (EntityLivingBase) entity;
        //Attacker is a god: no problem
        if(DivinityHelper.isGod(attacker)) {
            //target is not a player: simply kill it
            if(!(target instanceof EntityPlayer)) {
                DivinityHelper.smiteEntity(attacker, target);
                return true;
            }
            EntityPlayer attacked = (EntityPlayer) target;
            //target is a god, need to ask Thomaz what actually needs to happen with gods attacking gods.
            if(DivinityHelper.isGod(attacked)) {

            }
            //target is not a god, kill it
            else {
                DivinityHelper.smiteEntity(attacker, target);
            }
        }
        //Attacker is not a god: weapon does nothing
        else {
            /*
            DivinityHelper.spawnLightningAt(azttacker.getEntityWorld(), attacker.posX, attacker.posY, attacker.posZ);
            attacker.dropOneItem(true);
            */
            attacker.addChatComponentMessage(new ChatComponentText("info.renascintis.youAreNotWorthy"));
        }
        return true;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int nr, boolean flag) {
        if(entity !=null && entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            DivinityHelper.enchantWeapon(stack, player, Enchantment.sharpness, 5);
            if(DivinityHelper.isGod(player)) {
                //Check if the god is swinging his sword
                PotionEffect haste = player.getActivePotionEffect(Potion.digSpeed);
                float swing = haste == null ? 0.16666667F : haste.getAmplifier() == 1 ? 0.5F : 0.4F;
                if(!world.isRemote && player.getCurrentEquippedItem()==stack && player.swingProgress==swing) {
                    weaponEffect(stack, player);
                }
            }
        }
    }

    private void weaponEffect(ItemStack stack, EntityPlayer player) {
        switch(this.effectId) {
            //no effect
            case -1:
                return;
            case ID_GENESIS:
                player.worldObj.spawnEntityInWorld(new EntityDivineProjectile(player, EntityDivineProjectile.GENESIS_ID));
                return;
            case ID_APOCALYPSE:
                return;
        }
    }

}
