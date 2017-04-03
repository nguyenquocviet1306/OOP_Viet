package models;

/**
 * Created by Admin on 3/25/2017.
 */
public class GameVector {
    public int dx;
    public int dy;

    public GameVector() {
        this(0, 0);
    }

    public GameVector(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
