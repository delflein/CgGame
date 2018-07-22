package com.mygdx.game.stages.Menus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.screens.MainMenuScreen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

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

        //Play Button
        final TextButton playButton = new TextButton("Start!", skin);
        playButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                new GameConfigStage(game, screen, skin);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });


        Stage parent = this;

        //Options Button
        final TextButton optionsButton = new TextButton("Options", skin);
        optionsButton.addListener(new InputListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                screen.setStage(new OptionsMenuStage(game, skin, screen, parent));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        //Exit Button
        final TextButton exitButton = new TextButton("Exit!", skin);
        exitButton.addListener(new InputListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.exit(0);
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });


        // Create tables
        Table topTable = new Table().top();
        Table bottomTable = new Table().bottom();


        // Set table structure
        topTable.top();
        topTable.add(title).padTop(5f).colspan(3).expandX().center();
        topTable.row().spaceTop(20).expandX();
        topTable.add(title);

        bottomTable.padBottom(25);
        bottomTable.add(playButton).width(200).expandX().spaceBottom(15);
        bottomTable.row();
        bottomTable.add(optionsButton).width(200).expandX().spaceBottom(25);
        ;
        bottomTable.row().width(200).expandX().spaceBottom(25);
        bottomTable.add(exitButton);

        // Pack table
        topTable.setFillParent(true);
        bottomTable.setFillParent(true);
        topTable.pack();
        bottomTable.pack();

        // Set table's alpha to 0
        topTable.getColor().a = 0f;
        bottomTable.getColor().a = 0f;

        // Adds created table to stage
        this.addActor(topTable);
        this.addActor(bottomTable);

        // To make the table appear smoothly
        topTable.addAction(fadeIn(2f));
        bottomTable.addAction(fadeIn(2f));
    }
}
