package sokoban.render.core;

import java.util.List;

/**
 * Handles rendering and removal of RenderNode scenes.
 */
public class Renderer {
    /**
     * Renders all nodes in the scene.
     *
     * @param scene list of render nodes
     */
    public void render(List<RenderNode> scene) {
        for (RenderNode node : scene) {
            node.show();
        }
    }

    /**
     * Hides all nodes in the scene.
     *
     * @param scene list of render nodes
     */
    public void remove(List<RenderNode> scene) {
        for (RenderNode node : scene) {
            node.hide();
        }
    }
}
