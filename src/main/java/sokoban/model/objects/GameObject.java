package sokoban.model.objects;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;

/**
 * Base class for all objects existing in the game world.
 * Provides position handling and common object behavior contracts.
 */
public abstract class GameObject {
    private Position position;

    /**
     * Creates a game object at a given position.
     *
     * @param position initial position
     */
    public GameObject(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("position can not be null");
        }

        this.position = position;
    }

    /**
     * @return current object position
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * Updates object position.
     *
     * @param position new position
     */
    public void setPosition(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Position can not be null");
        }

        this.position = position;
    }

    /**
     * @return render type used for visualization
     */
    public abstract RenderType getRenderType();

    @Override
    public boolean equals(Object object) {
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }

        if (this == object) {
            return true;
        }

        GameObject other = (GameObject)object;

        return this.position.equals(other.position);
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