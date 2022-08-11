package net.danh.zzonecore.mZZHook.MythicMobs;

import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.ITargetedEntitySkill;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.api.skills.SkillResult;
import io.lumine.mythic.bukkit.BukkitAdapter;
import net.danh.zzonecore.mZZone.holoZZ;
import net.danh.zzonecore.mZZone.mZZ;
import net.danh.zzonecore.mZZone.pdZZone.ZZXP;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Random;

public class Mechanic implements ITargetedEntitySkill {
    protected final String xp;

    public Mechanic(MythicLineConfig config) {
        this.xp = config.getString(new String[]{"zzone-xp", "xp"});
    }

    @Override
    public SkillResult castAtEntity(SkillMetadata data, AbstractEntity target) {
        Player bukkitTarget = (Player) BukkitAdapter.adapt(target);
        if (bukkitTarget != null) {
            if (xp.contains("-")) {
                if (Integer.parseInt(xp.split("-")[1]) - Integer.parseInt(xp.split("-")[0]) > 1) {
                    int zzone = new Random().nextInt(Integer.parseInt(xp.split("-")[0]), Integer.parseInt(xp.split("-")[1]));
                    ZZXP.addXP(bukkitTarget, zzone);
                    holoZZ.createHolo(bukkitTarget, data.getCaster().getLocation().toPosition().toLocation(), Objects.requireNonNull(mZZ.getConfig().getString("holo.xp"))
                            .replaceAll("#xp#", String.format("%,d", zzone))
                            .replaceAll("#player#", bukkitTarget.getDisplayName()));
                } else {
                    ZZXP.addXP(bukkitTarget, Integer.parseInt(xp.split("-")[0]));
                    holoZZ.createHolo(bukkitTarget, data.getCaster().getLocation().toPosition().toLocation(), Objects.requireNonNull(mZZ.getConfig().getString("holo.xp"))
                            .replaceAll("#xp#", String.format("%,d", Integer.parseInt(xp.split("-")[0])))
                            .replaceAll("#player#", bukkitTarget.getDisplayName()));
                }
            } else {
                ZZXP.addXP(bukkitTarget, Integer.parseInt(xp));
                holoZZ.createHolo(bukkitTarget, data.getCaster().getLocation().toPosition().toLocation(), Objects.requireNonNull(mZZ.getConfig().getString("holo.xp"))
                        .replaceAll("#xp#", String.format("%,d", Integer.parseInt(xp)))
                        .replaceAll("#player#", bukkitTarget.getDisplayName()));
            }
        }
        return SkillResult.SUCCESS;
    }
}
