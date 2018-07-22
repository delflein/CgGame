package com.mygdx.game.stages.Menus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.screens.MainMenuScreen;

public class OptionsMenuStage extends MenuStage {

    Stage parent;
    GraphicsSettingsTable gs;

    OptionsMenuStage(Game game, Skin skin, MainMenuScreen screen, Stage parent) {
        super(game, skin, screen);
        this.parent = parent;
        buildStage();
    }


    @Override
    void buildStage() {
        gs = new GraphicsSettingsTable(skin);
        gs.getTbCancel().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                screen.setStage(parent);
            }
        });
        this.addActor(gs);

    }
}
