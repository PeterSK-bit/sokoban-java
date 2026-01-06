package sokoban.render.api;

/**
 * Abstraction for a visual node in the game or UI.
 */
public interface IVisualNode {
    /**
     * Makes the visual node visible.
     */
    void show();
    /**
     * Hides the visual node.
     */
    void hide();
}
