package sokoban.model.objects;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;

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

    @Override
    public RenderType getRenderType() {
        return RenderType.BOX;
    }
}
