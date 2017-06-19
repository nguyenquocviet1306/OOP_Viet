package models;

import managers.CollisionManager;
import managers.SceneManager;
import scenes.ActionType;

/**
 * Created by Admin on 5/5/2017.
 */
public class Door extends GameObject {

    private static final int WIDTH = 80;
    private static final int HEIGHT = 80;
    private int check = 0;
    private ActionType sceneType;

    public Door(int x, int y, int z, ActionType sceneType) {
        super(x, y, z, WIDTH, HEIGHT);
        this.sceneType = sceneType;
        CollisionManager.getInstance().register(this);
    }


    @Override
    public GameObject getGameObject() {
        return this;
    }

    @Override
    public void onCollide(Collision otherCollision) {
        if (otherCollision instanceof MainCharacter) {
            if (check == 0 && sceneType == ActionType.PLAY_STAGE_1) {
                check = 1;
                SceneManager.getInstance().sceneAction(ActionType.PLAY_STAGE_2);
            }
            else if (check == 0 && sceneType == ActionType.PLAY_STAGE_2){
                check = 1;
                SceneManager.getInstance().sceneAction(ActionType.BACK_TO_STAGE_1);
            }
            System.out.println(check + " ");
        }
    }
}
