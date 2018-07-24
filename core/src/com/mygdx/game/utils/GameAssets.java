package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public enum GameAssets {
    BOARD("Board2/Board2_New.g3db", Model.class),
    //DICE("Dice/Dice_New.obj"),
    TOKEN_1("Token/Token_New.g3db", Model.class),
    TOKEN_2("Token2/Token2_New.g3db", Model.class),
    TOKEN_3("Token3/Token3_New.g3db", Model.class),
    TOKEN_4("Token4/Token4_New.g3db", Model.class),
    CHANCE_CARDSTACK("CChest Card Stack/cchest_cardstack.g3db", Model.class),
    CHEST_CARDSTACK("Chance Card Stack/chance_cardstack.g3db", Model.class),
    HOUSE_1("UI/streetViewer/House1.png",Texture.class),
    HOUSE_2("UI/streetViewer/House2.png",Texture.class),
    HOUSE_3("UI/streetViewer/House3.png",Texture.class),
    HOUSE_4("UI/streetViewer/House4.png",Texture.class),
    HOUSE_5("UI/streetViewer/House5.png",Texture.class),
    ELECTRIC("UI/electric.png", Texture.class),
    STATION("UI/station.png", Texture.class),
    WATERWORKS("UI/Waterworks.png", Texture.class),
    DICE_SIDE_1("UI/dice/1.png", Texture.class),
    DICE_SIDE_2("UI/dice/2.png", Texture.class),
    DICE_SIDE_3("UI/dice/3.png", Texture.class),
    DICE_SIDE_4("UI/dice/4.png", Texture.class),
    DICE_SIDE_5("UI/dice/5.png", Texture.class),
    DICE_SIDE_6("UI/dice/6.png", Texture.class),
    DEFAULT_UI_SKIN("Skins/default/uiskin.json", Skin.class);

    public String filepath;
    public Class type;

    GameAssets(String filepath, Class type) {
        this.filepath = filepath;
        this.type = type;
    }
}
