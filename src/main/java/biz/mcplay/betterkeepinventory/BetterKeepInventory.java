package biz.mcplay.betterkeepinventory;

import biz.mcplay.betterkeepinventory.commands.ReloadCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterKeepInventory extends JavaPlugin {

    private boolean keepInventoryEnabled;
    private boolean keepExpEnabled;
    private boolean keepCurseItems;


    @Override
    public void onEnable() {
        loadConfig();
        getCommand("bkireload").setExecutor(new ReloadCommand(this));
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
