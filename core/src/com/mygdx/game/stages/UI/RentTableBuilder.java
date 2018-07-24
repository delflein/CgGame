package com.mygdx.game.stages.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.components.Street;


public class RentTableBuilder {
        Street street;
        RentTable rt;

        private Skin skin = new Skin(Gdx.files.internal("Skins/default/uiskin.json"));
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
            rt.add(firstCell).colspan(2).left();
            rt.add(secondCell).right();
            return this;
        }

        private RentTableBuilder createFullGroupRow() {
            rt.row().padBottom(5).expandX();
            Label firstCell = new Label("Rent with whole group", skin);
            firstCell.setColor(Color.BLACK);
            Label secondCell = new Label((street.getBaseRent() * 2) + "M", skin);
            secondCell.setColor(Color.BLACK);
            rt.add(firstCell).colspan(2).left();
            rt.add(secondCell).right();
            return this;
        }

        private RentTableBuilder createHouseRow(int icon) {
            if (this.icons == null) {
                return this;
            }
            rt.row().padBottom(10).expandX();
            Label firstCell = new Label("Rent with", skin);
            firstCell.setColor(Color.BLACK);
            Label secondCell = new Label(street.getRents()[icon - 1] + "M", skin);
            secondCell.setColor(Color.BLACK);
            Image image = new Image();
            image.setDrawable(icons[icon - 1]);
            rt.add(firstCell).left();
            rt.add(image).center();
            rt.add(secondCell).right();

            return this;
        }

        public RentTable createPropertyTable() {
            rt.row().fill().colspan(3).center();
            rt.add(createColorElement());
            rt.row();
            return this.createRentRow().
                    createFullGroupRow().
                    createHouseRow(1).
                    createHouseRow(2).
                    createHouseRow(3).
                    createHouseRow(4).buildTable();
        }

    public RentTable createFacilityTable() {
        Image background;
        if (street.getName().equals("Electric Company")) {
            background = new Image(new Texture(Gdx.files.internal("UI/electric.png")));
        } else {
            background = new Image(new Texture(Gdx.files.internal("UI/Waterworks.png")));
        }
        background.setSize(rt.getWidth() * 0.3f, rt.getHeight() * 0.3f);
        rt.add(background);
        rt.row();
        Label name = new Label(street.getName(), skin);
        name.setColor(Color.BLACK);
        rt.add(name);
        rt.row();
        TextArea text = createFacilityText();
        rt.add(text);
        return rt;
    }

    private TextArea createFacilityText() {
        return new TextArea("If ONE Utility is owned, rent is 4x the amount shown on the dice when the opponent rolled, but if BOTH Utilities are owned, rent is 10x the amount shown on the dice.", skin);
    }


    private Actor createColorElement() {
        Table t = new Table();
        Label name = new Label(street.getName(), skin);
        name.setColor(Color.BLACK);
        t.add(name).center();
        Pixmap backgroundColor = new Pixmap(280, 70, Pixmap.Format.RGB888);
        //TODO Set correct colour code
        backgroundColor.setColor(Color.DARK_GRAY);
        backgroundColor.fill();
        t.setBackground(new Image(new Texture(backgroundColor)).getDrawable());
        return t;
    }

    private RentTable buildTable() {
            return rt;
        }

    protected class RentTable extends Table {

    }
}

