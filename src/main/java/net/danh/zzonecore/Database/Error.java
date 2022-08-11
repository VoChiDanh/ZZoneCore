package net.danh.zzonecore.Database;

import net.danh.zzonecore.ZZoneCore;

import java.util.logging.Level;

public class Error {

    public static void execute(Exception ex) {
        ZZoneCore.getZZ().getLogger().log(Level.SEVERE, "Couldn't execute MySQL statement: ", ex);
    }

    public static void close(Exception ex) {
        ZZoneCore.getZZ().getLogger().log(Level.SEVERE, "Failed to close MySQL connection: ", ex);
    }
}
