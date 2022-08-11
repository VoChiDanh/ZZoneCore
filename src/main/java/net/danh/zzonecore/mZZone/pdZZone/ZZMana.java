package net.danh.zzonecore.mZZone.pdZZone;

import net.danh.zzonecore.mZZone.mZZData;
import org.bukkit.entity.Player;

import static net.danh.zzonecore.mZZone.mZZ.data;
import static net.danh.zzonecore.mZZone.mZZ.getData;

public class ZZMana {

    public static int getMana(Player p) {
        return data.getOrDefault(getData(p, mZZData.MANA), 0);
    }

    public static int getMaxMana(Player p) {
        return data.getOrDefault(getData(p, mZZData.MAX_MANA), 1000);
    }

    public static void addMana(Player p, Integer amount) {
        int after_add = getMana(p) + amount;
        if (after_add < getMaxMana(p)) {
            data.replace(getData(p, mZZData.MANA), getMana(p) + amount);
        } else {
            data.put(getData(p, mZZData.MANA), getMaxMana(p));
        }
    }

    public static void removeMana(Player p, Integer amount) {
        int after_remove = getMana(p) - amount;
        if (after_remove > 0) {
            data.replace(getData(p, mZZData.MANA), after_remove);
        } else {
            data.put(getData(p, mZZData.MANA), 0);
        }
    }

    public static void setMana(Player p, Integer amount) {
        if (amount <= getMaxMana(p)) {
            data.put(getData(p, mZZData.MANA), amount);
        } else {
            data.put(getData(p, mZZData.MANA), getMaxMana(p));
        }
    }

    public static void addMaxMana(Player p, Integer amount) {
        data.replace(getData(p, mZZData.MAX_MANA), getMaxMana(p) + amount);
    }

    public static void removeMaxMana(Player p, Integer amount) {
        int after_remove = getMaxMana(p) - amount;
        data.replace(getData(p, mZZData.MAX_MANA), Math.max(after_remove, 1000));
    }

    public static void setMaxMana(Player p, Integer amount) {
        data.put(getData(p, mZZData.MAX_MANA), Math.max(amount, 1000));
    }
}

