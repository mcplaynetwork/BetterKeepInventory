# BetterKeepInventory

The gamerule keepinventory that can be set in Minecraft retains EXP and Curse of Vanishing.

BetterKeepInventory is Minecraft plugin that allows you to optionally set them.

## Commands

**/bkireload** - Reloads the config.

## Permissions

**betterkeepinventory.reload** - Allows you to use /bkireload.

## Config

```yaml
keep-inventory:
  # If true, the inventory is kept.
  enabled: true
  # If true, the enchanted item is kept.
  enchantments:
    CURSE_OF_VANISHING: false
    CURSE_OF_BINDING: false
  durability:
    # Randomly reduces the durability of an item upon death.
    # min: Minimum durability reduction
    # max: Maximum durability reduction
    min: 0.1
    max: 0.3
keep-level:
  # If true, the level is kept.
  enabled: true
  # Randomly reduces the level upon death.
  # min: Minimum level reduction
  # max: Maximum level reduction
  multiplier:
    min: 0.1
    max: 0.5

```
