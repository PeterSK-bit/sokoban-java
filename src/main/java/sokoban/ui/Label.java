package sokoban.ui;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;

/**
 * Represents a text label UI element.
 */
public class Label extends UIElement {
    private String text;

    /**
     * Creates a label.
     *
     * @param position position of the label
     * @param visibility whether the label is visible
     * @param text text to display
     */
    public Label(Position position, boolean visibility, String text) {
        super(position, visibility);
        this.text = text;
    }

    /**
     * @return text
     */
    public String getText() {
        return this.text;
    }

    /**
     * does nothing
     */
    @Override
    public void onClick() {

    }

    /**
     * @return render type
     */
    @Override
    public RenderType getRenderType() {
        return RenderType.UI_LABEL;
    }
}
