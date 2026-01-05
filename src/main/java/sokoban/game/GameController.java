package sokoban.game;

import fri.shapesge.Manager;
import sokoban.model.level.Level;
import sokoban.model.objects.Box;
import sokoban.model.objects.MoveableObject;
import sokoban.model.objects.Player;
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
import sokoban.render.shapege.ShapesGEVisualFactory;
import sokoban.ui.Button;
import sokoban.ui.UIController;
import sokoban.ui.enums.UIState;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private GameSave currentGame;
    private Level currentLevel;
    private GameState gameState;
    private ArrayList<RenderNode> scene = new ArrayList<>();
    private ArrayList<RenderNode> uiScene = new ArrayList<>();
    private UIState uiState = UIState.MAIN_MENU;
    private final Manager manager = new Manager();
    private final Renderer renderer;
    private final FileSystemSaveRepository fileSystemSaveRepository;
    private MovementManager movementManager;
    private final RenderFactory renderFactory;
    private final UIController uiController;
    private final Timer timer;

    public GameController() {
        this.renderer = new Renderer();
        this.fileSystemSaveRepository = new FileSystemSaveRepository();
        this.renderFactory = new RenderFactory(new ShapesGEVisualFactory());
        this.uiController = new UIController(this.renderFactory, this);
        this.timer = new Timer();
        this.manager.manageObject(this);

        List<SaveDescriptor> loadedSaves = this.fileSystemSaveRepository.listSaves();
        this.uiScene.addAll(this.uiController.renderMainMenu(loadedSaves));
        this.renderer.render(this.uiScene);
    }

    public void leftClick(int x, int y) {
        System.out.format("LEFT [%d, %d]%n", x, y);
        Position clickPosition = new Position(x, y);

        for (Button b : this.uiController.getActiveButtons()) {
            if (b.isClicked(clickPosition)) {
                System.out.println("clicked");
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
                this.timer.start();
                break;
            }
        }
    }

    public void resetGame() {
        System.out.format("RESET%n");

        if (this.currentLevel != null && this.uiState == UIState.IN_GAME) {
            this.gameState = GameStateFactory.fromLevel(this.currentLevel);
        }
    }

    public void escape() {
        System.out.format("ESCAPE%n");
    }

    public void loadSave(SaveDescriptor saveDescriptor) {
        this.currentGame = FileSystemSaveRepository.load(saveDescriptor.path());
        this.currentLevel = this.currentGame.level();

        int width = this.currentLevel.getWidth();
        int height = this.currentLevel.getHeight();
        MoveableObject[][] grid = new MoveableObject[height][width];

        Position pp = this.currentGame.state().player();
        grid[pp.getY()][pp.getX()] = new Player(pp);

        for (Position bp : this.currentGame.state().boxes()) {
            grid[bp.getY()][bp.getX()] = new Box(bp);
        }

        this.gameState = new GameState(
                width,
                height,
                this.currentGame.state().player(),
                grid,
                this.currentGame.state().moves(),
                this.currentGame.state().pushes(),
                this.currentGame.state().timeElapsed()
        );
    }

    private String convertTimeToString(int timeElapsedInSeconds) {
        int minutes = timeElapsedInSeconds / 60;
        int seconds = timeElapsedInSeconds % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    public void tick() {
        if (this.uiState == UIState.IN_GAME) {
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
    }
}
