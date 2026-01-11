package org.xani4kaaddonelytra;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import org.bukkit.plugin.java.JavaPlugin;
import org.xani4kaaddonelytra.flags.ElytraBlockFlag;
import org.xani4kaaddonelytra.listeners.ElytraEquipListener;
import org.xani4kaaddonelytra.listeners.ElytraGlideListener;
import org.xani4kaaddonelytra.listeners.ElytraMoveListener;
import org.xani4kaaddonelytra.utils.MessageUtil;

public final class Xani4kaAddonElytra extends JavaPlugin {

    private static Xani4kaAddonElytra instance;
    private ElytraBlockFlag elytraBlockFlag;
    private MessageUtil messageUtil;

    @Override
    public void onLoad() {
        instance = this;
        
        elytraBlockFlag = new ElytraBlockFlag();
        try {
            WorldGuard.getInstance().getFlagRegistry().register(elytraBlockFlag);
            getLogger().info("Флаг elytra-block успешно зарегистрирован!");
        } catch (FlagConflictException e) {
            getLogger().severe("Не удалось зарегистрировать флаг elytra-block: " + e.getMessage());
        }
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        
        messageUtil = new MessageUtil(this);
        
        getServer().getPluginManager().registerEvents(new ElytraGlideListener(this), this);
        getServer().getPluginManager().registerEvents(new ElytraMoveListener(this), this);
        getServer().getPluginManager().registerEvents(new ElytraEquipListener(this), this);
        
        messageUtil.printStartupMessage();
        getLogger().info("Флаг elytra-block доступен для использования в регионах WorldGuard");
    }

    @Override
    public void onDisable() {
        if (messageUtil != null) {
            messageUtil.printShutdownMessage();
        }
    }
    
    public static Xani4kaAddonElytra getInstance() {
        return instance;
    }
    
    public ElytraBlockFlag getElytraBlockFlag() {
        return elytraBlockFlag;
    }
    
    public boolean isDebugEnabled() {
        return getConfig().getBoolean("debug", false);
    }
}
