package sokoban.persistence.save;

import sokoban.model.position.Position;

import java.util.List;

/**
 * Serializable snapshot of runtime game state.
 */
public record GameStateSave(
        int moves,
        int pushes,
        int timeElapsed,
        Position player,
        List<Position> boxes
) { }

