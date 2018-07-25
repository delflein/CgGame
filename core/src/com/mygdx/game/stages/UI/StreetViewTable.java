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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.components.Street;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.GameStates;
import com.mygdx.game.utils.GameAssets;
import com.mygdx.game.utils.ModelFactory;

public class StreetViewTable extends Dialog implements GameUiElement {

    RentTableBuilder.RentTable rt;
    TextButton buyBtn, auctBtn;

    Street street;
    boolean showButtons;

    private static SpriteDrawable[] icons = new SpriteDrawable[5];

    private static boolean visible = false;
    private static boolean buyable = false;

    public StreetViewTable(Street street, boolean showButtons) {
        super("", ModelFactory.loadSkin(GameAssets.DEFAULT_UI_SKIN.filepath));
        this.street = street;
        this.showButtons = showButtons;
        this.initIcons();
    }

    private void initIcons() {
        for (int i = 0; i < 5; i++) {
            Sprite sprite = new Sprite(ModelFactory.loadTexture("UI/streetViewer/House" + (i + 1) + ".png"));
            sprite.setSize(32f, 32f);
            SpriteDrawable icon = new SpriteDrawable(sprite);
            icons[i] = icon;
        }
    }

    @Override
    public StreetViewTable create() {
        Pixmap backgroundColor = new Pixmap((int) (Gdx.graphics.getHeight()*0.25), (int) (Gdx.graphics.getWidth()*0.25), Pixmap.Format.RGB888);
        backgroundColor.setColor(Color.WHITE);
        backgroundColor.fill();
        this.setBackground(new Image(new Texture(backgroundColor)).getDrawable());
        this.setVisible(true);
        this.setPosition(Gdx.graphics.getWidth() / 2 - this.getWidth() / 2, Gdx.graphics.getHeight() / 2 - this.getHeight() / 2);
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



        if (GameController.getGameStateMachine().getCurrentState() == GameStates.BUILD &&
                street.hasAllOfType(GameController.getCurrentPlayerComponent()) &&
                street.getType()== Street.StreetType.PROPERTY && street.isBuildable()){

            addBuildButtons();
        } else {
            addBuyButtons();
        }

        this.getContentTable().add(rt).grow();
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
        this.setPosition(Gdx.graphics.getWidth() / 2 - this.getWidth()/2, Gdx.graphics.getHeight() / 2 - this.getHeight() / 2);
        this.setVisible(true);

        this.getButtonTable().row().setActorWidth(rt.getWidth()/2);
        this.getButtonTable().add(buyBtn).padLeft(10);
        this.getButtonTable().add(auctBtn).padRight(10);

        return this;
    }

    private void addBuildButtons() {
        buyBtn = new TextButton("Build!", getSkin());
        buyBtn.setColor(Color.BLACK);
        buyBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                street.buildHouse();
                remove();
            }
        });
        this.getButtonTable().add(buyBtn);
    }

    public void disableBuyOption() {
        buyBtn.setTouchable(Touchable.disabled);
        buyBtn.setText("Insufficient Funds !");
    }

    public void addBuyButtons(){
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
            auctBtn = new TextButton("Pass!", getSkin());
            auctBtn.setColor(Color.BLACK);
            auctBtn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    remove();
                }
            });
            this.getButtonTable().add(buyBtn).expand();
            this.getButtonTable().add(auctBtn).expand();
        }
    }
}
