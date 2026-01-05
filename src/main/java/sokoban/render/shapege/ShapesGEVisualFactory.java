package sokoban.render.shapege;

import fri.shapesge.Rectangle;
import fri.shapesge.Circle;
import fri.shapesge.Triangle;
import fri.shapesge.TextBlock;
import fri.shapesge.FontStyle;
import sokoban.model.position.Position;
import sokoban.render.api.IVisualNode;
import sokoban.render.api.IVisualNodeFactory;
import sokoban.render.enums.RenderType;
import sokoban.ui.Background;
import sokoban.ui.Button;
import sokoban.ui.Label;
import sokoban.ui.UIElement;
import sokoban.util.UIConstants;

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
            case BACKGROUND -> this.createBackground(position, (Background)element);
            default -> this.createFallback(position);
        };
    }

    private List<IVisualNode> createButton(Position position, Button button) {
        int x = position.getX();
        int y = position.getY();
        int fontSize = UIConstants.DEFAULT_FONT_SIZE;
        int padding = 10;
        String labelText = button.getLabel();
        int width = (int)(labelText.length() * fontSize * UIConstants.COURIER_WIDTH + 2 * padding);
        int height = (int)(UIConstants.COURIER_HEIGHT * fontSize + 2 * padding);

        button.setHeight(height);
        button.setWidth(width);

        Rectangle bg = new Rectangle(x, y);
        bg.changeSize(width, height);
        bg.changeColor("black");

        Rectangle bg2 = new Rectangle(x + 1, y + 1);
        bg2.changeSize(width - 2, height - 2);
        bg2.changeColor("white");

        TextBlock textBlock = new TextBlock(labelText, x + padding, (int)(y + padding + UIConstants.COURIER_HEIGHT * fontSize));
        textBlock.changeFont("Courier New", FontStyle.PLAIN, fontSize);
        textBlock.changeColor("black");

        return List.of(new ShapesGEVisualNode(bg), new ShapesGEVisualNode(bg2), new ShapesGEVisualNode(textBlock));
    }

    private List<IVisualNode> createLabel(Position position, Label label) {
        int x = position.getX();
        int y = position.getY();
        int fontSize = UIConstants.DEFAULT_FONT_SIZE;
        int padding = 10;
        String labelText = label.getText();

        Rectangle bg = new Rectangle(x, y);
        bg.changeSize(
                (int)(labelText.length() * fontSize * UIConstants.COURIER_WIDTH + 2 * padding),
                (int)(UIConstants.COURIER_HEIGHT * fontSize + 2 * padding)
        );
        bg.changeColor("white");

        TextBlock textBlock = new TextBlock(labelText, x + padding, (int)(y + padding + UIConstants.COURIER_HEIGHT * fontSize));
        textBlock.changeFont("Courier New", FontStyle.PLAIN, fontSize);
        textBlock.changeColor("black");

        return List.of(new ShapesGEVisualNode(bg), new ShapesGEVisualNode(textBlock));
    }

    private List<IVisualNode> createBackground(Position position, Background background) {
        int x = position.getX();
        int y = position.getY();

        Rectangle bg = new Rectangle(x, y);
        bg.changeSize(600, 600);
        bg.changeColor(background.getColor());

        return List.of(new ShapesGEVisualNode(bg));
    }
}
