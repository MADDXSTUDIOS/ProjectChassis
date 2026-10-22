package uk.co.maddxstudios.chassis.vehicle.definition;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

/** Kinds of part slot a chassis can expose. Each slot type wears and is serviced in its own way (Phase 2). */
public enum PartType implements StringRepresentable {
    ENGINE("engine"),
    MOTOR("motor"),
    WHEEL("wheel"),
    BRAKES("brakes"),
    SUSPENSION("suspension"),
    FUEL_TANK("fuel_tank"),
    BATTERY("battery"),
    HITCH("hitch"),
    ACCESSORY("accessory");

    public static final Codec<PartType> CODEC = StringRepresentable.fromEnum(PartType::values);

    private final String name;

    PartType(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }
}
