package sokoban.model.objects;

import sokoban.model.position.Position;

public abstract class MoveableObject extends GameObject {
    public MoveableObject(Position position) {
        super(position);
    }

    public abstract boolean isBlocking();
}
