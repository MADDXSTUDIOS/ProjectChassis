# Project Chassis — Development Roadmap

An original vehicle mod for Minecraft (NeoForge 1.21.1) by Phoenix Software / MADDX Studios.
Inspired by the feel and scale of MrCrayfish's Vehicle Mod — built from scratch, with deeper systems: modular parts that wear, servicing, workshops, fuel, logistics, buses and more.

Part of **Project Harmony**.

---

## Guiding rules

- **Vanilla feel, Crayfish scale.** Chunky, low-res 16x textures, compact proportions. Stepped edges instead of angles.
- **Entities, not contraptions.** Every vehicle is an entity built from a chassis + parts.
- **Core first, content later.** One car that feels great beats fifty that feel shit.
- **No hard dependencies.** CC:Tweaked, Lightman's Currency and the business mod are optional integrations through clean APIs.
- **Configurable.** Wear harshness, repair limits, VSI, fuel use, economy item — all server config.

---

## Phase 0 — Foundations
- [ ] Repo, NeoForge 1.21.1 MDK, Gradle build, GitHub Actions CI
- [ ] Code style, branch rules, issue labels, project board
- [ ] Blockbench pipeline + scale reference (1 block cube, player height)
- [ ] Data-driven vehicle definition format (JSON: chassis, seats, part slots, stats)

## Phase 1 — Core vehicle (MVP: "it drives")
- [ ] Base vehicle entity: spawn, mount, dismount, seats
- [ ] Driving physics: acceleration, braking, steering, slopes, step-up
- [ ] Client/server sync and multiplayer smoothness
- [ ] Collision with blocks and entities
- [ ] First vehicle: **City Car v2** (stepped-edge design)
- [ ] Basic keys (VIN-bound, no key = no drive)
- [ ] Vehicle item / placement and pick-up

## Phase 2 — Parts, wear and servicing
- [ ] Modular part slots: engine, wheels/tyres, brakes, suspension, fuel tank/battery, hitch, accessory slots
- [ ] Part quality tiers (cheap / standard / performance–heavy duty)
- [ ] Wear model: condition, max condition, repair count; each repair lowers the ceiling, then scrap
- [ ] Worn parts perform worse (power loss, misfires, longer braking, bouncy suspension, less grip)
- [ ] Tyres: tread + pressure, tyre types (road, all-terrain, off-road, winter), flats and blowouts
- [ ] Servicing: interval by engine hours, oil/filters/coolant/brake fluid; serviced = slower wear, overdue = faster wear
- [ ] Service history stored on the vehicle

## Phase 3 — Workshops and fuel
- [ ] Workshop blocks: vehicle lift, mechanic's bench, tyre machine, compressor, diagnostic block, tyre rack, parts store, shelving
- [ ] Fuel types: petrol, diesel, electric
- [ ] Production chain: oil → refinery → fuel
- [ ] Fuel pump (owner-set price), underground tank (needs deliveries), jerry can
- [ ] EV charger running on FE; battery health for EVs

## Phase 4 — Identity and registration
- [ ] Unique VIN per vehicle; world-level registry (single source of truth)
- [ ] Licence plates (plate press block)
- [ ] Paint: primary + secondary colours via dyes / spray
- [ ] Text liveries: company name and fleet numbers in decal zones (no custom images)
- [ ] Keys: remote lock/unlock with range, spare keys, key revocation
- [ ] **Vehicle Database** block (admin): search by plate / VIN / keeper; type, colour, keeper (player or business), fleet number, service and VSI info, status (Active, Off-Road, Due Service, VSI Expired, Written Off, Scrapped); admin actions
- [ ] **Vehicle Safety Inspection (VSI)**: test station block, pass/fail on tyres, brakes, lights; config toggle

## Phase 5 — Cabin, dashboards and accessories
- [ ] Dashboard HUD overlay when driving, tiered per vehicle (basic / mid / premium EV / bus / emergency)
- [ ] On-vehicle storage (boot, load space) sized per vehicle, locked with the vehicle
- [ ] Accessory system: player-fitted (phone mount, dashcam, basic satnav, roof box) vs workshop-installed (built-in satnav, stereo upgrade, hitch, light bar, tracker)
- [ ] Factory-fitted options per model
- [ ] Car radio using the shared Project Harmony audio engine library (stripped-down: source, volume, skip)
- [ ] Audio isolated to the vehicle: full inside, muffled bleed outside scaling with volume
- [ ] Satnav: waypoints, job destinations, bus stops

## Phase 6 — Dealerships and economy
- [ ] Dealership / showroom block with static display models
- [ ] Owner-set price in any vanilla item
- [ ] Service history affects resale value
- [ ] Economy API hook (Lightman's Currency later)

## Phase 7 — Towing and logistics
- [ ] Hitch classes: light, heavy, fifth wheel, agricultural
- [ ] Trailers with articulation; towing affects handling and wear
- [ ] Cargo: inventories sized by vehicle/trailer, fluid tankers
- [ ] Pallets + forklift loading
- [ ] Loading bay block linked to storage
- [ ] Dispatch block, delivery block, job board
- [ ] Routes as waypoint lists with satnav guidance (player-driven)

## Phase 8 — Public transport
- [ ] Minibus, single-decker, double-decker, coach
- [ ] Bus stop block (named stops)
- [ ] Depot block: build routes by linking stops, route numbers
- [ ] Destination blind + next-stop display
- [ ] Timetables (later), ticketing (later — vanilla item / Lightman's)

## Phase 9 — Content expansion (land)
- [ ] Cars: hatchback, saloon, estate, sports, 4x4, premium EV
- [ ] Vans: small panel, Transit-size, Luton, campervan (sleepable)
- [ ] Bikes: motorbike, quad
- [ ] HGV: tractor unit, curtainsider, box, flatbed, tanker, tipper
- [ ] Emergency: ambulance (stretcher), fire engine (hose, ladder), police car (plate lookup, lights and sirens)
- [ ] Construction: excavator, dumper, cement mixer, road roller, telehandler, mobile crane
- [ ] Farm: tractor, combine, sprayer, baler, plough, seeder, trailer
- [ ] Municipal: bin lorry, road sweeper, gritter, cherry picker
- [ ] Recovery: tow truck, low-loader
- [ ] Yard: forklift, pallet truck, yard shunter
- [ ] Oddballs: milk float, ice cream van, fuel bowser

## Phase 10 — Water
- [ ] Boat physics
- [ ] Jet ski, dinghy, speedboat, fishing boat, narrowboat, small cargo ship
- [ ] Coastguard boat

## Phase 11 — Air (arcade, not sim)
- [ ] Helicopter first (hover-based)
- [ ] Light plane, crop duster, cargo plane, air ambulance

## Phase 12 — Integrations
- [ ] CC:Tweaked peripheral: speed, fuel, part condition, location, cargo
- [ ] Vehicle tracking and fleet management
- [ ] Phoenix OS registration app (tax, VSI records, plate lookups)
- [ ] Business mod: keeper-as-business via API
- [ ] Lightman's Currency: dealerships, fuel pumps, tickets
- [ ] Autonomous driving (endgame stretch goal)

---

## Dependencies
- **Project Harmony audio engine** (shared library with Project Retnica) — needed for Phase 5 radio.
