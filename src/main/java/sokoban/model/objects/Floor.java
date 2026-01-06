package sokoban.model.objects;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;

/**
 * Represents a walkable floor tile.
 *
 * @author Peter Magdík
 */
public class Floor extends StaticObject {
    public Floor(Position position) {
        super(position);
    }

    /**
     * @return floor render type
     */
    @Override
    public RenderType getRenderType() {
        return RenderType.FLOOR;
    }
}
