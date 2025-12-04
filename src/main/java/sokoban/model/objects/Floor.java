package sokoban.model.objects;

import sokoban.model.position.Position;

public class Floor extends StaticObject {
    public Floor(Position position) {
        super(position);
    }

    @Override
    public boolean isBlocking() {
        return false;
    }
}
