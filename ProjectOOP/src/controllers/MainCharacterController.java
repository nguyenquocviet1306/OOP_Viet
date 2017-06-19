package controllers;

import game.GameConfig;
import game.ResourceMap;
import managers.ControllerManager;
import managers.SceneManager;
import models.*;
import scenes.ActionType;
import scenes.PlayScene;
import utils.Utils;
import views.Animation;
import views.SingleView;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Nhan on 3/7/2017.
 */
public class MainCharacterController extends CharacterController {
    private static final int BAR_X = 135;
    private static final int STRING_X = 110;
    private MainCharacter mainCharacter = (MainCharacter) this.gameObject;
    private int countTimeFall = 0;

    private Animation animationWalkingLeft;
    private Animation animationWalkingRight;
    private Animation animationRunningLeft;
    private Animation animationRunningRight;
    private Animation animationStandingRight;
    private Animation animationStandingLeft;
    private Animation animationNormalAttackRight;
    private Animation animationNormalAttackLeft;
    private Animation animationJumping;
    private Animation animationShootingRight;
    private Animation animationShootingLeft;
    private Animation animationFalling;
    private SingleView singleViewJumpingLeft;
    private SingleView singleViewJumpingRight;
    private SingleView singleViewStunNormal1;
    private SingleView singleViewStunNormal2;
    private SingleView singleViewFalling;
    private Animation animationElevatingStandingRight;
    private Animation animationElevatingStandingLeft;
    private Animation animationThrowingRight;
    private Animation animationThrowingLeft;
    private Animation animationElevatingWalkingRight;
    private Animation animationElevatingWalkingLeft;
    private Animation animationElevatingRunningRight;
    private Animation animationElevatingRunningLeft;

    private BufferedImage characterIconImage;

    private int countTimeRegenMana = 0;

    public MainCharacterController(GameObject gameObject) {
        super(gameObject);
        initView();
    }

    private void initView(){
        if (PlayScene.mainType == 1) {
            animationWalkingLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_WALKING),
                    GameConfig.WALKING_FRAME_RATE);
            animationWalkingRight = new Animation(Utils.flipImages(Utils.flipImages(ResourceMap.DAVIS_WALKING)),
                    GameConfig.WALKING_FRAME_RATE);
            animationRunningLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_RUNNING),
                    GameConfig.RUNNING_FRAME_RATE);
            animationRunningRight = new Animation(ResourceMap.DAVIS_RUNNING,
                    GameConfig.RUNNING_FRAME_RATE);
            animationStandingRight = new Animation(ResourceMap.DAVIS_STANDING,
                    GameConfig.STANDING_FRAME_RATE);
            animationStandingLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_STANDING),
                    GameConfig.STANDING_FRAME_RATE);
            animationNormalAttackRight = new Animation(ResourceMap.DAVIS_NORMAL_ATTACK_2,
                    GameConfig.ATTACKING_FRAME_RATE);
            animationNormalAttackLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_NORMAL_ATTACK_2),
                    GameConfig.ATTACKING_FRAME_RATE);
            animationJumping = new Animation(ResourceMap.DAVIS_JUMPING,
                    GameConfig.JUMPING_FRAME_RATE);
            animationShootingRight = new Animation(ResourceMap.DAVIS_SHOOTING,
                    GameConfig.ATTACKING_FRAME_RATE);
            animationShootingLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_SHOOTING),
                    GameConfig.ATTACKING_FRAME_RATE);
            animationFalling = new Animation("res/davis_falling_behind.png", GameConfig.FALLING_FRAME_RATE, 73, 77, 0);
            singleViewJumpingLeft = new SingleView(Utils.flipImage(Utils.loadImage("res/davis_jumping_01.png")));
            singleViewJumpingRight = new SingleView(Utils.loadImage("res/davis_jumping_01.png"));
            singleViewStunNormal1 = new SingleView(Utils.loadImage("res/davis_stun_normal_1_behind.png"));
            singleViewStunNormal2 = new SingleView(Utils.loadImage("res/davis_stun_normal_2_behind.png"));
            singleViewFalling = new SingleView(animationFalling.getSubImage(animationFalling.getNumberOfFrame()));
            animationElevatingStandingRight = new Animation(ResourceMap.DAVIS_ELEVATING,
                    GameConfig.STANDING_FRAME_RATE);
            animationElevatingStandingLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_ELEVATING),
                    GameConfig.STANDING_FRAME_RATE);
            animationThrowingRight = new Animation(ResourceMap.DAVIS_THROWING, GameConfig.STANDING_FRAME_RATE);
            animationThrowingLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_THROWING),
                    GameConfig.STANDING_FRAME_RATE);
            animationElevatingWalkingRight = new Animation(ResourceMap.DAVIS_ELEVATING_WALKING,
                    GameConfig.RUNNING_FRAME_RATE);
            animationElevatingWalkingLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_ELEVATING_WALKING),
                    GameConfig.RUNNING_FRAME_RATE);
            animationElevatingRunningRight = new Animation(ResourceMap.DAVIS_ELEVATING_WALKING,
                    GameConfig.WALKING_FRAME_RATE);
            animationElevatingRunningLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_ELEVATING_WALKING),
                    GameConfig.WALKING_FRAME_RATE);
            Dimension dimension = new Dimension(40, 40);
            characterIconImage = Utils.setSize(Utils.loadImage("res/davis_shooting_0.png"), dimension);
            mainCharacter.setDamage(10);
            mainCharacter.setHealth(100);
            mainCharacter.setMaxHealth(100);
            mainCharacter.setMana(50);
            mainCharacter.setMaxMana(50);
            System.out.println("player 1");
        } else {
            animationWalkingLeft = new Animation(Utils.flipImages(ResourceMap.JOHN_WALKING),
                    GameConfig.WALKING_FRAME_RATE);
            animationWalkingRight = new Animation(Utils.flipImages(Utils.flipImages(ResourceMap.JOHN_WALKING)),
                    GameConfig.WALKING_FRAME_RATE);
            animationRunningLeft = new Animation(Utils.flipImages(ResourceMap.JOHN_RUNNING),
                    GameConfig.RUNNING_FRAME_RATE);
            animationRunningRight = new Animation(ResourceMap.JOHN_RUNNING,
                    GameConfig.RUNNING_FRAME_RATE);
            animationStandingRight = new Animation(ResourceMap.JOHN_STANDING,
                    GameConfig.STANDING_FRAME_RATE);
            animationStandingLeft = new Animation(Utils.flipImages(ResourceMap.JOHN_STANDING),
                    GameConfig.STANDING_FRAME_RATE);
            animationNormalAttackRight = new Animation(ResourceMap.JOHN_NORMAL_ATTACK_2,
                    GameConfig.ATTACKING_FRAME_RATE);
            animationNormalAttackLeft = new Animation(Utils.flipImages(ResourceMap.JOHN_NORMAL_ATTACK_2),
                    GameConfig.ATTACKING_FRAME_RATE);
            animationJumping = new Animation(ResourceMap.JOHN_JUMPING,
                    GameConfig.JUMPING_FRAME_RATE);
            animationShootingRight = new Animation(ResourceMap.JOHN_SHOOTING,
                    GameConfig.ATTACKING_FRAME_RATE);
            animationShootingLeft = new Animation(Utils.flipImages(ResourceMap.JOHN_SHOOTING),
                    GameConfig.ATTACKING_FRAME_RATE);
            animationShootingRight = new Animation(ResourceMap.JOHN_SHOOTING,
                    GameConfig.ATTACKING_FRAME_RATE);
            animationShootingLeft = new Animation(Utils.flipImages(ResourceMap.JOHN_SHOOTING),
                    GameConfig.ATTACKING_FRAME_RATE);
            animationFalling = new Animation("res/main2/john_falling_behind.png", GameConfig.FALLING_FRAME_RATE,
                    73, 76, 0);
            singleViewJumpingLeft = new SingleView(Utils.flipImage(Utils.loadImage("res/main2/john_jumping (3).png")));
            singleViewJumpingRight = new SingleView(Utils.loadImage("res/main2/john_jumping (3).png"));
            singleViewStunNormal1 = new SingleView(Utils.loadImage("res/main2/john_stun_normal_0.png"));
            singleViewStunNormal2 = new SingleView(Utils.loadImage("res/main2/john_stun_normal_0.png"));
            singleViewFalling = new SingleView(animationFalling.getSubImage(animationFalling.getNumberOfFrame()));
            animationElevatingStandingRight = new Animation(ResourceMap.JOHN_ELEVATING,
                    GameConfig.STANDING_FRAME_RATE);
            animationElevatingStandingLeft = new Animation(Utils.flipImages(ResourceMap.JOHN_ELEVATING),
                    GameConfig.STANDING_FRAME_RATE);
            animationThrowingRight = new Animation(ResourceMap.JOHN_THROWING, GameConfig.STANDING_FRAME_RATE);
            animationThrowingLeft = new Animation(Utils.flipImages(ResourceMap.JOHN_THROWING),
                    GameConfig.STANDING_FRAME_RATE);
            animationElevatingWalkingRight = new Animation(ResourceMap.JOHN_ELEVATING_WALKING,
                    GameConfig.RUNNING_FRAME_RATE);
            animationElevatingWalkingLeft = new Animation(Utils.flipImages(ResourceMap.JOHN_ELEVATING_WALKING),
                    GameConfig.RUNNING_FRAME_RATE);
            animationElevatingRunningRight = new Animation(ResourceMap.JOHN_ELEVATING_WALKING,
                    GameConfig.WALKING_FRAME_RATE);
            animationElevatingRunningLeft = new Animation(Utils.flipImages(ResourceMap.JOHN_ELEVATING_WALKING),
                    GameConfig.WALKING_FRAME_RATE);
            Dimension dimension = new Dimension(40, 40);
            characterIconImage = Utils.setSize(Utils.loadImage("res/main2/john_normal_attack1 (4).png"), dimension);
            mainCharacter.setDamage(15);
            mainCharacter.setHealth(80);
            mainCharacter.setMaxHealth(80);
            mainCharacter.setMana(60);
            mainCharacter.setMaxMana(60);
            System.out.println("player 2");
        }
    }
    @Override
    public void run() {
        super.run();
//        System.out.println("X : " + mainCharacter.getX() + " Z: " + mainCharacter.getZ());
        switch (mainCharacter.getCharacterState()) {
            case  SKILL_SHOOTING:
                SceneManager.getInstance().sceneAction(ActionType.DETACH);
                if (animationShootingRight.isAnimationEnd() || animationShootingLeft.isAnimationEnd()) {
                    animationShootingRight.setAnimationEnd(false);
                    animationShootingLeft.setAnimationEnd(false);
                    SkillCharacterController skillController = new SkillCharacterController(
                            new MainSkill(mainCharacter.getX() + mainCharacter.getHeight(),
                                    mainCharacter.getY(),
                                    mainCharacter.getZ(),
                                    mainCharacter.getDrawX(),
                                    GameConfig.GAME_OBJECT_WIDTH,
                                    GameConfig.GAME_OBJECT_HEIGHT));
                    skillController.gameObject.setDrawY(mainCharacter.getZ() +
                            (mainCharacter.getHeight() - CharacterSkill.SKILL_HEIGHT) / 2);
                    skillController.gameObject.setLeft(mainCharacter.isLeft());
                    ControllerManager.getSkillList().add(skillController);
                    mainCharacter.setManaChange(-10);
                    mainCharacter.updateManaBarWidth();
                    mainCharacter.setCharacterState(CharacterState.STANDING);
                }
                break;
            case WALKING_LEFT:
                if (mainCharacter.getDrawX() <= GameConfig.SCREEN_WIDTH/2) {
                    PlayScene.bgScene.setBackgroundState(CharacterState.WALKING_LEFT);
                }
                break;
            case WALKING_RIGHT:
                if (mainCharacter.getDrawX() >= GameConfig.SCREEN_WIDTH/2 - GameConfig.GAME_OBJECT_WIDTH) {
                    PlayScene.bgScene.setBackgroundState(CharacterState.WALKING_RIGHT);
                }
                break;
            case RUNNING_LEFT:
                if (mainCharacter.getDrawX() <= GameConfig.SCREEN_WIDTH/2) {
                    PlayScene.bgScene.setBackgroundState(CharacterState.RUNNING_LEFT);
                }
                break;
            case RUNNING_RIGHT:
                if (mainCharacter.getDrawX() >= GameConfig.SCREEN_WIDTH/2 - GameConfig.GAME_OBJECT_WIDTH) {
                    PlayScene.bgScene.setBackgroundState(CharacterState.RUNNING_RIGHT);
                }
                break;
            case STUN_NORMAL_1:
            case STUN_NORMAL_2:
            case FALL_RIGHT:
            case FALL_LEFT:
                SceneManager.getInstance().sceneAction(ActionType.DETACH);
                break;
            case STANDING:
                SceneManager.getInstance().sceneAction(ActionType.ATTACH);
                break;
            case ELEVATING_WALKING_LEFT:
                if (mainCharacter.getDrawX() <= GameConfig.SCREEN_WIDTH/2) {
                    PlayScene.bgScene.setBackgroundState(CharacterState.WALKING_LEFT);
                }
                mainCharacter.walkLeft();
                break;
            case ELEVATING_WALKING_RIGHT:
                if (mainCharacter.getDrawX() >= GameConfig.SCREEN_WIDTH/2 - GameConfig.GAME_OBJECT_WIDTH) {
                    PlayScene.bgScene.setBackgroundState(CharacterState.WALKING_RIGHT);
                }
                mainCharacter.walkRight();
                break;
            case ELEVATING_WALKING_UP:
                mainCharacter.walkUp();
                break;
            case ELEVATING_WALKING_DOWN:
                mainCharacter.walkDown();
                break;
            case ELEVATING_RUNNING_LEFT:
                if (mainCharacter.getDrawX() <= GameConfig.SCREEN_WIDTH/2) {
                    PlayScene.bgScene.setBackgroundState(CharacterState.RUNNING_LEFT);
                }
                mainCharacter.runLeft();
                break;
            case ELEVATING_RUNNING_RIGHT:
                if (mainCharacter.getDrawX() >= GameConfig.SCREEN_WIDTH/2 - GameConfig.GAME_OBJECT_WIDTH) {
                    PlayScene.bgScene.setBackgroundState(CharacterState.RUNNING_RIGHT);
                }
                mainCharacter.runRight();
                break;
            case ELEVATING:
                break;
        }
        if (mainCharacter.getCharacterState() != CharacterState.WALKING_LEFT &&
                mainCharacter.getCharacterState() != CharacterState.WALKING_RIGHT &&
                mainCharacter.getCharacterState()  != CharacterState.RUNNING_LEFT &&
                mainCharacter.getCharacterState()  != CharacterState.RUNNING_RIGHT &&
                mainCharacter.getCharacterState()  != CharacterState.ELEVATING_WALKING_LEFT &&
                mainCharacter.getCharacterState()  != CharacterState.ELEVATING_WALKING_RIGHT &&
                mainCharacter.getCharacterState()  != CharacterState.ELEVATING_RUNNING_LEFT &&
                mainCharacter.getCharacterState()  != CharacterState.ELEVATING_RUNNING_RIGHT
                ){
            PlayScene.bgScene.setBackgroundState(CharacterState.STANDING);
        }
        if(mainCharacter.getMainExp() >= GameConfig.MAX_EXP){
            mainCharacter.levelUp();
        }

        countTimeRegenMana++;
        if(countTimeRegenMana > GameConfig.REGEN_MANA_DURATION) {
            countTimeRegenMana = 0;
            if (mainCharacter.getMana() < mainCharacter.getMaxMana()) {
                mainCharacter.setManaChange(1);
                mainCharacter.updateManaBarWidth();
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        //show level
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        g.setColor(Color.BLACK);
        g.drawString("LEVEL: " + mainCharacter.getMainLevel(), 10, 50);

        //icon
        g.drawImage(characterIconImage, 20, 55, null);

        //Health bar
        g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
        g.setColor(Color.BLACK);
        g.drawString("HP", STRING_X, 50);
        g.setColor(Color.RED);
        g.fillRect(BAR_X, 40, mainCharacter.getHealthBarWidth(), GameConfig.BAR_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(BAR_X, 40, GameConfig.BAR_WIDTH, GameConfig.BAR_HEIGHT);

        //Mana bar
        g.drawString("MP", STRING_X, 75);
        g.setColor(Color.BLUE);
        g.fillRect(BAR_X, 65, mainCharacter.getManaBarWidth(), GameConfig.BAR_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(BAR_X, 65, GameConfig.BAR_WIDTH, GameConfig.BAR_HEIGHT);

        //EXP bar
        g.drawString("EXP", STRING_X, 95);
        g.setColor(Color.CYAN);
        g.fillRect(BAR_X, 90, mainCharacter.getExpBarWidth(), GameConfig.EXP_BAR_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(BAR_X, 90, GameConfig.BAR_WIDTH, GameConfig.EXP_BAR_HEIGHT);
        switch (mainCharacter.getCharacterState()){
            case STANDING:
                resetAnimation();
                if (mainCharacter.isLeft())
                    this.view = animationStandingLeft;
                else
                    this.view = animationStandingRight;
                break;
            case WALKING_LEFT:
                this.view = animationWalkingLeft;
                break;
            case WALKING_RIGHT:
                this.view = animationWalkingRight;
                break;
            case WALKING_DOWN:
                if (mainCharacter.isLeft())
                    this.view = animationWalkingLeft;
                else
                    this.view = animationWalkingRight;
                break;
            case WALKING_UP:
                if (mainCharacter.isLeft())
                    this.view = animationWalkingLeft;
                else
                    this.view = animationWalkingRight;
                break;
            case RUNNING_LEFT:
                this.view = animationRunningLeft;
                break;
            case RUNNING_RIGHT:
                this.view = animationRunningRight;
                break;
            case ATTACKING_NORMAL:
                if (mainCharacter.isLeft())
                    this.view = animationNormalAttackLeft;
                else
                    this.view = animationNormalAttackRight;
                if (animationNormalAttackRight.isAnimationEnd() || animationNormalAttackRight.isAnimationEnd()){
                    animationNormalAttackRight.setAnimationEnd(false);
                    animationNormalAttackLeft.setAnimationEnd(false);
                    mainCharacter.setCharacterState(CharacterState.STANDING);
                }
                break;
            case JUMPING:
                if (mainCharacter.isLeft())
                    this.view = singleViewJumpingLeft;
                else
                    this.view = singleViewJumpingRight;
                break;
            case SKILL_SHOOTING:
                if(mainCharacter.isLeft())
                    this.view = animationShootingLeft;
                else
                    this.view = animationShootingRight;
                break;
            case STUN_NORMAL_1:
                this.view = singleViewStunNormal1;
                break;
            case STUN_NORMAL_2:
                this.view = singleViewStunNormal2;
                break;
            case FALL_LEFT:
                countTimeFall++;
                if (countTimeFall == 1){
                    this.view = animationFalling;
                }
                if (countTimeFall > TIME_FALL){
                    countTimeFall = 0;
                    animationFalling.setAnimationEnd(false);
                }
                if (animationFalling.isAnimationEnd()){
                    this.view = singleViewFalling;
                } else {
                        mainCharacter.walkLeft();
                }
                break;
            case FALL_RIGHT:
                countTimeFall++;
                if (countTimeFall == 1){
                    this.view = animationFalling;
                }
                if (countTimeFall > TIME_FALL){
                    countTimeFall = 0;
                    animationFalling.setAnimationEnd(false);
                }
                if (animationFalling.isAnimationEnd()){
                    this.view = singleViewFalling;
                } else {
                    mainCharacter.walkRight();
                }
                break;
            case DEAD:
                this.view = singleViewFalling;
                break;
            case ELEVATING:
                if (mainCharacter.isLeft())
                    this.view = animationElevatingStandingLeft;
                else
                    this.view = animationElevatingStandingRight;
                break;
            case THROWING:
                if (mainCharacter.isLeft())
                    this.view = animationThrowingLeft;
                else
                    this.view = animationThrowingRight;
                break;
            case ELEVATING_WALKING_LEFT:
                this.view = animationElevatingWalkingLeft;
                break;
            case ELEVATING_WALKING_RIGHT:
                this.view = animationElevatingWalkingRight;
                break;
            case ELEVATING_WALKING_DOWN:
                if (mainCharacter.isLeft())
                    this.view = animationElevatingWalkingLeft;
                else
                    this.view = animationElevatingWalkingRight;
                break;
            case ELEVATING_WALKING_UP:
                if (mainCharacter.isLeft())
                    this.view = animationElevatingWalkingLeft;
                else
                    this.view = animationElevatingWalkingRight;
                break;
            case ELEVATING_RUNNING_LEFT:
                this.view = animationElevatingRunningLeft;
                break;
            case ELEVATING_RUNNING_RIGHT:
                this.view = animationElevatingRunningRight;
                break;
            default:
                resetAnimation();
                break;
        }
        super.draw(g);
    }

    public void resetAnimation(){
        animationNormalAttackRight.resetAnimation(0);
        animationNormalAttackLeft.resetAnimation(0);
        animationWalkingLeft.resetAnimation(0);
        animationWalkingRight.resetAnimation(0);
        animationJumping.resetAnimation(0);
        animationFalling.resetAnimation(0);
    }
}
