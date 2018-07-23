package com.mygdx.game.stages.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.components.PlayerComponent;
import com.mygdx.game.components.Rectangle;

public class CardMatrix extends Table implements GameUiElement {

    private PlayerComponent player;

    private Rectangle cardMatrix[][];

    @Override
    public CardMatrix create() {
        int rows = 4;
        int columns = 10;

        cardMatrix = new Rectangle[rows][columns];

        for (int x = 0; x < rows; x++) {
            this.row().pad(5f);
            for (int y = 0; y < columns; y++) {
                Rectangle streetRectangle = new Rectangle(0, 0, 5, 10, Color.LIGHT_GRAY);
                cardMatrix[x][y] = streetRectangle;
                this.add(streetRectangle);
            }
        }

        cleanMatrix();
        return this;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
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
