package biz.mcplay.betterkeepinventory;

import biz.mcplay.betterkeepinventory.commands.ReloadCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterKeepInventory extends JavaPlugin {

    private boolean keepInventoryEnabled;
    private boolean keepExpEnabled;
    private boolean keepCurseOfVanishing;
    private boolean keepCurseOfBinding;
    private double minItemDurabilityMultiplier;
    private double maxItemDurabilityMultiplier;
    private double minLevelMultiplier;
    private double maxLevelMultiplier;


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
        keepCurseOfVanishing = getConfig().getBoolean("keep-inventory.enchantments.CURSE_OF_VANISHING");
        keepCurseOfBinding = getConfig().getBoolean("keep-inventory.enchantments.CURSE_OF_BINDING");
        minItemDurabilityMultiplier = getConfig().getDouble("keep-inventory.durability.min");
        maxItemDurabilityMultiplier = getConfig().getDouble("keep-inventory.durability.max");
        keepLevelEnabled = getConfig().getBoolean("keep-level.enabled");
        minLevelMultiplier = getConfig().getDouble("keep-level.multiplier.min");
        maxLevelMultiplier = getConfig().getDouble("keep-level.multiplier.max");
    }

    public boolean getKeepInventoryEnabled() {
        return keepInventoryEnabled;
    }

    public boolean getKeepExpEnabled() {
        return keepExpEnabled;
    }

    public boolean keepCurseOfVanishing() {
        return keepCurseOfVanishing;
    }

    public boolean keepCurseOfBinding() {
        return keepCurseOfBinding;
    }

    public double getMinItemDurabilityMultiplier() {
        return minItemDurabilityMultiplier;
    }

    public double getMaxItemDurabilityMultiplier() {
        return maxItemDurabilityMultiplier;
    }

    public double getMinLevelMultiplier() {
        return minLevelMultiplier;
    }

    public double getMaxLevelMultiplier() {
        return maxLevelMultiplier;
    }
}
