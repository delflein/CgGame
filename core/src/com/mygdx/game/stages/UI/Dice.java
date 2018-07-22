package com.mygdx.game.stages.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.screens.GameScreen;

import java.util.concurrent.ThreadLocalRandom;

public class Dice extends Group implements GameUiElement {

    private Image diceA, diceB;
    private SpriteDrawable[] faces = new SpriteDrawable[6];

    private int roll_a, roll_b;
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

    @Override
    public void act(float delta) {
        super.act(delta);
        if(rolls_left > 0) {
            rollDice();
            rolls_left--;
            if (rolls_left == 0) {
                screen.getGameController().movePlayer(roll_a, roll_b);
            }
        }
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


}
