package sokoban.model.objects;

import sokoban.model.position.Position;

public class Box extends MoveableObject {
    public Box(Position position) {
        super(position);
    }

    @Override
    public boolean isBlocking() {
        return true;
    }
}
