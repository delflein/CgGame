package com.mygdx.game.stages.Menus;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

abstract class MenuStage extends Stage {

    MenuStage(){
        super(new ScreenViewport());
    }

    abstract void buildStage();

}

