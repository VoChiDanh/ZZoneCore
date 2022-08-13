package net.danh.zzonecore.mZZHook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.danh.zzonecore.ZZoneCore;
import net.danh.zzonecore.mZZone.pdZZone.ZZLevel;
import net.danh.zzonecore.mZZone.pdZZone.ZZMana;
import net.danh.zzonecore.mZZone.pdZZone.ZZStamina;
import net.danh.zzonecore.mZZone.pdZZone.ZZXP;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlaceholderAPI extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "zzone";
    }

    @Override
    public @NotNull String getAuthor() {
        return ZZoneCore.getZZ().getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return ZZoneCore.getZZ().getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player p, @NotNull String args) {
        if (p == null) return "";
        if (args.equalsIgnoreCase("xp")) {
            return String.valueOf(ZZXP.getXP(p));
        }
        if (args.equalsIgnoreCase("level")) {
            return String.valueOf(ZZLevel.getLevel(p));
        }
        if (args.equalsIgnoreCase("xp_req")) {
            return String.valueOf(ZZLevel.getLevel(p) * 1000);
        }
        if (args.equalsIgnoreCase("mana")) {
            return String.valueOf(ZZMana.getMana(p));
        }
        if (args.equalsIgnoreCase("max_mana")) {
            return String.valueOf(ZZMana.getMaxMana(p));
        }
        if (args.equalsIgnoreCase("stamina")) {
            return String.valueOf(ZZStamina.getStamina(p));
        }
        if (args.equalsIgnoreCase("max_stamina")) {
            return String.valueOf(ZZStamina.getMaxStamina(p));
        }
        if (args.equalsIgnoreCase("version")) {
            return getVersion();
        }
        return null;
    }
}
