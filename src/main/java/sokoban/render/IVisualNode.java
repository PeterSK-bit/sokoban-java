package sokoban.render;

import sokoban.model.position.Position;

public interface IVisualNode {
    void show();
    void hide();
    void setPosition(Position position);
    void remove();
}
