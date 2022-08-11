package net.danh.zzonecore;

import net.Indyuce.mmoitems.MMOItems;
import net.danh.dcore.Resource.Files;
import net.danh.zzonecore.Command.CMDBase;
import net.danh.zzonecore.Database.Database;
import net.danh.zzonecore.Database.SQLite;
import net.danh.zzonecore.Event.JoinQuit;
import net.danh.zzonecore.mZZHook.MythicMobs.Event.Mechanic;
import net.danh.zzonecore.mZZHook.MythicMobs.Event.Reload;
import net.danh.zzonecore.mZZHook.PlaceholderAPI;
import net.danh.zzonecore.mZZone.PlayerData;
import net.danh.zzonecore.mZZone.mZZ;
import net.danh.zzonecore.mZZone.pdZZone.ZZLevel;
import net.danh.zzonecore.mZZone.pdZZone.ZZMana;
import net.danh.zzonecore.mZZone.pdZZone.ZZStamina;
import net.danh.zzonecore.mZZone.pdZZone.ZZXP;
import net.danh.zzonecore.pZZone.ZZHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

import static net.danh.zzonecore.mZZone.holoZZ.*;

public final class ZZoneCore extends JavaPlugin {

    private static ZZoneCore ins;
    private static Database db;

    @Override
    public void onEnable() {
        // Plugin startup logic
        ins = this;
        MMOItems.plugin.setRPG(new ZZHandler());
        getServer().getPluginManager().registerEvents(new JoinQuit(), this);
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPI().register();
        }
        if (getServer().getPluginManager().getPlugin("MythicMobs") != null) {
            getServer().getPluginManager().registerEvents(new Reload(), this);
            getServer().getPluginManager().registerEvents(new Mechanic(), this);
        }
        new CMDBase(this);
        new Files(this, "config").load();
        db = new SQLite(this);
        db.load();
        for (Player p : Bukkit.getOnlinePlayers()) {
            try {
                PlayerData playerData = mZZ.getPlayerDatabase(p);
                ZZLevel.setLevel(p, playerData.getLevel());
                ZZXP.setXP(p, playerData.getXP());
                ZZMana.setMana(p, playerData.getMana());
                ZZMana.setMaxMana(p, playerData.getMaxMana());
                ZZStamina.setStamina(p, playerData.getStamina());
                ZZStamina.setMaxStamina(p, playerData.getMaxStamina());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Entity stand : stands) {
                int ticksLeft = indicators.get(stand);
                if (ticksLeft == 0) {
                    stand.remove();
                    removal.add(stand);
                    continue;
                }
                ticksLeft--;
                indicators.put(stand, ticksLeft);
            }
            removal.forEach(stands::remove);
        }, 0L, 1L);
    }

    @Override
    public void onDisable() {
        for (Entity stand : stands) {
            int ticksLeft = indicators.get(stand);
            if (ticksLeft == 0) {
                stand.remove();
                removal.add(stand);
                continue;
            }
            ticksLeft--;
            indicators.put(stand, ticksLeft);
        }
        removal.forEach(stands::remove);
        new Files(this, "config").save();
        for (Player p : Bukkit.getOnlinePlayers()) {
            PlayerData playerData = new PlayerData(p.getUniqueId(), ZZXP.getXP(p), ZZLevel.getLevel(p), ZZMana.getMana(p), ZZMana.getMaxMana(p), ZZStamina.getStamina(p), ZZStamina.getMaxStamina(p));
            ZZoneCore.getDatabase().updateTable(playerData);
        }
    }

    public static Database getDatabase() {
        return db;
    }

    public static ZZoneCore getZZ() {
        return ins;
    }
}
