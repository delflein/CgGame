package com.mygdx.game.stages.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.TelegramProvider;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.components.PlayerComponent;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.GameStates;

public class WinStage extends Stage {
    public WinStage(PlayerComponent currentPlayerComponent) {
        Skin skin = GameController.getGameUi().getSkin();
        Button confirm = new TextButton("Wohoo!",skin);
        confirm.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                MessageDispatcher md = new MessageDispatcher();
                md.dispatchMessage(
                        0,               // Immediate message if <= 0; delayed otherwise
                        null,              // It can be null
                        GameController.getGameStateMachine(),           // It can be null, see the "Multiple Recipients" section below
                        1337,         // Any user-defined int code
                        null,           // Optional data accompanying the message; it can be null
                        false); // Whether the sender needs the return receipt or not

            }
        });
        int winplayer = currentPlayerComponent.getId()+1;
        Label wintext = new Label("Player " + winplayer +" won the Game",skin);


        Dialog windialog = new Dialog("You Won!", skin);
        windialog.getContentTable().add(wintext).center().expand();
        windialog.setFillParent(true);
        windialog.getTitleTable().center();
        windialog.getButtonTable().add(confirm).expandX();
        this.addActor(windialog);
        Gdx.input.setInputProcessor(this);
    }
}
