package phoenix.rp.potion;

import phoenix.rp.reference.ConfigSettings;
import net.minecraft.potion.Potion;

public class PotionHandler
{
    public static Potion multipleAttackers;
    public static void init()
    {
        int multipleAttackersPotionId = ConfigSettings.multipleAttackers;
        multipleAttackers = new PotionMultipleAttackers(multipleAttackersPotionId, false, 00000000);
        Potion.potionTypes[multipleAttackersPotionId] = multipleAttackers;
    }
}
