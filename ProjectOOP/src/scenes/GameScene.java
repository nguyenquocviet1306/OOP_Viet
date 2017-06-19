package scenes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

/**
 * Created by Nhan on 3/7/2017.
 */
public abstract class GameScene extends JPanel implements KeyListener{
    protected ActionType sceneType;

    public ActionType getSceneType() {
        return sceneType;
    }

    public void setSceneType(ActionType sceneType) {
        this.sceneType = sceneType;
    }

    public abstract void update(Graphics g);
    public abstract void run();

}
