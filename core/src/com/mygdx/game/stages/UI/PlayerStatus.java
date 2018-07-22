package com.mygdx.game.stages.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.components.PlayerComponent;

public class PlayerStatus extends Table implements GameUiElement {

    PlayerComponent player;
    Label nameLabel,moneyLabel;
    CardViewer cardViewer;
    Label streetLabel;

    PlayerStatus(PlayerComponent player){
        this.player =player;
        this.cardViewer = new CardViewer().create();
    }

    @Override
    public PlayerStatus create() {
        Skin skin = new Skin(Gdx.files.internal("Skins/default/uiskin.json"));

        nameLabel = new Label("Player " + (player.getId()+1), skin);
        moneyLabel = new Label( player.getMoney()+ " M",skin);
        streetLabel = new Label(player.getCurrentStreet().getName(), skin);


        this.add(nameLabel);
        this.row();
        this.add(moneyLabel);
        this.row();
        this.add(cardViewer);
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
