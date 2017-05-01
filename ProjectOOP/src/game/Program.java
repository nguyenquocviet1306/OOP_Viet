package game;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        GameWindow gameWindow = new GameWindow();
        Thread mainThread = new Thread(gameWindow);
        mainThread.run();
    }
}
