package net.danh.zzonecore.mZZone.pdZZone;

import net.danh.zzonecore.mZZone.mZZData;
import org.bukkit.entity.Player;

import static net.danh.zzonecore.mZZone.mZZ.data;
import static net.danh.zzonecore.mZZone.mZZ.getData;

public class ZZXP {

    public static int getXP(Player p) {
        return data.getOrDefault(getData(p, mZZData.XP), 0);
    }

    public static void addXP(Player p, Integer amount) {
        int after_add = getXP(p) + amount;
        if (after_add < ZZLevel.getLevel(p) * 1000) {
            data.replace(getData(p, mZZData.XP), getXP(p) + amount);
        } else {
            removeXP(p, ZZLevel.getLevel(p) * 1000);
            ZZLevel.addLevel(p, 1);
            ZZMana.addMaxMana(p, 1000);
            ZZStamina.addMaxStamina(p, 1000);
        }
    }

    public static void removeXP(Player p, Integer amount) {
        int after_remove = getXP(p) - amount;
        if (after_remove > 0) {
            data.replace(getData(p, mZZData.XP), after_remove);
        } else {
            data.put(getData(p, mZZData.XP), 0);
        }
    }

    public static void setXP(Player p, Integer amount) {
        if (amount < ZZLevel.getLevel(p) * 1000) {
            data.put(getData(p, mZZData.XP), amount);
        } else {
            removeXP(p, ZZLevel.getLevel(p) * 1000);
            ZZLevel.addLevel(p, 1);
            ZZMana.addMaxMana(p, 1000);
            ZZStamina.addMaxStamina(p, 1000);
        }
    }

}

