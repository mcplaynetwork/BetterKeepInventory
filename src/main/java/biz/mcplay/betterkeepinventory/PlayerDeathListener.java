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

            if (!plugin.getKeepCurseItems()) {
                for (ItemStack item : e.getPlayer().getInventory().getContents()) {
                    if (item != null && (item.containsEnchantment(Enchantment.VANISHING_CURSE) || item.containsEnchantment(Enchantment.BINDING_CURSE))) {
                        e.getPlayer().getInventory().remove(item);
                    }
                }
                for (ItemStack item : e.getPlayer().getEquipment().getArmorContents()) {
                    if (item != null && (item.containsEnchantment(Enchantment.VANISHING_CURSE) || item.containsEnchantment(Enchantment.BINDING_CURSE))) {
                        item.setAmount(0);
                    }
                }
            }
        }

        if (plugin.getKeepExpEnabled()) {
            e.setKeepLevel(true);
            e.setDroppedExp(0);
        }
    }
}
