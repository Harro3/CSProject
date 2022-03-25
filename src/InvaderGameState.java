import java.awt.*;
import java.awt.event.KeyEvent;

// game state class to handle every game object
public class InvaderGameState {
    Color bgColor;
    int canSize;

    Shooter shooter;

    public InvaderGameState(Color bgColor, int canSize) {
        // window configuration
        this.canSize = canSize;
        this.bgColor = bgColor;
        StdDraw.setCanvasSize(canSize, canSize);
        StdDraw.clear(bgColor);

        // shooter initialisation
        shooter = new Shooter(this, 0.01f, 0.05f, Color.WHITE);
    }

    public void update() {
        // key reading for the shooter's movement
        if (StdDraw.isKeyPressed(KeyEvent.getExtendedKeyCodeForChar('a')) && shooter.x > shooter.rad * 1.3) {
            shooter.dir = -1;
        } else if (StdDraw.isKeyPressed(KeyEvent.getExtendedKeyCodeForChar('d')) && shooter.x < 1 - shooter.rad * 1.3) {
            shooter.dir = 1;
        } else {
            shooter.dir = 0;
        }


        // moving game objects
        shooter.move();
    }
}
