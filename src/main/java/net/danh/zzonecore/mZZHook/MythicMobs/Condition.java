package net.danh.zzonecore.mZZHook.MythicMobs;

import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.conditions.IEntityCondition;
import io.lumine.mythic.bukkit.BukkitAdapter;
import net.danh.zzonecore.mZZone.pdZZone.ZZLevel;
import net.danh.zzonecore.mZZone.pdZZone.ZZMana;
import net.danh.zzonecore.mZZone.pdZZone.ZZStamina;
import net.danh.zzonecore.mZZone.pdZZone.ZZXP;
import org.bukkit.entity.Player;

public class Condition implements IEntityCondition {
    protected final String condition_type;
    protected final Integer amount;

    public Condition(MythicLineConfig config) {
        this.condition_type = config.getString(new String[]{"type", "t"});
        this.amount = config.getInteger(new String[]{"amount", "a"});
    }

    @Override
    public boolean check(AbstractEntity abstractEntity) {
        Player p = (Player) BukkitAdapter.adapt(abstractEntity);
        if (p != null) {
            if (condition_type.equalsIgnoreCase("xp")) {
                return ZZXP.getXP(p) >= amount;
            }
            if (condition_type.equalsIgnoreCase("level")) {
                return ZZLevel.getLevel(p) >= amount;
            }
            if (condition_type.equalsIgnoreCase("mana")) {
                return ZZMana.getMana(p) >= amount;
            }
            if (condition_type.equalsIgnoreCase("max_mana")) {
                return ZZMana.getMaxMana(p) >= amount;
            }
            if (condition_type.equalsIgnoreCase("stamina")) {
                return ZZStamina.getStamina(p) >= amount;
            }
            if (condition_type.equalsIgnoreCase("max_stamina")) {
                return ZZStamina.getMaxStamina(p) >= amount;
            }
        }
        return false;
    }
}
