package sokoban.model.level;

import sokoban.model.objects.StaticObject;

import sokoban.model.position.Position;

import java.util.Arrays;
import java.util.List;

public class Level {
    private final String name;
    private final int width;
    private final int height;
    private final StaticObject[][] staticObjects;
    private final Position playerStart;
    private final List<Position> boxStarts;
    private final List<Position> goalPositions;

    public Level(String name, int width, int height, StaticObject[][] staticObjects,
                 Position playerStart, List<Position> boxStarts, List<Position> goalPositions) {
        if (name == null) {
            throw new IllegalArgumentException("Name can not by null");
        }

        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Metrics of Level are too small");
        }

        if (staticObjects.length != height) {
            throw new IllegalArgumentException("Miss match of height and height of staticObjects array");
        }

        for (int i = 0; i < height; i++) {
            if (staticObjects[i].length != width) {
                throw new IllegalArgumentException("Miss match of width and width of staticObjects array");
            }
        }

        if (playerStart == null) {
            throw new IllegalArgumentException("playerStart position can not by null");
        }

        if (boxStarts == null) {
            throw new IllegalArgumentException("boxStarts can not be null");
        }

        if (goalPositions == null) {
            throw new IllegalArgumentException("goalPositions can not be null");
        }

        this.name = name;
        this.width = width;
        this.height = height;
        this.staticObjects = staticObjects;
        this.playerStart = playerStart;
        this.boxStarts = List.copyOf(boxStarts);
        this.goalPositions = List.copyOf(goalPositions);
    }

    private void ensureWithinBounds(Position position) {
        int x = position.getX();
        int y = position.getY();

        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            throw new IllegalArgumentException("Position out of array bounds.");
        }
    }

    public String getName() {
        return this.name;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public StaticObject getStaticObjectAt(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Position can not be null");
        }

        this.ensureWithinBounds(position);

        return this.staticObjects[position.getY()][position.getX()];
    }

    public StaticObject[][] getStaticObjects() {
        StaticObject[][] copy = new StaticObject[this.staticObjects.length][];

        for (int i = 0; i < this.staticObjects.length; i++) {
            copy[i] = Arrays.copyOf(this.staticObjects[i], this.staticObjects[i].length);
        }

        return copy;
    }

    public Position getPlayerStart() {
        return this.playerStart;
    }

    public List<Position> getBoxStarts() {
        return this.boxStarts;
    }

    public List<Position> getGoalPositions() {
        return this.goalPositions;
    }
}
