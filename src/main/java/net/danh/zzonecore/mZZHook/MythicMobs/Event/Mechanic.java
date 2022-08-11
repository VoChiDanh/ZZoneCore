package net.danh.zzonecore.mZZHook.MythicMobs.Event;

import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Mechanic implements Listener {

    @EventHandler
    public void onMythicMechanicLoad(MythicMechanicLoadEvent event) {
        if (event.getMechanicName().equalsIgnoreCase("zzonexp")) {
            event.register(new net.danh.zzonecore.mZZHook.MythicMobs.Mechanic(event.getConfig()));
        }
    }
}
