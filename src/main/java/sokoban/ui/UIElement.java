package sokoban.ui;

import sokoban.model.position.Position;

public abstract class UIElement {
    private Position position;
    private boolean visible;

    public UIElement(Position position, boolean visible) {
        this.position = position;
        this.visible = visible;
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
        return this.position.equals(mouseClick);
    }

    public abstract void onClick();
}
