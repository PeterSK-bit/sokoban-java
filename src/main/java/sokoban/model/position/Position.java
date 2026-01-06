package sokoban.model.position;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import sokoban.model.enums.Direction;

/**
 * Immutable 2D position within the game grid.
 */
public class Position {
    private final int x;
    private final int y;

    /**
     * Creates a position with given coordinates.
     * @param x
     * @param y
     */
    @JsonCreator
    public Position(
            @JsonProperty("x") int x,
            @JsonProperty("y") int y
    ) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return x-coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return y-coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     * Creates a new position translated by the given direction.
     *
     * @param direction movement direction
     * @return translated position
     */
    public Position translate(Direction direction) {
        return new Position(this.x + direction.dx(), this.y + direction.dy());
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        Position position = (Position)object;

        return (this.x == position.x && this.y == position.y);
    }

    // Required for correct behavior in hash-based collections (maybe will come handy)
    @Override
    public int hashCode() {
        return 31 * this.x + this.y; // 31 is a standard prime used for good hash distribution
    }

    @Override
    public String toString() {
        return String.format("[%d; %d]", this.x, this.y);
    }
}