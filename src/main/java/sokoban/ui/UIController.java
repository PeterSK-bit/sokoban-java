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
    private final List<Button> activeButtons = new ArrayList<>();

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
        int y = padding;
        ArrayList<RenderNode> scene = new ArrayList<>();

        for (SaveDescriptor save : savesFound) {
            Button levelButton = new Button(
                    new Position(padding, y), true, save.displayName(), () -> this.gameController.loadSave(save)
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
        int padding = UIConstants.DEFAULT_PADDING;
        int fontSize = UIConstants.DEFAULT_FONT_SIZE;
        int x = 0;

        Label levelLabel = new Label(new Position(x, 0), true, "Level " + levelNumber);
        scene.add(this.renderFactory.createForUIElement(levelLabel));

        x += (String.valueOf(levelNumber).length() + 6) * fontSize * 0.55 + 2 * padding;

        Label timeLabel = new Label(new Position(x, 0), true, "Time: " + time);
        scene.add(this.renderFactory.createForUIElement(timeLabel));

        x += (time.length() + 6) * fontSize * 0.55 + 2 * padding;

        Label movesLabel = new Label(new Position(x, 0), true, "Moves: " + moves);
        scene.add(this.renderFactory.createForUIElement(movesLabel));

        x += (String.valueOf(moves).length() + 7) * fontSize * 0.55 + 2 * padding;

        Label pushesLabel = new Label(new Position(x, 0), true, "Pushes: " + pushes);
        scene.add(this.renderFactory.createForUIElement(pushesLabel));

        return scene;
    }

    public List<RenderNode> renderGameStats(int levelNumber, String time, int moves, int pushes) {
        ArrayList<RenderNode> scene = new ArrayList<>();

        Background bg = new Background(
                new Position(150, 150), true, "white", 300, 300
        );
        scene.add(this.renderFactory.createForUIElement(bg));

        Label levelStatsLabel = new Label(new Position(255, 200), true, "Level Stats");
        scene.add(this.renderFactory.createForUIElement(levelStatsLabel));

        Label levelNumberLabel = new Label(new Position(270, 225), true, "Level " + levelNumber);
        scene.add(this.renderFactory.createForUIElement(levelNumberLabel));

        Label timeLabel = new Label(new Position(270, 250), true, "Time: " + time);
        scene.add(this.renderFactory.createForUIElement(timeLabel));


        Label movesLabel = new Label(new Position(270, 275), true, "Moves: " + moves);
        scene.add(this.renderFactory.createForUIElement(movesLabel));


        Label pushesLabel = new Label(new Position(270, 300), true, "Pushes: " + pushes);
        scene.add(this.renderFactory.createForUIElement(pushesLabel));

        return scene;
    }
}
