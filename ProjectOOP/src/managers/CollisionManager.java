package managers;

import models.Collision;
import models.GameObject;
import models.MainCharacter;

import java.util.ArrayList;

/**
 * Created by Nhan on 4/24/2017.
 */
public class CollisionManager {
    private static CollisionManager instance;

    public static CollisionManager getInstance() {
        if (instance == null)
            instance  = new CollisionManager();
        return instance;
    }

    public ArrayList<Collision> collisionsList;
    private CollisionManager(){
        collisionsList = new ArrayList<>();
    }

    public void register(Collision collision){
        collisionsList.add(collision);
    }

    public void unregister(Collision collision){
        collisionsList.remove(collision);
    }

    public void clear(){
        collisionsList = new ArrayList<>();
        collisionsList.add(MainCharacter.mainCharacter);
    }

    public void checkCollide(){
        for (int i = 0; i < collisionsList.size(); i++){
            Collision collisionI = collisionsList.get(i);
            GameObject gameObjectI = collisionI.getGameObject();
            for (int j = i + 1; j < collisionsList.size(); j++){
                Collision collisionJ = collisionsList.get(j);
                GameObject gameObjectJ = collisionJ.getGameObject();
                if (gameObjectI.intersects(gameObjectJ)){
                    collisionI.onCollide(collisionJ);
                    collisionJ.onCollide(collisionI);
                }
            }
        }
    }

}
