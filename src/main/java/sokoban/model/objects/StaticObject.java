package sokoban.model.objects;

import sokoban.model.position.Position;

/**
 * Base class for all static (non-movable) game objects.
 */
public abstract class StaticObject extends GameObject {
    /**
     * Creates a static object at a given position.
     *
     * @param position object position
     */
    public StaticObject(Position position) {
        super(position);
    }

    @Override
    public void setPosition(Position position) {
        throw new UnsupportedOperationException("Static object can not be moved");
    }
}
