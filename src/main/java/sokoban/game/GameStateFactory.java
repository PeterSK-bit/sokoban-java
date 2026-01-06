package sokoban.game;

import sokoban.model.level.Level;
import sokoban.model.objects.Box;
import sokoban.model.objects.MoveableObject;
import sokoban.model.objects.Player;
import sokoban.model.position.Position;
import sokoban.movement.GameState;
import sokoban.persistence.save.GameSave;
import sokoban.persistence.save.GameStateSave;

/**
 * Factory responsible for creating {@link GameState} instances
 * from different sources (level definition or saved game).
 */
public class GameStateFactory {

    /**
     * Creates a fresh game state from a level definition.
     * Used mainly for starting a new level or resetting progress.
     *
     * @param level source level definition
     * @return initialized game state
     */
    public static GameState fromLevel(Level level) {
        MoveableObject[][] grid = new MoveableObject[level.getHeight()][level.getWidth()];

        for (Position box : level.getBoxStarts()) {
            grid[box.getY()][box.getX()] = new Box(box);
        }

        return new GameState(level.getWidth(), level.getHeight(),
                new Player(level.getPlayerStart()), grid, 0, 0, 0);
    }

    /**
     * Reconstructs a game state from a persisted save.
     *
     * @param gameSave saved game data
     * @return restored game state
     */
    public static GameState fromSave(GameSave gameSave) {
        Level level = gameSave.level();
        GameStateSave gameStateSave = gameSave.state();
        MoveableObject[][] moveableObjects = new MoveableObject[level.getHeight()][level.getWidth()];
        gameStateSave.boxes().forEach(position ->
                moveableObjects[position.getY()][position.getX()] = new Box(position)
        );

        return new GameState(
                level.getWidth(),
                level.getHeight(),
                new Player(level.getPlayerStart()),
                moveableObjects,
                gameStateSave.moves(),
                gameStateSave.pushes(),
                gameStateSave.timeElapsed()
        );
    }
}
