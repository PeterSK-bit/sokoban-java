package sokoban.ui;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;

/**
 * Abstract base class for all UI elements.
 *
 * @author Peter Magdík
 */
public abstract class UIElement {
    private Position position;
    private boolean visible;
    private int width;
    private int height;

    public UIElement(Position position, boolean visible) {
        this.position = position;
        this.visible = visible;
    }

    /**
     * @return position of element
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * width setter
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * height setter
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
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
     * Checks if a given position (e.g., mouse click) is inside this element.
     *
     * @param mouseClick position to test
     * @return true if clicked
     */
    public boolean isClicked(Position mouseClick) {
        if (this.height == 0 || this.width == 0) {
            System.out.println("Cannot check click because of unset width and height");
            return false;
        }

        int x = mouseClick.getX();
        int y = mouseClick.getY();

        if (x >= this.position.getX() && y >= this.position.getY()) {
            return (x - this.width <= this.position.getX() && y - this.height <= this.position.getY());
        }

        return false;
    }

    /**
     * Called when the element is clicked.
     */
    public abstract void onClick();

    /**
     * Returns the render type of the element.
     *
     * @return RenderType
     */
    public abstract RenderType getRenderType();
}
