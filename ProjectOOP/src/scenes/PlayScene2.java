package scenes;

import controllers.BackgroundController;
import controllers.DoorController;
import controllers.MainCharacterController;
import game.GameConfig;
import managers.CollisionManager;
import managers.EnemyManager;
import managers.GameObjectManager;
import managers.ItemManager;
import models.Character;
import models.Door;
import models.GameObject;
import models.MainCharacter;
import sun.applet.Main;
import utils.Utils;
import views.SingleView;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Nhan on 5/1/2017.
 */
public class PlayScene2 extends PlayScene {
    public PlayScene2(){
        super(0, ActionType.PLAY_STAGE_2);
        this.sceneType = ActionType.PLAY_STAGE_2;
        GameObjectManager.getInstance().clear();
        CollisionManager.getInstance().clear();
        MainCharacter.mainCharacter.setX(100);
        MainCharacter.mainCharacter.setDrawX(100);
        MainCharacter.mainCharacter.setZ(400);
        MainCharacter.mainCharacter.setDrawY(400);
        MainCharacterController mainCharacterController = new MainCharacterController(MainCharacter.mainCharacter);
        Dimension dimension = new Dimension(2400, 600);
        BufferedImage backgroundImage = Utils.setSize(Utils.loadImage("res/background_2.png"), dimension);
        backgroundController = new BackgroundController(bgScene, new SingleView(backgroundImage));
        controllerManager.clear();
        controllerManager.addController(mainCharacterController);
        doorController = new DoorController(new Door(GameConfig.MAP_START_X , 0, 400, sceneType));
        controllerManager.addController(doorController);
        enemyManager = new EnemyManager(7, 7, 300, GameConfig.MAP_WIDTH - GameConfig.GAME_OBJECT_WIDTH + 1);
        itemManager = new ItemManager(1, 1, 1, GameConfig.MAP_START_X + 40,
                GameConfig.MAP_END_X - GameConfig.GAME_OBJECT_WIDTH + 1);
    }
}
