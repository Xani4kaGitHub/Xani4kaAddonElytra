package org.xani4kaaddonelytra.utils;

import org.bukkit.ChatColor;
import org.xani4kaaddonelytra.Xani4kaAddonElytra;

public class MessageUtil {
    
    private final Xani4kaAddonElytra plugin;
    
    public MessageUtil(Xani4kaAddonElytra plugin) {
        this.plugin = plugin;
    }
    
    public String getMessage(String key) {
        String message = plugin.getConfig().getString("messages." + key, "&cСообщение не найдено: " + key);
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    
    public void printStartupMessage() {
        plugin.getLogger().info("                      .__   _____  __            ");
        plugin.getLogger().info("___  ________    ____ |__| /  |  ||  | _______   ");
        plugin.getLogger().info("\\  \\/  /\\__  \\  /    \\|  |/   |  ||  |/ /\\__  \\  ");
        plugin.getLogger().info(" >    <  / __ \\|   |  \\  /    ^   /    <  / __ \\_");
        plugin.getLogger().info("/__/\\_ \\(____  /___|  /__\\____   ||__|_ \\(____  /");
        plugin.getLogger().info("      \\/     \\/     \\/        |__|     \\/     \\/ ");
        plugin.getLogger().info("Xani4kaAddonElytra активирован!");
        plugin.getLogger().info("Telegram: https://t.me/HomeXani");
    }
    
    public void printShutdownMessage() {
        plugin.getLogger().info("                      .__   _____  __            ");
        plugin.getLogger().info("___  ________    ____ |__| /  |  ||  | _______   ");
        plugin.getLogger().info("\\  \\/  /\\__  \\  /    \\|  |/   |  ||  |/ /\\__  \\  ");
        plugin.getLogger().info(" >    <  / __ \\|   |  \\  /    ^   /    <  / __ \\_");
        plugin.getLogger().info("/__/\\_ \\(____  /___|  /__\\____   ||__|_ \\(____  /");
        plugin.getLogger().info("      \\/     \\/     \\/        |__|     \\/     \\/ ");
        plugin.getLogger().info("Xani4kaAddonElytra деактивирован!");
        plugin.getLogger().info("Telegram: https://t.me/HomeXani");
    }
}