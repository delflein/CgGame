package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;

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

    public boolean intersects(Ray ray) {
        Vector3 position = new Vector3();
        instance.transform.getTranslation(position).add(center);
        return Intersector.intersectRayBounds(ray, bounds, null);
    }

    public void tra(float x, float y, float z) {
        tra(new Vector3(x, y, z));
    }

    public void tra(Vector3 tra) {
        instance.transform.translate(tra);
        instance.calculateBoundingBox(bounds);
        this.bounds.mul(instance.transform);
    }

    public void move(Vector3 newPosition) {
        instance.transform.setToTranslation(newPosition);
        instance.calculateBoundingBox(bounds);
        this.bounds.mul(instance.transform);
    }

    public Model getModel() {
        return model;
    }

    public ModelInstance getInstance() {
        return instance;
    }
}
