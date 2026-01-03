package sokoban.persistence.save;

public record SaveDescriptor(
        int id,
        String path,
        String displayName
) { }