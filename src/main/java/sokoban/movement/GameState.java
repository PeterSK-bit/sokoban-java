package sokoban.movement;

import sokoban.model.level.Level;
import sokoban.model.objects.Box;
import sokoban.model.objects.GameObject;
import sokoban.model.objects.Goal;
import sokoban.model.objects.MoveableObject;
import sokoban.model.position.Position;

public class GameState {
    private final Level level;
    private MoveableObject[][] moveableObjects;

    public GameState(Level level) {
        if (level == null) {
            throw new IllegalArgumentException("Level can not be null");
        }

        this.level = level;
        this.reset();
    }

    public void reset() {
        this.moveableObjects = this.level.getInitialMoveableObjects();
    }

    public boolean isCompleted() {
        for (int y = 0; y < this.level.getHeight(); y++) {
            for (int x = 0; x < this.level.getWidth(); x++){
                if (this.moveableObjects[y][x] instanceof Box && !(this.level.getStaticObjectAt(new Position(x, y)) instanceof Goal)) {
                    return false;
                }
            }
        }

        return true;
    }

    public GameObject getObjectAt(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Position can not be null");
        }

        int x = position.getX();
        int y = position.getY();

        if (x < 0 || x >= this.level.getWidth() || y < 0 || y >= this.level.getHeight()) {
            throw new IllegalArgumentException("Position out of array bounds.");
        }

        if (this.moveableObjects[y][x] != null) {
            return this.moveableObjects[y][x];
        }

        return this.level.getStaticObjectAt(position);
    }
}
