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
        if (plugin.getKeepInventoryEnabled()) {
            e.setKeepInventory(true);
            e.getDrops().clear();
            removeCurseItems(e);
        }

        if (plugin.getKeepExpEnabled()) {
            e.setKeepLevel(true);
            e.setDroppedExp(0);
        }
    }

    public void removeCurseItems(PlayerDeathEvent e) {
        for (ItemStack item : e.getPlayer().getInventory().getContents()) {
            if (item == null) continue;
            if (item.containsEnchantment(Enchantment.VANISHING_CURSE) && !plugin.keepCurseOfVanishing()) {
                item.setAmount(0);
            } else if (item.containsEnchantment(Enchantment.BINDING_CURSE) && !plugin.keepCurseOfBinding()) {
                item.setAmount(0);
            }
        }
    }
}
