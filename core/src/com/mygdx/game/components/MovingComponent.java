package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class MovingComponent implements Component {

    List<Vector3> path;

    Vector3 nextTarget;
    ModelComponent model;

    private final static float speedMul = 15;

    public boolean isMoving() {
        return path.size() > 0;
    }

    public MovingComponent(ModelComponent model) {
        this.path = new ArrayList<>();
        this.model = model;
    }

    private void next() {
        if(path.size() > 0) {
            nextTarget = path.remove(0);
        }else {
            model.move(nextTarget);
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
        if (nextTarget.dst(newPosition) < 2) {
            next();
        }
    }

    private Vector3 calculateSpeed(float delta) {
        Vector3 position = new Vector3();
        model.getInstance().transform.getTranslation(position);

        int speedX = 0;
        int speedY = 0;
        int speedZ = 0;

        if((int) nextTarget.x > (int)position.x) {
            speedX = 1;
        }if((int)nextTarget.y > (int)position.y) {
            speedY = 1;
        }if((int)nextTarget.z > (int)position.z) {
            speedZ = 1;
        }

        if((int)nextTarget.x < (int)position.x) {
            speedX = -1;
        }if((int)nextTarget.y < (int)position.y) {
            speedY = -1;
        }if((int)nextTarget.z < (int)position.z) {
            speedZ = -1;
        }

        float x = speedX * speedMul * delta;
        float y = speedY * speedMul * delta;
        float z = speedZ * speedMul * delta;

        return new Vector3(x, y, z);
    }

    public void setPath(List<Vector3> path) {
        this.path.addAll(path);
    }

}
