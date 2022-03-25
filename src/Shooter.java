import java.awt.*;

public class Shooter extends DefaultCritter {
    public Shooter(InvaderGameState gameState, float vel, float rad, Color color) {
        this.color = color;
        this.vel = vel;
        this.rad = rad;
        this.gameState = gameState;
        // original shooter position
        this.y = 0.1f;
        this.x = 0.5f;
    }


}
