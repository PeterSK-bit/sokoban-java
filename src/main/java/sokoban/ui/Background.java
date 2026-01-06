package sokoban.ui;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;
import sokoban.util.UIConstants;

public class Background extends UIElement {
    private final RenderType renderType = RenderType.BACKGROUND;
    private final String color;
    private final int width;
    private final int height;

    public Background(Position position, boolean visible, String color) {
        super(position, visible);
        this.color = color;
        this.width = UIConstants.WINDOW_WIDTH;
        this.height = UIConstants.WINDOW_HEIGHT;
    }

    public Background(Position position, boolean visible, String color, int width, int height) {
        super(position, visible);
        this.color = color;
        this.width = width;
        this.height = height;
    }

    public String getColor() {
        return this.color;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    public void onClick() {

    }

    @Override
    public RenderType getRenderType() {
        return this.renderType;
    }
}
