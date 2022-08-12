package net.danh.zzonecore.Database;

import net.danh.zzonecore.ZZoneCore;
import net.danh.zzonecore.mZZone.PlayerData;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;

public abstract class Database {
    // The name of the table we created back in SQLite class.
    public String table = "playerdata";
    JavaPlugin plugin;
    Connection connection;

    public Database(JavaPlugin instance) {
        plugin = instance;
    }

    public abstract Connection getSQLConnection();

    public abstract void load();

    public void initialize() {
        connection = getSQLConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table + " WHERE player = ?");
            ResultSet rs = ps.executeQuery();
            close(ps, rs);

        } catch (SQLException ex) {
            ZZoneCore.getZZ().getLogger().log(Level.SEVERE, "Unable to retrieve connection", ex);
        }
    }

    public PlayerData getPlayerData(UUID uuid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        PlayerData playerData;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM " + table + " WHERE player = '" + uuid + "';");
            rs = ps.executeQuery();
            if (rs.next()) {
                playerData = new PlayerData(UUID.fromString(rs.getString("player")), rs.getInt("xp"), rs.getInt("level"), rs.getInt("mana"), rs.getInt("max_mana"), rs.getInt("stamina"), rs.getInt("max_stamina")); // Return the players amount of kills. If you wanted to get total (just a random number for an example for you guys) You would change this to total!
                return playerData;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            ZZoneCore.getZZ().getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                ZZoneCore.getZZ().getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), ex);
            }
        }
        return null;
    }

    public void createTable(PlayerData playerData) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("INSERT INTO " + table + " (player,xp,level,mana,max_mana,stamina,max_stamina) VALUES(?,?,?,?,?,?,?)"); // IMPORTANT. In SQLite class, We made 3 colums. player, Kills, Total.
            ps.setString(1, playerData.getPlayer().toString());
            ps.setInt(2, playerData.getXP());
            ps.setInt(3, playerData.getLevel());
            ps.setInt(4, playerData.getMana());
            ps.setInt(5, playerData.getMaxMana());
            ps.setInt(6, playerData.getStamina());
            ps.setInt(7, playerData.getMaxMana());
            ps.executeUpdate();
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), ex);
            }
        }
    }

    public void updateTable(PlayerData playerData) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("UPDATE " + table + " SET player = ?, xp = ?, level = ?, mana = ?, max_mana = ?, stamina = ?, max_stamina = ?");
            ps.setString(1, playerData.getPlayer().toString());
            ps.setInt(2, playerData.getXP());
            ps.setInt(3, playerData.getLevel());
            ps.setInt(4, playerData.getMana());
            ps.setInt(5, playerData.getMaxMana());
            ps.setInt(6, playerData.getStamina());
            ps.setInt(7, playerData.getMaxMana());
            ps.executeUpdate();
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), ex);
            }
        }
    }

    public void close(PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        } catch (SQLException ex) {
            Error.close(ex);
        }
    }
}
