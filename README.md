# Sokoban – OOP Project (Java)

Sokoban is a grid-based puzzle game implemented in Java as a school project for an **Object-Oriented Programming course**.
The project focuses on clean OOP architecture, proper separation of concerns, and extensible game design.

The game uses an external lightweight graphics engine ([ShapesGE](https://github.com/infjava/shapesge/releases)) for rendering, input handling, and basic scene management.

---

## Project Goals
- Apply object-oriented design principles in practice
- Separate game logic, UI, rendering, and persistence
- Implement:
  - Level loading from files
  - Save/Load game state
  - UI with menus and in-game HUD
  - Keyboard input handling
- Produce a defensible UML design suitable for academic evaluation

---

## Architecture Overview

The project is structured into these main subsystems:
### Core Game Logic
- GameController – main game coordinator
- Level – game state container
- MovementManager – all movement and collision logic

### GameObject hierarchy:
- Player
- Box
- Wall, Floor, Goal

### UI System
- UIController – manages UI states (menu, game, pause, etc.)
- UIElement (abstract)
- Button
- Label

### Rendering
- Renderer – synchronizes game/UI state with the engine
- RenderNode – wrapper over engine objects
- RenderFactory – creates visuals for game and UI elements

### Persistence
- LevelRepository – loads and saves levels / game states

### Input
- InputAdapter – maps raw keyboard input to GameAction

---

## Project Structure

```
src/
├── controller/
├── model/
│   ├── level/
│   ├── objects/
│   └── movement/
├── ui/
├── render/
├── input/
├── persistence/
└── Main.java
```

---

## Controls (planned)

| Action     | Key       |
| ---------- | --------- |
| Move Up    | ↑ / W     |
| Move Down  | ↓ / S     |
| Move Left  | ← / A     |
| Move Right | → / D     |
| Save Game  | F5        |
| Load Game  | F9        |
| Open Menu  | ESC       |
| Confirm    | ENTER     |
| Cancel     | BACKSPACE |

---

## Technologies Used

- Java
- ShapesGE (for rendering & input)
- Git + GitHub (for version control)
- UML (for design documentation)

---

## How to Run
1. Clone the [repository](https://github.com/PeterSK-bit/sokoban-java.git).
2. Open the project in your IDE. (IntelliJ / Eclipse)
3. Add [ShapesGE](https://github.com/infjava/shapesge/releases) lib manually. Automatic dependency resolution is not available.
4. Run Main.java.

---

## Licence
- This project is released under the MIT License.
- See the `LICENSE` file for full text.

---

## Academic Notice
- This project is part of a school assignment.
- Copying the code for submission in other courses without permission may constitute plagiarism.
