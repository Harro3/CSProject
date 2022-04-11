import java.awt.*;
import java.awt.event.KeyEvent;

public class Shooter extends DefaultCritter {
    int initialCoolDown = 5; // cool down for the missiles
    int coolDown = 0;

    public Shooter(InvaderGameState gameState, float vel, float rad, Color color) {
        this.color = color;
        this.vel = vel;
        this.rad = rad;
        this.gameState = gameState;
        // original shooter position
        this.y = 0.1f;
        this.x = 0.5f;

        this.imageName = "images/shooter.png";
    }

    public void update() {
        StdDraw.clear(gameState.bgColor);
        // key reading for the shooter's movement
        if (StdDraw.isKeyPressed(KeyEvent.getExtendedKeyCodeForChar('a')) && x > rad * 1.3) {
            dir = -1;
        } else if (StdDraw.isKeyPressed(KeyEvent.getExtendedKeyCodeForChar('d')) && x < 1 - rad * 1.3) {
            dir = 1;
        } else {
            dir = 0;
        }

        if (coolDown > 0) {
            coolDown--;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
            this.fire();
            coolDown = initialCoolDown;
        }


    }

    void fire() {
        StdAudio.playInBackground("audio/soundEffects/laserShoot.wav");

        Missile newMissile = new Missile(gameState, x, y, 0.03f, 1, 0.01f, StdDraw.GREEN);
        if (gameState.missiles == null) { // if no missile exists
            gameState.missiles = newMissile;
        } else { // if the list is already initialized
            gameState.missiles.appendMissile(newMissile);
        }

    }


}
