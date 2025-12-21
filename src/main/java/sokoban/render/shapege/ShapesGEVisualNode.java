package sokoban.render.shapege;

import sokoban.model.position.Position;
import sokoban.render.api.IVisualNode;

public class ShapesGEVisualNode implements IVisualNode {

    private final Object shape;
    private final VisualWrapper visibility;

    public ShapesGEVisualNode(Object shape) {
        this.shape = shape;
        this.visibility = new VisualWrapper(shape);
    }

    @Override
    public void show() {
        this.visibility.makeVisible();
    }

    @Override
    public void hide() {
        this.visibility.makeInvisible();
    }

    @Override
    public void setPosition(Position p) {
        // no need for it yet
    }

    @Override
    public void remove() {
        // no need for it yet
    }
}

