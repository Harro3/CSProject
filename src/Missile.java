import java.awt.*;


public class Missile extends DefaultCritter {

    public Missile(InvaderGameState gameState, float x, float y, float vel, int dir, float rad, Color color) {
        this.color = color;
        this.vel = vel;
        this.rad = rad;
        this.gameState = gameState;
        // original missile position
        this.x = x;
        this.y = y;

        this.moveAxis = 'y';
        this.dir = dir;
    }

    public Missile next; // next missile in the list

    public void update() {
        this.move();
        if (next != null) {
            next.update();
        }


    }

    // adds a missile in the recursive list
    public void appendMissile(Missile m) {
        if (next == null) {
            next = m;
        } else {
            next.appendMissile(m);
        }
    }

    // returns the number of missile (debugging function)
    public int getLength() {
        if (next == null) {
            return 1;
        } else {
            return (1 + next.getLength());
        }
    }
}


