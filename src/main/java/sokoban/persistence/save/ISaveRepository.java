package sokoban.persistence.save;

import java.nio.file.Path;
import java.util.List;

public interface ISaveRepository {
    List<SaveDescriptor> listSaves();
    GameSave load(Path savePath);
    void save(GameSave save, Path savePath);
}
