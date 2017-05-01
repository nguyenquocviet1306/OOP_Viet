package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * Created by Admin on 4/27/2017.
 */
public abstract class Screen extends JPanel implements MouseListener,KeyListener {


    public abstract void draw(Graphics g);

    public abstract void run();

    final BufferedImage setSize(BufferedImage sbi, int w, int h) {

        if (sbi != null) {
            Image tmp = sbi.getScaledInstance(w, h, Image.SCALE_SMOOTH);
            BufferedImage dbi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            dbi.getGraphics().drawImage(tmp, 0, 0, null);
            return dbi;
        }
        return null;
    }
    final BufferedImage setSize(BufferedImage sbi, Dimension size) {
        return setSize(sbi, size.width, size.height);
    }


}
