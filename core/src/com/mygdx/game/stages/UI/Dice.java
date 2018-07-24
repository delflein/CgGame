package com.mygdx.game.stages.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.GameStates;
import com.mygdx.game.screens.GameScreen;

import java.util.concurrent.ThreadLocalRandom;

public class Dice extends Group implements GameUiElement {

    private Image diceA, diceB;
    private SpriteDrawable[] faces = new SpriteDrawable[6];

    private static int roll_a, roll_b;
    private int rolls_left = 0;

    GameScreen screen;

    Dice(GameScreen screen) {
        this.screen = screen;
    }

    public Dice create() {
        fillImageArray();

        diceA = createDice();
        diceB = createDice();

        this.addActor(diceA);
        this.addActor(diceB);
        diceA.setPosition(0,0, Align.bottomRight);
        diceB.setPosition(diceA.getX()-diceB.getWidth(),0);

        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                rolls_left = 50;
            }
        });

        return this;
    }

    public static int[] getRoll() {
        return new int[]{roll_a, roll_b};
    }

    public static int getRollSum() {
        return roll_a + roll_b;
    }


    private void fillImageArray() {
        for (int i = 0; i < 6; i++) {
            Texture texture = new Texture(Gdx.files.internal("UI/dice/"+(i+1)+".png"));
            Sprite sprite = new Sprite(texture);
            sprite.setSize(64f,64f);
            SpriteDrawable face = new SpriteDrawable(sprite);
            faces[i] = face;
        }
    }

    private Image createDice() {
        return new Image(faces[0]);
    }

    public void rollDice(){
        int randomNumA = ThreadLocalRandom.current().nextInt(0, 6);
        int randomNumB = ThreadLocalRandom.current().nextInt(0, 6);

        roll_a = randomNumA +1;
        roll_b = randomNumB +1;

        diceA.invalidate();
        diceB.invalidate();
        diceA.setDrawable(faces[randomNumA]);
        diceB.setDrawable(faces[randomNumB]);

    }

    public void toggleDice() {
        this.setVisible(!this.isVisible());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        GameStates currState = GameController.getGameStateMachine().getCurrentState();
        if (currState == GameStates.IDLE) {
            this.setTouchable(Touchable.enabled);
        } else {
            this.setTouchable(Touchable.disabled);
        }
        if (rolls_left > 0) {
            rollDice();
            rolls_left--;
            if (rolls_left == 0) {
                GameController.getGameStateMachine().changeState(GameStates.DICE);

            }
        }
    }
}
