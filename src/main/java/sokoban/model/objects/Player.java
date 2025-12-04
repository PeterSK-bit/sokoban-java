package sokoban.model.objects;

import sokoban.model.position.Position;

public class Player extends MoveableObject {
    public Player(Position position) {
        super(position);
    }

    @Override
    public boolean isBlocking() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return false;
    }
}
