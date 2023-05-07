package net.hellcat.mc.plugins.ezduperepair;

import net.hellcat.mc.plugins.ezduperepair.magicchests.MagicChestBase;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import java.util.HashMap;

public class ChestHost implements Listener
{
    private Plugin plugin;
    HashMap<String, MagicChestBase> magicChestHandlers;
    HashMap<String, MagicChestBase> magicChestHandlersByTriggerName;

    ChestHost(Plugin plugin)
    {
        this.plugin = plugin;
        magicChestHandlers = new HashMap<String, MagicChestBase>();
    }

    public void registerChestHandler( MagicChestBase chestHandler )
    {
        String handlerId = chestHandler.getId();
        String triggerName = chestHandler.getChestTriggerName();

        if( magicChestHandlers.containsKey( handlerId ) )
        {
            plugin.getLogger().warning( "Attempt to register chest handler with already registered ID: " + handlerId );
            return;
        }

        if( magicChestHandlersByTriggerName.containsKey( handlerId ) )
        {
            plugin.getLogger().warning( "Attempt to register chest handler with duplicate trigger name: " + triggerName );
            return;
        }

        magicChestHandlers.put(handlerId, chestHandler);
        magicChestHandlersByTriggerName.put(triggerName, chestHandler);
        plugin.getLogger().info( "Registered magic chest handler for '" + chestHandler.getDisplayName() + "'" );
    }

    @EventHandler
    public void onBlockPlace( BlockPlaceEvent event )
    {
        Block block;
        String placedDisplayName;

        if( event.getItemInHand().hasItemMeta() )
        {
            block = event.getBlock();
            placedDisplayName = event.getItemInHand().getItemMeta().getDisplayName();

            if(
                    (block.getType() == Material.CHEST) &&
                    ( magicChestHandlersByTriggerName.containsKey(placedDisplayName) )
            )
            {}
        }
    }

    @EventHandler
    public void onBlockBreak( BlockBreakEvent event )
    {}

    @EventHandler
    public void onPlayerInteract( PlayerInteractEvent event )
    {}

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {}
}
