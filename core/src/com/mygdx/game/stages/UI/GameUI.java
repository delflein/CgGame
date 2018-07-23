package com.mygdx.game.stages.UI;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.components.PlayerComponent;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.systems.PlayerSystem;

import java.util.ArrayList;
import java.util.List;

public class GameUI extends Stage {
    Engine engine;
    GameScreen screen;
    Skin skin;

    List<PlayerStatus> playerStatus;
    Dice dice;
    TurnButton turnBtn;
    StreetViewTable streetView;

    //

    public GameUI(GameScreen gameScreen, Engine engine){
        this.engine=engine;
        this.screen = gameScreen;
        this.skin = new Skin(Gdx.files.internal("Skins/default/uiskin.json"));
        this.dice = new Dice(this.screen).create();
        this.playerStatus = new ArrayList<PlayerStatus>();
        this.turnBtn = new TurnButton("End Turn!", skin, screen).create();
        this.streetView = new StreetViewTable(screen, skin).create();
        initUI();

    }

    private void initUI(){
        dice.setPosition(Gdx.graphics.getWidth()-dice.getWidth(),0);
        this.addActor(dice);
        this.addActor(turnBtn);

        Table rootPlayerTabel = new Table();
        rootPlayerTabel.setFillParent(true);
        rootPlayerTabel.top();

        ImmutableArray<Entity> playerEntities = engine.getSystem(PlayerSystem.class).getEntities();
        for (Entity playerEntity : playerEntities) {
            PlayerComponent player = playerEntity.getComponent(PlayerComponent.class);
            PlayerStatus ps = new PlayerStatus(player).create();
            playerStatus.add(ps);
            rootPlayerTabel.add(ps).expandX();
        }
        this.addActor(rootPlayerTabel);

        streetView.debug();
        this.addActor(streetView);
        streetView.pack();
    }


}
