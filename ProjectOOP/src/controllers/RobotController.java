package controllers;

import game.ResourceMap;
import models.*;
import models.Robot;
import views.Animation;
import views.SingleView;

import java.awt.*;

/**
 * Created by Admin on 3/25/2017.
 */
public class RobotController extends SingleController {
    public MainCharacter mainCharacter;
    private MainCharacterController mainCharacterController;
    private Robot robot = (Robot) this.gameObject;
    private SingleView robotImage = new SingleView("res/robot_left_3.png");
    private Animation animationRobot = new Animation("res/robot_left_0.png",
            "res/robot_left_1.png",
            "res/robot_left_2.png",
            "res/robot_left_3.png");

    public static final int SPEED = 5;

    public RobotController(GameObject gameObject) {
        super(gameObject);
        //this.gameVector.dx = SPEED;
    }

    public RobotController(GameObject gameObject, Animation view) {
        super(gameObject, view);
        this.gameVector.dx = SPEED;
    }

    @Override
    public void run() {
        System.out.println("run");
        super.run();
       //System.out.println("Toa do la" + MainCharacterController.instace.getGameObject().getX());
       System.out.println("Toa do robot" + this.getGameObject().getX() );
        if (gameObject.getX() - mainCharacter.getX() > 200) {
            System.out.println("a");
            this.gameObject.setX(gameObject.getX() - SPEED);
        }


        //this.gameVector.dx = - SPEED;
//        if (this.gameObject.getX() > 300  ) {
//            robot.runLeft();
//        }
//        robot.walkUp();
    }



    @Override
    public void draw(Graphics g) {
        if (gameObject.getX() - mainCharacter.getX()> 200) {
            this.view = animationRobot;

        } else {
            this.view = robotImage;
        }
            super.draw(g);
    }

    public Robot getRobotController() {
        return robot;
    }
}
