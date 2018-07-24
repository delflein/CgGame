package com.mygdx.game.stages.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.components.PlayerComponent;
import com.mygdx.game.utils.GameAssets;
import com.mygdx.game.utils.ModelFactory;

public class PlayerStatus extends Table implements GameUiElement {

    PlayerComponent player;
    Label nameLabel,moneyLabel;
    CardMatrix cardMatrix;
    Label streetLabel;

    PlayerStatus(PlayerComponent player){
        this.player =player;
        this.cardMatrix = new CardMatrix(player).create();
    }

    @Override
    public PlayerStatus create() {
        Skin skin = ModelFactory.loadSkin(GameAssets.DEFAULT_UI_SKIN.filepath);

        nameLabel = new Label("Player " + (player.getId()+1), skin);
        moneyLabel = new Label( player.getMoney()+ " M",skin);
        streetLabel = new Label(player.getCurrentStreet().getName(), skin);


        this.add(nameLabel);
        this.row();
        this.add(moneyLabel);
        this.row();
        this.add(cardMatrix);
        this.row();
        this.add(streetLabel);



        return this;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        this.moneyLabel.setText(player.getMoney()+" M");
        this.streetLabel.setText(player.getCurrentStreet().getName());
    }

}
