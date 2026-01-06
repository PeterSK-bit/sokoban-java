package sokoban.ui;

import sokoban.model.position.Position;
import sokoban.render.enums.RenderType;

/**
 * Represents a clickable button UI element with a label.
 */
public class Button extends UIElement {
    private String label;
    private final Runnable onClick;

    /**
     * Creates a button.
     *
     * @param position position of the button
     * @param visible whether the button is visible
     * @param label text to display
     * @param onClick action to execute when clicked
     */
    public Button(Position position, boolean visible, String label, Runnable onClick) {
        super(position, visible);
        this.label = label;
        this.onClick = onClick;
    }

    /**
     * @return label
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * runs on click runnable
     */
    @Override
    public void onClick() {
        this.onClick.run();
    }

    /**
     * @return render type
     */
    @Override
    public RenderType getRenderType() {
        return RenderType.UI_BUTTON;
    }
}
