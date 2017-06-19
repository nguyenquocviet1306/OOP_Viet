package scenes;

import game.GameWindow;
import managers.SceneManager;
import utils.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Admin on 5/6/2017.
 */
public class GameOverScene extends GameScene implements MouseListener {

    private GameWindow gameWindow;
    private BufferedImage backButton;

    private Rectangle backRect;

    private BufferedImage gameOverImage;
    private final Point firstButtonLocation;

//    private final Dimension buttonSize;


    public GameOverScene(GameWindow gameWindow) throws IOException {
        this.sceneType = ActionType.GAME_OVER_SCENE;
        this.gameWindow = gameWindow;
//        buttonSize = new Dimension(200, this.gameWindow.getHeight() / 9);
        loadImage();
        firstButtonLocation = new Point(this.gameWindow.getWidth() / 2 - 50 ,
                this.gameWindow.getHeight() / 2 );
        makeRect();

    }

    private void loadImage() {

        try {

//            gameOverImage = ImageIO.read(new FileInputStream("res/game_over.png"));


            backButton = ImageIO.read(new FileInputStream("res/button-play-again.png"));

            backButton = Utils.setSize(backButton,140,70);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeRect(){

        backRect =  new Rectangle(firstButtonLocation.x ,
                firstButtonLocation.y ,
                backButton.getWidth(),
                backButton.getHeight());
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (backRect.contains(e.getX(),e.getY())) {
            System.out.println("back");
            SceneManager.getInstance().sceneAction(ActionType.MENU_SCENE);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void update(Graphics g) {
//        g.drawImage(gameOverImage,100,100,null);
        g.drawImage(backButton,backRect.x , backRect.y,null);
    }

    @Override
    public void run() {

    }
}
