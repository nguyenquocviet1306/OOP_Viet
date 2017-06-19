package models;

import game.GameConfig;

/**
 * Created by Nhan on 5/1/2017.
 */
public class ThrowedItem extends Item {
    private static final int SPACE = 18;
    private int damage;

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return this.damage;
    }

    private int hit;

    public int getHit() {
        return this.hit;
    }

    public void setHit(int h) {
        hit = h;
    }

    private int originalXCoord, originalZCoord;

    public void setOriginalXZCoord(int x, int z) {
        this.originalXCoord = x;
        this.originalZCoord = z;
    }

    public int getOriginalXCoord() {
        return originalXCoord;
    }

    public int getOriginalZCoord() {
        return originalZCoord;
    }


    private boolean isBox;

    public boolean isBox() {
        return isBox;
    }

    public void setBox(boolean isBox) {
        this.isBox = isBox;
    }


    public ThrowedItem(int x, int y, int z, int width, int height, int hit, int damage, boolean isBox) {
        super(x, y, z);
        this.setHit(hit);
        this.setDamage(damage);
        this.isBox = isBox;
        this.drawY = 0;
    }

    @Override
    public ThrowedItem getGameObject() {
        return this;
    }

    public void fallDown() {
        if (this.getDrawY() < this.getZ()) {
            this.setDrawY(this.getDrawY() + GameConfig.FALLING_SPEED);
        } else {
            this.setItemSate(ItemState.ON_GROUND);
            this.setDrawY(this.getZ() + SPACE);
        }
    }

    public void pickedUp() {
        this.setX(this.getMainCharacter().getX() - 4);
        this.setZ(this.getMainCharacter().getZ() - 35);
        this.setDrawX(this.getX());
        this.setDrawY(this.getZ());
        this.setOriginalXZCoord(this.getX(), this.getZ());
    }

    public void fallDownFromCharacter() {
        this.setX(this.getMainCharacter().getX() + 4);
        this.setZ(this.getMainCharacter().getZ() + 5);
        this.setDrawX(this.getX() + SPACE);
        this.setDrawY(this.getZ() + SPACE);
        this.setOriginalXZCoord(this.getX(), this.getZ());
    }

    public void throwed(int xCoord, int zCoord) {
        if (this.getZ() < this.getMainCharacter().getZ() + this.getMainCharacter().getHeight()/4 - SPACE) {
            this.setZ(this.getZ() + 3);
            this.setDrawY(this.getZ() + SPACE);
            if (getMainCharacter().isLeft) {
                this.setX(xCoord - (int) (150 * Math.sqrt(10000 - (this.getZ() - zCoord - 100) * (this.getZ() - zCoord -
                        100)) / 100)); // ellipse x^2/a^2 + y^2/b^2 = 1 with a = 150, b= 100
            } else {
                this.setX(xCoord + (int) (150 * Math.sqrt(10000 - (this.getZ() - zCoord - 100) * (this.getZ() - zCoord -
                        100)) / 100)); // ellipse
            }
            this.setDrawX(this.getX());
        } else {
            this.setItemSate(ItemState.ON_GROUND);
            this.setHit(this.getHit() - 1);
            if (this.getHit() < 0) {
                this.setItemSate(ItemState.BROKEN);
            }
        }
    }

    @Override
    public void onCollide(Collision otherCollision) {
        // encounter the enemy
        if (otherCollision instanceof EnemyCharacter) {
            EnemyCharacter enemyCharacter = (EnemyCharacter) otherCollision;
            if (!enemyCharacter.isInvulnerable() && this.getItemSate() != ItemState.ON_GROUND)
                if (enemyCharacter.isLeft())
                    enemyCharacter.setCharacterState(CharacterState.FALL_RIGHT);
                else
                    enemyCharacter.setCharacterState(CharacterState.FALL_LEFT);
            enemyCharacter.setHealth(((EnemyCharacter) otherCollision).getHealth() - this.getDamage());
        }
        // 2 items collide
        if (otherCollision instanceof ThrowedItem) {
            if (((ThrowedItem) otherCollision).getX() < this.getX()) {
                this.setX(this.getX() + 20);
                this.setDrawX(this.getDrawX() + 20);
            } else {
                this.setX(this.getX() - 20);
                this.setDrawX(this.getDrawX() - 20);
            }
        }
    }
}
