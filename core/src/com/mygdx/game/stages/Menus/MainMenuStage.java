package com.mygdx.game.stages.Menus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MainMenuScreen;

public class MainMenuStage extends MenuStage {

   public MainMenuStage(MainMenuScreen screen, Game game, Skin skin){
        super(game,skin, screen);
        buildStage();
    }

    @Override
    void buildStage() {
        Label title = new Label("Monopoly feat. CG-Superstars", skin);
        title.setColor(Color.GOLD);
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight()*7/8);
        title.setWidth(Gdx.graphics.getWidth());
        this.addActor(title);

        TextButton playButton = new TextButton("New Game!", skin);
        playButton.setWidth(Gdx.graphics.getWidth()/2);
        playButton.setPosition(Gdx.graphics.getWidth()/2-playButton.getWidth()/2,Gdx.graphics.getHeight()/6-playButton.getHeight()/2);
        playButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                screen.setStage(new GameConfigStage(game,screen,skin));

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        this.addActor(playButton);

        TextButton optionsButton = new TextButton("Options", skin);
        optionsButton.setWidth(Gdx.graphics.getWidth()/2);
        optionsButton.setPosition(Gdx.graphics.getWidth()/2-optionsButton.getWidth()/2,Gdx.graphics.getHeight()/10-optionsButton.getHeight()/2);
        optionsButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                screen.setStage(new OptionsMenuStage(game,skin,screen));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        this.addActor(optionsButton);
    }
}
