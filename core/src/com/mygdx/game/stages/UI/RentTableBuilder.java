package com.mygdx.game.stages.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.components.Street;
import com.mygdx.game.utils.GameAssets;
import com.mygdx.game.utils.ModelFactory;


public class RentTableBuilder {
        Street street;
        RentTable rt;

        private Skin skin = ModelFactory.loadSkin(GameAssets.DEFAULT_UI_SKIN.filepath);
        private SpriteDrawable[] icons;

        public RentTableBuilder(Street street, SpriteDrawable[] icons) {
            this.street = street;
            this.icons = icons;
            this.rt = new RentTable();

        }

    public RentTableBuilder(Street street) {
        this.street = street;
        this.icons = null;
        this.rt = new RentTable();
    }

        private RentTableBuilder createRentRow() {
            rt.row().padBottom(5).expandX();
            Label firstCell = new Label("Rent", skin);
            firstCell.setColor(Color.BLACK);
            Label secondCell = new Label(street.getBaseRent() + "M", skin);
            secondCell.setColor(Color.BLACK);
            float padding = (float) (rt.getWidth()*0.1);
            rt.add(firstCell).colspan(2).left().padLeft(padding);
            rt.add(secondCell).right().padRight(padding);
            return this;
        }

        private RentTableBuilder createFullGroupRow() {
            rt.row().padBottom(5).expandX();
            Label firstCell = new Label("Rent with whole group", skin);
            firstCell.setColor(Color.BLACK);
            Label secondCell = new Label((street.getBaseRent() * 2) + "M", skin);
            secondCell.setColor(Color.BLACK);
            float padding = (float) (rt.getWidth()*0.1);
            rt.add(firstCell).colspan(2).left().padLeft(padding);
            rt.add(secondCell).right().padRight(padding);
            return this;
        }

        private RentTableBuilder createHouseRow(int icon) {
            if (this.icons == null) {
                return this;
            }
            rt.row().expandX();
            Label firstCell = new Label("Rent with", skin);
            firstCell.setColor(Color.BLACK);
            Label secondCell = new Label(street.getRents()[icon - 1] + "M", skin);
            secondCell.setColor(Color.BLACK);
            Image image = new Image();
            image.setDrawable(icons[icon - 1]);
            float padding = (float) (rt.getWidth()*0.1);
            rt.add(firstCell).left().padLeft(padding);
            rt.add(image).center();
            rt.add(secondCell).right().padRight(padding);

            return this;
        }

    private RentTableBuilder createStationRow(int i) {
        rt.row().spaceBottom(10).expandX();
        Label firstCell = new Label("Rent with " + i + " stations", skin);
        firstCell.setColor(Color.BLACK);
        Label secondCell = new Label(street.getRents()[i - 1] + "M", skin);
        secondCell.setColor(Color.BLACK);
        float padding = (float) (rt.getWidth()*0.1);
        rt.add(firstCell).left().colspan(2).padLeft(padding);
        rt.add(secondCell).right().padRight(padding).colspan(1);

        return this;
    }

        public RentTable createPropertyTable() {
            rt.row().colspan(3).expandX().center().padTop(10).padBottom(5);
            rt.add(createColorElement());
            rt.pack();
            return this.createRentRow().
                    createFullGroupRow().
                    createHouseRow(1).
                    createHouseRow(2).
                    createHouseRow(3).
                    createHouseRow(4).
                    createHouseRow(5).
                    buildTable();
        }

    public RentTable createFacilityTable() {
        Image icon;
        if (street.getName().equals("Electric Company")) {
            Texture tex = ModelFactory.loadTexture("UI/electric.png");
            TextureRegionDrawable drawi =new TextureRegionDrawable(new TextureRegion (tex,(int)(Gdx.graphics.getWidth()*0.05), (int)(Gdx.graphics.getHeight()*0.1)));
            icon = new Image(drawi);
        } else {
            icon = new Image(ModelFactory.loadTexture("UI/Waterworks.png"));
        }
        //rt.add(icon).center();
        rt.row().expand();
        Label name = new Label(street.getName(), skin);
        name.setColor(Color.BLACK);
        rt.add(name);
        rt.row().expand();
        Label text = createFacilityText();
        rt.add(text).grow();
        rt.debug();
        return this.buildTable();
    }


    public RentTable createStationTable() {
        Image background = new Image(ModelFactory.loadTexture("UI/station.png"));
        rt.setSize(rt.getParent().getWidth(), rt.getParent().getHeight());
        background.setSize(rt.getWidth() * 0.3f, rt.getHeight() * 0.3f);
        rt.add(background).center().colspan(3);
        rt.row();
        rt.pack();
        Label name = new Label(street.getName(), skin);
        name.setColor(Color.BLACK);
        rt.add(name).colspan(3).center().spaceBottom(20f);
        return this.createStationRow(1).
                createStationRow(2).
                createStationRow(3).
                createStationRow(4).buildTable();
    }




    private Label createFacilityText() {
        Label text = new Label("", skin);
        text.setColor(Color.BLACK);
        rt.pack();
        //text.setWidth((float) (rt.getWidth()*0.9));
        text.setWrap(true);
        text.setText("If ONE Utility is owned, rent is 4x the amount shown on the dice when the opponent rolled, but if BOTH Utilities are owned, rent is 10x the amount shown on the dice.");

        return text;
    }


    private Actor createColorElement() {
        Table t = new Table();
        Label name = new Label(street.getName(), skin);
        name.setColor(Color.BLACK);
        t.add(name).center().expandX();
        Pixmap backgroundColor = new Pixmap((int)(Gdx.graphics.getWidth()*(0.95)*0.2), (int)(Gdx.graphics.getHeight()*(0.95)*0.1), Pixmap.Format.RGB888);
        backgroundColor.setColor(street.getColorCode());
        backgroundColor.fill();
        t.setBackground(new Image(new Texture(backgroundColor)).getDrawable());
        return t;
    }

    private RentTable buildTable() {
            rt.invalidateHierarchy();
            rt.pack();
            return rt;
        }


    class RentTable extends Table {

    }
}

