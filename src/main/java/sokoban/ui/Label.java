package sokoban.ui;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;

public class Label extends UIElement {
    private String text;

    public Label(Position position, boolean visibility, String text) {
        super(position, visibility);
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public void onClick() {

    }

    @Override
    public RenderType getRenderType() {
        return RenderType.UI_LABEL;
    }
}
