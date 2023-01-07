package biz.mcplay.betterkeepinventory;

import org.bukkit.plugin.java.JavaPlugin;

public final class BetterKeepInventory extends JavaPlugin {

    private boolean keepInventoryEnabled;
    private boolean keepExpEnabled;
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
        keepExpEnabled = getConfig().getBoolean("keep-exp.enabled");
    }

    public boolean getKeepInventoryEnabled() {
        return keepInventoryEnabled;
    }

    public boolean getKeepCurseItems() {
        return keepCurseItems;
    }

    public boolean getKeepExpEnabled() {
        return keepExpEnabled;
    }
}
