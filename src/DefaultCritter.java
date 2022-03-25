import java.awt.*;

// Default class for every entity
public class DefaultCritter implements Critter {
    // Current position
    float x;
    float y;
    // previous position
    float prevX;
    float prevY;
    float rad = 0.05f;
    float vel;

    int dir; // direction of the entity -1 = left | 1 = right | 0 = not moving

    Color color;

    InvaderGameState gameState;

    public DefaultCritter() {
    }


    @Override
    public void move() {
        prevX = x;
        prevY = y;
        this.x += this.vel * this.dir;

        this.draw(this.color);
    }

    @Override
    public void draw(Color color) {
        StdDraw.setPenColor(gameState.bgColor); // covering the old drawing
        StdDraw.filledCircle(prevX, prevY, rad * 1.1);
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(x, y, rad);
    }
}
