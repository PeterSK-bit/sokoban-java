package sokoban.model.objects;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;

/**
 * Represents the player character.
 */
public class Player extends MoveableObject {
    public Player(Position position) {
        super(position);
    }

    /**
     * @return copy of the player
     */
    @Override
    public MoveableObject copy() {
        return new Player(this.getPosition());
    }

    /**
     * @return player render type
     */
    @Override
    public RenderType getRenderType() {
        return RenderType.GOAL;
    }
}
