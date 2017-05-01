package screen;

import controllers.BulletRobotController;
import controllers.MainCharacterController;
import controllers.RobotController;
import game.GameConfig;
import game.GameWindow;
import managers.ControllerManager;
import models.*;
import utils.Utils;

import java.awt.*;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Admin on 4/27/2017.
 */
public class PlayScreen extends Screen {
    private GameWindow gameWindow;
    private MainCharacterController mainCharacterController;
    private Stack<Integer> stackControlAction;
    private Stack<Integer> stackCheckPressed;
    private ArrayList<Integer> arrayAction = new ArrayList<>();
    private MainCharacter mainCharacter;

    private RobotController robotController;
    private models.Robot robot;

    private RobotController robotController2;
    private models.Robot robot2;

    private RobotBullet robotBullet;
    private BulletRobotController bulletRobotController;

    private Image backgroundImage;
    private ControllerManager controllerManager;

    public PlayScreen(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        mainCharacterController = new MainCharacterController(new MainCharacter(0, 300, 0, 40, 80));
        mainCharacter = mainCharacterController.getMainCharacter();

        robotController = new RobotController(new models.Robot(600,300,0, 80, 100));
        robot = robotController.getRobotController();
        robotController.setMainCharacter(mainCharacter) ;

        stackControlAction = mainCharacter.getStackControlAction();
        stackCheckPressed = new Stack<>();
        backgroundImage = Utils.loadImage("res/background.png");
        controllerManager = new ControllerManager();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
        mainCharacterController.draw(g);
        robotController.draw(g);

        controllerManager.draw(g);
    }

    private int popStackCount = 0;

    @Override
    public void run() {
        System.out.println("run in playscreen");
        robotController.run();

        mainCharacterController.run();

        popStackCount++;
        if (popStackCount == GameConfig.POP_STACK_TIME){
            popStackCount = 0;
            if (!stackControlAction.empty())
                stackControlAction.pop();
        }
        if (stackControlAction.size() > 2) {
            int a = stackControlAction.pop();
            int b = stackControlAction.pop();
            int c = stackControlAction.pop();
            System.out.println(a + " " + b + " " + c);
            System.out.println(KeyEvent.VK_K + "---" + KeyEvent.VK_RIGHT + "---" + KeyEvent.VK_J);
            if (a == KeyEvent.VK_J &&
                    b == KeyEvent.VK_RIGHT &&
                    c == KeyEvent.VK_K) {
                mainCharacter.setCharacterState(CharacterState.SKILL_SHOOTING);
                System.out.println(mainCharacter.getCharacterState() + "");
            } else if (a == KeyEvent.VK_J &&
                    b == KeyEvent.VK_J &&
                    c == KeyEvent.VK_J) {
                mainCharacter.setCharacterState(CharacterState.ATTACKING_HARD);
                System.out.println(mainCharacter.getCharacterState() + "");
            } else if (a == KeyEvent.VK_LEFT &&
                    b == KeyEvent.VK_LEFT) {
                mainCharacter.setCharacterState(CharacterState.RUNNING_LEFT);
                System.out.println(mainCharacter.getCharacterState() + "");
            } else if (a == KeyEvent.VK_RIGHT &&
                    b == KeyEvent.VK_RIGHT) {
                mainCharacter.setCharacterState(CharacterState.RUNNING_RIGHT);
                System.out.println(mainCharacter.getCharacterState() + "");
            }
        }
        controllerManager.run();
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }
    private void addKeyCodeIntoStack(Integer keyEvent){

        stackControlAction.add(keyEvent);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Bam phim nhan vat");
        if (!stackCheckPressed.contains(e.getKeyCode())) {
            stackCheckPressed.add(e.getKeyCode());
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    addKeyCodeIntoStack(KeyEvent.VK_UP);
                    mainCharacter.setCharacterState(CharacterState.WALKING_UP);
                    break;
                case KeyEvent.VK_DOWN:
                    addKeyCodeIntoStack(KeyEvent.VK_DOWN);
                    mainCharacter.setCharacterState(CharacterState.WALKING_DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    addKeyCodeIntoStack(KeyEvent.VK_LEFT);
                    mainCharacter.setCharacterState(CharacterState.WALKING_LEFT);
                    mainCharacter.setLeft(true);
                    break;
                case KeyEvent.VK_RIGHT:
                    addKeyCodeIntoStack(KeyEvent.VK_RIGHT);
                    mainCharacter.setCharacterState(CharacterState.WALKING_RIGHT);
                    mainCharacter.setLeft(false);
                    break;
                case KeyEvent.VK_J:
                    addKeyCodeIntoStack(KeyEvent.VK_J);
                    mainCharacter.setCharacterState(CharacterState.ATTACKING_NORMAL);
                    break;
                case KeyEvent.VK_K:
                    addKeyCodeIntoStack(KeyEvent.VK_K);
                    mainCharacter.setCharacterState(CharacterState.DEFENDING);
                    break;
                case KeyEvent.VK_L:
                    addKeyCodeIntoStack(KeyEvent.VK_L);
                    mainCharacter.setCharacterState(CharacterState.JUMPING);
                    break;
                default:
//                mainCharacter.setCharacterState(CharacterState.STANDING);
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (mainCharacter.getCharacterState() != CharacterState.RUNNING_LEFT &&
                mainCharacter.getCharacterState() != CharacterState.RUNNING_RIGHT){
            mainCharacter.setCharacterState(CharacterState.STANDING);
        }
        stackCheckPressed = new Stack<>();
    }

}
