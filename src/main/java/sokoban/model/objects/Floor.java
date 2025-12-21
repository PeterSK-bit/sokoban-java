package sokoban.model.objects;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;

public class Floor extends StaticObject {
    public Floor(Position position) {
        super(position);
    }

    @Override
    public boolean isBlocking() {
        return false;
    }

    @Override
    public RenderType getRenderType() {
        return RenderType.FLOOR;
    }
}
