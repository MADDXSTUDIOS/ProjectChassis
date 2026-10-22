package uk.co.maddxstudios.chassis;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import uk.co.maddxstudios.chassis.vehicle.definition.VehicleDefinitions;

/**
 * Project Chassis — entry point.
 *
 * Phase 0 only wires up the data-driven vehicle definitions. Entities, parts,
 * wear and everything else land in later phases (see ROADMAP.md).
 */
@Mod(Chassis.MODID)
public final class Chassis {
    public static final String MODID = "chassis";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Chassis(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.addListener(VehicleDefinitions::onAddReloadListeners);
        LOGGER.info("Project Chassis loading — Phase 0 scaffold");
    }
}
