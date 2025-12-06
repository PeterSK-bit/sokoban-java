package sokoban.movement;

import sokoban.model.level.Level;
import sokoban.model.objects.*;
import sokoban.model.position.Position;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private final Level level;
    private MoveableObject[][] moveableObjects;
    private final List<Position> goalPositions;


    public GameState(Level level) {
        if (level == null) {
            throw new IllegalArgumentException("Level can not be null");
        }

        this.level = level;
        this.reset();
        this.goalPositions = new ArrayList<Position>();

        StaticObject[][] staticObjects = this.level.getStaticObjects();

        for (int y = 0; y < this.level.getHeight(); y++) {
            for (int x = 0; x < this.level.getWidth(); x++) {
                if (staticObjects[y][x] instanceof Goal) {
                    this.goalPositions.add(staticObjects[y][x].getPosition());
                }
            }
        }
    }

    public void reset() {
        this.moveableObjects = this.level.getInitialMoveableObjects();
    }

    public boolean isCompleted() {
        for (Position pos : this.goalPositions) {
            if (!(this.moveableObjects[pos.getY()][pos.getX()] instanceof Box)) {
                return false;
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
