package sokoban.game;

import sokoban.model.level.Level;
import sokoban.model.objects.Box;
import sokoban.model.objects.MoveableObject;
import sokoban.model.objects.Player;
import sokoban.model.position.Position;
import sokoban.movement.GameState;

public class GameStateFactory {
    public static GameState fromLevel(Level level) {
        MoveableObject[][] grid = new MoveableObject[level.getHeight()][level.getWidth()];

        grid[level.getPlayerStart().getY()][level.getPlayerStart().getX()] =
                new Player(level.getPlayerStart());

        for (Position box : level.getBoxStarts()) {
            grid[box.getY()][box.getX()] = new Box(box);
        }

        return new GameState(level.getWidth(), level.getHeight(), level.getPlayerStart(), grid, 0, 0, 0);
    }

    // for reset functionality
    public static GameState fromSave(Level level, GameState savedState) {
        return new GameState(
                level.getWidth(),
                level.getHeight(),
                level.getPlayerStart(),
                savedState.getMoveableObjects(),
                savedState.getMoves(),
                savedState.getPushes(),
                savedState.getTimeElapsed()
        );
    }
}
