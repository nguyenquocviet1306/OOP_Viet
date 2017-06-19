package models;

import managers.GameObjectManager;

/**
 * Created by Nhan on 4/26/2017.
 */
public class Background extends GameObject {
    private CharacterState backgroundState = CharacterState.STANDING;
    public Background(int x, int y, int z) {
        super(x, y, z);
        GameObjectManager.getInstance().remove(this);
    }

    public Background(int x, int y, int z, int width, int height) {
        super(x, y, z, width, height);
        GameObjectManager.getInstance().remove(this);
    }

    public CharacterState getBackgroundState() {
        return backgroundState;
    }

    public void setBackgroundState(CharacterState backgroundState) {
        this.backgroundState = backgroundState;
    }

    @Override
    public GameObject getGameObject() {
        return null;
    }

    @Override
    public void onCollide(Collision otherCollision) {

    }
}
