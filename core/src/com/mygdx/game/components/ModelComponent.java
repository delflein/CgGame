package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class ModelComponent implements Component {

    private Model model;
    private ModelInstance instance;

    public final Vector3 center = new Vector3();
    public final Vector3 dimensions = new Vector3();
    public final float radius;

    public final BoundingBox bounds = new BoundingBox();

    public ModelComponent(Model model, float x, float y, float z) {
        this.model = model;
        this.instance = new ModelInstance(model, new Matrix4().setToTranslation(x, y, z));

        instance.calculateBoundingBox(bounds);
        bounds.mul(instance.transform);
        bounds.getCenter(center);
        bounds.getDimensions(dimensions);
        radius = dimensions.len() / 2f;
    }

    public boolean isVisible(final Camera cam) {
        Vector3 position = new Vector3();
        instance.transform.getTranslation(position);
        position.add(center);
        return cam.frustum.boundsInFrustum(bounds);
    }

    public Model getModel() {
        return model;
    }

    public ModelInstance getInstance() {
        return instance;
    }
}
