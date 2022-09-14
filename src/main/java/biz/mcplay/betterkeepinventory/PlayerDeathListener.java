package biz.mcplay.betterkeepinventory;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeathListener implements Listener {

    private final BetterKeepInventory plugin;

    public PlayerDeathListener(BetterKeepInventory plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (plugin.isKeepInventoryEnabled()) {
            e.setKeepInventory(true);
            e.getDrops().clear();
        }

        if (plugin.isKeepInventoryEnabled() && !plugin.isKeepCurseItems()) {
            for (ItemStack item : e.getPlayer().getInventory().getContents()) {
                if (item != null && item.getItemMeta().getEnchants().containsKey(Enchantment.VANISHING_CURSE)) {
                    e.getPlayer().getInventory().remove(item);
                }
            }
        }

        if (plugin.isKeepLevelEnabled()) {
            e.setKeepLevel(true);
            e.setDroppedExp(0);
        }
    }
}
