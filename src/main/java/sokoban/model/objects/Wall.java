package sokoban.model.objects;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;

public class Wall extends StaticObject {
    public Wall(Position position) {
        super(position);
    }

    @Override
    public boolean isBlocking() {
        return true;
    }

    @Override
    public RenderType getRenderType() {
        return RenderType.WALL;
    }
}
