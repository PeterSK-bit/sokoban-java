package sokoban.movement;

import sokoban.model.enums.Direction;
import sokoban.model.level.Level;

// because I can not use "import sokoban.model.objects.*;" :)
import sokoban.model.objects.GameObject;
import sokoban.model.objects.MoveableObject;
import sokoban.model.objects.StaticObject;
import sokoban.model.objects.Goal;
import sokoban.model.objects.Player;
import sokoban.model.objects.Box;

import sokoban.model.position.Position;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private final Level level;
    private MoveableObject[][] moveableObjects;
    private final List<Position> goalPositions;
    private Position playerPosition;
    private final int width;
    private final int height;


    public GameState(Level level) {
        if (level == null) {
            throw new IllegalArgumentException("Level can not be null");
        }

        this.level = level;
        this.reset();
        this.goalPositions = new ArrayList<Position>();
        this.playerPosition = this.findPlayerPosition();
        this.width = this.getLevel().getWidth();
        this.height = this.getLevel().getHeight();

        StaticObject[][] staticObjects = this.level.getStaticObjects();

        for (int y = 0; y < this.level.getHeight(); y++) {
            for (int x = 0; x < this.level.getWidth(); x++) {
                if (staticObjects[y][x] instanceof Goal) {
                    this.goalPositions.add(staticObjects[y][x].getPosition());
                }
            }
        }
    }

    private Position findPlayerPosition() {
        Position found = null;

        for (MoveableObject[] line : this.getMoveableObjects()) {
            for (MoveableObject object : line) {
                if (object instanceof Player) {
                    if (found != null) {
                        throw new IllegalStateException("Multiple players found in moveableObjects grid");
                    }
                    found = object.getPosition();
                }
            }
        }

        if (found == null) {
            throw new IllegalStateException("Player not found in moveableObjects grid");
        }

        return found;
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

    public MoveableObject getMoveableObjectAt(Position position) {
        return this.moveableObjects[position.getY()][position.getX()].copy();
    }

    public MoveableObject[][] getMoveableObjects() {
        MoveableObject[][] copy = new MoveableObject[this.moveableObjects.length][];

        for (int y = 0; y < this.moveableObjects.length; y++) {
            copy[y] = new MoveableObject[this.moveableObjects[y].length];
            for (int x = 0; x < this.moveableObjects[y].length; x++) {
                MoveableObject obj = this.moveableObjects[y][x];
                copy[y][x] = (obj == null ? null : obj.copy());
            }
        }

        return copy;
    }

    public Level getLevel() {
        return this.level;
    }

    public void moveObject(Position position, Direction direction) {
        Position newPosition = position.translate(direction);

        if (position.getX() < 0 || position.getX() >= this.width || position.getY() < 0 || position.getY() >= this.height) {
            throw new IllegalArgumentException("Position is out of bounds");
        }

        if (newPosition.getX() < 0 || newPosition.getX() >= this.width || newPosition.getY() < 0 || newPosition.getY() >= this.height) {
            throw new IllegalArgumentException("New position is out of bounds");
        }

        MoveableObject obj = this.moveableObjects[position.getY()][position.getX()];

        if (obj == null) {
            throw new IllegalStateException("At given position there is nothing to move");
        }

        if (this.moveableObjects[newPosition.getY()][newPosition.getX()] != null) {
            throw new IllegalStateException("New position is occupied");
        }

        this.moveableObjects[position.getY()][position.getX()] = null;
        obj.setPosition(newPosition);
        if (obj instanceof Player) {
            this.setPlayerPosition(newPosition);
        }
        this.moveableObjects[newPosition.getY()][newPosition.getX()] = obj;
    }

    public Position getPlayerPosition() {
        return this.playerPosition;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setPlayerPosition(Position newPosition) {
        if (newPosition == null) {
            throw new IllegalArgumentException("Position can not be null");
        }

        this.playerPosition = newPosition;
    }
}
