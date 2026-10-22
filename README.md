# Project Chassis

An original vehicle mod for Minecraft **NeoForge 1.21.1**, by Phoenix Software / MADDX Studios.

Chunky, vanilla-feel vehicles at a compact scale — with real systems underneath: modular parts that wear out, servicing, workshops, fuel stations, licence plates and liveries, a vehicle database, deliveries, buses and more.

> Status: **Planning / Phase 0.** Nothing playable yet.

## Highlights (planned)
- Modular parts that wear, get repaired a few times, then need replacing
- Servicing, tyre tread and pressures
- Workshop and fuel station blocks (petrol, diesel, electric)
- Licence plates, text liveries and fleet numbers
- Vehicle Database and Vehicle Safety Inspection
- Dealerships, towing, logistics and bus routes
- Cars, vans, HGVs, emergency services, utility, boats and aircraft

See the full [ROADMAP](ROADMAP.md).

## Building
Needs Java 21. On Fedora: `sudo dnf install java-21-openjdk-devel`.

```bash
./gradlew build          # jar lands in build/libs/
./gradlew runClient      # dev client with the mod loaded
```

## Docs
- [Vehicle definition format](docs/VEHICLE_FORMAT.md) — how vehicles are defined in JSON
- [Scale reference](docs/SCALE.md) — sizes and style rules for Blockbench

Part of **Project Harmony**.
