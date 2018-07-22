package com.mygdx.game.stages.Menus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.screens.MainMenuScreen;

public class OptionsMenuStage extends MenuStage {

    OptionsMenuStage(Game game, Skin skin, MainMenuScreen screen) {

        super(game, skin, screen);
        buildStage();
    }


    @Override
    void buildStage() {
        this.addActor(new GraphicsSettingsTable(skin));

    }
}
