package com.mygdx.game.stages.Menus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MainMenuScreen;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

public class GameConfigStage extends MenuStage {

    private Integer players=1;
    private Float startCash= 15.0f;
    private Float cashOverGo= 2.0f;
    private boolean directGoDoubled = false;

    public GameConfigStage(Game game, MainMenuScreen screen, Skin skin){
        super(game,skin,screen);
        buildStage();
    }

    @Override
    void buildStage() {
        // Title Label
        final Label title = new Label("Game Settings", skin);
        title.setColor(Color.GOLD);

        //Slider for choosing start money
        final Slider cashSlider = new Slider(1.0f,30.0f,0.1f,false,skin);
        cashSlider.setValue(startCash);
        final Label cashsliderLabel = new Label("Start Cash:",skin);
        final Label cashIndicatorLabel = new Label("15.0M",skin);

        //Slider for choosing amount of money when going over GO!
        final Slider goCashSlider = new Slider(1.0f,30.0f,0.1f,false,skin);
        cashSlider.setValue(startCash);
        Label goCashSliderLabel = new Label("Start Cash:",skin);
        final Label goCashIndicatorLabel = new Label("2.0M",skin);

        cashSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                handleFloatSliderChange(cashSlider,cashIndicatorLabel,startCash);
            }
        });

        goCashSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               handleFloatSliderChange(goCashSlider,goCashIndicatorLabel,cashOverGo);
            }
        });

        //Checkbox for toggling double cash on directly hitting GO
        final CheckBox doubleCashBox = new CheckBox(null,skin);
        final Label doubleCashBoxLabel = new Label("Double cash when hitting go:",skin);
        doubleCashBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                directGoDoubled = !directGoDoubled;
            }
        });

        //Play Button
        final TextButton playButton = new TextButton("Start!", skin);
        playButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                screen.stopBGM();
                game.setScreen(new GameScreen(game));

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        //Play Button
        final TextButton backButton = new TextButton("Back!", skin);
        backButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                screen.setStage(new MainMenuStage(screen,game,skin));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        //Player slider for choosing amount of Players
        final Slider playerSlider = new Slider(1.0f,4.0f,1.0f,false,skin);
        Label sliderLabel = new Label("Players:",skin);
        final Label playerIndicatorLabel = new Label("1",skin);

        playerSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                players = (int)playerSlider.getValue();
                playerIndicatorLabel.setText(players.toString());
            }
        });

        // Create tables
       Table topTable = new Table().top();
       Table bottomTable = new Table().bottom();
       //topTable.debug();
       //bottomTable.debug();


        // Set table structure
        topTable.top();
        topTable.add(title).padTop(5f).colspan(3).expandX().center();
        topTable.row().spaceTop(20).expandX();
        topTable.add(sliderLabel);
        topTable.add(playerSlider).fill();
        topTable.add(playerIndicatorLabel).center().expandX();

        topTable.row().space(20,0,0,0);

        topTable.row().expandX();
        topTable.add(cashsliderLabel);
        topTable.add(cashSlider).fill();
        topTable.add(cashIndicatorLabel).width(30).center().expandX();

        topTable.row();
        topTable.add(doubleCashBoxLabel);
        topTable.add(doubleCashBox).fill();

        bottomTable.padBottom(25);
        bottomTable.add(backButton).width(200).expandX().spaceBottom(15);
        bottomTable.row();
        bottomTable.add(playButton).width(200).expandX();

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

    private void handleFloatSliderChange(Slider slider, Label labelToChange, Float varToChange){
        DecimalFormat df = new DecimalFormat(".#",new DecimalFormatSymbols(Locale.ENGLISH));
        String value = df.format(slider.getValue());
        cashOverGo = Float.parseFloat(value);
        labelToChange.setText(value+"M");
    }
}
