package sokoban.model.objects;

import sokoban.model.position.Position;

public class GameObject {
    private Position position;

    public GameObject(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("position can not be null");
        }

        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        if (this == object) {
            return true;
        }

        GameObject other = (GameObject) object;

        return position.equals(other.position);
    }

    @Override
    public int hashCode() {
        return this.position.hashCode();
    }

    @Override
    public String toString() {
        return "GameObject{position=" + this.position + "}";
    }
}