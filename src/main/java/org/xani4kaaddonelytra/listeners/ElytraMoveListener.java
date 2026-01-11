package org.xani4kaaddonelytra.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.xani4kaaddonelytra.Xani4kaAddonElytra;
import org.xani4kaaddonelytra.utils.MessageUtil;
import org.xani4kaaddonelytra.utils.RegionUtil;

public class ElytraMoveListener implements Listener {
    
    private final Xani4kaAddonElytra plugin;
    private final RegionUtil regionUtil;
    private final MessageUtil messageUtil;
    
    public ElytraMoveListener(Xani4kaAddonElytra plugin) {
        this.plugin = plugin;
        this.regionUtil = new RegionUtil(plugin);
        this.messageUtil = new MessageUtil(plugin);
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        
        if (player.isGliding() && regionUtil.isElytraBlocked(player)) {
            player.setGliding(false);
            player.sendMessage(messageUtil.getMessage("elytra-blocked"));
            
            if (plugin.isDebugEnabled()) {
                plugin.getLogger().info("Остановлено планирование для игрока: " + player.getName());
            }
        }
    }
}