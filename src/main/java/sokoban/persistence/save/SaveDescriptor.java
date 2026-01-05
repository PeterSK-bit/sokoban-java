package sokoban.persistence.save;

import java.nio.file.Path;

public record SaveDescriptor(
        int id,
        Path path,
        String displayName
) { }