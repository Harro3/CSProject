import java.awt.*;

public class Enemy extends DefaultCritter {
    float fireProb;
    float bulletSpeed;

    public Enemy(InvaderGameState gameState, float x, float y, float vel, float rad, float fireProb, float bulletSpeed, Color color) {
        this.gameState = gameState;
        this.angle = 0;
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
            // change direction and then go down one step
            this.angle = (this.angle + 180) % 360;
            this.y -= this.vel;
        }
        if (Math.random() < fireProb) {
            fire();
        }
        if (this.y < gameState.shooter.y) {
            gameState.winner = 2;
        }
        this.move();
    }

    void fire() {
        Missile newMissile = new Missile(gameState, x, y, bulletSpeed, "Enemy", 0.01f, StdDraw.BLUE);
        if (gameState.missiles == null) { // if no missile exists
            gameState.missiles = newMissile;
        } else { // if the list is already initialized
            gameState.missiles.appendMissile(newMissile);
        }

    }
}
