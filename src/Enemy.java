import java.awt.*;

public class Enemy extends DefaultCritter {
    public Enemy(InvaderGameState gameState, float x, float y, float vel, float rad, Color color) {
        this.gameState = gameState;
        this.moveAxis = 'x';
        this.dir = 1;
        this.vel = vel;
        this.color = color;
        this.rad = rad;
        this.x = x;
        this.y = y;
    }


    public void update(boolean changeDir) {

        if (changeDir) {
            this.dir *= -1;
            this.y -= this.vel;
        }
        this.move();
    }
}
