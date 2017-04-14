package views;

import game.GameConfig;
import models.GameObject;
import utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by Nhan on 3/6/2017.
 */
public class Animation implements View {
    private Vector<BufferedImage> imageVector;
    private int imageCount = 0;
    private int frameRateCount = 0;
    private int frameRate;
    private boolean animationEnd = false;
    public Animation(Vector<BufferedImage> images, int frameRate){
        this.imageVector = images;
        this.frameRate = frameRate;
    }

    public Animation(String... imageName){
        imageVector = new Vector<>();
        for (String name: imageName) {
            imageVector.add(Utils.loadImage(name));
            System.out.println("name : " + name);
            this.frameRate = GameConfig.STANDING_FRAME_RATE;

        }
    }

    public Animation(String url, int frameRate){
        this.imageVector = new Vector<>();
        imageVector = Utils.loadSheetAnimation(url, 80, 80, 0, 4);
        this.frameRate = frameRate;
    }

    @Override
    public void draw(Graphics g, GameObject gameObject) {
        BufferedImage image = imageVector.get(imageCount);
        frameRateCount++;
        g.drawImage(
                image,
                gameObject.getX(),
                gameObject.getY(),
                null);
        if (frameRateCount == frameRate) {
            frameRateCount = 0;
            imageCount++;
            if (imageCount > imageVector.size() - 1){
                imageCount = 0;
                animationEnd = true;
            }
        }

    }
}
