import java.awt.*;
import java.awt.event.KeyEvent;

// main class handling the game loop
public class Invaders {
    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();

        int timeStep = 20;
        boolean run = true;
        // game state initialisation
        InvaderGameState gameState = new InvaderGameState(Color.BLACK, 500);

        // main loop
        while (run) {

            gameState.update();

            // loop break
            if (StdDraw.isKeyPressed(KeyEvent.getExtendedKeyCodeForChar('q'))) {
                run = false;
            }

            // drawing current game state
            StdDraw.show();
            StdDraw.pause(timeStep);
        }

        System.exit(0);

    }
}
