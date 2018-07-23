package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class MovingComponent implements Component {

    List<Vector3> path;

    Vector3 nextTarget;
    ModelComponent model;

    private final static float speedMul = 20;

    public boolean isMoving() {
        return nextTarget != null;
    }

    public MovingComponent(ModelComponent model) {
        this.path = new ArrayList<>();
        this.model = model;
    }

    private void next() {
        if(path.size() > 0) {
            nextTarget = path.remove(0);
        }else {
            nextTarget = null;
        }
    }

    public void update(float delta){
        if(nextTarget == null) {
            if(path.size() > 0) {
                nextTarget = path.remove(0);
            }else{
                return;
            }
        }
        Vector3 speed = calculateSpeed(delta);
        model.tra(speed);
        Vector3 newPosition = new Vector3();
        model.getInstance().transform.getTranslation(newPosition);
        if (nextTarget.dst(newPosition) < 1) {
            next();
        }
    }

    private Vector3 calculateSpeed(float delta) {
        Vector3 position = new Vector3();
        model.getInstance().transform.getTranslation(position);

        int speedX = 0;
        int speedY = 0;
        int speedZ = 0;

        if(nextTarget.x > position.x) {
            speedX = 1;
        }if(nextTarget.y > position.y) {
            speedY = 1;
        }if(nextTarget.z > position.z) {
            speedZ = 1;
        }

        if(nextTarget.x < position.x) {
            speedX = -1;
        }if(nextTarget.y < position.y) {
            speedY = -1;
        }if(nextTarget.z < position.z) {
            speedZ = -1;
        }

        return new Vector3(speedX * speedMul * delta, speedY * speedMul * delta, speedZ * speedMul * delta);
    }

    public void setPath(List<Vector3> path) {
        this.path.addAll(path);
    }

}
