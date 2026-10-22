package uk.co.maddxstudios.chassis.vehicle.definition;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

/** Which driving HUD a vehicle gets (Phase 5). */
public enum DashboardTier implements StringRepresentable {
    NONE("none"),
    BASIC("basic"),
    MID("mid"),
    PREMIUM_EV("premium_ev"),
    BUS("bus"),
    EMERGENCY("emergency"),
    HGV("hgv");

    public static final Codec<DashboardTier> CODEC = StringRepresentable.fromEnum(DashboardTier::values);

    private final String name;

    DashboardTier(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
