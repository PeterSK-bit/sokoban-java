package sokoban.model.objects;

import sokoban.model.position.Position;

public class Goal extends StaticObject {
    public Goal(Position position) {
        super(position);
    }

    @Override
    public boolean isBlocking() {
        return false;
    }
}
