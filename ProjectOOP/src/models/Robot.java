package models;

import game.GameConfig;

/**
 * Created by Admin on 3/25/2017.
 */
public class Robot extends GameObject {

    public static final int WIDTH = 80;
    public static final int HEIGHT = 80;

    public Robot(int x, int y, int z) {
        super(x, y, z, WIDTH, HEIGHT);
    }

    public void walkLeft(){
        this.x -= GameConfig.WALKING_SPEED;
    }
    public void walkRight(){
        this.x += GameConfig.WALKING_SPEED;
    }
    public void walkUp(){
        this.y -= GameConfig.WALKING_SPEED;
    }
    public void walkDown(){
        this.y += GameConfig.WALKING_SPEED;
    }
    public void runLeft(){
        this.x -= GameConfig.RUNNING_SPEED;
    }
    public void runRight(){
        this.x += GameConfig.RUNNING_SPEED;
    }
}
