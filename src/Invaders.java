import java.awt.*;
import java.awt.event.KeyEvent;


// main class handling the game loop
public class Invaders {
    /*
    ####################################################################
       Precision about resources: all assets included in this project
                 including images, music and sound effects
    are fully original and created by the members of this project group.
    ####################################################################
     */


    public static void main(String[] args) {

        StdAudio.loopInBackground("audio/music/Celeste.wav");
        StdDraw.enableDoubleBuffering();

        int timeStep = 20;
        boolean run = true;
        int winner = 0; // 0 = game state | 1 = shooter wins | 2 = shooter loses
        // game state initialisation
        InvaderGameState gameState = null;

        Menu MainMenu = new Menu();

        while (run) {
            MainMenu.update(winner);

            if (MainMenu.gameLevel.equals(Menu.STATE.MENU)) {


                //StdDraw.clear();
                //MainMenu.update();

                StdDraw.show();
                StdDraw.pause(timeStep);
                gameState = null;

            } else if (MainMenu.gameLevel.equals(Menu.STATE.DIFFICULTY)) {


            } else if (MainMenu.gameLevel.equals(Menu.STATE.GAME)) {
                // loop break
                if (gameState == null) {
                    gameState = new InvaderGameState(Color.BLACK, 500, 55, 11, 0.001f, 0.005f, 0.005f, 1.02f);
                }

                System.out.println(gameState.winner);
                if (StdDraw.isKeyPressed(KeyEvent.getExtendedKeyCodeForChar('q'))) {
                    run = false;
                }
                winner = gameState.update();

                // drawing current game state
                StdDraw.show();
                StdDraw.pause(timeStep);
            }
        }


        System.exit(0);

    }
}
