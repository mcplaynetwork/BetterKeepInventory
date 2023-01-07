package biz.mcplay.betterkeepinventory;

import org.bukkit.plugin.java.JavaPlugin;

public final class BetterKeepInventory extends JavaPlugin {

    private boolean keepInventoryEnabled;
    private boolean keepLevelEnabled;
    private boolean keepCurseItems;


    @Override
    public void onEnable() {
        loadConfig();
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
    }

    public void loadConfig() {
        saveDefaultConfig();
        reloadConfig();
        keepInventoryEnabled = getConfig().getBoolean("keep-inventory.enabled");
        keepCurseItems = getConfig().getBoolean("keep-inventory.keep-curse-items");
        keepLevelEnabled = getConfig().getBoolean("keep-level.enabled");
    }

    public boolean isKeepInventoryEnabled() {
        return keepInventoryEnabled;
    }

    public boolean isKeepLevelEnabled() {
        return keepLevelEnabled;
    }

    public boolean isKeepCurseItems() {
        return keepCurseItems;
    }
}
