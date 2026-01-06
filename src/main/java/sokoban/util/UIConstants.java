package sokoban.util;

/**
 * Constants used for UI layout and rendering.
 *
 * @author Peter Magdík
 */
public final class UIConstants {
    public static final int DEFAULT_PADDING = 10;
    public static final int DEFAULT_FONT_SIZE = 10;

    // Heuristic values used because ShapesGE does not provide font metrics
    // Values are approximations for Courier New font only
    public static final double COURIER_WIDTH = 0.6;
    public static final double COURIER_HEIGHT = 0.7;


    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 600;

    private UIConstants() { }
}

