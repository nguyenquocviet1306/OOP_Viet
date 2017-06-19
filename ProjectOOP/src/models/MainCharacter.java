package models;


import managers.CollisionManager;
import game.GameConfig;

import java.util.Stack;

/**
 * Created by Nhan on 2/28/2017.
 */
public class MainCharacter extends Character{
    private Stack<Integer> stackControlAction;
    private int healthBarWidth = GameConfig.BAR_WIDTH;
    private int manaBarWidth = GameConfig.BAR_WIDTH;
    private int expBarWidth = 0;
    private int mainExp = 0;
    private int mainLevel = 1;

    public static MainCharacter mainCharacter = new MainCharacter(100, 0, 400, GameConfig.GAME_OBJECT_WIDTH,
            GameConfig.GAME_OBJECT_HEIGHT);
    public Stack<Integer> getStackControlAction() {
        return stackControlAction;
    }
    private int maxHealth = 100;
    private int maxMana = 100;
    private MainCharacter(int x, int y, int z, int width, int height){
        super(x, y, z, width, height, 100, 100, 10);
        stackControlAction  = new Stack<>();
        this.setLeft(true);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getMainExp() {
        return mainExp;
    }

    public void setMainExp(int mainExp) {
        this.mainExp = mainExp;
    }

    public void gainExp(int mainExp) {
        this.mainExp += mainExp;
        updateExpBarWidth();
    }

    public int getExpBarWidth() {
        return expBarWidth;
    }

    public int getMainLevel() {
        return mainLevel;
    }

    public void levelUp() {
        this.mainLevel++;
        this.maxHealth = 100 + 20 * mainLevel;
        this.maxMana += 100 + 20 * mainLevel;
        this.setHealth(maxHealth);
        this.setMana(maxMana);
        this.mainExp = GameConfig.MAX_EXP - this.mainExp;
        this.damageUp(1);
        updateExpBarWidth();
        updateHealthBarWidth();
        updateExpBarWidth();
    }

    public void resetMainCharacter(){
        this.isAlive = true;
        this.setCharacterState(CharacterState.STANDING);
        this.mainExp = 0;
        this.mainLevel = 1;
        this.maxHealth = 100;
        this.maxMana = 100;
        this.setDamage(10);
        this.setHealth(maxHealth);
        this.setMana(maxMana);
        updateExpBarWidth();
        updateHealthBarWidth();
        updateExpBarWidth();
    }

    public void updateHealthBarWidth(){
        healthBarWidth = mainCharacter.getHealth() * GameConfig.BAR_WIDTH / maxHealth;
    }
    public void updateManaBarWidth(){
        manaBarWidth = mainCharacter.getMana() * GameConfig.BAR_WIDTH / maxMana;
    }
    public void updateExpBarWidth(){
        expBarWidth = mainCharacter.getMainExp() * GameConfig.BAR_WIDTH / GameConfig.MAX_EXP;
    }

    public int getHealthBarWidth() {
        return healthBarWidth;
    }

    public int getManaBarWidth() {
        return manaBarWidth;
    }

    @Override
    public void walkLeft(){
        super.walkLeft();
        if (this.drawX > GameConfig.MAP_START_X)
            this.drawX -= GameConfig.WALKING_SPEED;
        else
            this.drawX = GameConfig.MAP_START_X;
        this.setX(this.getDrawX());
    }
    @Override
    public void walkRight(){
        super.walkRight();
        if (this.drawX < GameConfig.SCREEN_WIDTH - this.getWidth())
            this.drawX += GameConfig.WALKING_SPEED;
        else
            this.drawX = GameConfig.SCREEN_WIDTH - this.getWidth();
        this.setX(this.getDrawX());
    }
    @Override
    public void runLeft(){
        super.runLeft();
        if (this.drawX > GameConfig.MAP_START_X)
            this.drawX -= GameConfig.RUNNING_SPEED;
        else
            this.drawX = GameConfig.MAP_START_X;
        this.setX(this.getDrawX());
    }
    @Override
    public void runRight(){
        super.runRight();
        if (this.drawX < GameConfig.SCREEN_WIDTH - this.getWidth())
            this.drawX += GameConfig.RUNNING_SPEED;
        else
            this.drawX = GameConfig.SCREEN_WIDTH - this.getWidth();
        this.setX(this.getDrawX());
    }
    @Override
    public void getHit(int damage){
        super.getHit(damage);
        updateHealthBarWidth();
    }

    @Override
    public GameObject getGameObject() {
        return this;
    }

    @Override
    public void onCollide(Collision otherCollision) {
        if (otherCollision instanceof EnemyCharacter) {
            CharacterState characterState = ((Character) otherCollision.getGameObject()).getCharacterState();
            if ( characterState == CharacterState.ATTACKING_NORMAL) {
                if (otherCollision.getGameObject().isAttack() && !this.isInvulnerable()) {
                    this.getHit(((Character)otherCollision).getDamage());
                    if (this.getCharacterState() == CharacterState.STUN_NORMAL_1) {
                        this.setCharacterState(CharacterState.STUN_NORMAL_2);
                    } else if (this.getCharacterState() == CharacterState.STUN_NORMAL_2) {
                        if ((otherCollision.getGameObject()).isLeft())
                            this.setCharacterState(CharacterState.FALL_LEFT);
                        else
                            this.setCharacterState(CharacterState.FALL_RIGHT);
                    } else {
                        this.setCharacterState(CharacterState.STUN_NORMAL_1);
                    }
                }
            }
        }
        else if (otherCollision instanceof EnemySkill){
            if (!this.isInvulnerable()) {
                this.getHit(((CharacterSkill) otherCollision).getDamage());
                if (!otherCollision.getGameObject().isLeft()) {
                    this.setCharacterState(CharacterState.FALL_RIGHT);
                }
                else {
                    this.setCharacterState(CharacterState.FALL_LEFT);
                }
            }
        } else if (otherCollision instanceof ThrowedItem){
            if (((ThrowedItem) otherCollision).getItemSate() == ItemState.ON_GROUND &&
                    this.getCharacterState() == CharacterState.ATTACKING_NORMAL){
                this.setCharacterState(CharacterState.ELEVATING);
                ((ThrowedItem) otherCollision).setItemSate(ItemState.ON_HAND);
                System.out.println("Picked up");
            }
        }
    }
}
