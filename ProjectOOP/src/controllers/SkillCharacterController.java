package controllers;

import game.GameConfig;
import game.ResourceMap;
import models.CharacterSkill;
import models.GameObject;
import scenes.PlayScene;
import utils.Utils;
import views.Animation;
import views.SingleView;

import java.awt.*;

/**
 * Created by Nhan on 3/11/2017.
 */
public class SkillCharacterController extends SingleController {

    private SingleView skillSingleViewRight;
    private SingleView skillSingleViewLeft;
    private Animation skillAnimationRight;
    private Animation skillAnimationLeft;
    public SkillCharacterController(GameObject gameObject) {
        super(gameObject);
        initView();
    }

    private void initView(){
        if (PlayScene.mainType == 1) {
            skillSingleViewRight = new SingleView(Utils.loadImage("res/davis_ball_5.png"));
            skillSingleViewLeft = new SingleView(Utils.flipImage(Utils.loadImage("res/davis_ball_5.png")));
            skillAnimationRight = new Animation(ResourceMap.DAVIS_BALL_FLY, GameConfig.BALL_FLYING_FRAME_RATE);
            skillAnimationLeft = new Animation(Utils.flipImages(ResourceMap.DAVIS_BALL_FLY),
                    GameConfig.BALL_FLYING_FRAME_RATE);
        } else {
            skillSingleViewRight = new SingleView(Utils.loadImage("res/main2/john_ball_ani1 (3).png"));
            skillSingleViewLeft = new SingleView(Utils.flipImage(Utils.loadImage("res/main2/john_ball_ani1 (3).png")));
            skillAnimationRight = new Animation(ResourceMap.JOHN_BALL_FLY, GameConfig.BALL_FLYING_FRAME_RATE);
            skillAnimationLeft = new Animation(Utils.flipImages(ResourceMap.JOHN_BALL_FLY),
                    GameConfig.BALL_FLYING_FRAME_RATE);
        }
    }

    public SkillCharacterController(GameObject gameObject, SingleView view) {
        super(gameObject, view);
    }

    @Override
    public void run() {
        super.run();
        if (!gameObject.isLeft())
            ((CharacterSkill) gameObject).moveRight();
        else
            ((CharacterSkill) gameObject).moveLeft();
    }

    @Override
    public void draw(Graphics g) {
        if(gameObject.isLeft()){
            this.view = skillAnimationLeft;
        } else {
            this.view = skillSingleViewRight;
        }
        if (skillAnimationRight.isAnimationEnd()){
            skillAnimationRight.setAnimationEnd(false);
            if(gameObject.isLeft()){
                this.view = skillSingleViewLeft;
            } else {
                this.view = skillSingleViewRight;
            }
        }
        super.draw(g);
    }
}
