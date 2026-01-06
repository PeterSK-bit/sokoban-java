package sokoban.game;

import fri.shapesge.Manager;
import sokoban.model.enums.Direction;
import sokoban.model.level.Level;
import sokoban.model.objects.MoveableObject;
import sokoban.model.objects.StaticObject;
import sokoban.model.position.Position;
import sokoban.model.timer.Timer;
import sokoban.movement.GameState;
import sokoban.movement.MovementManager;
import sokoban.persistence.save.FileSystemSaveRepository;
import sokoban.persistence.save.GameSave;
import sokoban.persistence.save.SaveDescriptor;
import sokoban.render.core.RenderFactory;
import sokoban.render.core.RenderNode;
import sokoban.render.core.Renderer;
import sokoban.render.enums.RenderType;
import sokoban.render.shapege.ShapesGEVisualFactory;
import sokoban.ui.Button;
import sokoban.ui.UIController;
import sokoban.ui.enums.UIState;

import java.util.ArrayList;
import java.util.List;

/**
 * Central application controller.
 * Orchestrates game state, rendering, UI interaction, input handling,
 * persistence, and timing.
 *
 * @author Peter Magdík
 */
public class GameController {
    private GameSave currentGame;
    private Level currentLevel;
    private GameState gameState;
    private UIState uiState = UIState.MAIN_MENU;
    private MovementManager movementManager;
    private final ArrayList<RenderNode> scene = new ArrayList<>();
    private final ArrayList<RenderNode> uiScene = new ArrayList<>();
    private final Renderer renderer;
    private final RenderFactory renderFactory;
    private final UIController uiController;
    private final Timer timer;

    /**
     * Initializes the game controller, renderer, UI,
     * registers input handling and renders the main menu.
     */
    public GameController() {
        this.renderer = new Renderer();
        this.renderFactory = new RenderFactory(new ShapesGEVisualFactory());
        this.uiController = new UIController(this.renderFactory, this);
        this.timer = new Timer();

        Manager manager = new Manager();
        manager.manageObject(this);

        List<SaveDescriptor> loadedSaves = FileSystemSaveRepository.listSaves();
        this.uiScene.addAll(this.uiController.renderMainMenu(loadedSaves));
        this.renderer.render(this.uiScene);
    }

    /**
     * Handles left mouse click input.
     * Used primarily for UI interaction (buttons).
     *
     * @param x x-coordinate of click
     * @param y y-coordinate of click
     */
    public void leftClick(int x, int y) {
        Position clickPosition = new Position(x, y);

        for (Button b : this.uiController.getActiveButtons()) {
            if (b.isClicked(clickPosition)) {
                this.uiState = UIState.IN_GAME;

                b.onClick();
                this.movementManager = new MovementManager(this.currentLevel, this.gameState);
                this.renderer.remove(this.uiScene);
                this.uiScene.clear();
                this.uiScene.addAll(this.uiController.renderGameUI(
                        this.currentLevel.getLevelNumber(),
                        this.convertTimeToString(this.gameState.getTimeElapsed()),
                        this.gameState.getMoves(),
                        this.gameState.getPushes()
                ));
                this.renderer.render(this.uiScene);
                this.renderBoard();
                this.timer.start();
                break;
            }
        }
    }

    /**
     * Resets the current game to its initial state
     * and re-renders the board.
     */
    public void resetGame() {
        if (this.currentLevel != null && this.uiState == UIState.IN_GAME) {
            this.gameState = GameStateFactory.fromLevel(this.currentGame.level());
            this.movementManager = new MovementManager(this.currentLevel, this.gameState);
            this.renderBoard();
            this.timer.reset();
            this.timer.start();
        }
    }

    /**
     * Handles escape action.
     * Returns the application back to the main menu
     * and clears all runtime state.
     */
    public void escape() {
        System.out.format("ESCAPE%n");
        if (this.uiState != UIState.MAIN_MENU) {
            this.uiState = UIState.MAIN_MENU;

            this.renderer.remove(this.scene);
            this.renderer.remove(this.uiScene);
            this.uiScene.clear();
            this.scene.clear();

            this.gameState = null;
            this.currentLevel = null;
            this.currentGame = null;
            this.timer.reset();


            List<SaveDescriptor> loadedSaves = FileSystemSaveRepository.listSaves();
            this.uiScene.addAll(this.uiController.renderMainMenu(loadedSaves));
            this.renderer.render(this.uiScene);
        }
    }

    /**
     * Toggles pause state.
     * Stops or resumes the game timer accordingly.
     */
    public void pause() {
        switch (this.uiState) {
            case IN_GAME -> {
                this.uiState = UIState.PAUSED;
                this.timer.stop();
            }
            case PAUSED -> {
                this.uiState = UIState.IN_GAME;
                this.timer.start();
            }
        }
    }

    /**
     * Attempts to move the player up.
     */
    public void moveUp() {
        if (this.uiState == UIState.IN_GAME) {
            switch (this.movementManager.tryMove(Direction.UP)) {
                case PASS -> this.move();
                case PUSHED -> {
                    this.gameState.addPush();
                    this.move();
                }
            }
        }
    }

    /**
     * Attempts to move the player down.
     */
    public void moveDown() {
        if (this.uiState == UIState.IN_GAME) {
            switch (this.movementManager.tryMove(Direction.DOWN)) {
                case PASS -> this.move();
                case PUSHED -> {
                    this.gameState.addPush();
                    this.move();
                }
            }
        }
    }

    /**
     * Attempts to move the player left.
     */
    public void moveLeft() {
        if (this.uiState == UIState.IN_GAME) {
            switch (this.movementManager.tryMove(Direction.LEFT)) {
                case PASS -> this.move();
                case PUSHED -> {
                    this.gameState.addPush();
                    this.move();
                }
            }
        }
    }

    /**
     * Attempts to move the player right.
     */
    public void moveRight() {
        if (this.uiState == UIState.IN_GAME) {
            switch (this.movementManager.tryMove(Direction.RIGHT)) {
                case PASS -> this.move();
                case PUSHED -> {
                    this.gameState.addPush();
                    this.move();
                }
                case BLOCKED -> { }
                default -> throw new IllegalStateException();
            }
        }
    }

    /**
     * Applies a successful move:
     * updates statistics, re-renders the board,
     * and checks win condition.
     */
    private void move() {
        this.gameState.addMove();
        this.renderBoard();

        if (this.movementManager.checkWin()) {
            this.uiState = UIState.LEVEL_COMPLETED;
            this.renderer.remove(this.uiScene);
            this.uiScene.clear();
            this.uiScene.addAll(this.uiController.renderGameStats(
                    this.currentLevel.getLevelNumber(),
                    this.convertTimeToString(this.gameState.getTimeElapsed() + this.timer.getElapsedTime()),
                    this.gameState.getMoves(),
                    this.gameState.getPushes()
            ));
            this.renderer.render(this.uiScene);
        }
    }

    /**
     * Loads a saved game from persistence.
     *
     * @param saveDescriptor descriptor identifying the save
     */
    public void loadSave(SaveDescriptor saveDescriptor) {
        this.currentGame = FileSystemSaveRepository.load(saveDescriptor.path());
        this.currentLevel = this.currentGame.level();
        this.gameState = GameStateFactory.fromSave(this.currentGame);
    }

    /**
     * Periodic update callback.
     * Updates UI based on the current UI state.
     */
    public void tick() {
        switch (this.uiState) {
            case IN_GAME -> {
                this.renderer.remove(this.uiScene);
                this.uiScene.clear();

                this.uiScene.addAll(this.uiController.renderGameUI(
                        this.currentLevel.getLevelNumber(),
                        this.convertTimeToString(this.gameState.getTimeElapsed() + this.timer.getElapsedTime()),
                        this.gameState.getMoves(),
                        this.gameState.getPushes()
                ));

                this.renderer.render(this.uiScene);
            }
            case PAUSED -> {
                this.uiScene.addAll(this.uiController.renderPauseUI());
                this.renderer.render(this.uiScene);
            }
        }
    }

    /**
     * Renders the entire game board:
     * static objects, movable objects, and the player.
     */
    private void renderBoard() {
        this.renderer.remove(this.scene);
        this.scene.clear();

        for (StaticObject[] line : this.currentLevel.getStaticObjects()) {
            for (StaticObject object : line) {
                if (object == null) {
                    continue;
                }
                int x = object.getPosition().getX();
                int y = object.getPosition().getY();

                this.scene.add(this.renderFactory.createForGameObject(
                        object.getRenderType(), new Position(x * 50, 30 + y * 50))
                );
            }
        }

        for (MoveableObject[] line : this.gameState.getMoveableObjects()) {
            for (MoveableObject object : line) {
                if (object == null) {
                    continue;
                }
                int x = object.getPosition().getX();
                int y = object.getPosition().getY();

                this.scene.add(this.renderFactory.createForGameObject(
                        object.getRenderType(), new Position(x * 50, 30 + y * 50))
                );
            }
        }
        int x = this.gameState.getPlayerPosition().getX();
        int y = this.gameState.getPlayerPosition().getY();

        this.scene.add(this.renderFactory.createForGameObject(
                RenderType.PLAYER, new Position(x * 50, 30 + y * 50))
        );

        this.renderer.render(this.scene);
    }

    /**
     * Converts elapsed time in seconds to MM:SS format.
     *
     * @param timeElapsedInSeconds elapsed time in seconds
     * @return formatted time string
     */
    private String convertTimeToString(int timeElapsedInSeconds) {
        int minutes = timeElapsedInSeconds / 60;
        int seconds = timeElapsedInSeconds % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }
}
