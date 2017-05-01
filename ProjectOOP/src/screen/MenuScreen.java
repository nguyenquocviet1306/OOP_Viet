package screen;

import game.GameWindow;

import utils.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Admin on 4/27/2017.
 */
public class MenuScreen extends Screen {

    private BufferedImage backgroundImage;
    private BufferedImage playImage;
    private BufferedImage aboutImage;
    private BufferedImage exitImage;

    private GameWindow gameWindow;

    private Rectangle playRect;
    private Rectangle aboutRect;
    private Rectangle exitRect;

    private final Point firstButtonLocation;
    private final Dimension buttonSize;

    public MenuScreen(GameWindow gameWindow) throws IOException {
        this.gameWindow = gameWindow;
        buttonSize = new Dimension(200, this.gameWindow.windowSize.height / 9);
        loadImage();
        firstButtonLocation = new Point(this.gameWindow.windowSize.width / 2 - this.buttonSize.width / 2,
                this.gameWindow.windowSize.height / 2 );
        makeRect();
    }

    private void loadImage() {
        try {
            System.out.println("On");

            backgroundImage = ImageIO.read(new FileInputStream("res/menuscreen.png"));
            playImage = ImageIO.read(new FileInputStream("res/play button.png"));
            aboutImage = ImageIO.read(new FileInputStream("res/about button.png"));
            exitImage = ImageIO.read(new FileInputStream("res/exit button.png"));

//            backgroundImage = Utils.loadImage("res/background.png");
            backgroundImage = setSize(backgroundImage, this.gameWindow.windowSize.width, this.gameWindow.windowSize.height);
            playImage = setSize(playImage,buttonSize.width, buttonSize.height);
            aboutImage = setSize(aboutImage,buttonSize.width,buttonSize.height);
            exitImage = setSize(exitImage,buttonSize.width,buttonSize.height);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeRect(){
        playRect = new Rectangle(firstButtonLocation.x, firstButtonLocation.y, playImage.getWidth(), playImage.getHeight());
        aboutRect = new Rectangle(firstButtonLocation.x,firstButtonLocation.y + buttonSize.height + 10,aboutImage.getWidth(),aboutImage.getHeight());
        exitRect = new Rectangle(firstButtonLocation.x,firstButtonLocation.y + 2*buttonSize.height + 20 ,exitImage.getWidth(),exitImage.getHeight());


    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(backgroundImage, 0, 0, null);
        g.drawImage(playImage,playRect.x,playRect.y,null);
        g.drawImage(aboutImage,aboutRect.x,aboutRect.y,null);
        g.drawImage(exitImage,exitRect.x,exitRect.y,null);
    }

    @Override
    public void run() {
        System.out.println("run run");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (playRect.contains(e.getX(),e.getY())) {
            PlayScreen playScreen = new PlayScreen(gameWindow);
            gameWindow.removeMouseListener(this);
            gameWindow.addMouseListener(playScreen);
            GameScreenManager.instance.getScreenStack().push(playScreen);
        }

        if (aboutRect.contains(e.getX(),e.getY())) {
            AboutScreen aboutScreen = new AboutScreen(gameWindow);
            gameWindow.removeMouseListener(this);
            gameWindow.addMouseListener(aboutScreen);
            GameScreenManager.instance.getScreenStack().push(aboutScreen);
        }

        if (exitRect.contains(e.getX(),e.getY())) {
            System.exit(1);
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
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
