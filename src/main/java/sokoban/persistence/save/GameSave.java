package sokoban.persistence.save;

import sokoban.model.level.Level;

/**
 * Serializable representation of a complete game save.
 */
public record GameSave(
        int version,
        Level level,
        GameStateSave state
) { }

