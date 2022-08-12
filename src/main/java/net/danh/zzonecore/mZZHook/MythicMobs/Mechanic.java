package net.danh.zzonecore.mZZHook.MythicMobs;

import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.ITargetedEntitySkill;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.api.skills.SkillResult;
import io.lumine.mythic.bukkit.BukkitAdapter;
import net.danh.zzonecore.mZZone.holoZZ;
import net.danh.zzonecore.mZZone.mZZ;
import net.danh.zzonecore.mZZone.pdZZone.ZZLevel;
import net.danh.zzonecore.mZZone.pdZZone.ZZMana;
import net.danh.zzonecore.mZZone.pdZZone.ZZStamina;
import net.danh.zzonecore.mZZone.pdZZone.ZZXP;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Random;

public class Mechanic implements ITargetedEntitySkill {

    protected final String action;
    protected final String type;
    protected final String amount;

    public Mechanic(MythicLineConfig config) {
        this.action = config.getString(new String[]{"action", "a"});
        this.type = config.getString(new String[]{"type", "t"});
        this.amount = config.getString(new String[]{"amount", "n"});
    }

    @Override
    public SkillResult castAtEntity(SkillMetadata data, AbstractEntity target) {
        Player p = (Player) BukkitAdapter.adapt(target);
        if (p != null) {
            if (action.equalsIgnoreCase("add")) {
                if (amount.contains("-")) {
                    if (Integer.parseInt(amount.split("-")[1]) - Integer.parseInt(amount.split("-")[0]) > 1) {
                        int zzone = new Random().nextInt(Integer.parseInt(amount.split("-")[0]), Integer.parseInt(amount.split("-")[1]));
                        if (type.equalsIgnoreCase("xp")) {
                            ZZXP.addXP(p, zzone);
                            holoZZ.createHolo(p, data.getCaster().getLocation().toPosition().toLocation(), Objects.requireNonNull(mZZ.getConfig().getString("holo.xp"))
                                    .replaceAll("#xp#", String.format("%,d", zzone))
                                    .replaceAll("#player#", p.getDisplayName()));
                        }
                        if (type.equalsIgnoreCase("level")) {
                            ZZLevel.addLevel(p, zzone);
                        }
                        if (type.equalsIgnoreCase("mana")) {
                            ZZMana.addMana(p, zzone);
                        }
                        if (type.equalsIgnoreCase("max_mana")) {
                            ZZMana.addMaxMana(p, zzone);
                        }
                        if (type.equalsIgnoreCase("stamina")) {
                            ZZStamina.addStamina(p, zzone);
                        }
                        if (type.equalsIgnoreCase("max_stamina")) {
                            ZZStamina.addMaxStamina(p, zzone);
                        }
                    } else {
                        int zzone = Integer.parseInt(amount.split("-")[0]);
                        if (type.equalsIgnoreCase("xp")) {
                            ZZXP.addXP(p, zzone);
                            holoZZ.createHolo(p, data.getCaster().getLocation().toPosition().toLocation(), Objects.requireNonNull(mZZ.getConfig().getString("holo.xp"))
                                    .replaceAll("#xp#", String.format("%,d", zzone))
                                    .replaceAll("#player#", p.getDisplayName()));
                        }
                        if (type.equalsIgnoreCase("level")) {
                            ZZLevel.addLevel(p, zzone);
                        }
                        if (type.equalsIgnoreCase("mana")) {
                            ZZMana.addMana(p, zzone);
                        }
                        if (type.equalsIgnoreCase("max_mana")) {
                            ZZMana.addMaxMana(p, zzone);
                        }
                        if (type.equalsIgnoreCase("stamina")) {
                            ZZStamina.addStamina(p, zzone);
                        }
                        if (type.equalsIgnoreCase("max_stamina")) {
                            ZZStamina.addMaxStamina(p, zzone);
                        }
                    }
                } else {
                    int zzone = Integer.parseInt(amount);
                    if (type.equalsIgnoreCase("xp")) {
                        ZZXP.addXP(p, zzone);
                        holoZZ.createHolo(p, data.getCaster().getLocation().toPosition().toLocation(), Objects.requireNonNull(mZZ.getConfig().getString("holo.xp"))
                                .replaceAll("#xp#", String.format("%,d", zzone))
                                .replaceAll("#player#", p.getDisplayName()));
                    }
                    if (type.equalsIgnoreCase("level")) {
                        ZZLevel.addLevel(p, zzone);
                    }
                    if (type.equalsIgnoreCase("mana")) {
                        ZZMana.addMana(p, zzone);
                    }
                    if (type.equalsIgnoreCase("max_mana")) {
                        ZZMana.addMaxMana(p, zzone);
                    }
                    if (type.equalsIgnoreCase("stamina")) {
                        ZZStamina.addStamina(p, zzone);
                    }
                    if (type.equalsIgnoreCase("max_stamina")) {
                        ZZStamina.addMaxStamina(p, zzone);
                    }
                }
            }
        }
        return SkillResult.SUCCESS;
    }
}
