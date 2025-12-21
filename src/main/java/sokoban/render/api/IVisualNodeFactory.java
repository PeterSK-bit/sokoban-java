package sokoban.render.api;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;

import java.util.List;

public interface IVisualNodeFactory {
    List<IVisualNode> createVisual(RenderType type, Position position);
}

