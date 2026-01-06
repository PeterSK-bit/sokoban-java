package sokoban.model.enums;

/**
 * Represents movement directions with their corresponding
 * coordinate deltas.
 */
public enum  Direction {
    UP (0, -1),
    DOWN (0, 1),
    LEFT (-1, 0),
    RIGHT (1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Horizontal movement delta.
     *
     * @return x-axis delta
     */
    public int dx() {
        return this.dx;
    }

    /**
     * Vertical movement delta.
     *
     * @return y-axis delta
     */
    public int dy() {
        return this.dy;
    }
}
