package com.mygdx.game.stages.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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

        private RentTableBuilder createRentRow() {
            rt.row().padBottom(5).expandX();
            Label firstCell = new Label("Rent", skin);
            Label secondCell = new Label(street.getBaseRent() + "M", skin);
            rt.add(firstCell).colspan(2).left();
            rt.add(secondCell).right();
            return this;
        }

        private RentTableBuilder createFullGroupRow() {
            rt.row().padBottom(5).expandX();
            Label firstCell = new Label("Rent with whole group", skin);
            Label secondCell = new Label((street.getBaseRent() * 2) + "M", skin);
            rt.add(firstCell).colspan(2).left();
            rt.add(secondCell).right();
            return this;
        }

        private RentTableBuilder createHouseRow(int icon) {
            rt.row().padBottom(10).expandX();
            Label firstCell = new Label("Rent with", skin);
            Label secondCell = new Label(street.getRents()[icon - 1] + "M", skin);
            Image image = new Image();
            image.setDrawable(icons[icon - 1]);
            rt.add(firstCell).left();
            rt.add(image).center();
            rt.add(secondCell).right();

            return this;
        }

        public RentTable createPropertyTable() {
            rt.row().fill().colspan(3).center();
            rt.add(new Label(street.getName(), skin));
            rt.row();
            return this.createRentRow().
                    createFullGroupRow().
                    createHouseRow(1).
                    createHouseRow(2).
                    createHouseRow(3).
                    createHouseRow(4).buildTable();
        }

        private RentTable buildTable() {
            return rt;
        }

    protected class RentTable extends Table {

    }
}

