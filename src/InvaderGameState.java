import java.awt.*;

// game state class to handle every game object
public class InvaderGameState {
    Color bgColor;
    int canSize;

    public Shooter shooter;
    public Missile missiles; // recursive list implementation of the missiles

    public InvaderGameState(Color bgColor, int canSize) {
        // window configuration
        this.canSize = canSize;
        this.bgColor = bgColor;
        StdDraw.setCanvasSize(canSize, canSize);
        StdDraw.clear(bgColor);

        // shooter initialisation
        shooter = new Shooter(this, 0.01f, 0.05f, Color.WHITE);
    }

    public void moveObjects() {
        shooter.move();

    }

    public void update() {
        shooter.update();


        if (missiles != null) {
            missiles.update();
            if (missiles.y > 1 + missiles.rad * 1.2) {
                // removing last missile once out of the screen
                this.missiles = this.missiles.next;
            }
        }


        // moving game objects
        moveObjects();
    }
}
