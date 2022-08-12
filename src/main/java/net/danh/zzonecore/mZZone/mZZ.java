package net.danh.zzonecore.mZZone;

import net.danh.dcore.Resource.Files;
import net.danh.zzonecore.ZZoneCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.HashMap;

public class mZZ {

    public static HashMap<String, Integer> data = new HashMap<>();

    @NotNull
    public static String getData(@NotNull Player p, @NotNull mZZData type) {
        return p.getName() + "_" + type.getName();
    }


    public static PlayerData getPlayerDatabase(Player player) throws SQLException {

        PlayerData playerStats = ZZoneCore.getDatabase().getPlayerData(player.getUniqueId());

        if (playerStats == null) {
            playerStats = new PlayerData(player.getUniqueId(), 0, 1, 1000, 1000, 1000, 1000);
            ZZoneCore.getDatabase().createTable(playerStats);
        }

        return playerStats;
    }

    public static FileConfiguration getConfig() {
        return new Files(ZZoneCore.getZZ(), "config").getConfig();
    }

}
