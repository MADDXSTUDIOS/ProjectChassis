package uk.co.maddxstudios.chassis.vehicle.definition;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

/** Broad vehicle family. Drives defaults like licence class, dashboard and which workshops can service it. */
public enum VehicleCategory implements StringRepresentable {
    CAR("car"),
    VAN("van"),
    BIKE("bike"),
    HGV("hgv"),
    TRAILER("trailer"),
    BUS("bus"),
    EMERGENCY("emergency"),
    CONSTRUCTION("construction"),
    FARM("farm"),
    MUNICIPAL("municipal"),
    UTILITY("utility"),
    BOAT("boat"),
    AIRCRAFT("aircraft");

    public static final Codec<VehicleCategory> CODEC = StringRepresentable.fromEnum(VehicleCategory::values);

    private final String name;

    VehicleCategory(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
