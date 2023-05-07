package net.hellcat.mc.plugins.ezduperepair.magicchests;

import net.hellcat.mc.plugins.ezduperepair.Plugin;
import java.util.logging.Logger;

public abstract class MagicChestBase
{
    protected Logger logger;
    protected Plugin plugin;

    MagicChestBase(Plugin plugin)
    {
        this.plugin = plugin;
        logger = this.plugin.getLogger();
    }

    public abstract String getId();
    public abstract String getDisplayName();
    public abstract String getChestTriggerName();
}
