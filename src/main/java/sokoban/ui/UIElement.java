package sokoban.ui;

import sokoban.model.enums.GameAction;
import sokoban.model.position.Position;

public abstract class UIElement {
    private Position position;
    private boolean visible;
    private int width;
    private int height;

    public UIElement(Position position, boolean visible, int width, int height) {
        this.position = position;
        this.visible = visible;
        this.width = width;
        this.height = height;
    }

    public Position getPosition() {
        return this.position;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void toggleVisibility() {
        this.visible = !this.visible;
    }

    public void show() {
        this.visible = true;
    }

    public void hide() {
        this.visible = false;
    }

    public boolean isClicked(Position mouseClick) {
        int x = mouseClick.getX();
        int y = mouseClick.getY();

        if (x >= this.position.getX() && y >= this.position.getY()) {
            return (x - this.width <= this.position.getX() && y - this.height <= this.position.getY());
        }

        return false;
    }

    public void move(Position newPosition) {
        if (newPosition == null) {
            throw new IllegalArgumentException("NewPosition can not be null");
        }

        this.position = newPosition;
    }

    public abstract GameAction onClick();
}
