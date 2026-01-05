package sokoban.movement;

import sokoban.model.enums.Direction;

import sokoban.model.objects.MoveableObject;
import sokoban.model.objects.Player;

import sokoban.model.position.Position;

public class GameState {

    private final int width;
    private final int height;

    private Position playerPosition;
    private MoveableObject[][] moveableObjects;
    private int moves;
    private int pushes;
    private int timeElapsed;

    public GameState(
            int width, int height, Position playerPosition, MoveableObject[][] moveableObjects, int moves, int pushes, int timeElapsed
    ) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Metrics of level are too small");
        }

        if (playerPosition == null) {
            throw new IllegalArgumentException("playerPosition can not be null");
        }

        int x = playerPosition.getX();
        int y = playerPosition.getY();
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("playerPosition out of level bound");
        }

        if (moveableObjects == null) {
            throw new IllegalArgumentException("moveableObjects can not be null");
        }

        if (moveableObjects.length != height) {
            throw new IllegalArgumentException("Miss match of height and height of moveableObjects array");
        }

        for (int i = 0; i < height; i++) {
            if (moveableObjects[i].length != width) {
                throw new IllegalArgumentException("Miss match of width and width of moveableObjects array");
            }
        }

        if (moves < 0) {
            throw new IllegalArgumentException("moves can not be negative");
        }

        if (pushes < 0) {
            throw new IllegalArgumentException("pushes can not be negative");
        }

        if (timeElapsed < 0) {
            throw new IllegalArgumentException("time can not be negative");
        }

        this.width = width;
        this.height = height;
        this.playerPosition = playerPosition;
        this.moveableObjects = moveableObjects;
        this.moves = moves;
        this.pushes = pushes;
        this.timeElapsed = timeElapsed;
    }

    public MoveableObject getMoveableObjectAt(Position position) {
        MoveableObject object = this.moveableObjects[position.getY()][position.getX()];
        return (object == null ? null : object.copy());
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

    public int getMoves() {
        return this.moves;
    }

    public void addMove() {
        this.moves += 1;
    }

    public int getPushes() {
        return this.pushes;
    }

    public int getTimeElapsed() {
        return this.timeElapsed;
    }

    public void setTimeElapsed(int timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
}
