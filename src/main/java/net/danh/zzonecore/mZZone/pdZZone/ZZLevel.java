package net.danh.zzonecore.mZZone.pdZZone;

import net.danh.zzonecore.mZZone.mZZData;
import org.bukkit.entity.Player;

import static net.danh.zzonecore.mZZone.mZZ.data;
import static net.danh.zzonecore.mZZone.mZZ.getData;

public class ZZLevel {

    public static int getLevel(Player p) {
        return data.getOrDefault(getData(p, mZZData.LEVEL), 1);
    }

    public static void addLevel(Player p, Integer amount) {
        data.replace(getData(p, mZZData.LEVEL), getLevel(p) + amount);
    }

    public static void removeLevel(Player p, Integer amount) {
        int after_remove = getLevel(p) - amount;
        if (after_remove > 0) {
            data.replace(getData(p, mZZData.LEVEL), after_remove);
        } else {
            data.put(getData(p, mZZData.LEVEL), 0);
        }
    }

    public static void setLevel(Player p, Integer amount) {
        data.put(getData(p, mZZData.LEVEL), amount);
    }
}

