package sokoban.model.position;

import sokoban.model.enums.Direction;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        Position position = (Position) object;

        return (this.x == position.x && this.y == position.y);
    }

    public Position translate(Direction direction) {
        return new Position(this.x + direction.dx(), this.y + direction.dy());
    }

    // Required for correct behavior in hash-based collections (maybe will come handy)
    @Override
    public int hashCode() {
        return 31 * x + y; // 31 is a standard prime used for good hash distribution
    }
}