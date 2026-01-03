package sokoban.controller;

import fri.shapesge.Manager;
import sokoban.model.level.Level;
import sokoban.model.position.Position;
import sokoban.model.timer.Timer;
import sokoban.movement.GameState;
import sokoban.movement.MovementManager;
import sokoban.persistence.save.FileSystemSaveRepository;
import sokoban.render.core.RenderFactory;
import sokoban.render.core.RenderNode;
import sokoban.render.core.Renderer;
import sokoban.render.shapege.ShapesGEVisualFactory;
import sokoban.ui.Button;
import sokoban.ui.UIController;
import sokoban.ui.enums.UIState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private Level currentLevel;
    private GameState gameState;
    private ArrayList<RenderNode> scene = new ArrayList<>();
    private UIState uiState = UIState.MAIN_MENU;
    private final Manager manager = new Manager();
    private final Renderer renderer;
    private final FileSystemSaveRepository fileSystemSaveRepository;
    private final MovementManager movementManager;
    private final RenderFactory renderFactory;
    private final UIController uiController;
    private final Timer timer;

    public GameController() throws IOException {
        this.renderer = new Renderer();
        this.fileSystemSaveRepository = new FileSystemSaveRepository();
        this.renderFactory = new RenderFactory(new ShapesGEVisualFactory());
        this.uiController = new UIController(this.renderFactory);
        this.timer = new Timer();
        this.manager.manageObject(this);

        List<String> loadedSaves = this.fileSystemSaveRepository.scanSaveFolder();
        this.renderer.render(this.uiController.renderMainMenu(loadedSaves));

        this.movementManager = new MovementManager(this.gameState);
    }

    public void leftClick(int x, int y) {
        System.out.format("LEFT [%d, %d]%n", x, y);

        for (Button b : this.uiController.getActiveButtons()) {
            if (b.isClicked(new Position(x, y))) {
                System.out.println("clicked");
                switch (b.onClick()) {
                    case LOAD_GAME -> {
                        this.uiState = UIState.IN_GAME;
                        this.scene.clear();
                        this.renderer.render(this.uiController.renderGameUI(1, "00:01", 1, 0));
                    }
                }
                break;
            }
        }
    }

    public void resetGame() {
        System.out.format("RESET%n");
    }

    public void escape() {
        System.out.format("ESCAPE%n");
    }
}
