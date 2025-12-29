package sokoban.render.api;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;
import sokoban.ui.UIElement;

import java.util.List;

public interface IVisualNodeFactory {
    List<IVisualNode> createGameVisual(RenderType type, Position position);
    List<IVisualNode> createUIElementVisual(UIElement element);
}

