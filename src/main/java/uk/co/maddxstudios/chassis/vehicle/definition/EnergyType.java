package uk.co.maddxstudios.chassis.vehicle.definition;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

/** What a vehicle runs on. NONE is for trailers and anything else without a drivetrain. */
public enum EnergyType implements StringRepresentable {
    PETROL("petrol"),
    DIESEL("diesel"),
    ELECTRIC("electric"),
    NONE("none");

    public static final Codec<EnergyType> CODEC = StringRepresentable.fromEnum(EnergyType::values);

    private final String name;

    EnergyType(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
