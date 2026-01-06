package sokoban.persistence.save;

import java.nio.file.Path;

/**
 * Lightweight metadata descriptor for a save file.
 *
 * @author Peter Magdík
 */
public record SaveDescriptor(
        int id,
        Path path,
        String displayName
) { }