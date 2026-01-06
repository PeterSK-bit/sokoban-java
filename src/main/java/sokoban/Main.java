package sokoban;

import sokoban.game.GameController;

/**
 * Application entry point.
 * Responsible only for bootstrapping the game controller.
 */
public class Main {

    /**
     * Starts the Sokoban application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new GameController();
    }
}
