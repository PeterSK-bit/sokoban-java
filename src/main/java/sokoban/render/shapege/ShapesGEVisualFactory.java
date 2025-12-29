package sokoban.render.shapege;

import fri.shapesge.Circle;
import fri.shapesge.Rectangle;
import fri.shapesge.TextBlock;
import fri.shapesge.Triangle;
import sokoban.model.position.Position;
import sokoban.render.api.IVisualNode;
import sokoban.render.api.IVisualNodeFactory;
import sokoban.render.enums.RenderType;
import sokoban.ui.Button;
import sokoban.ui.Label;
import sokoban.ui.UIElement;

import java.util.List;

public class ShapesGEVisualFactory implements IVisualNodeFactory {
    @Override
    public List<IVisualNode> createGameVisual(RenderType type, Position position) {
        return switch (type) {
            case PLAYER -> this.createPlayer(position);
            case BOX -> this.createBox(position);
            case WALL -> this.createWall(position);
            case GOAL -> this.createGoal(position);
            case FLOOR -> this.createFloor(position);
            default -> this.createFallback(position);
        };
    }

    private List<IVisualNode> createPlayer(Position position) {
        Triangle t = new Triangle(position.getX(), position.getY());
        t.changeColor("blue");
        t.changeSize(50, 50);

        return List.of(
                new ShapesGEVisualNode(t)
        );
    }

    private List<IVisualNode> createBox(Position position) {
        int x = position.getX();
        int y = position.getY();

        Rectangle r1 = new Rectangle(x, y);
        r1.changeColor("brown");
        r1.changeSize(50, 50);

        Rectangle r2 = new Rectangle(x + 5, y + 5);
        r2.changeColor("light_brown");
        r2.changeSize(40, 40);

        return List.of(
            new ShapesGEVisualNode(r1),
            new ShapesGEVisualNode(r2)
        );
    }

    private List<IVisualNode> createWall(Position position) {
        Rectangle r = new Rectangle(position.getX(), position.getY());
        r.changeSize(50, 50);
        r.changeColor("rock");

        return List.of(new ShapesGEVisualNode(r));
    }

    private List<IVisualNode> createGoal(Position position) {
        Circle c = new Circle(position.getX() + 12, position.getY() + 12);
        c.changeSize(25);
        c.changeColor("red");

        Rectangle r = new Rectangle(position.getX(), position.getY());
        r.changeSize(50, 50);
        r.changeColor("sand");

        return List.of(
                new ShapesGEVisualNode(c),
                new ShapesGEVisualNode(r)
        );
    }

    private List<IVisualNode> createFloor(Position position) {
        Rectangle r = new Rectangle(position.getX(), position.getY());
        r.changeSize(50, 50);
        r.changeColor("sandy");

        return List.of(new ShapesGEVisualNode(r));
    }

    private List<IVisualNode> createFallback(Position position) {
        int x = position.getX();
        int y = position.getY();

        Rectangle r1 = new Rectangle(x, y);
        r1.changeColor("black");
        r1.changeSize(25, 25);

        Rectangle r2 = new Rectangle(x + 25, y);
        r2.changeColor("pink");
        r2.changeSize(25, 25);

        Rectangle r3 = new Rectangle(x, y + 25);
        r3.changeColor("pink");
        r3.changeSize(25, 25);

        Rectangle r4 = new Rectangle(x + 25, y + 25);
        r4.changeColor("black");
        r4.changeSize(25, 25);

        return List.of(
                new ShapesGEVisualNode(r1),
                new ShapesGEVisualNode(r2),
                new ShapesGEVisualNode(r3),
                new ShapesGEVisualNode(r4)
        );
    }

    @Override
    public List<IVisualNode> createUIElementVisual(UIElement element) {
        if (element == null) {
            throw new IllegalArgumentException("UIElement can not by null");
        }

        Position position = element.getPosition();

        return switch (element.getRenderType()) {
            case UI_BUTTON -> this.createButton(position, (Button)element);
            case UI_LABEL -> this.createLabel(position, (Label)element);
            default -> this.createFallback(position);
        };
    }

    private List<IVisualNode> createButton(Position position, Button button) {
        int x = position.getX();
        int y = position.getY();
        String labelText = button.getLabel();

        Rectangle bg = new Rectangle(x, y);
        bg.changeSize(labelText.length() * 15 + 20, 50);
        bg.changeColor("white");

        TextBlock textBlock = new TextBlock(labelText, x + 10, y + 10);
        textBlock.changeColor("black");

        return List.of(new ShapesGEVisualNode(bg), new ShapesGEVisualNode(textBlock));
    }

    private List<IVisualNode> createLabel(Position position, Label label) {
        int x = position.getX();
        int y = position.getY();
        String labelText = label.getText();

        Rectangle bg = new Rectangle(x, y);
        bg.changeSize(labelText.length() * 15 + 20, 50);
        bg.changeColor("white");

        TextBlock textBlock = new TextBlock(labelText, x + 10, y + 10);
        textBlock.changeColor("black");

        return List.of(new ShapesGEVisualNode(bg), new ShapesGEVisualNode(textBlock));
    }
}
