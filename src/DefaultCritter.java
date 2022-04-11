import java.awt.*;

// Default class for every entity
public class DefaultCritter implements Critter {
    // Current position
    float x;
    float y;

    float rad = 0.05f;
    float vel;

    int dir; // direction of the entity -1 = left | 1 = right | 0 = not moving
    char moveAxis = 'x'; // axis of movement

    Color color;

    String imageName = "";

    InvaderGameState gameState;

    public DefaultCritter() {
    }


    @Override
    public void move() {
        if (moveAxis == 'x')
            this.x += this.vel * this.dir;
        else
            this.y += this.vel * this.dir;

        if (this.imageName.isEmpty()) {
            this.draw(this.color);

        } else {
            this.draw();
        }
    }

    @Override
    public void draw(Color color) {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(x, y, rad);
    }

    public void draw() {
        StdDraw.picture(x, y, imageName, rad * 2, rad * 2);
    }

    @Override
    public void update() {
    }


}
