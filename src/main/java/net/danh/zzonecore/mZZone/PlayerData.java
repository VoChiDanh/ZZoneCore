package net.danh.zzonecore.mZZone;

import java.util.UUID;

public class PlayerData {

    private final UUID uuid;
    private final Integer xp;
    private final Integer level;
    private final Integer mana;
    private final Integer max_mana;
    private final Integer stamina;
    private final Integer max_stamina;

    public PlayerData(UUID uuid, Integer xp, Integer level, Integer mana, Integer max_mana, Integer stamina, Integer max_stamina) {
        this.uuid = uuid;
        this.xp = xp;
        this.level = level;
        this.mana = mana;
        this.max_mana = max_mana;
        this.stamina = stamina;
        this.max_stamina = max_stamina;
    }

    public UUID getPlayer() {
        return uuid;
    }

    public Integer getXP() {
        return xp;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getMana() {
        return mana;
    }

    public Integer getMaxMana() {
        return max_mana;
    }

    public Integer getStamina() {
        return stamina;
    }

    public Integer getMaxStamina() {
        return max_stamina;
    }
}
