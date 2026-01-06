package sokoban.persistence.save;

import java.nio.file.Path;

/**
 * Lightweight metadata descriptor for a save file.
 */
public record SaveDescriptor(
        int id,
        Path path,
        String displayName
) { }