package screen;

import game.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Admin on 4/30/2017.
 */
public class AboutScreen extends Screen{

    private GameWindow gameWindow;

    private BufferedImage backgroundAboutScreen;
    private BufferedImage backButton;

    private Rectangle backRect;

    public AboutScreen(GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        loadImage();
        makeRect();


    }

    private void loadImage(){
        try{
            backgroundAboutScreen = ImageIO.read(new FileInputStream("res/red.png"));
            backButton = ImageIO.read(new FileInputStream("res/backbutton.png"));

            backgroundAboutScreen = setSize(backgroundAboutScreen, this.gameWindow.windowSize.width, this.gameWindow.windowSize.height);
            backButton = setSize(backButton,70,70);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeRect(){
            backRect =  new Rectangle(this.gameWindow.windowSize.width -   backButton.getWidth(),
                    backButton.getHeight() / 2,
                    backButton.getWidth(),backButton.getHeight());
    }


    @Override
    public void draw(Graphics g) {
            g.drawImage(backgroundAboutScreen,0,0,null);
            g.drawImage(backButton,backRect.x , backRect.y,null);
    }

    @Override
    public void run() {

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
            this.gameWindow.removeMouseListener(this);
            GameScreenManager.instance.getScreenStack().pop();
            this.gameWindow.addMouseListener(GameScreenManager.instance.getScreenStack().peek());
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
}
