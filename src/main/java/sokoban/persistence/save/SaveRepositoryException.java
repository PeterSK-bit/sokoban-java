package sokoban.persistence.save;

/**
 * Runtime exception representing persistence layer failures.
 *
 * @author Peter Magdík
 */
public class SaveRepositoryException extends RuntimeException {
    /**
     * Creates a new save repository exception.
     *
     * @param message error message
     * @param cause underlying cause
     */
    public SaveRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
