package screen;

import java.util.Stack;

/**
 * Created by Admin on 4/27/2017.
 */
public class GameScreenManager {

    private Stack<Screen> screenStack;

    private GameScreenManager() {
        screenStack = new Stack<>();
    }

    public Stack<Screen> getScreenStack() {
        return screenStack;
    }
    public static final GameScreenManager instance = new GameScreenManager();

}
