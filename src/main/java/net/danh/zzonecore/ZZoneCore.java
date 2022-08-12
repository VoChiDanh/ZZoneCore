package net.danh.zzonecore;

import net.Indyuce.mmoitems.MMOItems;
import net.danh.dcore.NMS.NMSAssistant;
import net.danh.dcore.Resource.Files;
import net.danh.dcore.Utils.Chat;
import net.danh.dcore.Utils.Status;
import net.danh.zzonecore.Command.CMDBase;
import net.danh.zzonecore.Database.Database;
import net.danh.zzonecore.Database.SQLite;
import net.danh.zzonecore.Event.JoinQuit;
import net.danh.zzonecore.mZZHook.MythicMobs.Event.Condition;
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

    public static Database getDatabase() {
        return db;
    }

    public static ZZoneCore getZZ() {
        return ins;
    }

    @Override
    public void onEnable() {
        ins = this;
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPI().register();
        }
        ZZoneCore.getZZ().getLogger().info(Chat.colorize("&e-------------------- &bZZoneCore &e--------------------"));
        if (isPaper()) {
            ZZoneCore.getZZ().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Found server version: " + new NMSAssistant().getNMSVersion()));
        } else {
            ZZoneCore.getZZ().getLogger().info(Chat.colorize(Status.FALSE.getSymbol() + "&e Found server version: " + new NMSAssistant().getNMSVersion()));
            ZZoneCore.getZZ().getLogger().info(Chat.colorize(Status.FALSE.getSymbol() + "&c Please use PaperMC for fully support"));
        }
        ZZoneCore.getZZ().getLogger().info(Chat.colorize("&7"));
        if (getServer().getPluginManager().getPlugin("MMOItems") != null) {
            MMOItems.plugin.setRPG(new ZZHandler());
            ZZoneCore.getZZ().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Loaded system compatible with MMOItems"));
            ZZoneCore.getZZ().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Hooked ZZoneCore data (player's level, player's mana, player's stamina) to MMOItems"));
            ZZoneCore.getZZ().getLogger().info(Chat.colorize("&7"));
        }
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            ZZoneCore.getZZ().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Loaded system compatible with PlaceholderAPI"));
            ZZoneCore.getZZ().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e %zzone_xp%, %zzone_level%, %zzone_xp_req%, %zzone_mana%, %zzone_max_mana%, %zzone_stamina%, %zzone_max_stamina%, %zzone_version%"));
            ZZoneCore.getZZ().getLogger().info(Chat.colorize("&7"));
        }
        if (getServer().getPluginManager().getPlugin("MythicMobs") != null) {
            getServer().getPluginManager().registerEvents(new Reload(), this);
            getServer().getPluginManager().registerEvents(new Mechanic(), this);
            getServer().getPluginManager().registerEvents(new Condition(), this);
            ZZoneCore.getZZ().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Loaded system compatible with MythicMobs"));
            ZZoneCore.getZZ().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Mechanics:"));
            ZZoneCore.getZZ().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e - zzonemechanic{action=[add/remove];type=[xp/level/mana/max_mana/stamina/max_stamina];amount=[number-number/number]}"));
            ZZoneCore.getZZ().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e TargetConditions:"));
            ZZoneCore.getZZ().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e - zzonecondition{t=[xp/level/mana/max_mana/stamina/max_stamina];a=[number]} true"));
            ZZoneCore.getZZ().getLogger().info(Chat.colorize("&7"));
        }
        getServer().getPluginManager().registerEvents(new JoinQuit(), this);
        ZZoneCore.getZZ().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Registered events"));
        ZZoneCore.getZZ().getLogger().info(Chat.colorize("&7"));
        new CMDBase(this);
        ZZoneCore.getZZ().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Registered commands"));
        ZZoneCore.getZZ().getLogger().info(Chat.colorize("&7"));
        new Files(this, "config").load();
        ZZoneCore.getZZ().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Loaded config"));
        ZZoneCore.getZZ().getLogger().info(Chat.colorize("&7"));
        db = new SQLite(this);
        db.load();
        ZZoneCore.getZZ().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Loaded player data (SQLite)"));
        ZZoneCore.getZZ().getLogger().info(Chat.colorize("&7"));
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
        ZZoneCore.getZZ().getLogger().info(Chat.colorize(Status.TRUE.getSymbol() + "&e Loaded data for online players while the plugin was starting"));
        ZZoneCore.getZZ().getLogger().info(Chat.colorize("&e-------------------- &bZZoneCore &e--------------------"));
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

    private boolean isPaper() {
        try {
            Class.forName("com.destroystokyo.paper.VersionHistoryManager$VersionData");
            return true;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }
}
