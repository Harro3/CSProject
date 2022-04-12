import java.awt.*;

// Default class for every entity
public class DefaultCritter implements Critter {
    // Current position
    float x;
    float y;

    // radius, velocity and angle in degrees of the entity
    float rad = 0.05f;
    float vel;
    int angle = 90;

    Color color;

    String imageName = "";

    InvaderGameState gameState;

    public DefaultCritter() {
    }


    @Override
    public void move() {
        // converting the angles to radian and then updating the position
        this.x += this.vel * Math.cos((double) this.angle * Math.PI / 180f);
        this.y += this.vel * Math.sin((double) this.angle * Math.PI / 180f);

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
        // only the shooter needs to appear rotated
        if (this.equals(gameState.shooter)) {
            StdDraw.picture(x, y, imageName, rad * 2, rad * 2, this.angle - 90);
        } else {
            StdDraw.picture(x, y, imageName, rad * 2, rad * 2);
        }
    }

    @Override
    public void update() {
    }


}
