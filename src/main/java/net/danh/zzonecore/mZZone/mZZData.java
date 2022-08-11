package net.danh.zzonecore.mZZone;

public enum mZZData {
    MANA("mana"),
    MAX_MANA("max_mana"),
    STAMINA("stamina"),
    MAX_STAMINA("max_stamina"),
    LEVEL("level"),
    MAX_LEVEL("max_level"),
    XP("xp"),
    MAX_XP("max_xp");

    private final String name;

    mZZData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
