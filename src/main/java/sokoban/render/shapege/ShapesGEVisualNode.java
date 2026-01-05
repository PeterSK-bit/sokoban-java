package sokoban.render.shapege;

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
}

