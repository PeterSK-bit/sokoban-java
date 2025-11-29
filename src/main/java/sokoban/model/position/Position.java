package sokoban.model.position;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
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

    public Position translate(int dx, int dy) {
        return new Position(this.x + dx, this.y + dy);
    }

    // Required for correct behavior in hash-based collections (maybe will come handy)
    @Override
    public int hashCode() {
        return 31 * x + y; // 31 is a standard prime used for good hash distribution
    }
}