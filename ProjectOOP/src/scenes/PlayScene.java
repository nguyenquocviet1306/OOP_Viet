package scenes;

import controllers.*;
import game.GameConfig;
import managers.*;
import models.*;
import utils.Utils;
import views.SingleView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Stack;

/**
 * Created by Nhan on 3/7/2017.
 */
public class PlayScene extends GameScene {
    private Stack<Integer> stackControlAction;
    private Stack<Integer> stackCheckPressed;
    private MainCharacter mainCharacter;

    protected BackgroundController backgroundController;
    protected ControllerManager controllerManager;
    protected EnemyManager enemyManager;
    protected ItemManager itemManager;
    protected DoorController doorController;

    private BufferedImage gameOverImage;

    public static int mainType = 1;
    public MainCharacterController mainCharacterController = null;
    public static Background bgScene = new Background(0, 0, 0);
    private int count = 0;

    public PlayScene(int bgDrawX, ActionType actionType) {
        bgScene.setDrawX(bgDrawX);
        this.sceneType = ActionType.PLAY_STAGE_1;
        controllerManager = ControllerManager.instance;
        controllerManager.clear();
        mainCharacterController = new MainCharacterController(MainCharacter.mainCharacter);
        controllerManager.addController(mainCharacterController);
        GameObjectManager.getInstance().clear();
        CollisionManager.getInstance().clear();
        mainCharacter = MainCharacter.mainCharacter;
        stackControlAction = mainCharacter.getStackControlAction();
        stackCheckPressed = new Stack<>();
        BufferedImage backgroundImage = Utils.loadImage("res/background_play_1.png");
        gameOverImage = Utils.loadImage("res/game_over.png");
        backgroundController = new BackgroundController(bgScene, new SingleView(backgroundImage));
        if (actionType == ActionType.PLAY_STAGE_1) {
            doorController = new DoorController(new Door(GameConfig.MAP_WIDTH - GameConfig.GAME_OBJECT_WIDTH,
                    0,
                    400,
                    sceneType));
            enemyManager = new EnemyManager(10, 3, 300, GameConfig.MAP_WIDTH - GameConfig.GAME_OBJECT_WIDTH + 1);
            itemManager = new ItemManager(1, 1, 1,
                    GameConfig.MAP_START_X + 40, GameConfig.MAP_END_X - GameConfig.GAME_OBJECT_WIDTH + 1);
        } else if (actionType == ActionType.BACK_TO_STAGE_1) {
            doorController = new DoorController(new Door(GameConfig.SCREEN_WIDTH - GameConfig.GAME_OBJECT_WIDTH,
                    0,
                    400,
                    sceneType));
            enemyManager = new EnemyManager(10, 3,
                    GameConfig.GAME_OBJECT_WIDTH + GameConfig.SCREEN_WIDTH - GameConfig.MAP_END_X,
                    GameConfig.SCREEN_WIDTH - 200);
            itemManager = new ItemManager(1, 1, 1,
                    GameConfig.GAME_OBJECT_WIDTH + GameConfig.SCREEN_WIDTH - GameConfig.MAP_END_X,
                    GameConfig.SCREEN_WIDTH - 100);
        }
        controllerManager.addController(doorController);
    }

    @Override
    public void update(Graphics g) {
        backgroundController.draw(g);
        controllerManager.draw(g);
        enemyManager.draw(g);
        itemManager.draw(g);

        if (mainCharacter.getCharacterState() == CharacterState.DEAD) {
            g.drawImage(gameOverImage, 100, 100, null);
        }
    }

    private int popStackCount = 0;

    @Override
    public void run() {
        backgroundController.run();
        itemManager.run();
        enemyManager.run();
        if (mainCharacter.getCharacterState() == CharacterState.DEAD) {
            SceneManager.getInstance().sceneAction(ActionType.GAME_OVER_SCENE);
        }

        count++;
        if (count % 3000 == 0) itemManager.addBalloon();
        else if (count % 5000 == 0) itemManager.addStone();
        else if (count % 4000 == 0) itemManager.addBox();

        popStackCount++;
        if (popStackCount == GameConfig.POP_STACK_DURATION) {
            popStackCount = 0;
            if (!stackControlAction.empty())
                stackControlAction.pop();
        }
        if (stackControlAction.size() > 1) {
            int a = stackControlAction.pop();
            int b = stackControlAction.pop();
            if (a == KeyEvent.VK_A && b == KeyEvent.VK_A) {
                if (mainCharacter.getCharacterState() == CharacterState.ELEVATING
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_LEFT
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_RIGHT
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_UP
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_DOWN
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_LEFT
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_RIGHT
                        ) {
                    mainCharacter.setCharacterState(CharacterState.ELEVATING_RUNNING_LEFT);
                } else {
                    mainCharacter.setCharacterState(CharacterState.RUNNING_LEFT);
                }
            } else if (a == KeyEvent.VK_D && b == KeyEvent.VK_D) {
                if (mainCharacter.getCharacterState() == CharacterState.ELEVATING
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_LEFT
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_RIGHT
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_UP
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_DOWN
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_LEFT
                        || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_RIGHT
                        ) {
                    mainCharacter.setCharacterState(CharacterState.ELEVATING_RUNNING_RIGHT);
                } else {
                    mainCharacter.setCharacterState(CharacterState.RUNNING_RIGHT);
                }
            }
        }
        controllerManager.run();
        CollisionManager.getInstance().checkCollide();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private void addKeyCodeIntoStack(Integer keyEvent) {
        stackControlAction.add(keyEvent);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!stackCheckPressed.contains(e.getKeyCode())) {
            stackCheckPressed.add(e.getKeyCode());
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    addKeyCodeIntoStack(KeyEvent.VK_W);
                    if (mainCharacter.getCharacterState() == CharacterState.ELEVATING
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_RIGHT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_UP
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_DOWN
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_RIGHT
                            ) {
                        mainCharacter.setCharacterState(CharacterState.ELEVATING_WALKING_UP);
                    } else {
                        mainCharacter.setCharacterState(CharacterState.WALKING_UP);
                    }
                    break;
                case KeyEvent.VK_S:
                    addKeyCodeIntoStack(KeyEvent.VK_S);
                    if (mainCharacter.getCharacterState() == CharacterState.ELEVATING
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_RIGHT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_UP
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_DOWN
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_RIGHT
                            ) {
                        mainCharacter.setCharacterState(CharacterState.ELEVATING_WALKING_DOWN);
                    } else {
                        mainCharacter.setCharacterState(CharacterState.WALKING_DOWN);
                    }
                    break;
                case KeyEvent.VK_A:
                    addKeyCodeIntoStack(KeyEvent.VK_A);
                    if (mainCharacter.getCharacterState() == CharacterState.ELEVATING
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_RIGHT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_UP
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_DOWN
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_RIGHT
                            ) {
                        mainCharacter.setCharacterState(CharacterState.ELEVATING_WALKING_LEFT);
                    } else {
                        mainCharacter.setCharacterState(CharacterState.WALKING_LEFT);
                    }
                    mainCharacter.setLeft(true);
                    break;
                case KeyEvent.VK_D:
                    addKeyCodeIntoStack(KeyEvent.VK_D);
                    if (mainCharacter.getCharacterState() == CharacterState.ELEVATING
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_RIGHT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_UP
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_DOWN
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_RIGHT
                            ) {
                        mainCharacter.setCharacterState(CharacterState.ELEVATING_WALKING_RIGHT);
                    } else {
                        mainCharacter.setCharacterState(CharacterState.WALKING_RIGHT);
                    }
                    mainCharacter.setLeft(false);
                    break;
                case KeyEvent.VK_J:
                    addKeyCodeIntoStack(KeyEvent.VK_J);
                    if (mainCharacter.getCharacterState() == CharacterState.ELEVATING
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_RIGHT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_UP
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_DOWN
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_LEFT
                            || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_RIGHT
                            ) {
                        mainCharacter.setCharacterState(CharacterState.THROWING);
                        Utils.playSound("res/sound/throwing.wav", false);
                    } else {
                        mainCharacter.setCharacterState(CharacterState.ATTACKING_NORMAL);
                        Utils.playSound("res/sound/hit.wav", false);
                    }
                    break;
                case KeyEvent.VK_K:
                    addKeyCodeIntoStack(KeyEvent.VK_K);
                    if (mainCharacter.getMana() > 0) {
                        Utils.playSound("res/sound/shoot.wav", false);
                        mainCharacter.setCharacterState(CharacterState.SKILL_SHOOTING);
                    }

                    break;
                case KeyEvent.VK_L:
                    addKeyCodeIntoStack(KeyEvent.VK_L);
                    mainCharacter.setCharacterState(CharacterState.JUMPING);
                    Utils.playSound("res/sound/jump.wav", false);
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (mainCharacter.getCharacterState() != CharacterState.RUNNING_LEFT
                && mainCharacter.getCharacterState() != CharacterState.RUNNING_RIGHT
                && mainCharacter.getCharacterState() != CharacterState.ATTACKING_NORMAL
                && mainCharacter.getCharacterState() != CharacterState.SKILL_SHOOTING
                && mainCharacter.getCharacterState() != CharacterState.JUMPING
                && mainCharacter.getCharacterState() != CharacterState.STUN_NORMAL_1
                && mainCharacter.getCharacterState() != CharacterState.STUN_NORMAL_2
                && mainCharacter.getCharacterState() != CharacterState.FALL_LEFT
                && mainCharacter.getCharacterState() != CharacterState.FALL_RIGHT
                && mainCharacter.getCharacterState() != CharacterState.DEAD
                && mainCharacter.getCharacterState() != CharacterState.ELEVATING
                && mainCharacter.getCharacterState() != CharacterState.ELEVATING_WALKING_UP
                && mainCharacter.getCharacterState() != CharacterState.ELEVATING_WALKING_DOWN
                && mainCharacter.getCharacterState() != CharacterState.ELEVATING_WALKING_LEFT
                && mainCharacter.getCharacterState() != CharacterState.ELEVATING_WALKING_RIGHT
                && mainCharacter.getCharacterState() != CharacterState.ELEVATING_RUNNING_LEFT
                && mainCharacter.getCharacterState() != CharacterState.ELEVATING_RUNNING_RIGHT) {
            mainCharacter.setCharacterState(CharacterState.STANDING);
        } else if (mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_UP
                || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_DOWN
                || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_LEFT
                || mainCharacter.getCharacterState() == CharacterState.ELEVATING_WALKING_RIGHT
                || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_LEFT
                || mainCharacter.getCharacterState() == CharacterState.ELEVATING_RUNNING_RIGHT
                ) {
            mainCharacter.setCharacterState(CharacterState.ELEVATING);
        }
        stackCheckPressed = new Stack<>();
    }
}
