package sokoban.model.level;

// because I can not use "import sokoban.model.objects.*;" :)
import sokoban.model.objects.GameObject;
import sokoban.model.objects.MoveableObject;
import sokoban.model.objects.StaticObject;

import sokoban.model.position.Position;

import java.util.Arrays;

public class Level {
    private final int width;
    private final int height;
    private final MoveableObject[][] initialMoveableObjects;
    private final StaticObject[][] staticObjects;

    public Level(int width, int height, MoveableObject[][] initialMoveableObjects, StaticObject[][] staticObjects) {
        if (initialMoveableObjects.length != height) {
            throw new IllegalArgumentException("Miss match of height and height of initialMoveableObjects array");
        }

        for (int i = 0; i < height; i++) {
            if (initialMoveableObjects[i].length != width) {
                throw new IllegalArgumentException("Miss match of width and width of initialMoveableObjects array");
            }
        }

        if (staticObjects.length != height) {
            throw new IllegalArgumentException("Miss match of height and height of staticObjects array");
        }

        for (int i = 0; i < height; i++) {
            if (staticObjects[i].length != width) {
                throw new IllegalArgumentException("Miss match of width and width of staticObjects array");
            }
        }

        this.width = width;
        this.height = height;
        this.initialMoveableObjects = initialMoveableObjects;
        this.staticObjects = staticObjects;
    }

    private void ensureWithinBounds(Position position) {
        int x = position.getX();
        int y = position.getY();

        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            throw new IllegalArgumentException("Position out of array bounds.");
        }
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

    public MoveableObject getInitialMoveableAt(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Position can not be null");
        }

        this.ensureWithinBounds(position);

        return this.initialMoveableObjects[position.getY()][position.getX()];
    }

    public MoveableObject[][] getInitialMoveableObjects() {
        MoveableObject[][] copy = new MoveableObject[this.initialMoveableObjects.length][];

        for (int y = 0; y < this.initialMoveableObjects.length; y++) {
            copy[y] = new MoveableObject[this.initialMoveableObjects[y].length];
            for (int x = 0; x < this.initialMoveableObjects[y].length; x++) {
                MoveableObject obj = this.initialMoveableObjects[y][x];
                copy[y][x] = (obj == null ? null : obj.copy());
            }
        }

        return copy;
    }

    public StaticObject[][] getStaticObjects() {
        StaticObject[][] copy = new StaticObject[this.staticObjects.length][];

        for (int i = 0; i < this.staticObjects.length; i++) {
            copy[i] = Arrays.copyOf(this.staticObjects[i], this.staticObjects[i].length);
        }

        return copy;
    }

    public GameObject getObjectAt(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Position can not be null");
        }

        this.ensureWithinBounds(position);

        int x = position.getX();
        int y = position.getY();

        if (this.initialMoveableObjects[y][x] != null) {
            return this.initialMoveableObjects[y][x];
        }

        return this.staticObjects[y][x];
    }
}
