package sokoban.model.objects;

import sokoban.model.position.Position;

public class Wall extends StaticObject {
    public Wall(Position position) {
        super(position);
    }

    @Override
    public boolean isBlocking() {
        return true;
    }
}
