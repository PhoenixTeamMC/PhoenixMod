package phoenix.rp.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ItemHandler
{
    public static final Item.ToolMaterial mat = EnumHelper.addToolMaterial("RENA", 0, 9999, 100.0f, 100.0f, 20);
    public static final ItemR lightningWand = new ItemLightning();
    public static final ItemR wand = new ItemGhastWand();
    public static final ItemSword genesis = new ItemDivineSword(mat, "genesis", 0);
    public static final ItemSword apocalypse = new ItemDivineSword(mat, "apocalypse", 1);
    public static final ItemBow bow = new ItemDivineBow("bow");

    public static void init()
    {
        GameRegistry.registerItem(lightningWand, "lightningWand");
        GameRegistry.registerItem(wand, "ghastWand");
        GameRegistry.registerItem(apocalypse, "apocalypse");
        GameRegistry.registerItem(genesis, "genesis");
        GameRegistry.registerItem(bow, "bow");
    }
}
