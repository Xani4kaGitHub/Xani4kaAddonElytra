package org.xani4kaaddonelytra.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.xani4kaaddonelytra.Xani4kaAddonElytra;
import org.xani4kaaddonelytra.utils.MessageUtil;
import org.xani4kaaddonelytra.utils.RegionUtil;

public class ElytraEquipListener implements Listener {
    
    private final Xani4kaAddonElytra plugin;
    private final RegionUtil regionUtil;
    private final MessageUtil messageUtil;
    
    public ElytraEquipListener(Xani4kaAddonElytra plugin) {
        this.plugin = plugin;
        this.regionUtil = new RegionUtil(plugin);
        this.messageUtil = new MessageUtil(plugin);
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        
        if (item != null && item.getType() == Material.ELYTRA && 
            event.getSlot() == 38 && 
            regionUtil.isElytraBlocked(player)) {
            
            event.setCancelled(true);
            player.sendMessage(messageUtil.getMessage("elytra-equip-blocked"));
            
            if (plugin.isDebugEnabled()) {
                plugin.getLogger().info("Заблокировано надевание элитр для игрока: " + player.getName());
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        
        if (item != null && item.getType() == Material.ELYTRA && regionUtil.isElytraBlocked(player)) {
            event.setCancelled(true);
            player.sendMessage(messageUtil.getMessage("elytra-equip-blocked"));
            
            if (plugin.isDebugEnabled()) {
                plugin.getLogger().info("Заблокировано взаимодействие с элитрами для игрока: " + player.getName());
            }
        }
    }
}