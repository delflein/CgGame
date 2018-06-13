package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;

public class SelectableComponent implements Component {

    private ModelComponent modelComponent;

    public SelectableComponent(ModelComponent modelComponent) {
        this.modelComponent = modelComponent;
    }

    public ModelComponent getModelComponent() {
        return this.modelComponent;
    }
}
