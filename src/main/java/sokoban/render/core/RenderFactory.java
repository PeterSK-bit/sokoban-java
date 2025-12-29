package sokoban.render.core;

import sokoban.model.objects.GameObject;
import sokoban.render.api.IVisualNode;
import sokoban.render.api.IVisualNodeFactory;
import sokoban.ui.UIElement;

import java.util.List;

public class RenderFactory {
    private final IVisualNodeFactory visualFactory;

    public RenderFactory(IVisualNodeFactory visualFactory) {
        this.visualFactory = visualFactory;
    }

    public RenderNode createForGameObject(GameObject object) {
        if (object == null) {
            throw new IllegalArgumentException("GameObject can not be null");
        }

        List<IVisualNode> visuals =
                this.visualFactory.createGameVisual(
                        object.getRenderType(),
                        object.getPosition()
                );

        return new RenderNode(object.getPosition(), visuals, object.getRenderType());
    }

    public RenderNode createForUIElement(UIElement element) {
        if (element == null) {
            throw new IllegalArgumentException("UIElement can not be null");
        }

        List<IVisualNode> visuals = this.visualFactory.createUIElementVisual(element);

        return new RenderNode(element.getPosition(), visuals, element.getRenderType());
    }
}
