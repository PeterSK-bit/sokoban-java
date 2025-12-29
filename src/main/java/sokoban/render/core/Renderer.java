package sokoban.render.core;

import java.util.List;

public class Renderer {
    public void render(List<RenderNode> scene) {
        for (RenderNode node : scene) {
            node.show();
        }
    }
}
