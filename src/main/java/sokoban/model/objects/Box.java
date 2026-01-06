package sokoban.model.objects;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;

/**
 * Represents a movable box that can be pushed.
 *
 * @author Peter Magdík
 */
public class Box extends MoveableObject {
    public Box(Position position) {
        super(position);
    }

    /**
     * @return copy of the box
     */
    @Override
    public MoveableObject copy() {
        return new Box(this.getPosition());
    }

    /**
     * @return box render type
     */
    @Override
    public RenderType getRenderType() {
        return RenderType.BOX;
    }
}
