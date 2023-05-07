package net.hellcat.mc.plugins.ezduperepair;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

public class Plugin extends JavaPlugin
{
    private Logger logger;
    private ChestHost chestHost;

    @Override
    public void onEnable()
    {
        logger = getLogger();

        chestHost = new ChestHost(this);

        getServer().getPluginManager().registerEvents(chestHost, this);
    }
}
