package com.mygdx.game.stages.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.components.MonopolyColors;
import com.mygdx.game.components.PlayerComponent;
import com.mygdx.game.components.Rectangle;
import com.mygdx.game.components.Street;
import com.mygdx.game.controller.GameController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardMatrix extends Table implements GameUiElement {

    private PlayerComponent player;

    private Rectangle cardMatrix[][];
    private Map<Street, Rectangle> mapping;

    private Color[] colors = new Color[] {};

    CardMatrix(PlayerComponent player) {
        this.player = player;
    }

    @Override
    public CardMatrix create() {
        int rows = 4;
        int columns = 10;

        cardMatrix = new Rectangle[rows][columns];
        mapping = new HashMap<>();

        List<Street> streets = Street.getStreets();
        Map<Color, List<Street>> streetByColor = new HashMap<>();

        for (Street street : streets) {
            if(streetByColor.containsKey(street.getColorCode())) {
                streetByColor.get(street.getColorCode()).add(street);
            }else{
                List<Street> tmp = new ArrayList<>();
                tmp.add(street);
                streetByColor.put(street.getColorCode(), tmp);
            }
        }
        streetByColor.remove(null);

        /*Street[][] test = new Street[columns][rows];

        int bla = 0;

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                Street x = streets.get(bla);
                test[i][j] = x;
                bla++;
            }
        }*/

        for (int x = 0; x < rows; x++) {
            this.row().pad(5f);
            for (int y = 0; y < columns; y++) {
                List<Street> colorStreets = streetByColor.get(MonopolyColors.getColorByIndex(y));

                Street street = null;
                if(colorStreets.size() > x ) {
                    street = colorStreets.get(x);
                }

                Color color;
                Touchable touchable;
                if(street == null) {
                    color = Color.CLEAR;
                    touchable = Touchable.disabled;
                }else{
                    color = street.getColorCode();
                    touchable = Touchable.enabled;
                }

                Rectangle streetRectangle = new Rectangle(0, 0, 5, 10, Color.CLEAR, street);
                streetRectangle.setTouchable(Touchable.disabled);
                streetRectangle.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        GameController.getGameUi().addActor(new StreetViewTable(streetRectangle.getStreet(), false).create());
                    }
                });

                cardMatrix[x][y] = streetRectangle;
                mapping.put(street, streetRectangle);
                this.add(streetRectangle);
            }
        }

        //cleanMatrix();
        return this;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        List<Street> owned_streets = player.getOwned_streets();
        for (Street owned_street : owned_streets) {
            Rectangle rect = mapping.get(owned_street);
            rect.changeColor(owned_street.getColorCode());
            rect.setTouchable(Touchable.enabled);
        }
    }

    private void cleanMatrix() {
        int[] columns = {2,1,1,1,1,1,1,2,0,2};
        for (int i = 0; i < columns.length ; i++) {
            for (int j = 0; j < columns[i]; j++){
                cardMatrix[j][i].setColor(Color.CLEAR);
            }
        }
    }
}
