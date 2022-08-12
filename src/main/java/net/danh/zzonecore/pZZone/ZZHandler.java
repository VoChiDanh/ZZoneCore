package net.danh.zzonecore.pZZone;

import net.Indyuce.mmoitems.api.player.PlayerData;
import net.Indyuce.mmoitems.api.player.RPGPlayer;
import net.Indyuce.mmoitems.comp.rpg.RPGHandler;
import net.danh.zzonecore.mZZone.pdZZone.ZZLevel;
import net.danh.zzonecore.mZZone.pdZZone.ZZMana;
import net.danh.zzonecore.mZZone.pdZZone.ZZStamina;
import org.jetbrains.annotations.NotNull;

public class ZZHandler implements RPGHandler {
    @Override
    public RPGPlayer getInfo(PlayerData playerData) {
        return new ZZPlayer(playerData);
    }

    @Override
    public void refreshStats(PlayerData playerData) {

    }

    public static class ZZPlayer extends RPGPlayer {

        public ZZPlayer(@NotNull PlayerData playerData) {
            super(playerData);
        }

        @Override
        public int getLevel() {
            return ZZLevel.getLevel(getPlayer());
        }

        @Override
        public String getClassName() {
            return "";
        }

        @Override
        public double getMana() {
            return ZZMana.getMana(getPlayer());
        }

        @Override
        public void setMana(double v) {
            ZZMana.setMana(getPlayer(), (int) v);
        }

        @Override
        public double getStamina() {
            return ZZStamina.getStamina(getPlayer());
        }

        @Override
        public void setStamina(double v) {
            ZZStamina.setStamina(getPlayer(), (int) v);
        }
    }
}
