package pheonix.rp.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ItemHandler
{
    public static final Item.ToolMaterial mat = EnumHelper.addToolMaterial("RENA", 0, 9999, 100.0f, 100.0f, 20);
    public static final ItemR lightningWand = new ItemLightning();
    public static final ItemR wand = new ItemGhastWand();
    public static final ItemSword apocalypse = new ItemApocalypse(mat);
    public static final ItemSword genesis = new ItemGenesis(mat);

    public static void init()
    {
        GameRegistry.registerItem(lightningWand, "lightningWand");
        GameRegistry.registerItem(wand, "ghastWand");
        GameRegistry.registerItem(apocalypse, "apocalypse");
        GameRegistry.registerItem(genesis, "genesis");
    }
}
