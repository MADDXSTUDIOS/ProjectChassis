package uk.co.maddxstudios.chassis.vehicle.definition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.phys.Vec3;

import java.util.List;

/**
 * A data-driven vehicle chassis, loaded from {@code data/<namespace>/chassis/vehicles/<name>.json}.
 *
 * Describes what a vehicle IS (size, seats, part slots, base handling). Live state like part
 * condition, fuel level and keeper belongs on the entity, not here. See docs/VEHICLE_FORMAT.md.
 */
public record VehicleDefinition(
        VehicleCategory category,
        Dimensions dimensions,
        List<Seat> seats,
        List<PartSlot> partSlots,
        DrivingStats stats,
        EnergyType energy,
        int storageSlots,
        DashboardTier dashboard
) {
    public static final Codec<VehicleDefinition> CODEC = RecordCodecBuilder.<VehicleDefinition>create(i -> i.group(
            VehicleCategory.CODEC.fieldOf("category").forGetter(VehicleDefinition::category),
            Dimensions.CODEC.fieldOf("dimensions").forGetter(VehicleDefinition::dimensions),
            Seat.CODEC.listOf().fieldOf("seats").forGetter(VehicleDefinition::seats),
            PartSlot.CODEC.listOf().optionalFieldOf("part_slots", List.of()).forGetter(VehicleDefinition::partSlots),
            DrivingStats.CODEC.fieldOf("stats").forGetter(VehicleDefinition::stats),
            EnergyType.CODEC.optionalFieldOf("energy", EnergyType.PETROL).forGetter(VehicleDefinition::energy),
            Codec.intRange(0, 270).optionalFieldOf("storage_slots", 0).forGetter(VehicleDefinition::storageSlots),
            DashboardTier.CODEC.optionalFieldOf("dashboard", DashboardTier.BASIC).forGetter(VehicleDefinition::dashboard)
    ).apply(i, VehicleDefinition::new)).validate(VehicleDefinition::validate);

    private static DataResult<VehicleDefinition> validate(VehicleDefinition def) {
        long drivers = def.seats().stream().filter(Seat::driver).count();
        boolean powered = def.energy() != EnergyType.NONE;
        if (powered && drivers != 1) {
            return DataResult.error(() -> "Powered vehicles need exactly one driver seat, found " + drivers);
        }
        if (!powered && drivers != 0) {
            return DataResult.error(() -> "Unpowered vehicles (energy: none) can't have a driver seat");
        }
        return DataResult.success(def);
    }

    /** Size in blocks. 1.0 = one block = 16 Blockbench pixels. */
    public record Dimensions(float length, float width, float height) {
        private static final Codec<Float> SIZE = Codec.floatRange(0.25F, 32.0F);

        public static final Codec<Dimensions> CODEC = RecordCodecBuilder.create(i -> i.group(
                SIZE.fieldOf("length").forGetter(Dimensions::length),
                SIZE.fieldOf("width").forGetter(Dimensions::width),
                SIZE.fieldOf("height").forGetter(Dimensions::height)
        ).apply(i, Dimensions::new));
    }

    /** A seat, offset from the vehicle origin in blocks (x = right, y = up, z = forward). */
    public record Seat(Vec3 offset, boolean driver) {
        public static final Codec<Seat> CODEC = RecordCodecBuilder.create(i -> i.group(
                Vec3.CODEC.fieldOf("offset").forGetter(Seat::offset),
                Codec.BOOL.optionalFieldOf("driver", false).forGetter(Seat::driver)
        ).apply(i, Seat::new));
    }

    /** A group of identical part slots, e.g. four wheels. */
    public record PartSlot(PartType type, int count, boolean required) {
        public static final Codec<PartSlot> CODEC = RecordCodecBuilder.create(i -> i.group(
                PartType.CODEC.fieldOf("type").forGetter(PartSlot::type),
                Codec.intRange(1, 32).optionalFieldOf("count", 1).forGetter(PartSlot::count),
                Codec.BOOL.optionalFieldOf("required", true).forGetter(PartSlot::required)
        ).apply(i, PartSlot::new));
    }

    /**
     * Base handling with stock parts fitted. Parts and wear modify these at runtime.
     * Speeds in blocks per second, turn rate in degrees per second.
     */
    public record DrivingStats(float maxSpeed, float acceleration, float braking, float turnRate, float stepHeight) {
        public static final Codec<DrivingStats> CODEC = RecordCodecBuilder.create(i -> i.group(
                Codec.floatRange(0.0F, 100.0F).fieldOf("max_speed").forGetter(DrivingStats::maxSpeed),
                Codec.floatRange(0.0F, 100.0F).fieldOf("acceleration").forGetter(DrivingStats::acceleration),
                Codec.floatRange(0.0F, 100.0F).fieldOf("braking").forGetter(DrivingStats::braking),
                Codec.floatRange(0.0F, 720.0F).fieldOf("turn_rate").forGetter(DrivingStats::turnRate),
                Codec.floatRange(0.0F, 2.0F).optionalFieldOf("step_height", 1.0F).forGetter(DrivingStats::stepHeight)
        ).apply(i, DrivingStats::new));
    }
}
