package sokoban.model.objects;

import sokoban.model.position.Position;

public abstract class StaticObject extends GameObject {
    public StaticObject(Position position) {
        super(position);
    }

    public final boolean isPushable() {
        return false;
    }

    public final boolean isMoveable() {
        return false;
    }
}
