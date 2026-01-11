package org.xani4kaaddonelytra.utils;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.entity.Player;
import org.xani4kaaddonelytra.Xani4kaAddonElytra;

public class RegionUtil {
    
    private final Xani4kaAddonElytra plugin;
    
    public RegionUtil(Xani4kaAddonElytra plugin) {
        this.plugin = plugin;
    }
    
    public boolean isElytraBlocked(Player player) {
        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(player.getLocation()));
        
        Boolean flagValue = set.queryValue(localPlayer, plugin.getElytraBlockFlag());
        
        if (plugin.isDebugEnabled()) {
            plugin.getLogger().info("Проверка флага elytra-block для игрока " + player.getName() + 
                                  " в локации " + player.getLocation() + ": " + flagValue);
        }
        
        return flagValue != null && flagValue;
    }
}