# Vehicle definition format

Every vehicle chassis is a JSON file in a datapack:

```
data/<namespace>/chassis/vehicles/<name>.json   →   id <namespace>:<name>
```

So the bundled City Car is `data/chassis/chassis/vehicles/city_car.json` → `chassis:city_car`. Other mods and modpacks can add or override vehicles the same way, and `/reload` picks up changes.

A definition says what a vehicle **is**. Live state (part condition, fuel, keeper, plate, paint) lives on the vehicle entity, not here.

## Fields

| Field | Type | Required | Default | Notes |
|---|---|---|---|---|
| `category` | string | yes | | `car`, `van`, `bike`, `hgv`, `trailer`, `bus`, `emergency`, `construction`, `farm`, `municipal`, `utility`, `boat`, `aircraft` |
| `dimensions` | object | yes | | `length`, `width`, `height` in blocks (0.25–32) |
| `seats` | list | yes | | Each `{ "offset": [x, y, z], "driver": bool }`. Blocks from the vehicle origin; x right, y up, z forward |
| `part_slots` | list | no | `[]` | Each `{ "type", "count", "required" }` |
| `stats` | object | yes | | Base handling with stock parts, see below |
| `energy` | string | no | `petrol` | `petrol`, `diesel`, `electric`, `none` |
| `storage_slots` | int | no | `0` | Boot / load space, 0–270 |
| `dashboard` | string | no | `basic` | `none`, `basic`, `mid`, `premium_ev`, `bus`, `emergency`, `hgv` |

### Part slot types
`engine`, `motor` (EVs), `wheel`, `brakes`, `suspension`, `fuel_tank`, `battery`, `hitch`, `accessory`

`count` defaults to 1 (max 32). `required` defaults to true — a vehicle won't drive with a required slot empty.

### Stats
| Field | Unit | Range |
|---|---|---|
| `max_speed` | blocks/sec | 0–100 |
| `acceleration` | blocks/sec² | 0–100 |
| `braking` | blocks/sec² | 0–100 |
| `turn_rate` | degrees/sec | 0–720 |
| `step_height` | blocks | 0–2 (default 1) |

## Validation rules
- Powered vehicles (any `energy` except `none`) need **exactly one** driver seat.
- Unpowered vehicles (`energy: none`, e.g. trailers) can't have a driver seat.
- A file that fails validation is skipped with an error in the log; it never crashes the game.

## Example
See [`city_car.json`](../src/main/resources/data/chassis/chassis/vehicles/city_car.json).
