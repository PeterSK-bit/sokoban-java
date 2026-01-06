package sokoban.persistence.save;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * File-system based repository for game saves.
 */
public class FileSystemSaveRepository {
    private static final Path SAVES_DIR = Path.of("saves");
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Lists all available save files.
     *
     * @return list of save descriptors
     */
    public static List<SaveDescriptor> listSaves() {
        try (Stream<Path> paths = Files.list(SAVES_DIR)) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().endsWith(".json"))
                    .map(FileSystemSaveRepository::toDescriptor)
                    .toList();
        } catch (IOException e) {
            throw new SaveRepositoryException("Failed to list save files", e);
        }
    }

    /**
     * Loads a saved game from the given path.
     *
     * @param savePath path to save file
     * @return loaded game save
     */
    public static GameSave load(Path savePath) {
        try {
            return MAPPER.readValue(savePath.toFile(), GameSave.class);
        } catch (IOException e) {
            throw new SaveRepositoryException("Failed to load save: " + savePath, e);
        }
    }

    /**
     * Persists a game save to the given path.
     *
     * @param save game save data
     * @param savePath destination path
     */
    public static void save(GameSave save, Path savePath) {
        try {
            MAPPER.writerWithDefaultPrettyPrinter().writeValue(savePath.toFile(), save);
        } catch (IOException e) {
            throw new SaveRepositoryException("Failed to save game: " + savePath, e);
        }
    }

    private static SaveDescriptor toDescriptor(Path path) {
        try {
            JsonNode root = MAPPER.readTree(path.toFile());
            JsonNode level = root.path("level");

            int id = level.path("id").asInt();
            String displayName = level.path("name").asText();

            return new SaveDescriptor(id, path, displayName);

        } catch (IOException e) {
            throw new SaveRepositoryException("Failed to read save file: " + path, e);
        }
    }
}

