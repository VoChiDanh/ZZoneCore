package net.danh.zzonecore.mZZHook.MythicMobs.Event;

import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Mechanic implements Listener {

    @EventHandler
    public void onMythicMechanicLoad(MythicMechanicLoadEvent e) {
        if (e.getMechanicName().equalsIgnoreCase("zzonemechanic")) {
            e.register(new net.danh.zzonecore.mZZHook.MythicMobs.Mechanic(e.getConfig()));
        }
    }
}
