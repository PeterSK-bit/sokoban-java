package sokoban.model.objects;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;

/**
 * Represents an immovable blocking wall.
 */
public class Wall extends StaticObject {
    public Wall(Position position) {
        super(position);
    }

    /**
     * @return wall render type
     */
    @Override
    public RenderType getRenderType() {
        return RenderType.WALL;
    }
}
