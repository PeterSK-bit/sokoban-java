package sokoban.ui;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;
import sokoban.util.UIConstants;

/**
 * Represents a rectangular background UI element.
 */
public class Background extends UIElement {
    private final RenderType renderType = RenderType.BACKGROUND;
    private final String color;
    private final int width;
    private final int height;

    /**
     * Creates a background with default window size.
     *
     * @param position position of the background
     * @param visible whether the background is visible
     * @param color background color
     */
    public Background(Position position, boolean visible, String color) {
        super(position, visible);
        this.color = color;
        this.width = UIConstants.WINDOW_WIDTH;
        this.height = UIConstants.WINDOW_HEIGHT;
    }

    /**
     * Creates a background with custom size.
     *
     * @param position position of the background
     * @param visible whether visible
     * @param color background color
     * @param width width in pixels
     * @param height height in pixels
     */
    public Background(Position position, boolean visible, String color, int width, int height) {
        super(position, visible);
        this.color = color;
        this.width = width;
        this.height = height;
    }

    /**
     * @return color
     */
    public String getColor() {
        return this.color;
    }

    /**
     * @return width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * @return height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Does nothing
     */
    @Override
    public void onClick() {

    }

    /**
     * @return render type
     */
    @Override
    public RenderType getRenderType() {
        return this.renderType;
    }
}
