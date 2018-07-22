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

import java.util.concurrent.ThreadLocalRandom;

public class Dice extends Image implements GameUiElement {

    private Image diceA, diceB;
    private SpriteDrawable[] faces = new SpriteDrawable[6];

    public Group create() {
        fillImageArray();

        diceA = createDice();
        diceB = createDice();

        Group group = new Group();
        group.addActor(diceA);
        group.addActor(diceB);
        diceA.setPosition(0,0, Align.bottomRight);
        diceB.setPosition(diceA.getX()-diceB.getWidth(),0);

        group.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    rollDice();
            }
        });

        return group;
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
        int randomNumA = ThreadLocalRandom.current().nextInt(0, 5 + 1);
        int randomNumB = ThreadLocalRandom.current().nextInt(0, 5 + 1);
        diceA.invalidate();
        diceB.invalidate();
        diceA.setDrawable(faces[randomNumA]);
        diceB.setDrawable(faces[randomNumB]);

    }


}
