package sokoban.movement;

import sokoban.model.enums.Direction;
import sokoban.model.level.Level;
import sokoban.model.objects.Box;
import sokoban.model.objects.MoveableObject;
import sokoban.model.objects.Wall;
import sokoban.model.position.Position;

/**
 * Handles movement rules and win condition evaluation.
 */
public class MovementManager {
    private final GameState gameState;
    private final Level level;

    /**
     * Creates a movement manager bound to a level and game state.
     * @param level
     * @param gameState
     */
    public MovementManager(Level level, GameState gameState) {
        if (level == null) {
            throw new IllegalArgumentException("level can not be null");
        }

        if (gameState == null) {
            throw new IllegalArgumentException("gameState can not be null");
        }

        this.level = level;
        this.gameState = gameState;
    }

    /**
     * Attempts to move the player in the given direction.
     *
     * @param direction movement direction
     * @return result of the move attempt
     */
    public MoveResult tryMove(Direction direction) {
        Position playerPosition = this.gameState.getPlayerPosition();
        int newX = playerPosition.getX() + direction.dx();
        int newY = playerPosition.getY() + direction.dy();

        if (newX < 0 || newX >= this.gameState.getWidth() || newY < 0 || newY >= this.gameState.getHeight()) {
            return MoveResult.BLOCKED;
        }

        Position target = playerPosition.translate(direction);

        if (this.level.getStaticObjectAt(target) instanceof Wall) {
            return MoveResult.BLOCKED;
        }

        MoveableObject obj = this.gameState.getMoveableObjectAt(target);

        if (obj == null) {
            this.movePlayer(direction);
            return MoveResult.PASS;
        }

        if (!(obj instanceof Box)) {
            return MoveResult.BLOCKED;
        }

        Position boxTarget = target.translate(direction);

        if (this.level.getStaticObjectAt(boxTarget) instanceof Wall) {
            return MoveResult.BLOCKED;
        }

        if (this.gameState.getMoveableObjectAt(boxTarget) != null) {
            return MoveResult.BLOCKED;
        }

        this.gameState.moveObject(target, direction);
        this.movePlayer(direction);

        return MoveResult.PUSHED;
    }

    private void movePlayer(Direction direction) {
        Position oldPosition = this.gameState.getPlayerPosition();

        this.gameState.setPlayerPosition(oldPosition.translate(direction));
    }

    /**
     * Checks whether all goals are satisfied.
     *
     * @return true if level is completed
     */
    public boolean checkWin() {
        for (Position goal : this.level.getGoalPositions()) {
            MoveableObject object = this.gameState.getMoveableObjectAt(goal);
            if (!(object instanceof Box)) {
                return false;
            }
        }
        return true;
    }
}
