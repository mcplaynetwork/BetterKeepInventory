package biz.mcplay.betterkeepinventory;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerRespawnEvent e) {
        for (int i = 0; i < e.getPlayer().getInventory().getSize(); i++) {
            ItemStack item = e.getPlayer().getInventory().getItem(i);
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasEnchants() && item.getItemMeta().getEnchants().containsKey(Enchantment.VANISHING_CURSE)) {
                e.getPlayer().getInventory().remove(item);
            }
        }
    }
}
