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
 * Created by Admin on 5/5/2017.
 */
public class AboutScene extends GameScene implements MouseListener {

    private GameWindow gameWindow;
    private BufferedImage backgroundAboutScreen;
    private BufferedImage backButton;

    private Rectangle backRect;
    private final Dimension buttonSize;
    private final Point firstButtonLocation;

    public AboutScene(GameWindow gameWindow) throws IOException {
        this.sceneType = ActionType.ABOUT_SCENE;
        this.gameWindow = gameWindow;
        buttonSize = new Dimension(30, 30);
        loadImage();
        firstButtonLocation = new Point(this.gameWindow.getWidth()  - this.buttonSize.width - 20 ,
                this.gameWindow.getHeight()  );
        makeRect();
    }

    private void loadImage(){
        try{
            backgroundAboutScreen = ImageIO.read(new FileInputStream("res/wood2.jpg"));
            backButton = ImageIO.read(new FileInputStream("res/back.png"));


            backgroundAboutScreen = Utils.setSize(backgroundAboutScreen, this.gameWindow.getSize());
            backButton = Utils.setSize(backButton,70,70);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeRect(){
        backRect =  new Rectangle(firstButtonLocation.x - 2 * buttonSize.width,
                firstButtonLocation.y - 3 * buttonSize.height,
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
        g.drawImage(backgroundAboutScreen,0,0,null);
        g.drawImage(backButton,backRect.x , backRect.y,null);
    }

    @Override
    public void run() {
    }


}
