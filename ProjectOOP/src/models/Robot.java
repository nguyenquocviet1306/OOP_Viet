package models;

/**
 * Created by Admin on 3/25/2017.
 */
public class Robot extends GameObject {

    public static final int WIDTH = 80;
    public static final int HEIGHT = 80;

    public Robot(int x, int y, int z) {
        super(x, y, z, WIDTH, HEIGHT);
    }
}
