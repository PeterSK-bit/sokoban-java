package sokoban.ui;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;

public class Button extends UIElement {
    private String label;
    private final Runnable onClick;

    public Button(Position position, boolean visible, String label, Runnable onClick) {
        super(position, visible);
        this.label = label;
        this.onClick = onClick;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public void onClick() {
        this.onClick.run();
    }

    @Override
    public RenderType getRenderType() {
        return RenderType.UI_BUTTON;
    }
}
