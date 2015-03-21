package phoenix.rp.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import phoenix.rp.reference.ConfigSettings;
import phoenix.rp.reference.Reference;
import net.minecraftforge.common.config.Configuration;
import phoenix.rp.utility.DivinityHelper;
import phoenix.rp.utility.LogHelper;

import java.io.File;

public class ConfigurationHandler
{

    public static Configuration configuration;


    public static void init(File configFile) {

        //create configuration object from the given file
        if (configuration == null)
        {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
        {
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        ConfigSettings.multipleAttackers = configuration.getInt("multipleAttackersPotionId", Configuration.CATEGORY_GENERAL, 25, 25, 31, "ID for the multiple attackers potion effect.");
        String[] godsList = configuration.getStringList("Username list for Gods", Configuration.CATEGORY_GENERAL, ConfigSettings.defaultGods, "The gods");
        String[] godTypes = configuration.getStringList("God Types", Configuration.CATEGORY_GENERAL, ConfigSettings.defaultTypes, "Must be the same length as username list");
        if(godsList.length!=godTypes.length) {
            LogHelper.error("CONFIG ERROR: God list and type list do not match");
            System.exit(-1);
        }
        for(int i=0;i<godsList.length;i++) {
            DivinityHelper.gods.put(godsList[i], godTypes[i]);
        }
        if (configuration.hasChanged())
        {
            configuration.save();
        }
    }
}