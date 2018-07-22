package com.mygdx.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.mygdx.game.components.ModelComponent;
import com.mygdx.game.components.MovingComponent;

public class RenderSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;
    private ModelBatch batch;
    private Environment environment;

    private Camera cam;

    private int visibleCount;

    public RenderSystem(ModelBatch batch, Environment environment, Camera cam) {
        this.batch = batch;
        this.environment = environment;
        this.cam = cam;
    }

    public void addedToEngine(Engine e) {
        entities = e.getEntitiesFor(Family.all(ModelComponent.class).get());
    }

    public void update(float delta) {
        visibleCount = 0;
        for (Entity entity : entities) {
            ModelComponent mod = entity.getComponent(ModelComponent.class);
            if (mod.isVisible(cam)) {
                visibleCount++;
                batch.render(mod.getInstance(), environment);
            }
            MovingComponent mov = entity.getComponent(MovingComponent.class);
            if(mov != null) {
                mov.update(delta);
            }
        }
    }

    public ImmutableArray<Entity> getEntities() {
        return this.entities;
    }

    public int getVisibleCount() {
        return this.visibleCount;
    }
}
