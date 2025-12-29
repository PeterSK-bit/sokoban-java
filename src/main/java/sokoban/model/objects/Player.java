package sokoban.model.objects;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;

public class Player extends MoveableObject {
    public Player(Position position) {
        super(position);
    }

    @Override
    public MoveableObject copy() {
        return new Player(this.getPosition());
    }

    @Override
    public boolean isBlocking() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public RenderType getRenderType() {
        return RenderType.GOAL;
    }
}
