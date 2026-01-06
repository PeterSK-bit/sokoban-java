package sokoban.model.objects;

import sokoban.model.position.Position;

/**
 * Base class for all movable game objects.
 *
 * @author Peter Magdík
 */
public abstract class MoveableObject extends GameObject {
    /**
     * Creates a movable object at a given position.
     *
     * @param position initial position
     */
    public MoveableObject(Position position) {
        super(position);
    }

    /**
     * Creates a copy of this movable object.
     *
     * @return duplicated movable object
     */
    public abstract MoveableObject copy();
}
