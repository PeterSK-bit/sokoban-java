package sokoban.persistence.save;

import java.util.List;

public interface ISaveRepository {
    List<SaveDescriptor> listSaves();
    void load(SaveDescriptor save);
    void save(SaveDescriptor save);
}
