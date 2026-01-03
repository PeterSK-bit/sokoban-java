package sokoban.persistence.save;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public final class FileSystemSaveRepository implements ISaveRepository {

    private static final Path SAVES_DIR = Path.of("saves");

    @Override
    public List<SaveDescriptor> listSaves() {
        try (Stream<Path> paths = Files.list(SAVES_DIR)) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().endsWith(".json"))
                    .map(p -> this.toDescriptor(p))
                    .toList();
        } catch (IOException e) {
            throw e;
        }
    }

    private SaveDescriptor toDescriptor(Path path) {
        String fileName = path.getFileName().toString();
        ObajectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(path.toFile());

        int levelId = root
                .path("level")
                .path("id")
                .asInt();

        return new SaveDescriptor(id, fileName);
    }
}

