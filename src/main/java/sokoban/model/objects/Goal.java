package sokoban.model.objects;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;

/**
 * Represents a goal tile.
 */
public class Goal extends StaticObject {
    public Goal(Position position) {
        super(position);
    }

    /**
     * @return goal render type
     */
    @Override
    public RenderType getRenderType() {
        return RenderType.GOAL;
    }
}
