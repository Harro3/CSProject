import java.awt.*;

// game state class to handle every game object
public class InvaderGameState {

    Color bgColor;
    int canSize;
    int enemyNb = 55;
    int enemiesPerRow = 11; // more centered with an odd number

    // these variables handle the changing in direction of the enemies
    // they represent the sides of the enemy batch
    public float leftEnBound;
    public float rightEnBound;

    public Shooter shooter;
    public Missile missiles; // recursive list implementation of the missiles

    public Enemy[] enemyArray = new Enemy[enemyNb];
    public float enemyVel = 0.005f;
    public float enemyRad;
    public float enemyDir = 1;

    public InvaderGameState(Color bgColor, int canSize) {
        // window configuration
        this.canSize = canSize;
        this.bgColor = bgColor;
        StdDraw.setCanvasSize(canSize, canSize);
        StdDraw.clear(bgColor);

        // shooter initialisation
        shooter = new Shooter(this, 0.01f, 0.05f, Color.WHITE);

        // enemies initialisation
        InitEnemies(enemyArray, enemyNb, enemiesPerRow, enemyVel);

        this.updateLeftEnBound();
        this.updateRightBound();
    }

    // initialization of the enemies starting positions based on the number of enemies and the number per row
    public void InitEnemies(Enemy[] arr, int enemyNb, int enemiesPerRow, float initialVel) {
        float dist = 1f / enemiesPerRow; // distance between the center of two ennemies
        dist *= 0.8f;  // leaving some space between the enemies
        float rad = dist / 2 * 0.8f; // radius of the enemies
        enemyRad = rad;
        float parityOffset = 0; // offset if the number per row is even (to keep the enemies centered)
        if (enemiesPerRow % 2 == 0) {
            parityOffset = rad;
        }
        for (int intY = 0; intY < enemyNb / enemiesPerRow + 1; intY++) {

            for (int intX = 0; intX < enemiesPerRow; intX++) {
                if (intY * enemiesPerRow + intX >= enemyNb) { // if we reached the number of enemies
                    break;
                }
                //this parts is used to make the enemies gradually from the center and alternating left to right
                float x = parityOffset + .5f + (float) ((intX + 1) / 2) * (float) Math.pow(-1, intX) * dist;
                float y = 1f - dist - intY * dist;
                arr[intY * enemiesPerRow + intX] = new Enemy(this, x, y, initialVel, rad, Color.RED);

            }
        }
    }

    // these functions are called only when the bounds needs to be recalculated (when an enemy dies)
    public void updateLeftEnBound() {
        float res = 1;
        for (int i = 1; i < enemyNb; i++) {
            if (enemyArray[i] != null) res = Math.min(res, enemyArray[i].x);
        }
        res -= enemyRad * 1.1;
        this.leftEnBound = res;
    }

    public void updateRightBound() {
        float res = 0;
        for (int i = 1; i < enemyNb; i++) {
            if (enemyArray[i] != null) res = Math.max(res, enemyArray[i].x);
        }
        res += enemyRad * 1.1;
        this.rightEnBound = res;
    }


    public void update() {

        shooter.update();

        while (missiles != null) { // checking for collisions in the first missiles, while there are missiles left
            // and removing the missile from the list if a collision occurred
            if (missiles.checkCollision()) {
                missiles = missiles.next;
                continue;
            }
            missiles.update();
            if (missiles.y > 1 + missiles.rad * 1.2 || missiles.y < -missiles.rad * 1.2) {
                // removing last missile once out of the screen
                this.missiles = this.missiles.next;
            }
            break;
        }
        shooter.move();

        float inc = enemyVel * enemyDir; // increment for the bounds of the enemy batch
        this.leftEnBound += inc;
        this.rightEnBound += inc;
        // says if a change in direction needs to be made
        boolean changeDir = inc > 0 && rightEnBound >= 1 || inc < 0 && leftEnBound <= 0;
        if (changeDir) {
            enemyDir *= -1;
        }
        for (int i = 0; i < enemyNb; i++) {
            if (enemyArray[i] == null) {
                continue;
            }
            enemyArray[i].update(changeDir);

        }

    }
}
