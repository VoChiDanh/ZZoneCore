package net.danh.zzonecore.mZZone.pdZZone;

import net.danh.zzonecore.mZZone.mZZData;
import org.bukkit.entity.Player;

import static net.danh.zzonecore.mZZone.mZZ.data;
import static net.danh.zzonecore.mZZone.mZZ.getData;

public class ZZStamina {

    public static int getStamina(Player p) {
        return data.getOrDefault(getData(p, mZZData.STAMINA), 0);
    }

    public static int getMaxStamina(Player p) {
        return data.getOrDefault(getData(p, mZZData.MAX_STAMINA), 1000);
    }

    public static void addStamina(Player p, Integer amount) {
        int after_add = getStamina(p) + amount;
        if (after_add < getMaxStamina(p)) {
            data.replace(getData(p, mZZData.STAMINA), getStamina(p) + amount);
        } else {
            data.put(getData(p, mZZData.STAMINA), getMaxStamina(p));
        }
    }

    public static void removeStamina(Player p, Integer amount) {
        int after_remove = getStamina(p) - amount;
        if (after_remove > 0) {
            data.replace(getData(p, mZZData.STAMINA), after_remove);
        } else {
            data.put(getData(p, mZZData.STAMINA), 0);
        }
    }

    public static void setStamina(Player p, Integer amount) {
        if (amount <= getMaxStamina(p)) {
            data.put(getData(p, mZZData.STAMINA), amount);
        } else {
            data.put(getData(p, mZZData.STAMINA), getMaxStamina(p));
        }
    }

    public static void addMaxStamina(Player p, Integer amount) {
        data.replace(getData(p, mZZData.MAX_STAMINA), getMaxStamina(p) + amount);
    }

    public static void removeMaxStamina(Player p, Integer amount) {
        int after_remove = getMaxStamina(p) - amount;
        data.replace(getData(p, mZZData.MAX_STAMINA), Math.max(after_remove, 1000));
    }

    public static void setMaxStamina(Player p, Integer amount) {
        data.put(getData(p, mZZData.MAX_STAMINA), Math.max(amount, 1000));
    }
}

