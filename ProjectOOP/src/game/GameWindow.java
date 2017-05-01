package game;


import screen.GameScreenManager;
import screen.MenuScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Nhan on 2/28/2017.
 */
public class GameWindow extends Frame implements Runnable{
    private static final int SCREEN_WIDTH = 768;
    private static final int SCREEN_HEIGHT = 600;
    private BufferedImage backBuffer;
    private Graphics backGraphic;

    public final Dimension windowSize = new Dimension(768,600);

    public GameWindow() throws IOException {
       initWindow();
        MenuScreen menuScreen = new MenuScreen(this);
        this.addMouseListener(menuScreen);
        GameScreenManager.instance.getScreenStack().push(menuScreen);
    }

    void initWindow() {
        setVisible(true);
        setSize(windowSize.width, windowSize.height);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //playScene = new PlayScene();
//        this.addKeyListener(playScene);
//        backBuffer = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_BGR);
//        backGraphic = backBuffer.getGraphics();
    }

    void Update() {
        //playScene.update(backGraphic);
        //g.drawImage(backBuffer, 0, 0, null);
        GameScreenManager.instance.getScreenStack().peek().run();

    }

    @Override
    public void run() {
        while (true){
            Update();
            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            playScene.run();
        }

    }




    @Override
    public void update(Graphics g) {
        if (backBuffer == null) {
            backBuffer = new BufferedImage(windowSize.width,windowSize.height,1);
        }
        backGraphic = backBuffer.getGraphics();
        GameScreenManager.instance.getScreenStack().peek().draw(backGraphic);
        g.drawImage(backBuffer,0,0,null);
//        super.update(g);

    }
}
