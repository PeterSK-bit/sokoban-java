package sokoban.persistence.save;

import sokoban.model.level.Level;

public record GameSave(
        int version,
        Level level,
        GameStateSave gameStateSave
) { }

