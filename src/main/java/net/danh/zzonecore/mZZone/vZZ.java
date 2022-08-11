package net.danh.zzonecore.mZZone;

import net.danh.dcore.Utils.DVersion;
import net.danh.dcore.Utils.Status;
import net.danh.zzonecore.ZZoneCore;

public class vZZ extends DVersion {

    @Override
    public String getOriginalVersion() {
        return ZZoneCore.getZZ().getDescription().getVersion();
    }

    @Override
    public String getDevBuildVersion() {
        return Status.FALSE.getSymbol();
    }

    @Override
    public Status isDevBuild() {
        return Status.FALSE;
    }

    @Override
    public Status isPremium() {
        return Status.TRUE;
    }

    @Override
    public String getReleaseLink() {
        return Status.FALSE.getSymbol();
    }
}
