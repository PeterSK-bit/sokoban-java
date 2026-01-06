package sokoban.render.api;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;
import sokoban.ui.UIElement;

import java.util.List;

/**
 * Factory interface for creating visual nodes for game objects and UI elements.
 */
public interface IVisualNodeFactory {
    /**
     * Creates visual representation(s) for a game object.
     *
     * @param type object render type
     * @param position object position
     * @return list of visual nodes representing the object
     */
    List<IVisualNode> createGameVisual(RenderType type, Position position);
    /**
     * Creates visual representation(s) for a UI element.
     *
     * @param element UI element
     * @return list of visual nodes representing the UI element
     */
    List<IVisualNode> createUIElementVisual(UIElement element);
}

