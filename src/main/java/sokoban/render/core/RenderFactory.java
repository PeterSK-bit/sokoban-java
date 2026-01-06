package sokoban.render.core;

import sokoban.model.position.Position;
import sokoban.render.api.IVisualNode;
import sokoban.render.api.IVisualNodeFactory;
import sokoban.render.enums.RenderType;
import sokoban.ui.UIElement;

import java.util.List;

/**
 * Factory for creating RenderNode instances from game objects or UI elements.
 */
public class RenderFactory {
    private final IVisualNodeFactory visualFactory;

    /**
     * @param visualFactory
     */
    public RenderFactory(IVisualNodeFactory visualFactory) {
        this.visualFactory = visualFactory;
    }

    /**
     * Creates a RenderNode for a game object.
     *
     * @param renderType type of the object
     * @param position position on the grid
     * @return RenderNode wrapping visual nodes
     */
    public RenderNode createForGameObject(RenderType renderType, Position position) {
        List<IVisualNode> visuals = this.visualFactory.createGameVisual(renderType, position);

        return new RenderNode(position, visuals, renderType);
    }

    /**
     * Creates a RenderNode for a UI element.
     *
     * @param element UI element to render
     * @return RenderNode wrapping visual nodes
     */
    public RenderNode createForUIElement(UIElement element) {
        if (element == null) {
            throw new IllegalArgumentException("UIElement can not be null");
        }

        List<IVisualNode> visuals = this.visualFactory.createUIElementVisual(element);

        return new RenderNode(element.getPosition(), visuals, element.getRenderType());
    }
}
