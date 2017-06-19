package game;

import managers.SceneManager;
import models.MainCharacter;
import scenes.*;
import utils.Utils;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Nhan on 2/28/2017.
 */
public class GameWindow extends Frame implements Runnable, SceneListener{
    private BufferedImage backBuffer;
    private Graphics backGraphic;
    private GameScene gameScene;

    public GameWindow(){
        setVisible(true);
        setSize(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        setResizable(false);
        setTitle("Cute Fighter");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        SceneManager.getInstance().register(this);
        Utils.playSound("res/sound/background_sound.wav", true);
        try {
            gameScene = new MenuScene(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addKeyListener(gameScene);
        this.addMouseListener((MenuScene) gameScene);
        backBuffer = new BufferedImage(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT, BufferedImage.TYPE_INT_BGR);
        backGraphic = backBuffer.getGraphics();
    }

    @Override
    public void run() {
        while (true){
            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameScene.run();
        }

    }

    @Override
    public void update(Graphics g) {
       gameScene.update(backGraphic);
       g.drawImage(backBuffer, 0, 0, null);
    }

    @Override
    public void sceneAction(ActionType actionType) {
        switch (actionType){
            case MENU_SCENE:
                try {
                    attach(new MenuScene(this));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case INSTRUCTION_SCENE:
                try {
                    attach(new InstructionScene(this));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case ABOUT_SCENE:
                try {
                    attach(new AboutScene(this));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case CHOOSE_MAIN:
                try {
                    attach(new ChooseMainScene(this));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case GAME_OVER_SCENE:
                try {
                    attach(new GameOverScene(this));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case PLAY_STAGE_1:
                MainCharacter.mainCharacter.resetMainCharacter();
                attach(new PlayScene(0, ActionType.PLAY_STAGE_1));
                break;
            case PLAY_STAGE_2:
                attach(new PlayScene2());
                break;
            case BACK_TO_STAGE_1:
                MainCharacter.mainCharacter.setX(GameConfig.SCREEN_WIDTH - 100);
                MainCharacter.mainCharacter.setDrawX(GameConfig.SCREEN_WIDTH - 100);
                MainCharacter.mainCharacter.setZ(400);
                MainCharacter.mainCharacter.setDrawY(400);
                attach(new PlayScene(GameConfig.SCREEN_WIDTH - GameConfig.MAP_WIDTH, ActionType.BACK_TO_STAGE_1));
                break;
            case ATTACH:
                attach(gameScene);
                break;
            case DETACH:
                detach();
                break;
        }
    }

    private void detach(){
        if(gameScene != null){
            if(gameScene.getSceneType() == ActionType.MENU_SCENE) {
                removeMouseListener((MenuScene) gameScene);
            } else if(gameScene.getSceneType() == ActionType.CHOOSE_MAIN) {
                removeMouseListener((ChooseMainScene) gameScene);
            } else if (gameScene.getSceneType() == ActionType.ABOUT_SCENE) {
                removeMouseListener((AboutScene) gameScene);
            }else if (gameScene.getSceneType() == ActionType.INSTRUCTION_SCENE) {
                removeMouseListener((InstructionScene) gameScene);
            }else if (gameScene.getSceneType() == ActionType.GAME_OVER_SCENE) {
                removeMouseListener((GameOverScene) gameScene);
            }
            removeKeyListener(gameScene);
        }
    }

    private void attach(GameScene newScene){
        detach();
        gameScene = newScene;
        if(gameScene.getSceneType() == ActionType.MENU_SCENE) {
            addMouseListener((MenuScene) gameScene);
        } else if(gameScene.getSceneType() == ActionType.CHOOSE_MAIN) {
            addMouseListener((ChooseMainScene) gameScene);
        } else if (gameScene.getSceneType() == ActionType.ABOUT_SCENE) {
            addMouseListener((AboutScene)gameScene);
        } else if (gameScene.getSceneType() == ActionType.INSTRUCTION_SCENE) {
            addMouseListener((InstructionScene)gameScene);
        }else if (gameScene.getSceneType() == ActionType.GAME_OVER_SCENE) {
            addMouseListener((GameOverScene)gameScene);
        }
        addKeyListener(gameScene);
    }
}
