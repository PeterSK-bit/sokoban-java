package sokoban.model.objects;

import sokoban.model.position.Position;

public class Box extends MoveableObject {
    public Box(Position position) {
        super(position);
    }

    @Override
    public MoveableObject copy() {
        return new Box(this.getPosition());
    }

    @Override
    public boolean isBlocking() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return true;
    }
}
