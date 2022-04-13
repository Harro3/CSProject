import java.awt.*;
import java.awt.event.KeyEvent;

public class Shooter extends DefaultCritter {
    int initialCoolDown = 8; // cool down for the missiles
    int coolDown = 0;

    float rotSpeed = 2;
    int score = 0;

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
        if (StdDraw.isKeyPressed(KeyEvent.getExtendedKeyCodeForChar('z')) && x > rad * 1.3) {
            this.x -= vel;
        } else if (StdDraw.isKeyPressed(KeyEvent.getExtendedKeyCodeForChar('c')) && x < 1 - rad * 1.3) {
            this.x += vel;
        }

        // key reading for the shooter's rotation
        if (StdDraw.isKeyPressed(KeyEvent.getExtendedKeyCodeForChar('d')) && (this.angle > 45)) {
            this.angle -= rotSpeed;
        } else if (StdDraw.isKeyPressed(KeyEvent.getExtendedKeyCodeForChar('a')) && (this.angle < 135)) {
            this.angle += rotSpeed;
        }

        if (coolDown > 0) {
            coolDown--;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
            this.fire();
            coolDown = initialCoolDown;
        }

        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(0.1, 0.1, "Score: " + this.score);
    }

    void fire() {
        StdAudio.playInBackground("audio/soundEffects/laserShoot.wav");

        Missile newMissile = new Missile(gameState, x, y, 0.03f, "Shooter", 0.01f, StdDraw.GREEN);
        if (gameState.missiles == null) { // if no missile exists
            gameState.missiles = newMissile;
        } else { // if the list is already initialized
            gameState.missiles.appendMissile(newMissile);
        }

    }


}
