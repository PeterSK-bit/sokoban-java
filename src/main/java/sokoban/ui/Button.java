package sokoban.ui;

import sokoban.model.enums.GameAction;
import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;

public class Button extends UIElement {
    private String label;
    private GameAction action;

    public Button(Position position, boolean visible, String label, GameAction action) {
        super(position, visible);
        this.label = label;
        this.action = action;
    }

    public String getLabel() {
        return this.label;
    }

    public GameAction getAction() {
        return this.action;
    }

    public void setAction(GameAction action) {
        this.action = action;
    }

    public void setLabel(String label) {
        if (label == null) {
            throw new IllegalArgumentException("Label can not be null");
        }

        this.label = label;
    }

    @Override
    public GameAction onClick() {
        return this.action;
    }

    @Override
    public RenderType getRenderType() {
        return RenderType.UI_BUTTON;
    }
}
