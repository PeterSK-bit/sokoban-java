package sokoban.model.level;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import sokoban.model.objects.Floor;
import sokoban.model.objects.Goal;
import sokoban.model.objects.StaticObject;

import sokoban.model.objects.Wall;
import sokoban.model.position.Position;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Level {
    private final String name;
    private final int id;
    private final int levelNumber;
    private final int width;
    private final int height;
    private final Position playerStart;
    private final List<Position> boxStarts;
    private final List<Position> goalPositions;
    private final List<Position> wallPositions;
    private final StaticObject[][] staticObjects;

    @JsonCreator
    public Level(
            @JsonProperty("name") String name,
            @JsonProperty("id") int id,
            @JsonProperty("levelNumber") int levelNumber,
            @JsonProperty("width") int width,
            @JsonProperty("height") int height,
            @JsonProperty("playerStart") Position playerStart,
            @JsonProperty("boxStarts") List<Position> boxStarts,
            @JsonProperty("goals") List<Position> goalPositions,
            @JsonProperty("walls") List<Position> wallPositions
    ) {
        if (name == null) {
            throw new IllegalArgumentException("Name can not by null");
        }

        if (levelNumber < 0) {
            throw new IllegalArgumentException("levelNumber can not be negative");
        }

        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Metrics of Level are too small");
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

        if (wallPositions == null) {
            throw new IllegalArgumentException("wallPositions can not be null");
        }

        this.name = name;
        this.id = id;
        this.levelNumber = levelNumber;
        this.width = width;
        this.height = height;
        this.playerStart = playerStart;
        this.boxStarts = List.copyOf(boxStarts);
        this.goalPositions = List.copyOf(goalPositions);
        this.wallPositions = wallPositions;

        this.validateLevelState();
        this.staticObjects = this.constructStaticObjects(this.goalPositions, this.wallPositions);
    }

    private StaticObject[][] constructStaticObjects(List<Position> goalPositions, List<Position> wallPositions) {
        StaticObject[][] grid = new StaticObject[this.height][this.width];

        for (Position gp : goalPositions) {
            grid[gp.getY()][gp.getX()] = new Goal(gp);
        }

        for (Position wp : wallPositions) {
            grid[wp.getY()][wp.getX()] = new Wall(wp);
        }

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if (grid[y][x] == null) {
                    grid[y][x] = new Floor(new Position(x, y));
                }
            }
        }

        return grid;
    }

    private void validateLevelState() {
        Set<Position> walls = new HashSet<>(this.wallPositions);
        Set<Position> boxes = new HashSet<>(this.boxStarts);
        Set<Position> goals = new HashSet<>(this.goalPositions);

        // duplicates
        if (walls.size() != this.wallPositions.size()) {
            throw new IllegalStateException("Duplicate wall positions");
        }
        if (boxes.size() != this.boxStarts.size()) {
            throw new IllegalStateException("Duplicate box positions");
        }
        if (goals.size() != this.goalPositions.size()) {
            throw new IllegalStateException("Duplicate goal positions");
        }

        // within bounds
        for (Position gp : this.goalPositions) {
            this.ensureWithinBounds(gp);
        }

        for (Position wp : this.wallPositions) {
            this.ensureWithinBounds(wp);
        }

        this.ensureWithinBounds(this.playerStart);

        for (Position bp : this.boxStarts) {
            this.ensureWithinBounds(bp);
        }

        // wall covering
        for (Position wp : this.wallPositions) {
            if (goals.contains(wp)) {
                throw new IllegalStateException("Wall is covering goal at " + wp.toString());
            }
            if (boxes.contains(wp)) {
                throw new IllegalStateException("Wall is covering box at " + wp.toString());
            }
        }

        if (walls.contains(this.playerStart)) {
            throw new IllegalStateException("Wall is covering player at " + this.playerStart);
        }

        if (boxes.contains(this.playerStart)) {
            throw new IllegalStateException("Box is covering player at " + this.playerStart);
        }
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

    public int getId() {
        return this.id;
    }

    public int getLevelNumber() {
        return this.levelNumber;
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
