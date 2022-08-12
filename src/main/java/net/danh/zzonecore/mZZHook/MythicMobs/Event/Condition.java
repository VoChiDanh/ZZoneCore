package net.danh.zzonecore.mZZHook.MythicMobs.Event;

import io.lumine.mythic.bukkit.events.MythicConditionLoadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Condition implements Listener {

    @EventHandler
    public void onMythicConditionLoad(MythicConditionLoadEvent e) {
        if (e.getConditionName().equalsIgnoreCase("zzonecondition")) {
            e.register(new net.danh.zzonecore.mZZHook.MythicMobs.Condition(e.getConfig()));
        }
    }
}
