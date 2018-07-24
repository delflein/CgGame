package com.mygdx.game.stages.UI;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.controller.GameStates;
import com.mygdx.game.screens.GameScreen;

public class TurnButton extends TextButton implements GameUiElement {


    GameScreen screen;

    public TurnButton(String text, Skin skin, GameScreen screen) {
        super(text, skin);
        this.screen = screen;
    }

    @Override
    public TurnButton create() {
        this.setPosition(20, 20);
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                screen.getGameController().getGameStateMachine().changeState(GameStates.NEXT_PLAYER);
            }
        });
        return this;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
