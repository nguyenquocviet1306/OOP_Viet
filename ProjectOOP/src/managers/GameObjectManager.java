package managers;

import controllers.BaseController;
import models.GameObject;
import models.MainCharacter;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Nhan on 5/4/2017.
 */
public class GameObjectManager implements BaseController {
    private static GameObjectManager instance;
    private ArrayList<GameObject> listObject;

    public static GameObjectManager getInstance() {
        if(instance == null)
            instance = new GameObjectManager();
        return instance;
    }

    public void add(GameObject gameObject){
        listObject.add(gameObject);
    }

    public void remove(GameObject gameObject){
        listObject.remove(gameObject);
    }

    public void clear(){
        listObject = new ArrayList<>();
        listObject.add(MainCharacter.mainCharacter);
    }

    public ArrayList<GameObject> getListObject() {
        return listObject;
    }

    private GameObjectManager() {
        listObject = new ArrayList<>();
    }

    @Override
    public void run() {

    }

    @Override
    public void draw(Graphics g) {

    }
}
