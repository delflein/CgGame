package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.g3d.Model;

public enum GameAssets {
    BOARD("Board2/Board2_New.g3db", Model.class),
    //DICE("Dice/Dice_New.obj"),
    TOKEN_1("Token/Token_New.g3db", Model.class),
    TOKEN_2("Token2/Token2_New.g3db", Model.class),
    TOKEN_3("Token3/Token3_New.g3db", Model.class),
    TOKEN_4("Token4/Token4_New.g3db", Model.class);

    String filepath;
    Class type;

    GameAssets(String filepath, Class type) {
        this.filepath = filepath;
        this.type = type;
    }
}
