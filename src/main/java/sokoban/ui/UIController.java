package sokoban.ui;

import sokoban.game.GameController;
import sokoban.model.position.Position;
import sokoban.persistence.save.SaveDescriptor;
import sokoban.render.core.RenderFactory;
import sokoban.render.core.RenderNode;
import sokoban.util.UIConstants;

import java.util.ArrayList;
import java.util.List;

public class UIController {
    private final RenderFactory renderFactory;
    private final GameController gameController;
    private int levelNumber = -1;
    private String time = "";
    private int moves = -1;
    private int pushes = -1;
    private List<Button> activeButtons = new ArrayList<>();

    public UIController(RenderFactory renderFactory, GameController gameController) {
        this.renderFactory = renderFactory;
        this.gameController = gameController;
    }

    public List<Button> getActiveButtons() {
        return this.activeButtons;
    }

    public List<RenderNode> renderMainMenu(List<SaveDescriptor> savesFound) {
        this.activeButtons.clear();

        int fontSize = UIConstants.DEFAULT_FONT_SIZE;
        int padding = UIConstants.DEFAULT_PADDING;
        double fontHeightHeuristic = UIConstants.COURIER_HEIGHT;
        int x = padding;
        int y = padding;
        ArrayList<RenderNode> scene = new ArrayList<>();

        Background bg = new Background(new Position(0, 0), true, "#2C2C2C");
        scene.add(this.renderFactory.createForUIElement(bg));

        for (SaveDescriptor save : savesFound) {
            Button levelButton = new Button(
                    new Position(x, y), true, save.displayName(), () -> this.gameController.loadSave(save)
            );
            this.activeButtons.add(levelButton);
            scene.add(this.renderFactory.createForUIElement(levelButton));
            y += (2 * padding + fontHeightHeuristic * fontSize + 4);
        }

        return scene;
    }

    public List<RenderNode> renderGameUI(int levelNumber, String time, int moves, int pushes) {
        this.activeButtons.clear();

        ArrayList<RenderNode> scene = new ArrayList<>();
        int padding = 10;
        int fontSize = 10;
        int x = 0;

        Background bg = new Background(new Position(0, 0), true, "#2C2C2C");
        scene.add(this.renderFactory.createForUIElement(bg));

        if (this.levelNumber != levelNumber) {
            this.levelNumber = levelNumber;
            Label levelLabel = new Label(new Position(x, 0), true, "Level " + levelNumber);
            scene.add(this.renderFactory.createForUIElement(levelLabel));
        }

        x += (String.valueOf(this.levelNumber).length() + 6) * fontSize * 0.55 + 2 * padding;

        if (!time.equals(this.time)) {
            this.time = time;
            Label timeLabel = new Label(new Position(x, 0), true, "Time: " + time);
            scene.add(this.renderFactory.createForUIElement(timeLabel));
        }

        x += (this.time.length() + 6) * fontSize * 0.55 + 2 * padding;

        if (this.moves != moves) {
            this.moves = moves;
            Label movesLabel = new Label(new Position(x, 0), true, "Moves: " + moves);
            scene.add(this.renderFactory.createForUIElement(movesLabel));
        }

        x += (String.valueOf(this.moves).length() + 7) * fontSize * 0.55 + 2 * padding;

        if (this.pushes != pushes) {
            this.pushes = pushes;
            Label pushesLabel = new Label(new Position(x, 0), true, "Pushes: " + pushes);
            scene.add(this.renderFactory.createForUIElement(pushesLabel));
        }

        return scene;
    }
}
