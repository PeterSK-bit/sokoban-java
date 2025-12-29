package sokoban.controller;

import sokoban.input.InputAdapter;
import sokoban.model.level.Level;
import sokoban.movement.GameState;
import sokoban.movement.MovementManager;
import sokoban.persistence.LevelRepository;
import sokoban.render.core.Renderer;
import sokoban.ui.UIController;
import sokoban.ui.enums.UIState;

public class GameController {
    private Level currentLevel;
    private GameState gameState;
    private final Renderer renderer;
    private final InputAdapter inputAdapter;
    private final UIController uiController;
    private final LevelRepository levelRepository;
    private final MovementManager movementManager;
    private UIState uiState = UIState.MAIN_MENU;

    public GameController(
            Renderer renderer, UIController uiController, InputAdapter inputAdapter,
            LevelRepository levelRepository, MovementManager movementManager
    ) {
        this.renderer = renderer;
        this.uiController = uiController;
        this.inputAdapter = inputAdapter;
        this.levelRepository = levelRepository;
        this.movementManager = movementManager;
    }
}
