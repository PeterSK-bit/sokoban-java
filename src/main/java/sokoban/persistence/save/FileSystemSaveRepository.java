package sokoban.persistence.save;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public final class FileSystemSaveRepository implements ISaveRepository {

    private static final Path SAVES_DIR = Path.of("saves");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public List<SaveDescriptor> listSaves() {
        try (Stream<Path> paths = Files.list(SAVES_DIR)) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().endsWith(".json"))
                    .map(p -> this.toDescriptor(p))
                    .toList();
        } catch (IOException e) {
            throw new SaveRepositoryException("Failed to list save files", e);
        }
    }

    @Override
    public GameSave load(Path savePath) {
        try {
            return MAPPER.readValue(savePath.toFile(), GameSave.class);
        } catch (IOException e) {
            throw new SaveRepositoryException("Failed to load save: " + savePath, e);
        }
    }

    @Override
    public void save(GameSave save, Path savePath) {
        try {
            MAPPER.writerWithDefaultPrettyPrinter().writeValue(savePath.toFile(), save);
        } catch (IOException e) {
            throw new SaveRepositoryException("Failed to save game: " + savePath, e);
        }
    }

    private SaveDescriptor toDescriptor(Path path) {
        try {
            var root = MAPPER.readTree(path.toFile());

            int id = root.path("id").asInt();
            String displayName = root.path("name").asText();

            return new SaveDescriptor(id, path.toString(), displayName);

        } catch (IOException e) {
            throw new SaveRepositoryException("Failed to read save file: " + path, e);
        }
    }
}

