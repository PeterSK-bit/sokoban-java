package sokoban.game;

import sokoban.model.level.Level;
import sokoban.model.objects.Box;
import sokoban.model.objects.MoveableObject;
import sokoban.model.objects.Player;
import sokoban.model.position.Position;
import sokoban.movement.GameState;
import sokoban.persistence.save.GameSave;
import sokoban.persistence.save.GameStateSave;

public class GameStateFactory {
    // for reset functionality
    public static GameState fromLevel(Level level) {
        MoveableObject[][] grid = new MoveableObject[level.getHeight()][level.getWidth()];

        for (Position box : level.getBoxStarts()) {
            grid[box.getY()][box.getX()] = new Box(box);
        }

        return new GameState(level.getWidth(), level.getHeight(),
                new Player(level.getPlayerStart()), grid, 0, 0, 0);
    }

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
