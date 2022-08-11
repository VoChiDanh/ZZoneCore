package net.danh.zzonecore.Event;

import net.danh.zzonecore.ZZoneCore;
import net.danh.zzonecore.mZZone.PlayerData;
import net.danh.zzonecore.mZZone.mZZ;
import net.danh.zzonecore.mZZone.pdZZone.ZZLevel;
import net.danh.zzonecore.mZZone.pdZZone.ZZMana;
import net.danh.zzonecore.mZZone.pdZZone.ZZStamina;
import net.danh.zzonecore.mZZone.pdZZone.ZZXP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class JoinQuit implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        try {
            PlayerData playerData = mZZ.getPlayerDatabase(p);
            ZZLevel.setLevel(p, playerData.getLevel());
            ZZXP.setXP(p, playerData.getXP());
            ZZMana.setMana(p, playerData.getMana());
            ZZMana.setMaxMana(p, playerData.getMaxMana());
            ZZStamina.setStamina(p, playerData.getStamina());
            ZZStamina.setMaxStamina(p, playerData.getMaxStamina());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        PlayerData playerData = new PlayerData(p.getUniqueId(), ZZXP.getXP(p), ZZLevel.getLevel(p), ZZMana.getMana(p), ZZMana.getMaxMana(p), ZZStamina.getStamina(p), ZZStamina.getMaxStamina(p));
        ZZoneCore.getDatabase().updateTable(playerData);
    }
}
