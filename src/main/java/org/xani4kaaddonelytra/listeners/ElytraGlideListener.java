package org.xani4kaaddonelytra.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.xani4kaaddonelytra.Xani4kaAddonElytra;
import org.xani4kaaddonelytra.utils.MessageUtil;
import org.xani4kaaddonelytra.utils.RegionUtil;

public class ElytraGlideListener implements Listener {
    
    private final Xani4kaAddonElytra plugin;
    private final RegionUtil regionUtil;
    private final MessageUtil messageUtil;
    
    public ElytraGlideListener(Xani4kaAddonElytra plugin) {
        this.plugin = plugin;
        this.regionUtil = new RegionUtil(plugin);
        this.messageUtil = new MessageUtil(plugin);
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityToggleGlide(EntityToggleGlideEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        
        Player player = (Player) event.getEntity();
        
        if (event.isGliding() && regionUtil.isElytraBlocked(player)) {
            event.setCancelled(true);
            player.sendMessage(messageUtil.getMessage("elytra-use-blocked"));
            
            if (plugin.isDebugEnabled()) {
                plugin.getLogger().info("Заблокировано использование элитр для игрока: " + player.getName());
            }
        }
    }
}