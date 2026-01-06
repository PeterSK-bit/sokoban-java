package sokoban.render.shapege;

import sokoban.render.api.IVisualNode;

/**
 * Wrapper for a ShapesGE object implementing IVisualNode.
 * Provides show/hide functionality through VisualWrapper.
 *
 * @author Peter Magdík
 */
public class ShapesGEVisualNode implements IVisualNode {

    private final Object shape;
    private final VisualWrapper visibility;

    public ShapesGEVisualNode(Object shape) {
        this.shape = shape;
        this.visibility = new VisualWrapper(shape);
    }

    /**
     * Makes the node visible.
     */
    @Override
    public void show() {
        this.visibility.makeVisible();
    }

    /**
     * Hides the node.
     */
    @Override
    public void hide() {
        this.visibility.makeInvisible();
    }
}

