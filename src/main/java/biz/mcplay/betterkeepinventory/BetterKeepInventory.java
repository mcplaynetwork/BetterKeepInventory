package biz.mcplay.betterkeepinventory;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterKeepInventory extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
    }

    @Override
    public void onDisable() {
        // Disable logic
    }
}
