package sokoban.render.core;

import sokoban.model.position.Position;
import sokoban.render.api.IVisualNode;
import sokoban.render.enums.RenderType;

import java.util.List;

public class RenderNode {
    private Position position;
    private RenderType type;
    private List<IVisualNode> shape;

    public RenderNode(Position position, List<IVisualNode> shape, RenderType type) {
        this.position = position;
        this.shape = shape;
        this.type = type;
    }

    public void show() {
        for (IVisualNode v : this.shape) {
            v.show();
        }
    }

    public void hide() {
        for (IVisualNode v : this.shape) {
            v.hide();
        }
    }
}
