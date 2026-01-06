package sokoban.render.core;

import sokoban.model.position.Position;
import sokoban.render.api.IVisualNode;
import sokoban.render.enums.RenderType;

import java.util.List;

/**
 * Represents a visual node in the scene with position, type, and underlying visuals.
 */
public class RenderNode {
    private final Position position;
    private final RenderType type;
    private final List<IVisualNode> shape;

    /**
     * @param position of node
     * @param shape visual nodes
     * @param type type of render object
     */
    public RenderNode(Position position, List<IVisualNode> shape, RenderType type) {
        this.position = position;
        this.shape = shape;
        this.type = type;
    }

    /**
     * Makes all underlying visuals visible.
     */
    public void show() {
        for (IVisualNode v : this.shape) {
            v.show();
        }
    }

    /**
     * Hides all underlying visuals.
     */
    public void hide() {
        for (IVisualNode v : this.shape) {
            v.hide();
        }
    }
}
