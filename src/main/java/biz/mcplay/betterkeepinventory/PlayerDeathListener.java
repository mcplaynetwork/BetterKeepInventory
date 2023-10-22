package biz.mcplay.betterkeepinventory;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.Sound;

import java.util.Random;

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
            handleItems(e);
        }

        if (plugin.getKeepExpEnabled()) {
            e.setKeepLevel(true);
            e.setDroppedExp(0);
        }
    }

    public void handleItems(PlayerDeathEvent e) {
        Random random = new Random();
        Inventory inv = e.getEntity().getInventory();

        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);

            if (item == null) continue;

            if (item.containsEnchantment(Enchantment.VANISHING_CURSE) && !plugin.keepCurseOfVanishing()) {
                item.setAmount(0);
                continue;
            } else if (item.containsEnchantment(Enchantment.BINDING_CURSE) && !plugin.keepCurseOfBinding()) {
                item.setAmount(0);
                continue;
            }

            ItemMeta meta = item.getItemMeta();

            if (item.getType().getMaxDurability() > 0) {
                Damageable damageable = (Damageable) meta;

                double minDurabilityMultiplier = plugin.getMinItemDurabilityMultiplier();
                double maxDurabilityMultiplier = plugin.getMaxItemDurabilityMultiplier();
                double durabilityMultiplier = minDurabilityMultiplier + (maxDurabilityMultiplier - minDurabilityMultiplier) * random.nextDouble();
                int maxDurability = item.getType().getMaxDurability();
                int damage = (int) (maxDurability * durabilityMultiplier);

                damageable.setDamage(damageable.getDamage() + damage);
                item.setItemMeta(meta);

                if (damageable.getDamage() >= maxDurability) {
                    item.setAmount(0);
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
                }
            }
        }
    }
}
