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
 * Created by Nhan on 4/30/2017.
 */
public class MenuScene extends GameScene implements MouseListener {
    private BufferedImage backgroundImage;
    private BufferedImage playImage;
    private BufferedImage aboutImage;
    private BufferedImage instructionImage;
    private BufferedImage exitImage;
    private BufferedImage nameGame;
    private GameWindow gameWindow;
    private Rectangle playRect;
    private Rectangle aboutRect;
    private Rectangle exitRect;
    private Rectangle instructionRect;
    private Rectangle nameRect;
    private final Point firstButtonLocation;
    private final Dimension buttonSize;

    public MenuScene(GameWindow gameWindow) throws IOException {
        this.sceneType = ActionType.MENU_SCENE;
        this.gameWindow = gameWindow;
        buttonSize = new Dimension(200, this.gameWindow.getHeight() / 9);
        loadImage();
        firstButtonLocation = new Point(this.gameWindow.getWidth() / 2 - this.buttonSize.width / 2,
                this.gameWindow.getHeight() / 2 - 50 );
        makeRect();
    }

    private void loadImage() {
        try {
            backgroundImage = ImageIO.read(new FileInputStream("res/menu_scene.png"));
            playImage = ImageIO.read(new FileInputStream("res/play_button.png"));
            aboutImage = ImageIO.read(new FileInputStream("res/about button.png"));
            exitImage = ImageIO.read(new FileInputStream("res/exit button.png"));
            instructionImage = ImageIO.read(new FileInputStream("res/instruction button.png"));
            nameGame = ImageIO.read(new FileInputStream("res/name.png"));

            backgroundImage = Utils.setSize(backgroundImage, this.gameWindow.getSize());
            playImage = Utils.setSize(playImage,buttonSize.width, buttonSize.height);
            aboutImage = Utils.setSize(aboutImage,buttonSize.width,buttonSize.height);
            exitImage = Utils.setSize(exitImage,buttonSize.width,buttonSize.height);
            instructionImage = Utils.setSize(instructionImage,buttonSize.width,buttonSize.height);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeRect(){
        playRect = new Rectangle(firstButtonLocation.x, firstButtonLocation.y, playImage.getWidth(), playImage.getHeight());
        aboutRect = new Rectangle(firstButtonLocation.x,firstButtonLocation.y + buttonSize.height + 10,
                aboutImage.getWidth(),aboutImage.getHeight());
        instructionRect = new Rectangle(firstButtonLocation.x,firstButtonLocation.y + 2*buttonSize.height + 20,
                instructionImage.getWidth(),instructionImage.getHeight());
        exitRect = new Rectangle(firstButtonLocation.x,firstButtonLocation.y + 3*buttonSize.height + 30 ,
                exitImage.getWidth(),exitImage.getHeight());
        nameRect = new Rectangle(firstButtonLocation.x - buttonSize.width - 40,firstButtonLocation.y - 3*buttonSize.height,
                nameGame.getWidth(),nameGame.getHeight());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (playRect.contains(e.getX(),e.getY())) {
            PlayScene.mainType = 1;
            SceneManager.getInstance().sceneAction(ActionType.CHOOSE_MAIN);
        }
        if (aboutRect.contains(e.getX(),e.getY())) {
            System.out.println("about");
            SceneManager.getInstance().sceneAction(ActionType.ABOUT_SCENE);
        }
        if (instructionRect.contains(e.getX(),e.getY())) {
            System.out.println("instruction");
            SceneManager.getInstance().sceneAction(ActionType.INSTRUCTION_SCENE);
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
    public void update(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
        g.drawImage(playImage,playRect.x,playRect.y,null);
        g.drawImage(aboutImage,aboutRect.x,aboutRect.y,null);
        g.drawImage(instructionImage,instructionRect.x,instructionRect.y,null);
        g.drawImage(exitImage,exitRect.x,exitRect.y,null);
        g.drawImage(nameGame,nameRect.x,nameRect.y,null);
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
}
