package com.mygdx.game.stages.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.components.Street;

public class RentTable extends Table {


    RentTable() {
    }

    public static class RentTableBuilder {
        Street street;
        RentTable rt;
        Label firstCell, secondCell;
        Image image;

        private Skin skin = new Skin(Gdx.files.internal("Skins/default/uiskin.json"));
        private SpriteDrawable[] icons;

        public RentTableBuilder(Street street, SpriteDrawable[] icons) {
            this.street = street;
            rt = new RentTable();
            this.icons = icons;
        }

        private RentTableBuilder createRentRow() {
            rt.row().padBottom(5).expandX();
            firstCell = new Label("Rent", skin);
            secondCell = new Label(street.getBaseRent() + "M", skin);
            rt.add(firstCell).colspan(2).left();
            rt.add(secondCell);
            return this;
        }

        private RentTableBuilder createFullGroupRow() {
            rt.row().padBottom(5).expandX();
            firstCell = new Label("Rent with all streets", skin);
            secondCell = new Label((street.getBaseRent() * 2) + "M", skin);
            rt.add(firstCell).colspan(2);
            rt.add(secondCell);
            return this;
        }

        private RentTableBuilder createHouseRow(int icon) {
            rt.row().padBottom(5).expandX();
            firstCell = new Label("Rent with", skin);
            secondCell = new Label(street.getRents()[icon - 1] + "M", skin);
            secondCell.setText("M");
            //TODO Set correct Texture
            image = new Image();
            image.setDrawable(icons[icon]);
            rt.add(firstCell);
            rt.add(image);
            rt.add(secondCell);

            return this;
        }

        public RentTable createPropertyTable() {
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
    }


}
