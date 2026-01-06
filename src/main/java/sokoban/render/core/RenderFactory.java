package sokoban.render.core;

import sokoban.model.position.Position;
import sokoban.render.api.IVisualNode;
import sokoban.render.api.IVisualNodeFactory;
import sokoban.render.enums.RenderType;
import sokoban.ui.UIElement;

import java.util.List;

public class RenderFactory {
    private final IVisualNodeFactory visualFactory;

    public RenderFactory(IVisualNodeFactory visualFactory) {
        this.visualFactory = visualFactory;
    }

    public RenderNode createForGameObject(RenderType renderType, Position position) {
        List<IVisualNode> visuals = this.visualFactory.createGameVisual(renderType, position);

        return new RenderNode(position, visuals, renderType);
    }

    public RenderNode createForUIElement(UIElement element) {
        if (element == null) {
            throw new IllegalArgumentException("UIElement can not be null");
        }

        List<IVisualNode> visuals = this.visualFactory.createUIElementVisual(element);

        return new RenderNode(element.getPosition(), visuals, element.getRenderType());
    }
}
