package net.danh.zzonecore.Command;

import net.danh.dcore.NMS.NMSAssistant;
import net.danh.zzonecore.mZZone.pdZZone.ZZLevel;
import net.danh.zzonecore.mZZone.pdZZone.ZZMana;
import net.danh.zzonecore.mZZone.pdZZone.ZZStamina;
import net.danh.zzonecore.mZZone.pdZZone.ZZXP;
import net.danh.zzonecore.mZZone.vZZ;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.danh.dcore.Utils.Player.sendConsoleMessage;
import static net.danh.dcore.Utils.Player.sendPlayerMessage;

public class CMDBase extends net.danh.dcore.Commands.CMDBase {
    public CMDBase(JavaPlugin core) {
        super(core, "zzone");
    }

    @Override
    public void playerexecute(Player p, String[] args) {
        if (p.hasPermission("zzone.admin")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("v")) {
                    sendPlayerMessage(p, "&e&m                     &b ZZoneCore &e&m                    ");
                    sendPlayerMessage(p, "&6Server Version:&f " + new NMSAssistant().getNMSVersion());
                    sendPlayerMessage(p, "&6Plugin Version:&f " + new vZZ().getOriginalVersion());
                    sendPlayerMessage(p, "&6Premium:&f " + new vZZ().isPremium().getSymbol());
                    sendPlayerMessage(p, "&6Dev Build Version:&f " + new vZZ().getDevBuildVersion());
                    sendPlayerMessage(p, "&6Dev Build:&f " + new vZZ().isDevBuild().getSymbol());
                    sendPlayerMessage(p, "&6Release Link:&f " + new vZZ().getReleaseLink());
                    sendPlayerMessage(p, "&e&m                     &b ZZoneCore &e&m                    ");
                }
            }
            if (args.length == 4) {
                Player t = Bukkit.getPlayer(args[2]);
                if (t == null) return;
                if (args[1].equalsIgnoreCase("set")) {
                    if (args[0].equalsIgnoreCase("xp")) {
                        ZZXP.setXP(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("level")) {
                        ZZLevel.setLevel(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("mana")) {
                        ZZMana.setMana(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("max_mana")) {
                        ZZMana.setMaxMana(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("stamina")) {
                        ZZStamina.setStamina(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("max_stamina")) {
                        ZZStamina.setMaxStamina(t, Integer.valueOf(args[3]));
                    }
                }
                if (args[1].equalsIgnoreCase("add")) {
                    if (args[0].equalsIgnoreCase("xp")) {
                        ZZXP.addXP(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("level")) {
                        ZZLevel.addLevel(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("mana")) {
                        ZZMana.addMana(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("max_mana")) {
                        ZZMana.addMaxMana(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("stamina")) {
                        ZZStamina.addStamina(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("max_stamina")) {
                        ZZStamina.addMaxStamina(t, Integer.valueOf(args[3]));
                    }
                }
                if (args[1].equalsIgnoreCase("remove")) {
                    if (args[0].equalsIgnoreCase("xp")) {
                        ZZXP.removeXP(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("level")) {
                        ZZLevel.removeLevel(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("mana")) {
                        ZZMana.removeMana(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("max_mana")) {
                        ZZMana.removeMaxMana(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("stamina")) {
                        ZZStamina.removeStamina(t, Integer.valueOf(args[3]));
                    }
                    if (args[0].equalsIgnoreCase("max_stamina")) {
                        ZZStamina.removeMaxStamina(t, Integer.valueOf(args[3]));
                    }
                }
            }
        }
    }

    @Override
    public void consoleexecute(ConsoleCommandSender c, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("v")) {
                sendConsoleMessage(c, "&e-------------------- &bZZoneCore &e--------------------");
                sendConsoleMessage(c, "&6Server Version:&f " + new NMSAssistant().getNMSVersion());
                sendConsoleMessage(c, "&6Plugin Version:&f " + new vZZ().getOriginalVersion());
                sendConsoleMessage(c, "&6Premium:&f " + new vZZ().isPremium().getSymbol());
                sendConsoleMessage(c, "&6Dev Build Version:&f " + new vZZ().getDevBuildVersion());
                sendConsoleMessage(c, "&6Dev Build:&f " + new vZZ().isDevBuild().getSymbol());
                sendConsoleMessage(c, "&6Release Link:&f " + new vZZ().getReleaseLink());
                sendConsoleMessage(c, "&e-------------------- &bZZoneCore &e--------------------");
            }
        }
        if (args.length == 4) {
            Player t = Bukkit.getPlayer(args[2]);
            if (t == null) return;
            if (args[1].equalsIgnoreCase("set")) {
                if (args[0].equalsIgnoreCase("xp")) {
                    ZZXP.setXP(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("level")) {
                    ZZLevel.setLevel(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("mana")) {
                    ZZMana.setMana(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("max_mana")) {
                    ZZMana.setMaxMana(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("stamina")) {
                    ZZStamina.setStamina(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("max_stamina")) {
                    ZZStamina.setMaxStamina(t, Integer.valueOf(args[3]));
                }
            }
            if (args[1].equalsIgnoreCase("add")) {
                if (args[0].equalsIgnoreCase("xp")) {
                    ZZXP.addXP(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("level")) {
                    ZZLevel.addLevel(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("mana")) {
                    ZZMana.addMana(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("max_mana")) {
                    ZZMana.addMaxMana(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("stamina")) {
                    ZZStamina.addStamina(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("max_stamina")) {
                    ZZStamina.addMaxStamina(t, Integer.valueOf(args[3]));
                }
            }
            if (args[1].equalsIgnoreCase("remove")) {
                if (args[0].equalsIgnoreCase("xp")) {
                    ZZXP.removeXP(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("level")) {
                    ZZLevel.removeLevel(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("mana")) {
                    ZZMana.removeMana(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("max_mana")) {
                    ZZMana.removeMaxMana(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("stamina")) {
                    ZZStamina.removeStamina(t, Integer.valueOf(args[3]));
                }
                if (args[0].equalsIgnoreCase("max_stamina")) {
                    ZZStamina.removeMaxStamina(t, Integer.valueOf(args[3]));
                }
            }
        }
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        List<String> arg_1 = new ArrayList<>(Arrays.asList("xp", "level", "mana", "max_mana", "stamina", "max_stamina"));
        List<String> arg_2 = new ArrayList<>(Arrays.asList("add", "set", "remove"));
        if (sender.hasPermission("zzone.admin")) {
            if (args.length == 1) {
                commands.add("xp");
                commands.add("level");
                commands.add("mana");
                commands.add("max_mana");
                commands.add("stamina");
                commands.add("max_stamina");
                commands.add("version");
                commands.add("v");
                StringUtil.copyPartialMatches(args[0], commands, completions);
            }
            if (args.length == 2) {
                if (arg_1.contains(args[0])) {
                    commands.add("set");
                    commands.add("add");
                    commands.add("remove");
                    StringUtil.copyPartialMatches(args[1], commands, completions);
                }
            }
            if (args.length == 3) {
                if (arg_2.contains(args[1])) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        commands.add(p.getName());
                    }
                    StringUtil.copyPartialMatches(args[2], commands, completions);
                }
            }
        }
        Collections.sort(completions);
        return completions;
    }
}
