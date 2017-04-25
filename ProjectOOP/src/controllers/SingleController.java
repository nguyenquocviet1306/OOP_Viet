package controllers;

import managers.ControllerManager;
import models.GameObject;
import models.GameVector;
import views.Animation;
import views.SingleView;
import views.View;

import java.awt.*;

/**
 * Created by Nhan on 3/7/2017.
 */
public class SingleController implements BaseController {
    protected GameObject gameObject;
    protected View view;
    protected GameVector gameVector;
    protected boolean isAlive = true;


    public SingleController() {
    }

    public SingleController(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public SingleController(GameObject gameObject, Animation view) {
        this.gameObject = gameObject;
        this.view = view;
        this.gameVector = new GameVector();

    }

    public SingleController(GameObject gameObject, SingleView view) {
        this.gameObject = gameObject;
        this.view = view;
        this.gameVector = new GameVector();
    }

    public GameVector getGameVector() {
        return gameVector;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    @Override
    public void run() {
        //gameObject.move(this.gameVector);
    }

    @Override
    public void draw(Graphics g) {
        view.draw(g, gameObject);
    }

    public boolean isAlive(){
        return isAlive;
    }
}
