package controllers;

import models.Door;
import models.GameObject;
import utils.Utils;
import views.SingleView;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Admin on 5/5/2017.
 */
public class DoorController extends SingleController {

    private Door door =  (Door) this.gameObject;
    BufferedImage doorImage = Utils.setSize(Utils.loadImage("res/door.jpg"), new Dimension(80, 80));
    private SingleView singleView = new SingleView(doorImage);

    public DoorController(GameObject gameObject) {
        super(gameObject);

    }

    @Override
    public void run() {
        super.run();

    }

    @Override
    public void draw(Graphics g) {
        this.view = singleView;
        super.draw(g);

    }
}
