import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;


public class Menu extends MouseAdapter {


    public void difficulty() {

    }

    public enum STATE {
        MENU,
        GAME,
        DIFFICULTY
    }

    public STATE gameLevel;

    public Menu() {

        gameLevel = STATE.MENU;
        StdDraw.setCanvasSize(500, 500);
        draw();

    }

    public void draw() {
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.picture(0.5, 0.5, "images/background.jpg", 2.0, 2.0);

        Font fontMain = new Font("Arial", Font.BOLD, 40);
        StdDraw.setFont(fontMain);

        StdDraw.text(0.5, 0.8, "SPACE INVADERS", 0.0);

        Font fontSet = new Font("Arial", Font.BOLD, 22);
        StdDraw.setFont(fontSet);

        StdDraw.text(0.5, 0.4, "X To Start", 0.0);
        StdDraw.text(0.5, 0.2, "Y To Set Difficulty", 0.0);

        StdDraw.rectangle(0.5, 0.4, 0.15, 0.05);
        StdDraw.rectangle(0.5, 0.2, 0.25, 0.05);

        if (StdDraw.isMousePressed()) {


            if ((StdDraw.mouseX() >= 0.35 && StdDraw.mouseX() <= 0.65) && (StdDraw.mouseY() >= 0.35 && StdDraw.mouseY() <= 0.45)) {

                System.exit(0);
            }

        }

    }
    /*public void MousePressed(MouseEvent e){

    }
    public void MouseReleased(MouseEvent e){

    }*/


    public void update() {

        if (StdDraw.isKeyPressed(KeyEvent.getExtendedKeyCodeForChar('x'))) //X key is pressed to start game
        {
            gameLevel = STATE.GAME;
        } else if (StdDraw.isKeyPressed(KeyEvent.getExtendedKeyCodeForChar('y'))) {
            gameLevel = STATE.DIFFICULTY;
            

        } else if (StdDraw.isKeyPressed(KeyEvent.getExtendedKeyCodeForChar('m'))) {
            gameLevel = STATE.MENU;
            draw();
        }


    }


}
