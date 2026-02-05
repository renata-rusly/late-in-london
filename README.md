# late-in-london
A Java console-based text adventure where you navigate rooms across a home and London locations, collect key items, and reach the final destination without triggering a loss condition.

Originally developed as a university coursework project

## Gameplay Overview
You begin in a bedroom and explore connected locations to collect essentials (e.g., key items needed to leave safely and access the tube). Some events depend on interactions with a moving character, and there are mechanics that can help—or set you back—depending on your choices.

## Key Features
- **Room graph navigation** (multiple connected locations)
- **Command parser** with support for multi-word commands (e.g., interactions that include a target + item)
- **Inventory system** with constraints (e.g., weight/limits)
- **Event mechanics** including randomized character movement and a teleport/portal-style feature
- **Win/Loss conditions** enforced by game state checks

## Project Structure
- `src/` — Java source files (game loop, rooms, parser, player/inventory, items, character logic)

## How to Run

### Option A — Run the packaged JAR (recommended)
```bash
java -jar late-in-london.jar