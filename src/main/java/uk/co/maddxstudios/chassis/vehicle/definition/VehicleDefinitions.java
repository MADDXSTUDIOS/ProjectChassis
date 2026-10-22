package uk.co.maddxstudios.chassis.vehicle.definition;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import uk.co.maddxstudios.chassis.Chassis;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Server-side registry of vehicle definitions, rebuilt on every datapack reload.
 * Files live at {@code data/<namespace>/chassis/vehicles/<name>.json}; the id is {@code <namespace>:<name>}.
 *
 * TODO (Phase 1): sync definitions to clients on login and after /reload.
 */
public final class VehicleDefinitions extends SimpleJsonResourceReloadListener {
    public static final String DIRECTORY = "chassis/vehicles";
    private static final Gson GSON = new GsonBuilder().create();
    private static final VehicleDefinitions INSTANCE = new VehicleDefinitions();

    private Map<ResourceLocation, VehicleDefinition> definitions = Map.of();

    private VehicleDefinitions() {
        super(GSON, DIRECTORY);
    }

    public static void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(INSTANCE);
    }

    public static Optional<VehicleDefinition> get(ResourceLocation id) {
        return Optional.ofNullable(INSTANCE.definitions.get(id));
    }

    public static Map<ResourceLocation, VehicleDefinition> all() {
        return INSTANCE.definitions;
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> files, ResourceManager resourceManager, ProfilerFiller profiler) {
        Map<ResourceLocation, VehicleDefinition> loaded = new HashMap<>();
        files.forEach((id, json) -> VehicleDefinition.CODEC.parse(JsonOps.INSTANCE, json)
                .resultOrPartial(error -> Chassis.LOGGER.error("Skipping vehicle definition {}: {}", id, error))
                .ifPresent(def -> loaded.put(id, def)));
        definitions = Map.copyOf(loaded);
        Chassis.LOGGER.info("Loaded {} vehicle definition(s)", definitions.size());
    }
}
