package com.mygdx.game.stages.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.components.Street;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.GameStates;

public class StreetViewTable extends Dialog implements GameUiElement {

    RentTableBuilder.RentTable rt;
    TextButton buyBtn, auctBtn;

    Street street;
    boolean showButtons;

    private SpriteDrawable[] icons = new SpriteDrawable[5];

    private static boolean visible = false;
    private static boolean buyable = false;

    public StreetViewTable(Street street, boolean showButtons) {
        super("", new Skin(Gdx.files.internal("Skins/default/uiskin.json")));
        this.street = street;
        this.showButtons = showButtons;
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
        backgroundColor.setColor(Color.WHITE);
        backgroundColor.fill();
        this.setBackground(new Image(new Texture(backgroundColor)).getDrawable());
        this.setFillParent(false);
        this.setPosition(400, 400);
        this.setSize(300, 400);
        this.setVisible(true);
        this.setTouchable(Touchable.enabled);
        this.addListener(new DragListener() {
            public void drag(InputEvent event, float x, float y, int pointer) {
                moveBy(x - getWidth() / 2, y - getHeight() / 2);
            }
        });

        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(x < getOriginX() || x > (getOriginX() + getWidth()) || y < getOriginY() || y > (getOriginY() + getHeight())) {
                    remove();
                }
            }
        });

        if(showButtons) {
            buyBtn = new TextButton("Buy!", getSkin());
            buyBtn.setColor(Color.BLACK);
            buyBtn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    GameController.getGameStateMachine().changeState(GameStates.BUY);
                    remove();
                }
            });
            auctBtn = new TextButton("Auction!", getSkin());
            auctBtn.setColor(Color.BLACK);
            auctBtn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    remove();
                }
            });
            this.getButtonTable().add(buyBtn);
            this.getButtonTable().add(auctBtn);
        }
        this.getContentTable().add(rt);

        this.getContentTable().clearChildren();
        switch (street.getType()) {
            case PROPERTY:
                rt = new RentTableBuilder(street, icons).createPropertyTable();
                this.getContentTable().add(rt);
                break;
            case FACILITY:
                rt = new RentTableBuilder(street).createFacilityTable();
                this.getContentTable().add(rt);
                break;
            case STATION:
                rt = new RentTableBuilder(street).createStationTable();
                this.getContentTable().add(rt);
        }
        this.pack();
        this.setVisible(true);

        return this;
    }

    public void disableBuyOption() {
        buyBtn.setTouchable(Touchable.disabled);
        buyBtn.setText("Insufficient Funds !");
    }
}
