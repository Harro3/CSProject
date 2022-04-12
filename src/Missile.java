import java.awt.*;


public class Missile extends DefaultCritter {

    String type = ""; // name of the class that fired the missile

    public Missile(InvaderGameState gameState, float x, float y, float vel, String type, float rad, Color color) {
        this.color = color;
        this.vel = vel;
        this.rad = rad;
        this.gameState = gameState;
        // original missile position
        this.x = x;
        this.y = y;

        this.type = type;
        if (type.equals("Shooter")) {
            this.angle = gameState.shooter.angle;
        } else {
            this.angle = -90;
        }
    }

    // function that checks if a collision has occurred
    public boolean checkCollision() {
        boolean res = false;
        if (type.equals("Shooter")) {
            // checking collision with all the enemies
            for (int i = 0; i < gameState.enemyNb; i++) {
                if (gameState.enemyArray[i] == null) continue;
                float deltaX = this.x - gameState.enemyArray[i].x;
                float deltaY = this.y - gameState.enemyArray[i].y;

                // distance between the missile and the enemy
                float dist = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

                if (dist <= this.rad + gameState.enemyArray[i].rad) {
                    StdAudio.playInBackground("audio/soundEffects/explosion.wav");
                    gameState.enemyArray[i] = null; // delete enemy
                    res = true;
                    gameState.acc = true;
                    gameState.updateLeftEnBound(); // update the bounds
                    gameState.updateRightBound();
                    break;
                }
            }
        } else {
            // checking collision with the shooter
            float deltaX = gameState.shooter.x - this.x;
            float deltaY = gameState.shooter.y - this.y;

            float dist = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            if (dist <= this.rad + gameState.shooter.rad) {
                gameState.winner = 2;
            }
        }

        return res;
    }

    public Missile next; // next missile in the list

    public void update() {
        this.move();
        while (next != null) { // checks the collision with the next missile and deletes it if one occurred
            if (next.checkCollision()) {
                this.next = this.next.next;
                continue;
            }
            next.update();
            break;
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


