package sokoban.movement;

import sokoban.model.enums.Direction;
import sokoban.model.objects.Box;
import sokoban.model.objects.MoveableObject;
import sokoban.model.objects.Wall;
import sokoban.model.position.Position;

public class MovementManager {
    private final GameState gameState;

    public MovementManager(GameState gameState) {
        this.gameState = gameState;
    }

    public boolean tryMove(Direction direction) {
        Position playerPosition = this.gameState.getPlayerPosition();
        int newX = playerPosition.getX() + direction.dx();
        int newY = playerPosition.getY() + direction.dy();

        if (newX < 0 || newX >= this.gameState.getWidth() || newY < 0 || newY >= this.gameState.getHeight()) {
            return false;
        }

        Position target = playerPosition.translate(direction);

        if (this.gameState.getLevel().getStaticObjectAt(target) instanceof Wall) {
            return false;
        }

        MoveableObject obj = this.gameState.getMoveableObjectAt(target);

        if (obj == null) {
            return this.movePlayer(direction);
        }

        if (!(obj instanceof Box)) {
            return false;
        }

        Position boxTarget = target.translate(direction);

        if (this.gameState.getLevel().getStaticObjectAt(boxTarget) instanceof Wall) {
            return false;
        }

        if (this.gameState.getMoveableObjectAt(boxTarget) != null) {
            return false;
        }

        this.gameState.moveObject(target, direction);

        return this.movePlayer(direction);
    }

    private boolean movePlayer(Direction direction) {
        Position oldPosition = this.gameState.getPlayerPosition();

        this.gameState.moveObject(oldPosition, direction);
        this.gameState.setPlayerPosition(oldPosition.translate(direction));

        return true;
    }
}
