package com.mygdx.game.stages.Menus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.screens.MainMenuScreen;

abstract class MenuStage extends Stage {

    public MainMenuScreen screen;
    public Game game;
    public Skin skin;

    MenuStage(Game game, Skin skin, MainMenuScreen screen){
        super(new ScreenViewport());
        this.game = game;
        this.skin = skin;
        this.screen = screen;
    }

    abstract void buildStage();

    public void goBack(){

    }

}

