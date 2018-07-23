package com.mygdx.game.stages.UI;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.components.PlayerComponent;
import com.mygdx.game.components.Street;
import com.mygdx.game.components.Street.StreetType;
import com.mygdx.game.controller.GameStates;
import com.mygdx.game.screens.GameScreen;

public class StreetViewTable extends Table implements GameUiElement {

    GameScreen screen;
    Skin skin;
    Label streetNameLabel;
    RentTable rt;
    private SpriteDrawable[] icons = new SpriteDrawable[5];

    public StreetViewTable(GameScreen screen, Skin skin) {
        this.screen = screen;
        this.skin = skin;
        this.streetNameLabel = new Label(null, skin);
        ;
    }

    private void initIcons() {
        for (int i = 0; i < 5; i++) {
            Texture texture = new Texture(Gdx.files.internal("UI/streetViewer/House" + (i + 1) + ".png"));
            Sprite sprite = new Sprite(texture);
            sprite.setSize(32f, 32f);
            SpriteDrawable icon = new SpriteDrawable(sprite);
            icons[i] = icon;
        }
    }

    @Override
    public StreetViewTable create() {
        this.initIcons();
        Pixmap backgroundColor = new Pixmap(300, 400, Pixmap.Format.RGB888);
        backgroundColor.setColor(Color.LIGHT_GRAY);
        backgroundColor.fill();
        this.setBackground(new Image(new Texture(backgroundColor)).getDrawable());
        this.setFillParent(false);
        this.setPosition(400, 400);
        this.setVisible(true);
        this.setTouchable(Touchable.enabled);
        this.top();
        Table current = this;
        current.addListener(new DragListener() {
            public void drag(InputEvent event, float x, float y, int pointer) {
                current.moveBy(x - current.getWidth() / 2, y - current.getHeight() / 2);
            }
        });


        this.row();
        this.add(rt).fill();
        return this;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //this.setVisible(screen.getGameController().getGameStateMachine().getCurrentState() == GameStates.FIELD);
        if (screen.getGameController().getGameStateMachine().getCurrentState() == GameStates.FIELD) {
            this.removeActor(rt);

            Entity entity = screen.getGameController().getCurrentPlayer();
            PlayerComponent player = entity.getComponent(PlayerComponent.class);
            Street street = player.getCurrentStreet();
            if (street.getType() == StreetType.PROPERTY) {
                rt = new RentTable.RentTableBuilder(street, icons).createPropertyTable();
                this.add(rt);
                this.pack();
            }


            // }


        }
    }
}
