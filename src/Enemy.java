import java.awt.*;

public class Enemy extends DefaultCritter {
    float fireProb;
    float bulletSpeed;

    public Enemy(InvaderGameState gameState, float x, float y, float vel, float rad, float fireProb, float bulletSpeed, Color color) {
        this.gameState = gameState;
        this.moveAxis = 'x';
        this.dir = 1;
        this.vel = vel;
        this.color = color;
        this.rad = rad;
        this.x = x;
        this.y = y;
        this.fireProb = fireProb;
        this.bulletSpeed = bulletSpeed;

        this.imageName = "images/enemy.png";
    }


    public void update(boolean changeDir) {

        if (changeDir) {
            this.dir *= -1;
            this.y -= this.vel;
        }
        if (Math.random() < fireProb) {
            fire();
        }
        this.move();
    }

    void fire() {
        Missile newMissile = new Missile(gameState, x, y, bulletSpeed, -1, 0.01f, StdDraw.BLUE);
        if (gameState.missiles == null) { // if no missile exists
            gameState.missiles = newMissile;
        } else { // if the list is already initialized
            gameState.missiles.appendMissile(newMissile);
        }

    }
}
